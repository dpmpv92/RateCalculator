package com.dpmpv92.ratecalculator.rate.repository;

import com.dpmpv92.ratecalculator.rate.model.RateWindow;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class RateRepository {
    List<RateWindow> rates = Collections.emptyList();

    public void save(List<RateWindow> rates) {
        this.rates = rates;
    }

    public List<RateWindow> find() {
        return rates;
    }
}
