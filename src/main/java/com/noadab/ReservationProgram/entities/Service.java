package com.noadab.ReservationProgram.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@javax.persistence.Table(name = "Services")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Service {

    @Id
    @Column(name = "service_day")
    private LocalDate serviceDay;
    @OneToMany(mappedBy = "service", fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.REMOVE,CascadeType.REFRESH})
    @Singular
    @JsonIgnore
    private List<Order> orders=new ArrayList<>();

    @Override
    public String toString() {
        return "Service{" +
                "serviceDay=" + serviceDay +
                '}';
    }

//    private final int OPENING_TIME= 18 ;
//    private final int CLOSING_TIME= 23;
//    private final int RESERVATION_DURING_TIME= 1000*60*60*2; // two hours

}
