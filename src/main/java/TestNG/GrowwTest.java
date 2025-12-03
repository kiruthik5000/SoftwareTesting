    package TestNG;

    import org.openqa.selenium.By;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.WebElement;
    import org.openqa.selenium.chrome.ChromeDriver;
    import org.openqa.selenium.support.ui.Select;
    import org.testng.annotations.AfterTest;
    import org.testng.annotations.BeforeTest;
    import org.testng.annotations.Test;
    import java.util.List;

    public class GrowwTest {

        // webdriver
        WebDriver driver;

        @BeforeTest
        public void setup() throws InterruptedException{
            driver = new ChromeDriver();
            Thread.sleep(2000);
            driver.get("https://www.groww.in/.");
        }

        @Test
        public void T1() throws InterruptedException{

            // go to calculator
            driver.findElement(By.xpath("//div/span[text()='Calculators']")).click();
            Thread.sleep(4000);

            // click the home loan calculator
            driver.findElement(By.xpath("//a[contains(text(), 'Home')]")).click();
            Thread.sleep(4000);

            // enter the amount
            driver.findElement(By.id("LOAN_AMOUNT")).clear();
            driver.findElement(By.id("LOAN_AMOUNT")).sendKeys("2300000");
            Thread.sleep(1000);
            driver.findElement(By.id("RATE_OF_INTEREST")).clear();
            driver.findElement(By.id("RATE_OF_INTEREST")).sendKeys("8");
            Thread.sleep(1000);
            driver.findElement(By.id("LOAN_TENURE")).clear();
            driver.findElement(By.id("LOAN_TENURE")).sendKeys("25");

            // get result value
            String[] result = new String[4];

            List<WebElement> elements = driver.findElements(By.xpath("//td[contains(text(),'₹')]/span"));

            // store the results
            int i = 0;
            for(WebElement we : elements) {
                System.out.println(we.getText());
            }
        }
        public void select(WebElement e, String text) {
            try {
                Select select = new Select(e);
                select.selectByVisibleText(text);

            }catch (Exception g) {
                g.printStackTrace();
            }
        }
        @AfterTest
        public void quit(){
            driver.quit();
        }
    }
