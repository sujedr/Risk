package risk;

import java.util.ArrayList;

import risk.model.Conflit;
import risk.model.Joueur;
import risk.model.Monde;
import risk.model.Territoire;


public class test_attaqueMain {

	public static void main(String[] args) {
		
        // Creation du plateau (objets continents et territoires)
        Monde monde = new Monde();
        ArrayList<Territoire> territoires = monde.getTerritoires();//              <======== ajouter la var territoires en input @raph 
                
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
    	j1.ajouterTerritoiresConquis(monde.getTerritoires().get(0));
    	monde.getTerritoires().get(0).setOccupant(j1);
    	monde.getTerritoires().get(0).ajouterNbRegiments(4);
    	// Check combat classique ...
    	j2.ajouterTerritoiresConquis(monde.getTerritoires().get(1));
    	monde.getTerritoires().get(1).setOccupant(j2);
    	monde.getTerritoires().get(1).ajouterNbRegiments(3);
    	// Check si plus de troupes dutout => ....
    	j3.ajouterTerritoiresConquis(monde.getTerritoires().get(2));
    	monde.getTerritoires().get(2).setOccupant(j3);
    	monde.getTerritoires().get(2).ajouterNbRegiments(1);
    			
		Joueur joueur = j1;
		Territoire territoireAttaquant = monde.getTerritoires().get(0);
		Territoire territoireDefenseur = monde.getTerritoires().get(1);
		int nbRegimentsAttaque = 3;
		int nbRegimentsRiposte = 2;

		////// TEST ATTAQUE /////
		
		
		/** @Raph Demander choix pays attaquant, pays attaqué, nombre de troupes 
		 *  Output : territoireAttaquant, territoireDefenseur, nbRegimentsAttaque
		 */
		Conflit conflit = new Conflit(joueur, territoireAttaquant, territoireDefenseur, nbRegimentsAttaque);
		/** @Raph Demander defenseur nb de troupes riposte
		 *  Input : conflit.getBlablabla...
		 *  Output : nbRegimentsRiposte
		 */
		conflit.resultatConflit(nbRegimentsRiposte);
		
	}
}




