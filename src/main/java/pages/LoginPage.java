package pages;

import helper.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class LoginPage {
    WebDriver driver;
    Actions actions = new Actions();
    @FindBy(id = "username-in")
    WebElement username;
    @FindBy(id = "password-in")
    WebElement password;
    @FindBy(css = "[type='submit']")
    WebElement submit;
    @FindBy(css = "[action='/logout'] button")
    WebElement logout;

    //Constructor that will be automatically called as soon as the object of the class is created
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login(String usernameString, String passwordString) {
        username.sendKeys(usernameString);
        password.sendKeys(passwordString);
        submit.click();
        actions.waitForElement(driver, logout);
        actions.logger("Logged In to the application as " + usernameString, "pass");
    }
}
