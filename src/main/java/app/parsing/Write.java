package app.parsing;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;

public class Write {
    @SuppressWarnings("deprecation")
    public static void writeIntoExcel(HashMap groupMap, Map<Integer, SortedSet<String>> scheduleMap) throws FileNotFoundException, IOException, FileNotFoundException {
//        Workbook book = new HSSFWorkbook();
//        Sheet sheet = book.createSheet("Birthdays");
//
//        // Нумерация начинается с нуля
//        Row row = sheet.createRow(0);
//
//        // Мы запишем имя и дату в два столбца
//        // имя будет String, а дата рождения --- Date,
//        // формата dd.mm.yyyy
//        Cell name = row.createCell(0);
//        name.setCellValue("John");
//
//        Cell birthdate = row.createCell(1);
//
//        DataFormat format = book.createDataFormat();
//        CellStyle dateStyle = book.createCellStyle();
//        dateStyle.setDataFormat(format.getFormat("dd.mm.yyyy"));
//        birthdate.setCellStyle(dateStyle);
//
//
//        // Нумерация лет начинается с 1900-го
//        birthdate.setCellValue(new Date(110, 10, 10));
//
//        // Меняем размер столбца
//        sheet.autoSizeColumn(1);
//
//        // Записываем всё в файл
//        try {
//            book.write(new FileOutputStream(file));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        book.close();
    }
}
