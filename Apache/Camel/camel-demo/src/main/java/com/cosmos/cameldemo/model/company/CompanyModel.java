package com.cosmos.cameldemo.model.company;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyModel {
    public Info info;
    public Metadata metadata;
    public SecurityInfo securityInfo;
    public PriceInfo priceInfo;
    public IndustryInfo industryInfo;
    public PreOpenMarket preOpenMarket;
}
