package com.noadab.ReservationProgram.repositories;

import com.noadab.ReservationProgram.entities.Client;
import com.noadab.ReservationProgram.entities.Order;
import com.noadab.ReservationProgram.entities.Service;
import com.noadab.ReservationProgram.entities.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

    List<Order> findByTable(Table table);

    List<Order> findByTableAndService(Table table, Service service);

    Order findByClient(Client client);
}
