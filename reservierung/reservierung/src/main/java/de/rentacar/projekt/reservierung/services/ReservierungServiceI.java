package de.rentacar.projekt.reservierung.services;

import java.util.List;

import de.rentacar.projekt.reservierung.model.Reservierung;

public interface ReservierungServiceI {
	List<Reservierung> findAllReservierung();
    Reservierung findById(int id);
    Reservierung insert(Reservierung k);
    boolean delete(int id);
    boolean update(Reservierung k);

}
