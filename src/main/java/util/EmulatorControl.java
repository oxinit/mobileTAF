package util;

import static java.lang.String.format;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmulatorControl {
  private static final Logger logger = LogManager.getRootLogger();
  private static final String deviceName = ConfigurationReader.getProperty("device.name");
  private static boolean isEmulatorStarted = false;

  public static boolean IsEmulatorStarted() {
    return isEmulatorStarted;
  }

  public static void startEmulator() {
    Runtime rt = Runtime.getRuntime();
    Process proc = null;
    String s = null;
    String[] command = {"emulator", "-list-avds"};
    try {
      proc = rt.exec(command);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    BufferedReader stdInput = new BufferedReader(new
        InputStreamReader(proc.getInputStream()));
    System.out.println("Here is output of the command:\n");
    try {
      if ((s = stdInput.readLine()) != null && s.equals(deviceName)) {
        try {
          Runtime.getRuntime().exec("emulator -avd " + deviceName + " -no-boot-anim ");
          logger.info("Emulator started");
          try {
            Thread.sleep(32000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          logger.info("Emulator booted");
          isEmulatorStarted = true;
        } catch (IOException e) {
          logger.error("Error while starting emulator: " + e.getMessage());
        }
      } else {
        logger.error("Emulator with name " + deviceName + " not found");
      }

    } catch (IOException e) {
      throw new RuntimeException(e);
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
    } else {
      logger.info("Emulator already closed");
    }
  }
}
