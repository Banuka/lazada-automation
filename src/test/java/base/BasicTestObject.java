package base;

import driver.DriverConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

public class BasicTestObject {
    public static final String CONF_LAZADA_PROPERTIES = "conf/lazada.properties";
    static Logger logger = LogManager.getLogger(BasicTestObject.class);

    protected WebDriver webDriver;

    public static Properties properties;

    static {
        try {
            properties = loadProps();
        } catch (Exception e) {
            logger.fatal("unable to initialize configuration properties. test execution will fail.", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Create drver object and navigates to baseurl
     */
    @BeforeMethod(alwaysRun = true)
    public void initializeBaseSetup() throws Exception{
        webDriver = DriverConnection.getWebBrowserInstance();
    }

    /**
     * Read property values
     * @return
     * @throws Exception
     */
    static Properties loadProps() throws Exception {
        logger.debug("loading properies from :" + CONF_LAZADA_PROPERTIES );
        Properties props = new Properties();
        URL inputStream = null;
        inputStream = BasicTestObject.class.getClassLoader().getResource(CONF_LAZADA_PROPERTIES);
        FileInputStream fileInputStream = new FileInputStream(inputStream.getPath());
        props.load(fileInputStream);
        return props;
    }

    /**
     *
     * @throws Exception
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        DriverConnection.closeDriver();
    }
}
