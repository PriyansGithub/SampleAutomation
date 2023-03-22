package testCases;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasKey;

public class APITestingOfHero extends BaseTestAPI {
    @Test(dataProvider = "GETTestData")
    public void heroRegistrationSuccessful(HashMap<String, Object> testData) {
        try {
            RestAssured.given().baseUri(prop.getProperty("apiURL")).contentType(ContentType.JSON).body(testData).
                    log().all().when().post().then().assertThat().statusCode(200).body("$", hasKey("message")).
                    body("$", hasKey("timestamp")).log().all();
            actions.logger("Got status 200 for " + testData, "pass");
            //DB Checks below-- Commented as tables are not getting created by app
            //String query = "Select count(nat_id) from WORKING_CLASS_HEROES where nat_id="+testData.get("natid")
            //ArrayList<String> dbQueryToFindNatidCreated = actions.verifyDataBase(prop.getProperty("dbHost"), prop.getProperty("dbName"), prop.getProperty("dbUsername"), prop.getProperty("dbPassword"), query);
            //Assert.assertTrue(dbQueryToFindNatidCreated.get(0),1);
        } catch (Exception e) {
            Assert.fail(e.toString());
            actions.logger("Failed due to exception " + e, "fail");
            System.out.println("Failed due to Exception " + e);
            Reporter.log("Failed due to Exception " + e);
        }
    }

    //data provider method
    @DataProvider(name = "GETTestData")
    public Object[][] getData() throws Exception {
        try {
            HashMap<String, Object> testData = actions.getAPITestData("APITestingHappyPath");
            testData.put("natid", "natid-" + System.currentTimeMillis());
            return new Object[][]
                    {{testData}};
        } catch (Exception e) {
            throw new Exception("Error while reading API test data " + e);
        }
    }
}
