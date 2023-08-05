package org.bntu.accounting.bntuaccountingsystem.file.excel.creators;

import org.apache.poi.ss.usermodel.*;
import org.json.JSONObject;

public class ExcelFileCreator {

    protected static Font createFont(Workbook workbook, String fontFamily, int fontSize, boolean isBold){
        Font font = workbook.createFont();
        font.setFontName(fontFamily);
        font.setFontHeightInPoints((short) fontSize);
        font.setBold(isBold);
        return font;
    }
    protected static CellStyle createCellStyle(Workbook workbook, Font font){
        CellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }
    protected static void setColumnWidth(int columnIndex, int width, Sheet sheet){
        sheet.setColumnWidth(columnIndex, (width / 8)*256);
    }
    protected static void writeDataToCell(Row row, int columnIndex, String value, CellStyle style){
        Cell cell = row.createCell(columnIndex);
        cell.setCellValue(value);
        cell.setCellStyle(style);
    }
    protected static void writeDataToCellFromJSON(Row row, int columnIndex,
                                                  String jsonKey, JSONObject jsonData, CellStyle style){
        Cell cell = row.createCell(columnIndex);
        cell.setCellValue(JsonFileReader.getValueFromJson(jsonKey,jsonData));
        cell.setCellStyle(style);
    }
}