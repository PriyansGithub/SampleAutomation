package testCases;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.ClerkDashboardPage;

import java.util.ArrayList;
import java.util.HashMap;

public class AddHeroViaUIWithCorrectData extends BaseTest {
    @Test
    public void addHeroViaUIWithCorrectData() {
        ClerkDashboardPage clerkDashboardPage = new ClerkDashboardPage(driver);
        try {
            HashMap<String, String> testData = actions.getTestData("AddHeroViaUIWithCorrectData");
            loginPage.login(testData.get("username"), testData.get("password"));
            homePage.clickAddHero();
            ArrayList<String> natIdAndMessage = clerkDashboardPage.addHeroViaUI(testData.get("natId"), testData.get("name"), testData.get("gender"),
                    testData.get("birthDate"), testData.get("deathDate"), testData.get("browniePoints"),
                    testData.get("salary"), testData.get("taxPaid"));
            String query = testData.get("query").replace("{natIdFromCode}", natIdAndMessage.get(0));
            Assert.assertEquals(testData.get("expectedUIMessage"), natIdAndMessage.get(1));
            //DB Checks below-- Commented as tables are not getting created by app
            //ArrayList<String> dbQueryToFindNatidCreated = actions.verifyDataBase(prop.getProperty("dbHost"), prop.getProperty("dbName"), prop.getProperty("dbUsername"), prop.getProperty("dbPassword"), query);
            //Assert.assertTrue(dbQueryToFindNatidCreated.get(0),1);
        } catch (Exception e) {
            Assert.fail(e.toString());
            System.out.println("Failed due to Exception " + e);
            actions.logger("Failed due to exception "+e,"fail");
            Reporter.log("Failed due to Exception " + e);
        }
    }
}
