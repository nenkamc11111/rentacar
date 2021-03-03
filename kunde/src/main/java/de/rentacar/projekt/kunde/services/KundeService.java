package de.rentacar.projekt.kunde.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.rentacar.projekt.kunde.model.Kunde;
import de.rentacar.projekt.kunde.repositories.KundeRepository;

@Service
public class KundeService implements KundeServiceI{
	@Autowired
	private KundeRepository repository;

	@Override
	public List<Kunde> findAllKunde() {
	    return (List<Kunde>)repository.findAll();
	}

	@Override
	public Kunde insert(Kunde p) {
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
	public Kunde findById(int id) {
	    Optional<Kunde> result = repository.findById(id);
	    if (result.isPresent()) {
	        return result.get();
	    } else {
	        return null;
	    }
	}

	@Override
	public boolean update(Kunde p) {
	    try {
	        repository.save(p);
	        return true;
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	        return false;
	    }
	}

}
