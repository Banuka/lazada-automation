package productstests;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TestCase_02 extends BaseTest {
    Logger logger = LogManager.getLogger(TestCase_02.class);

    @Test(dataProvider = "data_test_case_02", dataProviderClass = testdata.ScenarioDataProvider.class)
    public void testVeryfyNavigateToLeafCategory(String topMenus[], String leafProducts[]) throws Exception {
        List actualProductList = lazadaHomePage.getLeafProductsByHoverOnCategory(topMenus);
        for (String expProduct : leafProducts){
            Assert.assertTrue(actualProductList.contains(expProduct), "The category / product ::" + expProduct + " missing.");
        }
    }
}
