import java.awt.*;
import javax.swing.*;

/**
 * La classe <code>Event_menu_fichier</code> sert à créer les boules, la surbrillance et l'effacage de boules
 *  
 * @version 1.1
 * @author Maxime Hauet, Yannis Sagal
 */
        public class Remplissage extends JComponent {

            private final int WIDTH = 50; 
            private final int HEIGTH = 40; 
            private final int WIDTHSCORE = 910; 
            private final int HEIGTHSCORE = 20; 
            private Boolean remplisseur;
            
            private char[][] tableau_char = new char[15][10];
            private Boolean[][] tableau_actif = new Boolean[15][10];
            private int key,score=0;
            private Groupe team;

            /**
             * Récupère l'objet groupe envoié par la classe jeu
             */
            public Remplissage(Boolean jeu_remplissage, Groupe gr) {

                this.remplisseur = jeu_remplissage;
                this.team = gr;
            }
            
            /**
             * récupère le tableau de caractères
             * @param couleur
             */
            public void setTableau(char[][] couleur) {
                

                tableau_char = couleur;   
                   
               
            }

            /**
             * récupère key, qui est la valeur du groupe en surbrillance
             */
            public void setKey(int cle) {
                this.key = cle;
                
            }

            
            /**
             * récupère le tableau d'actifs
             * @param etat
             */
            public void setActif(Boolean[][] etat) {
                

                tableau_actif = etat;     
                
            }
            /**
             * récupère le score
             */
            public void setScore(int nombre) {
                this.score = nombre;
            }

            /**
             * Méthode qui peint les boules en fonction des caractères du tableau, les effaces, les mets en surbrillance et affiche le score
             */
            
            @Override
            protected void paintComponent(Graphics pinceau) {

                int i,y,largeur,hauteur;

                Graphics secondPinceau = pinceau.create();
                


                if(this.isOpaque()) {

                    secondPinceau.setColor(this.getBackground());
                    secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
                } else {
                    secondPinceau.setColor(Color.WHITE);
                    secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
                }

                secondPinceau.setColor(Color.BLACK);
                secondPinceau.drawString("score: " + score, WIDTHSCORE, HEIGTHSCORE);
                if(remplisseur) {

                    
                    largeur = 10;
                    hauteur = 20;

                    
                        for(y = 0; y < 10 ; y++) {

                            for(i = 0; i < 15; i++) {
                                
                                
                                
                                if(tableau_actif[i][y] == true) {

                                    if(tableau_char[i][y] == 'R') {
                                        if(team.setConditionSurbrillanceR(i, y, key) == true){
                                            secondPinceau.setColor(new Color(232,51,51));
                                            secondPinceau.fillRect(largeur, hauteur, WIDTH+5, HEIGTH+5);
                                        } else {
                                            secondPinceau.setColor(new Color(208,16,16));
                                            secondPinceau.fillRect(largeur, hauteur, WIDTH, HEIGTH);
                                    }
                                        
                                    }
            
                                    if(tableau_char[i][y] == 'V') {
                                        if(team.setConditionSurbrillanceV(i, y, key) == true) {
                                            int[] xpoint = {largeur,largeur+WIDTH+5,largeur+25,largeur};
                                            int[] ypoint = {hauteur,hauteur,hauteur+HEIGTH+5,hauteur};
                                            secondPinceau.setColor(new Color(50,212,92));
                                            secondPinceau.fillPolygon(xpoint, ypoint, 4);
                                        } else {
                                            int[] xpoint = {largeur,largeur+WIDTH,largeur+25,largeur};
                                            int[] ypoint = {hauteur,hauteur,hauteur+HEIGTH,hauteur};
                                            secondPinceau.setColor(new Color(42,179,81));
                                            secondPinceau.fillPolygon(xpoint, ypoint, 4);
                                        }
                                        
                                        
                                    }
            
                                    if(tableau_char[i][y] == 'B') {
            
                                        if(team.setConditionSurbrillanceB(i, y, key) == true) {
                                            secondPinceau.setColor(new Color(34,139,212));
                                            secondPinceau.fillOval(largeur, hauteur, WIDTH+5, HEIGTH+5); 
                                        } else {
                                            secondPinceau.setColor(new Color(20,29,203));
                                            secondPinceau.fillOval(largeur, hauteur, WIDTH, HEIGTH);  
                                        }
                                        
                                    }
                                } else {

                                    secondPinceau.setColor(Color.WHITE);
                                    secondPinceau.fillRect(largeur, hauteur, WIDTH, HEIGTH);
                                }
                                
                                largeur += WIDTH+10;
                            }
                            largeur = 10;
                            hauteur += HEIGTH+10;
                        }

                    }
                }


                
            
            }

            
        
