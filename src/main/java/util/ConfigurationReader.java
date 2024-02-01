package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigurationReader {

  private static final Logger logger = LogManager.getRootLogger();
  private static final Properties properties = new Properties();
  private static ConfigurationReader instance;

  public ConfigurationReader() {
  }

  private static ConfigurationReader getProperties() {
    if (instance == null) {
      instance = new ConfigurationReader();
      try {
        properties.load(new FileInputStream("src/main/resources/test.properties"));
      } catch (IOException e) {
        logger.error("Error while reading configuration properties file: " + e.getMessage());
      }
    }
    return instance;
  }

  public static String getProperty(String propertyName) {
    getProperties();
    return properties.getProperty(propertyName);
  }
}
