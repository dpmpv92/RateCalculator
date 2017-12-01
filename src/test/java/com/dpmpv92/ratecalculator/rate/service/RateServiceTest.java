package com.dpmpv92.ratecalculator.rate.service;

import com.dpmpv92.ratecalculator.rate.model.Rate;
import com.dpmpv92.ratecalculator.rate.model.RateWindow;
import com.dpmpv92.ratecalculator.rate.model.RateWindowRequest;
import com.dpmpv92.ratecalculator.rate.model.RatesWindowRequest;
import com.dpmpv92.ratecalculator.rate.repository.RateRepository;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.dpmpv92.ratecalculator.rate.model.DaysOfWeek.MONDAY;
import static com.dpmpv92.ratecalculator.rate.model.DaysOfWeek.TUESDAY;
import static java.util.Arrays.asList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        ratesWindowRequest.setRates(asList(rateWindowRequest));
        RateWindow expectRate = RateWindow
                .builder()
                .price(1500)
                .startTime(new LocalTime(01, 00))
                .endTime(new LocalTime(10, 00))
                .daysOfWeekList(asList(MONDAY, TUESDAY))
                .build();

        rateService.saveRate(ratesWindowRequest);

        verify(rateRepository).save(asList(expectRate));
    }

    @Test
    public void getRate_callsRepo() {
        DateTime fromDate = new DateTime().minus(10);
        DateTime toDate = new DateTime();

        rateService.getRate(fromDate, toDate);

        verify(rateRepository).find();
    }

    @Test
    public void getRate_findRateWithinWindow() {
        DateTime startTime = new DateTime(2017, 11, 27, 4, 0);
        DateTime endTime = new DateTime(2017, 11, 27, 5, 0);
        Rate expectedRate = new Rate();
        expectedRate.setRate(100);
        expectedRate.setRateFound(true);

        RateWindow rateWindow = RateWindow.builder()
                .daysOfWeekList(asList(MONDAY, TUESDAY))
                .startTime(new LocalTime(3, 00))
                .endTime(new LocalTime(6, 0))
                .price(100)
                .build();
        when(rateRepository.find()).thenReturn(asList(rateWindow));

        Rate rate = rateService.getRate(startTime, endTime);
        assertThat(rate).isEqualTo(expectedRate);
    }

    @Test
    public void getRate_findRateOutsideWindow() {
        DateTime startTime = new DateTime(2017, 11, 27, 2, 0);
        DateTime endTime = new DateTime(2017, 11, 27, 4, 0);
        Rate expectedRate = new Rate();
        expectedRate.setRateFound(false);

        RateWindow rateWindow = RateWindow.builder()
                .daysOfWeekList(asList(MONDAY, TUESDAY))
                .startTime(new LocalTime(12, 00))
                .endTime(new LocalTime(14, 0))
                .price(100)
                .build();
        when(rateRepository.find()).thenReturn(asList(rateWindow));

        Rate rate = rateService.getRate(startTime, endTime);
        assertThat(rate).isEqualTo(expectedRate);
    }

    @Test
    public void getRate_findRateHalfInsideHalfOutsideWindow() {
        DateTime startTime = new DateTime(2017, 11, 27, 2, 0);
        DateTime endTime = new DateTime(2017, 11, 27, 18, 0);
        Rate expectedRate = new Rate();
        expectedRate.setRateFound(false);

        RateWindow rateWindow = RateWindow.builder()
                .daysOfWeekList(asList(MONDAY, TUESDAY))
                .startTime(new LocalTime(12, 00))
                .endTime(new LocalTime(14, 0))
                .price(100)
                .build();
        when(rateRepository.find()).thenReturn(asList(rateWindow));

        Rate rate = rateService.getRate(startTime, endTime);
        assertThat(rate).isEqualTo(expectedRate);
    }


}