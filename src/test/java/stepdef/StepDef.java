package stepdef;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.exception.InvalidBrowserException;
import org.exception.InvalidCalculatorBtnException;
import org.modules.CalculatorModule;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.DriverFactory;
import utils.PropertiesReader;
import java.time.Duration;
import java.util.Objects;
import static java.lang.Integer.parseInt;

public class StepDef {
    private static final Logger logger = LoggerFactory.getLogger(StepDef.class);
    public WebDriver driver;
    public CalculatorModule calculatorModule;
    @Given("the calculator application is open")
    public void the_calculator_application_is_open()throws Exception {
        try {
            driver = DriverFactory.getDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            driver.get(Objects.requireNonNull(PropertiesReader.geConfigProperties()).getProperty("url"));
            calculatorModule = new CalculatorModule(driver);
            logger.info("URL opened successfully.");
        } catch (Exception e) {
            logger.error("Error opening the browser: {}");
            throw new InvalidBrowserException("Unsupported browser detected: " + e.getMessage());
        }
    }
    @When("the user enters the {int} interfering number")
    public void the_user_enters_the_interfering_number(Integer int1) throws Exception {
        String strNum = String.valueOf(int1);
        for (char c : strNum.toCharArray()) {
            calculatorModule.enterNumber(parseInt(String.valueOf(c)));
        }
        logger.debug("Number entered: {}", int1);
    }
    @And("user hit {string} operator")
    public void userHitOperator(String arg0)throws Exception {
        logger.info("User hitting operator:");
        if(arg0.equals("+")){
            calculatorModule.clickButton("add");
        }else if (arg0.equals("-")) {
            calculatorModule.clickButton("sub");
        }else if (arg0.equals("*")) {
            calculatorModule.clickButton("mul");
        }else if (arg0.equals("/")) {
            calculatorModule.clickButton("div");
        }
        else {
            logger.error("Unsupported operator: ");
            throw new InvalidCalculatorBtnException("Unsupported operator: " + arg0);
        }
    }
    @When("the user hit the equals {string} button")
    public void the_user_hit_the_equals_button(String buttontype) throws Exception {
            logger.debug("Clicking the equals button: {}", buttontype);
            calculatorModule.clickButton("equals");
        }
    @Then("the result {int} should be display")
    public void the_result_should_be_display(int expectedResult) throws Exception {
            int actualResult = calculatorModule.getResult();
            assert actualResult == expectedResult : "Expected result: " + expectedResult + ", but found: " + actualResult;
            logger.info("Expected result: {}, Actual result: {}", expectedResult, actualResult);
    }
    @Then("the result {string} should be display")
    public void the_result_string_should_be_display(String expectedResult) throws Exception {
        String actualResult = calculatorModule.getErrorResult();
        assert actualResult.equals(expectedResult) : "Expected result: " + expectedResult + ", but found: " + actualResult;
        logger.info("Expected result: {}, Actual result: {}", expectedResult, actualResult);
    }
    @When("the user enters the {int} number")
    public void the_user_enters_the_number(Integer int1) throws Exception {
        logger.info("Entering number: " + int1);
        String strNum1 = String.valueOf(int1);
        for (char c1 : strNum1.toCharArray()) {
            if (c1 == '-') {
                logger.info("Clicking subtraction button");
                calculatorModule.clickButton("sub");
            } else {
                logger.info("Entering digit: " + c1);
                calculatorModule.enterNumber(parseInt(String.valueOf(c1)));
            }
        }
    }
    @When("the user looks at the plus sign")
    public void the_user_looks_at_the_plus_sign() {
        logger.info("User looks at the plus sign");
    }
    @Then("the user should see and be able to click the plus sign")
    public void the_user_should_see_and_be_able_to_click_the_plus_sign() throws Exception {
        calculatorModule.verifyButton("add");
        calculatorModule.clickButton("add");
        logger.info("Plus sign is visible and clickable");
        logger.info("Clicked the plus sign");
    }

    @When("the user looks at the minus sign")
    public void theUserLooksAtTheMinusSign() {
        logger.info("User looks at the Minus sign");
    }

    @Then("the user should see and be able to click the minus sign")
    public void theUserShouldSeeAndBeAbleToClickTheMinusSign() throws Exception {
        calculatorModule.verifyButton("sub");
        calculatorModule.clickButton("sub");
        logger.info("minus sign is visible and clickable");
        logger.info("Clicked the minus sign");
    }

    @When("the user looks at the multiply sign")
    public void theUserLooksAtTheMultiplySign() {
        logger.info("User looks at the Multiply sign");
    }
    @Then("the user should see and be able to click the multiply sign")
    public void theUserShouldSeeAndBeAbleToClickTheMultiplySign() throws Exception {
        calculatorModule.verifyButton("mul");
        calculatorModule.clickButton("mul");
        logger.info("multiplicaion sign is visible and clickable");
        logger.info("Clicked the Multiply sign");
    }
    @When("the user looks at the divide sign")
    public void theUserLooksAtTheDivideSign() {
        logger.info("User looks at the Division sign");
    }
    @Then("the user should see and be able to click the divide sign")
    public void theUserShouldSeeAndBeAbleToClickTheDivideSign() throws Exception {
        calculatorModule.verifyButton("div");
        calculatorModule.clickButton("div");
        logger.info("Division sign is visible and clickable");
        logger.info("Clicked the division sign");
    }
    @When("the user looks at the equals sign")
    public void theUserLooksAtTheEqualsSign() {
        logger.info("User looks at the Equals sign");
    }
    @Then("the user should see and be able to click the equals sign")
    public void theUserShouldSeeAndBeAbleToClickTheEqualsSign() throws Exception {
        calculatorModule.verifyButton("equals");
        calculatorModule.clickButton("equals");
        logger.info("equals sign is visible and clickable");
        logger.info("Clicked the equals sign");
    }
    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                String screenshotName = scenario.getName();
                TakesScreenshot ts = (TakesScreenshot) driver;
                byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", screenshotName);
                scenario.log("Screenshot taken for failure scenario: " + screenshotName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit(); // Close the WebDriver instance
            }
        }
    }
}
