package com.noadab.ReservationProgram.utils;

import java.time.LocalTime;

public class Arg {

    public static final int TIME_OF_RESERVATION=2;
    public static final int TIME_OF_RESERVATION_MILLY_SECOND=1000*60*60*TIME_OF_RESERVATION;// 2 hours in milly seconds
    public static final LocalTime OPEN_CLOCK_TIME=LocalTime.of(12,00);
}
