package risk.vue;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

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
     * @param territoire
     */
    public void choixJoueur(Territoire territoire) {
        String[] options = {"Attaquer", "Déplacer", "Passer tour"};
        int choice = JOptionPane.showOptionDialog(frame, territoire.getNumber() + " : " + territoire.getNom() + "\n Occupant : " + territoire.getOccupant().getNom() + "\n Choisissez une action ", "Action", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            JOptionPane.showMessageDialog(frame, "Vous avez choisi d'attaquer");
        } else if (choice == 1) {
            JOptionPane.showMessageDialog(frame, "Vous avez choisi de déplacer");
        } else if (choice == 2) {
            JOptionPane.showMessageDialog(frame, "Vous avez choisi de passer tour");
        }
    }
    
    /**
     * Méthode à invoker pour permettre à un joueur d'effectuer ses actions dans un tour
     * @param joueur
     */
    public void actionsTour(Joueur joueur) {
        int seuil = 20;
        this.label.setText("             "  + joueur.getNom());
        frame.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
            	
                int x = e.getX();
                int y = e.getY();
                
                for (Territoire territoire : territoires) {
                	if (territoire.isInTerritory(x, y, seuil) ) {
                	//	JOptionPane.showMessageDialog(null, territoire.getNumber() + " : " + territoire.getNom());
                		choixJoueur(territoire);
                	};
                }  
            }

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
        });
    }
    
    /**
     * setter des territoires
     * @param territoires
     */
    public void setTerritoires(ArrayList<Territoire> territoires) {
    	this.territoires = territoires;
    }
    
    
}
