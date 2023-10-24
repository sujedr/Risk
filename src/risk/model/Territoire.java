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
	
	/**
	 * Constructeur
	 * @param numero 
	 * @param nom
	 * @param centreX
	 * @param centreY
	 * @param voisins 
	 */
	public Territoire(int numero, String nom, int centreX, int centreY) {
		this.numero = numero;
		this.nom = nom;
		this.centreX = centreX;
		this.centreY = centreY;
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
}
