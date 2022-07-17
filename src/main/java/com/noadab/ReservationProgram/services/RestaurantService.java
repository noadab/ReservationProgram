package com.noadab.ReservationProgram.services;

import com.noadab.ReservationProgram.entities.Client;
import com.noadab.ReservationProgram.entities.Order;
import com.noadab.ReservationProgram.entities.Service;
import com.noadab.ReservationProgram.entities.Table;
import com.noadab.ReservationProgram.exception.ReservationSysException;

import java.time.LocalDate;
import java.util.List;


public interface RestaurantService {


    List<Service> getAllReservations() throws ReservationSysException;

    Service getAllReservationsByOneDay(LocalDate date) throws ReservationSysException;

    List<Table> getAllTable();

    List <Order> getAllReservationsByOneTable(Table table) throws ReservationSysException;

    void addTable(Table table) throws ReservationSysException;

    void canceledReserve(Order order) throws ReservationSysException;

    void canceledReserve(Client client) throws ReservationSysException;

    void updateReserve (Order order) throws ReservationSysException;

}
