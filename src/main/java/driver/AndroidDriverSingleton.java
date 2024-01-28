package driver;

import io.appium.java_client.android.AndroidDriver;

public class AndroidDriverSingleton {
  private static AndroidDriver driver;
  private AndroidDriverSingleton(){
  }
  public static AndroidDriver getDriver(){
    if (driver==null){
      driver =new AndroidDriver(CapabilitiesFactory.getAppiumServerUrl(), CapabilitiesFactory.getCapabilities());
    }
    return driver;
  }
  public static void quitDriver(){driver.quit();}
}
