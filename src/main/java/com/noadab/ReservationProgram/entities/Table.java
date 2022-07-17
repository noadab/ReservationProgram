package com.noadab.ReservationProgram.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@javax.persistence.Table(name = "Tables")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Table {

    @Id
    @Column(name="table_number")
    private int tableNumber;
    @Enumerated(value = EnumType.STRING)
    @Column(name="table_type")
    private TableType tableType;
    @OneToMany(mappedBy = "table", cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.REMOVE,CascadeType.REFRESH} , fetch = FetchType.EAGER)
    @Singular
    @JsonIgnore
    private List<Order> orders=new ArrayList<>();

    @Override
    public String toString() {
        return "Table{" +
                "tableNumber=" + tableNumber +
                ", tableType=" + tableType +
                '}';
    }
}
