package com.example.demo.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author haitao zhu
 * @date 2020/3/7 0:31
 */
public class MergeCell {
    public static void main(String[] args) {

        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("机构课表导出");

        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue(new XSSFRichTextString("张三"));

        sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 2));

        try {
            File file = new File("e://test.xlsx");
            FileOutputStream fileOut = new FileOutputStream(file);
            wb.write(fileOut);
            fileOut.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
