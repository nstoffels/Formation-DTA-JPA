package com.bankonet.model;

import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/** 
 * Un employé de l'entreprise.
 */

@Entity
@NamedQuery(name = "nomEmploye", query = "Select e.nom from Employe as e where upper(e.departement.nom) = :nomDept")
@DiscriminatorValue("E")
public class Employe extends Personne {
 
	
  private BigDecimal salaire;
  @ManyToOne
  private Employe superieur;
  @ManyToOne
  private Departement departement;
  
  @ManyToMany (cascade={CascadeType.ALL}, mappedBy="employe")
  private List<Projet> projetlist=new ArrayList<Projet>();
  
  @OneToMany (cascade={CascadeType.ALL}, mappedBy="employe", fetch=FetchType.EAGER)
  private List<Participation> participationlist=new ArrayList<Participation>();
  
  
  //constructeur
  /**
   * 
   */
  public Employe() {
  }
	/**
	 * 
	 * @param nom
	 */
  public Employe(String nom) {
	    super(nom);
	  }
  /**
   * 
   * @param nom
   * @param departement
   * @param superieur
   */
  public Employe(String nom, Departement departement, Employe superieur) {
	    super(nom);
	    departement.addEmploye(this);
	    this.superieur = superieur;
	  }
  
  //methode particulière
  /**
   * 
   * @param projet
   */
  public void addProjet(Projet projet){

	  projetlist.add(projet);
	  projet.getEmploye().add(this);
  }
  /**
   * 
   * @param projet
   * @param fonction
   */
//  public void ajouterParticipant(Projet projet, String fonction){
//	  Participation participation = new Participation();
//	  participation.setProjet(projet);
//	  participation.setFonction(fonction);
//	  participation.setEmploye(this);
//  }
  // à mettre soit en projet, soit en employé
  
  /**
   * 
   * @return
   */
  //getter setter
  
  private Collection <Projet>getProjet(){
	 return projetlist; 	
  }
  /**
   * 
   * @param projet
   */
  private void setProjet(Collection <Projet> projet) {
	// TODO Auto-generated method stub
	  this.projetlist=projetlist;
  }

  public BigDecimal getSalaire() {
    return salaire;
  }

  public void setSalaire(BigDecimal salaire) {
    this.salaire = salaire;
  }

  public Employe getSuperieur() {
    return superieur;
  }

  public void setSuperieur(Employe employe) {
    this.superieur = employe;
  }

  public Departement getDepartement() {
    return departement;
  }
  /**
   * 
   * @param departement
   */
  public void setDepartement(Departement departement) {
    this.departement = departement;
  }
/**
 * @return the participationlist
 */
public List<Participation> getParticipationlist() {
	return participationlist;
}
/**
 * @param participationlist the participationlist to set
 */
public void setParticipationlist(List<Participation> participationlist) {
	this.participationlist = participationlist;
}
  
  
}
