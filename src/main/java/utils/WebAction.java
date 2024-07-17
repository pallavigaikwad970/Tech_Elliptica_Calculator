package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class WebAction {
    private static final Logger logger = LoggerFactory.getLogger(WebAction.class.getName());

    WebDriver driver ;

    public WebAction(WebDriver driver){
        logger.info("WebAction instance created with WebDriver: " + driver);
        this.driver = driver;
    }
    public boolean click(By by){
        try {
            WebElement elm = getElement(by);
            elm.click();
            logger.info("Clicked element located by: " + by);
            return true;
        }catch(Exception e){
            logger.warn("Failed to click element located by " + by + ": " + e.getMessage());
            return false;
        }finally {
            logger.info("Click operation completed for element located by: " + by);
        }
    }
    public WebElement getElement(By by){
        logger.info("Attempting to find element located by: " + by);
        return  driver.findElement(by);
    }
        public boolean isElementEnabled(WebElement element) {
            logger.info("Checking if element is enabled");
             return element.isEnabled();
        }
    public boolean isElementDisplayed(WebElement element) {
        logger.info("Checking if element is display");
        return element.isDisplayed();
    }
}
