package a_useraccess.a1_login;

import com.base.qaBase;
import com.qa.testrailmanager.TestRailManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

        boolean isDisplayed = option.isDisplayed();
        System.out.println("Is option displayed? " + isDisplayed);

        if (isDisplayed) {
            String optionText = option.getText();
            System.out.println("Option text: " + optionText);

            if (optionText.equals(expectedText)) {
                System.out.println("Option text matches expected value.");
                TestRailManager.addResultsForTestCase(testCaseId, TestRailManager.TEST_CASE_PASS_STATUS, "Option text matches expected value.");
            } else {
                System.out.println("Option text does not match expected value.");
                TestRailManager.addResultsForTestCase(testCaseId, TestRailManager.TEST_CASE_FAIL_STATUS, "Option text does not match expected value. Actual: " + optionText);
            }
        } else {
            System.out.println("Option is not displayed.");
            TestRailManager.addResultsForTestCase(testCaseId, TestRailManager.TEST_CASE_FAIL_STATUS, "Option is not displayed.");
        }

        driver.quit();

    }

}
