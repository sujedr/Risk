package risk.model;

public class Equipe {
	// Attributs 
	private String nom;
	private String statut; 
	private Joueur[] joueurs = new Joueur[4];

	// Constructeur
	public Equipe(String nom, Joueur[] joueurs) {
		super();
		this.nom = nom;
		this.statut = "actif"; // MAJ JD
		this.joueurs = joueurs;
	}
	
	// Getter and setter
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	public Joueur[] getJoueurs() {
		return joueurs;
	}
	public void setJoueurs(Joueur[] joueurs) {
		this.joueurs = joueurs;
	}
	
	// Autres méthodes

}