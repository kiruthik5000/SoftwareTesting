package Hybrid;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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
    private static final Logger log = Logger.getLogger(h1.class);

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
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            File folder = new File("./screenshot");
            folder.mkdir(); // creates only if not exists

            File dest = new File(folder, "image.png");
            FileUtils.copyFile(src, dest);

        } catch (Exception e) {
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

    // alert
    Alert alert = driver.switchTo().alert();
    alert.accept();
    alert.dismiss();
    alert.sendKeys("good");

    // javascriptscroll
    public void scroll(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView()", element);

            // js click
            js.executeScript("arguments[0].click()", element);
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

    // reload window
    void f1() {
        driver.navigate().refresh();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    // select
    public void select(WebElement e, String text) {
        try {
            Select select = new Select(e);
            select.selectByVisibleText(text);

        }catch (Exception g) {
            g.printStackTrace();
        }
    }
}
