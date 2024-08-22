package a_useraccess.a1_login;

import com.base.qaBase;
import com.qa.testrailmanager.TestRailManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.constants.user_constants.*;

public class a_SuperUser extends qaBase {

    @Test(priority = 1)
    public void  superUserTest() {

        testCaseId  = "23";

        System.out.println("id");
        driver.findElement(By.id("login_username")).sendKeys(usernameSuperUser);
        System.out.println("test2");
        driver.findElement(By.id("login_password")).sendKeys(password);
        driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        driver.findElement(By.xpath("//button[@title='Select Profile']")).click();
        driver.findElement(By.xpath("(//a[@class='opt '])[1]")).click();
    }

    @Test(priority = 2)
    public void confirmSUProfile() {

        testCaseId = "24";

        WebElement dropdownMenu = driver.findElement(By.xpath("(//a[@data-toggle='dropdown'])[1]"));
        dropdownMenu.click();

        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dropdownOptions = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div/ul/li/ul[1]")));

        WebElement option = dropdownOptions.findElement(By.xpath("//body//div//ul//div//div[1]"));

        try {
            boolean isDisplayed = option.isDisplayed();

            // Assert that the option is displayed
            Assert.assertTrue(isDisplayed, "Option should be displayed.");

            String optionText = option.getText();

            // Assert that the option text matches the expected text
            Assert.assertEquals(optionText, expectedText, "User Type does not match expected value. Actual: " + optionText + ", Expected: " + expectedText);

            // Report success to TestRail
            TestRailManager.addResultsForTestCase(testCaseId, TestRailManager.TEST_CASE_PASS_STATUS, "User Type matches the expected value.");
        } catch (AssertionError e) {
            // Report failure to TestRail with additional context
            String errorMessage = "Test failed: " + e.getMessage();
            TestRailManager.addResultsForTestCase(testCaseId, TestRailManager.TEST_CASE_FAIL_STATUS, errorMessage);
            throw e; // Rethrow the exception to ensure the test fails
        } finally {
            driver.quit(); // Ensure WebDriver quits even if an exception is thrown
        }
    }
}