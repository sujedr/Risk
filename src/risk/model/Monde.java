package risk.model;

import java.util.ArrayList;

/**
 * Classe Monde
 * Instancie l'ensemble des continents et leurs territoires
 */
public class Monde {
	
	/** Attribut liste de Continent */
	private ArrayList<Continent> monde = new ArrayList<>(); 
	final private int nbTerritoireTotal = 42;
	
	/**
	 * Constructeur Monde
	 * Creation des objets territoire
	 * Creation des objets continent
	 * Ajout des continents à l'attribut monde (liste des continents)
	 */
	public Monde() {
			
		/** Initialisation des territoires */
        Territoire ter101 = new Territoire(101, "Alaska", 84, 105);
        Territoire ter102 = new Territoire(102, "Territoire du Nord Ouest", 189, 106);
        Territoire ter103 = new Territoire(103, "Alberta", 160, 146);
        Territoire ter104 = new Territoire(104, "Ontario", 241, 155);
        Territoire ter105 = new Territoire(105, "Quebec", 320, 149);
        Territoire ter106 = new Territoire(106, "Etat de l'Ouest", 138, 204);
        Territoire ter107 = new Territoire(107, "Etat de l'Est", 221, 228);
        Territoire ter108 = new Territoire(108, "Amerique Centrale", 147, 294);
        Territoire ter109 = new Territoire(109, "Groenland", 453, 64);
        
        Territoire ter201 = new Territoire(201, "Venezuela", 264, 364);
        Territoire ter202 = new Territoire(202, "Perou", 273, 455);
        Territoire ter203 = new Territoire(203, "Bresil", 339, 436);
        Territoire ter204 = new Territoire(204, "Argentine", 301, 547);
        
        Territoire ter301 = new Territoire(301, "Afrique du Nord", 559, 306);
        Territoire ter302 = new Territoire(302, "Egypte", 648, 270);
        Territoire ter303 = new Territoire(303, "Congo", 646, 398);
        Territoire ter304 = new Territoire(304, "Afrique Orientale", 695, 347);
        Territoire ter305 = new Territoire(305, "Afrique du Sud", 650, 491);
        Territoire ter306 = new Territoire(306, "Madagascar", 751, 474);
        
        Territoire ter401 = new Territoire(401, "Europe Occidentale", 561, 195);
        Territoire ter402 = new Territoire(402, "Grande Bretagne", 546, 151);
        Territoire ter403 = new Territoire(403, "Islande", 506, 112);
        Territoire ter404 = new Territoire(404, "Scandinavie", 614, 112);
        Territoire ter405 = new Territoire(405, "Europe du Nord", 616, 162);
        Territoire ter406 = new Territoire(406, "Europe de l'Est", 643, 194);
        Territoire ter407 = new Territoire(407, "Ukraine", 703, 148);
        
        Territoire ter501 = new Territoire(501, "Afghanistan", 803, 186);
        Territoire ter502 = new Territoire(502, "Inde", 860, 272);
        Territoire ter503 = new Territoire(503, "Oural", 804, 121);
        Territoire ter504 = new Territoire(504, "Siberie", 871, 107);
        Territoire ter505 = new Territoire(505, "Chine", 907, 228);
        Territoire ter506 = new Territoire(506, "Japon", 1101, 222);
        Territoire ter507 = new Territoire(507, "Moyen Orient", 730, 262);
        Territoire ter508 = new Territoire(508, "Mongolie", 969, 188);
        Territoire ter509 = new Territoire(509, "Tchita", 950, 146);
        Territoire ter510 = new Territoire(510, "Yakoutie", 969, 106);
        Territoire ter511 = new Territoire(511, "Kamchatka", 1076, 106);
        Territoire ter512 = new Territoire(512, "Siam", 1076, 106);
        
        Territoire ter601 = new Territoire(601, "Indonesie", 1034, 392);
        Territoire ter602 = new Territoire(602, "Nouvelle Guinee", 1146, 420);
        Territoire ter603 = new Territoire(603, "Australie Occidentale", 1052, 517);
        Territoire ter604 = new Territoire(604, "Australie Orientale", 1147, 515);
        
        // Territoires voisin
        
        // Alaska
        ter101.ajouterVoisin(ter102); // Territoire du Nord Ouest
        ter101.ajouterVoisin(ter103); // Etat de l'Ouest
        ter101.ajouterVoisin(ter511); // Etat de l'Ouest

        // Territoire du Nord Ouest
        ter102.ajouterVoisin(ter101); // Alaska
        ter102.ajouterVoisin(ter103); // Alberta
        ter102.ajouterVoisin(ter104); // Etat de l'Ouest
        ter102.ajouterVoisin(ter109); // Etat de l'Est

        // Alberta
        ter103.ajouterVoisin(ter101); 
        ter103.ajouterVoisin(ter102); 
        ter103.ajouterVoisin(ter104); 
        ter103.ajouterVoisin(ter106);
        ter103.ajouterVoisin(ter107);

        // Ontario
        ter104.ajouterVoisin(ter102);
        ter104.ajouterVoisin(ter103); 
        ter104.ajouterVoisin(ter105);
        ter104.ajouterVoisin(ter106);
        ter104.ajouterVoisin(ter107);
        ter104.ajouterVoisin(ter109);

        // Quebec
        ter105.ajouterVoisin(ter109); 
        ter105.ajouterVoisin(ter104);
        ter105.ajouterVoisin(ter107);

        // Etat de l'Ouest
        ter106.ajouterVoisin(ter103); // Alaska
        ter106.ajouterVoisin(ter104); // Territoire du Nord Ouest
        ter106.ajouterVoisin(ter107); // Etat de l'Est
        ter106.ajouterVoisin(ter108); // Amerique Centrale

        // Etat de l'Est
        ter107.ajouterVoisin(ter104); // Territoire du Nord Ouest
        ter107.ajouterVoisin(ter105); // Etat de l'Ouest
        ter107.ajouterVoisin(ter106);
        ter107.ajouterVoisin(ter108); // Amerique Centrale

        // Amerique Centrale
        ter108.ajouterVoisin(ter106); // Etat de l'Ouest
        ter108.ajouterVoisin(ter107); // Etat de l'Est
        ter108.ajouterVoisin(ter201); // Perou
        
     // Perou
        ter201.ajouterVoisin(ter108); //  
        ter201.ajouterVoisin(ter203); // 
        ter201.ajouterVoisin(ter202); // 

        // Bresil
        ter203.ajouterVoisin(ter201); // 
        ter203.ajouterVoisin(ter202); // 
        ter203.ajouterVoisin(ter204); // 
        ter203.ajouterVoisin(ter301); 
        
        // Argentine
        ter204.ajouterVoisin(ter203); // 
        ter204.ajouterVoisin(ter202); // 

        // Afrique du Nord
        ter301.ajouterVoisin(ter203); // 
        ter301.ajouterVoisin(ter401); // 
        ter301.ajouterVoisin(ter303); // 
        ter301.ajouterVoisin(ter304); //  
        ter301.ajouterVoisin(ter302); // 
        
        ter303.ajouterVoisin(ter301);
        ter303.ajouterVoisin(ter304);
        ter303.ajouterVoisin(ter305);
        
        ter302.ajouterVoisin(ter301);
        ter302.ajouterVoisin(ter304);
        ter302.ajouterVoisin(ter507);
        ter302.ajouterVoisin(ter406);
        
     // Oural
        ter503.ajouterVoisin(ter501); // Afghanistan
        ter503.ajouterVoisin(ter502); // Inde
        ter503.ajouterVoisin(ter504); // Siberie

        // Siberie
        ter504.ajouterVoisin(ter502); // Inde
        ter504.ajouterVoisin(ter503); // Oural
        ter504.ajouterVoisin(ter505); // Chine
        ter504.ajouterVoisin(ter506); // Japon
        ter504.ajouterVoisin(ter510); // Yakoutie
        ter504.ajouterVoisin(ter511); // Kamchatka

        // Chine
        ter505.ajouterVoisin(ter502); // Inde
        ter505.ajouterVoisin(ter504); // Siberie
        ter505.ajouterVoisin(ter506); // Japon
        ter505.ajouterVoisin(ter507); // Moyen Orient
        ter505.ajouterVoisin(ter508); // Mongolie

        // Japon
        ter506.ajouterVoisin(ter502); // Inde
        ter506.ajouterVoisin(ter504); // Siberie
        ter506.ajouterVoisin(ter505); // Chine

        // Moyen Orient
        ter507.ajouterVoisin(ter505); // Chine
        ter507.ajouterVoisin(ter508); // Mongolie
        ter507.ajouterVoisin(ter510); // Yakoutie
        ter507.ajouterVoisin(ter512); // Siam

        // Mongolie
        ter508.ajouterVoisin(ter501); // Afghanistan
        ter508.ajouterVoisin(ter505); // Chine
        ter508.ajouterVoisin(ter507); // Moyen Orient
        ter508.ajouterVoisin(ter509); // Tchita
        ter508.ajouterVoisin(ter511); // Kamchatka

        // Tchita
        ter509.ajouterVoisin(ter508); // Mongolie
        ter509.ajouterVoisin(ter510); // Yakoutie
        ter509.ajouterVoisin(ter511); // Kamchatka

        // Yakoutie
        ter510.ajouterVoisin(ter504); // Siberie
        ter510.ajouterVoisin(ter507); // Moyen Orient
        ter510.ajouterVoisin(ter509); // Tchita
        ter510.ajouterVoisin(ter511); // Kamchatka

        // Kamchatka
        ter511.ajouterVoisin(ter504); // Siberie
        ter511.ajouterVoisin(ter508); // Mongolie
        ter511.ajouterVoisin(ter509); // Tchita
        ter511.ajouterVoisin(ter510); // Yakoutie
        ter511.ajouterVoisin(ter512); // Siam

        // Siam
        ter512.ajouterVoisin(ter507); // Moyen Orient
        ter512.ajouterVoisin(ter511); // Kamchatka

        // Indonesie
        ter601.ajouterVoisin(ter306); // Madagascar
        ter601.ajouterVoisin(ter602); // Nouvelle Guinee
        ter601.ajouterVoisin(ter603); // Australie Occidentale

        // Nouvelle Guinee
        ter602.ajouterVoisin(ter306); // Madagascar
        ter602.ajouterVoisin(ter601); // Indonesie
        ter602.ajouterVoisin(ter603); // Australie Occidentale

        // Australie Occidentale
        ter603.ajouterVoisin(ter601); // Indonesie
        ter603.ajouterVoisin(ter602); // Nouvelle Guinee
        ter603.ajouterVoisin(ter604); // Australie Orientale

        // Australie Orientale
        ter604.ajouterVoisin(ter603); // Australie Occidentale
        ter604.ajouterVoisin(ter504); // Siberie

        
        

       		
		/** Creation des listes de territoires par continents */
        // Amerique du Nord
        ArrayList<Territoire> territoiresAmeriqueNord = new ArrayList<>();
        territoiresAmeriqueNord.add(ter101);
        territoiresAmeriqueNord.add(ter102);
        territoiresAmeriqueNord.add(ter103);
        territoiresAmeriqueNord.add(ter104);
        territoiresAmeriqueNord.add(ter105);
        territoiresAmeriqueNord.add(ter106);
        territoiresAmeriqueNord.add(ter107);
        territoiresAmeriqueNord.add(ter108);
        territoiresAmeriqueNord.add(ter109);
        
        // Amerique du Sud
        ArrayList<Territoire> territoiresAmeriqueSud = new ArrayList<>();
        territoiresAmeriqueSud.add(ter201);
        territoiresAmeriqueSud.add(ter202);
        territoiresAmeriqueSud.add(ter203);
        territoiresAmeriqueSud.add(ter204);
        
        // Afrique
        ArrayList<Territoire> territoiresAfrique = new ArrayList<>();
        territoiresAfrique.add(ter301);
        territoiresAfrique.add(ter302);
        territoiresAfrique.add(ter303);
        territoiresAfrique.add(ter304);
        territoiresAfrique.add(ter305);
        territoiresAfrique.add(ter306);
        
        // Europe
        ArrayList<Territoire> territoiresEurope = new ArrayList<>();
        territoiresEurope.add(ter401);
        territoiresEurope.add(ter402);
        territoiresEurope.add(ter403);
        territoiresEurope.add(ter404);
        territoiresEurope.add(ter405);
        territoiresEurope.add(ter406);
        territoiresEurope.add(ter407);
        
        // Asie
        ArrayList<Territoire> territoiresAsie = new ArrayList<>();
        territoiresAsie.add(ter501);
        territoiresAsie.add(ter502);
        territoiresAsie.add(ter503);
        territoiresAsie.add(ter504);
        territoiresAsie.add(ter505);
        territoiresAsie.add(ter506);
        territoiresAsie.add(ter507);
        territoiresAsie.add(ter508);
        territoiresAsie.add(ter509);
        territoiresAsie.add(ter510);
        territoiresAsie.add(ter511);
        territoiresAsie.add(ter512);
        
        // Oceanie
        ArrayList<Territoire> territoiresOceanie = new ArrayList<>();
        territoiresOceanie.add(ter601);
        territoiresOceanie.add(ter602);
        territoiresOceanie.add(ter603);
        territoiresOceanie.add(ter604);
        
		/** Initialisation des continents */
		Continent europe = new Continent("Europe", territoiresEurope);
		Continent asie = new Continent("Asie", territoiresAsie);
		Continent oceanie = new Continent("Oceanie", territoiresOceanie);
		Continent afrique = new Continent("Afrique", territoiresAfrique);
		Continent ameriqueNord = new Continent("Amerique du Nord", territoiresAmeriqueNord);
		Continent ameriqueSud = new Continent("Amerique du Sud", territoiresAmeriqueSud);
		
		System.out.println(territoiresEurope);
		/** Ajout des continents à la liste finale (attribut monde) */
		this.monde.add(ameriqueSud);
		this.monde.add(ameriqueNord);
		this.monde.add(afrique);
		this.monde.add(europe);
		this.monde.add(oceanie);
		this.monde.add(asie);
		
		/** Insertion de l'attribut contienent de chaque territoire */
		for (Territoire territoire : ameriqueSud.getTerritoires()) {
			territoire.setContinent(ameriqueSud);
		}
		for (Territoire territoire : ameriqueNord.getTerritoires()) {
			territoire.setContinent(ameriqueNord);
		}
		for (Territoire territoire : afrique.getTerritoires()) {
			territoire.setContinent(afrique);
		}
		for (Territoire territoire : europe.getTerritoires()) {
			territoire.setContinent(europe);
		}
		for (Territoire territoire : oceanie.getTerritoires()) {
			territoire.setContinent(oceanie);
		}
		for (Territoire territoire : asie.getTerritoires()) {
			territoire.setContinent(asie);
		}
	}

