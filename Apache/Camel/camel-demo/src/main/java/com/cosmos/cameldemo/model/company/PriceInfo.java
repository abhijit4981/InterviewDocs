package com.cosmos.cameldemo.model.company;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceInfo {
    public double lastPrice;
    public double change;
    public double pChange;
    public double previousClose;
    @JsonProperty("open")
    public double myopen;
    public int close;
    public double vwap;
    public String lowerCP;
    public String upperCP;
    public String pPriceBand;
    public double basePrice;
    public IntraDayHighLow intraDayHighLow;
    public WeekHighLow weekHighLow;
}
