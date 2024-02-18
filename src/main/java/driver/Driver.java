package driver;

import static java.lang.String.format;

import io.appium.java_client.android.AndroidDriver;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.CapabilitiesFactory;

public class Driver {
  /**
   * env - environment variable, which is used to define where to run tests
   * it can be "local" or "remote"
   */
  private static final String env = System.getProperty("env.name");
  private static final Logger logger = LogManager.getRootLogger();
  private static final String ACCESS_KEY = System.getProperty("token");
  private static final String PROJECT_NAME = "personal";
  private static final String APPIUM_HUB = "app.mobitru.com";
  private static AndroidDriver driver;

  private Driver() {
  }

  public static AndroidDriver getDriver() {
    if (driver == null) {
      switch (env) {
        case "local":
          return driver = new AndroidDriver(AppiumDriverServiceInitialization.getAppiumDriverLocalService(),
              CapabilitiesFactory.getCapabilities(env));
        case "remote":
          try {
            return driver = new AndroidDriver(
                new URL(format("https://%s:%s@%s/wd/hub", PROJECT_NAME, APPIUM_HUB, ACCESS_KEY)//fixme
                ),
                CapabilitiesFactory.getCapabilities(env));
          } catch (MalformedURLException e) {
            throw new RuntimeException(e);
          }
        default:
          throw new IllegalArgumentException("Unknown environment value!");
      }
    }
    logger.info("Driver initialized with env: " + env);
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
