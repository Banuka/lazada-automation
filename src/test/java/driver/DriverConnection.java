package driver;

import base.Configurations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.concurrent.TimeUnit;

import static base.BasicTestObject.properties;

public class DriverConnection {
    static Logger logger = LogManager.getLogger(DriverConnection.class);
    private static WebDriver driver;

    /**
     * Initiate Specific Browser Instance
     *
     */
    public static WebDriver getWebBrowserInstance()throws Exception{
        try {
            switch (properties.getProperty(Configurations.BROWSER)) {
                case "chrome":
                    try {
                        driver = initChromeDriver();
                        break;
                    } catch (Throwable e) {
                    }
                case "firefox":
                    try {
                        driver = initFirefoxDriver();
                        break;
                    } catch (Throwable e) {
                    }
            }
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().pageLoadTimeout(Long.valueOf(properties.getProperty(Configurations.PAGE_LOAD_TIMEOUT)), TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(Long.valueOf(properties.getProperty(Configurations.IMPLICIT_WAIT)), TimeUnit.SECONDS);
            String start_url = properties.getProperty(Configurations.BASE_URL);
            driver.get(start_url);
            logger.info("Browser Loaded And Navigated To : [\" + start_url + \" ]\"");
        }catch (Throwable e){
            e.printStackTrace();
            throw e;
        }
        return driver;
    }

    private static WebDriver initChromeDriver()throws Throwable{
        System.setProperty("webdriver.chrome.driver", DriverConnection.class.getClassLoader().getResource("drivers/chromedriver.exe").getPath());
        DesiredCapabilities capability = DesiredCapabilities.chrome();
        capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        return driver;
    }

    private static WebDriver initFirefoxDriver()throws Throwable{
        System.setProperty("webdriver.gecko.driver", "resources/drivers/geckodriver");
        driver = new FirefoxDriver();
        return driver;
    }

    /**
     * Close Currently Running WebDriver Instance
     *
     * @throws Exception
     */
    public static void closeDriver() throws Exception {
        if (!properties.getProperty(Configurations.BROWSER).equals("firefox")) {
            driver.close();
        }
        try {
            Thread.sleep(3000);
        } catch (Throwable e) {
            throw e;
        }
        driver.quit();
        driver = null;
    }
}
