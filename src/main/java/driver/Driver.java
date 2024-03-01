package driver;

import static java.lang.String.format;

import io.appium.java_client.android.AndroidDriver;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.CapabilitiesFactory;

public class Driver {
  private static final String env = System.getProperty("env.name");
  private static final Logger logger = LogManager.getRootLogger();
  private static AndroidDriver driver;

  private Driver() {
  }

  public static AndroidDriver getDriver() {
    final String APPIUM_MOBITRU_HUB = "app.mobitru.com";
    final String BROWSER_STACK = "hub-cloud.browserstack.com";
    final String ACCESS_KEY = System.getProperty("token");
    final Object USER_NAME = System.getProperty("username");
    final String PROJECT_NAME = "personal";
    if (driver == null) {
      logger.info("Creating driver with env: " + env);
      switch (env) {
        case "local" -> {
          return driver = new AndroidDriver(AppiumDriverServiceInitialization.getAppiumDriverLocalService(),
              CapabilitiesFactory.getCapabilities(env));
        }
        case "mobitru" -> {
          try {
            String url = format("https://%s:%s@%s/wd/hub", PROJECT_NAME, ACCESS_KEY, APPIUM_MOBITRU_HUB);
            return driver = new AndroidDriver(
                new URL(url),
                CapabilitiesFactory.getCapabilities(env));
          } catch (MalformedURLException e) {
            throw new RuntimeException(e);
          }
        }
        case "browserstack" -> {
          try {
            String url = format("https://%s:%s@%s/wd/hub", USER_NAME, ACCESS_KEY, BROWSER_STACK);
            return driver = new AndroidDriver(
                new URL(url),
                CapabilitiesFactory.getCapabilities(env));
          } catch (MalformedURLException e) {
            throw new RuntimeException(e);
          }
        }
        case "saucelabs" -> {
          try {
            return driver = new AndroidDriver(
                new URL("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub"),
                CapabilitiesFactory.getCapabilities(env));
          } catch (MalformedURLException e) {
            throw new RuntimeException(e);
          }
        }
        default -> throw new IllegalArgumentException("Unknown environment value!");
      }
    }
    return driver;
  }

  public static void closeAppium() {
    if (AppiumDriverServiceInitialization.getAppiumDriverLocalService() != null ||
        env.equals("local")) {
      AppiumDriverServiceInitialization.stopService();
      logger.info("Appium service stopped");
    }
  }

  public static void quitDriver() {
    driver.quit();
    driver = null;
    logger.info("Driver quited");
  }
}
