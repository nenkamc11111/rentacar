package de.rentacar.projekt.autos.controller;

import de.rentacar.projekt.autos.AutosApplication;
import de.rentacar.projekt.autos.model.*;
import de.rentacar.projekt.autos.services.AutoService;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Component
@RestController
@RequestMapping("/auto")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AutoController {
	
    private static final Logger LOGGER = LogManager.getLogger(AutosApplication.class);

	
	@Autowired
    AutoService autoService;
	
	private final FileService fileService = new FileService();
	
	@GetMapping("")
	public List<Auto> getAllAutos() {
		LOGGER.info("Liste Alle Kunde");
	    return autoService.findAllAuto();
	}

	
	@GetMapping("{id}")
	public Auto getAuto(@PathVariable int id) {
		LOGGER.info("Info fuer das Auto ID: "+id);
	    return autoService.findById(id);
	}

	@PostMapping("")
	public ResponseEntity<?> addAuto(@RequestBody Auto auto) {
		String message;
	    if(auto != null) {
	        autoService.insert(auto);
	        message = "Added a Auto";
			LOGGER.info("Added a Auto"+auto.mark+" KM-Stand "+auto.kmstand);
	        System.out.print("--------------IF----------"+auto.getFrei());
	       
	        return ResponseEntity.status(HttpStatus.OK).body(message);
	    } else {
	        message = "Request does not contain a body";
	        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
	    }
	}

	@DeleteMapping("{id}")
	public String deleteAuto(@PathVariable("id") int idauto) {

	    if(idauto > 0) {
	        if(autoService.delete(idauto)) {
	            return "Deleted the Auto.";
	        } else {
	            return "Cannot delete the Auto.";
	        }
	    }
	    return "The id is invalid for the Auto.";
	}

	@PutMapping("")
	public String updateAuto(@RequestBody Auto auto) {
	    if(auto != null) {
	        autoService.update(auto);
	        LOGGER.info("Update a Auto"+auto.idauto+" Verfugbar? "+auto.frei);
	        return "Updated Auto.";
	    } else {
	        return "Request does not contain a body";
	    }
	}
	
	@PatchMapping("{id}")
	public String updateAuto(@PathVariable("id") int idauto) {
	    if(idauto != 0) {
	    	 Auto a = autoService.findById(idauto);
	    	    // crush the variables of the object found
	    	    a.setFrei(0); 
	    	    autoService.insert(a);
	        
	        return "Updated Auto.";
	    } else {
	        return "Request does not contain a body";
	    }
	}
	
	@GetMapping("search")
	public List<Auto> getAutoFreieAuto() {
		LOGGER.info("Suche verfugbare  Auto");
	    return autoService.findAllFreieAuto();
	}

}