	// Methodes
	/**
	 * Getter monde
	 * @return ArrayLise<Continent> monde
	 */	
	public ArrayList<Continent> getMonde() {
		return monde;
	}
	
	/**
	 * Getter nom continents
	 * @return ArrayLise<String> monde
	 */	
	public String[] getNomContinent() {
		String[] nomContinents = new String[6];
		for (int i = 0; i < this.monde.size(); i++) {
			nomContinents[i] = this.monde.get(i).getNom();
		}
		return nomContinents;
	}
	/**
	 * @return int nbTerritoireTotal
	 */
	public int getNbTerritoireTotal() {
		return nbTerritoireTotal;
	}
	
	/** 
	 * Retourne une liste contenant l'ensemble des territoires du monde, touut continent confondu
	 * 
	 * @return ArrayList<Territoire> 
	 */
	public ArrayList<Territoire> getTerritoires() {
		// Liste finale des territoires
		ArrayList<Territoire> territoires = new ArrayList<>();
		// Parcours de chaque continent
		for (int i = 0; i < this.monde.size(); i++) {
			// Parcours de chaque territoires
			Continent continent = this.monde.get(i);
			for (int j = 0; j < continent.getTerritoires().size(); j++) {
				Territoire territoire = continent.getTerritoires().get(j);
	            territoires.add(territoire);
			}	
		}
		return territoires;
	}

	@Override
	public String toString() {
		return "Monde [monde=" + monde + ", getMonde()=" + getMonde() + ", getTerritoires()=" + getTerritoires() + "]";
	}
	
	

}
