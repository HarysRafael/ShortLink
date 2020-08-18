package com.shortlink;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ShortLinkMongoApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();

	}

	public static void main(String[] args) {
		SpringApplication.run(ShortLinkMongoApplication.class, args);
	}

}
