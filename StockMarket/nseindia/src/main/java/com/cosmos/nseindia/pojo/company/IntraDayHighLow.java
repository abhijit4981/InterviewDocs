package com.cosmos.nseindia.pojo.company;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
public class IntraDayHighLow {
    public double min;
    public double max;
    public double value;
}
