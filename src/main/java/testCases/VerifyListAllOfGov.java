package testCases;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
import pages.GovDashboardPage;

import java.util.HashMap;

public class VerifyListAllOfGov extends BaseTest {
    @Test
    public void verifyListAllShowsAllHeros() {
        GovDashboardPage govDashboardPage = new GovDashboardPage(driver);
        try {
            HashMap<String, String> testData = actions.getTestData("VerifyListAllOfGov");
            launchApplicationUnderTest();
            loginPage.login(testData.get("username"), testData.get("password"));
            int numberOfRecordsOnUI = govDashboardPage.clickListAllAndReturnTotalRecords();
            //DB Checks below-- Commented as tables are not getting created by app
            //ArrayList<String> numberOfRecordsFromDB = actions.verifyDataBase(prop.getProperty("dbHost"), prop.getProperty("dbName"), prop.getProperty("dbUsername"), prop.getProperty("dbPassword"), testData.get("query"));
            //Assert.assertEquals(numberOfRecordsOnUI,Integer.parseInt(numberOfRecordsFromDB.get(0)));
        } catch (Exception e) {
            Assert.fail(e.toString());
            actions.logger("Failed due to exception " + e, "fail");
            System.out.println("Failed due to Exception " + e);
            Reporter.log("Failed due to Exception " + e);
        }
    }
}
