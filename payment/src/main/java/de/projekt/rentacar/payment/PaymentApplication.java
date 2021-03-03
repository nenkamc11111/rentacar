package de.projekt.rentacar.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class PaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentApplication.class, args);
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
