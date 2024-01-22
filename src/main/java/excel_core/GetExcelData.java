package excel_core;


import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GetExcelData {


    public Map<String,String> getRowDataByID (String fileName, String sheetName, String ID, String dataNum, boolean suffix) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheet(sheetName);

        int lastColumnNum = sheet.getRow(1).getLastCellNum();
        int lastRowNum = sheet.getLastRowNum();

        Map<String, String> data = new HashMap<>();

        int idMatchesNum = 0;


        for(int j = 0; j <= lastRowNum;j++){
            if(sheet.getRow(j).getCell(0).getStringCellValue().equalsIgnoreCase(ID)) {
                idMatchesNum+=1;
                if(idMatchesNum>1){
                    break;
                }
                for (int i = 0; i<lastColumnNum;i++){
                    String key, value;

                    try {
                        key = sheet.getRow(1).getCell(i).getStringCellValue().trim();
                    } catch (Exception e){
                        key = String.valueOf(sheet.getRow(1).getCell(i).getNumericCellValue());
                    }
                    try {
                        value = sheet.getRow(j).getCell(i).getStringCellValue().trim();
                    } catch (Exception e){
                        value = String.valueOf(sheet.getRow(j).getCell(i).getNumericCellValue());
                    }
                    if(suffix){
                        data.put(key+"_"+dataNum, value);
                    }else{
                        data.put(key, value);
                    }
//                    if (dataNum.equalsIgnoreCase("1")){
//                        data.put(key, value);
//                    }else {
//                        data.put(key+"_"+dataNum, value);
//                    }

                }

            }

        }
        return data;
    }

}
