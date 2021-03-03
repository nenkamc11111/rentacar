package de.rentacar.projekt.reservierung.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.rentacar.projekt.reservierung.model.Mieten;
import de.rentacar.projekt.reservierung.repositories.MietenRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;

@Service
//@Slf4j
public class MietenService implements MietenServiceI{
	
	//private static final Logger log = org.slf4j.LoggerFactory.getLogger(MietenService.class);
	@Autowired
	private MietenRepository repository;

	@Override
	public List<Mieten> findAllMieten() {
		//log.info("ListMiete = {}" , "get alle gemietete Auto wurde aufgerufen");
	    return (List<Mieten>)repository.findAll();
	}

	@Override
	public Mieten insert(Mieten m) {
	    return repository.save(m);
	}

	@Override
	public boolean delete(int id) {
	    try {
	        repository.deleteById(id);
	        return true;
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	        return false;
	    }
	}

	@Override
	public Mieten findById(int id) {
	    Optional<Mieten> result = repository.findById(id);
	    if (result.isPresent()) {
	        return result.get();
	    } else {
	        return null;
	    }
	}

	@Override
	public boolean update(Mieten m) {
	    try {
	        repository.save(m);
	        return true;
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	        return false;
	    }
	}


}
