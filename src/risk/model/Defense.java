package risk.model;

public class Defense extends Action {
	
	private Territoire territoireDefenseur;
	private Joueur defense;
	private int nbRegimentDefenseur;
	
	public Defense(Territoire territoireDefenseur,int nbRegimentAttaquant) {
		// TODO Auto-generated constructor stub
		this.defense=territoireDefenseur.getOccupant();
		int nbTerritoireDefenseurable=territoireDefenseur.getNbRegiments();
	}

	private int choisirnbRegimentDefenseur(int nbTerritoireDefenseurable, int nbRegimentAttaquant) {
		
	}
}