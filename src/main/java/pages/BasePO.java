package pages;

import driver.Driver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class BasePO {
    public BasePO(){
        PageFactory.initElements(
            new AppiumFieldDecorator(Driver.getDriver())
            ,this);
    }
}
