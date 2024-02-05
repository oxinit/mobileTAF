package util;

import static java.lang.String.format;

import driver.Driver;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
  final static Logger logger = LogManager.getRootLogger();

  @Override
  public void onTestStart(ITestResult result) {
    logger.info("{} started ", result.getName());
  }

  @Override
  public void onTestSuccess(ITestResult result) {
    logger.info("{} passed ", result.getName());
  }

  @Override
  public void onTestFailure(ITestResult result) {
    takeScreenshot();
    logger.info("{} failed ", result.getName());
  }

  @Override
  public void onFinish(ITestContext contextFinish) {
    logger.info("onFinish method finished ");

  }

  private void takeScreenshot() {
    File screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.FILE);
    try {
      FileUtils.copyFile(screenshot,
          new File("target/screenshots/" + LocalDate.now()+".png"));

    } catch (IOException e) {
      logger.error("Failed to save screenshot: " + e.getLocalizedMessage());
    }
  }
}
