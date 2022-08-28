package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage{

    public ProductsPage(WebDriver driver) {
        super(driver);

    }
    public void addItemToCart(String itemName) throws InterruptedException {
    clickElement(driver.findElement(By.cssSelector("[name='add-to-cart-" + itemName + "']"))); //itemName must be in the same format as in selector(xxx-xxx-xxx)
    }

    public void openItemDetailsPage(int ItemNumber) throws InterruptedException {
        clickElement(driver.findElements(By.cssSelector(".inventory_item_name")).get(ItemNumber));
    }
}
