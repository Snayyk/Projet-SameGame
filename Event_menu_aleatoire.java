import java.awt.event.*;

/**
 * La classe <code>Event_menu_aléatoire</code> rassemble l'évenement du bouton pour ouvrir une grille aléatoire
 *  
 * @version 1.1
 * @author Maxime Hauet, Yannis Sagal
 */

        public class Event_menu_aleatoire implements ActionListener {

            
            private Menu objet;

            /**
             * récupère l'objet du menu dans le Main
             */
            public Event_menu_aleatoire (Menu obj) {

               
                this.objet = obj;
            
                
            }

            /**
             * change l'état du bouton à true lors du clique sur le bouton aléatoire
             */
            public void actionPerformed(ActionEvent evenement) {
                
                objet.setBouton(true);
                
                
            }
        }

    

