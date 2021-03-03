package de.rentacar.projekt.reservierung.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.rentacar.projekt.reservierung.model.Reservierung;
import de.rentacar.projekt.reservierung.services.ReservierungService;

@RestController
@RequestMapping("/reservierung")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ReservierungController {
	@Autowired
    ReservierungService reservierungService;
	
	@GetMapping("")
	public List<Reservierung> getAllReservierung() {

	    return reservierungService.findAllReservierung();
	}

	@GetMapping("{id}")
	public Reservierung getReservierung(@PathVariable int id) {
	    return reservierungService.findById(id);
	}

	@PostMapping("")
	public String addReservierung(@RequestBody Reservierung auto) {

	    if(auto != null) {
	        reservierungService.insert(auto);
	        return "Added a Reservierung";
	    } else {
	        return "Request does not contain a body";
	    }
	}

	@DeleteMapping("{id}")
	public String deleteReservierung(@PathVariable("idreservierung") int idreservierung) {

	    if(idreservierung > 0) {
	        if(reservierungService.delete(idreservierung)) {
	            return "Deleted the Reservierung.";
	        } else {
	            return "Cannot delete the Reservierung.";
	        }
	    }
	    return "The id is invalid for the Reservierung.";
	}

	@PutMapping("")
	public String updateReservierung(@RequestBody Reservierung r) {
	    if(r != null) {
	        reservierungService.update(r);
	        return "Updated Reservierung.";
	    } else {
	        return "Request does not contain a body";
	    }
	}

}
