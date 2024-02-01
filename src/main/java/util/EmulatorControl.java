package util;

import static java.lang.String.format;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmulatorControl {
  private static final Logger logger = LogManager.getRootLogger();
  private static boolean isEmulatorStarted = false;

  public static boolean IsEmulatorStarted() {
    return isEmulatorStarted;
  }

  public static void startEmulator() {
    try {
      Runtime.getRuntime().exec("emulator -avd Pixel_2_API_34  -no-boot-anim ");
      logger.info("Emulator started");
      Runtime.getRuntime().exec("adb wait-for-device");
      logger.info("Run wait command");
      isEmulatorStarted = true;
    } catch (IOException e) {
      logger.error("Error while starting emulator: " + e.getMessage());
    }
  }

  public static void closeEmulator() {
    if (isEmulatorStarted == true) {
      try {
        Runtime.getRuntime().exec(format("adb -s %s emu kill",
            ConfigurationReader.getProperty("udid")));
        logger.info("Emulator closed");
        isEmulatorStarted = false;
      } catch (IOException e) {
        logger.error("Error while closing emulator: " + e.getMessage());
      }
      logger.info("Emulator already closed");
    }
  }
}
