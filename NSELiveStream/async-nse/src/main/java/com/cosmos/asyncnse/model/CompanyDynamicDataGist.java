package com.cosmos.asyncnse.model;

import com.cosmos.asyncnse.pojo.company.PriceInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CompanyDynamicDataGist {
    private String symbol;
    private double pdSectorPe;
    private double pdSymbolPe;
    private PriceInfoGist priceInfo;
}
