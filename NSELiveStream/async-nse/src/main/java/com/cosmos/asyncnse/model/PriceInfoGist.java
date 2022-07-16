package com.cosmos.asyncnse.model;

import com.cosmos.asyncnse.pojo.company.IntraDayHighLow;
import com.cosmos.asyncnse.pojo.company.WeekHighLow;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PriceInfoGist {
    private LocalDate now;
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
