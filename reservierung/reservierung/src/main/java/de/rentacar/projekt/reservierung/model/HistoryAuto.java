package de.rentacar.projekt.reservierung.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "historyauto")
public class HistoryAuto extends ReservierungAbstract{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idhistoryauto")
    public int idhistoryauto;
	
	/*@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idmieten", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Mieten mieten;*/

	@Column(name = "idmieten")
    public int idmieten;
	
    @Column(name = "idauto")
    public int idauto;
    
    @Column(name = "idkunde")
    public int idkunde;
    
    @Column(name = "abholdatum")
    public String abholdatum;


    @Column(name = "ruckgabedatum")
    public String ruckgabedatum;

}
