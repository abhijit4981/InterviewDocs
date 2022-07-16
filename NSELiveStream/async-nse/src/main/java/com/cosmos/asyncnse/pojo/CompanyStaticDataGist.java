package com.cosmos.asyncnse.pojo;

import com.cosmos.asyncnse.pojo.company.IndustryInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CompanyStaticDataGist {
    private String symbol;
    private String series;
    private String companyName;
    private String industry;
    private ArrayList<String> activeSeries;
    private String isin;
    private LocalDate listingDate;
    private LocalDate lastUpdateTime;
    private int faceValue;
    private Long issuedSize;
    private IndustryInfo industryInfo;
}
