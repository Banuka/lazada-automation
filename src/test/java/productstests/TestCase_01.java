package productstests;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.LazadaProductPage;

public class TestCase_01 extends BaseTest {
    Logger logger = LogManager.getLogger(TestCase_01.class);
    private LazadaProductPage lazadaProductPage;

    @Test(dataProvider = "data_test_case_01", dataProviderClass = testdata.ScenarioDataProvider.class)
    public void testVeryfyNavigateToLeafCategory(String categoryPath[], String leaf, String expProductPage) throws Exception {
        lazadaProductPage = lazadaHomePage.clickOnLeaf(categoryPath, leaf);
        String actProductName = lazadaProductPage.getLeafProductInBreadCrumb();
        Assert.assertEquals(actProductName, expProductPage, "User has not landed to correct " + expProductPage + " page.");
    }
}
