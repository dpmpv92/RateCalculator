package com.dpmpv92.ratecalculator.rate.model;

import javafx.event.EventType;
import lombok.Getter;

public enum DaysOfWeek {
    MONDAY("mon",1),
    TUESDAY("tues",2),
    WEDENSDAY("wed",3),
    THURSDAY("thurs",4),
    FIRDAY("fri",5),
    SATURDAY("sat",6),
    SUNDAY("sun",7);

    @Getter
    private String name;
    @Getter
    private int dayOfWeek;

    DaysOfWeek(String name, int dayOfWeek){
        this.name=name;
        this.dayOfWeek=dayOfWeek;
    }
    public static DaysOfWeek forValue(String day){
        for ( DaysOfWeek daysOfWeek : DaysOfWeek.values()){
            if ( daysOfWeek.getName().equals(day)){
                return daysOfWeek;
            }
        }
        return null;
    }


}
