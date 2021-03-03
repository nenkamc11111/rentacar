package de.rentacar.projekt.reservierung.controller;



import java.net.URI;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import de.rentacar.projekt.reservierung.ReservierungApplication;
import de.rentacar.projekt.reservierung.model.Mieten;
import de.rentacar.projekt.reservierung.services.MietenService;

@RestController
@RequestMapping("/mieten")
@CrossOrigin(origins = "*", maxAge = 3600)
public class MietenController {
	
    private static final Logger LOGGER = LogManager.getLogger(ReservierungApplication.class);

	@Autowired
    MietenService mietenService;
	
	@GetMapping("")
	public List<Mieten> getAllMieten() {
		LOGGER.info("List Alle Vermietete Autos mit Kunde wurde aufgerufen");
	    return mietenService.findAllMieten();
	}

	@GetMapping("{id}")
	public Mieten getMieten(@PathVariable int id) {
		
	    return mietenService.findById(id);
	}

	@PostMapping("")
	public String addMieten(@RequestBody Mieten m) {
		LOGGER.info("Miete Auto:  "+m.idauto+"  Kunde: "+m.idkunde+" Von: "+m.abholdatum+" bis "+m.ruckgabedatum);
        
	    mietenService.insert(m);
	    RestTemplate restTemplate = new RestTemplate();
	    
	    long anzahlTag = ChronoUnit.DAYS.between(LocalDate.parse("2021-02-10"),LocalDate.parse("2021-02-19"));
	    
	    try {
			
			JSONObject request = new JSONObject();
			request.put("idauto",m.idauto);
			request.put("frei", 0);
			final String baseUrlPayment = "http://localhost:8084/payment";
		    
			URI uri = new URI("http://localhost:8082/auto/"+m.idauto);
			
			int id = 1000;
	         
	        URI uriAuto = new URI("http://localhost:8082/auto/");
	        URI uriKunde = new URI("http://localhost:8081/kunde/");
	        
	        List<Integer> listAutArray = new ArrayList<Integer>();
	        List<Integer> listKundeArray = new ArrayList<Integer>();
	     
	        ResponseEntity<String> resultAllAuto = restTemplate.getForEntity(uriAuto, String.class);
	        ResponseEntity<String> resultAllKunde = restTemplate.getForEntity(uriKunde, String.class);

	        //Verify request succeed
			JSONArray objAuto = new JSONArray(resultAllAuto.getBody());
			JSONArray objKunde = new JSONArray(resultAllKunde.getBody());

			
			for(int i=0; i < objAuto.length(); i++) {
				JSONObject listAuto = objAuto.getJSONObject(i);
				listAutArray.add(listAuto.getInt("idauto"));
			}
		     
			for(int i=0; i < objKunde.length(); i++) {
				JSONObject listK = objKunde.getJSONObject(i);
				listKundeArray.add(listK.getInt("idkunde"));				
			}
			
			if(!listAutArray.contains(m.idauto)) {
				LOGGER.info(m.idauto+" existiert nicht ! ");
				return "Auto ID: "+m.idauto+" existiert nicht ! ";
			}
			if(!listKundeArray.contains(m.idkunde)) {
				LOGGER.info(m.idkunde+" existiert nicht ! ");
				return "Kunde ID: "+m.idkunde+" existiert nicht ! ";
			}
			
	        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
	        //Verify request succeed
			JSONObject obj = new JSONObject(result.getBody());
			double preis = obj.getInt("preis");
			
			JSONObject payment = new JSONObject();
			payment.put("idauto",m.idauto);
			payment.put("idmieten",m.idmieten);
			payment.put("idkunde", m.idkunde);
			payment.put("preis",anzahlTag*preis);
			payment.put("ruckgabedatum", m.ruckgabedatum);
			payment.put("abholdatum", m.abholdatum);
			
			LOGGER.info("Auto "+m.idauto+"Preis "+preis+"MieteID "+m.idmieten+"Kunde "+m.idkunde+"Anzahl "+anzahlTag);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> entity = new HttpEntity<>(request.toString(), headers);
			HttpEntity<String> entityP = new HttpEntity<>(payment.toString(), headers);
			RestTemplate rest = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
			ResponseEntity<String> response = rest.exchange(uri, HttpMethod.PATCH, entity, String.class);
			ResponseEntity<String> resultPayment = rest.exchange(baseUrlPayment, HttpMethod.POST, entityP, String.class);
			LOGGER.info("Auto "+m.idauto+" ist  nun besetzt");
			
		} catch (Exception e) {

	        LOGGER.error("Fehler bei Update verfuegbare Autos"+e.getMessage());
			System.out.print("ERROR-- "+e.getMessage());
		}
	    
	    System.out.print("--------------------ID-AUTO----------"+m.idauto);
	    return "Der Kunde: "+m.idkunde+" hat das Auto: "+m.idauto+"verietet";
	}

	@DeleteMapping("{id}")
	public String deleteMieten(@PathVariable("idmieten") int idmieten) {

	    if(idmieten > 0) {
	        if(mietenService.delete(idmieten)) {
	            return "Deleted the Mieten.";
	        } else {
	            return "Cannot delete the Mieten.";
	        }
	    }
	    return "The id is invalid for the Mieten.";
	}

	@PutMapping("{id}")
	public String updateMieten(@RequestBody Mieten auto) {
	    if(auto != null) {
	        mietenService.update(auto);
	        return "Updated Mieten.";
	    } else {
	        return "Request does not contain a body";
	    }
	}

}
