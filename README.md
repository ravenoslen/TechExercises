# Tech Interview Exercises

## Requirements 
* Java 11 or above
* Maven 3.8.3 or above

## Execution 
* In terminal execute the following if is the first time:
```
mvn clean install integration-test -Drunner=MainRunner
```

Otherwise run:
```
mvn clean integration-test -Drunner=MainRunner
```

* This project can be executed with Intellij and Cucumber plugins.

## Considerations
* By default main runner will execute tag "@validated"
* Evidences are located in "target/report-evidences/CucumberHtmlReport.html" folder
* Logs are located in "target/generated-report" folder