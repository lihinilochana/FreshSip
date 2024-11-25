package com.freshsip.freshsip;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

public class FreshsipApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreshsipApplication.class, args);
	}
	@Bean
	public ModelMapper modalMapper(){
		return new ModelMapper();
	}
}
