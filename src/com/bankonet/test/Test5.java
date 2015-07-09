/**
 * 
 */
package com.bankonet.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.bankonet.model.Employe;

/**
 * @author ETY
 *
 */
public class Test5 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Employes");
		EntityManager em=emf.createEntityManager();
		
		//récupère des entités
		String texteQuery1 = "Select e from Personne";//
		Query query1 = em.createQuery(texteQuery1);
		query1.setParameter("nomDept", "Direction");
		List<Employe> listEmployes = (List<Employe>)query1.getResultList();
		
		
		List<Employe> listEmployes1 = (List<Employe>)query1.getResultList();
		for(Employe employe : listEmployes1){
			System.out.println("employé département direction"+employe.getNom());
		}
	}

}
