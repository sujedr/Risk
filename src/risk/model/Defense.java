package risk.model;

import java.util.ArrayList;

public class Defense extends Action {
	
	private Territoire territoireDefenseur;
	private Joueur defense;
	private int nbRegimentDefenseur;
	private ArrayList<Integer> desDefense;
	
	public Defense(Territoire territoireDefenseur,int nbRegimentAttaquant) {
		// TODO Auto-generated constructor stub
		this.defense=territoireDefenseur.getOccupant();
		int nbTerritoireDefenseurable=territoireDefenseur.getNbRegiments();
		this.nbRegimentDefenseur=choisirnbRegimentDefenseur(nbTerritoireDefenseurable, nbRegimentAttaquant);
		this.desDefense=desDefense(nbRegimentDefenseur);
	}

	private int choisirnbRegimentDefenseur(int nbTerritoireDefenseurable, int nbRegimentAttaquant) {
		int nbTerritoireDefenseurable2= nbTerritoireDefenseurable2(nbRegimentAttaquant);
		return Math.min(nbTerritoireDefenseurable2, nbTerritoireDefenseurable);
	}
	
	private int nbTerritoireDefenseurable2(int nbRegimentAttaquant) {
		 if (nbRegimentAttaquant == 3) {
		        return 2;
		    } else {
		        return nbRegimentAttaquant;
		    }
	}
	
	public ArrayList<Integer> desDefense(int nbRegimentDefenseur) {
        ArrayList<Integer> resultats = new ArrayList<>();

        for (int i = 0; i < nbRegimentDefenseur; i++) {
        	LancerDes lancerDes=new LancerDes();
            int resultat = lancerDes.getResultatDes();
            resultats.add(resultat);
        }

        return resultats;
	}
}