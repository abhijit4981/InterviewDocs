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
public class Metadata {
    public String series;
    public String symbol;
    public String isin;
    public String status;
    public String listingDate;
    public String industry;
    public String lastUpdateTime;
    public double pdSectorPe;
    public double pdSymbolPe;
    public String pdSectorInd;
}
