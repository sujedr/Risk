package risk;

import java.util.ArrayList;

import risk.model.Attaque;
import risk.model.Joueur;
import risk.model.Monde;
import risk.model.Territoire;
import risk.vue.Fenetre;

public class test_attaqueMain {

	public static void main(String[] args) {
		
        // Creation du plateau (objets continents et territoires)
        Monde monde = new Monde();
        ArrayList<Territoire> territoires = monde.getTerritoires();//              <======== ajouter la var territoires en input @raph 
        
        System.out.println("okk");
        
        // INITIALISATION D'UNE MANCHE
	    /** Deb - A supprimer apres test*/
		String dn = "2020-10-10";

		Joueur j1 = new Joueur("1", "AA", "aa", dn, monde.getMonde(), "jaunes");
		Joueur j2 = new Joueur("2", "BB", "bb", dn, monde.getMonde(), "rouges");
		Joueur j3 = new Joueur("3", "CC", "cc", dn, monde.getMonde(), "bleues");
		Joueur j4 = new Joueur("4", "DD", "dd", dn, monde.getMonde(), "noires");
		Joueur j5 = new Joueur("5", "EE", "ee", dn, monde.getMonde(), "violettes");
		Joueur j6 = new Joueur("6", "FF", "ff", dn, monde.getMonde(), "vertes");

		Joueur[] participants = {j1, j2, j3, j4, j5, j6};
		Joueur joueur = j1;
		Territoire territoireAttaquant = monde.getTerritoires().get(0);
		Territoire territoireDefenseur = monde.getTerritoires().get(1);
		int nbRegimentsAttaque = 2;

		////// TEST ATTAQUE /////
		
		/** Demander choix pays attaquant, pays attaqué, nombre de troupes */
		Attaque attaque = new Attaque(joueur, territoireAttaquant, territoireDefenseur, nbRegimentsAttaque);
	}

}
