package org.bntu.accounting.bntuaccountingsystem.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.bntu.accounting.bntuaccountingsystem.excel.ExcelFileCreator;
import org.bntu.accounting.bntuaccountingsystem.models.Teacher;

import java.util.List;

public class TeacherLoadWriter extends ExcelFileCreator {

    public int writeAllTeachers(int startRowIndex, List<Teacher> teachers, Workbook workbook){
        Sheet sheet = workbook.getSheetAt(0);
        int i = startRowIndex;
        int count = 1;
        for (Teacher t: teachers) {
            Row row = sheet.createRow(i);
            writeTeacher(row,count,t);
            i++;
            count++;
        }
        return i;
    }

    private void writeTeacher(Row row, int teacherIndex,Teacher teacher){
        Cell idCell = row.createCell(0);
        idCell.setCellValue(teacherIndex);
        Cell fioCell = row.createCell(1);
        fioCell.setCellValue(teacher.getName());
        Cell postCell = row.createCell(2);
        postCell.setCellValue(teacher.getPost());
        Cell subjectCell = row.createCell(3);
        subjectCell.setCellValue(teacher.getSubject());
        Cell totalLoadCell = row.createCell(4);
        totalLoadCell.setCellValue(teacher.getLoad().getTotalLoad());
        Cell academicLoadCell = row.createCell(5);
        academicLoadCell.setCellValue(teacher.getLoad().getAcademicLoad());
        Cell addLoadCell = row.createCell(6);
        addLoadCell.setCellValue(teacher.getLoad().getAddLoad());
        Cell orgLoadCell = row.createCell(7);
        orgLoadCell.setCellValue(teacher.getLoad().getOrgLoad());
    }
}
