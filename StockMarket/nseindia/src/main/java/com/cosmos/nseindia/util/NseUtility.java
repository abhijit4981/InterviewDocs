package com.cosmos.nseindia.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Component
@Slf4j
public class NseUtility {
    public void readExcelFile(){
        String fileLocation ="";
        FileInputStream file = null;
        try {
            file = new FileInputStream(new File(getClass().getResource("ListedCompanyListNSE2022.xlsx").getFile()));
            Workbook workbook = new XSSFWorkbook(file);

            Sheet sheet = workbook.getSheetAt(0);
            log.info("Read this sheet "+sheet.getSheetName());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
