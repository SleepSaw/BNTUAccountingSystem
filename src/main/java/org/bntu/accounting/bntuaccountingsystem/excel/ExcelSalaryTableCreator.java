package org.bntu.accounting.bntuaccountingsystem.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.bntu.accounting.bntuaccountingsystem.models.Teacher;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelSalaryTableCreator extends ExcelTableCreator  {
    private final JsonFileReader reader = new JsonFileReader();
    private Workbook workbook;

    public void createLoadTableColumns(String fileName, JSONObject jsonData, Workbook workbook) {
        this.workbook = workbook;
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

        setAllColumnsWidth(sheet);

        Row row9 = sheet.createRow(9);
        Row row10 = sheet.createRow(10);
        row10.setHeightInPoints(30);
        Row row11 = sheet.createRow(11);
        row11.setHeightInPoints(30);
        Row row12 = sheet.createRow(12);
        row12.setHeightInPoints(100);
        Row row13 = sheet.createRow(13);



        writeDataToCellFromJSON(row9, 0, "chapter_name", jsonData, styleBold);

        createColumn(10,12,0,0,jsonData.getString("index_column"),columnsStyle,workbook);
        createColumn(10,12,1,1,jsonData.getString("fio_column"),columnsStyle,workbook);
        createColumn(10,12,2,2,jsonData.getString("post_column"),columnsStyle,workbook);
        createColumn(10,12,3,3,jsonData.getString("subject_column"),columnsStyle,workbook);

        createColumn(10,10,4,7,jsonData.getString("week_load_column"),columnsStyle,workbook);
        createColumn(11,11,5,7,jsonData.getString("parts_load_column"),columnsStyle,workbook);

        createColumn(11,12,4,4,jsonData.getString("total_load_column"),columnsStyle,workbook);
        createColumn(12,12,5,5,jsonData.getString("academic_load_column"),columnsStyle,workbook);
        createColumn(12,12,6,6,jsonData.getString("additional_load_column"),columnsStyle,workbook);
        createColumn(12,12,7,7,jsonData.getString("organization_load_column"),columnsStyle,workbook);

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
    public void setAllColumnsWidth(Sheet sheet){
        setColumnWidth(0,50,sheet);
        setColumnWidth(1,290,sheet);
        setColumnWidth(2,200,sheet);
        setColumnWidth(3,660,sheet);
        setColumnWidth(4,200,sheet);
        setColumnWidth(5,200,sheet);
        setColumnWidth(6,200,sheet);
        setColumnWidth(7,200,sheet);
    }

    @Override
    public void addOneTeacherToTable(int number, Teacher teacher, Row row) {
        CellStyle styleAlignCenter = workbook.createCellStyle();
        styleAlignCenter.cloneStyleFrom(columnStyle);
        styleAlignCenter.setAlignment(HorizontalAlignment.CENTER);

        CellStyle styleAlignLeft = workbook.createCellStyle();
        styleAlignLeft.cloneStyleFrom(columnStyle);
        styleAlignLeft.setAlignment(HorizontalAlignment.LEFT);

        CellStyle styleAlignRight = workbook.createCellStyle();
        styleAlignRight.cloneStyleFrom(columnStyle);
        styleAlignRight.setAlignment(HorizontalAlignment.RIGHT);

        Cell numberCell = row.createCell(0);
        numberCell.setCellValue(number);
        numberCell.setCellStyle(styleAlignCenter);

        Cell fioCell = row.createCell(1);
        fioCell.setCellValue(teacher.getName());
        fioCell.setCellStyle(styleAlignLeft);

        Cell postCell = row.createCell(2);
        postCell.setCellValue(teacher.getPost());
        postCell.setCellStyle(styleAlignLeft);

        Cell subjectCell = row.createCell(3);
        subjectCell.setCellValue(teacher.getSubject());
        subjectCell.setCellStyle(styleAlignLeft);

        Cell totalLoadCell = row.createCell(4);
        totalLoadCell.setCellValue(teacher.getLoad().getTotalLoad());
        totalLoadCell.setCellStyle(styleAlignRight);

        Cell academicLoadCell = row.createCell(5);
        academicLoadCell.setCellValue(teacher.getLoad().getAcademicLoad());
        academicLoadCell.setCellStyle(styleAlignRight);

        Cell addLoadCell = row.createCell(6);
        addLoadCell.setCellValue(teacher.getLoad().getAddLoad());
        addLoadCell.setCellStyle(styleAlignRight);

        Cell orgLoadCell = row.createCell(7);
        orgLoadCell.setCellValue(teacher.getLoad().getOrgLoad());
        orgLoadCell.setCellStyle(styleAlignRight);
    }
}
