package com.noadab.ReservationProgram.services;

import com.noadab.ReservationProgram.entities.*;
import com.noadab.ReservationProgram.exception.ErrMsg;
import com.noadab.ReservationProgram.exception.ReservationSysException;
import com.noadab.ReservationProgram.repositories.ClientRepository;
import com.noadab.ReservationProgram.repositories.OrderRepository;
import com.noadab.ReservationProgram.repositories.ServiceRepository;
import com.noadab.ReservationProgram.repositories.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

@org.springframework.stereotype.Service
public class RestaurantServiceImp implements RestaurantService{


    @Autowired
    ServiceRepository serviceRepository;
    @Autowired
    TableRepository tableRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ClientRepository clientRepository;

    @Override
    public List<Service> getAllReservations() throws ReservationSysException {
        if (serviceRepository.findAll().isEmpty()){
            throw new ReservationSysException(ErrMsg.NOT_HAVE_RESERVATIONS);
        }
        return serviceRepository.findAll();
    }

    @Override
    public Service getAllReservationsByOneDay(LocalDate date) throws ReservationSysException {
        return serviceRepository.findById(date).orElseThrow(()->new ReservationSysException(ErrMsg.NOT_HAVE_RESERVATIONS));
    }

    @Override
    public List<Table> getAllTable() {
        return tableRepository.findAll();
    }

    @Override
    public List <Order> getAllReservationsByOneTable(Table table) throws ReservationSysException {
        if (orderRepository.findByTable(table).isEmpty()){
            throw new ReservationSysException(ErrMsg.NOT_HAVE_RESERVATIONS);
        }
        return orderRepository.findByTable(table);
    }

    @Override
    public void addTable(Table table) throws ReservationSysException {
        if (tableRepository.existsById(table.getTableNumber())){
            throw new ReservationSysException(ErrMsg.ID_ALREADY_EXIST);
        }
        tableRepository.save(table);
    }

    @Override
    public void canceledReserve(Order order) throws ReservationSysException {
        if (!orderRepository.existsById(order.getId())){
            throw new ReservationSysException(ErrMsg.ID_NOT_FOUND);
        }
        order.setTable(null);
        order.setOrderStatus(OrderStatus.CANCELED);
        orderRepository.delete(order);
    }

    @Override
    public void canceledReserve(Client client) throws ReservationSysException {
        if (!clientRepository.existsById(client.getId())){
            throw new ReservationSysException(ErrMsg.ID_NOT_FOUND);
        }
        canceledReserve(orderRepository.findByClient(client));
    }

    @Override
    public void updateReserve(Order order) throws ReservationSysException {
        if (!orderRepository.existsById(order.getId())){
            throw new ReservationSysException(ErrMsg.ID_NOT_FOUND);
        }
        orderRepository.saveAndFlush(order);

    }
}
