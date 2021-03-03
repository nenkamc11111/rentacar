package de.rentacar.projekt.reservierung.services;

import java.util.List;

import de.rentacar.projekt.reservierung.model.Mieten;

public interface MietenServiceI {
	List<Mieten> findAllMieten();
    Mieten findById(int id);
    Mieten insert(Mieten k);
    boolean delete(int id);
    boolean update(Mieten k);
}
