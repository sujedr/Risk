package risk.model;
import java.util.HashMap;
import java.util.Scanner;
import risk.model.Archive_Attaque;
import risk.model.Archive_Defense;


public class Tour {
	
	// Attributs
	private HashMap<Integer, Archive_Conflit> conflitMap;
	private Joueur joueur;

	// Constructeur 
	public Tour(Joueur joueur) {
		this.joueur=joueur;
		int numero=0;
		HashMap<Integer, Archive_Conflit> conflitMap = new HashMap<>();
		while(numero!=-1||numero<4) {
			numero=getPlayerChoice();
			if(numero!=-1) {
				numero++;
				Archive_Attaque attaque = new Archive_Attaque(joueur);
				Archive_Defense defense = new Archive_Defense(attaque);
				Archive_Conflit conflit = new Archive_Conflit(defense);
				conflitMap.put(numero, conflit);
			}
		}
	}
	
	//////////////////////  INTERFACE CF RAPHH //////////////////////
	public int getPlayerChoice() {
	    Scanner scanner = new Scanner(System.in);
	    System.out.print("Veuillez entrer votre choix (-1 signifie abandonner l'attaque) : ");
	    
	    if (scanner.hasNextInt()) {
	        int choice = scanner.nextInt();
	        if (choice == -1) {
	            System.out.println("Le joueur choisit d'abandonner l'attaque");
	            return -1;
	        } else {
	            System.out.println("Le joueur choisit d'attaquer ");
	            return choice;
	        }
	    } else {
	        System.out.println("Saisie invalide, veuillez saisir un entier ou -1 pour abandonner l'attaque.");
	        return getPlayerChoice(); // 递归调用，直到得到有效输入
	    }
	}
	
	/**
	 * @return Joueur
	 */
	public Joueur getJoueur() {
		return joueur;
	}
	/**
	 * @param joueur
	 */
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
	
	
	
	
	
	
	
}