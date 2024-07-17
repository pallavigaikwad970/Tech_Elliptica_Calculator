Feature: Subtraction of Integer Numbers
  Background: calculator
    Given the calculator application is open

  @sanity @sub_positive
  Scenario Outline:To verify if the calculator can correctly subtraction of two Positive numbers <TestcaseName>
    When the user enters the <First> number
    And user hit "-" operator
    And the user enters the <Second> number
    And the user hit the equals "=" button
    Then the result <Result> should be display
  Examples:
      | First | Second | Result | TestcaseName   |
      | 100   | 20     | 80     | For 100 and 20 |
      | 0     | 20000  | -20000 | For 0 and 20000|
      | 100   | 20     | 80     | For 100 and 20  |


  @sanity  @sub_negative
  Scenario Outline: Check whether the calculator can perform subtraction for two negative numbers <TestcaseName>
    When the user enters the <First> number
    And user hit "-" operator
    And the user enters the <Second> number
    And the user hit the equals "=" button
    Then the result <Result> should be display
  Examples:
    | First | Second | Result  |TestcaseName       |
    | -0    | -0     | -0      | For -0 and -0     |


  @sanity @sub_both
  Scenario Outline: To verify if the calculator can correctly subtract a Positive number from a Negative number <TestcaseName>
    When the user enters the <Positive> number
    And user hit "-" operator
    And the user enters the <Negative> number
    And the user hit the equals "=" button
    Then the result <Result> should be display
  Examples:
    | Positive | Negative | Result | TestcaseName       |
    | 0        |   -0     | 0      | For 0 and -0       |
    | 9999999  |   -0     | 9999999| For 9999999 and -0 |

  @sanity @sub_both
  Scenario Outline: To verify if the calculator can correctly subtract a Positive number from a Negative number <TestcaseName>
    When the user enters the <Positive> number
    And user hit "-" operator
    And the user enters the <Negative> number
    And the user hit the equals "=" button
    Then the result "<Result>" should be display
    Examples:
      | Positive | Negative | Result               | TestcaseName       |
      | -10   | -5          |Invalid Expression    | For -10 and -5    |
      | 50       |   -20    | Invalid Expression   | For 50 and -20     |
      | -125  | -7895       | Invalid Expression        | For -125 and -7895|