package risk;

import risk.vue.Fenetre;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.Collections;
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
        Fenetre vue = new Fenetre(territoires); // Crée une instance de Fenetre
        
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
		
		//Carte de mission
		//TODO Peut être à instancier dans une classe comme les territoires dans "monde" pour clean le main
		Mission mission1 = new Mission("Vous devez conquérir 18 territoires et occuper chacun d'eux avec deux armées au moins.");
		Mission mission2 = new Mission("Vous devez conquérir en totalité l'Amérique du Nord et l'Afrique.");
		Mission mission3 = new Mission("Vous devez conquérir en totalité l'Europe et l'Amérique du sud plus un troisième continent au choix.");
		Mission mission4 = new Mission("Vous devez conquérir en totalité l'Europe et l'Océanie plus un troisième continent au choix.");
		Mission mission5 = new Mission("Vous devez conquérir 24 territoires aux choix.");
		Mission mission6 = new Mission("Vous devez conquérir en totalité l'Amérique du Nord et l'Océanie.");
		Mission mission7 = new Mission("Vous devez conquérir en totalité l'Asie et l'Afrique.");
		Mission mission8 = new Mission("Vous devez conquérir en totalité l'Asie et l'Amérique du sud.");
		Mission mission9 = new Mission("Vous devez détruire les armées jaunes. Si vous êtes vous même le propriétaire des armées jaunes ou si le joueur qui en est\r\n"
				+ "  propriétaire est éliminé par un autre joueur, votre but devient automatiquement de conquérir 24 territoires.");
		Mission mission10 = new Mission("Vous devez détruire les armées rouges. Si vous êtes vous même le propriétaire des armées rouges ou si le joueur qui en est\r\n"
				+ "  propriétaire est éliminé par un autre joueur, votre but devient automatiquement de conquérir 24 territoires.");
		Mission mission11 = new Mission("Vous devez détruire les armées bleues. Si vous êtes vous même le propriétaire des armées bleues ou si le joueur qui en est\r\n"
				+ "  propriétaire est éliminé par un autre joueur, votre but devient automatiquement de conquérir 24 territoires.");
		Mission mission12 = new Mission("Vous devez détruire les armées noires. Si vous êtes vous même le propriétaire des armées noires ou si le joueur qui en est\r\n"
				+ "  propriétaire est éliminé par un autre joueur, votre but devient automatiquement de conquérir 24 territoires.");
		Mission mission13 = new Mission("Vous devez détruire les armées violettes. Si vous êtes vous même le propriétaire des armées violettes ou si le joueur qui en est\r\n"
				+ "  propriétaire est éliminé par un autre joueur, votre but devient automatiquement de conquérir 24 territoires.");
		Mission mission14 = new Mission("Vous devez détruire les armées vertes. Si vous êtes vous même le propriétaire des armées vertes ou si le joueur qui en est\r\n"
				+ "  propriétaire est éliminé par un autre joueur, votre but devient automatiquement de conquérir 24 territoires.");
		List<String> missions = new ArrayList<>();
        missions.add(mission1.getMission());
        missions.add(mission2.getMission());
        missions.add(mission3.getMission());
        missions.add(mission4.getMission());
        missions.add(mission5.getMission());
        missions.add(mission6.getMission());
        missions.add(mission7.getMission());
        missions.add(mission8.getMission());
        missions.add(mission9.getMission());
        missions.add(mission10.getMission());
        missions.add(mission11.getMission());
        missions.add(mission12.getMission());
        missions.add(mission13.getMission());
        missions.add(mission14.getMission());
        //Mélanger les cartes de mission
		Collections.shuffle(missions);
		
		//Attribuer une mission randomisée à chaque joueur
		for (int i = 0; i < participants.length; i++) {
			Joueur joueur = participants[i];
			String mission = missions.get(i);
            System.out.println("La mission du joueur " + joueur.getId() + " : " + mission);
		}
		
		// DEBUT - ATTRIBUTION DES CARTES TERRITOIRES AUX JOUEURS
		// instances régiment pour infanterie, cavalerie et artillerie
		Regiment infanterie = new Regiment("Infanterie",1);
		Regiment cavalerie = new Regiment("Cavalerie",5);
		Regiment artilleire = new Regiment("Artillerie",10);
				
		List<String> typesRegiments = new ArrayList<>();
        typesRegiments.add("Infanterie");
        typesRegiments.add("Cavalerie");
        typesRegiments.add("Artillerie");
        
        List<Carte> cartes = new ArrayList<>();
        Random random = new Random();
        for (Territoire territoire : territoires) {
            int randRegiment = random.nextInt(typesRegiments.size());
            String typeRegiment = typesRegiments.get(randRegiment);
            cartes.add(new Carte(territoire, typeRegiment));
        }

        // afficher toutes les cartes
        for (Carte carte : cartes) {
            System.out.println("CARTE : Territoire : " + carte.getTerritoire() + ", Type de Régiment : " + carte.getTypeRegiment());
        }
        
        // déterminer le nombre de personnes à jouer et le nombre de cartes à distribuer par personne
        int nbParticipants = participants.length;
        System.out.println(nbParticipants);
        int carteJoueur = cartes.size() / nbParticipants;
        System.out.println(carteJoueur);
        // distribution des cartes
        Collections.shuffle(cartes);
        for (int i = 0; i < nbParticipants; i++) {
            List<Carte> mainJoueur = cartes.subList(i * carteJoueur, (i + 1) * carteJoueur);
            System.out.println("Joueur " + (i + 1) + " a reçu les cartes : " + mainJoueur);
            // Mise à jour des data 
            for (Carte carte : mainJoueur) {
            	participants[i].ajouterTerritoiresConquis(carte.getTerritoire());
            	participants[i].enleverNbRegimentsRestants(1);
            	carte.getTerritoire().ajouterNbRegiments(1);
            	carte.getTerritoire().setOccupant(participants[i]);
            	System.out.println("liste territoire :"+participants[i].getAllTerritoires());
            	System.out.println("Nb troupes a placer :"+participants[i].getNbRegimentsRestants());
            	System.out.println("Territoire occupé par :"+carte.getTerritoire().getOccupant()+" , nb de troupes : "+carte.getTerritoire().getNbRegiments());
            }
        }
		// DEBUT - ATTRIBUTION DES CARTES TERRITOIRES AUX JOUEURS
		
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
         boolean isFirstTour = true;
         boolean isWinner = false;
         while (isWinner != true) {
        	 // POUR CHAQUE JOUEUR
        	 for (Joueur joueur : participants) {
	        	 System.out.println("Joueur " + joueur.getNom());
	        	 
	        	 // INSTANCIATION VARIABLE DE STOCKAGE
	        	 // Variable stockant si un nouveau territoire a ete conquis ou non au cours des attaques
	        	 Boolean isNouveauTerritoireConquis = false;
	        	 // Variable stockant les choix du joueur
	        	 String choixAction = "null"; // choix du joueur dans le menu (cf plus bas)
        		 String choixDeplacer = "null"; // variable pour stocker si il y a validation des modifications des troupes, si il veut ajouter ou encore retirer de nouvelles troupes 
        		 /** @Moi pas fini a ajouter autres verif dont nb regiments*/
        		 
	        	 // PLACEMENT DES 20 REGIMENTS POUR LE PREMIER TOUR
	        	 if (isFirstTour == true) {
		        	 // Tant que le joueur n'a pas ajouter tous ses regiments, il ajoute les regiments restants
		        	 while (joueur.getNbRegimentsRestants() != 0) {
		        		 
			        	 /** @Raph BESOIN - Methode retournant un territoire et une quantité pour choisir le territoire où ajouter les troupes
			        	  *  >> Rappel condition : territoire.occupant == null || territoire.occupant == joueur
			        	  *  Sinon retourner fenetre message erreur territoire deja occupé 
			        	  *  OU
			        	  *  Si galere je le bloque à la mano dans le main 
			        	  *  
			        	  *  Pour simplifier, l'algo c'est qu'à la phase d'ajout, il peuvent pas enlever quand ils posent ahah 
			        	  *  genre, il pose 2, puis 1 , quand il en a plus ca passe à autre chose :3
			        	  */
		        		 
		        		 
		        		 for (Territoire territoire : joueur.getAllTerritoires()) {
		        			 vue.premierTour(joueur, territoire);
		        		 } 
		        		 
		        	//	 vue.premierTour(joueur, territoire); //Permet d'effectuer les actions pour un joueur au premier tour
			    
			        	 Territoire destTerritoireAjout = monde.getTerritoires().get(0);                    //    <== changer valeur
			        	 int nbRegimentsAjoutes = 1;														//    <== changer valeur
			        	 System.out.println("*Debut* Territoire : "+destTerritoireAjout.getNom()+" - Nb : "+destTerritoireAjout.getNbRegiments());
			        	 System.out.println("*Debut* Joueur : "+joueur.getNom()+" - Nb : "+joueur.getNbRegimentsRestants());
			        	 destTerritoireAjout.ajouterNbRegiments(nbRegimentsAjoutes); // Ajout régiment au territoire
			        	 joueur.enleverNbRegimentsRestants(nbRegimentsAjoutes); // Retrait nb au nb de regiment à placer
			        	 System.out.println("*Fin* Territoire : "+destTerritoireAjout.getNom()+" - Nb : "+destTerritoireAjout.getNbRegiments());
			        	 System.out.println("*Fin* Joueur : "+joueur.getNom()+" - Nb : "+joueur.getNbRegimentsRestants());
		        	 }
	        		 isFirstTour = false;
	        	 }
	        	 // PROCESSUS NORMAL POUR LES AUTRES TOURS
	        	 else {
		        	 // VERFICATION ET MISE A JOUR DES DATA EN DEBUT DE TOUR DE CHAQUE JOUEUR
	        		 int nbRegimentAPlacer = joueur.calculerNbRegimentsAPlacer();
	        		 joueur.ajouterNbRegimentsRestants(nbRegimentAPlacer);
	        		 
		        	 // AJOUT NOUVEAUX REGIMENTS
		        	 while (joueur.getNbRegimentsRestants() != 0) {
		        		 
		        	 /** @Raph BESOIN - Methode retournant un territoire et une quantité pour choisir le territoire où ajouter les troupes
		        	  *  >> Rappel condition : territoire.occupant == null || territoire.occupant == joueur
		        	  *  Sinon retourner fenetre message erreur territoire deja occupé 
		        	  *  OU
		        	  *  Si galere je le bloque à la mano dans le main 
		        	  *  
		        	  *  Pour simplifier, l'algo c'est qu'à la phase d'ajout, il peuvent pas enlever quand ils posent ahah 
		        	  *  genre, il pose 2, puis 1 , quand il en a plus ca passe à autre chose :3
		        	  */	        	 
		    
		        	 Territoire destTerritoireAjout = monde.getTerritoires().get(0);                    //    <== changer valeur
		        	 int nbRegimentsAjoutes = 1;														//    <== changer valeur
		        	 System.out.println("*Debut* Territoire : "+destTerritoireAjout.getNom()+" - Nb : "+destTerritoireAjout.getNbRegiments());
		        	 System.out.println("*Debut* Joueur : "+joueur.getNom()+" - Nb : "+joueur.getNbRegimentsRestants());
		        	 destTerritoireAjout.ajouterNbRegiments(nbRegimentsAjoutes); // Ajout régiment au territoire
		        	 joueur.enleverNbRegimentsRestants(nbRegimentsAjoutes); // Retrait nb au nb de regiment à placer
		        	 System.out.println("*Fin* Territoire : "+destTerritoireAjout.getNom()+" - Nb : "+destTerritoireAjout.getNbRegiments());
		        	 System.out.println("*Fin* Joueur : "+joueur.getNom()+" - Nb : "+joueur.getNbRegimentsRestants());
		        	 }
		        	 
		        	 // CHOIX D ATTAQUER, MODIFIER SES TROUPES OU PASSER SON TOUR
		        	 // Tant que le tour du joueur n'est pas fini (continuer d'attaquer), on affiche la fenetre des choix 
		        	 while (choixAction == "Attaquer") {
			        	 /** @Raph BESOIN - Modifier ta methode choixJoueur pour qu'elle retourne le choix du joueur
			        	  *  Genre string "Attaquer", "Déplacer" ou "Passer tour" c'est impec :)
			        	  */
		        		 // LANCER UNE ATTAQUE
		        		 // Si le joueur clique sur l'option d'attaquer, il choisie le territoire d'attaque, de defense et le nombre de regiments pour attaquer
		        		 if (choixAction == "Attaquer") {
				        	 /** @Kun / @Yujie 
				        	  * Attaque / Défense
				        	  * integrer dans le code : Si nouveau territoire conquis => isNouveauTerritoireConquis = true;
				        	  */
		        		 }
		        		 // DEPLACER CERTAINS DE SES REGIMENTS
		        		 // Si le joueur clique sur l'option deplacer, il choisie autant de deplacement qu'il souhaite (tant que les territoires sont voisins)
		        		 // Lorsque qu'il valide les changements, son tour est automatiquement terminé
			        	 /** @Raph BESOIN - Modifier ta methode choixJoueur pour qu'elle retourne le choix du joueur
			        	  *  Genre string "Attaquer", "Déplacer" ou "Passer tour" c'est impec :)
			        	  */
		        		 else if (choixAction == "Déplacer") {
		        			while (choixDeplacer != "Valider") {
		        				/** @Raph choix joueur AJOUTER ou RETIRER (a chaque fin de choix si faisable) ou VALIDER
		        				 * => AJOUTER : territoire + nb regiments
		        				 * => RETIRER : territoire + nb regiments
		        				 *  >> Rappel conditions : 
		        				 *  - joueur occupe le territoire (cf Territoire.occupant == Joueur)
		        				 *  - pour le RETRAIT de troupes : nb de troupes retirées <= nb de troupes presentes (cd Territoire.nbRegiments)
		        				 *  - pour l'AJOUT : nb de troupes en stock >= nb de troupe à ajouter 
		        				 *  - pour VALIDER : nb de troupes en stock == 0 ? (franchement optionnel ahah, au pire le joueur prend de l'avance hein)
		        				 *  - pays voisins mais un peu relou :/
		        				 */ 
		        				
		        				// Choix d'AJOUTER des regiments
		        				if (choixDeplacer == "Ajouter") {
			        				Territoire territoireModifie = monde.getTerritoires().get(0);	// A modifier (pour test)
			        				int nbRegiments = 1;											// A modifier (pour test)
			        				
			        				joueur.enleverNbRegimentsRestants(nbRegiments);
			        				territoireModifie.ajouterNbRegiments(nbRegiments);
		        				}
			        			// Choix d'ENLEVER des regiments
		        				else if (choixDeplacer == "Retirer") {
			        				Territoire territoireModifie = monde.getTerritoires().get(0);	// A modifier (pour test)
			        				int nbRegiments = 1;											// A modifier (pour test)
			        				
			        				joueur.ajouterNbRegimentsRestants(nbRegiments);
			        				territoireModifie.enleverNbRegiments(nbRegiments);
			        				
			        				// Si le joueur a retiré toutes ses troupes d'un territoire on met à jour les data
			        				if (territoireModifie.getNbRegiments() == 0) {
			        					joueur.supprimerTerritoiresConquis(territoireModifie);
			        					territoireModifie.setOccupant(null);
			        				}
		        				}
		        			}
		        		 }
		        	 }
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

