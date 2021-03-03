package de.rentacar.projekt.kunde.services;

import java.util.List;

import de.rentacar.projekt.kunde.model.Kunde;


public interface KundeServiceI {

	List<Kunde> findAllKunde();
    Kunde findById(int id);
    Kunde insert(Kunde k);
    boolean delete(int id);
    boolean update(Kunde k);
}