package risk;

import risk.vue.Fenetre;

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
        Fenetre vue = new Fenetre(); // Crée une instance de Fenetre
        
        // INITIALISATION D'UNE MANCHE
	    /** Deb - A supprimer apres test*/
        
		String dn = "2020-10-10";
		Joueur j1 = new Joueur("1", "AA", "aa", dn);
		Joueur j2 = new Joueur("2", "BB", "bb", dn);
		Joueur j3 = new Joueur("3", "CC", "cc", dn);
		Joueur j4 = new Joueur("4", "DD", "dd", dn);
		Joueur j5 = new Joueur("5", "EE", "ee", dn);
		Joueur j6 = new Joueur("6", "FF", "ff", dn);
		Joueur[] participants = {j1, j2, j3, j4, j5, j6};
	    /** Fin - A supprimer apres test*/

		// Enregistrement des joueurs dans la base de données
		String url = "jdbc:mysql://localhost:3306/risk";
        String user = "root";
        String password = "";

        ConnexionDB dbRisk = new ConnexionDB(url, user, password);
        
        for (Joueur joueur : participants) {
            dbRisk.insertJoueur(joueur.getNom(), joueur.getPrenom(), joueur.getDtNaissance());
        }
		
		// Creation d'une manche 
		Manche manche1 = new Manche(participants);
        System.out.println(manche1.toString());  
        
        // Determination du joueur qui commencera le tour et oganisation de l'ordre des joueurs dans la manche
        LancerDes des = new LancerDes();
        int resultatDes = des.lancerDes();
        System.out.println(resultatDes);        
        manche1.definirOrdreJoueur(resultatDes);
        System.out.println(manche1.toString());  
        
        // DEBUT DE LA MANCHE
         boolean isWinner = false;
         while (isWinner != true) {
        	 // 
        	 // isWinner = true if all territoire conquis ou cart objectif realisee
         }
        
        //TODO Pour l'actualisation de l'affichage du jouer à qui c'est le tour
        //vue.actualiserTour(tour);
	}
}

