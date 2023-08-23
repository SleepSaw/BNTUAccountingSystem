package org.bntu.accounting.bntuaccountingsystem.excel;

import org.apache.poi.ss.usermodel.*;
import org.bntu.accounting.bntuaccountingsystem.util.JsonFileReader;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelFileHeaderCreator extends ExcelUtil {
    private final JsonFileReader reader = new JsonFileReader();
    public ExcelFileHeaderCreator(Workbook workbook) {
        super(workbook);
    }

    public void writeDataToExcel(String fileName, int marginLeftIndexColumn, JSONObject jsonData, Workbook workbook) {
        Sheet sheet = workbook.getSheetAt(0);

        CellStyle headerStyle = createCellStyle(createFont("Times New Roman", 20, false));
        CellStyle headerStyleBold = createCellStyle(createFont("Times New Roman", 20, true));
        CellStyle headerStyleBoldRight = createCellStyle(createFont("Times New Roman", 24, true));

        Row row0 = sheet.createRow(0);
        Row row1 = sheet.createRow(1);
        Row row2 = sheet.createRow(2);
        Row row3 = sheet.createRow(3);
        Row row5 = sheet.createRow(5);
        Row row6 = sheet.createRow(6);


        writeDataToCell(row0, 0, jsonData.getString("governing_institution_name"), headerStyle);
        writeDataToCell(row1, 0, jsonData.getString("educational_institution_name"), headerStyle);
        writeDataToCell(row2, 0, jsonData.getString("branch"), headerStyleBold);
        writeDataToCell(row5, 0, jsonData.getString("table_description_part1"), headerStyle);
        writeDataToCell(row6, 0, jsonData.getString("table_description_part2"), headerStyle);
        writeDataToCell(row1, marginLeftIndexColumn, jsonData.getString("main_person_post"), headerStyleBoldRight);

        writeDataToCell(row0, marginLeftIndexColumn, "УТВЕРЖДАЮ", headerStyleBoldRight);
        writeDataToCell(row2, marginLeftIndexColumn, "__________________   " +
                (String) jsonData.get("main_person_name"), headerStyleBoldRight);
        writeDataToCell(row3, marginLeftIndexColumn, "\"_____ \"_______________2023год", headerStyleBoldRight);
        // Сохранение в файл
        try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
            workbook.write(outputStream);
            System.out.println("Данные успешно записаны в файл " + fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
