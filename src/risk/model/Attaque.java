package risk.model;

import java.util.ArrayList;
import java.util.Scanner;
import RiskTest.Joueur;

public class Attaque extends Action {
	
	private Joueur attaquant;
	private Joueur defense;
	private Territoire territoireAttaquant;
	private Territoire territoireDefenseur;
	
	public Attaque(Joueur attaquant,Territoire territoireAttaquant,Territoire territoireDefenseur) {
		// TODO Auto-generated constructor stub
		int nbTerritoireAttaquant;
	}	
	private Territoire choisirTerritoirePartir(Joueur attaquant) {
		ArrayList<Territoire> allTerritoires = new ArrayList<>();
		allTerritoires=attaquant.getAllTerritoires();
	    // 显示可选的Territoire列表
	    System.out.println("Veuille choisir une territoire：");
	    for (int i = 0; i < allTerritoires.size(); i++) {
	        System.out.println((i + 1) + ". " + allTerritoires.get(i).getNom()); // 假设Territoire有一个getNom()方法来获取名称
	    }

	    // 获取玩家的选择
	    Scanner scanner = new Scanner(System.in);
	    int choix = scanner.nextInt();

	    // 确保选择在有效范围内
	    if (choix >= 1 && choix <= allTerritoires.size()) {
	        return allTerritoires.get(choix - 1); // 减去1以获取正确的索引
	    } else {
	        System.out.println("invalid");
	        return null; // 或者采取其他操作，例如重新提示选择
	    }

	}
}