package testCases;

import Reporter.Reporter;
import helper.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseTestAPI extends Reporter {
    Properties prop;
    Actions actions;
    @Parameters("browser")
    @BeforeClass
    public void beforeTest(String browser) {
        File file = new File("resources/config.properties");
        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        prop = new Properties();
        try {
            prop.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
        actions = new Actions();
    }
}
