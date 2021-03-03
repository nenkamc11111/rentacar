package de.rentacar.projekt.reservierung.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reservierung")
public class Reservierung extends ReservierungAbstract{
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idreservierung")
    public int idreservierung;

    @Column(name = "idauto")
    public int idauto;
    
    @Column(name = "idkunde")
    public int idkunde;
    
    @Column(name = "abholdatum")
    public String abholdatum;


    @Column(name = "ruckgabedatum")
    public String ruckgabedatum;
    

}
