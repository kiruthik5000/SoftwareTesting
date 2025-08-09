package FileAccess;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DataAccess {

    public void propertiesData() {
        Properties prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/main/java/FileAccess/testing.properties");

            prop.load(fis);
            String name = prop.getProperty("user");
            String pass = prop.getProperty("pass");
            System.out.println(name + " " + pass);
        }catch (FileNotFoundException e) {
            System.out.println("File Cannot be found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void excelData() {
        try {
            FileInputStream fis = new FileInputStream("src/main/java/FileAccess/Data.xlsx");
            if(fis == null) {
                System.out.println("File Not found");
                return;
            }
            Workbook wb = new XSSFWorkbook(fis);
            Sheet sheet = wb.getSheetAt(0);
            for(Row r : sheet) {
                String name = String.valueOf(r.getCell(0));
                String pass = String.valueOf(r.getCell(1));
                System.out.println(name +" "+pass);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        DataAccess da = new DataAccess();
        da.propertiesData();
        da.excelData();
    }
}
