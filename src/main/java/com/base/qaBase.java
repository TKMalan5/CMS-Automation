package com.base;

import com.qa.testrailmanager.TestRailManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.time.Duration;

public class qaBase {

    protected WebDriver driver;
    protected String testCaseId;
    protected String custom_browser;

    @Parameters({"url", "browser"})
    @BeforeClass
    public void setup(String url, String browserName) {
        System.out.println("launching browser: "+ browserName);
        if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        } else {
            System.out.println("please pass the right browserName..." + browserName);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get(url);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterMethod
    public void addResultsToTestRail(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            TestRailManager.addResultsForTestCase(testCaseId, TestRailManager.TEST_CASE_PASS_STATUS, "");
        }else if(result.getStatus() == ITestResult.FAILURE) {
            TestRailManager.addResultsForTestCase(testCaseId, TestRailManager.TEST_CASE_FAIL_STATUS,
                    "test failed"+ result.getName() + " : FAILED");
        }
    }
}
