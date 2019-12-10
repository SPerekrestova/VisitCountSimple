package app.parsing;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

public class Read {

    public static HashMap readFromExcel(String file) throws IOException {
        HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(file));
        HSSFSheet myExcelSheet = myExcelBook.getSheetAt(0);
        HashMap<String, HashMap<String, String>> resultMap = new HashMap<>();
        HashMap<String, String> valuesMap = new HashMap<>();
        String card = "";
        String name = "";
        for (Row myrow : myExcelSheet) {
            for (Cell mycell : myrow) {
                switch (mycell.getCellType()) {
                    case HSSFCell.CELL_TYPE_NUMERIC:
                        card = String.valueOf((int)mycell.getNumericCellValue());
                        break;
                    case HSSFCell.CELL_TYPE_STRING:
                        name = mycell.getStringCellValue();
                        break;
                    default:
                }
                valuesMap.put(card, name);
            }
        }
        resultMap.put("group", valuesMap);
        myExcelBook.close();
        return resultMap;
    }
}
