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
public class WeekHighLow {
    public double min;
    public String minDate;
    public double max;
    public String maxDate;
    public double value;
}
