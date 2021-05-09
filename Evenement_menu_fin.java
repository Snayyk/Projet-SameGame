import java.awt.event.*;
import javax.swing.*;

/**
 * La classe <code>Event_menu_fin</code> gère l'évenement du bouton "quitter" du menu fin
 *  
 * @version 1.1
 * @author Maxime Hauet, Yannis Sagal
 */
public class Evenement_menu_fin implements ActionListener {

    private JFrame fnetre;

    /**
     * récupère l'objet fenêtre de la classe menu_fin
     */
    public Evenement_menu_fin(JFrame fenetre) {

        this.fnetre = fenetre;
    }

    /**
     * ferme la fenêtre de jeu à l'appui du bouton quitter
     */
    public void actionPerformed(ActionEvent evenement) {
        fnetre.dispose();
        
        
    }
    
}
