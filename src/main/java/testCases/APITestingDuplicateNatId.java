package testCases;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasKey;

public class APITestingDuplicateNatId extends BaseTestAPI {
    @Test(dataProvider = "GETTestData")
    public void userRegistrationWithDDuplicateNatId(HashMap<String, Object> testData) {
        try {
            //DB Checks below-- Commented as tables are not getting created by app
            //String query = "Select name,gender,salary,birthdate,deathdate,taxpaid from WORKING_CLASS_HEROES where nat_id="+testData.get("natid")
            //ArrayList<String> dbQueryForBeforeAPICall = actions.verifyDataBase(prop.getProperty("dbHost"), prop.getProperty("dbName"), prop.getProperty("dbUsername"), prop.getProperty("dbPassword"), query);

            RestAssured.given().baseUri(prop.getProperty("apiURL")).contentType(ContentType.JSON).body(testData).
                    log().all().when().post().then().assertThat().statusCode(400).body("$", hasKey("errorMsg")).
                    body("$", hasKey("timestamp")).log().all();
            actions.logger("Got statusCode 400 for duplicate NatID in " + testData, "pass");
            //String query = "Select name,gender,salary,birthdate,deathdate,taxpaid from WORKING_CLASS_HEROES where nat_id="+testData.get("natid")
            //ArrayList<String> dbQueryForAfterAPICall = actions.verifyDataBase(prop.getProperty("dbHost"), prop.getProperty("dbName"), prop.getProperty("dbUsername"), prop.getProperty("dbPassword"), query);
            //Assert.assertEquals(dbQueryForBeforeAPICall,dbQueryForAfterAPICall,"Data is getting modified on Bad Request);
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
            HashMap<String, Object> testData = actions.getAPITestData("RepeatedNatId");
            return new Object[][]
                    {{testData}};
        } catch (Exception e) {
            throw new Exception("Error while reading API test data " + e);
        }
    }
}
