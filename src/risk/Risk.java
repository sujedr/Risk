package risk;

import risk.vue.Fenetre;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import risk.controler.ConnexionDB;
import risk.model.*;

/**
 * 
 */
public class Risk {

	/**
	 * Classe principale du projet
	 * @param args
	 */
	public static void main(String[] args) {
		
        // Creation du plateau (objets continents et territoires)
        Monde monde = new Monde();
        ArrayList<Territoire> territoires = monde.getTerritoires();//              <======== ajouter la var territoires en input @raph 
        Fenetre vue = new Fenetre(); // Crée une instance de Fenetre 
        
        System.out.println("okk");
        
        // INITIALISATION D'UNE MANCHE
	    /** Deb - A supprimer apres test*/
        String[] nomsContinents = monde.getNomContinent();
		String dn = "2020-10-10";
		Joueur j1 = new Joueur("1", "AA", "aa", dn, nomsContinents);
		Joueur j2 = new Joueur("2", "BB", "bb", dn, nomsContinents);
		Joueur j3 = new Joueur("3", "CC", "cc", dn, nomsContinents);
		Joueur j4 = new Joueur("4", "DD", "dd", dn, nomsContinents);
		Joueur j5 = new Joueur("5", "EE", "ee", dn, nomsContinents);
		Joueur j6 = new Joueur("6", "FF", "ff", dn, nomsContinents);
		Joueur[] participants = {j1, j2, j3, j4, j5, j6};
	    /** Fin - A supprimer apres test*/
		
//		// Enregistrement des joueurs dans la base de données
//		String url = "jdbc:mysql://localhost:3306/risk";
//        String user = "root";
//        String password = "";
//
//        ConnexionDB dbRisk = new ConnexionDB(url, user, password);
//        
//        for (Joueur joueur : participants) {
//            if (!dbRisk.joueurExiste(joueur.getNom(), joueur.getPrenom())) {
//                dbRisk.insertJoueur(joueur.getNom(), joueur.getPrenom(), joueur.getDtNaissance());
//            } else {
//                System.out.println("Le joueur " + joueur.getNom() + " " + joueur.getPrenom() + " existe déjà.");
//            }
//        }
		
		// Creation d'une manche 
		Manche manche1 = new Manche(participants);
        System.out.println(manche1.toString());  
        
        // Determination du joueur qui commencera le tour et oganisation de l'ordre des joueurs dans la manche
        LancerDes des = new LancerDes();
        int resultatDes = des.lancerDes();
        System.out.println(resultatDes);        
        manche1.definirOrdreJoueur(resultatDes);
        System.out.println(manche1.toString());  
        System.out.println("--------------------------");  

        // DEBUT DE LA MANCHE
         boolean isWinner = false;
         while (isWinner != true) {
        	 for (Joueur joueur : participants) {
	        	 System.out.println("Joueur " + joueur.getNom());
	        	 // Ajouter les nouveaux régiments
	        	 while (joueur.getNbRegimentsRestants() != 0) {
	        	 System.out.println("Tour : " + joueur.getNom());

	        	 /** NEEDED - Fonction retournant un territoire et une quantité au click 
	        	  *  Condition : territoire.occupant == null || territoire.occupant == joueur
	        	  *  Sinon retourner fenetre message erreur territoire deja occupé
	        	  */	        	 
	        	 Territoire destTerritoireAjout = monde.getTerritoires().get(0);                    //    <== changer valeur
	        	 int nbRegimentsAjoutes = 1;														//    <== changer valeur
	        	 System.out.println("*Debut* Territoire : "+destTerritoireAjout.getNom()+" - Nb : "+destTerritoireAjout.getNbRegiments());
	        	 System.out.println("*Debut* Joueur : "+joueur.getNom()+" - Nb : "+joueur.getNbRegimentsRestants());
	        	 destTerritoireAjout.setNbRegiments(nbRegimentsAjoutes); // Ajout régiment au territoire
	        	 joueur.enleverNbRegimentsRestants(nbRegimentsAjoutes); // Retrait nb au nb de regiment à placer
	        	 System.out.println("*Fin* Territoire : "+destTerritoireAjout.getNom()+" - Nb : "+destTerritoireAjout.getNbRegiments());
	        	 System.out.println("*Fin* Joueur : "+joueur.getNom()+" - Nb : "+joueur.getNbRegimentsRestants());
	        	 }
        	  
        	 isWinner = true;																	// A supprimer (for testing only)
        	 // isWinner = true if all territoire conquis ou cart objectif realisee
        	 }
         }
        
        //TODO Pour l'actualisation de l'affichage du jouer à qui c'est le tour
        //vue.actualiserTour(tour);
         
        System.out.println("end");
	}
}

