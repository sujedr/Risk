package risk.model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Random;

public class Joueur {
	/** Attributs informations joueur */
	private String id; 
	private String nom;
	private String prenom;
	private String dtNaissance;
	
	private String couleur;
	private String currentmission;
	/** Attributs spÃ©cifiques Ã  une manche */
	private HashMap<Continent, ArrayList<Territoire>> territoiresConquis = new HashMap<>() ;
	private int nbRegimentsRestants;
	
	/** Attributs statistiques */           // *** A peut etre supprimer ***
	private int[] nbTirageDes = new int[6];
	private int nbAttaque;
	private int nbDefense;
	private int nbTerritoire;
	private int nbRegiments;
	private int nbTours;
	private ArrayList<Continent> continentsConquis = new ArrayList<>();
	private ArrayList<Territoire> allTerritoires = new ArrayList<>();
	private int nbRegimentsAterritoires;
	/**
	 * Constructeur
	 * @param id
	 * @param nom
	 * @param prenom
	 * @param dtNaissance
	 */
	public Joueur(String id, String nom, String prenom, String dtNaissance, ArrayList<Continent> Continents,String couleur) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dtNaissance = dtNaissance;
		this.couleur = couleur;
		
		/** Initialisation des continents dans la hashmap */			
		for (int i = 0; i < Continents.size(); i++) {
			this.territoiresConquis.put(Continents.get(i), new ArrayList<Territoire>());
		}
		this.nbRegimentsRestants = 20;
		
