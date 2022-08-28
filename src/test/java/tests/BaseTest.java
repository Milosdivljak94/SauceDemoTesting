package tests;

import org.openqa.selenium.WebDriver;
import selenium_core.DriverManager;
import selenium_core.DriverManagerFactory;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriver driver;
    DriverManager driverManager;

    public void setUPTest(String browser, String version, int waitTime) throws Exception {
        driverManager = DriverManagerFactory.getDriverManager(browser);
        driver = driverManager.getWebDriver(version);
        driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
    }

    public void quit() {
        driverManager.quitWebDriver();
    }


    public void startApplication(String env) throws Exception {
        switch (env) {
            case "QA": {
                driver.get("https://www.saucedemo.com/");
            }
            break;
            case "STG":
            case "PROD": {
                driver.get("https://www.saucedemo.com/");
            }
            break;
            default:
                throw new Exception("ENV: " + env + " Not Supported!");
        }
    }

}
