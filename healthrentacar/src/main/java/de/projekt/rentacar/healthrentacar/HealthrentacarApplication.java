package de.projekt.rentacar.healthrentacar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@Configuration
@EnableAutoConfiguration
@EnableAdminServer
@SpringBootApplication
public class HealthrentacarApplication {

    private static final Logger LOGGER = LogManager.getLogger(HealthrentacarApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(HealthrentacarApplication.class, args);
		LOGGER.info("Start Monitoring Server ....");
	}

}
