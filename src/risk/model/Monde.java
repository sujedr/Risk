package risk.model;

import java.util.ArrayList;

public class Monde {
	
	private ArrayList<Continent> monde = new ArrayList<>(); 
	
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
       		
		/** Creation des listes de territoires par continents */
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
        
        ArrayList<Territoire> territoiresAmeriqueSud = new ArrayList<>();
        
        territoiresAmeriqueSud.add(ter201);
        territoiresAmeriqueSud.add(ter202);
        territoiresAmeriqueSud.add(ter203);
        territoiresAmeriqueSud.add(ter204);
        
        ArrayList<Territoire> territoiresAfrique = new ArrayList<>();
        
        territoiresAfrique.add(ter301);
        territoiresAfrique.add(ter302);
        territoiresAfrique.add(ter303);
        territoiresAfrique.add(ter304);
        territoiresAfrique.add(ter305);
        territoiresAfrique.add(ter306);
        
        ArrayList<Territoire> territoiresEurope = new ArrayList<>();
        
        territoiresAfrique.add(ter401);
        territoiresAfrique.add(ter402);
        territoiresAfrique.add(ter403);
        territoiresAfrique.add(ter404);
        territoiresAfrique.add(ter405);
        territoiresAfrique.add(ter406);
        territoiresAfrique.add(ter407);
        
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
	}
}
