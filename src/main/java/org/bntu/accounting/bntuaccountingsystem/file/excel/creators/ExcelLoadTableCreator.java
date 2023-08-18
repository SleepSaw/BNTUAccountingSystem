package org.bntu.accounting.bntuaccountingsystem.file.excel.creators;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelLoadTableCreator extends ExcelFileCreator {
    private final JsonFileReader reader = new JsonFileReader();

    public void createLoadTableColumns(String fileName, JSONObject jsonData, Workbook workbook) {
        Sheet sheet = workbook.getSheetAt(0);

        CellStyle columnsStyle = createCellStyle(workbook, createFont(workbook, "Times New Roman", 16, false));
        columnsStyle.setAlignment(HorizontalAlignment.CENTER);
        columnsStyle.setWrapText(true);
        columnsStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        columnsStyle.setBorderTop(BorderStyle.THIN);
        columnsStyle.setBorderBottom(BorderStyle.THIN);
        columnsStyle.setBorderLeft(BorderStyle.THIN);
        columnsStyle.setBorderRight(BorderStyle.THIN);
        CellStyle styleBold = createCellStyle(workbook, createFont(workbook, "Times New Roman", 20, true));


        Row row9 = sheet.createRow(9);
        Row row10 = sheet.createRow(10);
        row10.setHeightInPoints(30);
        Row row11 = sheet.createRow(11);
        row11.setHeightInPoints(30);
        Row row12 = sheet.createRow(12);
        row12.setHeightInPoints(100);
        Row row13 = sheet.createRow(13);



        writeDataToCellFromJSON(row9, 0, "chapter_name", jsonData, styleBold);
        createMergeColumn(10,12,0,0,"index_column",jsonData,sheet,columnsStyle);
        createMergeColumn(10,12,1,1,"fio_column",jsonData,sheet,columnsStyle);
        createMergeColumn(10,12,2,2,"post_column",jsonData,sheet,columnsStyle);
        createMergeColumn(10,12,3,3,"subject_column",jsonData,sheet,columnsStyle);

        createMergeColumn(10,10,4,7,"week_load_column",jsonData,sheet,columnsStyle);
        createMergeColumn(11,11,5,7,"parts_load_column",jsonData,sheet,columnsStyle);

        createMergeColumn(11,12,4,4,"total_load_column",jsonData,sheet,columnsStyle);
        createMergeColumn(12,12,5,5,"academic_load_column",jsonData,sheet,columnsStyle);
        createMergeColumn(12,12,6,6,"additional_load_column",jsonData,sheet,columnsStyle);
        createMergeColumn(12,12,7,7,"organization_load_column",jsonData,sheet,columnsStyle);

        try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
            workbook.write(outputStream);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    private void createMergeColumn(int fromRow, int toRow,int fromColumn, int toColumn,
                                          String jsonKey, JSONObject jsonData,
                                          Sheet sheet, CellStyle style){
        for(int i = fromRow;i<=toRow;i++){
            Row row = sheet.getRow(i);
            for(int j = fromColumn;j<=toColumn;j++){
                Cell cell = row.createCell(j);
                cell.setCellValue((String) reader.getValueFromJson(jsonKey,jsonData));
                cell.setCellStyle(style);
            }

        }
        if(fromRow == toRow && fromColumn == toColumn){
            return;
        }
        CellRangeAddress mergedRegion = new CellRangeAddress(fromRow, toRow, fromColumn, toColumn);
        sheet.addMergedRegion(mergedRegion);
    }

}
