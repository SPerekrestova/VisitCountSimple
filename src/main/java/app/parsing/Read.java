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

    public static HashMap readGroupFromExcel(String file) throws IOException {
        HSSFWorkbook groupExcel = new HSSFWorkbook(new FileInputStream(file));
        HSSFSheet groupExcelSheet = groupExcel.getSheetAt(0);
        HashMap<String, HashMap<String, String>> resultMap = new HashMap<>();
        HashMap<String, String> valuesMap = new HashMap<>();
        String card = "";
        String name = "";
        String group = "";
        for (Row student : groupExcelSheet) {
            for (Cell param : student) {
                if (0 == student.getRowNum()){
                    group = student.getCell(1).getStringCellValue();
                } else {
                    switch (param.getCellType()) {
                        case HSSFCell.CELL_TYPE_NUMERIC:
                            card = String.valueOf((int) param.getNumericCellValue());
                            break;
                        case HSSFCell.CELL_TYPE_STRING:
                            name = param.getStringCellValue();
                            break;
                        default:
                    }
                    valuesMap.put(card, name);
                }

            }
        }
        resultMap.put(group, valuesMap);
        groupExcel.close();
        return resultMap;
    }
}
