package driver;

import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;

import io.appium.java_client.android.options.UiAutomator2Options;
import java.io.File;
import util.ConfigurationReader;

public class CapabilitiesFactory {
  public static UiAutomator2Options getCapabilities(String environment) {
    switch (environment) {
      case "local":
        return getLocalCapabilities();
      default:
        throw new IllegalArgumentException("Unknown environment value!");
    }
  }

  private static UiAutomator2Options getLocalCapabilities() {
    UiAutomator2Options uiAutomator2Options = new UiAutomator2Options();
    uiAutomator2Options.setCapability(PLATFORM_NAME, "Android");
    uiAutomator2Options.setAutomationName(ConfigurationReader.getProperty("automation.name"));
    uiAutomator2Options.setCapability("udid", ConfigurationReader.getProperty("udid"));
    uiAutomator2Options.setCapability("appPackage", ConfigurationReader.getProperty("app.package"));
    uiAutomator2Options.setCapability("appActivity", ConfigurationReader.getProperty("app.activity"));
    uiAutomator2Options.setCapability("deviceName", ConfigurationReader.getProperty("device.name"));
    uiAutomator2Options.setCapability("app", new File(ConfigurationReader.getProperty("app.path")).getAbsolutePath());
    return uiAutomator2Options;
  }
}
