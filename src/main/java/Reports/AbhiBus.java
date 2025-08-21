package Reports;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;

public class AbhiBus {
    WebDriver driver;
    @BeforeMethod
    public void openUrl() throws InterruptedException{
        driver = new ChromeDriver();
        driver.get("https://www.abhibus.com/");
        Thread.sleep(2000);
    }

    @Test
    public void T1() throws InterruptedException {
        driver.findElement(By.xpath("//span[text()='Trains']")).click();
        Thread.sleep(2000);

        String actual = driver.getCurrentUrl();
        Assert.assertTrue(actual.contains("trains"));

        driver.findElement(By.xpath("//div[@class='ctkt-logo']")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("login-link")).click();
        String acutal = driver.findElement(By.xpath("//h4")).getText();

        Assert.assertTrue(actual.contains("Login to Abhibus"));

//        try {
//            TakesScreenshot ts = (TakesScreenshot) driver;
//
//            File dest = new File("");
//            File src = ts.getScreenshotAs(OutputType.FILE);
//
//            FileUtils.copyFile(src, dest);
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
    }
    private String getText() {
        // get data from excel;
        return "Banglore";
    }

    @Test
    public void selectDropdown() {

    }
}
