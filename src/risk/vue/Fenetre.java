package risk.vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import javax.swing.*;

import risk.model.Joueur;
import risk.model.Territoire;
import risk.model.Tour;

/**
 * Objet fenêtre
 */
public class Fenetre {
	
	/**
	 * Attributs
	 */
	private JFrame frame = new JFrame("Risk");
    ImageIcon map = new ImageIcon("./img/map4.jpg");
    JLabel mapLabel = new JLabel(map);
    JLabel label = new JLabel("");
    ArrayList<Territoire> territoires= new ArrayList<>();
    int seuil = 20;
    
    /**
     * Constructeur
     * @param territoires 
     */
    public Fenetre(ArrayList<Territoire> territoires) {
    	//TODO Affichage dynamique des tours
    	this.territoires = territoires;
        frame.add(mapLabel);
        frame.add(label, BorderLayout.SOUTH);
        frame.setSize(1230, 650);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    /**
     * @param tour
     */
    public void actualiserTour(Tour tour) {
    	this.label.setText(tour.getJoueur().getNom());
    }
    
    /**
     * Gestion des interraction pour le premier tour
     * @param joueur
     * @param territoire 
     * @return nbTroupe à ajouter
     */
    public int premierTour(Joueur joueur, Territoire territoire) {
    	//Actualisation de l'affichage
    	boolean validationTroupe = false;
    	int nbTroupes = 0;
    	while(!validationTroupe) {
	        this.label.setText("             Joueur "  + joueur.getId() + "\n" + joueur.getAllTerritoiresClear());
	        JPanel panel = new JPanel();
	        JLabel terrLab = new JLabel( territoire.getNumber() + " : " + territoire.getNom() + " | " );
	        JLabel label = new JLabel("Joueur" + joueur.getId() + " | Voulez-vous ajouter des troupes ? | " + joueur.getNbRegimentsRestants() + " regiments restants");
	        JTextField textField = new JTextField(10);
	        
	
	        panel.add(terrLab);
	        panel.add(label);
	        if (joueur.getNbRegimentsRestants() != 0) {
	        	panel.add(textField);
	        }
	        
	
	        int option = JOptionPane.showConfirmDialog(null, panel, "Ajouter des troupes", JOptionPane.OK_CANCEL_OPTION);
	
	        if (option == JOptionPane.OK_OPTION) {
	            try {
	                    String input = textField.getText();
	        	        if (joueur.getNbRegimentsRestants() != 0) {
	        	        	nbTroupes = Integer.parseInt(input);
	        	        }
	                    if (joueur.getNbRegimentsRestants() >= nbTroupes) {
	                    	if (nbTroupes != 0) {
	                    		JOptionPane.showMessageDialog(null, "Vous avez saisi " + nbTroupes + " troupes.");
	                    	}
	                    	validationTroupe = true;
	                    } else if(joueur.getNbRegimentsRestants() == 0) {
	                    	nbTroupes = 0;
	                    	validationTroupe = true;
	                    } else {
	                    	JOptionPane.showMessageDialog(null, "Vous n'avez plus de troupes à ajouter ou le nombre saisi est trop élevé");
	                    }
	            } catch (NumberFormatException e) {
	                JOptionPane.showMessageDialog(null, "Saisie invalide. Veuillez entrer un nombre valide.");
	            }
	        }
    	}
        return nbTroupes;
    }
    /**
     * Méthode à invoker pour permettre à un joueur d'effectuer ses actions dans un tour
     * @param joueur
     */
    public int actionsTour(Joueur joueur) {
    	final int[] choix = { 0 };
        this.label.setText("             "  + joueur.getNom());
        frame.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
            	
                int x = e.getX();
                int y = e.getY();
                
                for (Territoire territoire : territoires) {
                	if (territoire.isInTerritory(x, y, seuil) ) {
                		choix[0] = choixJoueurTour(territoire, joueur);
                	} else {
                		choix[0] = 0;
                	};
                }  
            }
			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}
        });
        return choix[0];
    }
    
    /**
     * @param territoire
     */
    public int choixJoueurTour(Territoire territoire, Joueur joueur) {
        String[] options = {"Attaquer", "Déplacer", "Passer tour"};
        int choice = JOptionPane.showOptionDialog(frame, territoire.getNumber() + " : " + territoire.getNom() + "\n Occupant : " 
        + territoire.getOccupant().getNom() + "\n Choisissez une action ", "Action", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            JOptionPane.showMessageDialog(frame, "Vous avez choisi d'attaquer \n Cliquez maintenant sur le pays que vous souhaitez attaquer");
            int option = JOptionPane.showConfirmDialog(null, frame, "Ajouter des troupes", JOptionPane.OK_CANCEL_OPTION);
            frame.addMouseListener(new MouseListener() {
                public void mouseClicked(MouseEvent e) {
                	
                    int x = e.getX();
                    int y = e.getY();
                    
                    for (Territoire territoire : territoires) {
                    	if (territoire.isInTerritory(x, y, seuil) ) {
                    		if (territoire.getOccupant() == joueur) {
                    			System.out.println("Vous ne pouvez pas attaquer votre propre territoire");
                    		}
                    		else {
                    			System.out.println("Vous avez choisi d'attaquer le territoire de : " + territoire.getOccupant().getNom());
                    			System.out.println(territoire.getNumber() + " : " + territoire.getNom());
                    		}
                    	};
                    }  
                }
    			@Override
    			public void mousePressed(MouseEvent e) {}

    			@Override
    			public void mouseReleased(MouseEvent e) {}

    			@Override
    			public void mouseEntered(MouseEvent e) {}

    			@Override
    			public void mouseExited(MouseEvent e) {}
            });
            return 1;
        } else if (choice == 1) {
            JOptionPane.showMessageDialog(frame, "Vous avez choisi de déplacer \n Cliquez maintenant sur le pays depuis lequel vous voulez déplacer");
            return 2;
        } else if (choice == 2) {
            JOptionPane.showMessageDialog(frame, "Vous avez choisi de passer votre tour");
            return 3;
        }
        return 0;
    }
    
    /**
     * Gestion des interraction pour le premier tour
     * @param joueur
     * @param territoire 
     * @return nbTroupe à ajouter
     */
    public int tour(Joueur joueur) {
        // Actualisation de l'affichage
        boolean validationTroupe = false;
        int choix = 0;
        
        while (!validationTroupe) {
            this.label.setText("             Joueur " + joueur.getId() + "\n" + joueur.getAllTerritoiresClear());

            // Utilisation de JOptionPane pour afficher les options
            Object[] options = {"Déplacer", "Attaquer", "Passer tour"};
            int choice = JOptionPane.showOptionDialog(null, "Que voulez-vous faire ?", "Choix d'action",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

            // Traitement de la sélection de l'utilisateur
            if (choice == 0) {
                // Déplacer
            	choix = 1;
                validationTroupe = true; // Remplacez cela par la logique appropriée
            } else if (choice == 1) {
                // Attaquer
                choix = 2;
                //attaque(joueur); //TODO Enlevé pour le test
                validationTroupe = true; // Remplacez cela par la logique appropriée
            } else if (choice == 2) {
                // Passer tour
                choix = 3; // Mettez le nombre de troupes à 0 (ou autre logique)
                validationTroupe = true;
            }
        }
        return choix;
    }
    
    public Territoire attaque(Joueur joueur) {
        this.label.setText("             Joueur " + joueur.getId() + "\n" + joueur.getAllTerritoiresClearNumero() + " Choisissez le territoire attaquant");
        Territoire terAttaque = null;
        while (true) {
            CountDownLatch clickWait = new CountDownLatch(1); // Attendre le click

            frame.addMouseListener(new MouseListener() {
                public void mouseClicked(MouseEvent e) {
                    int x = e.getX();
                    int y = e.getY();

                    for (Territoire territoire : territoires) {
                        if (territoire.isInTerritory(x, y, seuil)) {
                            System.out.println(territoire.getNumber() + " : " + territoire.getNom());
                            Territoire terAttaque = territoire;
                            clickWait.countDown();
                        }
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });

            try {
                // Attende de l'action utilisateur
                clickWait.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Suite du code
            System.out.println("Suite du code après le clic.");
            break;
        }
        return terAttaque;
    }

    

    
    /**
     * setter des territoires
     * @param territoires
     */
    public void setTerritoires(ArrayList<Territoire> territoires) {
    	this.territoires = territoires;
    }
    
    
}
