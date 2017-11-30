package com.dpmpv92.ratecalculator.rate.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
//@Builder
@XmlRootElement(name = "rates")
@NoArgsConstructor
public class RatesWindowRequest {
    List<RateWindowRequest> rates;
}
