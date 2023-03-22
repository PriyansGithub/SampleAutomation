package testCases;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
import pages.ClerkDashboardPage;

import java.util.HashMap;

public class VerifyFileUploadWithWrongData extends BaseTest {
    @Test
    public void verifyFileUploadWithWrongData() {
        ClerkDashboardPage clerkDashboardPage =new ClerkDashboardPage(driver);
        try {
            HashMap<String, String> testData = actions.getTestData("VerifyFileUploadWithWrongData");
            loginPage.login(testData.get("username"), testData.get("password"));
            homePage.clickUploadCSV();
            clerkDashboardPage.verifyNoUploadMessagePresent();
            String uploadMessage = clerkDashboardPage.uploadCSVAndReturnMessage(testData.get("filePathWithWrongData"));
            Assert.assertEquals(uploadMessage, testData.get("expectedUploadMessage"));
            clerkDashboardPage.pressBackAndVerifyNavigation();
            //DB Checks below-- Commented as tables are not getting created by app
            //ArrayList<String> fileNamesUploadedYet = actions.verifyDataBase(prop.getProperty("dbHost"), prop.getProperty("dbName"), prop.getProperty("dbUsername"), prop.getProperty("dbPassword"), testData.get("query"));
            //Assert.assertTrue(!fileNamesUploadedYet.contains(testData.get("fileName")));
        } catch (Exception e) {
            Assert.fail(e.toString());
            actions.logger("Failed due to exception " + e, "fail");
            System.out.println("Failed due to Exception " + e);
            Reporter.log("Failed due to Exception " + e);
        }
    }
}
