package com.noadab.ReservationProgram.exception;

public enum ErrMsg {

    ID_ALREADY_EXIST("Id already exist"),
    ID_NOT_FOUND("Id not found"),
    NOT_HAVE_RESERVATIONS("There is no reservations"),
    DOES_NOT_HAVE_A_PLACE_FOR_RESERVE("We are full, try to do other reservation");

    private String desc;

    ErrMsg(String desc){
        this.desc=desc;
    }

    public String getDesc() {
        return desc;
    }
}
