package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

public class SwagLabsTests extends BaseTest {

    // This method is executed before each test method to set up the test environment.
    @BeforeMethod
    public void setup(){
        baseSetUP();
    }

    // This method is executed after each test method to tear down the test environment.
    @AfterMethod
    public void tearDown(){
        baseTearDown();
    }

    // This test case simulates a user login, adds and removes products, and performs assertions.
    @Test
    public void login() throws InterruptedException {
        // Navigate to the Swag Labs login page.
        driver.get("https://www.saucedemo.com/v1/");

        // Create a LoginPage object and login with the specified credentials.
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user","secret_sauce");

        // Create a ProductsPage object.
        ProductsPage productPage = new ProductsPage(driver);

        // Add  products to the shopping cart.

        productPage.addProducts("Sauce Labs Onesie", "Test.allTheThings() T-Shirt (Red)");

        Thread.sleep(1000);
        // Remove the added products from the shopping cart.
        productPage.removeProducts("Sauce Labs Onesie","Test.allTheThings() T-Shirt (Red)");


        String [] prices = productPage.getPrices("Sauce Labs Onesie","Test.allTheThings() T-Shirt (Red)");
        String [] expectedPrices = {"$7.99", "$15.99"};

        Assert.assertEquals(prices,expectedPrices);
        Assert.assertTrue(loginPage.login.isDisplayed());

//        // Assert that the price of "Proizvod 1" is "0" and the price of "Sauce Labs Onesie" is "$7.99".
//        Assert.assertEquals(productPage.getPrice("Proizvod 1"),"0");
//        Assert.assertEquals(productPage.getPrice("Sauce Labs Onesie"),"$7.99");

        // Open the menu.
        productPage.openMenu();
    }
}
