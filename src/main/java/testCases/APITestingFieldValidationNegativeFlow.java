package testCases;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasKey;

public class APITestingFieldValidationNegativeFlow extends BaseTestAPI {
    @Test(dataProvider = "GETTestData")
    public void heroRegistrationMustBeFaied(HashMap<String, Object> testData) {
        try {
            RestAssured.given().baseUri(prop.getProperty("apiURL")).contentType(ContentType.JSON).body(testData).
                    log().all().when().post().then().assertThat().statusCode(400).body("$", hasKey("errorMsg")).
                    body("$", hasKey("timestamp")).log().all();
            actions.logger("Got status 400 for negative scenario "+testData, "pass");
            //DB Checks below-- Commented as tables are not getting created by app
            //String query = "Select count(nat_id) from WORKING_CLASS_HEROES where nat_id="+testData.get("natid")
            //ArrayList<String> dbQueryToFindNatidCreated = actions.verifyDataBase(prop.getProperty("dbHost"), prop.getProperty("dbName"), prop.getProperty("dbUsername"), prop.getProperty("dbPassword"), query);
            //Assert.assertTrue(dbQueryToFindNatidCreated.get(0),0);
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
            HashMap<String, Object> testDataS1 = actions.getAPITestData("NegativeFlow-InvalidNatIdFormat");
            testDataS1.put("natid", "natid" + System.currentTimeMillis());
            HashMap<String, Object> testDataS2 = actions.getAPITestData("NegativeFlow-InvalidNatIdNoNat");
            testDataS2.put("natid", "id-" + System.currentTimeMillis());
            HashMap<String, Object> testDataS13 = actions.getAPITestData("NegativeFlow-InvalidNatIdNoNat");
            testDataS13.put("natid", "natid-");
            HashMap<String, Object> testDataS3 = actions.getAPITestData("NegativeFlow-NoName");
            testDataS3.put("natid", "natid-" + System.currentTimeMillis());
            HashMap<String, Object> testDataS4 = actions.getAPITestData("NegativeFlow-NoGender");
            testDataS4.put("natid", "natid-" + System.currentTimeMillis());
            HashMap<String, Object> testDataS5 = actions.getAPITestData("NegativeFlow-InvalidGender");
            testDataS5.put("natid", "natid-" + System.currentTimeMillis());
            HashMap<String, Object> testDataS6 = actions.getAPITestData("NegativeFlow-FutureBirthDate");
            testDataS6.put("natid", "natid-" + System.currentTimeMillis());
            //HashMap<String, Object> testDataS12 = actions.getAPITestData("NegativeFlow-NoBirthDate");
            //testDataS12.put("natid","natid-"+System.currentTimeMillis());
            HashMap<String, Object> testDataS7 = actions.getAPITestData("NegativeFlow-InvalidFormatBirthDate");
            testDataS7.put("natid", "natid-" + System.currentTimeMillis());
            HashMap<String, Object> testDataS8 = actions.getAPITestData("NegativeFlow-InvalidFormatDeathDate");
            testDataS8.put("natid", "natid-" + System.currentTimeMillis());
            HashMap<String, Object> testDataS9 = actions.getAPITestData("NegativeFlow-FutureDeathDate");
            testDataS9.put("natid", "natid-" + System.currentTimeMillis());
            HashMap<String, Object> testDataS10 = actions.getAPITestData("NegativeFlow-NegativeSalary");
            testDataS10.put("natid", "natid-" + System.currentTimeMillis());
            HashMap<String, Object> testDataS11 = actions.getAPITestData("NegativeFlow-NegativeTax");
            testDataS11.put("natid", "natid-" + System.currentTimeMillis());

            return new Object[][]
                    {{testDataS1}, {testDataS2}, {testDataS3}, {testDataS4}, {testDataS5}, {testDataS6},
                            {testDataS7}, {testDataS8}, {testDataS9}, {testDataS10}, {testDataS11}, {testDataS13}};
        } catch (Exception e) {
            throw new Exception("Error while reading API test data " + e);
        }
    }
}
