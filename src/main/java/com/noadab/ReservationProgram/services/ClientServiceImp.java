package com.noadab.ReservationProgram.services;

import com.noadab.ReservationProgram.entities.*;
import com.noadab.ReservationProgram.exception.ErrMsg;
import com.noadab.ReservationProgram.exception.ReservationSysException;
import com.noadab.ReservationProgram.repositories.OrderRepository;
import com.noadab.ReservationProgram.repositories.ServiceRepository;
import com.noadab.ReservationProgram.repositories.TableRepository;
import com.noadab.ReservationProgram.utils.Arg;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

@org.springframework.stereotype.Service
public class ClientServiceImp implements ClientService{

    @Autowired
    ServiceRepository serviceRepository;
    @Autowired
    TableRepository tableRepository;
    @Autowired
    OrderRepository orderRepository;

    @Override
    public void addReserve(Client client, int diners, LocalTime reservationTime, LocalDate serviceDay) throws ReservationSysException{

        Service service= serviceRepository.findById(serviceDay).orElse(serviceRepository.save(Service.builder().serviceDay(serviceDay).build()));
        Table table=null;

        double minTime=Arg.TIME_OF_RESERVATION*2;

        List<Table> tables=tableRepository.findByTableType(TableType.valueOfDinerNum(diners));
        for (Table t:tables){
            List<Order> orders=orderRepository.findByTableAndService(t,service);
            Collections.sort(orders,(o1, o2) -> o1.getReservationTime().compareTo(o2.getReservationTime()));
            double lostTime=helperAddReserve(orders,reservationTime);
            if (lostTime==0){
                table=t;
                break;
            }
            if(minTime>lostTime){
                minTime=lostTime;
                table=t;
            }
        };
        if (table==null){
            throw new ReservationSysException(ErrMsg.DOES_NOT_HAVE_A_PLACE_FOR_RESERVE);
        }

        orderRepository.save(Order.builder()
                .client(client)
                .reservationTime(reservationTime)
                .reservationEndTime(reservationTime.plusHours(Arg.TIME_OF_RESERVATION_MILLY_SECOND))
                .diners(diners)
                .service(service)
                .table(table)
                .orderStatus(OrderStatus.CONFIRMED)
                .build());

    }

    public double helperAddReserve (List <Order> orders, LocalTime reservationTime) {
        if (Arg.OPEN_CLOCK_TIME.isAfter(reservationTime)){
            System.out.println("We are close");
            return Arg.TIME_OF_RESERVATION*2;
        }
        if (!Arg.OPEN_CLOCK_TIME.isAfter(reservationTime) &&
            !orders.get(0).getReservationTime().isBefore(reservationTime.plusHours(Arg.TIME_OF_RESERVATION))) {
            System.out.println("I AM HERE");
            return lostTimeToFirstReserve(orders.get(0),reservationTime);
        }
        for (int i = 0; i < (orders.toArray().length-1); i++) {
            if (!(orders.get(i).getReservationEndTime().isAfter(reservationTime)) &&
                !(orders.get(i + 1).getReservationTime().isBefore(reservationTime.plusHours(Arg.TIME_OF_RESERVATION)))) {
                return lostTimeBetweenTwoOrders(orders.get(i),orders.get(i+1),reservationTime);
            }
        }
        return Arg.TIME_OF_RESERVATION*2;
    }

    private double lostTimeBetweenTwoOrders(Order o1,Order o2, LocalTime reservationTime){
        Long timeBeforeReserve=o1.getReservationEndTime().until(reservationTime, ChronoUnit.MINUTES);
        Long timeAfterReserve=reservationTime.plusHours(Arg.TIME_OF_RESERVATION).until(o2.getReservationTime(),ChronoUnit.MINUTES);
        double lostTime= lostTimeHelper(timeBeforeReserve)+ lostTimeHelper(timeAfterReserve);
        return lostTime;
    }

    private double lostTimeToFirstReserve(Order o1, LocalTime reservationTime){
        Long timeBeforeReserve=Arg.OPEN_CLOCK_TIME.until(reservationTime, ChronoUnit.MINUTES);
        Long timeAfterReserve=reservationTime.plusHours(Arg.TIME_OF_RESERVATION).until(o1.getReservationTime(),ChronoUnit.MINUTES);
        double lostTime= lostTimeHelper(timeBeforeReserve)+ lostTimeHelper(timeAfterReserve);
        return lostTime;
    }

    private double lostTimeHelper(long minutes){
        return (double)minutes/60%Arg.TIME_OF_RESERVATION;
    }
}

