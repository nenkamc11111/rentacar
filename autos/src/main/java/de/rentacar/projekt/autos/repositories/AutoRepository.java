package de.rentacar.projekt.autos.repositories;

import de.rentacar.projekt.autos.model.Auto;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoRepository extends CrudRepository<Auto, Integer> {

	@Query("SELECT a FROM Auto a WHERE a.frei = 1")
	List<Auto> findAllFreieAuto();
}
