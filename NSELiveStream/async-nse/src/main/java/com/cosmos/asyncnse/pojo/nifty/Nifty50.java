package com.cosmos.asyncnse.pojo.nifty;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */
 class Advance{
     String declines;
     String advances;
     String unchanged;
}

 class Datum{
     int priority;
     String symbol;
     String identifier;
    @JsonProperty("open")
     double myopen;
     double dayHigh;
     double dayLow;
     double lastPrice;
     double previousClose;
     double change;
     double pChange;
     double ffmc;
     double yearHigh;
     double yearLow;
     int totalTradedVolume;
     double totalTradedValue;
     String lastUpdateTime;
     double nearWKH;
     double nearWKL;
     double perChange365d;
     String date365dAgo;
     String chart365dPath;
     String date30dAgo;
     double perChange30d;
     String chart30dPath;
     String chartTodayPath;
     String series;
     Meta meta;
}

 class MarketStatus{
     String market;
     String marketStatus;
     String tradeDate;
     String index;
     double last;
     int variation;
     double percentChange;
     String marketStatusMessage;
}

 class Meta{
     String symbol;
     String companyName;
     String industry;
     ArrayList<String> activeSeries;
     ArrayList<String> debtSeries;
     ArrayList<String> tempSuspendedSeries;
     boolean isFNOSec;
     boolean isCASec;
     boolean isSLBSec;
     boolean isDebtSec;
     boolean isSuspended;
     boolean isETFSec;
     boolean isDelisted;
     String isin;
}

 class Metadata{
     String indexName;
    @JsonProperty("open")
     double myopen;
     double high;
     double low;
     double previousClose;
     double last;
     double percChange;
     int change;
     String timeVal;
     double yearHigh;
     double yearLow;
     int totalTradedVolume;
     double totalTradedValue;
     double ffmc_sum;
}

 public class Nifty50{
     private String name;
     private Advance advance;
     private String timestamp;
     private ArrayList<Datum> data;
     private Metadata metadata;
     private MarketStatus marketStatus;
     private String date30dAgo;
     private String date365dAgo;
}


