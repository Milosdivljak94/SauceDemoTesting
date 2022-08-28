package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutOverviewPage extends BasePage{
    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(css = "#finish")
    WebElement finishButton;

    public void clickFinishButton() throws InterruptedException {
        clickElement(finishButton);
    }

}
