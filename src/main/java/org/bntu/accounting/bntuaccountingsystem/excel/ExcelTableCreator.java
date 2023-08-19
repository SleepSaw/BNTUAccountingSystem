package org.bntu.accounting.bntuaccountingsystem.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.bntu.accounting.bntuaccountingsystem.interfaces.ExcelTable;
import org.bntu.accounting.bntuaccountingsystem.models.Teacher;

import java.util.List;

public abstract class ExcelTableCreator extends ExcelFileCreator implements ExcelTable {

    @Override
    public void createColumn(int startRow, int endRow, int startColumn, int endColumn, String title, CellStyle style,
                             Sheet sheet) {
        for(int i = startRow;i<=endRow;i++){
            Row row = sheet.getRow(i);
            for(int j = startColumn;j<=endColumn;j++){
                Cell cell = row.createCell(j);
                cell.setCellValue(title);
                cell.setCellStyle(style);
            }

        }
        if(startRow == endRow && startColumn == endColumn){
            return;
        }
        CellRangeAddress mergedRegion = new CellRangeAddress(startRow, endRow, startColumn, endColumn);
        sheet.addMergedRegion(mergedRegion);
    }

    @Override
    public abstract void setAllColumnsWidth(Sheet sheet);

    @Override
    public int addAllTeacherToTable(List<Teacher> teacherList, Sheet sheet) {
        addOneTeacherToTable(teacherList.get(0), sheet.createRow(0));
        return 0;
    }

    @Override
    public abstract int addOneTeacherToTable(Teacher teacher, Row row);
}
