package com.dpmpv92.ratecalculator.rate.model;

import lombok.Builder;
import lombok.Data;
import org.joda.time.LocalTime;

import java.util.List;

@Data
@Builder
public class RateWindow {
    LocalTime startTime;
    LocalTime endTime;
    List<DaysOfWeek> daysOfWeekList;
    int price;

}
