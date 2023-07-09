package org.bntu.accounting.bntuaccountingsystem.excel.readers;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bntu.accounting.bntuaccountingsystem.builder.TeacherBuilder;
import org.bntu.accounting.bntuaccountingsystem.models.Teacher;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TeacherReader {
    private String filePath = "C:\\Users\\danii\\Desktop\\Задание практика\\Таблицы\\Информация о работниках.xlsx";
    public TeacherReader() {
    }

    public TeacherReader(String filePath) {
        this.filePath = filePath;
    }

    public Teacher readRow(Row row) {
        String fio = row.getCell(1).getStringCellValue();
        String post = row.getCell(2).getStringCellValue();
        String subject = row.getCell(3).getStringCellValue();
        String qualification = row.getCell(4).getStringCellValue();
        String experience = row.getCell(5).getStringCellValue();
        double category = row.getCell(6).getNumericCellValue();
        String youngSpecialist = row.getCell(7).getStringCellValue();
        TeacherBuilder teacherBuilder = new TeacherBuilder();
        return teacherBuilder.setFio(fio).
                setPost(post).
                setSubject(subject).
                setQualification(qualification).
                setWorkExperience(experience).
                setCategory((int)category).
                setYoungSpecialist(youngSpecialist).
                build();
    }

    public List<Teacher> readSheet() {
        List<Teacher> teachers = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            int num;
            for (Row row : sheet) {
                num = row.getRowNum();
                if (row.getRowNum() > 8 && row.getCell(0).getNumericCellValue() != 0) {
                    teachers.add(readRow(row));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return teachers;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
