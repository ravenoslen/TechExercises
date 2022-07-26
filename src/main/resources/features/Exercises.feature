@exercises
Feature: Exercises

  @validated
  Scenario: Check an array/list that can get max and min values
    Given an array list of "10" possible numbers
    When check all values
    Then get max and min values

  @validated
  Scenario Outline: Check an array/list that can get max and min values
    Given an array list of <nTimes> possible numbers
    When check all values
    Then get max and min values

    Examples:
      | nTimes |
      | "5"    |
      | "21"   |
      | "20"   |
      | "50"   |
      | "0"    |
      | "1"    |
      | "-1"   |
      | "a"    |
      | "?"    |
      | "1000" |
      | "100"  |

  @validated
  Scenario: Check API datausa with response 200 and return data with state, year and population
    Given API "https://datausa.io/api/data" with get method
    And parametrized by drilldowns "State", measures "Population", year "latest"
    When is executed
    Then  verify response "200"
    And check response data


  @validated
  Scenario: Check API datausa with response 200 but no data returned
    Given API "https://datausa.io/api/data" with get method
    And parametrized by drilldowns "N/A", measures "N/A", year "N/A"
    When is executed
    Then  verify response "200"
    And check response data with no data

  @validated
  Scenario: Check API datausa with wrong enpoint
    Given API "https://datausa.io/api/datas" with get method
    And parametrized by drilldowns "State", measures "Population", year "latest"
    When is executed
    Then  verify response "404"