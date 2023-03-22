package pages;

import helper.Actions;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.util.ArrayList;

public class ClerkDashboardPage {
    WebDriver driver;
    Actions actions = new Actions();
    @FindBy(id = "upload-csv-file")
    WebElement chooseFileButton;
    @FindBy(css = "[id='notification-block'] h3")
    WebElement uploadNotification;
    @FindBy(css = "[onclick='uploadCsv()']")
    WebElement create;
    @FindBy(css = "[href*='dashboard']")
    WebElement back;
    @FindBy(id = "dropdownMenuButton2")
    WebElement addAHero;
    @FindBy(id = "name-in")
    WebElement name;
    @FindBy(id = "natid-in")
    WebElement natID;
    @FindBy(css = "[for='birth-date']+input")
    WebElement birthDate;
    @FindBy(css = "[for='death-date']+input")
    WebElement deathDate;
    @FindBy(css = "[id='brownie-points']+input")
    WebElement browniePoints;
    @FindBy(id = "salary-in")
    WebElement salary;
    @FindBy(id = "tax-paid-in")
    WebElement taxPaid;
    @FindBy(id = "gender-radio-2")
    WebElement femaleGender;
    @FindBy(css = "[onclick='addHero()']")
    WebElement createHero;

    //Constructor that will be automatically called as soon as the object of the class is created
    public ClerkDashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String uploadCSVAndReturnMessage(String filePath) {
        File f = new File(filePath);
        String absolute = f.getAbsolutePath();
        chooseFileButton.sendKeys(absolute);
        create.click();
        actions.waitForElement(driver, uploadNotification);
        actions.logger("uploaded CSV " + filePath + " and got the notification " + uploadNotification.getText(), "pass");
        return uploadNotification.getText();
    }

    public void verifyNoUploadMessagePresent() throws Exception {
        try {
            uploadNotification.getText();
            throw new Exception(uploadNotification.getText());
        } catch (NoSuchElementException e) {
            actions.logger("No upload Message present before upload of file", "pass");
        }
    }

    public void pressBackAndVerifyNavigation() {
        back.click();
        actions.waitForElement(driver, addAHero);
        actions.logger("Pressed back button and verified user is redirected to Home page", "pass");
    }

    public ArrayList<String> addHeroViaUI(String natIdString, String nameString, String genderString, String birthDateString,
                                          String deathDateString, String browniePointsString, String salaryString, String taxPaidString) {
        natIdString = natIdString + System.currentTimeMillis();
        natID.sendKeys(natIdString);
        name.sendKeys(nameString);
        if (genderString.equalsIgnoreCase("Female"))
            femaleGender.click();
        birthDate.sendKeys(birthDateString);
        if (deathDateString.length() != 0)
            deathDate.sendKeys(deathDateString);
        browniePoints.sendKeys(browniePointsString);
        salary.sendKeys(salaryString);
        taxPaid.sendKeys(taxPaidString);
        createHero.click();
        actions.waitForElement(driver, uploadNotification);
        actions.logger("Got Message " + uploadNotification, "info");
        ArrayList<String> returnArray = new ArrayList<>();
        returnArray.add(natIdString);
        returnArray.add(uploadNotification.getText());
        actions.logger("Created Hero via UI with NATID " + returnArray.get(0), "pass");
        return returnArray;
    }
}
