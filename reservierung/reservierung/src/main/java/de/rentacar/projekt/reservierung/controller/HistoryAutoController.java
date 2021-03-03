package de.rentacar.projekt.reservierung.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import de.rentacar.projekt.reservierung.model.HistoryAuto;
import de.rentacar.projekt.reservierung.services.HistoryAutoService;


@Component
@RestController
@RequestMapping("/historyauto")
@CrossOrigin(origins = "*", maxAge = 3600)
public class HistoryAutoController {
	@Autowired
    HistoryAutoService historyAutoService;
	
	@GetMapping("")
	public List<HistoryAuto> getAllHistoryAuto() {
	    return historyAutoService.findAllHistoryAuto();
	}

	@GetMapping("{id}")
	public HistoryAuto getHistoryAuto(@PathVariable int id) {
	    return historyAutoService.findById(id);
	}

	@PostMapping("")
	public String addHistoryAuto(@RequestBody HistoryAuto auto) {

	    if(auto != null) {
	        historyAutoService.insert(auto);
	        return "Added a HistoryAuto";
	    } else {
	        return "Request does not contain a body";
	    }
	}

	@DeleteMapping("{id}")
	public String deleteHistoryAuto(@PathVariable("idhistoryAuto") int idhistoryAuto) {

	    if(idhistoryAuto > 0) {
	        if(historyAutoService.delete(idhistoryAuto)) {
	            return "Deleted the HistoryAuto.";
	        } else {
	            return "Cannot delete the HistoryAuto.";
	        }
	    }
	    return "The id is invalid for the HistoryAuto.";
	}

	@PutMapping("")
	public String updateHistoryAuto(@RequestBody HistoryAuto auto) {
	    if(auto != null) {
	        historyAutoService.update(auto);
	        return "Updated HistoryAuto.";
	    } else {
	        return "Request does not contain a body";
	    }
	}

}
