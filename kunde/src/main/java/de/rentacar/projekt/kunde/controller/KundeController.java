package de.rentacar.projekt.kunde.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.rentacar.projekt.kunde.KundeApplication;
import de.rentacar.projekt.kunde.model.Kunde;
import de.rentacar.projekt.kunde.services.KundeService;

@Component
@RestController
@RequestMapping("/kunde")
@CrossOrigin(origins = "*", maxAge = 3600)
public class KundeController {

    private static final Logger LOGGER = LogManager.getLogger(KundeApplication.class);

	@Autowired
    KundeService kundeService;
	
	@GetMapping("")
	public List<Kunde> getAllKunde() {
		LOGGER.info("Liste Alle Kunde");
	    return kundeService.findAllKunde();
	}
	

	@GetMapping("{id}")
	public Kunde getKunde(@PathVariable int id) {
		LOGGER.info("Info für den Kunde ID"+id);
	    return kundeService.findById(id);
	}

	@PostMapping("")
	public ResponseEntity<String> addKunde(@RequestBody Kunde kunde) {
		String message;
	    if(kunde != null) {
	        kundeService.insert(kunde);
	        message = "Added a Kunde";
			LOGGER.info("Neuer Kunde ID"+kunde.idkunde+" Email: "+kunde.email+" Name "+kunde.name);

	        return ResponseEntity.status(HttpStatus.OK).body(message);
	    } else {
	        message = "Request does not contain a body";
	        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
	    }
	}

	@DeleteMapping("{id}")
	public String deleteKunde(@PathVariable("idkunde") int idkunde) {

	    if(idkunde > 0) {
	        if(kundeService.delete(idkunde)) {
	            return "Deleted the Kunde.";
	        } else {
	            return "Cannot delete the Kunde.";
	        }
	    }
	    return "The id is invalid for the Kunde.";
	}

	@PutMapping("{id}")
	public String updateKunde(@RequestBody Kunde auto) {
	    if(auto != null) {
	        kundeService.update(auto);
	        return "Updated Kunde.";
	    } else {
	        return "Request does not contain a body";
	    }
	}
}
