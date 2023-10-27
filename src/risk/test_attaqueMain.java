package risk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import risk.model.Conflit;
import risk.model.Continent;
import risk.model.Joueur;
import risk.model.Mission;
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
    	
		/** ATTRIBUTION DES CARTES OBJECTIFS AUX JOUEURS */
		//MÃ©langer les cartes de mission æ‰“ä¹±å�¡ç‰Œ
		Mission m = new Mission();
		Collections.shuffle(m.getMissionListe(), new Random());
		
		//Distribution des cartes de mission aux joueurs éš�æœºåˆ†é…�ä»»åŠ¡å�¡ç‰Œç»™çŽ©å®¶
		for(int i=0;i<participants.length;i++) {
			participants[i].DistribuerRandomMission(m.getMissionListe());
		}
		
		Joueur joueur = j1;
		Territoire territoireAttaquant = monde.getTerritoires().get(0);
		Territoire territoireDefenseur = monde.getTerritoires().get(2);
		int nbRegimentsAttaque = 3;
		int nbRegimentsRiposte = 2;    
        
		////// TEST ATTAQUE /////
		
		/** @Raph Demander choix pays attaquant, pays attaquÃ©, nombre de troupes 
		 *  Output : territoireAttaquant, territoireDefenseur, nbRegimentsAttaque
		 */
		Conflit conflit = new Conflit(joueur, territoireAttaquant, territoireDefenseur, nbRegimentsAttaque);
		/** @Raph Demander defenseur nb de troupes riposte
		 *  Input : conflit.getBlablabla...
		 *  Output : nbRegimentsRiposte
		 */
		conflit.resultatConflit(nbRegimentsRiposte);
		
		////// TEST ATTAQUE /////
		
		joueur = j6;
		
		for (Continent continent : monde.getMonde()) {
			for (Territoire territoire : continent.getTerritoires()) {
				j6.ajouterTerritoiresConquis(territoire);
			}
		}
		
		Boolean isWinner = false;
        // Si le joueur a complÃ©tÃ© son objectif
        Boolean isObjectifCompleted = joueur.MissionReussie(participants); // check dans test_Main avant d'insérer
        if (isObjectifCompleted == true) {
       	 isWinner = true;	
       	 System.out.println("FIN PARTIE, OBJETCIF COMPLETE PAR "+joueur+" : \n"+joueur.getCurrentmission());
        }
        
        // Si le joueur a remportÃ© tous les territoires
        int nbTerritoiresConquis = (int) joueur.getAllTerritoires().size();
        if (nbTerritoiresConquis == monde.getNbTerritoireTotal()) {
         System.out.println("FIN PARTIE, TOUS LES TERRITOIRES CONQUIS PAS "+joueur);
       	 isWinner = true;	
        }
	}
}




