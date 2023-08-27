package org.bntu.accounting.bntuaccountingsystem.excel;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bntu.accounting.bntuaccountingsystem.models.Teacher;
import org.bntu.accounting.bntuaccountingsystem.util.JsonFileReader;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class LoadFileCreator {
    private final static String headerFilePath = "src\\main\\resources\\files\\excel_header.json";
    private final static String loadTableFilePath = "src\\main\\resources\\files\\load_table.json";
    private JsonFileReader jsonFileReader;
    private ExcelFileHeaderCreator headerCreator;
    private ExcelLoadTableCreator loadTableCreator;
    private Workbook workbook;

    public void createFile(String filePath, List<Teacher> teacherList){
        try(Workbook workbook = new XSSFWorkbook()){
            this.workbook = workbook;
            Sheet sheet = workbook.createSheet("Педагогическая нагрузка");
            init();
            JSONObject headersData = jsonFileReader.readJsonFile(headerFilePath);
            JSONObject loadTableData = jsonFileReader.readJsonFile(loadTableFilePath);
            headerCreator.writeDataToExcel(filePath,5,headersData,workbook);
            loadTableCreator.createLoadTableColumns(filePath,loadTableData);
            int endRow = loadTableCreator.addAllTeacherToTable(14,teacherList);
            loadTableCreator.addCommonData(endRow, teacherList);
            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                workbook.write(outputStream);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void init(){
        headerCreator = new ExcelFileHeaderCreator(workbook);
        loadTableCreator = new ExcelLoadTableCreator(workbook);
        jsonFileReader = new JsonFileReader();
    }

}
