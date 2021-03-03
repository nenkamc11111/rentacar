package de.rentacar.projekt.autos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.rentacar.projekt.autos.model.*;
import de.rentacar.projekt.autos.repositories.AutoRepository;

@Service
public class AutoService implements AutoServiceI {
	@Autowired
	private AutoRepository repository;

	@Override
	public List<Auto> findAllAuto() {
	    return (List<Auto>)repository.findAll();
	}
	
	@Override
	public List<Auto> findAllFreieAuto(){
	    return (List<Auto>)repository.findAllFreieAuto();
	}
	
	@Override
	public Auto insert(Auto p) {
	    return repository.save(p);
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
	public Auto findById(int id) {
	    Optional<Auto> result = repository.findById(id);
	    if (result.isPresent()) {
	        return result.get();
	    } else {
	        return null;
	    }
	}

	@Override
	public boolean update(Auto p) {
	    try {
	        repository.save(p);
	        return true;
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	        return false;
	    }
	}
}
