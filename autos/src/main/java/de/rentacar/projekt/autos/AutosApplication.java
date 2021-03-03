package de.rentacar.projekt.autos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class AutosApplication {
    private static final Logger LOGGER = LogManager.getLogger(AutosApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(AutosApplication.class, args);
		LOGGER.info("Start Autos MicroService ...");
	}
	@Configuration
	 public static class SecurityPermitAllConfig extends WebSecurityConfigurerAdapter {
	     @Override
	     protected void configure(HttpSecurity http) throws Exception {
	         http.authorizeRequests().anyRequest().permitAll()  
	             .and().csrf().disable();
	     }
	 }

}
