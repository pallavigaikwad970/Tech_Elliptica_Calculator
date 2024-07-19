
package org.modules;
import org.enums.ActionButtonEnum;
import org.exception.InvalidCalculatorBtnException;
import org.exception.InvalidCalculatorNumberException;
import org.exception.InvalidCalculatorRessultException;
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
        logger.info("Initializing CalculatorModule with WebDriver instance");
        wAction = new WebAction(this.driver);
        logger.info("Initialized WebAction in CalculatorModule");
        wVerification = new WebVerification();
        logger.info("Initialized WebVerification in CalculatorModule");
    }
    public void enterNumber(int number) throws InvalidCalculatorNumberException {
        logger.debug("Attempting to click number: {}", number);
        if (number < 0 || number > 9999) {
            throw new InvalidCalculatorNumberException("Invalid number: " + number);
        }
        By btn = btnXpath(String.valueOf(number));
        wAction.click(btn);
        logger.info("Clicked number: " + number);
    }
    public void clickButton(String buttonType)throws InvalidCalculatorBtnException {
        try {
            for (ActionButtonEnum eValue : ActionButtonEnum.values()) {
                if (eValue.getBtnName().equals(buttonType)) {
                    wAction.click(eValue.getElementLocator());
                    logger.info("Clicked button: " + buttonType);
                    break;
                }
            }
        }catch(Exception e) {
            boolean buttonClicked = false;
            if (!buttonClicked) {
                logger.warn("Button not found: " + buttonType);
                throw new InvalidCalculatorBtnException("Invalid button :"+buttonType);
            }
        }
    }
    public int getResult()throws InvalidCalculatorRessultException {
        try{
        WebElement resultFieldElement = wAction.getElement(btn_result);
        logger.info("Attempting to retrieve result...");
        wVerification.assertTrue("Result obtained: {}" + resultFieldElement, wAction.isElementDisplayed(resultFieldElement));
        logger.info("Result field element is displayed");
        String resultText = resultFieldElement.getText();
        logger.info("Result text retrieved: " + resultText);
        int result = Integer.parseInt(resultText);
        logger.info("Converted result to integer: " + result);
        return result;
        }catch (Exception e){
            throw new InvalidCalculatorRessultException("Resul not found");
        }
    }

    public String getErrorResult()throws InvalidCalculatorRessultException {
        try{
        WebElement resultFieldElement = wAction.getElement(btn_result);
        logger.info("Attempting to retrieve error result...");
        wVerification.assertTrue("Result obtained: {}" + resultFieldElement, wAction.isElementDisplayed(resultFieldElement));
        logger.info("Error result field element is displayed");
        String resultText = resultFieldElement.getText();
        logger.info("Error result text retrieved: " + resultText);
        return resultText;
        }
        catch (Exception e){
            throw new InvalidCalculatorRessultException("Resul not found");
        }
    }

    public boolean verifyButton(String buttonType)throws InvalidCalculatorBtnException {
        try {
            WebElement buttonElement = null;
            if (buttonElement != null) {
                logger.info("Verifying button type: " + buttonType);
                wVerification.assertTrue("Is button element enabled " + buttonElement, wAction.isElementEnabled(buttonElement));
                logger.info("Button element is enabled");
                wVerification.assertTrue("Is button element displayed " + buttonElement, wAction.isElementDisplayed(buttonElement));
                logger.info("Button element is display");
                return true;
            } else {
                logger.warn("Button element not found for type: " + buttonType);
                return false;
            }
        }catch (Exception e){
            throw new InvalidCalculatorBtnException("Invalid Button : " +buttonType);

        }
    }
}
