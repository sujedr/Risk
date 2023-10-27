package risk.model;

import java.util.ArrayList;

/**
 * Objet Territoire
 */
public class Territoire {
	private int numero;
	private String nom;
	private int centreX;
	private int centreY;
	private ArrayList<Territoire> voisins;
	private Joueur occupant;
	private int nbRegiments;
	private Continent continent;
	
	/**
	 * Constructeur
	 * @param numero 
	 * @param nom
	 * @param centreX
	 * @param centreY
	 */
	public Territoire(int numero, String nom, int centreX, int centreY) {
		this.numero = numero;
		this.nom = nom;
		this.centreX = centreX;
		this.centreY = centreY;
		this.nbRegiments = 0;
		this.voisins = new ArrayList<Territoire>();
	}
	
    /**
     * isInTerritory
     * @param x
     * @param y
     * @param tolerance
     * @return boolean is les coordonées sont dans le territoire
     */
    public boolean isInTerritory(int x, int y, int tolerance) {
        int distance = (int) Math.sqrt(Math.pow(x - centreX, 2) + Math.pow(y - centreY, 2));
        return distance <= tolerance;
    }

    /**
     * Getter numero
     * @return numero
     */
    public int getNumber() {
        return this.numero;
    }
    
    /**
     * Getter nom
     * @return nom
     */
    public String getNom() {
    	return this.nom;
    }
    
	/** 
	 * Getter joueur occupant
	 * @return Joueur
	 */
	public Joueur getOccupant() {
		return occupant;
	}

	/** 
	 * Setter joueur occupant
	 * @param Joueur occupant
	 */
	public void setOccupant(Joueur occupant) {
		this.occupant = occupant;
	}

	/**
	 * Getter nombre de régiment qu'à un joueur occupant sur un torritoire donné
	 * @return int nbRegiments
	 */
	public int getNbRegiments() {
		return nbRegiments;
	}

	public void setNbRegiments(int nbRegiments) {
		this.nbRegiments = nbRegiments;
	}

	/** Setter nombre de régiment qu'à un joueur occupant sur un territoire donné, ajout du nombre regiments à la valeur initiale
	 * @param nbRegiments
	 */
	public void ajouterNbRegiments(int nbRegimentsAjoutes) {
		this.nbRegiments = this.nbRegiments + nbRegimentsAjoutes ;
	}
	/** Setter nombre de régiment qu'à un joueur occupant sur un territoire donné, retrait du nombre regiments à la valeur initiale
	 * @param nbRegiments
	 */
	public void enleverNbRegiments(int nbRegimentsAjoutes) {
		this.nbRegiments = this.nbRegiments - nbRegimentsAjoutes ;
	}
	/**
	 * Retourne le continent auquel appartient le territoire
	 * @return Continent
	 */
	public Continent getContinent() {
		return continent;
	}

	/**
	 * Initialise le continent auquel appartient le territoire
	 * @param continent
	 */
	public void setContinent(Continent continent) {
		this.continent = continent;
	}
	
	public void ajouterVoisin(Territoire voisin) {
        // Ajoute le territoire voisin � la liste des voisins
        this.voisins.add(voisin);
    }
	
	/**
	 * Fonction non termin�e par manque de temps
	 * @return voisin d'un territoire donn�
	 */
	public ArrayList<Territoire> getVoisins() {
		return voisins;
	}

	@Override
	public String toString() {
		return "Territoire [nom=" + nom + "]";
	}


}
