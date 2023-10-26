package risk.model;

import java.util.ArrayList;
import java.util.Scanner;
import risk.model.LancerDes;
//import RiskTest.Joueur;

public class Attaque extends Action {
	
	private Joueur attaquant;
	private Territoire territoireAttaquant;
	private Territoire territoireDefenseur;
	private int nbRegimentAttaquant;
	private ArrayList<Integer> desAttaque;
	
	public Attaque(Joueur attaquant, Territoire territoireAttaquant, Territoire territoireDefenseur, int nbRegimentAttaquant) {
		// TODO Auto-generated constructor stub
		this.attaquant=attaquant;
		this.territoireAttaquant = territoireAttaquant;
		this.territoireDefenseur = territoireDefenseur;
		this.nbRegimentAttaquant = nbRegimentAttaquant;
		this.desAttaque=desAttaquer(nbRegimentAttaquant);
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
     * @param nbRegimentAttaquant
     * @return ArrayList<Integer> resultats
     */
    public ArrayList<Integer> desAttaquer(int nbRegimentAttaquant) {
        ArrayList<Integer> resultats = new ArrayList<>();

        for (int i = 0; i < nbRegimentAttaquant; i++) {
        	LancerDes lancerDes=new LancerDes();
            int resultat = lancerDes.getResultatDes();
            resultats.add(resultat);
        }
        return resultats;
    }
    
	// ************** FONCTION REUTILISABLE POUR TOI RAPH ****************** //
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
	    return allTerritoires.get(choix - 1); // 减去1以获取正确的索引
	}
	
	private int choisirnbRegimentAttaquant(int nbTerritoireAttaquantable) {
		int choix;
		Scanner scanner = new Scanner(System.in);
		if(nbTerritoireAttaquantable>=3) {
			System.out.println("Veuillez entrer le nombre de troupes que vous souhaitez déployer, veuillez saisir un chiffre inférieur à 3.");
			choix = scanner.nextInt();
			while(choix>3 || choix<1 ) {
				System.out.println("Veuillez saisir à nouveau.");
				choix = scanner.nextInt();
			}
		}
		else {
			System.out.println("Veuillez entrer le nombre de troupes que vous souhaitez déployer, veuillez saisir un chiffre inférieur à "+nbTerritoireAttaquantable+".");
			choix = scanner.nextInt();
			while(choix> nbTerritoireAttaquantable || choix<1 ) {
				System.out.println("Veuillez saisir à nouveau.");
				choix = scanner.nextInt();
			}
		}
		return choix;
	}
	
	private Territoire choisirTerritoireDefenseur(Territoire territoireAttaquant) {
		ArrayList<Territoire> voisins= new ArrayList<>();
		voisins=territoireAttaquant.getVoisins();
		System.out.println("Veuillez sélectionner le pays que vous souhaitez attaquer.");
	    for (int i = 0; i < voisins.size(); i++) {
	        System.out.println((i + 1) + ". " + voisins.get(i).getNom()); // 假设Territoire有一个getNom()方法来获取名称
	    }
	    // 获取玩家的选择
	    Scanner scanner = new Scanner(System.in);
	    int choix = scanner.nextInt();

	    return voisins.get(choix - 1); 
	}
}