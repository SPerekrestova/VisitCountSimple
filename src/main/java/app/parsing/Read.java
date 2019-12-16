package app.parsing;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellUtil;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByKey;
import static java.util.stream.Collectors.toMap;

public class Read {

    public static HashMap<String, HashMap<String, String>> readGroupFromExcel(String file) throws IOException {
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

    public static Object readScheduleFromExcel(String file) throws IOException {
        HSSFWorkbook scheduleExcel = new HSSFWorkbook(new FileInputStream(file));
        HSSFSheet scheduleExcelSheet = scheduleExcel.getSheetAt(0);
        HashMap<String, Map<String, SortedSet<String>>> resultMap = new HashMap<>();
        HashMap<Integer, Map<String, SortedSet<String>>> valuesMap = new HashMap<>();
        ArrayList<String> week_number = new ArrayList<>();
       // ArrayList<String> days = new ArrayList<String>();
        //ArrayList<String> time = new ArrayList<>();
        SortedSet<String> time = new TreeSet<String>();
        Map<String, SortedSet<String>> lessons = new LinkedHashMap<>();  // ordered
        Map<String, SortedSet<String>> sortedLessons = new LinkedHashMap<>();  // sorted
        String regexDate = "([1-9]|[12]\\d|3[01])";
        String group = "ИСТ-722";

        DataFormatter dataFormatter = new DataFormatter();
        Row row;
        Cell cell;

        for (int columnIndex = 0; columnIndex < 7; columnIndex++) {
            int timeIndex = 0;
            int currentDay = 0;
            for (int rowIndex = 0; rowIndex < 113; rowIndex++) {
                row = CellUtil.getRow(rowIndex, scheduleExcelSheet);
                cell = CellUtil.getCell(row, columnIndex);
                if (columnIndex == 0 &&  dataFormatter.formatCellValue(cell).matches(regexDate)) {
                    week_number.add(dataFormatter.formatCellValue(cell));
                } else if (columnIndex != 0 && cell != null) {
                    if (dataFormatter.formatCellValue(cell).matches(regexDate) && (Integer.parseInt(dataFormatter.formatCellValue(cell)) > currentDay)) {
                        currentDay = Integer.parseInt(dataFormatter.formatCellValue(cell));
                        time = new TreeSet<String>();
                    }
                    if (dataFormatter.formatCellValue(cell).contains("ИСТ-722")) {
                        timeIndex = rowIndex +1;
                        //days.add(String.valueOf(currentDay));
                        //String time = dataFormatter.formatCellValue(CellUtil.getCell(scheduleExcelSheet.getRow(timeIndex), columnIndex));
                        time.add(dataFormatter.formatCellValue(CellUtil.getCell(scheduleExcelSheet.getRow(timeIndex), columnIndex)));
                        lessons.put(String.valueOf(currentDay), time);
                    }
                }
            }
        }

        scheduleExcel.close();
        return resultMap;
    }
}
