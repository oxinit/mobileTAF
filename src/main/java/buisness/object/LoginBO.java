package buisness.object;

import pages.LoginPage;

public class LoginBO {
  private final LoginPage loginPage;

  public LoginBO() {
    this.loginPage = new LoginPage();
  }

  public LoginBO loginTest() {
    loginPage.clickSkipIntroduction();
    loginPage.clickLogInButton();
    return this;
  }

  public String returnDescriptionOfLoginMethods() {
    return loginPage.returnDescriptionOfLoginMethods();
  }
}
