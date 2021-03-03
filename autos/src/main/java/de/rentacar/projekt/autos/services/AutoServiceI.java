package de.rentacar.projekt.autos.services;

import java.util.List;

import org.springframework.stereotype.Service;

import de.rentacar.projekt.autos.model.Auto;


@Service
public interface AutoServiceI {
	List<Auto> findAllAuto();
	List<Auto> findAllFreieAuto();
    Auto findById(int idauto);
    Auto insert(Auto a);
    boolean delete(int idauto);
    boolean update(Auto a);

}
