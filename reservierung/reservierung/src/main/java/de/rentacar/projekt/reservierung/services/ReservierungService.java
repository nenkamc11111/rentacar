package de.rentacar.projekt.reservierung.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.rentacar.projekt.reservierung.model.Reservierung;
import de.rentacar.projekt.reservierung.repositories.ReservierungRepository;

@Service
public class ReservierungService implements ReservierungServiceI{
	
	@Autowired
	private ReservierungRepository repository;

	@Override
	public List<Reservierung> findAllReservierung() {
	    return (List<Reservierung>)repository.findAll();
	}

	@Override
	public Reservierung insert(Reservierung r) {
	    return repository.save(r);
	}

	@Override
	public boolean delete(int id) {
	    try {
	        repository.deleteById((long) id);
	        return true;
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	        return false;
	    }
	}

	@Override
	public Reservierung findById(int id) {
	    Optional<Reservierung> result = repository.findById((long) id);
	    if (result.isPresent()) {
	        return result.get();
	    } else {
	        return null;
	    }
	}

	@Override
	public boolean update(Reservierung r) {
	    try {
	        repository.save(r);
	        return true;
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	        return false;
	    }
	}

}
