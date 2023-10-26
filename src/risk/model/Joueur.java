package risk.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

/**
 * Objet joueur
 */
public class Joueur {
	/** Attributs informations joueur */
	private String id; 
	private String nom;
	private String prenom;
	private String dtNaissance;
	
	private String couleur;
	private String currentmission;
	/** Attributs spécifiques à une manche */
	private HashMap<Continent, ArrayList<Territoire>> territoiresConquis = new HashMap<>() ;
	private int nbRegimentsRestants;
	
	/** Attributs statistiques */           // *** A peut etre supprimer ***
	private int[] nbTirageDes = new int[6];
	private int nbAttaque;
	private int nbDefense;
	private int nbTerritoire;
	private int nbRegiments;
	private int nbTours;

	ArrayList<Continent> continentsConquis = new ArrayList<>();
	
	/**
	 * Constructeur
	 * @param id
	 * @param nom
	 * @param prenom
	 * @param dtNaissance
	 */
	public Joueur(String id, String nom, String prenom, String dtNaissance, ArrayList<Continent> Continents,String couleur) {

		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dtNaissance = dtNaissance;
		this.couleur = couleur;
		
		/** Initialisation des continents dans la hashmap */			
		for (int i = 0; i < Continents.size(); i++) {
			this.territoiresConquis.put(Continents.get(i), new ArrayList<Territoire>());
		}
		this.nbRegimentsRestants = 20;
		
		/** Initialisation des attributs statistique à zero */         //*** A peut etre supprimer ***
		for (int i=0; i<6; i++) {
			this.nbTirageDes[i] = 0;
		}
		this.nbAttaque = 0;
		this.nbDefense = 0;
		this.nbTerritoire = 0;
		this.nbRegiments = 0;
		this.nbTours = 0;
	}
	
	// Couleur choisie par le joueur 玩家所选的颜色
		public String getCouleur() {
			return couleur;
		}
		
		//Distribuer les cartes de mission aux joueurs 分发任务卡牌给玩家
		public void DistribuerRandomMission(ArrayList<String> listeMission) {
			Random random = new Random();
			int randomIndex = random.nextInt(listeMission.size());
			this.currentmission = listeMission.remove(randomIndex);
			System.out.println(
			"La mission du joueur " + id + " : " + currentmission);
		}
	
	
	
	// Getter and setter
	
	/** @return int */
	public String getId() {
		return id;
	}
	/** @param id */
	public void setId(String id) {
		this.id = id;
	}
	/** @return String */
	public String getNom() {
		return nom;
	}
	/** @param nom */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/** @return String */
	public String getPrenom() {
		return prenom;
	}
	/** @param prenom */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	/** @return Date */
	public String getDtNaissance() {
		return dtNaissance;
	}
	/** @param dtNaissance */
	public void setDtNaissance(String dtNaissance) {
		this.dtNaissance = dtNaissance;
	}
	/**
	 * Nombre de régiments à placer sur la carte
	 * @return nbRegimentsRestants
	 */
	public int getNbRegimentsRestants() {
		return nbRegimentsRestants;
	}

	/**
	 * Ajoute des régiments au nombre initial
	 * @param nbRegimentsRestants
	 */
	public void ajouterNbRegimentsRestants(int nbRegimentsBonus) {
		this.nbRegimentsRestants =  this.nbRegimentsRestants + nbRegimentsBonus;
	}
	/**
	 * Enlève des régiments au nombre initial
	 * @param nbRegimentsRestants
	 */
	public void enleverNbRegimentsRestants(int nbRegimentsMalus) {
		this.nbRegimentsRestants =  this.nbRegimentsRestants - nbRegimentsMalus;
	}
	/**
	 * Retourne les territoires conquis par le joueur classés par continents
	 * @return HashMap<String, ArrayList<Territoire>>
	 */
	public HashMap<Continent, ArrayList<Territoire>> getTerritoiresConquis() {
		return territoiresConquis;
	}
	
	/**
	 * @return Liste de territoires
	 */
	public ArrayList<Territoire> getAllTerritoires() {
		HashMap<Continent, ArrayList<Territoire>> map = new HashMap<>(this.territoiresConquis);
		ArrayList<Territoire> allTerritoires = new ArrayList<>();

	    // 遍历HashMap中的所有值（ArrayList<Territoire>）
	    for (ArrayList<Territoire> territoriesList : map.values()) {
	        // 遍历每个ArrayList<Territoire>，将其中的Territoire添加到allTerritoires中
	        for (Territoire territoire : territoriesList) {
	            allTerritoires.add(territoire);
	        }
	    }
	    return allTerritoires;
	}
	
