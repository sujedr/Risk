package risk;

import risk.vue.Fenetre;
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
		String dn = "10/10/2020";
		Joueur j1 = new Joueur("1", "AA", "aa", dn);
		Joueur j2 = new Joueur("2", "BB", "bb", dn);
		Joueur j3 = new Joueur("3", "CC", "cc", dn);
		Joueur j4 = new Joueur("4", "DD", "dd", dn);
		Joueur j5 = new Joueur("5", "EE", "ee", dn);
		Joueur j6 = new Joueur("6", "FF", "ff", dn);
		Joueur[] participants = {j1, j2, j3, j4, j5, j6};
	    /** Fin - A supprimer apres test*/

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
	}
}


// test kun

