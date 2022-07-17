package com.noadab.ReservationProgram.services;

import com.noadab.ReservationProgram.entities.Client;
import com.noadab.ReservationProgram.entities.Order;
import com.noadab.ReservationProgram.exception.ReservationSysException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ClientService {

    void addReserve(Client client, int diners, LocalTime reservationTime, LocalDate serviceDay) throws ReservationSysException;

    double helperAddReserve (List<Order> orders, LocalTime reservationTime);
}
