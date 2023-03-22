package testCases;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.GovDashboardPage;

import java.util.HashMap;

public class SearchByInvalidNatIdGov extends BaseTest {
    @Test
    public void verifyGovIsAbleToSearchViaUniqueNatId() {
        GovDashboardPage govDashboardPage = new GovDashboardPage(driver);
        try {
            HashMap<String, String> testData = actions.getTestData("SearchByInvalidNatIdGov");
            loginPage.login(testData.get("username"), testData.get("password"));
            int searchByNatIdResultsFromUI = govDashboardPage.searchByNatId(testData.get("natidToSearch"));
            Assert.assertEquals(searchByNatIdResultsFromUI, 0, "Invalid NATID got returned from UI");
        } catch (Exception e) {
            Assert.fail(e.toString());
            actions.logger("Failed due to exception " + e, "fail");
            System.out.println("Failed due to Exception " + e);
            Reporter.log("Failed due to Exception " + e);
        }
    }
}
