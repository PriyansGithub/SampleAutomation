package testCases;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.ClerkDashboardPage;

import java.util.HashMap;

public class VerifyBackButtonNavigationInClerk extends BaseTest {
    @Test
    public void verifyClerkIsAbleToNavigateByBackButton() {
        ClerkDashboardPage clerkDashboardPage =new ClerkDashboardPage(driver);
        try {
            HashMap<String, String> testData = actions.getTestData("VerifyBackButtonNavigationInClerk");
            loginPage.login(testData.get("username"), testData.get("password"));
            homePage.clickUploadCSV();
            clerkDashboardPage.verifyNoUploadMessagePresent();
            clerkDashboardPage.pressBackAndVerifyNavigation();
        } catch (Exception e) {
            Assert.fail(e.toString());
            actions.logger("Failed due to exception " + e, "fail");
            System.out.println("Failed due to Exception " + e);
            Reporter.log("Failed due to Exception " + e);
        }
    }
}
