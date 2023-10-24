package risk.model;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class Manche {
	
	// Attributs
	/** Joueurs participants à la partie, triés dans l'ordre */
	private Joueur[] joueurs = new Joueur[6]; 
	private ArrayList<Tour> tours;
	private Joueur vainqueur;
	private Chronometre duree = new Chronometre();
	private Boolean estTerminee;
	private String dateDebut;

	// Constructeur
	public Manche(Joueur[] joueurs) {
		this.joueurs = joueurs;
		this.tours = tours;
		this.vainqueur = vainqueur;
		this.duree = duree;
		// Lancement du chronometre à la création de la manche
		duree.demarrerChronometre();
		this.estTerminee = false;
		
		// Debut - Récupération de la date actuelle
		SimpleDateFormat dateActuelle = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String dateCastee = dateActuelle.format(date);
		// Fin - Récupération de la date actuelle
		this.dateDebut = dateCastee;
	}

	// Getter and setter
	public String getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}
	public Joueur[] getJoueurs() {
		return joueurs;
	}
	public void setJoueurs(Joueur[] joueurs) {
		this.joueurs = joueurs;
	}
	public ArrayList<Tour> getTours() {
		return tours;
	}
	public void setTours(ArrayList<Tour> tours) {
		this.tours = tours;
	}
	public Boolean getEstTerminee() {
		return estTerminee;
	}
	public Chronometre getDuree() {
		return duree;
	}
	public void setDuree(Chronometre duree) {
		this.duree = duree;
	}
	
	@Override
	public String toString() {
		return "Manche [joueurs=" + Arrays.toString(joueurs) + ", tours=" + tours + ", vainqueur=" + vainqueur
				+ ", duree=" + consulterDureeManche() + ", estTerminee=" + estTerminee + ", dateDebut=" + dateDebut + "]";
	}

	// Autres méthodes
	/** Met à jour la variable estTerminee correspondant au statut de la partie à vrai pour signifier que la partie est terminée */ 
	public void cloturerPartie() {
		this.estTerminee = true;
	}
	/** Permet de consulter le chronometre de la manche visée */
	public int consulterDureeManche() {
		return duree.getTempsEcoule();
	}
	
	/** Réorganisation de l'ordre des joueurs dans la manche
	 * @param resultatDes : résultat du tirage du dès déterminant le joueur qui commence la partie
	 */
	public void definirOrdreJoueur(int resultatDes) {
		Joueur[] joueurs_stock = new Joueur[6]; 
		int index_stock = 0;
		resultatDes = resultatDes - 1; // -1 pour adapter le tirage à l'index d'un tableau
		// Ajout des joueurs dans un tableau stock, à partir du numéro correspondant au tirage du dés
		// Joueur positionnés à / ou / après l'indice correpondant au resultat du des
		for (int i = resultatDes; i < 6; i++) {
			joueurs_stock[index_stock++] = joueurs[i];
		}
		// Joueur positionnés avant l'indice correpondant au resultat du des
		for (int i = 0; i < resultatDes; i++) {
		    joueurs_stock[index_stock++] = joueurs[i];
		}
		// Mise à jour du tableau d'origine avec les joueurs triés
		for (int i = 0; i < 6; i++) {
		    joueurs[i] = joueurs_stock[i];
		}
	}




}
