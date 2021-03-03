package de.rentacar.projekt.autos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "auto")
public class Auto {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idauto")
    public int idauto;

    @Column(name = "kmstand")
    public int kmstand;
    
    @Column(name = "mark")
    public String mark;

    @Column(name = "zustand")
    public String zustand;
    
    @Column(name = "filiale")
    public String filiale;
    
    @Column(name = "preis")
    public double preis;
    
    @Column(name = "bild")
    public String bild;
    
    @Column(name = "beschreibung")
    public String beschreibung;

    @Column(name = "frei")
    public int frei;

    public int getIdauto() {
		return idauto;
	}


	public void setIdauto(int idauto) {
		this.idauto = idauto;
	}


	public int getKmstand() {
		return kmstand;
	}


	public void setKmstand(int kmstand) {
		this.kmstand = kmstand;
	}


	public String getMark() {
		return mark;
	}


	public void setMark(String mark) {
		this.mark = mark;
	}


	public double getPreis() {
		return preis;
	}


	public void setPreis(double preis) {
		this.preis = preis;
	}


	public int getFrei() {
		return frei;
	}


	public void setFrei(int frei) {
		this.frei = frei;
	}


	public Auto() {}


    public Auto(int idauto, String mark, int kmstand, String zustand, String filiale, double preis,String bild, String beschreibung) {
        this.idauto  = idauto;
        this.mark    = mark;
        this.zustand = zustand;
        this.kmstand = kmstand;
        this.filiale = filiale;
        this.beschreibung=beschreibung;
        this.preis=preis;
        this.bild=bild;
    }
    
    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        builder.append(String.valueOf(idauto));
        builder.append(", ");
        builder.append(mark);
        builder.append(", ");
        builder.append(kmstand);

        return builder.toString();
    }

}
