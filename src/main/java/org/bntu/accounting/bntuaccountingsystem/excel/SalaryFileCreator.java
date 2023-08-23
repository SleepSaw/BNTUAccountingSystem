package org.bntu.accounting.bntuaccountingsystem.excel;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bntu.accounting.bntuaccountingsystem.models.Teacher;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class SalaryFileCreator {
    private final static String headerFilePath = "src\\main\\resources\\files\\excel_header.json";
    private final static String salaryTableFilePath = "src\\main\\resources\\files\\salary_table.json";
    private JsonFileReader jsonFileReader;
    private ExcelFileHeaderCreator headerCreator;
    private ExcelSalaryTableCreator SalaryTableCreator;
    private Workbook workbook;

    public void createFile(String filePath, List<Teacher> teacherList){
        try(Workbook workbook = new XSSFWorkbook()){
            this.workbook = workbook;
            Sheet sheet = workbook.createSheet("Педагогическая нагрузка");
            init();
            JSONObject headersData = jsonFileReader.readJsonFile(headerFilePath);
            JSONObject salaryTableData = jsonFileReader.readJsonFile(salaryTableFilePath);
            headerCreator.writeDataToExcel(filePath,12,headersData,workbook);
            SalaryTableCreator.createLoadTableColumns(filePath,salaryTableData);
            int endRow = SalaryTableCreator.addAllTeacherToTable(14,teacherList);
            SalaryTableCreator.addCommonData(endRow, teacherList);
            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                workbook.write(outputStream);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void init(){
        headerCreator = new ExcelFileHeaderCreator(workbook);
        SalaryTableCreator = new ExcelSalaryTableCreator(workbook);
        jsonFileReader = new JsonFileReader();
    }
}
