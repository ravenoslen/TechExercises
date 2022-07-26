package exercises.interview.Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"exercises.interview.StepDefinition"},
        features = "src/main/resources/features",
        plugin = {"json:target/report-evidences/CucumberJson.json",
                "html:target/report-evidences/CucumberHtmlReport.html",
                "junit:target/report-evidences/CucumberResults.xml"},
        tags = "@validated"
)

public class MainRunner {

    @BeforeClass
    public static void configProperties() {
        System.setProperty("CUCUMBER_PUBLISH_ENABLED", "false");
    }
}
