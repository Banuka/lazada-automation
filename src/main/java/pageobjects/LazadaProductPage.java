package pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.base.BasePage;

import java.util.List;

public class LazadaProductPage extends BasePage {
    Logger logger = LogManager.getLogger(LazadaProductPage.class);

    @FindBy(xpath = "//ul[@class='breadcrumb']")
    private WebElement listBreadCrumb;

    public LazadaProductPage(WebDriver webDriver) {
        super(webDriver);
        waitForLoad(webDriver);
    }

    public String getLeafProductInBreadCrumb(){
        List<WebElement> breadCrumbElms = listBreadCrumb.findElements(By.xpath("li"));
        return breadCrumbElms.get(breadCrumbElms.size()-1).getText();
    }
}
