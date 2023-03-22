package testCases;

import Reporter.Reporter;
import driverManagers.DriverManager;
import driverManagers.DriverManagerFactory;
import helper.Actions;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
import pages.Logout;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseTest extends Reporter {

    DriverManager driverManager;
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    Logout logout;
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
        driverManager = DriverManagerFactory.getManager(browser);
        driver = driverManager.getDriver();
        Dimension d = new Dimension(275, 812);
        if(browser.equalsIgnoreCase("mobile")){
            driver.manage().window().setSize(d);
        }
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        actions = new Actions();
        logout = new Logout(driver);
    }

    @AfterClass
    public void afterClass() {
        logout.logout();
        driverManager.quitDriver();
    }
    @BeforeClass
    public void launchApplicationUnderTest() {
        driver.get(prop.getProperty("url"));
        Assert.assertEquals(prop.getProperty("title"), driver.getTitle());
    }
}
