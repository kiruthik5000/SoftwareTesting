package TestNG;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;

public class Magneto {
    public WebDriver driver;

    @BeforeMethod
    public void openUrl() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://magento.softwaretestingboard.com");
        Thread.sleep(2000);
    }

    @Test
    public void T1() throws InterruptedException {
        driver.findElement(By.name("q")).sendKeys("Shoe", Keys.ENTER);
        WebElement element = driver.findElement(By.xpath("//span[@class='base']"));

        Assert.assertEquals(element.getText().trim(), "Search results for: 'Shoe'");

        Thread.sleep(2000);
        TakesScreenshot ts = (TakesScreenshot) driver;

        try {
            // dest file
            File dest = new File("src/main/java/TestNG/screenshots/screenshot.png");

            // src file
            File src = ts.getScreenshotAs(OutputType.FILE);

            FileUtils.copyFile(src, dest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void T2() throws InterruptedException {
        try {
            FileInputStream fis = new FileInputStream("src/main/java/TestNG/data.xlsx");
            Workbook wb = new XSSFWorkbook(fis);
            Sheet sheet = wb.getSheetAt(0);
            Row row = sheet.getRow(0);
            Cell cell = row.getCell(0);

            String name = cell.getStringCellValue();

            driver.findElement(By.name("q")).sendKeys(name, Keys.ENTER);
            WebElement element = driver.findElement(By.xpath("//span[@class='base']"));
            Assert.assertEquals(element.getText().trim(), "Search results for: 'Hoodie'");

            driver.findElement(By.name("q")).sendKeys(name, Keys.ENTER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Thread.sleep(2000);
        TakesScreenshot ts = (TakesScreenshot) driver;

        try {
            // dest file
            File dest = new File("src/main/java/TestNG/screenshots/image2.png");

            // src file
            File src = ts.getScreenshotAs(OutputType.FILE);

            FileUtils.copyFile(src, dest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void T3() throws InterruptedException {
        driver.findElement(By.xpath("(//img[contains(@class, 'product-image')])[1]")).click();

        Thread.sleep(2000);

        WebElement qty = driver.findElement(By.name("qty"));
        qty.sendKeys("1", Keys.ENTER);

        Thread.sleep(2000);
        TakesScreenshot ts = (TakesScreenshot) driver;

        try {
            // dest file
            File dest = new File("src/main/java/TestNG/screenshots/image2.png");

            // src file
            File src = ts.getScreenshotAs(OutputType.FILE);

            FileUtils.copyFile(src, dest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @AfterMethod
    public void quit(){
        driver.quit();
    }
}