package methods;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CommonMethods {
    public int waitTime = 20;
    WebDriver driver;

    public CommonMethods(WebDriver driver) {
        this.driver = driver;
    }


    public void clickElement(WebElement element) throws InterruptedException {
        WebDriverWait wdWait = new WebDriverWait(driver, waitTime);
        wdWait.until(ExpectedConditions.visibilityOf(element));
        wdWait.until(ExpectedConditions.elementToBeClickable(element));

        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).build().perform();
            element.click();
        } catch (StaleElementReferenceException e) {
            Thread.sleep(1000);
            element.click();
        }
    }

    public void typeText(WebElement element, String text) throws InterruptedException {
        WebDriverWait wdWait = new WebDriverWait(driver, waitTime);
        wdWait.until(ExpectedConditions.visibilityOf(element));
        wdWait.until(ExpectedConditions.elementToBeClickable(element));

        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).build().perform();
            element.sendKeys(text);
        } catch (StaleElementReferenceException e) {
            Thread.sleep(1000);
            element.sendKeys(text);
        }
    }

}