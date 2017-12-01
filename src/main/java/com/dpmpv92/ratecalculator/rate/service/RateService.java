package com.dpmpv92.ratecalculator.rate.service;

import com.dpmpv92.ratecalculator.rate.model.*;
import com.dpmpv92.ratecalculator.rate.repository.RateRepository;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

@Service
public class RateService {
    @Autowired
    RateRepository rateRepository;

    public void saveRate(RatesWindowRequest ratesWindowRequest) {
        if (ratesWindowRequest.getRates() != null) {
            List<RateWindow> rates = ratesWindowRequest.getRates()
                    .stream()
                    .map(rateWindowRequest -> converterToRates(rateWindowRequest))
                    .collect(toList());
            rateRepository.save(rates);
        }
    }

    private RateWindow converterToRates(RateWindowRequest rateWindowRequest) {
        return RateWindow.builder()
                .price(rateWindowRequest.getPrice())
                .startTime(converterMilitaryTimeToLocalTime(rateWindowRequest.getTimes().split("-")[0]))
                .endTime(converterMilitaryTimeToLocalTime(rateWindowRequest.getTimes().split("-")[1]))
                .daysOfWeekList(Arrays.stream(rateWindowRequest.getDays().split(",")).map(day -> DaysOfWeek.forValue(day)).collect(toList()))
                .build();

    }

    private LocalTime converterMilitaryTimeToLocalTime(String militaryTime) {
        String hour = militaryTime.substring(0, 2);
        String minutes = militaryTime.substring(2, 4);
        return new LocalTime(Integer.parseInt(hour), Integer.parseInt(minutes));
    }

    public Rate getRate(DateTime fromDate, DateTime toDate) {
        List<RateWindow> matchingRateWindow = asList(RateWindow.builder().build());
        List<RateWindow> rates = rateRepository.find();
        List<RateWindow> rateForDay = rates.stream().filter(rateWindow -> rateWindow.getDaysOfWeekList().contains(DaysOfWeek.forValue(toDate.getDayOfWeek()))).collect(toList());
        if (rateForDay != null && !rateForDay.isEmpty()) {
            matchingRateWindow = rateForDay.stream().filter(rateWindow -> rateWindow.getStartTime().compareTo(fromDate.toLocalTime()) < 0 &&
                    rateWindow.getEndTime().compareTo(toDate.toLocalTime()) > 0).collect(toList());
        }
        Rate rate = new Rate();
        if (!matchingRateWindow.isEmpty()) {
            rate.setRate(matchingRateWindow.get(0).getPrice());
            rate.setRateFound(true);
        } else {
            rate.setRateFound(false);
        }

        return rate;
    }
}
