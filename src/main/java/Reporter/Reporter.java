package Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestResult;
import org.testng.TestNG;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class Reporter {
    // directory where output is to be printed
    public static ExtentSparkReporter spark;
    public static ExtentReports extent;
    public static ExtentTest test;

    @BeforeSuite
    public void setUpReport() {
        spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/testReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @BeforeClass
    public void startTest() {
        ITestResult itr = org.testng.Reporter.getCurrentTestResult();
        test = extent.createTest(itr.getInstance().getClass().getName());
    }

    @AfterSuite
    public void getResult() {
        extent.flush();
    }

}
