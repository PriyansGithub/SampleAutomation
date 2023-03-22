package pages;

import helper.Actions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Logout {
    WebDriver driver;
    Actions actions = new Actions();
    @FindBy(css = "[action='/logout'] button")
    WebElement logout;

    //Constructor that will be automatically called as soon as the object of the class is created
    public Logout(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void logout() {
        logout.click();
        actions.logger("Logged Out From the application","pass");
    }
}
