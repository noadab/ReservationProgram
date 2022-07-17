package com.noadab.ReservationProgram;

import com.noadab.ReservationProgram.entities.Client;
import com.noadab.ReservationProgram.entities.Order;
import com.noadab.ReservationProgram.entities.Table;
import com.noadab.ReservationProgram.entities.TableType;
import com.noadab.ReservationProgram.repositories.OrderRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class ReservationProgramApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationProgramApplication.class, args);

	}


}
