package org.bntu.accounting.bntuaccountingsystem.excel.readers;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bntu.accounting.bntuaccountingsystem.models.Load;
import org.bntu.accounting.bntuaccountingsystem.models.Teacher;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadReader {
    private String filePath = "C:\\Users\\danii\\Desktop\\Задание практика\\Таблицы\\Педагогическая нагрузка.xlsx";

    public LoadReader() {
    }

    public LoadReader(String filePath) {
        this.filePath = filePath;
    }

    public Load readRow(Row row) {
        double academicLoad = row.getCell(5).getNumericCellValue();
        double orgLoad = row.getCell(6).getNumericCellValue();
        double addLoad = row.getCell(7).getNumericCellValue();
        return new Load(academicLoad,orgLoad,addLoad);
    }
    public void readSheet(List<Teacher> teachers) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() > 13 && row.getCell(0).getNumericCellValue() != 0) {
                    for (Teacher teacher: teachers) {
                        if (teacher.getFio().equals(row.getCell(1).getStringCellValue())){
                            teacher.setLoad(readRow(row));
                            System.out.println(teacher);
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}
