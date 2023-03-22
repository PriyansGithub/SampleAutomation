package pages;

import helper.Actions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;
    Actions actions = new Actions();
    @FindBy(id = "dropdownMenuButton2")
    WebElement addAHero;
    @FindBy(css = "[id='dropdownMenuButton2']+ul li:nth-child(1)")
    WebElement add;
    @FindBy(css = "[id='dropdownMenuButton2']+ul li:nth-child(2)")
    WebElement uploadCSV;
    @FindBy(id = "upload-csv-file")
    WebElement chooseFileButton;

    //Constructor that will be automatically called as soon as the object of the class is created
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickUploadCSV() {
        addAHero.click();
        uploadCSV.click();
        actions.waitForElement(driver, chooseFileButton);
        actions.logger("Clicked on upload CSV option", "pass");
    }

    public void clickAddHero() {
        addAHero.click();
        add.click();
        actions.logger("Clicked on Add Button", "pass");
    }
}
