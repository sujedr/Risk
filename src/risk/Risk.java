package risk;

import risk.vue.Fenetre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.util.List;
import java.util.Random;
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
        Fenetre vue = new Fenetre(territoires); // Crée une instance de Fenetre
        
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
		
		// générer les cartes en associant chaque carte à un territoire 
		List<String> nomsTerritoires = new ArrayList<>();
		for (Territoire nomTerritoire : territoires) {
			nomsTerritoires.add(nomTerritoire.getNom());
		}
		
		List<String> typesRegiments = new ArrayList<>();
        typesRegiments.add("Infanterie");
        typesRegiments.add("Cavalerie");
        typesRegiments.add("Artillerie");
        
        List<Carte> cartes = new ArrayList<>();
        Random random = new Random();
        for (String territoire : nomsTerritoires) {
            int randRegiment = random.nextInt(typesRegiments.size());
            String typeRegiment = typesRegiments.get(randRegiment);
            cartes.add(new Carte(territoire, typeRegiment));
        }

        // afficher toutes les cartes
        for (Carte carte : cartes) {
            System.out.println("CARTE : Territoire : " + carte.getTerritoire() + ", Type de Régiment : " + carte.getTypeRegiment());
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
        System.out.println("--------------------------");  

        // DEBUT DE LA MANCHE
         boolean isWinner = false;
         while (isWinner != true) {
        	 // POUR CHAQUE JOUEUR
        	 for (Joueur joueur : participants) {
	        	 System.out.println("Joueur " + joueur.getNom());
	        	 
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
	        	 destTerritoireAjout.setNbRegiments(nbRegimentsAjoutes); // Ajout régiment au territoire
	        	 joueur.enleverNbRegimentsRestants(nbRegimentsAjoutes); // Retrait nb au nb de regiment à placer
	        	 System.out.println("*Fin* Territoire : "+destTerritoireAjout.getNom()+" - Nb : "+destTerritoireAjout.getNbRegiments());
	        	 System.out.println("*Fin* Joueur : "+joueur.getNom()+" - Nb : "+joueur.getNbRegimentsRestants());
	        	 }
	        	 
	        	 // Variable stockant si un nouveau territoire a ete conquis ou non au cours des attaques
	        	 Boolean isNouveauTerritoireConquis = false;
	        	 // Variable stockant si un nouveau continent a ete conquis ou non au cours des attaques
	        	 HashMap <String, Boolean> stockContinentsOccupes = new HashMap();
	        	 stockContinentsOccupes = joueur.consulterContinentsEntierementOccupes();


	        	 String choix = "null"; // choix du joueur dans le menu (cf plus bas)
        		 Boolean valider = false; // variable pour stocker si il y a validation des modifications des troupes ou non 

	        	 // CHOIX D ATTAQUER, MODIFIER SES TROUPES OU PASSER SON TOUR
	        	 // Tant que le tour du joueur n'est pas fini (decision de passer son tour), on affiche la fenetre des choix 
	        	 while (choix != "Passer tour") {
		        	 /** @Raph BESOIN - Modifier ta methode choixJoueur pour qu'elle retourne le choix du joueur
		        	  *  Genre string "Attaquer", "Déplacer" ou "Passer tour" c'est impec :)
		        	  */
	        		 // LANCER UNE ATTAQUE
	        		 // Si le joueur clique sur l'option d'attaquer, il choisie le territoire d'attaque, de defense et le nombre de regiments pour attaquer
	        		 if (choix == "Attaquer") {
			        	 /** @Kun / @Yujie 
			        	  * Attaque / Défense
			        	  */
	        		 }
	        		 // DEPLACER CERTAINS DE SES REGIMENTS
	        		 // Si le joueur clique sur l'option deplacer, il choisie autant de deplacement qu'il souhaite (tant que les territoires sont voisins)
	        		 // Lorsque qu'il valide les changements, son tour est automatiquement terminé
		        	 /** @Raph BESOIN - Modifier ta methode choixJoueur pour qu'elle retourne le choix du joueur
		        	  *  Genre string "Attaquer", "Déplacer" ou "Passer tour" c'est impec :)
		        	  */
	        		 else if (choix == "Déplacer") {
	        			while (valider != true) {
	        				
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

