package com.nicoarbio.cardealership.vehiclemodel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan("com.nicoarbio.cardealership")
public class VehicleModelServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleModelServiceApplication.class, args);
	}

}
