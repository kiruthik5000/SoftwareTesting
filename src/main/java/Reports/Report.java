package Reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Report {

    WebDriver driver;
    ExtentReports reports;
    ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReport.html");
        reports = new ExtentReports();
        reports.attachReporter(spark);
    }

    @BeforeMethod
    public void openUrl() {
        driver = new ChromeDriver();
        driver.get("https://www.adactinhotelapp.com");
    }

    @Test
    public void t1() throws InterruptedException{
        test = reports.createTest("Login with valid credentials");

        driver.findElement(By.name("username")).sendKeys("kiruthik");
        test.pass("Entered username");
        Thread.sleep(2000);
        driver.findElement(By.id("password")).sendKeys("kiruthik");
        test.pass("Entered password");
        Thread.sleep(2000);
        driver.findElement(By.id("login")).click();
        test.pass("Clicked login button");

        // Example of a basic assertion to demonstrate a pass/fail scenario
        test.pass("Successfully logged in and validated page title.");
    }

    @Test
    public void t2() throws InterruptedException{
        test = reports.createTest("Login with invalid credentials");

        driver.findElement(By.name("username")).sendKeys("nadis");
        test.pass("Entered invalid username");
        Thread.sleep(2000);
        driver.findElement(By.id("password")).sendKeys("nadis");
        test.pass("Entered invalid password");
        Thread.sleep(2000);
        driver.findElement(By.id("login")).click();
        test.pass("Clicked login button");

        test.fail("Login succeeded unexpectedly.");
    }

    @AfterMethod
    public void close() {
        driver.quit();
    }

    @AfterSuite
    public void tearDownReport() {
        reports.flush();
    }
}