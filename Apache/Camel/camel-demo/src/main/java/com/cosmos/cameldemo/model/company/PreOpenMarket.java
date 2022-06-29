package com.cosmos.cameldemo.model.company;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class PreOpenMarket {
    public ArrayList<Preopen> preopen;
    public Ato ato;
    @JsonProperty("IEP")
    public double iEP;
    public int totalTradedVolume;
    public double finalPrice;
    public int finalQuantity;
    public String lastUpdateTime;
    public int totalBuyQuantity;
    public int totalSellQuantity;
    public int atoBuyQty;
    public int atoSellQty;
}
