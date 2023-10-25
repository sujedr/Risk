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
	
	/** Attributs spécifiques à une manche */
	private HashMap<String, ArrayList<Territoire>> territoiresConquis = new HashMap<>();

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
	public Joueur(String id, String nom, String prenom, String dtNaissance, String[] nomsContinents) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dtNaissance = dtNaissance;
		
		/** Initialisation des continents dans la hashmap */			
		for (int i = 0; i < nomsContinents.length; i++) {
			this.territoiresConquis.put(nomsContinents[i], new ArrayList<Territoire>());
		}
		
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
	 * Retourne les territoires conquis par le joueur classés par continents
	 * @return HashMap<String, ArrayList<Territoire>>
	 */
	public HashMap<String, ArrayList<Territoire>> getTerritoiresConquis() {
		return territoiresConquis;
	}
	
	/**
	 * Ajoute un territoire conquis à la HashMap<Continent, Territoire> alias territoiresConquis du joueur 
	 * @param territoiresConquis
	 */
	public void ajouterTerritoiresConquis(Territoire territoire) {
		String nomContinent = territoire.getContinent().getNom();
        ArrayList<Territoire> territoiresParContinent = territoiresConquis.get(nomContinent);
        territoiresParContinent = new ArrayList<>();
        territoiresConquis.put(nomContinent, territoiresParContinent);
        territoiresParContinent.add(territoire);
	}
	
	/**
	 * Supprimer un territoire conquis à la HashMap<Continent, Territoire> alias territoiresConquis du joueur 
	 * @param territoiresConquis
	 */
	public void supprimerTerritoiresConquis(Territoire territoire) {
	    String nomContinent = territoire.getContinent().getNom();
	    ArrayList<Territoire> territoiresParContinent = territoiresConquis.get(nomContinent);
	    territoiresParContinent.remove(territoire);
	}

	@Override
	public String toString() {
		return "Joueur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", dtNaissance=" + dtNaissance
				+ ", territoiresConquis=" + territoiresConquis + ", nbTirageDes=" + Arrays.toString(nbTirageDes)
				+ ", nbAttaque=" + nbAttaque + ", nbDefense=" + nbDefense + ", nbTerritoire=" + nbTerritoire
				+ ", nbRegiments=" + nbRegiments + ", nbTours=" + nbTours + "]";
	}
}
