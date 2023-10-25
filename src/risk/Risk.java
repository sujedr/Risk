package risk;

import risk.vue.Fenetre;

import java.util.ArrayList;
import java.util.Date;
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
        vue.setTerritoires(territoires);
        
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
		
		
		// instances régiment pour infanterie, cavalerie et artillerie
		Regiment infanterie = new Regiment("infanterie",1);
		Regiment cavalerie = new Regiment("cavalerie",5);
		Regiment artilleire = new Regiment("artilleire",10);
		
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
        	 // Ajouter les nouveaux régiments
        	 /** NEEDED - Fonction retournant un territoire et une quantité au click */
        	 Territoire destTerritoireAjout = monde.getTerritoires().get(0);                    //    <== changer valeur
        	 int nbRegimentsAjoutes = 2;														//    <== changer valeur
        	 
        	 System.out.println(destTerritoireAjout);
        	 destTerritoireAjout.setNbRegiments(nbRegimentsAjoutes);
        	 System.out.println(destTerritoireAjout);
        	 System.out.println(joueur);
        	 joueur.enleverNbRegimentsRestants(nbRegimentsAjoutes);
        	 System.out.println(joueur);

        	 isWinner = true;																	// A supprimer (for testing only)
        	 // isWinner = true if all territoire conquis ou cart objectif realisee
        	 }
         }
        
        //TODO Pour l'actualisation de l'affichage du jouer à qui c'est le tour
        //vue.actualiserTour(tour);
         
        System.out.println("end");
	}
}

