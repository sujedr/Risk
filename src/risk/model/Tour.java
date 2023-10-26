package risk.model;
import java.util.HashMap;
import java.util.Scanner;
import risk.model.Attaque;
import risk.model.Defense;


public class Tour {
	
	// Attributs
	private HashMap<Integer, Conflit> conflitMap;
	private Joueur joueur;
	private Conflit conflit;	
	private int NbRegimentsAPiocher;
	
	// Constructeur 
	public Tour(Joueur joueur) {
		int numero=0;
		while(numero!=-1||numero<4) {
			numero++;
			Attaque attaque = new Attaque(joueur);
			Defense defense = new Defense(attaque);
			Conflit conflit = new Conflit(defense);
			this.conflit=conflit;
			
		}
		this.joueur = joueur;
	}
	
	private int getPlayerChoice() {
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
	
	
	
	
	
	
	
}