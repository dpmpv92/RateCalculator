package com.dpmpv92.ratecalculator.rate.controller;

import com.dpmpv92.ratecalculator.rate.model.Rate;
import com.dpmpv92.ratecalculator.rate.model.RateWindowRequest;
import com.dpmpv92.ratecalculator.rate.model.RatesWindowRequest;
import com.dpmpv92.ratecalculator.rate.service.RateService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
public class RateController {
    @Autowired
    RateService rateService;

    @GetMapping(path = "/rates")
    public Rate getRate(
            @RequestParam(value = "from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime fromTime,
            @RequestParam(value = "to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime toTime){
        Rate rate =  new Rate();
        rate.setRate(10);
        System.out.println("fromeDate = " + fromTime);
        return rate;
    }
    @PutMapping(path = "/rates")
    public void saveRates(@RequestBody RatesWindowRequest ratesWindowRequest){
        System.out.println("rateWindowRequest = " + ratesWindowRequest);
        rateService.saveRate(ratesWindowRequest);
    }
}

