Feature: Division of Integer Numbers
 Background: Calcy
 Given the calculator application is open

  @sanity @div_positive
  Scenario Outline: To verify if the calculator can correctly divide two integer numbers <TestcaseName>
    When the user enters the <First> number
    And user hit "/" operator
    And the user enters the <Second> number
    And the user hit the equals "=" button
    Then the result <Result> should be display
  Examples:
            | First    | Second  | Result | TestcaseName   |
            | 100      | 20      | 5      | For 100 and 20 |
            | 0        | 1       | 0      | For 0 and 1    |

  @sanity @div_positive
  Scenario Outline: To verify if the calculator can correctly provide not-number divide two integer numbers <TestcaseName>
    When the user enters the <First> number
    And user hit "/" operator
    And the user enters the <Second> number
    And the user hit the equals "=" button
    Then the result "<Result>" should be display
    Examples:
      | First    | Second  | Result              | TestcaseName   |
      | 100      | 0       | Infinity            | For 100 and 0  |
      | 99999    | 0       | Infinity            | For 99999 and 0|
      | 999      | 52      | 19.21153846153846   | For 999 and 52 |
      | 20       | 100     | 0.2                 | For 20 and 100 |
      | -0       | -0      | Bad Expression      | For -0 and -0  |

  @sanity @div_negative
  Scenario Outline: Verify that the calculator can correctly divide two negative numbers <TestcaseName>
    When the user enters the <First_negative> number
    And user hit "/" operator
    And the user enters the <Second_negative> number
    And the user hit the equals "=" button
    Then the result <Result> should be display
  Examples:
                | First_negative| Second_negative  | Result           | TestcaseName   |
                | -10           | -2               | 5                | For -10 and -2 |
                | -0            | -99              | 0                | For -0 and -99 |



