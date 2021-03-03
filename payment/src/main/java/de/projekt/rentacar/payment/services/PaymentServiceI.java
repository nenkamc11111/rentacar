package de.projekt.rentacar.payment.services;

import java.util.List;

import org.springframework.stereotype.Service;

import de.projekt.rentacar.payment.model.Payment;

@Service
public interface PaymentServiceI {
	
	List<Payment> findAllPayment();
	Payment findById(int idpayment);
	Payment insert(Payment a);
    boolean delete(int idpayment);
    boolean update(Payment a);

}
