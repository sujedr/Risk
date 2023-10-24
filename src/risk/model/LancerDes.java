package risk.model;
import java.util.Random;

public class LancerDes {

	// Attributs
	private int resultatDes;
	
	/**
	 * 
	 */
	public LancerDes() {
		this.resultatDes = resultatDes;
	}
	
	
	/** Obtenir resultat du lancé de dès 
	 * @return entier correspondant au résultat (de 1 à 6)
	 */
	public int getResultatDes() {
		return resultatDes;
	}

	/**
	 * @return int : résultat du tirage du dès (entre 1 et 6)
	 */
	public int lancerDes() {
        Random random = new Random();
        return random.nextInt(6)+1; 
    }
}