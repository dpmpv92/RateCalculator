package com.dpmpv92.ratecalculator.rate.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@Data
//@Builder
@XmlRootElement(name = "rates")
@NoArgsConstructor
public class RateWindowRequest {
    String days;
    String times;
    int price;
}
