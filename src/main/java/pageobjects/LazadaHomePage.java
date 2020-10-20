package pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobjects.base.BasePage;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class LazadaHomePage extends BasePage {
    Logger logger = LogManager.getLogger(LazadaHomePage.class);

    public LazadaHomePage(WebDriver webDriver) {
        super(webDriver);
        waitForLoad(webDriver);
    }

    @FindBy(xpath = "//span[@class='lzd-site-menu-nav-category-text']")
    private WebElement lnkCategories;

    @FindBy(xpath = "//ul[@class='lzd-site-menu-root']")
    private WebElement lnkmenuRoot;


    /**
     * Navigate through the parent categories and clicks on leaf level product
     *
     * @param parentCategories
     * @param leaf
     * @return
     * @throws Exception
     */
    public LazadaProductPage clickOnLeaf(String parentCategories[], String leaf) throws Exception {
        clickFromJSExecutor(lnkCategories);
        int menuIndex = 1;
        int noParents = parentCategories.length;
        for (String parentCat : parentCategories) {
            List<WebElement> rootCats = lnkmenuRoot.findElements(By.xpath("li"));
            WebElement catElement = null;
            for (int i = 0; i < rootCats.size(); i++) {
                if (rootCats.get(i).getText().equalsIgnoreCase(parentCat)) {
                    catElement = rootCats.get(i);
                    break;
                }
                menuIndex++;
            }
            if (catElement == null) {
                logger.error("Invalid category provided.");
                throw new Exception("ERROR::Category " + parentCat + " Not Found.....!!!!!");
            } else {
                hoverOnElement(catElement);
                noParents--;
                if (noParents == 0) {
                    List<WebElement> leafProducts = catElement.findElements(By.xpath("ul/li"));
                    Optional<WebElement> matches = leafProducts.stream().filter(l -> l.findElement(By.xpath("a/span")).getText().equalsIgnoreCase(leaf)).findFirst();
                    if (matches == null) {
                        logger.error("Invalid leaf product provided.");
                        throw new Exception("ERROR::Leaf " + leaf + " Not Found.....!!!!!");
                    } else {
                        WebElement leafElm = matches.get();
                        clickElement(leafElm);
                    }
                    break;
                }
                lnkmenuRoot = lnkmenuRoot.findElement(By.xpath("ul[" + menuIndex + "]"));
            }
            menuIndex = 1;
        }
        LazadaProductPage lazadaProductPage = new LazadaProductPage(webDriver);
        PageFactory.initElements(webDriver, lazadaProductPage);
        return lazadaProductPage;
    }


    public List getLeafProductsByHoverOnCategory(String parentCategories[]) throws Exception {
        clickFromJSExecutor(lnkCategories);
        int menuIndex = 1;
        int noParents = parentCategories.length;
        List childProductList = new ArrayList();
        for (String parentCat : parentCategories) {
            List<WebElement> rootCats = lnkmenuRoot.findElements(By.xpath("li"));
            WebElement catElement = null;
            for (int i = 0; i < rootCats.size(); i++) {
                if (rootCats.get(i).getText().equalsIgnoreCase(parentCat)) {
                    catElement = rootCats.get(i);
                    break;
                }
                menuIndex++;
            }
            if (catElement == null) {
                logger.error("Invalid category provided.");
                throw new Exception("ERROR::Category " + parentCat + " Not Found.....!!!!!");
            } else {
                hoverOnElement(catElement);
                noParents--;
                if (noParents == 0) {
                    List<WebElement> leafProducts;
                    webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
                    if (catElement.findElements(By.xpath("ul")).size() > 0) {
                        webDriver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
                        leafProducts = catElement.findElements(By.xpath("ul/li"));
                    } else {
                        webDriver.manage().timeouts().pageLoadTimeout(Long.valueOf(20), TimeUnit.SECONDS);
                        leafProducts = lnkmenuRoot.findElements(By.xpath("ul[" + menuIndex + "]/li"));
                    }
                    leafProducts.forEach(p -> childProductList.add(p.getText()));
                    break;
                }
                lnkmenuRoot = lnkmenuRoot.findElement(By.xpath("ul[" + menuIndex + "]"));
            }
            menuIndex = 1;
        }
        return childProductList;
    }
}
