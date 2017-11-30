package com.dpmpv92.ratecalculator.rate.controller;

import com.dpmpv92.ratecalculator.rate.model.RateWindowRequest;
import com.dpmpv92.ratecalculator.rate.model.RatesWindowRequest;
import com.dpmpv92.ratecalculator.rate.service.RateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RateControllerTest {

    @Mock
    RateService rateService;

    @InjectMocks
    RateController rateController;

    @Test
    public void getRate() throws Exception {
    }

    @Test
    public void saveRates_delegatesToService() throws Exception {
        RatesWindowRequest rateWindowRequest = Mockito.mock(RatesWindowRequest.class);

        rateController.saveRates(rateWindowRequest);

        verify(rateService).saveRate(rateWindowRequest);
    }

}