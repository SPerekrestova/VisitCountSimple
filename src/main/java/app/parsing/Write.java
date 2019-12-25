package app.parsing;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class Write {
    @SuppressWarnings("deprecation")
    public static void writeIntoExcel(LinkedHashMap<Integer, String> groupMap, Map<Integer, SortedSet<String>> scheduleMap) throws IOException {
        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet("Scheduler");
        File file = new File("C:\\Users\\Svetlana\\Desktop\\result.xls");


        //Создание подписей
        Row groupNameRow = sheet.createRow(0);
        Cell groupNameCell = groupNameRow.createCell(2);
        groupNameCell.setCellValue("гр. ИСТ-722");

        Row lessonNumRow = sheet.createRow(1);
        Cell lessonNumCell = lessonNumRow.createCell(2);
        lessonNumCell.setCellValue("Номер зантия");

        Row lessonDayRow = sheet.createRow(2);
        Cell lessonDayCell = lessonDayRow.createCell(2);
        lessonDayCell.setCellValue("День");

        Row lessonTimeRow = sheet.createRow(3);
        Cell lessonTimeCell = lessonTimeRow.createCell(2);
        lessonTimeCell.setCellValue("Время");

        Row studentTextRow = sheet.createRow(4);
        Cell studentNumTextCell = studentTextRow.createCell(0);
        studentNumTextCell.setCellValue("№ п/п");

        Cell studentCardTextCell = studentTextRow.createCell(1);
        studentCardTextCell.setCellValue("№ карты");

        Cell studentNameTextCell = studentTextRow.createCell(2);
        studentNameTextCell.setCellValue("ФИО");

        // заполнение первых двух столбцов с картой и студентом
        int countStudentRow = 5;
        int countNumberCell = 0;
        int numCount = 1;

        for (int key : groupMap.keySet()) {
            int countStudentCell = 1;
            Row studentRow = sheet.createRow(countStudentRow);
            Cell num = studentRow.createCell(countNumberCell);
            num.setCellValue(numCount);
            Cell card = studentRow.createCell(countStudentCell);
            countStudentCell++;
            Cell student = studentRow.createCell(countStudentCell);
            card.setCellValue(key);
            student.setCellValue(groupMap.get(key));
            countStudentRow++;
            numCount++;
        }
        //заполнение столбцов с занятиями
        int countScheduleCell = 3;
        int countLesson = 1;
        for (int key : scheduleMap.keySet()) {
            TreeSet<String> timeSet = new TreeSet(scheduleMap.get(key));
            for (String value : timeSet) {
                Cell num = lessonNumRow.createCell(countScheduleCell);
                num.setCellValue(countLesson);
                Cell day = lessonDayRow.createCell(countScheduleCell);
                day.setCellValue(key+".09");
                Cell time = lessonTimeRow.createCell(countScheduleCell);
                time.setCellValue(value);
                countScheduleCell++;
                countLesson++;
            }
        }
        // Меняем размер столбца
        sheet.autoSizeColumn(2, false);
        // Записываем всё в файл
        try {
            book.write(new FileOutputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        book.close();
    }
}
