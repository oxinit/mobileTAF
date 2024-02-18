package driver;


import static io.appium.java_client.service.local.flags.GeneralServerFlag.LOG_LEVEL;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.SESSION_OVERRIDE;
import static util.EmulatorControl.IsEmulatorStarted;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import java.io.IOException;
import java.net.ServerSocket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.ConfigurationReader;
import util.EmulatorControl;

public class AppiumDriverServiceInitialization {
  private static final Logger logger = LogManager.getRootLogger();
  private static final String ERROR_LOG_LEVEL = "error";
  private static final String KILL_NODE_COMMAND = "taskkill /F /IM node.exe";
  private static AppiumDriverLocalService service;
  private static final int portNumber =
      Integer.parseInt(ConfigurationReader.getProperty("appium.server.port"));

  public AppiumDriverServiceInitialization() {
  }

  public static AppiumDriverLocalService getAppiumDriverLocalService() {
    if (IsEmulatorStarted() == false) {
      EmulatorControl.startEmulator();
    }
    if (service == null) {
      startLocalService(portNumber);
    }
    return service;
  }

  private static void startLocalService(int portNumber) {
    makePortAvailableIfOccupied(portNumber);
    service = new AppiumServiceBuilder()
        .usingPort(portNumber)
        .withIPAddress(ConfigurationReader.getProperty("appium.server.ip"))
        .withArgument(SESSION_OVERRIDE)
        .withArgument(LOG_LEVEL, ERROR_LOG_LEVEL)
        .build();
    service.start();
    logger.info("Appium service started on port: " + portNumber);
  }

  public static void stopService() {
    if (service != null) {
      service.stop();
      logger.info("Appium service stopped");
    }
  }

  private static boolean isPortAvailable(int portNumber) {
    try (ServerSocket ignored = new ServerSocket(portNumber)) {
      logger.info("Port " + portNumber + " is available");
      return true;
    } catch (IOException e) {
      logger.warn("Port " + portNumber + " is not available");
      return false;
    }
  }

  private static void makePortAvailableIfOccupied(int portNumber) {
    if (!isPortAvailable(portNumber)) {
      try {
        Runtime.getRuntime().exec(KILL_NODE_COMMAND);
        logger.info("Port " + portNumber + " is available now");
      } catch (IOException e) {
        logger.error("Error while killing node.exe: " + e.getMessage());
      }
      stopService();
    }
  }
}
