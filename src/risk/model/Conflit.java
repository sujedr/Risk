package risk.model;

import java.util.ArrayList;
import java.util.Collections;

public class Conflit {
	private Territoire territoireAttaquant;
	private Territoire territoireDefenseur;
	private ArrayList<Integer> desAttaque;
	private ArrayList<Integer> desDefense;
	
	public Conflit(Territoire territoireAttaquant,Territoire territoireDefenseur,ArrayList<Integer> desAttaque,ArrayList<Integer> desDefense) {
		this.desAttaque=trierDesAttaqueAvecRetour(desAttaque);
		this.desDefense=trierDesAttaqueAvecRetour(desDefense);
		int nbSuivivant=ResultConflit(territoireAttaquant, territoireDefenseur, desAttaque, desDefense) ;
	}
	
	public ArrayList<Integer> trierDesAttaqueAvecRetour(ArrayList<Integer> desAttaque) {
	    // 创建一个新的ArrayList并进行排序
	    ArrayList<Integer> sortedDesAttaque = new ArrayList<>(desAttaque);
	    Collections.sort(sortedDesAttaque, Collections.reverseOrder());
	    return sortedDesAttaque;
	}
	public int ResultConflit(Territoire territoireAttaquant, Territoire territoireDefenseur, ArrayList<Integer> desAttaque, ArrayList<Integer> desDefense) {
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
	    return nbSurvivant;
	}
	public void reglementDefaite(Territoire territoireAttaquant, Territoire territoireDefenseur,int nbSuivivant) {
		if(territoireDefenseur.getNbRegiments()==0) {
			territoireDefenseur.setOccupant(territoireAttaquant.getOccupant());
			territoireDefenseur.setNbRegiments(nbSuivivant);
		}
	}

}
