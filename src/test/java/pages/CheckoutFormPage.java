package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutFormPage extends BasePage{
    public CheckoutFormPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#first-name")
    WebElement firstNameBar;
    @FindBy(css = "#last-name")
    WebElement lastNameBar;
    @FindBy(css = "#postal-code")
    WebElement zipCodeBar;
    @FindBy(css = "[type='submit']")
    WebElement continueButton;


    public void enterFirstName(String firstName) throws InterruptedException {
        typeText(firstNameBar, firstName);
    }

    public void enterLastName(String lastName) throws InterruptedException {
        typeText(lastNameBar, lastName);
    }

    public void enterZipCode(int zipCode) throws InterruptedException {
        typeText(zipCodeBar, String.valueOf(zipCode));
    }

    public void clickContinueButton() throws InterruptedException {
        clickElement(continueButton);
    }


}
