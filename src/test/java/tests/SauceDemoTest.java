package tests;

import com.sun.org.glassfish.gmbal.Description;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.*;

import java.io.File;
import java.io.IOException;


public class SauceDemoTest extends BaseTest {

    @BeforeMethod
    @Parameters
            ({"BROWSER", "BROWSER_VERSION", "WAIT", "ENV"})
    public void setUp(String browser, String browserVersion, String wait, String env) throws Exception {
        setUPTest(browser, browserVersion, Integer.parseInt(wait));
        startApplication(env);
    }

//Takes screenshot if test fails

    @AfterMethod
    public void tearDown(ITestResult result)
    {
        if(ITestResult.FAILURE==result.getStatus())
        {
            try
            {
                TakesScreenshot ts=(TakesScreenshot)driver;
                File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                try{
                    FileUtils.copyFile(file, new File("screenshots/" + result.getName() + ".png"));
                    System.out.println("Screenshot taken");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            catch (Exception e)
            {
                System.out.println("Exception while taking screenshot "+e.getMessage());
            }
        }
        quit();
    }

    @Test
    @Parameters
            ({"userName", "password", "itemName", "numberOfProductsInCart", "itemNumber", "firstItemName", "secondItemName", "itemPosition", "remainingItemName", "firstName", "lastName", "zipCode", "conformationMessage"})
    public void sauceDemoTestEnd2End(String userName,
                              String password,
                              String itemName,
                              int numberOfProductsInCart,
                              int itemNumber,
                              String firstItemName,
                              String secondItemName,
                              int itemPosition,
                              String remainingItemName,
                              String firstName,
                              String lastName, int zipCode, String conformationMessage) throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutFormPage checkoutFormPage = new CheckoutFormPage(driver);
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);


        loginPage.enterUsername(userName);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        productsPage.addItemToCart(itemName);

//Verifies the item has been added to cart

        Assert.assertEquals(String.valueOf(numberOfProductsInCart), driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText());
        productsPage.openItemDetailsPage(itemNumber);
        productDetailsPage.clickAddToCart();
        productDetailsPage.clickShoppingCartIcon();

//Verifies the correct items are added to cart

        Assert.assertEquals(firstItemName, driver.findElements(By.xpath("//div[@class='inventory_item_name']")).get(0).getText());
        Assert.assertEquals(secondItemName, driver.findElements(By.xpath("//div[@class='inventory_item_name']")).get(1).getText());
        cartPage.removeItemFromCart(itemPosition);

//Verifies the correct item remains in cart

        Assert.assertEquals(remainingItemName, driver.findElements(By.xpath("//div[@class='inventory_item_name']")).get(0).getText());
        cartPage.clickCheckoutButton();
        checkoutFormPage.enterFirstName(firstName);
        checkoutFormPage.enterLastName(lastName);
        checkoutFormPage.enterZipCode(zipCode);
        checkoutFormPage.clickContinueButton();
        checkoutOverviewPage.clickFinishButton();

//Verifies the order was successful

        Assert.assertEquals(conformationMessage, driver.findElement(By.xpath("//h2[@class='complete-header']")).getText());
    }

        @Test
        @Description("This test checks if log in is possible with invalid password")
        @Parameters
                ({"userName", "password", "errorMessage"})

        public void failLoginTest(String userName,
                                   String password,
                                   String errorMessage
                                   ) throws InterruptedException {

            LoginPage loginPage = new LoginPage(driver);


            loginPage.enterUsername(userName);
            loginPage.enterPassword(password);
            loginPage.clickLogin();
            Assert.assertEquals(errorMessage, driver.findElement(By.xpath("//h3[@data-test='error']")).getText());



        }

    @Test
    @Description("This test checks if checking out is possible without zipcode")
    @Parameters
            ({"userName", "password", "itemName", "itemNumber", "itemPosition", "firstName", "lastName", "errorMessage"})
    public void checkoutWithoutZipcode(String userName,
                                        String password,
                                        String itemName,
                                        int itemNumber,
                                        int itemPosition,
                                        String firstName,
                                        String lastName,
                                        String errorMessage) throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutFormPage checkoutFormPage = new CheckoutFormPage(driver);


        loginPage.enterUsername(userName);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        productsPage.addItemToCart(itemName);
        productsPage.openItemDetailsPage(itemNumber);
        productDetailsPage.clickAddToCart();
        productDetailsPage.clickShoppingCartIcon();
        cartPage.removeItemFromCart(itemPosition);
        cartPage.clickCheckoutButton();
        checkoutFormPage.enterFirstName(firstName);
        checkoutFormPage.enterLastName(lastName);
        checkoutFormPage.clickContinueButton();

//Verifies the correct message is displayed

        Assert.assertEquals(errorMessage, driver.findElement(By.xpath("//h3[@data-test='error']")).getText());

    }

    @Test
    @Description("This test checks if continue shopping button works properly")
    @Parameters
            ({"userName", "password", "itemName", "pageTitle"})
    public void continueShopping(String userName,
                                 String password,
                                 String itemName,
                                 String pageTitle
                                 ) throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);
        CartPage cartPage = new CartPage(driver);


        loginPage.enterUsername(userName);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        productsPage.addItemToCart(itemName);
        productDetailsPage.clickShoppingCartIcon();
        cartPage.clickCountinueShoppingButton();

//Verifies the page title name

        Assert.assertEquals(pageTitle,driver.getTitle());
    }


}
