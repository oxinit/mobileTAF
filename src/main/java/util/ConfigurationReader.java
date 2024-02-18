package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigurationReader {
  private static final String env = System.getProperty("env.name");
  private static final Logger logger = LogManager.getRootLogger();
  private static final Properties properties = new Properties();
  private static ConfigurationReader instance;

  public ConfigurationReader() {
  }

  private static ConfigurationReader getProperties(String propertiesFileName) {
    if (instance == null) {
      instance = new ConfigurationReader();
      try {
        properties.load(new FileInputStream("src/main/resources/" + propertiesFileName + ".properties"));
      } catch (IOException e) {
        logger.error("Error while reading configuration properties file: " + e.getMessage());
      }
    }
    return instance;
  }

  public static String getProperty(String propertyName) {
    switch (env) {
      case "local":
        getProperties("test");
        return properties.getProperty(propertyName);
      case "remote":
        getProperties("mobitru");
        return properties.getProperty(propertyName);
      default:
        throw new IllegalArgumentException("Unknown environment value!");
    }
  }
}
