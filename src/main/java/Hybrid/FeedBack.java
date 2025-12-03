package Hybrid;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.Scanner;

public class FeedBack {

    WebDriver driver;

    @BeforeTest
    public void url() {
        ChromeOptions options = new ChromeOptions();

        // ✅ Correct user data directory (existing)
        options.addArguments("user-data-dir=C:\\Users\\Kiruthik\\AppData\\Local\\Google\\Chrome\\User Data");

        // ✅ Correct profile name — check it exists under that folder
        options.addArguments("profile-directory=SeleniumProfile");

        // ✅ Important startup flags
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--remote-debugging-port=9222");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-notifications");
        options.addArguments("--no-first-run");
        options.addArguments("--no-service-autorun");
        options.addArguments("--password-store=basic");

        // ✅ Initialize driver
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://forms.gle/4tzY4pULqmuGtBZc7");
    }

    @Test
    public void T1() throws InterruptedException {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the topics covered:");
        String topics = s.nextLine();
        System.out.println("Enter no of questions completed today:");
        String no_of_questions = s.nextLine();

        driver.findElement(By.id("i5")).click();
        driver.findElement(By.xpath("//input[@type='text' and @aria-labelledby='i8 i11']")).sendKeys("Kiruthik V");
        driver.findElement(By.xpath("//input[@type='text' and @aria-labelledby='i13 i16']")).sendKeys("727723EUIT106");
        driver.findElement(By.xpath("//input[@type='text' and @aria-labelledby='i18 i21']")).sendKeys("727723euit106@skcet.ac.in");
        driver.findElement(By.xpath("//span[text()='Batch 3']")).click();
        driver.findElement(By.xpath("//span[text()='Shourav']")).click();
        driver.findElement(By.xpath("(//div[text()='2'])[1]")).click();
        driver.findElement(By.xpath("(//div[text()='3'])[2]")).click();
        driver.findElement(By.xpath("//input[@type='text' and @aria-labelledby='i73 i76']")).sendKeys(topics);
        driver.findElement(By.xpath("//input[@type='text' and @aria-labelledby='i78 i81']")).sendKeys(no_of_questions);
        driver.findElement(By.xpath("(//div[text()='3'])[3]")).click();
    }

    @AfterTest
    public void close() {
        driver.quit();
    }
}
