package org.bntu.accounting.bntuaccountingsystem.excel;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bntu.accounting.bntuaccountingsystem.models.Teacher;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class LoadFileCreator {
    private final static String headerFilePath = "src\\main\\resources\\files\\excel_header.json";
    private final static String loadTableFilePath = "src\\main\\resources\\files\\load_table.json";
    private JsonFileReader jsonFileReader;
    private ExcelFileHeaderCreator headerCreator;

    public void createFile(String filePath, List<Teacher> teacherList){
        try(Workbook workbook = new XSSFWorkbook()){
            Sheet sheet = workbook.createSheet("Педагогическая нагрузка");
            init();
            JSONObject headersData = jsonFileReader.readJsonFile(headerFilePath);
            JSONObject loadTableData = jsonFileReader.readJsonFile(loadTableFilePath);
            headerCreator.createHeader(headerFilePath,headersData,workbook);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void init(){
        headerCreator = new ExcelFileHeaderCreator();
        jsonFileReader = new JsonFileReader();
    }

}
