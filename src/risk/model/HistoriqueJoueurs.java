package risk.model;

import java.util.HashMap;

public class HistoriqueJoueurs {
	private HashMap<Integer,Joueur> classement;

	public HistoriqueJoueurs() {
		// TODO Auto-generated constructor stub
	}

	public HashMap<Integer, Joueur> getClassement() {
		return classement;
	}

	public void setClassement(HashMap<Integer, Joueur> classement) {
		this.classement = classement;
	}

	private int findMaxValue() {
	    int maxValue = 0; // 初始化最大值为0

	    for (int key : classement.keySet()) {
	        
	        if (key > maxValue) {
	            maxValue = key; // 更新最大值
	        }
	    }

	    return maxValue;
	}

}