package de.rentacar.projekt.reservierung.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.rentacar.projekt.reservierung.model.HistoryAuto;
import de.rentacar.projekt.reservierung.repositories.HistoryAutoRepository;

@Service
public class HistoryAutoService implements HistoryAutoServiceI{
	@Autowired
	private HistoryAutoRepository repository;

	@Override
	public List<HistoryAuto> findAllHistoryAuto() {
	    return (List<HistoryAuto>)repository.findAll();
	}

	@Override
	public HistoryAuto insert(HistoryAuto h) {
	    return repository.save(h);
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
	public HistoryAuto findById(int id) {
	    Optional<HistoryAuto> result = repository.findById(id);
	    if (result.isPresent()) {
	        return result.get();
	    } else {
	        return null;
	    }
	}

	@Override
	public boolean update(HistoryAuto h) {
	    try {
	        repository.save(h);
	        return true;
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	        return false;
	    }
	}


}
