package de.rentacar.projekt.kunde.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.rentacar.projekt.kunde.model.Kunde;

@Repository
public interface KundeRepository  extends CrudRepository<Kunde, Integer> {

}
