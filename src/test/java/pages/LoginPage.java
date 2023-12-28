package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{



    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }
    @FindBy(css ="#user-name")
    WebElement username;
    @FindBy(css ="#password")
    WebElement password;
    @FindBy(css ="#login-button")
    public  WebElement login;



    /**
     * Use this method to login to app with valid username and password
     * @param usernameValue username
     * @param passwordValue password
     */
    public void login(String usernameValue, String passwordValue){
        enterUsername(usernameValue);
        enterPassword(passwordValue);
        clickLogin();
    }

    public void enterUsername(String usernameValue){
        sendKeys(username, usernameValue, "Username input");


    }
    public void enterPassword(String passwordValue){
        sendKeys(password, passwordValue, "Password input");

    }
    public void clickLogin(){
        clickElement(login, "Login Button");
    }

}
