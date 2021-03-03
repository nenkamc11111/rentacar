package de.projekt.rentacar.payment.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.projekt.rentacar.payment.model.Payment;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Integer> {

}
