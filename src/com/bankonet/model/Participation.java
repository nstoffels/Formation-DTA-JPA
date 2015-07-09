package com.bankonet.model;

//import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
//import javax.persistence.ManyToOne;

@Entity
//@table
public class Participation {
	@Id
	@GeneratedValue
	private int Id;
	
//	@ManyToOne(cascade={CascadeType.ALL})
//	private Employe employe;
//	
//	@ManyToOne(cascade={CascadeType.ALL})
//	private Projet projet;

	private String fonction;

	private Employe employe;
	
	private Projet projet;
	
	
	//constructeur
	/**
	 * 
	 */
	public Participation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//Methode particulière
	
	
	
	//getter/setter
	
	/**
	 * @return the id
	 */
	public int getId() {
		return Id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		Id = id;
	}

	/**
	 * @return the employe
	 */
	public Employe getEmploye() {
		return employe;
	}

	/**
	 * @param employe the employe to set
	 */
	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	/**
	 * @return the projet
	 */
	public Projet getProjet() {
		return projet;
	}

	/**
	 * @param projet the projet to set
	 */
	public void setProjet(Projet projet) {
		this.projet = projet;
	}
	/**
	 * 
	 * @param fonction
	 */
	public void setFonction(String fonction) {
		// TODO Auto-generated method stub
		this.fonction=fonction;
		
	}

	
	
}
