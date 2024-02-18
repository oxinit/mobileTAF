package util;

import io.appium.java_client.android.options.UiAutomator2Options;
import java.io.File;

public class CapabilitiesFactory {
  public static UiAutomator2Options getCapabilities(String environment) {
    switch (environment) {
      case "local":
        return getLocalCapabilities();
      case "remote":
        return getRemoteCapabilities();
      default:
        throw new IllegalArgumentException("Unknown environment value!");
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
    return uiAutomator2Options;
  }
}
