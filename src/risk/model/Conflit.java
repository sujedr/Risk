package risk.model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Class Clonflit Ã  jour
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
	 * @return ArrayList<Integer> desDefense
	 */
	public ArrayList<Integer> getDesDefense() {
		return desDefense;
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
	 * @return Boolean isNouveauTerritoireConquis
	 */
	public Boolean resultatConflit(int nbRegimentsRiposte) {
	    
		// Setting nb regiment attaquant
		this.nbRegimentDefenseur = nbRegimentsRiposte;
		// Lancer dÃ¨s attaquant et enregistrer resultat pour traitement data en BD
		this.desAttaque = desAttaquer();
		// Lancer dÃ¨s dÃ©fenseur et enregistrer resultat pour traitement data en BD
		this.desDefense = desDefenseur();
		
		// Gestion des resultats des lancÃ©s de dÃ¨s
		// Classement des dÃ¨s du plus grand au plus petit
        Collections.sort(desAttaque, Collections.reverseOrder());
        Collections.sort(desDefense, Collections.reverseOrder());
        
	    System.out.println("ATTAQUANT : "+ this.desAttaque);
	    System.out.println("DEFENDEUR : "+ this.desDefense);
	    
	    System.out.println("\nAVANT : Territoire attaqué = "+this.territoireDefenseur+" - Occupant = "+this.territoireDefenseur.getOccupant());
	    System.out.println("ATTAQUANT : "+ this.territoireAttaquant.getNbRegiments());
	    System.out.println("DEFENDEUR : "+this.territoireDefenseur.getNbRegiments());
	    
	    int iterations = desDefense.size();
	    int nbRegimentsAttaquantRestant = desAttaque.size();
	    
	    // Comparaison des dÃ¨s par ordre de grandeur
	    for (int i = 0; i < iterations; i++) {
	        int attaque = desAttaque.get(i);
	        int defense = desDefense.get(i);

	        // Pour chaque dés, si le resultat de l'attaquant est plus grand que celui de la dÃ©fense, le defenseur perd un regiment
	        if (attaque > defense) {
	            int nbRegimentsDefenseur = territoireDefenseur.getNbRegiments();
	            if (nbRegimentsDefenseur > 0) {
	                territoireDefenseur.setNbRegiments(territoireDefenseur.getNbRegiments() - 1);
	                
	            }
		    // Pour chaque dÃ¨s, si le resultat de l'attaquant est Ã©gale ou plus petit que celui de la dÃ©fense, l' attaquant perd un regiment
	        } else {
	            int nbRegimentsAttaquant = territoireAttaquant.getNbRegiments();
	            if (nbRegimentsAttaquant > 0) {
	                territoireAttaquant.setNbRegiments(territoireAttaquant.getNbRegiments() - 1);
	            }
	            nbRegimentsAttaquantRestant=nbRegimentsAttaquantRestant-1;
	        }
	    }
	    System.out.println("\nAPRES : Territoire attaqué = "+this.territoireDefenseur+" - Occupant = "+this.territoireDefenseur.getOccupant());
	    System.out.println("ATTAQUANT : "+this.territoireAttaquant.getNbRegiments());
	    System.out.println("DEFENDEUR : "+this.territoireDefenseur.getNbRegiments());
	    
	    System.out.println("\nNb de régiment de l'attaquant déplacable : "+nbRegimentsAttaquantRestant); 
	    
	    // Si le defenseur a perdu toutes ses troupes, on update le statut du territoire
	    Boolean isNouveauTerritoireConquis = false;
		if(this.territoireDefenseur.getNbRegiments()==0) {
			
			// UPDATE CLASSE TERRITOIRE
			// Retrait des troupes du territoire attaquant pour les deplacer dans le territoire conquis
			this.territoireAttaquant.enleverNbRegiments(nbRegimentsAttaquantRestant);
			this.territoireDefenseur.setNbRegiments(nbRegimentsAttaquantRestant);
			// Mise à jour du joueur occupant le territoire conquis
			this.territoireDefenseur.setOccupant(this.territoireAttaquant.getOccupant());
			
			// UPDATE CLASSE JOUEUR
			// Mise à jour de la liste des territoires occupés par les deux joueurs 
			this.defenseur.supprimerTerritoiresConquis(this.territoireDefenseur);
			this.attaquant.ajouterTerritoiresConquis(this.territoireDefenseur);
			// Declaration qu'un nouveau territoire a ete conquis par le joueur attaquant
			isNouveauTerritoireConquis = true;
		}
	    System.out.println("\nAPRES MAJ : Territoire attaqué = "+this.territoireDefenseur+" - Occupant = "+this.territoireDefenseur.getOccupant()+" - Nb Troupes : "+this.territoireDefenseur.getNbRegiments());
	    System.out.println("ATTAQUANT : "+this.territoireAttaquant.getNbRegiments());
	    System.out.println("DEFENDEUR : "+this.territoireDefenseur.getNbRegiments());
	    return isNouveauTerritoireConquis;
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
