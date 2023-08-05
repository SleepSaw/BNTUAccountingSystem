package org.bntu.accounting.bntuaccountingsystem.file.excel.creators;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class TableCreator {
    public static void createMergedColumnsTable(Sheet sheet, int rowCount) {
        // Создание стиля для границ
        CellStyle borderStyle = sheet.getWorkbook().createCellStyle();
        borderStyle.setBorderTop(BorderStyle.THIN);
        borderStyle.setBorderBottom(BorderStyle.THIN);
        borderStyle.setBorderLeft(BorderStyle.THIN);
        borderStyle.setBorderRight(BorderStyle.THIN);
        borderStyle.setWrapText(true); // Включение автоматического переноса текста

        // Создание ячеек
        for (int colIdx = 0; colIdx < 4; colIdx++) {
            for (int rowIdx = 0; rowIdx < rowCount; rowIdx++) {
                Row row = sheet.getRow(rowIdx);
                if (row == null) {
                    row = sheet.createRow(rowIdx);
                }
                Cell cell = row.createCell(colIdx);
                cell.setCellValue("Колонка " + (colIdx + 1));
                cell.setCellStyle(borderStyle);
            }
        }

        // Объединение для каждой колонки
        for (int colIdx = 0; colIdx < 4; colIdx++) {
            CellRangeAddress mergedRegion = new CellRangeAddress(0, rowCount - 1, colIdx, colIdx);
            sheet.addMergedRegion(mergedRegion);
        }
    }

    public static void main(String[] args) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("MergedColumnsTable");

        int rowCount = 6; // Количество строк в таблице

        // Создание таблицы из 4 объединенных колонок с черными границами
        createMergedColumnsTable(sheet, rowCount);

        // Сохранение в файл
        String fileName = "merged_columns_table.xlsx";
        try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
            workbook.write(outputStream);
            System.out.println("Таблица с объединенными колонками успешно создана в файле " + fileName);
        } catch (IOException e) {
            System.err.println("Ошибка при создании таблицы: " + e.getMessage());
        }
    }
}

