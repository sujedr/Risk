package risk.model;

import java.util.HashMap;

public class HistoriqueJoueurs {

	private HashMap<Integer,Joueur> classement;

	public HistoriqueJoueurs() {
		this.classement=new HashMap<>();
	}

	public HashMap<Integer, Joueur> getClassement() {
		return classement;
	}

	public void addClassement(Joueur joueur) {
		int rank=getClassementLength();
		this.classement.put(rank,joueur);
	}

	private int findMaxValue() {
	    int maxValue = 0;

	    for (int key : classement.keySet()) {
	        
	        if (key > maxValue) {
	            maxValue = key;
	        }
	    }

	    return maxValue;
	}
	public int getClassementLength() {
	    return classement.size();
	}

}