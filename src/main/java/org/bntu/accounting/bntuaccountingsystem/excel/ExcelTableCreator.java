package org.bntu.accounting.bntuaccountingsystem.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.bntu.accounting.bntuaccountingsystem.interfaces.ExcelTable;
import org.bntu.accounting.bntuaccountingsystem.models.Teacher;

import java.util.List;

public abstract class ExcelTableCreator extends ExcelFileCreator implements ExcelTable {
    protected CellStyle columnStyle;
    private Integer counter =0;

    @Override
    public void createColumn(int startRow, int endRow, int startColumn, int endColumn, String title, CellStyle style,
                             Workbook workbook) {
        Sheet sheet = workbook.getSheetAt(0);

        columnStyle = workbook.createCellStyle();
        columnStyle.cloneStyleFrom(style);
        Cell cell;
        Row row = sheet.getRow(startRow);
        for(int i = startRow;i<=endRow;i++){
            row = sheet.getRow(i);
            for(int j = startColumn;j<=endColumn;j++){
                cell = row.createCell(j);
                cell.setCellValue(title);
                cell.setCellStyle(style);
            }
        }

        if(startRow != endRow || startColumn != endColumn){
            CellRangeAddress mergedRegion = new CellRangeAddress(startRow, endRow, startColumn, endColumn);
            sheet.addMergedRegion(mergedRegion);
        }
        if(startColumn == endColumn){
            row = sheet.getRow(endRow+1);
            counter++;
            indexingColumn(row,startColumn,counter.toString(),workbook);
        }
    }
    private void indexingColumn(Row row, int columnIndex,String value, Workbook workbook){
        Cell cell = row.createCell(columnIndex);
        Font font = createFont(workbook,"Times New Roman",12,false,true);
        CellStyle style = createCellStyle(workbook,font);
        style.cloneStyleFrom(columnStyle);
        style.setFont(font);
        cell.setCellValue(value);
        cell.setCellStyle(style);
    }

    @Override
    public abstract void setAllColumnsWidth(Sheet sheet);

    // Реализация добавления учителей в таблицу
    @Override
    public int addAllTeacherToTable(int startRow,List<Teacher> teacherList, Sheet sheet) {
        int i = startRow;
        int count = 1;
        for (Teacher t: teacherList) {
            Row row = sheet.createRow(i);
            addOneTeacherToTable(count,t,row);
            i++;
            count++;
        }
        return i;
    }

    // Асбтрактный метод, который необходимо переопределить для добавления одного учителя
    @Override
    public abstract void addOneTeacherToTable(int number,Teacher teacher, Row row);
}
