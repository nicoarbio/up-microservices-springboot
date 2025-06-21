package com.nicoarbio.cardealership.branch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableDiscoveryClient
@SpringBootApplication
public class BranchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BranchServiceApplication.class, args);
	}

}
