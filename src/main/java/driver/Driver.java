package driver;

import static java.lang.String.format;

import io.appium.java_client.android.AndroidDriver;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.ConfigurationReader;

public class Driver {
  private static final String env = ConfigurationReader.getProperty("env.type");
  private static int portNumber =
      Integer.parseInt(ConfigurationReader.getProperty("appium.server.port"));
  private static final Logger logger = LogManager.getRootLogger();
  private static AndroidDriver driver;

  private Driver() {
  }

  public static AndroidDriver getDriver() {
    if (driver == null) {
      driver = new AndroidDriver(AppiumDriverServiceInitialization.getAppiumDriverLocalService(portNumber),
          CapabilitiesFactory.getCapabilities(env));
      logger.info("Driver initialized with env: " + env);
    }
    return driver;
  }

  public static void closeAppium() {
    AppiumDriverServiceInitialization.stopService();
    logger.info("Appium service stopped");
  }

  public static void quitDriver() {
    driver.quit();
    driver = null;
    logger.info("Driver quited");
  }

}
