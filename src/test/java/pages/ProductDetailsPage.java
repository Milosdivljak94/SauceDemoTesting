package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetailsPage extends BasePage{

    @FindBy(css = ".btn_inventory")
    WebElement addToCartButton;
    @FindBy(css = ".shopping_cart_container")
    WebElement shoppingCartIcon;
    public ProductDetailsPage(WebDriver driver) {
        super(driver);

    }

    public void clickAddToCart() throws InterruptedException {
        clickElement(addToCartButton);
    }

    public void clickShoppingCartIcon() throws InterruptedException {
        clickElement(shoppingCartIcon);
    }
}
