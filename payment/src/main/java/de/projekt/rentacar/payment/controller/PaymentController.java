package de.projekt.rentacar.payment.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.projekt.rentacar.payment.PaymentApplication;
import de.projekt.rentacar.payment.model.Payment;
import de.projekt.rentacar.payment.services.PaymentService;

@Component
@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PaymentController {
private static final Logger LOGGER = LogManager.getLogger(PaymentApplication.class);

	
	@Autowired
    PaymentService paymentservice;
	
	
	@GetMapping("")
	public List<Payment> getAllPayment() {
		LOGGER.info("Liste Alle Payment");
	    return paymentservice.findAllPayment();
	}

	
	@GetMapping("{id}")
	public Payment getPayment(@PathVariable int id) {
		LOGGER.info("Info fuer das Auto ID: "+id);
	    return paymentservice.findById(id);
	}

	@PostMapping("")
	public ResponseEntity<?> addPayment(@RequestBody Payment p) {
		String message;
	    if(p != null) {
	    	paymentservice.insert(p);
	        message = "Added a Payment";
			//LOGGER.info("Added a Payment Auto"+p.idauto+" IDMiete "+p.idmieten);
	       
	        return ResponseEntity.status(HttpStatus.OK).body(message);
	    } else {
	        message = "Request does not contain a body";
	        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
	    }
	}

	@DeleteMapping("{id}")
	public String deletePayment(@PathVariable("id") int idauto) {

	    if(idauto > 0) {
	        if(paymentservice.delete(idauto)) {
	            return "Deleted the Auto.";
	        } else {
	            return "Cannot delete the Auto.";
	        }
	    }
	    return "The id is invalid for the Auto.";
	}

	@PutMapping("")
	public String updatePayment(@RequestBody Payment p) {
	    if(p != null) {
	    	paymentservice.update(p);
	        LOGGER.info("Update a Auto"+p.idauto+" Verfugbar? "+p.idmieten);
	        return "Updated Auto.";
	    } else {
	        return "Request does not contain a body";
	    }
	}



}
