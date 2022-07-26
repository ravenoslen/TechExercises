package exercises.interview.StepDefinition;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exercises.interview.Utils.ScenarioContext;
import exercises.interview.model.apis.DataUsaResponse;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class ExercisesDefinition extends BaseDefinition {

    Logger log = LogManager.getLogger(this.getClass());
    Response response;
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ExercisesDefinition(ScenarioContext scenarioContext) {
        super(scenarioContext);
        this.scenarioContext = scenarioContext;
    }

    @Given("an array list of {string} possible numbers")
    public void anArrayListOfnPossibleNumbers(String nTimes) {
        Random random = new Random();
        List<Integer> integerList = new ArrayList<>();
        int counter = 0;
        while (counter < Integer.parseInt(nTimes)) {
            integerList.add(random.nextInt());
            counter++;
        }
        log.info("List of " + nTimes + " values are the following:\n" + integerList);
        scenarioContext.setScenarioContext("EVIDENCE", "List of " + nTimes + " values are the following:\n" + integerList);
        Assert.assertEquals(integerList.size(), Integer.parseInt(nTimes));
        scenarioContext.setScenarioContext("LIST", integerList);
    }

    @When("check all values")
    public void checkAllValues() {
        List<Object> objectList = (List<Object>) scenarioContext.getScenarioContext("LIST");
        log.info("LIST => " + objectList);
        for (Object list : objectList) {
            try {
                Integer.parseInt(String.valueOf(list));
                log.info(list + " is a valid value");
                scenarioContext.setScenarioContext("EVIDENCE", list + " is a valid value");
                Assert.assertTrue(true);
            } catch (NumberFormatException e) {
                log.error(list + " is not a valid value");
                scenarioContext.setScenarioContext("EVIDENCE", list + " is not a valid value");
                Assert.fail();
            }
        }
    }

    @Then("get max and min values")
    public void getMaxAndMinValues() {
        int minValue = Collections.min((List<Integer>) scenarioContext.getScenarioContext("LIST"));
        int maxValue = Collections.max((List<Integer>) scenarioContext.getScenarioContext("LIST"));
        if (minValue == maxValue) {
            log.info("Min Value is " + minValue + " and Max Value is " + maxValue + " therefore, they are equal");
            scenarioContext.setScenarioContext("EVIDENCE", "Min Value is " + minValue + " and Max Value is " + maxValue + " therefore, they are equal\n");
            Assert.fail();
        }
        log.info("The Min Value is " + minValue + " and Max Value is " + maxValue);
        scenarioContext.setScenarioContext("EVIDENCE", "The Min Value is " + minValue + " and Max Value is " + maxValue + "\n");
        Assert.assertTrue(true);
    }

    @Given("an array list of nTimes> possible numbers")
    public void anArrayListOfNTimesPossibleNumbers(String nTimes) {
        this.anArrayListOfnPossibleNumbers(nTimes);
    }

    @Given("API {string} with get method")
    public void apiWithGetMethod(String endpoint) {
        Assert.assertTrue(endpoint.contains("https://datausa.io"));
        scenarioContext.setScenarioContext("ENDPOINT", endpoint);
        scenarioContext.setScenarioContext("EVIDENCE", "Endpoint selected " + endpoint);

    }

    @And("parametrized by drilldowns {string}, measures {string}, year {string}")
    public void parametrizedByDrilldownsMeasuresYear(String drilldowns, String measures, String year) {
        Assert.assertFalse(drilldowns.isEmpty() && measures.isEmpty() && year.isEmpty());
        scenarioContext.setScenarioContext("PARAM1", drilldowns);
        scenarioContext.setScenarioContext("PARAM2", measures);
        scenarioContext.setScenarioContext("PARAM3", year);
        scenarioContext.setScenarioContext("EVIDENCE", "drilldowns=" + drilldowns + " measures=" + measures + " year=" + year);

    }

    @When("is executed")
    public void isExecuted() {
        response = given()
                .queryParam("drilldowns", scenarioContext.getScenarioContext("PARAM1"))
                .queryParam("measures", scenarioContext.getScenarioContext("PARAM2"))
                .queryParam("year", scenarioContext.getScenarioContext("PARAM3"))
                .when()
                .get((String) scenarioContext.getScenarioContext("ENDPOINT"))
                .thenReturn();
        scenarioContext.setScenarioContext("EVIDENCE", "Endpoint executed");
        Assert.assertNotNull(response);
    }

    @Then("verify response {string}")
    public void verifyResponse(String status) {
        scenarioContext.setScenarioContext("EVIDENCE", "Endpoint status " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), Integer.parseInt(status));
    }

    @And("check response data")
    public void checkResponseData() {
        DataUsaResponse dataUsaResponse = gson.fromJson(response.getBody().asString(), DataUsaResponse.class);
        log.info("Full Response: \n" + dataUsaResponse);
        scenarioContext.setScenarioContext("EVIDENCE", "Endpoint response Data \n" + dataUsaResponse.getData());
        Assert.assertNotNull(dataUsaResponse.getData());
    }

    @And("check response data with no data")
    public void checkResponseDataWithNoData() {
        DataUsaResponse dataUsaResponse = gson.fromJson(response.getBody().asString(), DataUsaResponse.class);
        log.info("Full Response: \n" + dataUsaResponse);
        scenarioContext.setScenarioContext("EVIDENCE", "Endpoint response Data \n" + dataUsaResponse.getData());

        DataUsaResponse jsonEmpty = new DataUsaResponse();
        jsonEmpty.setData(new ArrayList<>());
        jsonEmpty.setSource(new ArrayList<>());

        scenarioContext.setScenarioContext("EVIDENCE", "Endpoint response Data \n" + dataUsaResponse);
        Assert.assertEquals(dataUsaResponse.getData(), jsonEmpty.getData());
        Assert.assertEquals(dataUsaResponse.getSource(), jsonEmpty.getSource());

    }
}
