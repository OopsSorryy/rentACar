package com.yunusahmet.rentacar;



import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.IOException;


@EntityScan("com/yunusahmet/rentacar/entity")
@SpringBootApplication
public class RentACarApplication implements CommandLineRunner {

	//private final BCryptPasswordEncoder encoder;
	//private final CustomerDao customerDao;

//	public RentACarApplication(BCryptPasswordEncoder encoder, CustomerDao customerDao) {
//		//this.encoder = encoder;
//		this.customerDao = customerDao;
//
//	}

	public static void main(String[] args) throws IOException {

		SpringApplication.run(RentACarApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
//		Customer customer = new Customer(15,"Ahmet","Dayı","1289@gmail.com",encoder.encode("ahmetDayi*"),encoder.encode("ahmetDayi*"), Role.ADMIN);
//		customerDao.save(customer);
	}
}
