package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage{
    @FindBy(css = ".cart_button")
    List<WebElement> removeButton;
    @FindBy(css = "#checkout")
    WebElement checkoutButton;
    @FindBy(css = "#continue-shopping")
    WebElement continueShoppingButton;
    public CartPage(WebDriver driver) {
        super(driver);
    }

//Removes an item from the cart
//Item position starts from 0

    public void removeItemFromCart(int itemPosition) throws InterruptedException
    {
        clickElement(removeButton.get(itemPosition));
    }

    public void clickCheckoutButton() throws InterruptedException {
        clickElement(checkoutButton);
    }

    public void clickCountinueShoppingButton() throws InterruptedException {
        clickElement(continueShoppingButton);
    }
}
