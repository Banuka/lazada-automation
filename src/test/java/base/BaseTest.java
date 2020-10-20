package base;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import pageobjects.LazadaHomePage;

public class BaseTest extends BasicTestObject{
    protected LazadaHomePage lazadaHomePage;

    @BeforeMethod
    public void set_up(){
        lazadaHomePage = new LazadaHomePage(webDriver);
        PageFactory.initElements(webDriver, lazadaHomePage);
    }
}
