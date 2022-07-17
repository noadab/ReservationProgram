package com.noadab.ReservationProgram.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.REMOVE,CascadeType.REFRESH} )
    private Client client;
    private int diners;
    @Column(name="reservation_time")
    private LocalTime reservationTime;
    @Column(name="reservation_end_time")
    private LocalTime reservationEndTime;
    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;
    @ManyToOne(fetch=FetchType.EAGER,cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="table_number")
    private com.noadab.ReservationProgram.entities.Table table;
    @ManyToOne(fetch=FetchType.EAGER,cascade = {CascadeType.MERGE, CascadeType.DETACH,  CascadeType.REFRESH})
    @JoinColumn(name="service_day")
    private Service service;



}
