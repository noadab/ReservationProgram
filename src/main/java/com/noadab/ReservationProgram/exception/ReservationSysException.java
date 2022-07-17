package com.noadab.ReservationProgram.exception;

public class ReservationSysException extends Exception {

    public  ReservationSysException (ErrMsg errMsg){
        super(errMsg.getDesc());
    }
}
