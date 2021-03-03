package de.rentacar.projekt.kunde;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@SpringBootApplication
public class KundeApplication {
	
    private static final Logger LOGGER = LogManager.getLogger(KundeApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(KundeApplication.class, args);
		LOGGER.info("Start Kunde MicroService Application ...");
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
