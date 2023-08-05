package org.bntu.accounting.bntuaccountingsystem.file.excel.creators;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class LoadFileCreator {
    public static void main(String[] args) {
        createExcelFile("example.xlsx");
    }


    public static void createExcelFile(String fileName) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Sheet1");
            // Задание ширины колонки "А" равной 200 пикселей (приближенно)
            int widthInPixels = 200;
            int columnWidth = (widthInPixels / 8) * 256;
            sheet.setColumnWidth(0, 5*256);
            // Заполнение данными
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Имяfdfsafsadfsadfsfasdfsdfas");


            Row dataRow1 = sheet.createRow(1);
            dataRow1.createCell(0).setCellValue("Иван");
            dataRow1.createCell(1).setCellValue("Иванов");
            dataRow1.createCell(2).setCellValue(25);

            Row dataRow2 = sheet.createRow(2);
            dataRow2.createCell(0).setCellValue("Петр");
            dataRow2.createCell(1).setCellValue("Петров");
            dataRow2.createCell(2).setCellValue(30);

            // Автонастройка ширины столбцов, кроме колонки "А"
            for (int i = 1; i < 3; i++) {
                sheet.autoSizeColumn(i);
            }

            // Сохранение в файл
            try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
                workbook.write(outputStream);
            }

            System.out.println("Файл " + fileName + " успешно создан.");
        } catch (IOException e) {
            System.err.println("Ошибка при создании файла: " + e.getMessage());
        }
    }

}
