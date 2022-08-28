package selenium_core;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;



public class EdgeDriverManager extends DriverManager{
    @Override
    public void createWebDriver(String version){
        System.setProperty("webdriver.edge.driver", "src/main/resources/msedgedriver"+version+".exe");
        EdgeOptions options = new EdgeOptions();
        this.driver = new EdgeDriver(options);
        driver.manage().window().maximize();


    }
}
