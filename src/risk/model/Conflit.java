package risk.model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
		// Classement des dès du plus grand au plus petit
        Collections.sort(desAttaque, Collections.reverseOrder());
        Collections.sort(desDefense, Collections.reverseOrder());
        
	    System.out.println("ATTAQUANT : "+ this.desAttaque);
	    System.out.println("DEFENDEUR : "+ this.desDefense);
	    
	    System.out.println("\nAVANT");
	    System.out.println("ATTAQUANT : "+ this.territoireAttaquant.getNbRegiments());
	    System.out.println("DEFENDEUR : "+this.territoireDefenseur.getNbRegiments());
	    
	    // 循环比较desDefense的长度次
	    int iterations = desDefense.size();
	    int nbSurvivant = desAttaque.size();
	    
	    // Comparaison des dès par ordre de grandeur
	    for (int i = 0; i < iterations; i++) {
	        int attaque = desAttaque.get(i);
	        int defense = desDefense.get(i);

	        // 如果desAttaque的值大于desDefense，则territoireDefenseur上的兵数减去1
	        // Pour chaque dès, si le resultat de l'attaquant est plus grand que celui de la défense, le defenseur perd un regiment
	        if (attaque > defense) {
	            int nbRegimentsDefenseur = territoireDefenseur.getNbRegiments();
	            if (nbRegimentsDefenseur > 0) {
	                territoireDefenseur.setNbRegiments(nbRegimentsDefenseur - 1);
	                
	            }
	        // 否则territoireAttaquant上的兵数减去1
		    // Pour chaque dès, si le resultat de l'attaquant est égale ou plus petit que celui de la défense, l' attaquant perd un regiment
	        } else {
	            int nbRegimentsAttaquant = territoireAttaquant.getNbRegiments();
	            if (nbRegimentsAttaquant > 0) {
	                territoireAttaquant.setNbRegiments(nbRegimentsAttaquant - 1);
	            }
	            nbSurvivant=nbSurvivant-1;
	        }
	    }
	    System.out.println("\nAPRES");
	    System.out.println("ATTAQUANT : "+this.territoireAttaquant.getNbRegiments());
	    System.out.println("DEFENDEUR : "+this.territoireDefenseur.getNbRegiments());
	    
	    System.out.println(nbSurvivant); // check a quoi ca correspond
	    // mettre à jour troupes si pays conquis (cf rules fonction yujie)
	    // + boolean de victoire territoire
	    
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
