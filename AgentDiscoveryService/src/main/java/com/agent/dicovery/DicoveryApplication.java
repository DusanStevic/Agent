package com.agent.dicovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DicoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DicoveryApplication.class, args);
	}

}
