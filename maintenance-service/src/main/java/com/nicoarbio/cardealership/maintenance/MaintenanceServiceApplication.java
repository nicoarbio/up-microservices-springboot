package com.nicoarbio.cardealership.maintenance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class MaintenanceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaintenanceServiceApplication.class, args);
	}

}
