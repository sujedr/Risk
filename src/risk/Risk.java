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
        Fenetre vue = new Fenetre(territoires); // CrÃ©e une instance de Fenetre
        
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
		
		int nbUnitesAjout = 0; //Nombre d'unitÃ©s a ajoutÃ© et enlever
	    /** Fin - A supprimer apres test*/
		
//		// Enregistrement des joueurs dans la base de donnÃ©es
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
//                System.out.println("Le joueur " + joueur.getNom() + " " + joueur.getPrenom() + " existe dÃ©jÃ .");
//            }
//        }
		
		//MÃ©langer les cartes de mission æ‰“ä¹±å�¡ç‰Œ
		Mission m = new Mission();
		Collections.shuffle(m.getMissionListe(), new Random());
		
		//Distribution des cartes de mission aux joueurs éš�æœºåˆ†é…�ä»»åŠ¡å�¡ç‰Œç»™çŽ©å®¶
		for(int i=0;i<participants.length;i++) {
			participants[i].DistribuerRandomMission(m.getMissionListe());
		}
		
		// DEBUT - ATTRIBUTION DES CARTES TERRITOIRES AUX JOUEURS
		// instances rÃ©giment pour infanterie, cavalerie et artillerie
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
            System.out.println("CARTE : Territoire : " + carte.getTerritoire() + ", Type de RÃ©giment : " + carte.getTypeRegiment());
        }
        
        // dÃ©terminer le nombre de personnes Ã  jouer et le nombre de cartes Ã  distribuer par personne
        int nbParticipants = participants.length;
        System.out.println(nbParticipants);
        int carteJoueur = cartes.size() / nbParticipants;
        System.out.println(carteJoueur);
        // distribution des cartes
        Collections.shuffle(cartes);
        for (int i = 0; i < nbParticipants; i++) {
            List<Carte> mainJoueur = cartes.subList(i * carteJoueur, (i + 1) * carteJoueur);
            System.out.println("Joueur " + (i + 1) + " a reÃ§u les cartes : " + mainJoueur);
            // Mise Ã  jour des data 
            for (Carte carte : mainJoueur) {
            	participants[i].ajouterTerritoiresConquis(carte.getTerritoire());
            	participants[i].enleverNbRegimentsRestants(1);
            	carte.getTerritoire().ajouterNbRegiments(1);
            	carte.getTerritoire().setOccupant(participants[i]);
            	System.out.println("liste territoire :"+participants[i].getAllTerritoires());
            	System.out.println("Nb troupes a placer :"+participants[i].getNbRegimentsRestants());
            	System.out.println("Territoire occupÃ© par :"+carte.getTerritoire().getOccupant()+" , nb de troupes : "+carte.getTerritoire().getNbRegiments());
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
         boolean isObjectifCompleted = false;
         int nbJouer = 0; //On vÃ©rifie que tous les joueurs on placÃ© leurs pions

         while (isWinner != true) {
        	 // POUR CHAQUE JOUEUR
        	 int indiceJoueur = 0;
        	 for (Joueur joueur : participants) {
	        	 System.out.println("Joueur " + joueur.getNom());
	        	 
	        	 // INSTANCIATION VARIABLE DE STOCKAGE
	        	 // Variable stockant si un nouveau territoire a ete conquis ou non au cours des attaques
	        	 Boolean isNouveauTerritoireConquis = false;
	        	 // Variable stockant les choix du joueur
	        	 String choixAction = "null"; // choix du joueur dans le menu (cf plus bas)
        		 String choixDeplacer = "null"; // variable pour stocker si il y a validation des modifications des troupes, si il veut ajouter ou encore retirer de nouvelles troupes 
        		 
	        	 // PLACEMENT DES 20 REGIMENTS POUR LE PREMIER TOUR (7 DEJA PLACES)
        		 //Condition pour que les joueurs placent leurs pions sur le premier tour
	        	 if (nbJouer != 6) {
        			 while (joueur.getNbRegimentsRestants() != 0) {
		        		 for (Territoire territoire : joueur.getAllTerritoires()) {
		        			 //Affichage de l'ajout d'unitÃ©s sur un territoire retourne le nombre a ajoutÃ©
		        			 nbUnitesAjout = vue.premierTour(joueur, territoire);
		        			 territoire.ajouterNbRegiments(nbUnitesAjout);
		        			 joueur.enleverNbRegimentsRestants(nbUnitesAjout);
		        		 } 
		        	 }
        			 nbJouer +=1;
	        	 }
	        	 // PROCESSUS NORMAL POUR LES AUTRES TOURS
	        	 else {
		        	 // MISE A JOUR DU NOMBRE DE REGIMENTS QUE PEUT POSITIONNER UN JOUEUR EN DEBUT DE TOUR
	        		 // => selon nombre de territoires occupÃ©s et de continents complets occupÃ©s
	        		 int nbRegimentAPlacer = joueur.calculerNbRegimentsAPlacer();
	        		 joueur.ajouterNbRegimentsRestants(nbRegimentAPlacer);
	        		 System.out.println("regiment a placer : " + nbRegimentAPlacer);
	        		 
<<<<<<< HEAD
		        	 // AJOUT NOUVEAUX REGIMENTS
		        	 while (joueur.getNbRegimentsRestants() != 0) {
		        		 
		        	 /** @Raph BESOIN - Methode retournant un territoire et une quantitÃ© pour choisir le territoire oÃ¹ ajouter les troupes
		        	  *  >> Rappel condition : territoire.occupant == null || territoire.occupant == joueur
		        	  *  Sinon retourner fenetre message erreur territoire deja occupÃ© 
		        	  *  OU
		        	  *  Si galere je le bloque Ã  la mano dans le main 
		        	  *  
		        	  *  Pour simplifier, l'algo c'est qu'Ã  la phase d'ajout, il peuvent pas enlever quand ils posent ahah 
		        	  *  genre, il pose 2, puis 1 , quand il en a plus ca passe Ã  autre chose :3
		        	  */	    
=======
		        	 // AJOUT NOUVEAUX REGIMENTS   
>>>>>>> 72a604c4ed253e8e24dd9be195e6c53a25f92695
	        			 while (joueur.getNbRegimentsRestants() != 0) {
			        		 for (Territoire territoire : joueur.getAllTerritoires()) {
			        			 //Affichage de l'ajout d'unitÃ©s sur un territoire retourne le nombre a ajoutÃ©
			        			 nbUnitesAjout = vue.premierTour(joueur, territoire);
			        			 territoire.ajouterNbRegiments(nbUnitesAjout);
			        			 joueur.enleverNbRegimentsRestants(nbUnitesAjout);
			        		 } 
			        	 }
<<<<<<< HEAD
		        		 
		        	 /*
		        	 Territoire destTerritoireAjout = monde.getTerritoires().get(0);                    //    <== changer valeur
		        	 int nbRegimentsAjoutes = 1;														//    <== changer valeur
		        	 System.out.println("*Debut* Territoire : "+destTerritoireAjout.getNom()+" - Nb : "+destTerritoireAjout.getNbRegiments());
		        	 System.out.println("*Debut* Joueur : "+joueur.getNom()+" - Nb : "+joueur.getNbRegimentsRestants());
		        	 destTerritoireAjout.ajouterNbRegiments(nbRegimentsAjoutes); // Ajout rÃ©giment au territoire
		        	 joueur.enleverNbRegimentsRestants(nbRegimentsAjoutes); // Retrait nb au nb de regiment Ã  placer
		        	 System.out.println("*Fin* Territoire : "+destTerritoireAjout.getNom()+" - Nb : "+destTerritoireAjout.getNbRegiments());
		        	 System.out.println("*Fin* Joueur : "+joueur.getNom()+" - Nb : "+joueur.getNbRegimentsRestants()); */
		        	 }
=======
	        			 
	        		vue.actionsTour(joueur);
	        		
	        		System.out.println("Test de pause");
	        		
>>>>>>> 72a604c4ed253e8e24dd9be195e6c53a25f92695
		        	 
		        	 // CHOIX D ATTAQUER, MODIFIER SES TROUPES OU PASSER SON TOUR
		        	 // Tant que le tour du joueur n'est pas fini (continuer d'attaquer), on affiche la fenetre des choix 
		        	 while (choixAction == "Attaquer") {
			        	 /** @Raph BESOIN - Modifier ta methode choixJoueur pour qu'elle retourne le choix du joueur
			        	  *  Genre string "Attaquer", "DÃ©placer" ou "Passer tour"  par exeple 
			        	  *  OUTPUT : choixAction 
			        	  */
		        			
		        		 // LANCER UNE ATTAQUE
				            /** @Kun / @Yujie integrer dans le code : Si nouveau territoire conquis => isNouveauTerritoireConquis = true;*/
		        		 // Si le joueur clique sur l'option d'attaquer, il choisie le territoire d'attaque, de defense et le nombre de regiments pour attaquer
		        		 if (choixAction == "Attaquer") {
			        		   /** @Raph Demander choix pays attaquant, pays attaquÃ©, nombre de troupes 
			        			*  OUTPUT : territoireAttaquant, territoireDefenseur, nbRegimentsAttaque
			        			*/
			        			Territoire territoireAttaquant = monde.getTerritoires().get(0);      // DonnÃ©es tests Ã  mettre Ã  jour avec output !!!!
			        			Territoire territoireDefenseur = monde.getTerritoires().get(1);
			        			int nbRegimentsAttaque = 2;
			        			int nbRegimentsRiposte = 1;
			        			// Creation du conflit 
			        			Conflit conflit = new Conflit(joueur, territoireAttaquant, territoireDefenseur, nbRegimentsAttaque);
			        			/** @Raph Demander defenseur nb de troupes riposte
			        			 *  Input : conflit.getBlablabla...
			        			 *  Output : nbRegimentsRiposte
			        			 */
			        			// Resultat du conflit
			        			isNouveauTerritoireConquis = conflit.resultatConflit(nbRegimentsRiposte);
		        		 }
		        		 // DEPLACER CERTAINS DE SES REGIMENTS
		        		 // Si le joueur clique sur l'option deplacer, il choisie autant de deplacement qu'il souhaite (tant que les territoires sont voisins)
		        		 // Lorsque qu'il valide les changements, son tour est automatiquement terminÃ©
			        	 /** @Raph BESOIN - Modifier ta methode choixJoueur pour qu'elle retourne le choix du joueur
			        	  *  Genre string "Attaquer", "DÃ©placer" ou "Passer tour" c'est impec :)
			        	  */
		        		 else if (choixAction == "DÃ©placer") {
		        			while (choixDeplacer != "Valider") {
		        				/** @Raph choix joueur AJOUTER ou RETIRER (a chaque fin de choix si faisable) ou VALIDER
		        				 * => AJOUTER : territoire + nb regiments
		        				 * => RETIRER : territoire + nb regiments
		        				 *  >> Rappel conditions : 
		        				 *  - joueur occupe le territoire (cf Territoire.occupant == Joueur)
		        				 *  - pour le RETRAIT de troupes : nb de troupes retirÃ©es <= nb de troupes presentes (cd Territoire.nbRegiments)
		        				 *  - pour l'AJOUT : nb de troupes en stock >= nb de troupe Ã  ajouter 
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
			        				
			        				// Si le joueur a retirÃ© toutes ses troupes d'un territoire on met Ã  jour les data
			        				if (territoireModifie.getNbRegiments() == 0) {
			        					joueur.supprimerTerritoiresConquis(territoireModifie);
			        					territoireModifie.setOccupant(null);
			        				}
		        				}
		        			}
		        		 }
		        	 }
	        	 }
		         // Si le joueur a remportÃ© tous les territoires
		         int nbTerritoiresConquis = (int) joueur.getAllTerritoires().size();
		         if (nbTerritoiresConquis == monde.getNbTerritoireTotal()) {
		        	 isWinner = true;	
		         }
		         // Si le joueur a complÃ©tÃ© son objectif
		         if (isObjectifCompleted == true) {
		        	 isWinner = true;	
		         }
		         // Si le joueur n'a plus de territoire il est eliminÃ© 
		         if (nbTerritoiresConquis == 0) {
		        	int nouvelIndiceTableau = 0;
		        	// Copie des joueurs dans un tableau Ã  jour (sans le joueur eliminÃ©)
		        	Joueur[] participantsMaj = new Joueur[participants.length - 1];
		        	for (int k = 0; k < participants.length; k++) {
		        	    if (k != indiceJoueur) {
		        	    	participantsMaj[nouvelIndiceTableau] = participants[k];
		        	    	nouvelIndiceTableau = nouvelIndiceTableau+1;
		        	    }
		        	}
		        	// Mise Ã  jour du tableau des participants
		        	participants = participantsMaj;
		         }	 
        	 isWinner = true;																	// A supprimer (for testing only)
        	 //isWinner = true;																	// A supprimer (for testing only)
        	 // Incrementation indice joueur de 1
        	 indiceJoueur = indiceJoueur+1;
        	 }
         }
        
        //TODO Pour l'actualisation de l'affichage du jouer Ã  qui c'est le tour
        //vue.actualiserTour(tour);
	}
}

