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
public class SecurityInfo {
    public String boardStatus;
    public String tradingStatus;
    public String tradingSegment;
    public String sessionNo;
    public String slb;
    public String classOfShare;
    public String derivatives;
    public String surveillance;
    public int faceValue;
    public String issuedSize;
}
