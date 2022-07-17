package com.noadab.ReservationProgram.repositories;

import com.noadab.ReservationProgram.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, LocalDate> {

}
