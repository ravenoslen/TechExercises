package exercises.interview.StepDefinition;

import exercises.interview.Utils.ScenarioContext;
import io.cucumber.java.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Hooks extends BaseDefinition {

    Logger log = LogManager.getLogger(this.getClass());
    int step = 0;

    public Hooks(ScenarioContext scenarioContext) {
        super(scenarioContext);
    }

    @Before
    public void prepareTest(Scenario scenario) {
        log.info("Preparing test execution...");

    }

    @BeforeStep
    public void beforeStep(Scenario scenario) {
        scenarioContext.setScenarioContext("EVIDENCE", "");
        step++;
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        scenario.attach((String) scenarioContext.getScenarioContext("EVIDENCE"), "text/plain", "step " + step);
    }

    @After
    public void endTest(Scenario scenario) {
        log.info("End test execution...");
    }
}

