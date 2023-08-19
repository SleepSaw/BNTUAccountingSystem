package org.bntu.accounting.bntuaccountingsystem.excel;
import org.apache.poi.ss.usermodel.*;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelFileHeaderCreator extends ExcelFileCreator {
    private final JsonFileReader reader = new JsonFileReader();
    public void writeDataToExcel(String fileName, JSONObject jsonData, Workbook workbook) {
            Sheet sheet = workbook.getSheetAt(0);

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
                    (String) jsonData.get("main_person_name"),headerStyleBoldRight);
            writeDataToCell(row3,5,"\"_____ \"_______________2023год",headerStyleBoldRight);
            // Сохранение в файл
            try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
                workbook.write(outputStream);
                System.out.println("Данные успешно записаны в файл " + fileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

    }

    // Вынести в создание колонок


    public void createHeader(String fileName, JSONObject jsonData, Workbook workbook){
        Sheet sheet = workbook.getSheetAt(0);

        CellStyle headerStyle = createCellStyle(workbook,createFont(workbook,"Times New Roman",20,false));
        CellStyle headerStyleBold = createCellStyle(workbook,createFont(workbook,"Times New Roman",20,true));
        CellStyle headerStyleBoldRight = createCellStyle(workbook,createFont(workbook,"Times New Roman",24,true));


    }

}
