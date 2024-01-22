package tests;

import excel_core.GetExcelData;
import org.openqa.selenium.WebDriver;
import selenium_core.DriverManager;
import selenium_core.DriverManagerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BaseTest {


    WebDriver driver;
    DriverManager driverManager;

    Map<String, String> data;





    public void baseSetUP(String browser, String version, int wait){
        driverManager = DriverManagerFactory.getDriverManager(browser);
        driver = driverManager.getWebDriver(version);
        driver.get("https://www.booking.com/");
        driver.manage().timeouts().implicitlyWait(wait, TimeUnit.SECONDS);
    }

    public void baseTearDown(){
        driverManager.quit();

    }

    public void loadTestData(String fileName,String sheet, String ID, String dataNum) throws IOException {
        data = new GetExcelData().getRowDataByID(fileName, sheet, ID,dataNum,false);
    }
    public void mergeTestData(String fileName,String sheet, String key) throws IOException {
        String [] object = data.get(key).split(",");
        Map<String, String> dataToBeMerged = new HashMap<>();

        for (int i=0; i<object.length;i++){
            dataToBeMerged.putAll(new GetExcelData().getRowDataByID(fileName,sheet,object[i],String.valueOf(i+1),true));
        }
        data.putAll(dataToBeMerged);

    }

    public void mergeTestData(String fileName,String sheet, String key, int index) throws IOException {
        String [] object = data.get(key).split(",");
        Map<String, String> dataToBeMerged = new HashMap<>();

        for (int i=0; i<object.length;i++){
            dataToBeMerged.putAll(new GetExcelData().getRowDataByID(fileName,sheet,object[i],index+"_"+(i+1),true));
        }
        data.putAll(dataToBeMerged);

    }

}

