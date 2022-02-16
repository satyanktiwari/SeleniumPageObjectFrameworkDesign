package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

    private static String TEST_DATA_SHEET_PATH = "src/test/java/com/qa/opencart/resources/testdata/DemoCartTestData.xlsx";
    private static Workbook book;
    private static Sheet sheet;

    /**
     * This method is to get data from excel file.
     * @param sheetName
     * @return 
     */
    public static Object[][] getTestData(String sheetName){
        Object data[][]=null;
        try {
            FileInputStream fis = new FileInputStream(TEST_DATA_SHEET_PATH);
            book= WorkbookFactory.create(fis);
            sheet = book.getSheet(sheetName);

            data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

            for (int row=0; row<sheet.getLastRowNum();row++){
                for(int column=0;column<sheet.getRow(0).getLastCellNum();column++){
                    data[row][column] = sheet.getRow(row+1).getCell(column).toString();

                }
            }

        } catch (FileNotFoundException e) {
            
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            
            e.printStackTrace();
        } catch (IOException e) {
            
            e.printStackTrace();
        }
        return data;

    }
    
}
