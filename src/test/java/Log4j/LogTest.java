package Log4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.Duration;

public class LogTest {

    WebDriver driver;
    private static final Logger log = LogManager.getLogger(LogTest.class);

    @BeforeTest
    public void setup() {
        log.info("Launching Chrome browser...");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("https://adactinhotelapp.com/");
        log.info("Opened Adactin Hotel App");
    }

    @Test
    public void simpleTest() {
        String username = "admin";
        String pass = "admin@123";

        log.info("Proceeding with username: {} and password: {}", username, pass);

        try {
            driver.findElement(By.name("username")).sendKeys(username);
            driver.findElement(By.name("password")).sendKeys(pass);
            log.info("Entered the username and password");
        } catch (Exception e) {
            log.error("An error occurred", e);
        }

        log.info("Test completed");
    }

    @AfterTest
    public void exit() {
        log.info("Closing the browser...");
        driver.quit();
    }
}
