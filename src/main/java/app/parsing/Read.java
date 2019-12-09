package app.parsing;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

public class Read {

    public static void readFromExcel(String file) throws IOException {
        HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(file));
        HSSFSheet myExcelSheet = myExcelBook.getSheet("Birthdays");
        HSSFRow row = myExcelSheet.getRow(0);

        if(row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING){
            String card = row.getCell(0).getStringCellValue();
            System.out.println("card : " + card);
        }

        if(row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
            Date name = row.getCell(1).getDateCellValue();
            System.out.println("name :" + name);
        }
        myExcelBook.close();
    }
}
