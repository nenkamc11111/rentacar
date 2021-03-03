package de.rentacar.projekt.kunde.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "kunde")
public class Kunde {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idkunde")
    public int idkunde;

    @Column(name = "alter")
    public int alter;
    
    @Column(name = "vorname")
    public String vorname;


    @Column(name = "name")
    public String name;
    
    @Column(name = "stadt")
    public String stadt;

    @Column(name = "email")
    public String email;
    
    @Column(name = "password")
    public String password;

    public int getIdkunde() {
		return idkunde;
	}


	public void setIdkunde(int idkunde) {
		this.idkunde = idkunde;
	}


	public int getAlter() {
		return alter;
	}


	public void setAlter(int alter) {
		this.alter = alter;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Kunde() {}


    public Kunde(int idkunde, String vorname, int alter, String name, String stadt) {
        this.idkunde  = idkunde;
        this.vorname    = vorname;
        this.name = name;
        this.alter = alter;
        this.stadt = stadt;
    }
}
