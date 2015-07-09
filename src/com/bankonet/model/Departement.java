package com.bankonet.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Departement {
  @Id
  @GeneratedValue
  private int id;
  private String nom;
  private String lieu;
  @OneToMany(mappedBy="departement")
  private Collection<Employe> employes = new ArrayList<Employe>();
	
  /**
   * Constructeur obligatoire pour JPA.
   *
   */
  public Departement() {
  }
	
  public Departement(String nom, String lieu) {
    this.nom = nom;
    this.lieu = lieu;
  }

  /**
   * Retourne l'identificateur g�r� par le SGBD. Identifie une ligne
   * de la base.
   */
  public int getId() {
    return id;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getLieu() {
    return lieu;
  }

  public void setLieu(String lieu) {
    this.lieu = lieu;
  }

  public Collection<Employe> getEmployes() {
    return employes;
  }

  /**
   * M�thode utilitaire souvent employ�e pour les associations
   * bidirectionnelles pour �viter d'oublier de mettre � jour
   * un des bouts de l'assocation.
   * Ne pas utiliser directement la m�thode setDepartement
   * de la classe Employe.
   * @param employe
   */
  public void addEmploye(Employe employe) {
    employes.add(employe);
    // Si l'employ� �tait d�j� dans un d�partement, il ne faut
    // pas oublier de le retirer de la collection de l'ancien d�partement.
    Departement ancienDept = employe.getDepartement();
    if (ancienDept != null) {
      ancienDept.getEmployes().remove(employe);
    }
    employe.setDepartement(this);
  }
}
