package FileAccess;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DataAcces {

    WebDriver driver;

    public String[] propertiesData() {
        Properties prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/main/java/FileAccess/testing.properties");

            prop.load(fis);
            String name = prop.getProperty("user");
            String pass = prop.getProperty("pass");
            return new String[]{name, pass};
        }catch (FileNotFoundException e) {
            System.out.println("File Cannot be found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Map<String, String> excelData() {
        try {
            FileInputStream fis = new FileInputStream("src/main/java/FileAccess/Data.xlsx");
            Workbook wb = new XSSFWorkbook(fis);
            Sheet sheet = wb.getSheetAt(0);
            Map<String, String> result = new HashMap<>();
            int totalRows = sheet.getLastRowNum();
            for(int i=1; i<=totalRows; i++) {
                String name = sheet.getRow(i).getCell(0).getStringCellValue();
                String pass = sheet.getRow(i).getCell(1).getStringCellValue();
                result.put(name, pass);
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeMethod
    public void get_url() {
        driver = new ChromeDriver();
        driver.get("https://www.adactinhotelapp.com");
    }

    public void sendValues(String userName, String passWord) throws InterruptedException{
        driver.findElement(By.name("username")).sendKeys(userName);
        Thread.sleep(3000);
        driver.findElement(By.id("password")).sendKeys(passWord);
        Thread.sleep(3000);
        driver.findElement(By.id("login")).click();
    }

    @Test(priority = 2)
    public void from_properties() throws InterruptedException{
        String[] credentials = propertiesData();
        sendValues(credentials[0], credentials[1]);
    }

    @Test(priority = 1)
    public void form_excel() throws InterruptedException {
        Map<String, String> credentials = excelData();

        for(Map.Entry<String, String> entry : credentials.entrySet()) {
            sendValues(entry.getKey(), entry.getValue());
        }
    }

    @AfterMethod
    public void close_browser() {
        driver.quit();
    }
//    public static void main(String[] args) {
//        DataAcces da = new DataAcces();
//        da.propertiesData();
//        da.excelData();
//    }
}
