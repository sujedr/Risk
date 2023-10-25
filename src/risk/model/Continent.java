package risk.model;

import java.util.ArrayList;

/**
 * Objet Continent
 *
 */
public class Continent {
	
	// Attributs
	private String nom;
	private ArrayList<Territoire> territoires = new ArrayList<Territoire>();
	
	// Constructeur
	public Continent(String nom, ArrayList<Territoire> territoires) {
		this.nom = nom;
		this.territoires = territoires;
	}
	
	// Getter and setter
	
	/** Get nom du continent
	 * @return String nom
	 */
	public String getNom() {
		return nom;
	}
	
	/** Get tous les territoires composant un continent
	 * @return arraylist<territoire> territoires
	 */
	public ArrayList<Territoire> getTerritoires() {
		return territoires;
	}

	/** Set les territoires composant un continent
	 * @param territoires
	 */
	public void setTerritoires(ArrayList<Territoire> territoires) {
		this.territoires = territoires;
	}
}