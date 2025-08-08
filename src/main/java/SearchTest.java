import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class SearchTest {
    WebDriver driver;

    SearchTest() {
        driver = new ChromeDriver();
    }

    public void alertTest() throws InterruptedException {
        driver.get("https://demo.automationtesting.in/Alerts.html");
        Thread.sleep(2000);

        // ✅ 1. Click on "Alert with OK"
        driver.findElement(By.xpath("//a[normalize-space(text())='Alert with OK']")).click();
        Thread.sleep(1000);

        // ✅ Click the button to trigger simple alert
        driver.findElement(By.xpath("//button[contains(@class, 'btn btn-danger')]")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();

        // ✅ 2. Click on "Alert with OK & Cancel"
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[normalize-space(text())='Alert with OK & Cancel']")).click();
        Thread.sleep(1000);

        // ✅ Click the button to trigger confirm alert
        driver.findElement(By.xpath("//button[contains(@class, 'btn btn-primary')]")).click();
        Alert confirmAlert = driver.switchTo().alert();
        confirmAlert.dismiss(); // or confirmAlert.accept();

        Thread.sleep(2000); // optional for seeing result
    }
    public void amazonTest() throws InterruptedException{

        driver.get("https://www.amazon.in");
        Thread.sleep(4000);
        WebElement e = driver.findElement(By.xpath("//div[@id='nav-link-accountList']"));
        Actions a = new Actions(driver);
        a.moveToElement(e).perform();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//span[text()='Sign in']")).click();
        Thread.sleep(4000);
        driver.navigate().back();
        Thread.sleep(4000);
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Vivo v25");
        Thread.sleep(4000);
        driver.findElement(By.id("nav-search-submit-button")).click();
        Thread.sleep(4000);
        List<WebElement> items = driver.findElements(By.xpath("//img[@class='s-image']"));
        // "//button[text()='login']
        items.getFirst().click();
        Thread.sleep(4000);
        driver.findElement(By.id("landingImage")).click();
    }
    public void agniTest() {

    }

    public static void main(String[] args) throws InterruptedException{
        System.setProperty("webdriver.chrome.driver", "C:/Drivers/chromedriver-win64/chromedriver.exe");

        SearchTest st = new SearchTest();

        try {
            Thread.sleep(2000);
            st.agniTest();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
