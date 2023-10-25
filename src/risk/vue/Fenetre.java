package risk.vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;

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
    JLabel label = new JLabel("            Tour : Joueur 1");
    /**
     * Constructeur
     */
    public Fenetre() {
    	//TODO Affichage dynamique des tours
        frame.add(mapLabel);
        frame.add(label, BorderLayout.SOUTH);
        frame.setSize(1230, 650);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        int seuil = 20;
        
        ArrayList<Territoire> territoires = new ArrayList<>();
        
        Territoire ter101 = new Territoire(101, "Alaska", 84, 105);
        Territoire ter102 = new Territoire(102, "Territoire du Nord Ouest", 189, 106);
        Territoire ter103 = new Territoire(103, "Alberta", 160, 146);
        Territoire ter104 = new Territoire(104, "Ontario", 241, 155);
        Territoire ter105 = new Territoire(105, "Quebec", 320, 149);
        Territoire ter106 = new Territoire(106, "Etat de l'Ouest", 138, 204);
        Territoire ter107 = new Territoire(107, "Etat de l'Est", 221, 228);
        Territoire ter108 = new Territoire(108, "Amerique Centrale", 147, 294);
        Territoire ter109 = new Territoire(109, "Groenland", 453, 64);
        
        Territoire ter201 = new Territoire(201, "Venezuela", 264, 364);
        Territoire ter202 = new Territoire(202, "Perou", 273, 455);
        Territoire ter203 = new Territoire(203, "Bresil", 339, 436);
        Territoire ter204 = new Territoire(204, "Argentine", 301, 547);
        
        Territoire ter301 = new Territoire(301, "Afrique du Nord", 559, 306);
        Territoire ter302 = new Territoire(302, "Egypte", 648, 270);
        Territoire ter303 = new Territoire(303, "Congo", 646, 398);
        Territoire ter304 = new Territoire(304, "Afrique Orientale", 695, 347);
        Territoire ter305 = new Territoire(305, "Afrique du Sud", 650, 491);
        Territoire ter306 = new Territoire(306, "Madagascar", 751, 474);
        
        Territoire ter401 = new Territoire(401, "Europe Occidentale", 561, 195);
        Territoire ter402 = new Territoire(402, "Grande Bretagne", 546, 151);
        Territoire ter403 = new Territoire(403, "Islande", 506, 112);
        Territoire ter404 = new Territoire(404, "Scandinavie", 614, 112);
        Territoire ter405 = new Territoire(405, "Europe du Nord", 616, 162);
        Territoire ter406 = new Territoire(406, "Europe de l'Est", 643, 194);
        Territoire ter407 = new Territoire(407, "Ukraine", 703, 148);
        
        Territoire ter501 = new Territoire(501, "Afghanistan", 803, 186);
        Territoire ter502 = new Territoire(502, "Inde", 860, 272);
        Territoire ter503 = new Territoire(503, "Oural", 804, 121);
        Territoire ter504 = new Territoire(504, "Siberie", 871, 107);
        Territoire ter505 = new Territoire(505, "Chine", 907, 228);
        Territoire ter506 = new Territoire(506, "Japon", 1101, 222);
        Territoire ter507 = new Territoire(507, "Moyen Orient", 730, 262);
        Territoire ter508 = new Territoire(508, "Mongolie", 969, 188);
        Territoire ter509 = new Territoire(509, "Tchita", 950, 146);
        Territoire ter510 = new Territoire(510, "Yakoutie", 969, 106);
        Territoire ter511 = new Territoire(511, "Kamchatka", 1076, 106);
        Territoire ter512 = new Territoire(512, "Siam", 1076, 106);
        
        Territoire ter601 = new Territoire(601, "Indonesie", 1034, 392);
        Territoire ter602 = new Territoire(602, "Nouvelle Guinee", 1146, 420);
        Territoire ter603 = new Territoire(603, "Australie Occidentale", 1052, 517);
        Territoire ter604 = new Territoire(604, "Australie Orientale", 1147, 515);
        
        
        territoires.add(ter101);
        territoires.add(ter102);
        territoires.add(ter103);
        territoires.add(ter104);
        territoires.add(ter105);
        territoires.add(ter106);
        territoires.add(ter107);
        territoires.add(ter108);
        territoires.add(ter109);
        
        territoires.add(ter201);
        territoires.add(ter202);
        territoires.add(ter203);
        territoires.add(ter204);
        
        territoires.add(ter301);
        territoires.add(ter302);
        territoires.add(ter303);
        territoires.add(ter304);
        territoires.add(ter305);
        territoires.add(ter306);
        
        territoires.add(ter401);
        territoires.add(ter402);
        territoires.add(ter403);
        territoires.add(ter404);
        territoires.add(ter405);
        territoires.add(ter406);
        territoires.add(ter407);
        
        territoires.add(ter501);
        territoires.add(ter502);
        territoires.add(ter503);
        territoires.add(ter504);
        territoires.add(ter505);
        territoires.add(ter506);
        territoires.add(ter507);
        territoires.add(ter508);
        territoires.add(ter509);
        territoires.add(ter510);
        territoires.add(ter511);
        territoires.add(ter512);
        
        territoires.add(ter601);
        territoires.add(ter602);
        territoires.add(ter603);
        territoires.add(ter604);
        
   /*     JDialog testAlert = new JDialog();
        testAlert.setSize(new Dimension(100, 100));
        testAlert.setLocationRelativeTo(frame);
        testAlert.add(new JLabel("Test"));
        testAlert.setModal(true);
        testAlert.setResizable(false);
        testAlert.setVisible(true); */
        
        frame.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
            	afficherBoiteDialogue();
                int x = e.getX();
                int y = e.getY();
                
                for (Territoire territoire : territoires) {
                	if (territoire.isInTerritory(x, y, seuil) ) {
                		JOptionPane.showMessageDialog(null, territoire.getNumber() + " : " + territoire.getNom());
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
     * @param tour
     */
    public void actualiserTour(Tour tour) {
    	this.label.setText(tour.getJoueur().getNom());
    }
    
    public void afficherBoiteDialogue() {
        String[] options = {"Attaquer", "Déplacer", "Passer tour"};
        int choice = JOptionPane.showOptionDialog(frame, "Choisissez une action", "Action", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            JOptionPane.showMessageDialog(frame, "Vous avez choisi d'attaquer");
        } else if (choice == 1) {
            JOptionPane.showMessageDialog(frame, "Vous avez choisi de déplacer");
        } else if (choice == 2) {
            JOptionPane.showMessageDialog(frame, "Vous avez choisi de passer tour");
        }
    }
    
    
}
