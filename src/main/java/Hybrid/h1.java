package Hybrid;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

public class h1 {

    // driver
    WebDriver driver = new ChromeDriver();

    // report
    ExtentSparkReporter spark;
    ExtentReports report;
    ExtentTest test;

    // log4j
    private static final Logger log = LogManager.getLogger(h1.class);

    @BeforeTest
    public void report() {
        spark = new ExtentSparkReporter("./reports/report.html");
        report = new ExtentReports();
        report.attachReporter(spark);
    }

    @BeforeMethod
    public void url() {
        driver.get("url");
    }

    @Test
    public void t1() {
        test = report.createTest("Test No 1");
        getScreenshot();
        test.addScreenCaptureFromPath("./screenshots/image.png");
    }

    // screenshot
    public void getScreenshot() {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File srcDir = new File("./screenshot");
            if(!srcDir.exists()) {
                srcDir.mkdir();
            }
            File dest = new File(srcDir, "image.png");
            File screenshot = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcDir, dest);
            Files.copy(screenshot, dest);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    // webdriver wait element
    public void wait(WebElement element) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(element));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    // hover
    public void hover(WebElement element) {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    // javascriptscroll
    public void scroll(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView()", element);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    // switch new window
    public void switchNewWindow() {
        try {
            Set<String> windows = driver.getWindowHandles();
            for(String window : windows) {
                if(!window.isEmpty()) {
                    driver.switchTo().window(window);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    // excel reader
    private void readFromexcel() {
        Workbook excel;
        try {
            FileInputStream fis = new FileInputStream("./testdata/data.xlsx");
            excel = new XSSFWorkbook(fis);

            // define
            Sheet sheet = excel.getSheetAt(0);
            Row r = sheet.getRow(0);
            Cell c = r.getCell(0);
            System.out.println(c.getStringCellValue());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    // get values from properties
    private void getFromProperties() {
        Properties prop;
        try{
            FileInputStream fis = new FileInputStream("./config/browser.properties");
            prop = new Properties();
            prop.load(fis);
            prop.getProperty("url");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
