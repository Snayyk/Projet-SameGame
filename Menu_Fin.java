import java.awt.BorderLayout;
import javax.swing.*;

/**
 * La classe <code>Menu_Fin</code> ouvre le menu de fin affichant le score et proposant de quitter le jeu
 *  
 * @version 1.1
 * @author Maxime Hauet, Yannis Sagal
 */

public class Menu_Fin extends JPanel {
	   
    /**
     * ouvre la fenêtre du menu de fin, ajoute les boutons, les évenements
     * @param score
     */
    public Menu_Fin(int score) {
    JFrame fenetre = new JFrame("Bonbon Crush");
    JButton bouton_quitter = new JButton("Quitter");
	JLabel score_final = new JLabel("Score: " + Integer.toString(score));
	JPanel panneau = new JPanel();
    JPanel panneau2 = new JPanel();
    Evenement_menu_fin event = new Evenement_menu_fin(fenetre);

    fenetre.setSize(300,200);
    fenetre.setLocation(300,200);
    fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	bouton_quitter.addActionListener(event);
	panneau2.add(bouton_quitter);
	panneau.add(score_final);

    fenetre.add(panneau, BorderLayout.NORTH);
    fenetre.add(panneau2, BorderLayout.SOUTH);
    

    fenetre.setVisible(true);
    }
}