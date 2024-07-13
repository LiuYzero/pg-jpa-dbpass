package com.springbootwork.pg_jpa_dbpaas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
public class PgJpaDbpaasApplication {

	public static void main(String[] args) {
		SpringApplication.run(PgJpaDbpaasApplication.class, args);
	}

}
