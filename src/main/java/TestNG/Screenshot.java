package TestNG;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class Screenshot {

    static WebDriver driver;

    public static void main(String[] args) {
        try {
            driver = new ChromeDriver();

            driver.manage().window().maximize();

            driver.get("https://www.google.com");

            Thread.sleep(4000);
            driver.findElement(By.name("q")).sendKeys("ViratKohli", Keys.ENTER);
            Thread.sleep(4000);
            // cast driver to a screenshot object
            TakesScreenshot ts = (TakesScreenshot) driver;

            // take screenshot
            File src = ts.getScreenshotAs(OutputType.FILE);

            File pic = new File("./src/main/java/TestNG/screenshots/image.png");

            FileUtils.copyFile(src, pic);

            System.out.println("Screenshot saved in file"+pic.getAbsolutePath());
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            driver.quit();
        }
    }
}
