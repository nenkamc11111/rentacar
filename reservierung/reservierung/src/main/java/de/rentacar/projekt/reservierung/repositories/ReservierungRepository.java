package de.rentacar.projekt.reservierung.repositories;

import org.springframework.data.repository.CrudRepository;

import de.rentacar.projekt.reservierung.model.Reservierung;


public interface ReservierungRepository extends CrudRepository<Reservierung, Long> {

}
