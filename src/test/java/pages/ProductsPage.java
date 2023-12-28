package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage extends BasePage {

    // Constructor to initialize the WebDriver and PageFactory
    public ProductsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // WebElement for the shopping cart icon
    @FindBy(css = ".shopping_cart_container")
    WebElement shoppingCart;

    // XPath expressions for product-related elements
    String productNameXpath = "//div[@class = 'inventory_item_name' and contains(text(),'$$')]";
    String productPriceXpath = "//div[@class = 'inventory_item_name' and contains(text(),'$$')]/../../..//div[@class = 'inventory_item_price']";
    String productAddXpath = "//div[@class = 'inventory_item_name' and contains(text(),'$$')]/../../..//button[contains(text(),'ADD TO CART')]";
    String productRemoveBtnXpath = "//div[@class = 'inventory_item_name' and contains(text(),'$$')]/../../..//button[text()='REMOVE']";

    // Method to add one or more products to the shopping cart
    public void addProducts(String... products) throws InterruptedException {
        for (String product : products) {
            clickElement(driver.findElement(By.xpath(productAddXpath.replace("$$", product))), "Add Product - " + product);
        }
    }

    // Method to remove a specific product from the shopping cart
    public void removeProduct(String productName) throws InterruptedException {
        clickElement(driver.findElement(By.xpath(productRemoveBtnXpath.replace("$$", productName))), "Remove Product - " + productName);
    }

    // Method to remove one or more products from the shopping cart
    public void removeProducts(String... products) throws InterruptedException {
        for (String product : products) {
            clickElement(driver.findElement(By.xpath(productRemoveBtnXpath.replace("$$", product))), "Remove Product - " + product);
        }
    }

    // Method to get the price of a specific product
    public String getPrice(String productName) {
        if (isElementPresent(driver.findElements(By.xpath(productPriceXpath.replace("$$", productName))))) {
            return getText(driver.findElement(By.xpath(productPriceXpath.replace("$$", productName))), "Price for " + productName);
        } else {
            // If the element is not present, return "0" or handle accordingly
            return "0";
        }
    }

    //        if (isElementPresent(driver.findElements(By.xpath(productPriceXpath.replace("$$", product))))) { //Ovako proveravamo da li postoji na stranici
//            return getText(driver.findElement(By.xpath(productPriceXpath.replace("$$", product))), "Price for "+ product);
//
//        } else {
//            //return next pages until element found or until there are no more pages - mora da ima return
//            return "0";
//        }

    // Method to get the prices of multiple products
    public String[] getPrices(String... products) {
        String[] prices = new String[products.length];

        for (int i = 0; i < products.length; i++) {
            prices[i] = getText(driver.findElement(By.xpath(productPriceXpath.replace("$$", products[i]))), "Price for " + products[i]);
        }

        return prices;
    }

    // Method to click on the shopping cart icon
    public void clickShoppingCart() {
        clickElement(shoppingCart, "Shopping cart");
    }
}
