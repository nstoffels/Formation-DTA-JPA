package com.bankonet.test;

import javax.persistence.*;
import com.bankonet.model.*;

/**
 * Teste la persistance de plusieurs classes, dont une "embedded".
 */
public class Test4 {

  public static void main(String[] args) {
    EntityManagerFactory emf = null;
    EntityManager em = null;
	EntityTransaction tx = null;
    try {
      emf = Persistence.createEntityManagerFactory("Employes");
      em = emf.createEntityManager();
      tx = em.getTransaction();
      tx.begin();
      // Création des 3 départements
      Departement dept1 = new Departement("Direction", "Nice");
      Departement dept2 = new Departement("Comptabilité", "Nice");
      Departement dept3 = new Departement("Gestion personnel", "Nantes");
      em.persist(dept1);
      em.persist(dept2);
      em.persist(dept3);
      dept1.setLieu("Paris");
      // 2 clients avec leur adresse
      Adresse ad1 = new Adresse(36, "avenue Cyrnos", "Paris");
      Client client1 = new Client("Bibi", ad1);
      em.persist(client1);
      Adresse ad2 = new Adresse(50, "rue Victor Hugo", "Paris");
      Client client2 = new Client("Toto", ad2);
      em.persist(client2);
      // Création des 3 employés
      Employe emp1 = new Employe("Dupond");
      Employe emp2 = new Employe("Durand" ,dept2, emp1);
      Employe emp3 = new Employe("Legrand",dept1, null);
      em.persist(emp1);
      em.persist(emp2);
      em.persist(emp3);
      dept1.addEmploye(emp1);
      dept1.addEmploye(emp3);
      dept2.addEmploye(emp3);
      //projet
      Projet pro1 = new Projet("Projet1");
      Projet pro2 = new Projet("Projet2");
      Projet pro3 = new Projet("Projet3");
      em.persist(pro1);
      em.persist(pro2);
      em.persist(pro3);
      emp1.addProjet(pro1);
      emp3.addProjet(pro2);
      emp3.addProjet(pro3);
      //participation
      pro1.ajouterParticipant(emp1, "chef");
      pro2.ajouterParticipant(emp2, "chef");
      pro3.ajouterParticipant(emp3, "chef");
      pro2.ajouterParticipant(emp2, "tier-temps");
      pro3.ajouterParticipant(emp3, "mi-temps");
      tx.commit();
    }
    catch(Exception e) {
      // En "vrai" il faudrait affiner un peu plus...
      e.printStackTrace();
      if (tx != null) {
        tx.rollback();
      }
    }
    finally {
      if (em != null) {
        em.close();
      }
      if (emf != null) {
        emf.close();
      }
    }
  }
}

