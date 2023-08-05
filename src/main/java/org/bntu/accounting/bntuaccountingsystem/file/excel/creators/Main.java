package org.bntu.accounting.bntuaccountingsystem.file.excel.creators;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;

import java.io.IOException;

public class Main {
    private final static String jsonLoadTableFilePath = "C:\\Users\\danii\\IdeaProjects\\BNTUAccountingSystem\\src\\main\\resources\\files\\load_table.json";
    private final static String jsonHeaderFilePath = "C:\\Users\\danii\\IdeaProjects\\BNTUAccountingSystem\\src\\main\\resources\\files\\excel_header.json";
    private final static String fileName = "output.xlsx";

    public static void main(String[] args) {
        try (Workbook workbook = new XSSFWorkbook()) {
            JSONObject jsonHeader = JsonFileReader.readJsonFile(jsonHeaderFilePath);
            JSONObject jsonLoadTable = JsonFileReader.readJsonFile(jsonLoadTableFilePath);
            ExcelFileHeaderCreator.writeDataToExcel(fileName,jsonHeader,workbook);
            ExcelLoadTableCreator.createLoadTableColumns(fileName,jsonLoadTable,workbook);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
