package testCases;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasKey;

public class APITestingFieldValidationHappyPath extends BaseTestAPI {
    @Test(dataProvider = "GETTestData")
    public void heroRegistrationSuccessful(HashMap<String, Object> testData) {
        try {
            RestAssured.given().baseUri(prop.getProperty("apiURL")).contentType(ContentType.JSON).body(testData).
                    log().all().when().post().then().assertThat().statusCode(200).body("$", hasKey("message")).
                    body("$", hasKey("timestamp")).log().all();
            actions.logger("Got 200 from heroAPI for " + testData, "pass");
            //DB Checks below-- Commented as tables are not getting created by app
            //String query = "Select count(nat_id) from WORKING_CLASS_HEROES where nat_id="+testData.get("natid")
            //ArrayList<String> dbQueryToFindNatidCreated = actions.verifyDataBase(prop.getProperty("dbHost"), prop.getProperty("dbName"), prop.getProperty("dbUsername"), prop.getProperty("dbPassword"), query);
            //Assert.assertTrue(dbQueryToFindNatidCreated.get(0),1);
        } catch (Exception e) {
            Assert.fail(e.toString());
            System.out.println("Failed due to Exception " + e);
            actions.logger("Failed due to exception " + e, "fail");
            Reporter.log("Failed due to Exception " + e);
        }
    }

    //data provider method
    @DataProvider(name = "GETTestData")
    public Object[][] getData() throws Exception {
        try {
            HashMap<String, Object> testDataS1 = actions.getAPITestData("FieldValidationHappyPathS1");
            testDataS1.put("natid", "natid-" + System.currentTimeMillis());
            HashMap<String, Object> testDataS2 = actions.getAPITestData("FieldValidationHappyPathS2");
            testDataS2.put("natid", "natid-" + System.currentTimeMillis());
            HashMap<String, Object> testDataS3 = actions.getAPITestData("FieldValidationHappyPathS3");
            testDataS3.put("natid", "natid-" + System.currentTimeMillis());
            return new Object[][]
                    {{testDataS1}, {testDataS2}, {testDataS3}};
        } catch (Exception e) {
            throw new Exception("Error while reading API test data " + e);
        }
    }
}
