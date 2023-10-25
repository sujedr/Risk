package risk.model;

public class Tour {
	
	// Attributs
	private int numero = 1;
	private Joueur joueur;
	private Action action;	
	private int NbRegimentsAPiocher;
	
	// Constructeur 
	public Tour(int numero, Joueur joueur, Action action, int nbRegimentsAPiocher) {
		super();
		this.numero = numero;
		this.joueur = joueur;
		this.action = action;
		NbRegimentsAPiocher = nbRegimentsAPiocher;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
	
	
	
	
	
	
	
	
	
}