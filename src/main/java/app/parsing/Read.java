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
import java.util.Iterator;

public class Read {

    public static void readFromExcel(String file) throws IOException {
        HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(file));
        HSSFSheet myExcelSheet = myExcelBook.getSheetAt(0);

        for (Row myrow : myExcelSheet) {
            for (Cell mycell : myrow) {
                switch (mycell.getCellType()) {
                    case HSSFCell.CELL_TYPE_NUMERIC:
                        String card = String.valueOf((int)mycell.getNumericCellValue());
                        System.out.println("card : " + card);
                        break;
                    case HSSFCell.CELL_TYPE_STRING:
                        String name = mycell.getStringCellValue();
                        System.out.println("name :" + name);
                        break;
                    default:
                }
            }
        }
        myExcelBook.close();
    }
}
