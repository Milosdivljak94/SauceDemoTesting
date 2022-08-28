package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    WebDriver driver;
    @FindBy(css = "[name='user-name']")
    WebElement usernameBar;
    @FindBy(css = "#password")
    WebElement passwordBar;
    @FindBy(css = "#login-button")
    WebElement loginButton;
    public LoginPage(WebDriver driver) {
        super(driver);

    }

    public void enterUsername(String username) throws InterruptedException {
        typeText(usernameBar,username);
    }

    public void enterPassword(String password) throws InterruptedException {
        typeText(passwordBar,password);
    }

    public void clickLogin() throws InterruptedException {
        clickElement(loginButton);
    }
}
