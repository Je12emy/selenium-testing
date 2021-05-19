package com.selenium.selenium_testing.ExcelExportAndRead;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
    public Sheet readExcel(String filePath, String fileName, String SheetName) throws IOException {
        File file = new File(filePath);
        FileInputStream inputStream = new FileInputStream(file);
        Workbook excelWorkbook = null;
        String fileExtension = fileName.substring(fileName.indexOf("."));
        if (fileExtension.equals(".xlsx")) {
            excelWorkbook = new XSSFWorkbook(inputStream);
        } else if (fileExtension.equals(".xls")) {
            excelWorkbook = new HSSFWorkbook(inputStream);
        }
        Sheet excelSheet = excelWorkbook.getSheet(SheetName);
        return excelSheet;
    }

}
