package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverFactory {
static WebDriver driver ;
    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);
    public static WebDriver getDriver(){
        String browser = PropertiesReader.geConfigProperties().getProperty("browser");
        if(Boolean.parseBoolean(PropertiesReader.geConfigProperties().getProperty("headless")));
        if(browser.equalsIgnoreCase("chrome")){
            logger.info("Opening Chrome browser...");
            ChromeOptions chromeOptions = new ChromeOptions();
             chromeOptions.addArguments("--headless");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(chromeOptions);
            logger.info("Chrome browser opened successfully.");
        }
        if(browser.equalsIgnoreCase("firefox")){
            logger.info("Opening Firefox browser...");
            FirefoxOptions firefoxOptions = new FirefoxOptions();
             firefoxOptions.addArguments("--headless");
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver(firefoxOptions);
            logger.info("FireFox browser opened successfully.");
        }
       if(browser.equalsIgnoreCase("edge")){
           logger.info("Opening Edge browser...");
           EdgeOptions edgeOptions = new EdgeOptions();
           edgeOptions.addArguments("--headless");
           WebDriverManager.edgedriver().setup();
           driver=new EdgeDriver(edgeOptions);
           logger.info("Edge browser opened successfully");
       }
        return driver;
    }
}