	public ArrayList<String> getAllTerritoiresClear() {
		ArrayList<String>liste = new ArrayList<>();
		for (Territoire territoire : this.getAllTerritoires()) {
			liste.add(territoire.getNom());
		}
		return liste;
	}
	
	/**
	 * @return int nb de regiments que le joueur dois placer en début de tour
	 */
	public int calculerNbRegimentsAPlacer() {
   	 // Continent conquis ?  
   	 ArrayList<Continent> continentsOccupes = new ArrayList<Continent>();
   	 int total = 0;
   	 continentsOccupes = this.consulterContinentsEntierementOccupes();
   	 int ajoutCauseContinent = 0;	        	 
   	 // Parcours de continent de la liste (conquis)
     for (Continent continent : continentsOccupes) {
        	if (continent.getNom() == "Europe") {
        		ajoutCauseContinent = 7;
            }
            else if (continent.getNom() == "Asie") {
            	ajoutCauseContinent = 12;
            }
            else if (continent.getNom() == "Amerique du Nord") {
            	ajoutCauseContinent = 9;
            }
            else if (continent.getNom() == "Amerique du Sud") {
            	ajoutCauseContinent = 4;
            }
            else if (continent.getNom() == "Afrique") {
            	ajoutCauseContinent = 6;
            }
            else if (continent.getNom() == "Oceanie") {
            	ajoutCauseContinent = 4;
            }
        }
     int ajoutCauseTerritoire = 3;
     int totalTerritoires = this.getAllTerritoires().size();
     // On calcul uniquement si la quantité de territoire repond au seuil min de regiments
     // 9/9 = 3 qui est le minimum de troupe que recoit un joueur
     if (totalTerritoires >= 9) {
    	 ajoutCauseTerritoire = totalTerritoires / 3; 
     }
    total = total + ajoutCauseTerritoire + ajoutCauseContinent;
	return total;
	}

	/**
	 * Ajoute un territoire conquis à la HashMap<Continent, Territoire> alias territoiresConquis du joueur 
	 * @param territoiresConquis
	 */
	public void ajouterTerritoiresConquis(Territoire territoire) {
		Continent continent = territoire.getContinent();
        territoiresConquis.get(continent).add(territoire);
	}
	
	/**
	 * Supprimer un territoire conquis à la HashMap<Continent, Territoire> alias territoiresConquis du joueur 
	 * @param territoiresConquis
	 */
	public void supprimerTerritoiresConquis(Territoire territoire) {
		Continent continent = territoire.getContinent();
		territoiresConquis.get(continent).remove(territoire);
	}
	
	/**
	 * @return Arraylist <Continent> nom continent conquis
	 */
	public ArrayList<Continent> consulterContinentsEntierementOccupes() {

		// Parcours de chaque continent dans le dico de stockage des territoires conquis
        for (Continent continent : this.territoiresConquis.keySet()) {
            int countContinents = this.territoiresConquis.get(continent).size();
            int totalContinents = 0;
            // Vérfication du nombre de territoires par continents
            if (continent.getNom() == "Europe") {
            	totalContinents = 7;
            }
            else if (continent.getNom() == "Asie") {
            	totalContinents = 12;
            }
            else if (continent.getNom() == "AmeriqueDuNord") {
            	totalContinents = 9;
            }
            else if (continent.getNom() == "AmeriqueDuSud") {
            	totalContinents = 4;
            }
            else if (continent.getNom() == "Afrique") {
            	totalContinents = 6;
            }
            else if (continent.getNom() == "Oceanie") {
            	totalContinents = 4;
            }
            // Si le joueur à tous les territoires d'un continent on note la conquete du continent dans le dico
            System.out.println("count "+countContinents+" vs. total "+totalContinents);
            if (countContinents == totalContinents) {
            	continentsConquis.add(continent);
            }
        }
        return continentsConquis;
	}
	
	public void MissionRussie() {
		//MissionRussie : conquérir toute l'Amérique du Nord et l'Afrique 
		//完成任务征服整个北美洲和非洲
		if(this.currentmission == "Vous devez conquérir en totalité l'Asie et l'Amérique du sud.") {
			boolean ConquerirAmNord = false;
			boolean ConquerirAfri = false;
			for(int i=0;i<this.continentsConquis.size();i++) {
				if(continentsConquis.get(i).getNom()=="AmeriqueDuNord") {ConquerirAmNord=true;}
				if(continentsConquis.get(i).getNom()=="Afrique") {ConquerirAfri=true;}
			}
			if (ConquerirAmNord&&ConquerirAfri) {
				System.out.println("Vous avez gagné !");
			}
		}
		
		
		
	}
	
	
	
	@Override
	public String toString() {
		return "Joueur [nom=" + nom + ", prenom=" + prenom + "]";
	}
}
