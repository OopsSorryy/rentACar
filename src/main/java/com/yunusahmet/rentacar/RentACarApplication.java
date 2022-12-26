package com.yunusahmet.rentacar;



import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.yunusahmet.rentacar.dataAccess.CustomerDao;

import java.io.IOException;


@EntityScan("com/yunusAhmet/rentACar/entity")
@SpringBootApplication
@SecurityScheme(
		name = "Bearer Authentication",
		type = SecuritySchemeType.HTTP,
		bearerFormat = "JWT",
		scheme = "bearer")
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

	@Bean
	public OpenAPI customizeOpenAPI() {
		final String securitySchemeName = "bearerAuth";
		return new OpenAPI()
				.addSecurityItem(new SecurityRequirement()
						.addList(securitySchemeName))
				.components(new Components()
						.addSecuritySchemes(securitySchemeName, new io.swagger.v3.oas.models.security.SecurityScheme()
								.name(securitySchemeName)
								.type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP)
								.scheme("bearer")
								.bearerFormat("JWT")));
	}

}
