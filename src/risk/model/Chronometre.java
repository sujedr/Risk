package risk.model;

import java.util.Timer;
import java.util.TimerTask;

public class Chronometre {
	
	// Attributs
    private Timer timer;
    private int tempsEcoule;
    /** Variable pour vérifier si le chronomètre est actif ou interrompu */
    private boolean actif; 
    
    // Constructeur
    public Chronometre() {
        this.timer = new Timer();
        this.tempsEcoule = 0;
        this.actif = false;
    }
    
    // Methodes
    /**
     * Demarre le chronometre des secondes
     */
    public void demarrerChronometre() {
    	// Si le chronometre n'a pas encore été lancé on le lance
    	if (!actif) {
	        int delai = 1000; // Ici, 1000 ms = 1 sec
	
	        TimerTask tache = new TimerTask() {
	            @Override
	            public void run() {
	                tempsEcoule++;
//	                System.out.println("Temps écoulé : " + tempsEcoule + " secondes");
	            }
	        };
	        // Mise à jour du timer toutes les secondes
	        timer.scheduleAtFixedRate(tache, delai, delai);
	        // Mise à jour du statut du chronometre
	        actif = true;
    	}
    }
    
    /**
     * Arrete le chronometre des secondes
     * @return Temps écoulé en secondes
     */
    public int arreterChronometre() {
        if (actif) {
        	// Stopper le chronomètre
            timer.cancel(); 
            actif = false;
        }
        return tempsEcoule;
    }

    /** Consultation du temps écoulé */
	public int getTempsEcoule() {
		return tempsEcoule;
	}
    /** Consultation du statut du chronometre 
     * @return statut actif ou non actif sous forme de booleen (actif : vrai ou faux)  
     */
	public boolean isActif() {
		return actif;
	}
}