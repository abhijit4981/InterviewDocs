package com.cosmos.nseindia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "companyinternal009")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CompanyView {
    @Id
    @Column(name = "companyName",unique=true,columnDefinition="VARCHAR(64)")
    private String companyName;

    private String[] companyWatchList;
    private String companyTrendPattern;
    private String companyDaysAsNumbers;
    private int companyPosition;

}
