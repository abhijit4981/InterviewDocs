package com.cosmos.nseindia.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Component
@Slf4j
public class NseUtility {
    public static Set<String> nseListedCompany = new HashSet<>();
    @Bean
    public void readExcelFile(){
        FileInputStream file = null;
        try {
            File file1 = ResourceUtils.getFile("classpath:ListedCompanyListNSE2022.xlsx");
            file = new FileInputStream(file1);
            Workbook workbook = new XSSFWorkbook(file);

            DataFormatter dataFormatter = new DataFormatter();
            Sheet sheet = workbook.getSheetAt(0);
            log.info("Read this sheet "+sheet.getSheetName());
            Iterator<Row> rows = sheet.iterator();
            while(rows.hasNext()){
                Row row = rows.next();
                Iterator<Cell> cellIterator = row.iterator();
                while(cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    String cellValue = dataFormatter.formatCellValue(cell);
                    nseListedCompany.add(cellValue);
                }
            }
        workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
