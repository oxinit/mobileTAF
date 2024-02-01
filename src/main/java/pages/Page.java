package pages;

import driver.Driver;
import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page extends BasePO {
  @FindBy(xpath = "//android.widget.TextView[@text='Skip']")
  private WebElement skipIntroduction;
  @FindBy(xpath = "//android.view.ViewGroup[@content-desc='Log In']/android.view.ViewGroup")
  private WebElement logInButton;
  @FindBy(xpath = "//android.view.View[@content-desc='Continue with EPAM']")
  private WebElement epamLoginButton;
  @FindBy(xpath = "//android.view.View[@content-desc='Continue with LinkedIn']")
  private WebElement linkedInLoginButton;
  @FindBy(xpath = "//android.view.View[@content-desc='Continue with Apple']")
  private WebElement appleLoginButton;
  @FindBy(xpath = "//android.view.View[@content-desc='Show more']")
  private WebElement showMoreOptionsLoginButton;

  public Page clickSkipIntroduction() {
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(60));
    wait.until(ExpectedConditions.visibilityOf(skipIntroduction));
    skipIntroduction.click();
    return this;
  }

  public Page clickLogInButton() {
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(60));
    wait.until(ExpectedConditions.visibilityOf(logInButton));
    logInButton.click();
    return this;
  }

  public String returnDescriptionOfLoginMethods() {
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(60));
    wait.until(ExpectedConditions.visibilityOf(epamLoginButton));
    String loginDescriptionEpam = epamLoginButton.getAttribute("content-desc");
    String loginDescriptionLinkedIn = linkedInLoginButton.getAttribute("content-desc");
    String loginDescriptionApple = appleLoginButton.getAttribute("content-desc");
    String loginDescriptionShowMoreOptions = showMoreOptionsLoginButton.getAttribute("content-desc");
    return loginDescriptionEpam + " " + loginDescriptionLinkedIn + " " + loginDescriptionApple + " " +
        loginDescriptionShowMoreOptions;
  }
}
