/**
 * 
 */
package com.bankonet.test;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.bankonet.model.Employe;

;
/**
 * @author ETY
 *
 */
public class Test3 {

	/**
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Employes");
		EntityManager em = emf.createEntityManager();
		
		//récupère des entités
		
		/**
		 * premikère version
		 */
		String texteQuery1 = "Select e from Employe as e where upper(e.departement.nom) = :nomDept ";//le deux point signale une valeur
		Query query1 = em.createQuery(texteQuery1);
		query1.setParameter("nomDept", "Direction");
		List<Employe> listEmployes1 = (List<Employe>)query1.getResultList();

		for(Employe employe : listEmployes1){
			System.out.println("employé du département de direction : "+employe.getNom());
		}
		
		/**
		 * deuxième version
		 */
		String texteQuery2 = "Select e.nom, e.salaire from Employe as e where upper(e.departement.nom) = :nomDept ";//le deux point signale une valeur
		Query query2 = em.createQuery(texteQuery2);
		query2.setParameter("nomDept", "Direction");
		List<Object[]> listEmployes2 = (List<Object[]>)query2.getResultList();
		
	
		for(Object[] employe : listEmployes2){
			System.out.println("employé du département de direction : "+employe[0]+" gagne "+employe[1]);
		}
		
		/**
		 * troisième version
		 */
		Query query3 = em.createNamedQuery("nomEmploye");
		query3.setParameter("nomDept", "Direction");
		List<Employe> listEmployes3 = (List<Employe>)query3.getResultList();
		
		List<String> listEmploye33 = (List<String>)query3.getResultList();
		for(String nom : listEmploye33){
			System.out.println("le nom des employés du département de direction : "+nom);
		}
		
		/**
		 * quatrième version
		 */
		
		String texteQuery4 = "Select e from Employe as e where upper(e.departement.nom) = :nomDept ";//le deux point signale une valeur
		Query query4 = em.createQuery(texteQuery4);
		query4.setParameter("nomDept", "Direction");
		List<Employe> listEmployes4 = (List<Employe>)query4.getResultList();
		
		
		EntityTransaction tx=em.getTransaction();
		tx.begin();

		for(Employe employe : listEmployes4){
			employe.setSalaire((employe.getSalaire().add(new BigDecimal(2200))));
			employe.setSalaire((employe.getSalaire().multiply(new BigDecimal(1.05))));
			em.persist(employe);
		}
		
		tx.commit();
		/**
		 * cinquième version
		 * 
		 * 
		 */
		
		
		tx.begin();

		String texteQuery5 = "update Employe e set e.salaire = 2200 ";//le deux point signale une valeur
		Query query5 = em.createQuery(texteQuery5);
		int n = query5.executeUpdate();
		query5.setParameter("nomDept", "Direction");
		
		tx.commit();
		System.out.println("Nombre de salaire modifié : "+n);
		//vérification qy'ybe requête en volume ne modifie pas les entités en mémoire.


		
		
		em.close();
		emf.close();
	}

}