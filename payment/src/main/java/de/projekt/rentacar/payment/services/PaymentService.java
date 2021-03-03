package de.projekt.rentacar.payment.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.projekt.rentacar.payment.model.Payment;
import de.projekt.rentacar.payment.repositories.PaymentRepository;

@Service
public class PaymentService implements PaymentServiceI{
	
	@Autowired
	private PaymentRepository repository;

	@Override
	public List<Payment> findAllPayment() {
	    return (List<Payment>)repository.findAll();
	}
	
	
	@Override
	public Payment insert(Payment p) {
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
	public Payment findById(int id) {
	    Optional<Payment> result = repository.findById(id);
	    if (result.isPresent()) {
	        return result.get();
	    } else {
	        return null;
	    }
	}

	@Override
	public boolean update(Payment p) {
	    try {
	        repository.save(p);
	        return true;
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	        return false;
	    }
	}
	

}
