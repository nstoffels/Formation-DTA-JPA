package com.bankonet.model;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Projet {
	@Id
	@GeneratedValue
  private int ID;
  private String nom;
  
	  @ManyToMany 
	  @JoinTable(name="ProEmp",
	  joinColumns= 
	  @JoinColumn(name="ID_Pro", referencedColumnName="ID"),
	  inverseJoinColumns=
	  @JoinColumn(name="ID_Emp", referencedColumnName="id")
	  )

	  private List<Employe> employe = new ArrayList<Employe>();
	  
	  /**
	   * 
	   */
	  @OneToMany (cascade={CascadeType.ALL}, mappedBy="projet", fetch=FetchType.EAGER)
	  private List<Participation> participation=new ArrayList<Participation>();
	  /**
	   * Constructeur
	   */
	  
	  public Projet() {
		super();
		// TODO Auto-generated constructor stub
	  }
		public Projet(String nom) {
			super();
			this.nom = nom;
		}

	
	
	//constructeur particulier
	/**
	 * 
	 * @param employe
	 * @param fonction
	 */
	
	  public void ajouterParticipant(Employe employe, String fonction){
		  Participation participation = new Participation();
		  participation.setEmploye(employe);
		  participation.setFonction(fonction);
		  participation.setProjet(this);
		  this.getParticipation().add(participation);
		  employe.getParticipationlist().add(participation);
	  }
	
	public static void add(Projet projet) {
		// TODO Auto-generated method stub
		
	}
	  //getter and setter
	/**
	 * 
	 * @return
	 */

		public int getId() {
	    return ID;
	  }
	
	  public String getNom() {
	    return nom;
	  }
	
	  public void setNom(String nom) {
	    this.nom = nom;
	  }
	/**
	 * @return the employe
	 */
	public List<Employe> getEmploye() {
		return employe;
	}
	/**
	 * @param employe the employe to set
	 */
	public void setEmploye(List<Employe> employe) {
		this.employe = employe;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.ID = id;
	}
	/**
	 * @return the participation
	 */
	public List<Participation> getParticipation() {
		return participation;
	}
	/**
	 * @param participation the participation to set
	 */
	public void setParticipation(List<Participation> participation) {
		this.participation = participation;
	}
	

}

