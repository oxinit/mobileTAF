package buisness.object;

import pages.Page;

public class LoginBO {

  private final Page page;
  public LoginBO() {
    this.page = new Page();
  }

  public LoginBO loginTest(){
    //page.clickSkipIntroduction();
    page.clickLogInButton();
    return this;
  }
  public String returnDescriptionOfLoginMethods(){
    return page.returnDescriptionOfLoginMethods();
  }
}
