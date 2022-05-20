package ru.telegram.usersdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("ru.telegram.usersdb.model.entity")
@EnableJpaRepositories("ru.telegram.usersdb.repository")
@EnableEurekaClient
public class UsersDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersDbApplication.class, args);
	}

}
