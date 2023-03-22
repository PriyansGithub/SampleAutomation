package testCases;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.GovDashboardPage;

import java.util.HashMap;

public class ResetOnClearingSearchBar extends BaseTest {
    @Test
    public void verifyOnClearingSearchBarResultsReset() {
        GovDashboardPage govDashboardPage = new GovDashboardPage(driver);
        try {
            HashMap<String, String> testData = actions.getTestData("ResetOnClearingSearchBar");
            loginPage.login(testData.get("username"), testData.get("password"));
            int totalRecordsInTable = govDashboardPage.clickListAllAndReturnTotalRecords();
            int numberOfRecordsAfterSearch = govDashboardPage.searchAndReturnRecords(testData.get("nameToSearch"));
            govDashboardPage.clearSearch();
            int numberOfRecordsAfterReset = govDashboardPage.searchAndReturnRecords("");
            Assert.assertEquals(totalRecordsInTable, numberOfRecordsAfterReset);
        } catch (Exception e) {
            Assert.fail(e.toString());
            actions.logger("Failed due to exception " + e, "fail");
            System.out.println("Failed due to Exception " + e);
            Reporter.log("Failed due to Exception " + e);
        }
    }
}
