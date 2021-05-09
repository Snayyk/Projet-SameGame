import javax.swing.*;
import java.util.Random;

/**
 * La classe <code>Jeu</code> contient la fenêtre du jeu et génère le tableau aléatoire
 *  
 * @version 1.1
 * @author Maxime Hauet, Yannis Sagal
 */
        public class Jeu extends JComponent {
            

            private char[][] tableau_char = new char[15][10];
            private Boolean[][] tableau_actif = new Boolean[15][10];
            private Boolean remplissage = true;
            private Groupe groupement = new Groupe();
            private Remplissage remplisseur = new Remplissage(remplissage,groupement);
            private JFrame jeu = new JFrame("Bonbon Crush");
            private Evenement_survol observateur_survol = new Evenement_survol(groupement,remplisseur, jeu);
            
           
            /**
             * Ouvre la fenêtre de jeu et appel les méthodes qui crééent les groupes, transmettent les groupes.
             * Ajoute les évenements
             * @param decision
             */
            public Jeu(int decision) {

                jeu.setSize(1000,600);
                jeu.setLocation(400,200);
                jeu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                if(decision == 1) {
                    groupement.remplissageTableau();
                } else {
                    
                    groupement.remplissageTableauFichier();
                }
                
                groupement.remplissageActif();
                tableau_char = groupement.getTableau();
                tableau_actif = groupement.getActif();
                this.transmissionTableau();
                this.transmissionActif();
                groupement.creationGroupes();
             
                jeu.addMouseListener(observateur_survol);
                jeu.addMouseMotionListener(observateur_survol);
                jeu.add(remplisseur);
                
                
                

                jeu.setVisible(true);
            }

            /**
             * transmet le tableau de caractères à la classe Remplissage
             */
            public void transmissionTableau() {

                int i,y;
                for(y = 0; y < 10 ; y++) {

                    for(i = 0; i < 15; i++) {
                       
                         remplisseur.setTableau(tableau_char);
                       
                    }
                }
            }
               
            /**
             * transmet le tableau d'actifs à la classe Remplissage
             */
            public void transmissionActif() {

                int i,y;
                for(y = 0; y < 10 ; y++) {

                    for(i = 0; i < 15; i++) {
                       
                         remplisseur.setActif(tableau_actif);
        
                    }
                }
            }

            /**
             * Rempli un tableau de caractères R,V,B de manière aléatoire
             */
            public void remplissageTableau() {
                int condition,i,y;
                Random generateur = new Random();

                for(y = 0; y < 10 ; y++) {

                    for(i = 0; i < 15; i++) {
                        condition = generateur.nextInt();

                        if(condition < 0  && condition%2 == 0) {

                            tableau_char[i][y] = 'R';
                        } else if(condition < 0 && condition%2 != 0) {
                            i--;
                        }

                        if(condition > 0 && condition%2 == 0) {

                            tableau_char[i][y] = 'V';
                            
                        }

                        if(condition > 1000 && condition%2 != 0) {

                            tableau_char[i][y] = 'B';
                            
                        }
                        
                        
                    }
                }

            }

            /**
             * rempli le tableau d'actifs de true
             */
            public void remplissageActif() {
                int i,y;
                for(y = 0; y < 10 ; y++) {

                    for(i = 0; i < 15; i++) {
                      
                        tableau_actif[i][y] = true;
                        
                    }
                }

            }


            
        }
