package com.cosmos.nseindia.model;

import com.cosmos.nseindia.pojo.company.IndustryInfo;
import com.cosmos.nseindia.pojo.company.IntraDayHighLow;
import com.cosmos.nseindia.pojo.company.PriceInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "nselistedcompany009")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Company {
    @Id
    @Column(name = "companyName",unique=true,columnDefinition="VARCHAR(64)")
    private String companyName;
    private String isin;
    public String status;
    private LocalDate listingDate;
    @Embedded
    private IndustryInfo industryInfo;
    private LocalDate lastUpdateTime;
    private double minOfWeek;
    private LocalDate minWeekDate;
    private double maxOfWeek;
    private LocalDate maxWeekDate;
    @Embedded
    private IntraDayHighLow intraDayHighLow;

}
