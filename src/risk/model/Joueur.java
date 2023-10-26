package risk.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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
	
	/** Attributs spécifiques à une manche */
	private HashMap<Continent, ArrayList<Territoire>> territoiresConquis;
	private int nbRegimentsRestants;
	
	/** Attributs statistiques */           // *** A peut etre supprimer ***
	private int[] nbTirageDes = new int[6];
	private int nbAttaque;
	private int nbDefense;
	private int nbTerritoire;
	private int nbRegiments;
	private int nbTours;

	/**
	 * Constructeur
	 * @param id
	 * @param nom
	 * @param prenom
	 * @param dtNaissance
	 */
	public Joueur(String id, String nom, String prenom, String dtNaissance, Continent[] Continents, String couleur) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dtNaissance = dtNaissance;
		this.couleur = couleur;
		
		/** Initialisation des continents dans la hashmap */			
		for (int i = 0; i < Continents.length; i++) {
			this.territoiresConquis.put(Continents[i], new ArrayList<Territoire>());
		}
		this.nbRegimentsRestants = 0;
		this.ajouterNbRegimentsRestants(3);
		
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
	
	// Getter and setter
	
	public String getCouleur() {
		return couleur;
	}
	
	
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
	
	public int calculerNbRegimentsAPlacer() {
   	 // Continent conquis ?
   	 ArrayList<String> continentsOccupes = new ArrayList<String>();
   	 int total = 0;
   	 continentsOccupes = this.consulterContinentsEntierementOccupes();
   	 int ajoutCauseContinent = 0;	        	 
   	 // Parcours de continent de la liste (conquis)
     for (String continent : continentsOccupes) {
        	if (continent == "Europe") {
        		ajoutCauseContinent = 7;
            }
            else if (continent == "Asie") {
            	ajoutCauseContinent = 12;
            }
            else if (continent == "Amerique du Nord") {
            	ajoutCauseContinent = 9;
            }
            else if (continent == "Amerique du Sud") {
            	ajoutCauseContinent = 4;
            }
            else if (continent == "Afrique") {
            	ajoutCauseContinent = 6;
            }
            else if (continent == "Oceanie") {
            	ajoutCauseContinent = 4;
            }
        }
     int ajoutCauseTerritoire = 3;
     int totalTerritoires = this.getAllTerritoires().size();
     ajoutCauseTerritoire = totalTerritoires / 3; ////// A finir
     
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
	
<<<<<<< HEAD
	/**
	 * @return Arraylist <String> nom continent conquis
	 */
	public ArrayList<String> consulterContinentsEntierementOccupes() {
		ArrayList<String> continentsConquis = new ArrayList<>();
		// Parcours de chaque continent dans le dico de stockage des territoires conquis
        for (String continent : this.territoiresConquis.keySet()) {
=======
	public HashMap <String, Boolean> consulterContinentsEntierementOccupes() {
		HashMap <String, Boolean> continentsOccupes = new HashMap();
		
        for (Continent continent : this.territoiresConquis.keySet()) {
>>>>>>> cadbc60bc7fe1e85f479ae7db3dfc482d881dcd1
            int countContinents = this.territoiresConquis.get(continent).size();
            int totalContinents = 0;
            // Vérfication du nombre de territoires par continents
            if (continent == Europe) {
            	totalContinents = 7;
            }
            else if (continent == Asie) {
            	totalContinents = 12;
            }
            else if (continent == AmeriqueDuNord) {
            	totalContinents = 9;
            }
            else if (continent == AmeriqueDuSud) {
            	totalContinents = 4;
            }
            else if (continent == Afrique) {
            	totalContinents = 6;
            }
            else if (continent == Oceanie) {
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

	@Override
	public String toString() {
		return "Joueur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", dtNaissance=" + dtNaissance
				+ ", territoiresConquis=" + territoiresConquis + ", nbRegimentsRestants=" + nbRegimentsRestants
				+ ", nbTirageDes=" + Arrays.toString(nbTirageDes) + ", nbAttaque=" + nbAttaque + ", nbDefense="
				+ nbDefense + ", nbTerritoire=" + nbTerritoire + ", nbRegiments=" + nbRegiments + ", nbTours=" + nbTours
				+ "]";
	}
}
