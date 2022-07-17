package com.noadab.ReservationProgram.repositories;

import com.noadab.ReservationProgram.entities.Table;
import com.noadab.ReservationProgram.entities.TableType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TableRepository extends JpaRepository<Table,Integer> {

    List<Table> findByTableType(TableType tableType);
}
