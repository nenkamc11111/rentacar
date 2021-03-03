package de.rentacar.projekt.reservierung;

import org.springframework.boot.SpringApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@SpringBootApplication
public class ReservierungApplication {

    private static final Logger LOGGER = LogManager.getLogger(ReservierungApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ReservierungApplication.class, args);
		LOGGER.info("Start Rent A Car Application ");
        
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
