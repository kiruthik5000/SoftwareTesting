package runner;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utils.*;

public class TestRunner extends Base{
    // driver
    WebDriverHelper helper;

    ExtentReports report;


    @BeforeTest
    public void report() {
        report = Reporter.generateExtentReport("report");
    }
    @BeforeMethod
    public void getUrl(){
        openBrowser();
        helper = new WebDriverHelper(driver);
    }

    @Test
    public void t1() {
        ExtentTest test = report.createTest("Test 1");
        LoggerHandler.info("Test Created");
        By e1 = By.xpath("(//span[contains(text(), 'Company')])[1]");
        helper.hoverOverElement(e1);
        By e2 = By.xpath("//a[contains(text(), 'About us')]");
        test.log(Status.PASS, "click on about us");
        helper.clickOnElement(e2);
        By e3 = By.xpath("(//a/img)[6]");
        helper.javascriptScroll(e3);
        LoggerHandler.info("scorlled to the element");
        helper.clickOnElement(e3);
        LoggerHandler.info("click on ginger");
        helper.switchToNewWindow();
        By e4 = By.xpath("//div/button/div");
        helper.clickOnElement(e4);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // implicit
        LoggerHandler.info("switched window");
        Screenshot.captureScreenShot("ss");
        LoggerHandler.info("Screenshot captured");
    }
    @Test
    public void t2() throws IOException {
        ExtentTest test = report.createTest("T2");
        By e1 = By.xpath("//div/span[text()='Contact us']");
        helper.hoverOverElement(e1);
        helper.clickOnElement(e1);
        LoggerHandler.info("Clicked on contact us");
        Screenshot.captureScreenShot("contact_screenshot");
        Reporter.attachScreenshotToReport("contact_screenshot", test, null);
        LoggerHandler.info("captured screenshot");


        // excel
        String out = ExcelReader.readdata("./data/data.xlsx", "sheet1", 0, 0);
        System.out.println(out);
    }
    @Test
    public void t3() {
        By e1 = By.xpath("//div/a[text()='Privacy Policy']");
        helper.javascriptScroll(e1);
        helper.clickOnElement(e1);
        helper.switchToNewWindow();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        By e2 = By.xpath("//div/p[text()='Subscribe']");
        helper.javascriptScroll(e2);
        helper.clickOnElement(e2);
    }

    @AfterMethod
    public void end() {
        driver.quit();
    }
    @AfterTest
    public void saveReport() {
        report.flush();
    }
}
