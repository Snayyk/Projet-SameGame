import javax.swing.*;
import java.awt.*;

/**
 * La classe <code>Menu</code> contient le menu pour choisir une génération aléatoire ou à partir d'un fichier
 *  
 * @version 1.1
 * @author Maxime Hauet, Yannis Sagal
 */

    public class Menu extends JComponent {
            
        
        private  Boolean jeu_aleatoire = false;
        private  Boolean jeu_fichier = false;

        private  JButton aleatoire = new JButton("Grille aleatoire");
        private  JButton fichier = new JButton("A partir d'un fichier");
        private  JFrame menu = new JFrame("Bonbon Crush");

        /**
         * Le constructeur ouvre la fenêtre et ajoute les boutons à la fenêtre
         */
        public Menu() {
              
            JPanel panneau = new JPanel();
            JPanel titre = new JPanel();
            JLabel letitre = new JLabel("Bonbon Crush");

            
            
            
            menu.setSize(600,300);
			menu.setLocation(400,200);
			menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


            aleatoire.setFont(new Font("Century Gothic", Font.BOLD, 20));
            fichier.setFont(new Font("Century Gothic", Font.BOLD, 20));
            letitre.setFont(new Font("Century Gothic", Font.BOLD, 20));

            panneau.setBackground(new Color(255,192,203));
            titre.setBackground(new Color(255,192,203));
            titre.add(letitre);



            panneau.add(aleatoire);
            panneau.add(fichier);
            menu.add(titre, BorderLayout.NORTH);
            menu.add(panneau, BorderLayout.CENTER);
            

            menu.setVisible(true);

            

        }

        /**
         * récupère les informations de la méthode actionPerformed des classes Event_menu_aleatoire et Event_menu_fichier
         * change l'état des boutons en fonction de l'état récupéré
         */
        public void setBouton(Boolean bouton) {
           
            if(bouton) {
               jeu_aleatoire = true;
               jeu_fichier = false;
               this.choix();
            } else {
                jeu_aleatoire = false;
                jeu_fichier = true;
                this.choix();
            }
            

        }

        
        /**
         * return l'état du bouton aléatoire
         */
        public JButton getBouton_aleatoire() {

            return aleatoire;
        }

        /**
         * return l'état du bouton fichier
         */
        public JButton getBouton_fichier() {

            return fichier;
        }

        /**
         * En fonction de l'état des boutons, ouvre le jeu à partir de la grille aléatoire ou d'un fichier
         * Ferme la fenêtre du menu
         */
        public void choix() {
                
            if(jeu_aleatoire) {
                
                menu.dispose();
                Jeu le_jeu = new Jeu(1);
                }

            if(jeu_fichier) {
                menu.dispose();
                Jeu le_jeu = new Jeu(2);
            }

            
        }
           
}
