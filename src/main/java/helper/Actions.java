package helper;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import static Reporter.Reporter.test;

public class Actions extends Reporter {

    public void logger(String log, String logLevel) {
        System.out.println(log);
        Reporter.log(log);
        switch (logLevel.toUpperCase()) {
            case "PASS":
                test.pass(log);
                break;
            case "INFO":
                test.info(log);
                break;
            case "FAIL":
                test.fail(log);
                break;
            default:
                test.info(log);
        }
    }

        public String replacePlaceHolder (String locator, String text){
            locator = locator.replace("{replace}", text);
            return locator;
        }

        public void waitForElement (WebDriver driver, WebElement ele){
            WebDriverWait wait = new WebDriverWait(driver, 20);
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(ele));
        }

        public HashMap<String, String> getTestData (String scenarioName) throws Exception {
            HashMap<String, String> testData = new HashMap<>();
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("src/main/testData/" + scenarioName + ".json"));
            JSONObject jsonObject = (JSONObject) obj;
            ArrayList<String> keys = new ArrayList<>(jsonObject.keySet());
            for (int i = 0; i < jsonObject.size(); i++) {
                testData.put(keys.get(i), (String) jsonObject.get(keys.get(i)));
            }
            logger("Extracted test Data for Scenario= " + scenarioName + "  " + testData,"info");
            return testData;
        }

        public HashMap<String, Object> getAPITestData (String scenarioName) throws Exception {
            HashMap<String, Object> testData = new HashMap<>();
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("src/main/testData/ApiTestData/" + scenarioName + ".json"));
            JSONObject jsonObject = (JSONObject) obj;
            ArrayList<String> keys = new ArrayList<>(jsonObject.keySet());
            for (int i = 0; i < jsonObject.size(); i++) {
                testData.put(keys.get(i), jsonObject.get(keys.get(i)));
            }
            logger("Got test Data for API Test Scenario= " + scenarioName + "  " + testData,"info");
            return testData;
        }

        public ArrayList<String> verifyDataBase (String dbHost, String dbName, String username, String password, String
        query) throws Exception {
            ArrayList<String> databaseValues = new ArrayList<>();
            Connection connection = DriverManager.getConnection(
                    dbHost + dbName,
                    username, password
            );
            try {
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String val1 = resultSet.getString(1); // by column index
                    databaseValues.add(val1);
                }
            } catch (Exception e) {
                throw new Exception("Error while database query " + e);
            } finally {
                connection.close();
            }
            return databaseValues;
        }
    }
