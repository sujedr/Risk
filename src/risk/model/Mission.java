package risk.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import risk.vue.Fenetre;


/**
 * 
 */
public class Mission {
//	private int numeroMission;
//	private String mission;
	private ArrayList<String> missions = new ArrayList<>();
	private String content;

	public Mission() {
		//Carte de mission
		//TODO Peut être à instancier dans une classe comme les territoires dans "monde" pour clean le main
		missions.add("Vous devez conquérir 18 territoires et occuper chacun d'eux avec deux armées au moins.");
		missions.add("Vous devez conquérir en totalité l'Amérique du Nord et l'Afrique.");
		missions.add("Vous devez conquérir en totalité l'Europe et l'Amérique du sud plus un troisième continent au choix.");
		missions.add("Vous devez conquérir en totalité l'Europe et l'Océanie plus un troisième continent au choix.");
		missions.add("Vous devez conquérir 24 territoires aux choix.");
		missions.add("Vous devez conquérir en totalité l'Amérique du Nord et l'Océanie.");
		missions.add("Vous devez conquérir en totalité l'Asie et l'Afrique.");
		missions.add("Vous devez conquérir en totalité l'Asie et l'Amérique du sud.");
		
		// N�cessite de modifier la strcuture de la classe Mission (attribut joueurAVaincre) ainsi que les methodes associ�es, pas assez de temps 
//		missions.add("Vous devez détruire les armées jaunes. Si vous êtes vous même le propriétaire des armées jaunes ou si le joueur qui en est\r\n"
//				+ "  propriétaire est éliminé par un autre joueur, votre but devient automatiquement de conquérir 24 territoires.");
//		missions.add("Vous devez détruire les armées rouges. Si vous êtes vous même le propriétaire des armées rouges ou si le joueur qui en est\r\n"
//				+ "  propriétaire est éliminé par un autre joueur, votre but devient automatiquement de conquérir 24 territoires.");
//		missions.add("Vous devez détruire les armées bleues. Si vous êtes vous même le propriétaire des armées bleues ou si le joueur qui en est\r\n"
//				+ "  propriétaire est éliminé par un autre joueur, votre but devient automatiquement de conquérir 24 territoires.");
//		missions.add("Vous devez détruire les armées noires. Si vous êtes vous même le propriétaire des armées noires ou si le joueur qui en est\r\n"
//				+ "  propriétaire est éliminé par un autre joueur, votre but devient automatiquement de conquérir 24 territoires.");
//		missions.add("Vous devez détruire les armées violettes. Si vous êtes vous même le propriétaire des armées violettes ou si le joueur qui en est\r\n"
//				+ "  propriétaire est éliminé par un autre joueur, votre but devient automatiquement de conquérir 24 territoires.");
//		missions.add("Vous devez détruire les armées vertes. Si vous êtes vous même le propriétaire des armées vertes ou si le joueur qui en est\r\n"
//				+ "  propriétaire est éliminé par un autre joueur, votre but devient automatiquement de conquérir 24 territoires.");
	}
        
	
	public ArrayList<String> getMissionListe(){
		return missions;
		
	}
	
	public void sysout() {
		for (int i = 0; i < missions.size(); i++) {
		      System.out.println(missions.get(i));
		}
	}
	public String getContent(int i) {
		return content = this.missions.get(i);
	}

}
