package com.cosmos.cameldemo.model.company;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Info {
    public String symbol;
    public String companyName;
    public String industry;
    public ArrayList<String> activeSeries;
    public ArrayList<Object> debtSeries;
    public ArrayList<String> tempSuspendedSeries;
    public boolean isFNOSec;
    public boolean isCASec;
    public boolean isSLBSec;
    public boolean isDebtSec;
    public boolean isSuspended;
    public boolean isETFSec;
    public boolean isDelisted;
    public String isin;
    public boolean isTop10;
    public String identifier;
}
