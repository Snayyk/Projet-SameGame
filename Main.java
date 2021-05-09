import javax.swing.*;

/**
 * La classe <code>Main</code> sert à ouvrir le menu de début du jeu
 *  
 * @version 1.1
 * @author Maxime Hauet, Yannis Sagal
 */

    public class Main extends JComponent {

            
            
/**
 * 
 * @param args
 * création du menu et lien entre les boutons du menu et les évenements
 */
        public static void main(String[] args) {
            JButton aleatoire = new JButton();
            JButton fichier = new JButton();

            Menu menu = new Menu();

           
            
            

            aleatoire = menu.getBouton_aleatoire();
            fichier = menu.getBouton_fichier();
            
            Event_menu_aleatoire observateur_aleatoire = new Event_menu_aleatoire(menu);
            Event_menu_fichier observateur_fichier = new Event_menu_fichier(menu);

            
            fichier.addActionListener(observateur_fichier);
            aleatoire.addActionListener(observateur_aleatoire);
            
           
            
            
        }

    }