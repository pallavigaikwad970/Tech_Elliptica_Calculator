package org.modules;
import org.enums.ActionButtonEnum;
import org.exception.InvalidCalculatorNumberException;
import org.exception.InvalidCalculatoreOperatorException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.pageobject.CalculatorScreenPageObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.WebAction;
import utils.WebVerification;
import static org.pageobject.CalculatorScreenPageObject.*;

public class CalculatorModule {
    private static final Logger logger = LoggerFactory.getLogger(CalculatorModule.class);
    public WebDriver driver;
    public WebAction wAction;
    public WebVerification wVerification;

    public CalculatorModule(WebDriver driver) {
        this.driver = driver;
        wAction = new WebAction(this.driver);
        wVerification = new WebVerification();
    }
    public void enterNumber(int number) throws InvalidCalculatorNumberException {
        logger.debug("Attempting to click number: {}", number);
        if (number < 0 || number > 9999) {
            throw new InvalidCalculatorNumberException("Invalid number: " + number);
        }
        By btn = CalculatorScreenPageObject.btnXpath(String.valueOf(number));
        wAction.click(btn);
    }
    public void clickButton(String buttonType) throws InvalidCalculatoreOperatorException {
        for(ActionButtonEnum eValue :  ActionButtonEnum.values()){
           if(eValue.getBtnName().equals(buttonType)){
               wAction.click(eValue.getElementLocator());
               break;
           }
       }
        throw new InvalidCalculatoreOperatorException("Invalid Operator");
    }
    public int getResult() {
        WebElement resultFieldElement = wAction.getElement(btn_result);
        wVerification.assertTrue("Result obtained: {}" + resultFieldElement, wAction.isElementDisplayed(resultFieldElement));
        String resultText = resultFieldElement.getText().replaceAll("[^\\d]", ""); // Remove non-numeric characters
       int result = Integer.parseInt(resultText);
        return result;
    }
    public boolean verifyButton(String buttonType)throws InvalidCalculatoreOperatorException {
        WebElement buttonElement = null;
        if (buttonElement != null) {
            wVerification.assertTrue("Is button element enabled " + buttonElement, wAction.isElementEnabled(buttonElement));
            wVerification.assertTrue("Is button element displayed " + buttonElement, wAction.isElementDisplayed(buttonElement));
            return true;
        } else {
            return false;
        }
    }
}
