package org.bntu.accounting.bntuaccountingsystem.excel.writers;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bntu.accounting.bntuaccountingsystem.models.Teacher;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TeacherWriter {
    private String filePath = "C:\\Users\\danii\\Desktop\\Задание практика\\Таблицы\\test.xlsx";

    public void writeToExistingTable(Teacher teacher) {
        try (FileInputStream fileIn = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fileIn);
             FileOutputStream fileOut = new FileOutputStream(filePath)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                try {
                        Cell cellIndex = row.createCell(0);
                        cellIndex.setCellValue(teacher.getId());
                        Cell cellFIO = row.createCell(1);
                        cellFIO.setCellValue(teacher.getName());
                        Cell cellPost = row.createCell(2);
                        cellPost.setCellValue(teacher.getPost());
                        Cell cellSubject = row.createCell(3);
                        cellSubject.setCellValue(teacher.getSubject());
                        Cell cellQualification = row.createCell(4);
                        cellQualification.setCellValue(teacher.getQualification());
                        Cell cellExp = row.createCell(5);
                        cellExp.setCellValue(teacher.getExp());
                        Cell cellCategory = row.createCell(6);
                        cellCategory.setCellValue(teacher.getCategory());
                        Cell cellSpec = row.createCell(7);
                        cellSpec.setCellValue(teacher.getYoungSpecialist());
                }
                catch (NullPointerException ex){
                    continue;
                }

            }
            workbook.write(fileOut);
            System.out.println("Data successfully written to the existing table in the excel file.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing data to the existing table in the excel file: " + e.getMessage());
        }
    }

    public void initNum(){
        try (FileInputStream fileIn = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fileIn);
             FileOutputStream fileOut = new FileOutputStream(filePath)) {

            Sheet sheet = workbook.getSheetAt(0);
            int i = 0;
            for (Row row : sheet) {
                if (row.getRowNum() > 8 && row.getRowNum() < 45) {
                    Cell cell = row.createCell(0);
                    cell.setCellValue(++i);
                }
            }
            workbook.write(fileOut);
            System.out.println("Data successfully written to the existing table in the excel file.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing data to the existing table in the excel file: " + e.getMessage());
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
