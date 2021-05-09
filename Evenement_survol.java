import javax.swing.*;
import java.awt.event.*;

/**
 * La classe <code>Event_survol</code> permet de gérer la surbrillance des groupes au survol et d'activer la chute des boules lors d'un clique
 * 
 * @version 1.1
 * @author Maxime Hauet, Yannis Sagal
 */

            public class Evenement_survol implements MouseMotionListener, MouseListener {

                private Groupe objet;
                
                private final int WIDTH = 60; 
                private final int HEIGTH = 50; 
                private int x,y;
                private JFrame fenetre_jeu;
                
                
                private Remplissage  surbrillance;
                
                private int taille_gr;
                private int score = 0;
                private int key;
                private int key_out;

                /**
                 * récupère les objets groupe,remplissage et la fenêtre du jeu de la classe jeu
                 */
                public Evenement_survol(Groupe gr, Remplissage rm, JFrame jeu) {
                	
                    this.objet = gr;
                    this.surbrillance = rm;
                    this.fenetre_jeu = jeu;
                    
                }
                 
                /**
                 * Lors du clique, appelle les méthodes pour la chute des boules du groupe, récupère le score et l'envoie à la classe remplissage
                 * Ferme la fenêtre de jeu en cas ce fin de partie et ouvre le menu de fin
                 */
                public void mouseClicked(MouseEvent evenement) {

                    objet.getCoord(x, y);
                    key_out = objet.getKey();
                    taille_gr = objet.calculGroupe(x, y, key_out);
                    score += objet.calculPoints(taille_gr);
                    surbrillance.setScore(score);
                    objet.bouleInactive(x, y, key_out);
                    surbrillance.repaint();
                    if(objet.clafin() ) {
                        fenetre_jeu.dispose();
                        Menu_Fin fin = new Menu_Fin(score);
                    }
                    
                    
                }
                    // un bouton cliqué
                    public void mouseEntered(MouseEvent evenement) {
                
                        
                    
                    }  
                    // debut du survol
                    public void mouseExited(MouseEvent evenement) {
                        
                
                    }           // fin du survol
                    public void mousePressed(MouseEvent evenement) {
                      
                    
                    }          // un bouton appuyé
                    public void mouseReleased(MouseEvent evenement) {

                    
                    
                
                    }
                    
                    public void mouseDragged(MouseEvent evenement) {
                       
                
                    }      
                    
                    /**
                     * Détermine la case du tableau par rapport aux coordonnées de la souris
                     */
                    public void mouseMoved(MouseEvent evenement) {
                        
                        
                        int coordx,coordy;

                        coordx = evenement.getX()-20;
                        coordy = evenement.getY()-50;
                        
                        if( (coordx > 0 && coordx < 890) && (coordy > 0 && coordy < 490) ) {
                            x = coordx/WIDTH;
                           
                            y = coordy/HEIGTH;
                            
                           objet.getCoord(x, y);
                           key = objet.getKey();
                           surbrillance.setKey(key);
                           surbrillance.repaint();

                           
                            
                        }
  
                    }
                
            }
