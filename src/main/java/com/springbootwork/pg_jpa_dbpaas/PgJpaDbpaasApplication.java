package com.springbootwork.pg_jpa_dbpaas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
//@ComponentScan(basePackages = "com.springbootwork.pg_jpa_dbpaas")
//@EnableJpaRepositories(basePackages = {"com.springbootwork.pg_jpa_dbpaas.repository", "com.springbootwork.pg_jpa_dbpaas.entity"})
//@EntityScan(basePackages = { "com.springbootwork.pg_jpa_dbpaas.entity" })
public class PgJpaDbpaasApplication {

	public static void main(String[] args) {
		SpringApplication.run(PgJpaDbpaasApplication.class, args);
	}

}
