package pageobjects.base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected WebDriver webDriver;
    JavascriptExecutor js;

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        js = (JavascriptExecutor)webDriver;
    }

    public void waitForLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }

    public void clickElement(WebElement inputElement) throws InterruptedException {
        inputElement.click();
        Thread.sleep(2500);
    }

    public void clickFromJSExecutor(WebElement inputElement) throws InterruptedException {
        js.executeScript("arguments[0].checked = true;", inputElement);
        Thread.sleep(1500);
    }

    public void hoverOnElement(WebElement inputElement){
        Actions actions = new Actions(webDriver);
        actions.moveToElement(inputElement).perform();
    }
}
