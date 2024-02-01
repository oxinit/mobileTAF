import driver.Driver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import util.EmulatorControl;

public class BaseTest {
  @AfterClass
  public void quitDriver() {
    Driver.quitDriver();
    Driver.closeAppium();
  }

  @AfterSuite
  public void closeEmulator() {
    EmulatorControl.closeEmulator();
  }
}
