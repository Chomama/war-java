package com.ryan.war;

import com.ryan.war.player.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = PlayerRepository.class)
public class WarApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(WarApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WarApplication.class, args);
	}
}
