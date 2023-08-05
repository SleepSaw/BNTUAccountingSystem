package org.bntu.accounting.bntuaccountingsystem.file.excel.creators;
import org.apache.poi.ss.usermodel.*;
        import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
        import java.nio.file.Files;
        import java.nio.file.Paths;

public class ExcelFileHeaderCreator extends ExcelFileCreator {
    private final String jsonFilePath = "C:\\Users\\danii\\IdeaProjects\\BNTUAccountingSystem\\src\\main\\resources\\files\\excel_header.json";
    private String fileName;
    private Workbook workbook;

    public static void writeDataToExcel(String fileName, JSONObject jsonData, Workbook workbook) {
            Sheet sheet = workbook.createSheet("Лист");

            CellStyle headerStyle = createCellStyle(workbook,createFont(workbook,"Times New Roman",20,false));
            CellStyle headerStyleBold = createCellStyle(workbook,createFont(workbook,"Times New Roman",20,true));
            CellStyle headerStyleBoldRight = createCellStyle(workbook,createFont(workbook,"Times New Roman",24,true));

            Row row0 = sheet.createRow(0);
            Row row1 = sheet.createRow(1);
            Row row2 = sheet.createRow(2);
            Row row3 = sheet.createRow(3);
            Row row5 = sheet.createRow(5);
            Row row6 = sheet.createRow(6);


            writeDataToCellFromJSON(row0,0,"governing_institution_name",jsonData,headerStyle);
            writeDataToCellFromJSON(row1,0,"educational_institution_name",jsonData,headerStyle);
            writeDataToCellFromJSON(row2,0,"branch",jsonData,headerStyleBold);
            writeDataToCellFromJSON(row5,0,"table_description_part1",jsonData,headerStyle);
            writeDataToCellFromJSON(row6,0,"table_description_part2",jsonData,headerStyle);
            writeDataToCellFromJSON(row1,5,"main_person_post",jsonData,headerStyleBoldRight);

            writeDataToCell(row0,5,"УТВЕРЖДАЮ",headerStyleBoldRight);
            writeDataToCell(row2,5,"__________________   " +
                    JsonFileReader.getValueFromJson("main_person_name",jsonData),headerStyleBoldRight);
            writeDataToCell(row3,5,"\"_____ \"_______________2023год",headerStyleBoldRight);
            setAllColumnsWidth(sheet);
            // Сохранение в файл
            try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
                workbook.write(outputStream);
                System.out.println("Данные успешно записаны в файл " + fileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

    }
    private static void setAllColumnsWidth(Sheet sheet){
        setColumnWidth(0,60,sheet);
        setColumnWidth(1,290,sheet);
        setColumnWidth(2,200,sheet);
        setColumnWidth(3,500,sheet);
        setColumnWidth(4,200,sheet);
        setColumnWidth(5,200,sheet);
        setColumnWidth(6,200,sheet);
        setColumnWidth(7,200,sheet);
    }

}
