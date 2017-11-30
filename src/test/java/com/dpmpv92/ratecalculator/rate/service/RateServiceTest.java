package com.dpmpv92.ratecalculator.rate.service;

import com.dpmpv92.ratecalculator.rate.model.RateWindow;
import com.dpmpv92.ratecalculator.rate.model.RateWindowRequest;
import com.dpmpv92.ratecalculator.rate.model.RatesWindowRequest;
import com.dpmpv92.ratecalculator.rate.repository.RateRepository;
import org.joda.time.LocalTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.dpmpv92.ratecalculator.rate.model.DaysOfWeek.MONDAY;
import static com.dpmpv92.ratecalculator.rate.model.DaysOfWeek.TUESDAY;
import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RateServiceTest {
    @Mock
    RateRepository rateRepository;

    @InjectMocks
    RateService rateService;

    @Test
    public void saveRate_covertsAndSavesToRepository() throws Exception {
        RateWindowRequest rateWindowRequest = new RateWindowRequest();
        rateWindowRequest.setDays("mon,tues");
        rateWindowRequest.setTimes("0100-1000");
        rateWindowRequest.setPrice(1500);

        RatesWindowRequest ratesWindowRequest = new RatesWindowRequest();
        ratesWindowRequest.setRates( asList(rateWindowRequest));
        RateWindow expectRate = RateWindow
                .builder()
                .price(1500)
                .startTime(new LocalTime(01,00))
                .endTime(new LocalTime(10,00))
                .daysOfWeekList(asList(MONDAY,TUESDAY))
                .build();

        rateService.saveRate(ratesWindowRequest);

        verify(rateRepository).save(asList(expectRate));
    }

}