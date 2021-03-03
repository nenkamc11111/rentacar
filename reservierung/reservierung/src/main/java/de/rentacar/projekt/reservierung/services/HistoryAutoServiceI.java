package de.rentacar.projekt.reservierung.services;

import java.util.List;

import de.rentacar.projekt.reservierung.model.HistoryAuto;

public interface HistoryAutoServiceI {
	List<HistoryAuto> findAllHistoryAuto();
    HistoryAuto findById(int id);
    HistoryAuto insert(HistoryAuto k);
    boolean delete(int id);
    boolean update(HistoryAuto k);

}
