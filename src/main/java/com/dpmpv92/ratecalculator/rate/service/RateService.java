package com.dpmpv92.ratecalculator.rate.service;

import com.dpmpv92.ratecalculator.rate.model.DaysOfWeek;
import com.dpmpv92.ratecalculator.rate.model.RateWindow;
import com.dpmpv92.ratecalculator.rate.model.RateWindowRequest;
import com.dpmpv92.ratecalculator.rate.model.RatesWindowRequest;
import com.dpmpv92.ratecalculator.rate.repository.RateRepository;
import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class RateService {
    @Autowired
    RateRepository rateRepository;

    public void saveRate(RatesWindowRequest ratesWindowRequest) {
        if(ratesWindowRequest.getRates()!= null ){
            List<RateWindow> rates = ratesWindowRequest.getRates()
                    .stream()
                    .map(rateWindowRequest -> converterToRates(rateWindowRequest))
                    .collect(toList());
            rateRepository.save(rates);
        }
    }

    private RateWindow converterToRates(RateWindowRequest rateWindowRequest) {
        Arrays.stream(rateWindowRequest.getDays().split(",")).forEach(day -> System.out.println("day = " + day));
        Arrays.stream(rateWindowRequest.getTimes().split("-")).forEach(time -> System.out.println("time = " + time));
        return RateWindow.builder()
                .price(rateWindowRequest.getPrice())
                .startTime(converterMilitaryTimeToLocalTime(rateWindowRequest.getTimes().split("-")[0]))
                .endTime(converterMilitaryTimeToLocalTime(rateWindowRequest.getTimes().split("-")[1]))
                .daysOfWeekList(Arrays.stream(rateWindowRequest.getDays().split(",")).map(day -> DaysOfWeek.forValue(day)).collect(toList()))
                .build();

    }

    private LocalTime converterMilitaryTimeToLocalTime(String militaryTime) {
        String hour = militaryTime.substring(0,2);
        String minutes = militaryTime.substring(2,4);
        return new LocalTime(Integer.parseInt(hour), Integer.parseInt(minutes));
    }
}
