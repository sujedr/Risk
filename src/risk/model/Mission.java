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
	private String mission;
	private ArrayList<String> missions = new ArrayList<>();
	private String content;
	

	public Mission(String mission) {
		this.mission = mission;
//		this.numeroMission = numeroMission;
	}
	
	
	public Mission() {
		
		//Carte de mission
		//TODO Peut être à instancier dans une classe comme les territoires dans "monde" pour clean le main
		Mission mission1 = new Mission("Vous devez conquérir 18 territoires et occuper chacun d'eux avec deux armées au moins.");
		Mission mission2 = new Mission("Vous devez conquérir en totalité l'Amérique du Nord et l'Afrique.");
		Mission mission3 = new Mission("Vous devez conquérir en totalité l'Europe et l'Amérique du sud plus un troisième continent au choix.");
		Mission mission4 = new Mission("Vous devez conquérir en totalité l'Europe et l'Océanie plus un troisième continent au choix.");
		Mission mission5 = new Mission("Vous devez conquérir 24 territoires aux choix.");
		Mission mission6 = new Mission("Vous devez conquérir en totalité l'Amérique du Nord et l'Océanie.");
		Mission mission7 = new Mission("Vous devez conquérir en totalité l'Asie et l'Afrique.");
		Mission mission8 = new Mission("Vous devez conquérir en totalité l'Asie et l'Amérique du sud.");
		Mission mission9 = new Mission("Vous devez détruire les armées jaunes. Si vous êtes vous même le propriétaire des armées jaunes ou si le joueur qui en est\r\n"
				+ "  propriétaire est éliminé par un autre joueur, votre but devient automatiquement de conquérir 24 territoires.");
		Mission mission10 = new Mission("Vous devez détruire les armées rouges. Si vous êtes vous même le propriétaire des armées rouges ou si le joueur qui en est\r\n"
				+ "  propriétaire est éliminé par un autre joueur, votre but devient automatiquement de conquérir 24 territoires.");
		Mission mission11 = new Mission("Vous devez détruire les armées bleues. Si vous êtes vous même le propriétaire des armées bleues ou si le joueur qui en est\r\n"
				+ "  propriétaire est éliminé par un autre joueur, votre but devient automatiquement de conquérir 24 territoires.");
		Mission mission12 = new Mission("Vous devez détruire les armées noires. Si vous êtes vous même le propriétaire des armées noires ou si le joueur qui en est\r\n"
				+ "  propriétaire est éliminé par un autre joueur, votre but devient automatiquement de conquérir 24 territoires.");
		Mission mission13 = new Mission("Vous devez détruire les armées violettes. Si vous êtes vous même le propriétaire des armées violettes ou si le joueur qui en est\r\n"
				+ "  propriétaire est éliminé par un autre joueur, votre but devient automatiquement de conquérir 24 territoires.");
		Mission mission14 = new Mission("Vous devez détruire les armées vertes. Si vous êtes vous même le propriétaire des armées vertes ou si le joueur qui en est\r\n"
				+ "  propriétaire est éliminé par un autre joueur, votre but devient automatiquement de conquérir 24 territoires.");
		
        missions.add(getMission());
        missions.add(getMission());
        missions.add(getMission());
        missions.add(getMission());
        missions.add(getMission());
        missions.add(getMission());
        missions.add(getMission());
        missions.add(getMission());
        missions.add(getMission());
        missions.add(getMission());
        missions.add(getMission());
        missions.add(getMission());
        missions.add(getMission());
        missions.add(getMission());
        
//        missions.add(getMission());
//        missions.add(mission2.getMission());
//        missions.add(mission3.getMission());
//        missions.add(mission4.getMission());
//        missions.add(mission5.getMission());
//        missions.add(mission6.getMission());
//        missions.add(mission7.getMission());
//        missions.add(mission8.getMission());
//        missions.add(mission9.getMission());
//        missions.add(mission10.getMission());
//        missions.add(mission11.getMission());
//        missions.add(mission12.getMission());
//        missions.add(mission13.getMission());
//        missions.add(mission14.getMission());
        
      //Mélanger les cartes de mission
		Collections.shuffle(missions, new Random());
		
	}
        

	public String getMission() {
		return mission;
	}
	
	public ArrayList<String> getMissionListe(){
		return missions;
	}
	public String getContent(int i) {
		return content = this.missions.get(i);
	}

}
