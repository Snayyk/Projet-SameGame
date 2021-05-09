import java.awt.event.*;

/**
 * La classe <code>Event_menu_fichier</code> rassemble l'évenement du bouton pour ouvrir un fichier
 *  
 * @version 1.1
 * @author Maxime Hauet, Yannis Sagal
 */

        public class Event_menu_fichier implements ActionListener {

            
            private Menu objet;

            /**
             * récupère l'objet du menu provenant du Main
             */
            public Event_menu_fichier (Menu obj) {

                this.objet = obj;
               
                
            }
            /**
             * change l'état du bouton à false au clique du bouton permettant la sélection d'un fichier
             */
            public void actionPerformed(ActionEvent evenement) {
                
                objet.setBouton(false);
                
            }
        }

            
        
