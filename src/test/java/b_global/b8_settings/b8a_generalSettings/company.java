package b_global.b8_settings.b8a_generalSettings;

import static com.constants.company_constants.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class company {

        WebDriver driver;
    //Verify that the user can Edit the Company Details
    @Test(priority = 1)
    public void updateCompany() throws InterruptedException {

        Thread.sleep(4000);
        driver.findElement(By.partialLinkText("Settings")).click();
        Thread.sleep(3000);
        driver.findElement(By.partialLinkText("Company")).click();
        WebElement reseller = driver.findElement(By.xpath("//input[@name='company_name']"));
        reseller.clear();
        reseller.sendKeys(reseller_name);
        WebElement regno = driver.findElement(By.xpath("//input[@name='reg_no']"));
        regno.clear();
        regno.sendKeys(company_reg_no);
        WebElement clfee = driver.findElement(By.xpath("//input[@name='transfer_cost']"));
        clfee.clear();
        clfee.sendKeys(transfer_clearance_fee);
        WebElement telno = driver.findElement(By.xpath("//input[@name='account_tel']"));
        telno.clear();
        telno.sendKeys(telephone_no);
        WebElement outemail = driver.findElement(By.xpath("//input[@name='general_email']"));
        outemail.clear();
        outemail.sendKeys(outgoing_email);

    }

    //Verify that the user can Edit the Company Bank Details
    @Test (priority = 2)
    public void editCompanyBank() {
        WebElement accountHolder = driver.findElement(By.id("account_name"));
        accountHolder.clear();
        accountHolder.sendKeys(account_holder);
        driver.findElement(By.xpath("(//span[contains(@class,'filter-option pull-left')])[1]")).click();
        driver.findElement(By.linkText("First National Bank")).click();
        driver.findElement(By.xpath("(//span[contains(@class,'filter-option pull-left')])[2]")).click();
        driver.findElement(By.linkText("Current"));
        WebElement accountNumber = driver.findElement(By.id("account_number"));
        accountNumber.clear();
        accountNumber.sendKeys(account_number);
        WebElement branchCode = driver.findElement(By.id("branch_code"));
        branchCode.clear();
        branchCode.sendKeys(branch_code);
        WebElement branchName = driver.findElement(By.id("branch_name"));
        branchName.clear();
        branchName.sendKeys(branch_name);

        driver.findElement(By.xpath("//input[@value='Save details']")).click();

    }

}
