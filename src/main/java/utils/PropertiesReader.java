package utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class PropertiesReader {
    private static final Logger logger = Logger.getLogger(PropertiesReader.class.getName());
    public static Properties geConfigProperties() {
        try {
            Properties prop=new Properties();
            logger.info("Attempting to load properties file: " + prop);
            prop.load(new FileInputStream(new File(System.getProperty("user.dir")+"/config/input.properties")));
            logger.info("General properties file loaded successfully");
            return prop;
        } catch (Exception e) {
            logger.severe("Error loading properties file: " + e.getMessage());
            return null;
        }
    }
 }

