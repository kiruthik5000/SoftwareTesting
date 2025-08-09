package TestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Set;

public class SpiceJet {

    private WebDriver driver;

    @BeforeMethod
    public void openLink() {
        System.setProperty("webdriver.chrome.driver", "C:/Drivers/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();

        driver.get("https://www.spicejet.com");
    }

    @Test
    public void Login() throws InterruptedException {
        String parentHandel = driver.getWindowHandle();
        String childHandel = null;
        System.out.println(driver.getTitle());
        Thread.sleep(4000);
        // step 1 -> signup
        driver.findElement(By.xpath("//div[contains(text(), 'Signup')]")).click();

        // handeling the window
        Set<String> handling = driver.getWindowHandles();

        for(String cur : handling) {
            if(!cur.equals(parentHandel)) {
                childHandel = cur;
            }
        }
        Thread.sleep(4000);
        // switch to child handle
        driver.switchTo().window(childHandel);
        String childTitle = driver.getTitle();
        Thread.sleep(4000);
        System.out.println(childTitle);
    }

    @AfterMethod
    public void closeAll() {
        driver.quit();
    }
}