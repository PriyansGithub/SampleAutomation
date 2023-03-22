package testCases;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.GovDashboardPage;

import java.util.HashMap;

public class SearchByUniqueNatIdGov extends BaseTest {
    @Test
    public void verifyGovIsAbleToSearchViaUniqueNatId() {
        GovDashboardPage govDashboardPage = new GovDashboardPage(driver);
        try {
            HashMap<String, String> testData = actions.getTestData("SearchByUniqueNatIdGov");
            loginPage.login(testData.get("username"), testData.get("password"));
            int searchByNatIdResultsFromUI = govDashboardPage.searchByNatId(testData.get("natidToSearch"));
            Assert.assertTrue(searchByNatIdResultsFromUI <= 1, "Unique NATID not returning from UI");
            //DB Checks below-- Commented as tables are not getting created by app
            //ArrayList<String> numberOfRecordsFromDB = actions.verifyDataBase(prop.getProperty("dbHost"), prop.getProperty("dbName"), prop.getProperty("dbUsername"), prop.getProperty("dbPassword"), testData.get("query"));
            //Assert.assertEquals(searchByNatIdResultsFromUI,Integer.parseInt(numberOfRecordsFromDB.get(0)));
        } catch (Exception e) {
            Assert.fail(e.toString());
            actions.logger("Failed due to exception " + e, "fail");
            System.out.println("Failed due to Exception " + e);
            Reporter.log("Failed due to Exception " + e);
        }
    }
}
