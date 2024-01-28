package driver;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.remote.MobileCapabilityType;

public class CapabilitiesFactory {
  private static final String PLATFORM_NAME_CAPABILITY = "Android";
  private static final String DEVICE_NAME_CAPABILITY = "Pixel_2_API_34";
  private static final String AUTOMATION_NAME_CAPABILITY ="UiAutomator2";
  //private static final String APP_ACTIVITY_CAPABILITY = "com.epam.connect.android.apps.nexuslauncher";
  private static final String COMMAND_TIME_OUT_CAPABILITY = "60";

  public static DesiredCapabilities getCapabilities() {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM_NAME_CAPABILITY);
    capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AUTOMATION_NAME_CAPABILITY);
    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME_CAPABILITY);
    capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, COMMAND_TIME_OUT_CAPABILITY);
    //capabilities.setCapability("appActivity", APP_ACTIVITY_CAPABILITY);
    return capabilities;
  }

  public static URL getAppiumServerUrl() {
    try{
      return new URL ("http://localhost:4723");
    }catch (MalformedURLException e){
      e.printStackTrace();
    }
    return null;
  }
}
