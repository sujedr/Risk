package risk.model;
import java.util.ArrayList;
import risk.model.LancerDes;

/**
 * Class Clonflit à jour
 * Archive (version scanner => old version)
 *
 */
public class Conflit {
	
	private Joueur attaquant;
	private Joueur defenseur;
	private Territoire territoireAttaquant;
	private Territoire territoireDefenseur;
	private int nbRegimentAttaquant;
	private int nbRegimentDefenseur;
	private ArrayList<Integer> desAttaque;
	private ArrayList<Integer> desDefense;

	
	public Conflit(Joueur attaquant, Territoire territoireAttaquant, Territoire territoireDefenseur, int nbRegimentAttaquant) {
		this.attaquant = attaquant;
		this.territoireAttaquant = territoireAttaquant;
		this.territoireDefenseur = territoireDefenseur;
		this.nbRegimentAttaquant = nbRegimentAttaquant;
		this.defenseur = territoireDefenseur.getOccupant();
	}	
	
	/**
	 * @return ArrayList<Integer> desAttaque 
	 */
	public ArrayList<Integer> getDesAttaque() {
		return desAttaque;
	}
	/**
	 * @return Terrtoire territoireAttaquant
	 */
	public Territoire getTerritoireAttaquant() {
		return territoireAttaquant;
	}
	/**
	 * @return Territoire getTerritoireDefenseur
	 */
	public Territoire getTerritoireDefenseur() {
		return territoireDefenseur;
	}
	/**
	 * @return int nbRegimentAttaquant
	 */
	public int getNbRegimentAttaquant() {
		return nbRegimentAttaquant;
	}
	/**
	 * @return joueur defenseur
	 */
	public Joueur getDefenseur() {
		return defenseur;
	}
	/**
	 * @return joueur attaquant
	 */
	public Joueur getAttaquant() {
		return attaquant;
	}    
	/**
	 * @return int nbRegimentDefenseur
	 */
	public int getNbRegimentDefenseur() {
		return nbRegimentDefenseur;
	}
	
	/**
	 * @param nbRegimentsRiposte
	 * @return .... a definir ....
	 */
	public int resultatConflit(int nbRegimentsRiposte) {
		// Setting nb regiment attaquant
		this.nbRegimentDefenseur = nbRegimentsRiposte;
		// Lancer dès attaquant et enregistrer resultat pour traitement data en BD
		this.desAttaque = desAttaquer();
		// Lancer dès défenseur et enregistrer resultat pour traitement data en BD
		this.desDefense = desDefenseur();
		
		// Gestion des resultats des lancés de dès
	    // 循环比较desDefense的长度次
	    int iterations = desDefense.size();
	    int nbSurvivant = desAttaque.size();
	    for (int i = 0; i < iterations; i++) {
	        int attaque = desAttaque.get(i);
	        int defense = desDefense.get(i);

	        // 如果desAttaque的值大于desDefense，则territoireDefenseur上的兵数减去1
	        if (attaque > defense) {
	            int nbRegimentsDefenseur = territoireDefenseur.getNbRegiments();
	            if (nbRegimentsDefenseur > 0) {
	                territoireDefenseur.setNbRegiments(nbRegimentsDefenseur - 1);
	            }
	        } else {
	            // 否则territoireAttaquant上的兵数减去1
	            int nbRegimentsAttaquant = territoireAttaquant.getNbRegiments();
	            if (nbRegimentsAttaquant > 0) {
	                territoireAttaquant.setNbRegiments(nbRegimentsAttaquant - 1);
	            }
	            nbSurvivant=nbSurvivant-1;
	        }
	    }
	    System.out.println(this.desAttaque);
	    System.out.println(this.desDefense);
	    System.out.println(nbSurvivant);
	    return nbSurvivant;
	}
	
    /**
     * @return ArrayList<Integer> resultats
     */
    public ArrayList<Integer> desAttaquer() {
        ArrayList<Integer> resultats = new ArrayList<>();

        for (int i = 0; i < this.nbRegimentAttaquant; i++) {
        	LancerDes lancerDes=new LancerDes();
            int resultat = lancerDes.getResultatDes();
            resultats.add(resultat);
        }
        return resultats;
    }
    
    /**
     * @return ArrayList<Integer> resultats
     */
    public ArrayList<Integer> desDefenseur() {
        ArrayList<Integer> resultats = new ArrayList<>();
        for (int i = 0; i < this.nbRegimentDefenseur; i++) {
        	LancerDes lancerDes=new LancerDes();
            int resultat = lancerDes.getResultatDes();
            resultats.add(resultat);
        }
        return resultats;
    }
}