		/** Initialisation des attributs statistique Ã  zero */         //*** A peut etre supprimer ***
		for (int i=0; i<6; i++) {
			this.nbTirageDes[i] = 0;
		}
		this.nbAttaque = 0;
		this.nbDefense = 0;
		this.nbTerritoire = 0;
		this.nbRegiments = 0;
		this.nbTours = 0;
	}
	
	// Couleur choisie par le joueur 
		// 玩家所选的颜色
			public String getCouleur() {
				return couleur;
			}
			
			//Distribuer les cartes de mission aux joueurs 
			//分发任务卡牌给玩家
			public void DistribuerRandomMission(ArrayList<String> listeMission) {
				Random random = new Random();
				int randomIndex = random.nextInt(listeMission.size());
				this.currentmission = listeMission.remove(randomIndex);
				System.out.println(
				"La mission du joueur " + id + " : " + currentmission);
			}
	// Getter and setter
	
	/** @return int */
	public String getId() {
		return id;
	}
	/** @param id */
	public void setId(String id) {
		this.id = id;
	}
	/** @return String */
	public String getNom() {
		return nom;
	}
	/** @param nom */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/** @return String */
	public String getPrenom() {
		return prenom;
	}
	/** @param prenom */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	/** @return Date */
	public String getDtNaissance() {
		return dtNaissance;
	}
	/** @param dtNaissance */
	public void setDtNaissance(String dtNaissance) {
		this.dtNaissance = dtNaissance;
	}
	/**
	 * Nombre de rÃ©giments Ã  placer sur la carte
	 * @return nbRegimentsRestants
	 */
	public int getNbRegimentsRestants() {
		return nbRegimentsRestants;
	}
	/**
	 * Ajoute des rÃ©giments au nombre initial
	 * @param nbRegimentsRestants
	 */
	public void ajouterNbRegimentsRestants(int nbRegimentsBonus) {
		this.nbRegimentsRestants =  this.nbRegimentsRestants + nbRegimentsBonus;
	}
	/**
	 * EnlÃ¨ve des rÃ©giments au nombre initial
	 * @param nbRegimentsRestants
	 */
	public void enleverNbRegimentsRestants(int nbRegimentsMalus) {
		this.nbRegimentsRestants =  this.nbRegimentsRestants - nbRegimentsMalus;
	}
	/**
	 * Retourne les territoires conquis par le joueur classÃ©s par continents
	 * @return HashMap<String, ArrayList<Territoire>>
	 */
	public HashMap<Continent, ArrayList<Territoire>> getTerritoiresConquis() {
		return territoiresConquis;
	}
	
	/**
	 * @return ArrayList<Territoire> Liste de territoires
	 */
	public ArrayList<Territoire> getAllTerritoires() {
		HashMap<Continent, ArrayList<Territoire>> map = new HashMap<>(this.territoiresConquis);
		ArrayList<Territoire> allTerritoires = new ArrayList<>();
	    // é  åŽ†HashMapä¸­çš„æ‰€æœ‰å€¼ï¼ˆArrayList<Territoire>ï¼‰
	    for (ArrayList<Territoire> territoriesList : map.values()) {
	        // é  åŽ†æ¯ ä¸ªArrayList<Territoire>ï¼Œå°†å…¶ä¸­çš„Territoireæ·»åŠ åˆ°allTerritoiresä¸­
	        for (Territoire territoire : territoriesList) {
	            allTerritoires.add(territoire);
	        }
	    }
	    return allTerritoires;
	}
	
	/**
	 * @return ArrayList<String> liste nom des territoires occupés par le joueur
	 */
	public ArrayList<String> getAllTerritoiresClear() {
		ArrayList<String>liste = new ArrayList<>();
		for (Territoire territoire : this.getAllTerritoires()) {
			liste.add(territoire.getNom());
		}
		return liste;
	}
	
	/**
	 * @return ArrayList<String> liste nom des territoires occupés par le joueur
	 */
	public ArrayList<String> getAllTerritoiresClearNumero() {
		ArrayList<String>liste = new ArrayList<>();
		for (Territoire territoire : this.getAllTerritoires()) {
			liste.add(territoire.getNumber() + " : " + territoire.getNom());
		}
		return liste;
	}
	
	/**
	 * @return int nb de regiments que le joueur dois placer en dÃ©but de tour
	 */
	public int calculerNbRegimentsAPlacer() {
   	 // Continent conquis ?  
   	 ArrayList<Continent> continentsOccupes = new ArrayList<Continent>();
   	 int total = 0;
   	 continentsOccupes = this.consulterContinentsEntierementOccupes();
   	 int ajoutCauseContinent = 0;	        	 
   	 // Parcours de continent de la liste (conquis)
     for (Continent continent : continentsOccupes) {
        	if (continent.getNom() == "Europe") {
        		ajoutCauseContinent = 7;
            }
            else if (continent.getNom() == "Asie") {
            	ajoutCauseContinent = 12;
            }
            else if (continent.getNom() == "Amerique du Nord") {
            	ajoutCauseContinent = 9;
            }
            else if (continent.getNom() == "Amerique du Sud") {
            	ajoutCauseContinent = 4;
            }
            else if (continent.getNom() == "Afrique") {
            	ajoutCauseContinent = 6;
            }
            else if (continent.getNom() == "Oceanie") {
            	ajoutCauseContinent = 4;
            }
        }
     int ajoutCauseTerritoire = 3;
     int totalTerritoires = this.getAllTerritoires().size();
     // On calcul uniquement si la quantitÃ© de territoire repond au seuil min de regiments
     // 9/9 = 3 qui est le minimum de troupe que recoit un joueur
     if (totalTerritoires >= 9) {
    	 ajoutCauseTerritoire = totalTerritoires / 3; 
     }
    total = ajoutCauseTerritoire + ajoutCauseContinent;
	return total;
	}
	
	/**
	 * Ajoute un territoire conquis Ã  la HashMap<Continent, Territoire> alias territoiresConquis du joueur 
	 * @param territoiresConquis
	 */
	public void ajouterTerritoiresConquis(Territoire territoire) {
		Continent continent = territoire.getContinent();
        territoiresConquis.get(continent).add(territoire);
	}
	
	/**
	 * Supprimer un territoire conquis Ã  la HashMap<Continent, Territoire> alias territoiresConquis du joueur 
	 * @param territoiresConquis
	 */
	public void supprimerTerritoiresConquis(Territoire territoire) {
		Continent continent = territoire.getContinent();
		territoiresConquis.get(continent).remove(territoire);
	}
	
	/**
	 * @return Arraylist <Continent> nom continent conquis
	 */
	public ArrayList<Continent> consulterContinentsEntierementOccupes() {
		// Parcours de chaque continent dans le dico de stockage des territoires conquis
        for (Continent continent : this.territoiresConquis.keySet()) {
            int countContinents = this.territoiresConquis.get(continent).size();
            int totalContinents = 0;
            // VÃ©rfication du nombre de territoires par continents
            if (continent.getNom() == "Europe") {
            	totalContinents = 7;
            }
            else if (continent.getNom() == "Asie") {
            	totalContinents = 12;
            }
            else if (continent.getNom() == "AmeriqueDuNord") {
            	totalContinents = 9;
            }
            else if (continent.getNom() == "AmeriqueDuSud") {
            	totalContinents = 4;
            }
            else if (continent.getNom() == "Afrique") {
            	totalContinents = 6;
            }
            else if (continent.getNom() == "Oceanie") {
            	totalContinents = 4;
            }
            // Si le joueur Ã  tous les territoires d'un continent on note la conquete du continent dans le dico
            System.out.println("count "+countContinents+" vs. total "+totalContinents);
            if ((int)countContinents == totalContinents) {
            	continentsConquis.add(continent);
            }
        }
        return continentsConquis;
	}
	
	/**
	 * @param t
	 * @return int nbRegimentsAterritoires
	 * Nobre de régiments présent sur un territoire donné
	 */
	public int calculerNbRegimentsATerritoires(Territoire t) {
		return nbRegimentsAterritoires=t.getNbRegiments();
	}
	
		//Compétition gagnant : reussi mission
		//获胜竞赛：完成任务mission
		public boolean MissionReussie(Joueur[] participants) {
			boolean reussi = false;
			boolean ConquerirAmNord = false;
			boolean ConquerirAfri = false;
			boolean ConquerirEurope = false;
			boolean ConquerirAsie = false;
			boolean ConquerirOceanie = false;
			boolean ConquerirAmSud = false;
			for(int i=0;i<this.continentsConquis.size();i++) {
				if(continentsConquis.get(i).getNom()=="AmeriqueDuNord") {ConquerirAmNord=true;}
				if(continentsConquis.get(i).getNom()=="Afrique") {ConquerirAfri=true;}
				if(continentsConquis.get(i).getNom()=="Europe") {ConquerirEurope=true;}
				if(continentsConquis.get(i).getNom()=="Asie") {ConquerirAsie=true;}
				if(continentsConquis.get(i).getNom()=="Oceanie") {ConquerirOceanie=true;}
				if(continentsConquis.get(i).getNom()=="AmeriqueDuSud") {ConquerirAmSud=true;}
			}
			//0 MissionReussie : 
			//Vous devez conquérir 18 territoires et occuper chacun d'eux avec deux armées au moins
			//您必须征服 18 块领土，并至少用两支军队占领其中的每一块领土
			if(this.currentmission == "Vous devez conquérir 18 territoires et occuper chacun d'eux avec deux armées au moins.") {
				if(allTerritoires.size()>=18 ||nbRegimentsAterritoires>=2) {
					reussi=true;
					System.out.println("Vous avez gagné !");
				}
			}
			
			//1 MissionReussie : conquérir toute l'Amérique du Nord et l'Afrique 
			//完成任务征服整个北美洲和非洲
			else if(this.currentmission == "Vous devez conquérir en totalité l'Asie et l'Amérique du sud.") {
				if (ConquerirAmNord&&ConquerirAfri) {
					reussi=true;
					System.out.println("Vous avez gagné !");
				}
			}
			
			//2 MissionReussie : 
			//Vous devez conquérir en totalité l'Europe et l'Amérique du sud plus un troisième continent au choix
			//您必须征服整个欧洲和南美洲，外加您选择的第三个洲
			else if(this.currentmission == "Vous devez conquérir en totalité l'Europe et l'Amérique du sud plus un troisième continent au choix.") {
				if ((ConquerirEurope&&ConquerirAmSud)||ConquerirAmNord||ConquerirAfri||ConquerirAsie||ConquerirOceanie) {
					reussi=true;
					System.out.println("Vous avez gagné !");
				}
			}
			
			//3 MissionReussie : 
			//Vous devez conquérir en totalité l'Europe et l'Océanie plus un troisième continent au choix
			//您必须征服整个欧洲和大洋洲，外加您选择的第三个洲
			else if(this.currentmission == "Vous devez conquérir en totalité l'Europe et l'Océanie plus un troisième continent au choix.") {
				if ((ConquerirEurope&&ConquerirOceanie)||ConquerirAmNord||ConquerirAfri||ConquerirAsie||ConquerirAmSud) {
					reussi=true;
					System.out.println("Vous avez gagné !");
				}
			}
			
			//4 MissionReussie : 
			//Vous devez conquérir 24 territoires aux choix
			//您必须征服您选择的 24 个领土
			else if(this.currentmission == "Vous devez conquérir 24 territoires aux choix.") {
				if(allTerritoires.size()>=24) {
					reussi=true;
					System.out.println("Vous avez gagné !");
				}
			}
			
			//5 MissionReussie : 
			//Vous devez conquérir en totalité l'Amérique du Nord et l'Océanie
			//您必须征服整个北美洲和大洋洲
			else if(this.currentmission == "Vous devez conquérir en totalité l'Amérique du Nord et l'Océanie.") {
				if (ConquerirAmNord&&ConquerirOceanie) {
					reussi=true;
					System.out.println("Vous avez gagné !");
				}
			}
			
			//6 MissionReussie : 
			//Vous devez conquérir en totalité l'Asie et l'Afrique
			//您必须征服整个亚洲和非洲
			else if(this.currentmission == "Vous devez conquérir en totalité l'Asie et l'Afrique.") {
				if (ConquerirAsie&&ConquerirAfri) {
					reussi=true;
					System.out.println("Vous avez gagné !");
				}
			}
			
			//7 MissionReussie : 
			//Vous devez conquérir en totalité l'Asie et l'Amérique du sud
			//您必须征服整个亚洲和南美洲
			else if(this.currentmission == "Vous devez conquérir en totalité l'Asie et l'Amérique du sud.") {
				if (ConquerirAsie&&ConquerirAmSud) {
					reussi=true;
					System.out.println("Vous avez gagné !");
				}
			}
			
			
			//8 MissionReussie : 
			//Vous devez détruire les armées jaunes. Si vous êtes vous même le propriétaire des armées jaunes ou si le joueur qui en est propriétaire est éliminé par un autre joueur, votre but devient automatiquement de conquérir 24 territoires.
			//您必须摧毁黄色军队。如果您自己是黄色军队的拥有者，或者拥有黄色军队的玩家被其他玩家淘汰，您的目标就会自动变为征服 24 个领土
			else if(this.currentmission == "Vous devez détruire les armées jaunes. Si vous êtes vous même le propriétaire des armées jaunes ou si le joueur qui en est propriétaire est éliminé par un autre joueur, votre but devient automatiquement de conquérir 24 territoires.") {
				if (couleur!="jaunes") {
					for(int i=0;i<participants.length;i++) {
						if(participants[i].getCouleur()=="jaunes"&&participants[i].ComprtitionEchoue()==true) {
							reussi=true;
							System.out.println("Vous avez gagné !");
						}
					}
				}else {
					if(allTerritoires.size()>=24) {
						reussi=true;
						System.out.println("Vous avez gagné !");		
					}
				}
			}
			
			//Vous devez détruire les armées bleues. Si vous êtes vous même le propriétaire des armées bleues ou si le joueur qui en est propriétaire est éliminé par un autre joueur, votre but devient automatiquement de conquérir 24 territoires.
			else if(this.currentmission == "Vous devez détruire les armées bleues. Si vous êtes vous même le propriétaire des armées bleues ou si le joueur qui en est propriétaire est éliminé par un autre joueur, votre but devient automatiquement de conquérir 24 territoires.") {
				if (couleur!="bleues") {
					for(int i=0;i<participants.length;i++) {
						if(participants[i].getCouleur()=="bleues"&&participants[i].ComprtitionEchoue()==true) {
							reussi=true;
							System.out.println("Vous avez gagné !");
						}
					}
				}else {
					if(allTerritoires.size()>=24) {
						reussi=true;
						System.out.println("Vous avez gagné !");		
					}
				}
			}
			
			//Vous devez détruire les armées noires. Si vous êtes vous même le propriétaire des armées jaunes ou si le joueur qui en est propriétaire est éliminé par un autre joueur, votre but devient automatiquement de conquérir 24 territoires.
			else if(this.currentmission == "Vous devez détruire les armées noires. Si vous êtes vous même le propriétaire des armées noires ou si le joueur qui en est propriétaire est éliminé par un autre joueur, votre but devient automatiquement de conquérir 24 territoires.") {
				if (couleur!="noires") {
					for(int i=0;i<participants.length;i++) {
						if(participants[i].getCouleur()=="noires"&&participants[i].ComprtitionEchoue()==true) {
							reussi=true;
							System.out.println("Vous avez gagné !");
						}
					}
				}else {
					if(allTerritoires.size()>=24) {
						reussi=true;
						System.out.println("Vous avez gagné !");		
					}
				}
			}
			
			//Vous devez détruire les armées violettes. Si vous êtes vous même le propriétaire des armées jaunes ou si le joueur qui en est propriétaire est éliminé par un autre joueur, votre but devient automatiquement de conquérir 24 territoires.
			else if(this.currentmission == "Vous devez détruire les armées violettes. Si vous êtes vous même le propriétaire des armées violettes ou si le joueur qui en est propriétaire est éliminé par un autre joueur, votre but devient automatiquement de conquérir 24 territoires.") {
				if (couleur!="violettes") {
					for(int i=0;i<participants.length;i++) {
						if(participants[i].getCouleur()=="violettes"&&participants[i].ComprtitionEchoue()==true) {
							reussi=true;
							System.out.println("Vous avez gagné !");
						}
					}
				}else {
					if(allTerritoires.size()>=24) {
						reussi=true;
						System.out.println("Vous avez gagné !");		
					}
				}
			}
			
			//Vous devez détruire les armées vertes. Si vous êtes vous même le propriétaire des armées jaunes ou si le joueur qui en est propriétaire est éliminé par un autre joueur, votre but devient automatiquement de conquérir 24 territoires.		//您必须摧毁黄色军队。如果您自己是黄色军队的拥有者，或者拥有黄色军队的玩家被其他玩家淘汰，您的目标就会自动变为征服 24 个领土
			else if(this.currentmission == "Vous devez détruire les armées vertes. Si vous êtes vous même le propriétaire des armées vertes ou si le joueur qui en est propriétaire est éliminé par un autre joueur, votre but devient automatiquement de conquérir 24 territoires.") {
				if (couleur!="vertes") {
					for(int i=0;i<participants.length;i++) {
						if(participants[i].getCouleur()=="vertes"&&participants[i].ComprtitionEchoue()==true) {
							reussi=true;
							System.out.println("Vous avez gagné !");
						}
					}
				}else {
					if(allTerritoires.size()>=24) {
						reussi=true;
						System.out.println("Vous avez gagné !");		
					}
				}
			}
			
			//Vous devez détruire les armées rouges. Si vous êtes vous même le propriétaire des armées jaunes ou si le joueur qui en est propriétaire est éliminé par un autre joueur, votre but devient automatiquement de conquérir 24 territoires.
			else if(this.currentmission == "Vous devez détruire les armées rouges. Si vous êtes vous même le propriétaire des armées rouges ou si le joueur qui en est propriétaire est éliminé par un autre joueur, votre but devient automatiquement de conquérir 24 territoires.") {
				if (couleur!="rouges") {
					for(int i=0;i<participants.length;i++) {
						if(participants[i].getCouleur()=="rouges"&&participants[i].ComprtitionEchoue()==true) {
							reussi=true;
							System.out.println("Vous avez gagné !");
						}
					}
				}else {
					if(allTerritoires.size()>=24) {
						reussi=true;
						System.out.println("Vous avez gagné !");		
					}
				}
			}
			else {
				reussi=false;
			}
		return reussi;
	}
	
	@Override
	public String toString() {			
		return "Joueur [nom=" + nom + ", prenom=" + prenom + "]";
	}		
	
	/**
	 * ARCHIVE_METHODE_ComprtitionEchoue()
	 * @return boolean 
	 * true si nbRegiments==0 
	 * c'est a dire que le joueur n'a plus de regiment sur la carte
	 */
	//Compétition échoué
	//竞赛失败：所以领土都被占领（没有军队了）
	public boolean ComprtitionEchoue() {
		System.out.println("Joueur "+id+" : Malheureusement, tous vos territoires ont été confisqués ! Vous avez échoué !");
		return nbRegiments==0;
	}
	/**
	 * ARCHIVE_METHODE_ComprtitionReussie()
	 * @return boolean 
	 * true si le joueur possède tous les territoires
	 * [fonction doublon mais conservé pour archive]
	 */
	//Compétition gagnant : Conquérir tous les continents
	//获胜竞赛：征服所有大洲（打败所有人）
	public boolean ComprtitionReussie() {
		boolean reussi = false;
		boolean ConquerirAmNord = false;
		boolean ConquerirAfri = false;
		boolean ConquerirEurope = false;
		boolean ConquerirAsie = false;
		boolean ConquerirOceanie = false;
		boolean ConquerirAmSud = false;
		for(int i=0;i<this.continentsConquis.size();i++) {
			if(continentsConquis.get(i).getNom()=="AmeriqueDuNord") {ConquerirAmNord=true;}
			if(continentsConquis.get(i).getNom()=="Afrique") {ConquerirAfri=true;}
			if(continentsConquis.get(i).getNom()=="Europe") {ConquerirEurope=true;}
			if(continentsConquis.get(i).getNom()=="Asie") {ConquerirAsie=true;}
			if(continentsConquis.get(i).getNom()=="Oceanie") {ConquerirOceanie=true;}
			if(continentsConquis.get(i).getNom()=="AmeriqueDuSud") {ConquerirAmSud=true;}
		}
		if (ConquerirAmNord&&ConquerirAfri&&ConquerirEurope&&ConquerirAsie
				&&ConquerirOceanie&&ConquerirAmSud) {
			reussi=true;
			System.out.println("Félicitations au joueur "+id+" : Vous avez gagné !");
		}
		return reussi;
	}
}