package com.dpmpv92.ratecalculator.rate.controller;

import com.dpmpv92.ratecalculator.rate.model.RatesWindowRequest;
import com.dpmpv92.ratecalculator.rate.service.RateService;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RateControllerTest {

    @Mock
    RateService rateService;

    @InjectMocks
    RateController rateController;

    @Test
    public void getRate() throws Exception {
        DateTime fromDate = new DateTime().minus(10);
        DateTime toDate = new DateTime();

        rateController.getRate(fromDate, toDate);

        verify(rateService).getRate(fromDate, toDate);
    }

    @Test
    public void saveRates_delegatesToService() throws Exception {
        RatesWindowRequest rateWindowRequest = Mockito.mock(RatesWindowRequest.class);

        rateController.saveRates(rateWindowRequest);

        verify(rateService).saveRate(rateWindowRequest);
    }
}