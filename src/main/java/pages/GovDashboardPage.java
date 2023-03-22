package pages;

import helper.Actions;
import jdk.javadoc.doclet.Reporter;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.util.List;

public class GovDashboardPage {
    WebDriver driver;
    Actions actions = new Actions();
    @FindBy(css = "[value='List ALL']")
    WebElement listAll;
    @FindBy(id = "search-all-table_info")
    WebElement tableInfo;
    @FindBy(css = "[type='search'][aria-controls='search-all-table']")
    WebElement searchTable;
    @FindBy(css = "[id='search-all-table_processing'][style='display: block;']")
    WebElement searchProcessing;
    @FindBy(id = "natid")
    WebElement searchNATID;
    @FindBy(css = "[id='natid']+button")
    WebElement searchNATIDClick;
    @FindBy(css = "[id='results-block'] tr")
    WebElement searchHeaderForSearchByNatId;
    @FindBy(css = "[id='results-block'] tbody tr")
    List<WebElement> searchResultsForSearchByNatId;

    //Constructor that will be automatically called as soon as the object of the class is created
    public GovDashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int clickListAllAndReturnTotalRecords() throws InterruptedException {
        listAll.click();
        actions.waitForElement(driver, tableInfo);
        int numberOfRecordsOnUI = getCountOfRecordsInTable();
        actions.logger("Verified that List All button is working and returned " + numberOfRecordsOnUI, "pass");
        return numberOfRecordsOnUI;
    }

    public void clearSearch() throws InterruptedException {
        searchTable.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        Thread.sleep(2000);
        actions.logger("Cleared the search button ", "pass");
    }

    public int searchAndReturnRecords(String searchItem) throws InterruptedException {
        searchTable.sendKeys(searchItem);
        for (int i = 0; i < 20; i++) {
            try {
                searchProcessing.isDisplayed();
                Thread.sleep(1000);
            } catch (Exception e) {
                break;
            }
        }
        int numberOfRecordsOnUI = getCountOfRecordsInTable();
        actions.logger("Verified that Search By Field is working and returned " + numberOfRecordsOnUI + " For " + searchItem, "pass");
        return numberOfRecordsOnUI;
    }

    private int getCountOfRecordsInTable() throws InterruptedException {
        int numberOfRecordsOnUI = 0;
        for (int i = 0; i < 20; i++) {
            try {
                String info = tableInfo.getText();
                numberOfRecordsOnUI = Integer.parseInt(info.substring(info.indexOf("of") + 3, info.indexOf("entries") - 1).trim());
                break;
            } catch (Exception e) {
                Thread.sleep(1000);
            }
        }
        return numberOfRecordsOnUI;
    }

    public int searchByNatId(String natId) throws InterruptedException {
        searchNATID.sendKeys(natId);
        searchNATIDClick.click();
        actions.waitForElement(driver, searchHeaderForSearchByNatId);
        actions.logger("Entered " + natId + " in the search bar and got records= " + searchResultsForSearchByNatId.size(), "pass");
        return searchResultsForSearchByNatId.size();
    }
}
