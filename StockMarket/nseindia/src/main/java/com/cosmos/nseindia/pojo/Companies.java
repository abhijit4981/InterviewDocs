package com.cosmos.nseindia.pojo;

import com.cosmos.nseindia.model.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Companies {
    private List<Company> companyList;
}
