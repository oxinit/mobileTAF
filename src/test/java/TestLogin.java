import buisness.object.LoginBO;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestLogin extends BaseTest {
  private final LoginBO login = new LoginBO();

  @Test
  public void loginMethodsTest() {
    login.loginTest();
    Assert.assertEquals(login.returnDescriptionOfLoginMethods(),
        "Continue with EPAM Continue with LinkedIn Continue with Apple Show more"
        , "Login methods are not as expected");
  }
}
