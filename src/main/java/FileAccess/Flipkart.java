package FileAccess;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import java.io.*;
import java.time.Duration;
import java.util.*;

public class Flipkart {
    public WebDriver driver;
    public static int IMPLICIT_WAIT_TIME =10;

    @BeforeMethod
    public void openBrowser() throws Throwable {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_TIME));
    }

    private String getlink() {
        Properties prop = new Properties();

        try {
            FileInputStream fis = new FileInputStream("src/main/java/FileAccess/testing.properties");
            prop.load(fis);
            return prop.getProperty("link");
        }catch(Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Test
    public void T1() throws InterruptedException{
        driver.get(getlink());
        Thread.sleep(2000);

        List<String> inputs = getInputs();

        int n = inputs.size();
        for(int i=0; i<n; i++) {
            driver.findElement(By.name("q")).sendKeys(inputs.get(i), Keys.ENTER);
            Thread.sleep(4000);
        }

    }

    private List<String> getInputs(){
        Workbook wb;
        try {
            FileInputStream fis = new FileInputStream("src/main/java/FileAccess/Data.xlsx");
            wb = new XSSFWorkbook(fis);

            Sheet sheet = wb.getSheetAt(0);


            int rows = sheet.getLastRowNum();


            List<String> names = new ArrayList<>();
            for(int i=0; i<=rows; i++) {
                Row r = sheet.getRow(i);
                Cell c = r.getCell(0);
                String val = c.getStringCellValue();
                names.add(val);
            }
            System.out.println(names);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        new Flipkart().getInputs();
    }
}