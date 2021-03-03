package de.rentacar.projekt.reservierung.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mieten")
public class Mieten extends ReservierungAbstract{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmieten")
    public int idmieten;

    @Column(name = "idauto")
    public int idauto;
    
    @Column(name = "idkunde")
    public int idkunde;
    
    @Column(name = "abholdatum")
    public Date abholdatum;


    @Column(name = "ruckgabedatum")
    public Date ruckgabedatum;

    
    

}
