package util;

import io.appium.java_client.android.options.UiAutomator2Options;
import java.io.File;
import org.openqa.selenium.MutableCapabilities;

public class CapabilitiesFactory {
  public static UiAutomator2Options getCapabilities(String environment) {
    switch (environment) {
      case "local" -> {
        return getLocalCapabilities();
      }
      case "mobitru" -> {
        return getRemoteCapabilities();
      }
      case "browserstack" -> {
        return getBrowserStackCapabilities();
      }
      case "saucelabs" -> {
        return getSauceLabsCapabilities();
      }
      default -> throw new IllegalArgumentException("Unknown environment value!");
    }
  }

  private static UiAutomator2Options getLocalCapabilities() {
    UiAutomator2Options uiAutomator2Options = new UiAutomator2Options();
    uiAutomator2Options.setCapability("platformName", ConfigurationReader.getProperty("platform.name"));
    uiAutomator2Options.setAutomationName(ConfigurationReader.getProperty("automation.name"));
    uiAutomator2Options.setCapability("udid", ConfigurationReader.getProperty("udid"));
    uiAutomator2Options.setCapability("appPackage", ConfigurationReader.getProperty("app.package"));
    uiAutomator2Options.setCapability("appActivity", ConfigurationReader.getProperty("app.activity"));
    uiAutomator2Options.setCapability("deviceName", ConfigurationReader.getProperty("device.name"));
    uiAutomator2Options.setCapability("app", new File(ConfigurationReader.getProperty("app.path")).getAbsolutePath());
    return uiAutomator2Options;
  }

  private static UiAutomator2Options getRemoteCapabilities() {
    UiAutomator2Options uiAutomator2Options = new UiAutomator2Options();
    uiAutomator2Options.setCapability("platformName", ConfigurationReader.getProperty("platform.name"));
    uiAutomator2Options.setAutomationName(ConfigurationReader.getProperty("automation.name"));
    uiAutomator2Options.setCapability("udid", ConfigurationReader.getProperty("udid"));
    uiAutomator2Options.setCapability("appPackage", ConfigurationReader.getProperty("app.package"));
    uiAutomator2Options.setCapability("appActivity", ConfigurationReader.getProperty("app.activity"));
    uiAutomator2Options.setCapability("app", new File(ConfigurationReader.getProperty("app.path")).getAbsolutePath());
    return uiAutomator2Options;
  }

  private static UiAutomator2Options getBrowserStackCapabilities() {
    UiAutomator2Options uiAutomator2Options = new UiAutomator2Options();
    uiAutomator2Options.setCapability("app", ConfigurationReader.getProperty("app.id"));
    uiAutomator2Options.setCapability("platformName", ConfigurationReader.getProperty("platform.name"));
    uiAutomator2Options.setCapability("platformVersion", ConfigurationReader.getProperty("platform.version"));
    uiAutomator2Options.setCapability("deviceName", ConfigurationReader.getProperty("device.name"));
    uiAutomator2Options.setCapability("buildName", ConfigurationReader.getProperty("build.name"));
    uiAutomator2Options.setCapability("browserstack.local", ConfigurationReader.getProperty("browserstack.local"));
    uiAutomator2Options.setCapability("sessionName", ConfigurationReader.getProperty("session.name"));
    uiAutomator2Options.setCapability("projectName", ConfigurationReader.getProperty("project.name"));
    uiAutomator2Options.setCapability("local", false);
    return uiAutomator2Options;
  }

  private static UiAutomator2Options getSauceLabsCapabilities() {
    UiAutomator2Options uiAutomator2Options = new UiAutomator2Options();
    uiAutomator2Options.setCapability("platformName", ConfigurationReader.getProperty("platform.name"));
    uiAutomator2Options.setCapability("appium:app", ConfigurationReader.getProperty("app"));
    uiAutomator2Options.setCapability("appium:deviceName", ConfigurationReader.getProperty("device.name"));
    uiAutomator2Options.setCapability("appium:platformVersion", ConfigurationReader.getProperty("platform.version"));
    uiAutomator2Options.setCapability("appium:automationName", "UiAutomator2");
    MutableCapabilities sauceOptions = new MutableCapabilities();
    sauceOptions.setCapability("username", System.getProperty("username"));
    sauceOptions.setCapability("accessKey", System.getProperty("token"));
    sauceOptions.setCapability("build", ConfigurationReader.getProperty("build"));
    sauceOptions.setCapability("name", ConfigurationReader.getProperty("project.name"));
    sauceOptions.setCapability("deviceOrientation", ConfigurationReader.getProperty("device.orientation"));
    uiAutomator2Options.setCapability("sauce:options", sauceOptions);
    return uiAutomator2Options;
  }
}
