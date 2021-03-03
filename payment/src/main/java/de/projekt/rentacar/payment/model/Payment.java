package de.projekt.rentacar.payment.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payment")
public class Payment {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpayment")
    public int idpayment;

    @Column(name = "idauto")
    public int idauto;
    
    @Column(name = "idmieten")
    public int idmieten;
    
    @Column(name = "idkunde")
    public int idkunde;
    
    @Column(name = "abholdatum")
    public Date abholdatum;


    @Column(name = "ruckgabedatum")
    public Date ruckgabedatum;
    
    @Column(name = "preis")
    public Double preis;

	public int getIdpayment() {
		return idpayment;
	}

	public void setIdpayment(int idpayment) {
		this.idpayment = idpayment;
	}

	public int getIdauto() {
		return idauto;
	}

	public void setIdauto(int idauto) {
		this.idauto = idauto;
	}


	public Date getAbholdatum() {
		return abholdatum;
	}

	public void setAbholdatum(Date abholdatum) {
		this.abholdatum = abholdatum;
	}

	public Date getRuckgabedatum() {
		return ruckgabedatum;
	}

	public void setRuckgabedatum(Date ruckgabedatum) {
		this.ruckgabedatum = ruckgabedatum;
	}

	public Double getPreis() {
		return preis;
	}

	public void setPreis(Double preis) {
		this.preis = preis;
	}
    
    
    


}
