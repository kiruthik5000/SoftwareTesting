package Reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Report {
    WebDriver driver;
    ExtentSparkReporter spark;
    ExtentReports reports;
    ExtentTest test;

    @BeforeTest
    public void intialize() {
        driver = new ChromeDriver();
        spark = new ExtentSparkReporter("Report.html");
        reports = new ExtentReports();
        reports.attachReporter(spark);
    }

    @BeforeMethod
    public void openUrl() {
        driver.get("https://opensource-demo.orangehrmlive.com");
    }

    @Test
    public void t1() throws InterruptedException{
        // Start the test case
        test = reports.createTest("Login to OrangeHRM");

        try {
            // Get credentials from Excel
            test.log(Status.INFO, "Fetching login credentials from Excel file.");
            String[] credentials = getCredentials();

            // Find and interact with the username field
            test.log(Status.INFO, "Entering username: " + credentials[0]);
            WebElement usernameField = driver.findElement(By.name("username"));
            usernameField.sendKeys(credentials[0]);

            // Find and interact with the password field
            test.log(Status.INFO, "Entering password: " + credentials[1]);
            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.sendKeys(credentials[1]);

            // Simulate clicking login button (optional)
            WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
            loginButton.click();
            try {
                TakesScreenshot ts = (TakesScreenshot) driver;
            }catch (Exception e) {
                e.printStackTrace();
            }
            test.addScreenCaptureFromPath("./screenshots/image.png");
            test.log(Status.PASS, "Successfully logged in with provided credentials.");
        } catch (Exception e) {
            // Log any failures
            test.log(Status.FAIL, "Test failed due to an exception: " + e.getMessage());
        }
    }


    @AfterMethod
    public void close() {
        driver.quit();
    }
    @AfterTest
    public void save() {
        reports.flush();
    }

    private String[] getCredentials(){
        Workbook excel;
        try {
            FileInputStream fis = new FileInputStream("src/main/java/FileAccess/Data.xlsx");
            excel = new XSSFWorkbook(fis);
            Sheet sheet = excel.getSheetAt(0);
            Row r = sheet.getRow(1);
            return new String[]{r.getCell(0).getStringCellValue(), r.getCell(1).getStringCellValue()};
        }catch (FileNotFoundException e) {
            System.out.println("Error: "+e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}