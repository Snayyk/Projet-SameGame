import java.util.Random;
import java.io.*;
import javax.swing.*;

/**
 * La classe <code>Groupe</code> sert à créer les groupes de même couleur et adjacent, tester si la colonne est vide ,
 * la chute des boules, le décalage à gauche des boules, définir la fin du jeu
 * @version 1.1
 * @author Maxime Hauet, Yannis Sagal
 */


        public class Groupe {

                private char[][] tableau_char = new char[15][10];
                private Boolean[][] tableau_actif = new Boolean[15][10];
                private int [] [] tableau_groupeR = new int [15] [10];
                private int [] [] tableau_groupeV = new int [15] [10];
                private int [] [] tableau_groupeB = new int [15] [10];
                private int key;
               
                private int x,y;
                

                /**
                 * appel les méthodes qui crééent les groupes de couleurs
                 */

                public void creationGroupes() {
                  
                  this.remplirGroupes();
                  this.setGroupeR();
                  this.setGroupeV();
                  this.setGroupeB();
                  this.determinerGroupesRouge();
                  this.determinerGroupesVert();
                  this.determinerGroupesBleu();
  
                }

                /**
                 * récupère le tableau de caractères
                 */
                public char[][] getTableau() {

                  return tableau_char;  
              }

              /**
               * récupère le tableau d'actifs
               */
              public Boolean[][] getActif() {
                

                return tableau_actif;    
                
            }

            
            /**
             * détermine la fin de la partie
             * @return l'état de la partie: true = fini, false = en cours
             */
            public Boolean clafin() {
              int i,j,compteur=0;

              for(j=0;j<10;j++) {
                for(i=0;i<15;i++) {
                  if(tableau_actif[i][j] == true && ( (tableau_groupeR[i][j] != 1 && tableau_groupeB[i][j] != 1 && tableau_groupeV[i][j] != 1 ) || ( tableau_groupeR[i][j] != 0 && tableau_groupeB[i][j] != 0 && tableau_groupeV[i][j] != 0 ) )) {
                    compteur++;
                  }
                }
              }
              if(compteur == 0) {
                return true;
              } else {
                return false;
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
             * lit le fichier choisit et rempli la grille
             */
            public void remplissageTableauFichier() {
              int i,y;
              

                JFileChooser chooser = new JFileChooser(new File("./"));
              chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
              if (chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
                File fichier = chooser.getSelectedFile();
                String chemin = fichier.getAbsolutePath();
                try {

                FileReader fichier_lecture = new FileReader(chemin);
		            BufferedReader flux_lecture = new BufferedReader(fichier_lecture);

                  try {
                    
                    for(y = 0; y < 10 ; y++) {
                      String ligne = flux_lecture.readLine();
                      for(i = 0; i < 15; i++) {
                      tableau_char[i][y] = ligne.charAt(i);
                      
                      }
                      
                    }

                  } catch (IOException e) {
                    System.out.println("Erreur");
                  }
                  try {
                flux_lecture.close();
              } catch(IOException e) {
                
              }
              } catch (FileNotFoundException e) {
                System.out.println("Fichier non trouvé");
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

           /**
            * teste si la case du tableau en paramètre appartient au groupe rouge
            * @param v
            * @param w
            * @param cle
            * @return
            */
            public Boolean setConditionSurbrillanceR(int v, int w, int cle) {

              if(tableau_groupeR[v][w] == cle) {
                return true;
              } else {
                return false;
              }
            }
            /**
            * teste si la case du tableau en paramètre appartient au groupe vert
            * @param v
            * @param w
            * @param cle
            * @return
            */
            public Boolean setConditionSurbrillanceV(int v, int w, int cle) {

              if(tableau_groupeV[v][w] == cle) {
                return true;
              } else {
                return false;
              }
            }
            /**
            * teste si la case du tableau en paramètre appartient au groupe bleu
            * @param v
            * @param w
            * @param cle
            * @return
            */
            public Boolean setConditionSurbrillanceB(int v, int w, int cle) {

              if(tableau_groupeB[v][w] == cle) {
                return true;
              } else {
                return false;
              }
            }

            /**
             * récupère les coordonées du tableau et teste la couleurs de la case, si elle est active et appartenant à un groupe, key prend la valeur du groupe
             */
            public void getCoord(int v, int w) {
              this.x = v;
              this.y = w;
              if(tableau_char[x][y] == 'R') {
                if(tableau_groupeR[x][y] != 0 && tableau_groupeR[x][y] != 1) {
                  key = tableau_groupeR[x][y];
                  
                }
                
              }
              if(tableau_char[x][y] == 'V') {
                if(tableau_groupeV[x][y] != 0 && tableau_groupeV[x][y] != 1) {
                  key = tableau_groupeV[x][y];
                  
                }
              }
              if(tableau_char[x][y] == 'B') {
                if(tableau_groupeB[x][y] != 0 && tableau_groupeB[x][y] != 1) {
                  key = tableau_groupeB[x][y];
                  
                }
              }

            }

            /**
             * 
             * @return renvoie key
             */
            public int getKey() {
              return key;
            }
            /**
             * désactive les boules, appel la méthode de chute de boules, redéfini les groupes, décale à gauche en cas de colonne vide
             * @param v
             * @param w
             * @param cle
             */
            public void bouleInactive(int v, int w, int cle) {
              int i,j;

              for(j=0;j<10;j++) {

                for(i=0;i<15;i++) {

                  
                    if(tableau_char[x][y] == 'R') {
                      if(tableau_groupeR[i][j] == cle) {
                        tableau_char[i][j] = ' ';
                        tableau_actif[i][j] = false;
                        this.chuteBoule(i, j);
                        this.determinerGroupesRouge();
                        this.determinerGroupesVert();
                        this.determinerGroupesBleu();
                        if( this.testColonneVide(i, j) ) {
                          this.decalageGauche(i, j);
                        }
                        
                      }
                      
                    }
                    if(tableau_char[x][y] == 'V') {
                      if(tableau_groupeV[i][j] == cle) {
                        tableau_char[i][j] = ' ';
                        tableau_actif[i][j] = false;
                        this.chuteBoule(i, j);
                        this.determinerGroupesRouge();
                        this.determinerGroupesVert();
                        this.determinerGroupesBleu();
                        if( this.testColonneVide(i, j) ) {
                          this.decalageGauche(i, j);
                        }
                      }
                    }
                    if(tableau_char[x][y] == 'B') {
                      if(tableau_groupeB[i][j] == cle) {
                        tableau_char[i][j] = ' ';
                        tableau_actif[i][j] = false;
                        this.chuteBoule(i, j);
                        this.determinerGroupesRouge();
                        this.determinerGroupesVert();
                        this.determinerGroupesBleu();
                        if( this.testColonneVide(i, j) ) {
                          this.decalageGauche(i, j);
                        }
                        
                      }
                    }
                }
              }

            }

            /**
             * fait chuter les boules
             * @param v
             * @param w
             */
            public void chuteBoule(int v, int w) {

              int o;
              char ephemere1,ephemere2;
              Boolean bephemere1,bephemere2;

              for(o=w-1;o!=0-1;o--) {
                ephemere1 = tableau_char[v][o];
                ephemere2 = tableau_char[v][o+1];
                bephemere1 = tableau_actif[v][o];
                bephemere2 = tableau_actif[v][o+1];

                tableau_char[v][o+1] = ephemere1; 
                tableau_char[v][o] = ephemere2;
                tableau_actif[v][o+1] = bephemere1; 
                tableau_actif[v][o] = bephemere2;
               
              
              }

              



            }
            /**
             * teste si la colonne est vide
             * @param v
             * @param w
             * @return true si la colonne est vide ou false si elle est pleine
             */
            public Boolean testColonneVide(int v, int w) { 
              
              int i,compteur=0;

              for(i=0;i<10;i++) {
                if(tableau_char[v][i] == 'R') {
                    
                      compteur++;
                }
                if(tableau_char[v][i] == 'V') {
                  
                    compteur++;           
                }
                if(tableau_char[v][i] == 'B') {
                  
                    compteur++;            
                }
                }

                if(compteur !=0) {
                  return false;
                } else {
                  return true;
                }
                  
                            
            }

            /**
             * décale la colonne à gauche
             * @param v
             * @param w
             */
            public void decalageGauche(int v, int w) {
              
              int o,i;
              char ephemere1,ephemere2;
              Boolean bephemere1,bephemere2;

              for(i=v;i<14;i++) {
                  for(o=0;o<10;o++) {
                  ephemere1 = tableau_char[i][o];
                  ephemere2 = tableau_char[i+1][o];
                  bephemere1 = tableau_actif[i][o];
                  bephemere2 = tableau_actif[i+1][o];

                  tableau_char[i+1][o] = ephemere1; 
                  tableau_char[i][o] = ephemere2;
                  tableau_actif[i+1][o] = bephemere1; 
                  tableau_actif[i][o] = bephemere2;
                
                
                }
              }
                
              

            }

            /**
             * compte le nombre d'éléments dans le groupe en surbrillance
             * @return le nombre
             */
            public int calculGroupe(int v, int w, int y) {

                          int i,j,compteur = 0;
                          if(tableau_char[v][w] == 'R') {
                    
                            for(j=0;j<10;j++) {
                              for(i=0;i<15;i++) {
                                if(tableau_groupeR[i][j] == y) {
                                  compteur++;
                                }
                              }
                            }
                          }
                          if(tableau_char[v][w] == 'V') {
                            for(j=0;j<10;j++) {
                              for(i=0;i<15;i++) {
                                if(tableau_groupeV[i][j] == y) {
                                  compteur++;
                                }
                              }
                            }
                                       
                          }
                          if(tableau_char[v][w] == 'B') {
                            for(j=0;j<10;j++) {
                              for(i=0;i<15;i++) {
                                if(tableau_groupeB[i][j] == y) {
                                  compteur++;
                                }
                              }
                            }
                                       
                          }
                          
                          return compteur;
            }

                    
            /**
             * calcul les points par rapport au nombre de boules dans le groupe
             */
            public int calculPoints(int nombre) {

              int resultat;

              resultat = (nombre - 2) * (nombre - 2);
              return resultat;

            }

            /**
             * détermine les groupes de boules rouges
             */
            public void determinerGroupesRouge() {
              
              int i,j,y,imoinsun,iplusun,jmoinsun,jplusun,levraiy,compteur;
              for(j = 0, y=2; j < 10 ; j++) {

                for(i = 0 ; i < 15; i++) {
                  compteur=0;
                  imoinsun = i-1;
                  iplusun = i+1;
                  jmoinsun = j-1;
                  jplusun = j+1;

                  if(i==0) {
                    imoinsun = i;
                  }
                  if(i==14) {
                    iplusun = i;
                  }
                  if(j==0) {
                    jmoinsun = j;
                  }
                  if(j==9) {
                    jplusun = j;
                  }

                  
                    if(tableau_char[i][j] == 'R' && tableau_actif[i][j] == true) /*Sous entendu: Si la case du tableau est rouge et active*/{
                      

                      if(imoinsun!=i) {
                        
                        if(tableau_char[imoinsun][j] == 'R' && tableau_actif[i][j] == true) {
                           levraiy = y;
                           y = tableau_groupeR[imoinsun][j];
                           this.assignationGroupesRouge(i, j, y);
                           this.assignationGroupesRouge(imoinsun, j, y);

                           if(tableau_char[iplusun][j] == 'R' && tableau_actif[i][j] == true) {
                             this.assignationGroupesRouge(iplusun, j, y);
                          }

                           compteur+=1;
                           y = levraiy;
                        }
                      }
                        

                      if(iplusun!=i) {
                        if(tableau_char[iplusun][j] == 'R' && tableau_actif[i][j] == true) {
                          if(tableau_char[iplusun][jmoinsun] == 'R' && tableau_actif[i][j] == true) {
                            levraiy = y;
                            y = tableau_groupeR[iplusun][jmoinsun];
                          this.assignationGroupesRouge(i, j, y);
                           this.assignationGroupesRouge(iplusun, j, y);
                           y = levraiy;
                          } else {
                            this.assignationGroupesRouge(i, j, y);
                           this.assignationGroupesRouge(iplusun, j, y);
                          }
                           
                           compteur+=2;
                        }
                      }
                        
                        if(jmoinsun!=j) {
                          if(tableau_char[i][jmoinsun] == 'R' && tableau_actif[i][j] == true) {
                          levraiy = y;
                           y = tableau_groupeR[i][jmoinsun];
                           this.assignationGroupesRouge(i, j, y);
                           this.assignationGroupesRouge(i, jmoinsun, y);
                           
                           if(tableau_char[imoinsun][j] == 'R' && tableau_actif[i][j] == true) {
                            
                            this.assignationGroupesRouge(imoinsun, j, y);
                           }
                           if(tableau_char[i][jplusun] == 'R' && tableau_actif[i][j] == true) {
                            this.assignationGroupesRouge(i, jplusun, y);
                            
                         }
                           y = levraiy;
                           compteur+=4;
                           
                        } 
                      }
                        if(jplusun!=j) {
                          if(tableau_char[i][jplusun] == 'R' && tableau_actif[i][j] == true) {
                        
                           this.assignationGroupesRouge(i, j, y);
                           this.assignationGroupesRouge(i, jplusun, y);
                           compteur+=5;
                        }
                        }
                        if(compteur == 1 || compteur>3) {
                          y++;
                        } 
                        
                    }
                    
                }
               
             }
             

            }

            
             /**
             * détermine les groupes de boules vertes
             */
            public void determinerGroupesVert() {
              int i,j,y,imoinsun,iplusun,jmoinsun,jplusun,levraiy,compteur;
              for(j = 0, y=100; j < 10 ; j++) {

                for(i = 0 ; i < 15; i++) {
                  compteur=0;
                  imoinsun = i-1;
                  iplusun = i+1;
                  jmoinsun = j-1;
                  jplusun = j+1;

                  if(i==0) {
                    imoinsun = i;
                  }
                  if(i==14) {
                    iplusun = i;
                  }
                  if(j==0) {
                    jmoinsun = j;
                  }
                  if(j==9) {
                    jplusun = j;
                  }

                  
                    if(tableau_char[i][j] == 'V' && tableau_actif[i][j] == true) /*Sous entendu: Si la case du tableau est verte et active*/{
                      

                      if(imoinsun!=i) {
                        
                        if(tableau_char[imoinsun][j] == 'V' && tableau_actif[imoinsun][j] == true) {
                           levraiy = y;
                           y = tableau_groupeV[imoinsun][j];
                           this.assignationGroupesVert(i, j, y);
                           this.assignationGroupesVert(imoinsun, j, y);

                           if(tableau_char[iplusun][j] == 'V' && tableau_actif[iplusun][j] == true) {
                             this.assignationGroupesVert(iplusun, j, y);
                          }

                           compteur+=1;
                           y = levraiy;
                        }
                      }
                        

                      if(iplusun!=i) {
                        if(tableau_char[iplusun][j] == 'V' && tableau_actif[iplusun][j] == true) {
                          if(tableau_char[iplusun][jmoinsun] == 'V' && tableau_actif[iplusun][jmoinsun] == true) {
                            levraiy = y;
                            y = tableau_groupeV[iplusun][jmoinsun];
                          this.assignationGroupesVert(i, j, y);
                           this.assignationGroupesVert(iplusun, j, y);
                           y = levraiy;
                          } else {
                            this.assignationGroupesVert(i, j, y);
                           this.assignationGroupesVert(iplusun, j, y);
                          }
                           
                           compteur+=2;
                        }
                      }
                        
                        if(jmoinsun!=j) {
                          if(tableau_char[i][jmoinsun] == 'V' && tableau_actif[i][jmoinsun] == true) {
                          levraiy = y;
                           y = tableau_groupeV[i][jmoinsun];
                           this.assignationGroupesVert(i, j, y);
                           this.assignationGroupesVert(i, jmoinsun, y);
                           
                           if(tableau_char[imoinsun][j] == 'V' && tableau_actif[imoinsun][j] == true) {
                            
                            this.assignationGroupesVert(imoinsun, j, y);
                           }
                           if(tableau_char[i][jplusun] == 'V' && tableau_actif[i][jplusun] == true) {
                            this.assignationGroupesVert(i, jplusun, y);
                            
                         }
                           y = levraiy;
                           compteur+=4;
                           
                        } 
                      }
                        if(jplusun!=j) {
                          if(tableau_char[i][jplusun] == 'V' && tableau_actif[i][jplusun] == true) {
                        
                           this.assignationGroupesVert(i, j, y);
                           this.assignationGroupesVert(i, jplusun, y);
                           compteur+=5;
                        }
                        }
                        if(compteur == 1 || compteur>3) {
                          y++;
                        } 
                        
                    }
                    
                }
               
             }


            }

            /**
             * détermine les groupes de boules bleues
             */
            public void determinerGroupesBleu() {
              int i,j,y,imoinsun,iplusun,jmoinsun,jplusun,levraiy,compteur;
              for(j = 0, y=200; j < 10 ; j++) {

                for(i = 0 ; i < 15; i++) {
                  compteur=0;
                  imoinsun = i-1;
                  iplusun = i+1;
                  jmoinsun = j-1;
                  jplusun = j+1;

                  if(i==0) {
                    imoinsun = i;
                  }
                  if(i==14) {
                    iplusun = i;
                  }
                  if(j==0) {
                    jmoinsun = j;
                  }
                  if(j==9) {
                    jplusun = j;
                  }

                  
                    if(tableau_char[i][j] == 'B' && tableau_actif[i][j] == true) /*Sous entendu: Si la case du tableau est bleu et active*/{
                      

                      if(imoinsun!=i) {
                        
                        if(tableau_char[imoinsun][j] == 'B' && tableau_actif[imoinsun][j] == true) {
                           levraiy = y;
                           y = tableau_groupeB[imoinsun][j];
                           this.assignationGroupesBleu(i, j, y);
                           this.assignationGroupesBleu(imoinsun, j, y);

                           if(tableau_char[iplusun][j] == 'B' && tableau_actif[iplusun][j] == true) {
                             this.assignationGroupesBleu(iplusun, j, y);
                          }

                           compteur+=1;
                           y = levraiy;
                        }
                      }
                        

                      if(iplusun!=i) {
                        if(tableau_char[iplusun][j] == 'B' && tableau_actif[iplusun][j] == true) {
                          if(tableau_char[iplusun][jmoinsun] == 'B' && tableau_actif[iplusun][jmoinsun] == true) {
                            levraiy = y;
                            y = tableau_groupeB[iplusun][jmoinsun];
                          this.assignationGroupesBleu(i, j, y);
                           this.assignationGroupesBleu(iplusun, j, y);
                           y = levraiy;
                          } else {
                            this.assignationGroupesBleu(i, j, y);
                           this.assignationGroupesBleu(iplusun, j, y);
                          }
                           
                           compteur+=2;
                        }
                      }
                        
                        if(jmoinsun!=j) {
                          if(tableau_char[i][jmoinsun] == 'B' && tableau_actif[i][jmoinsun] == true) {
                          levraiy = y;
                           y = tableau_groupeB[i][jmoinsun];
                           this.assignationGroupesBleu(i, j, y);
                           this.assignationGroupesBleu(i, jmoinsun, y);
                           
                           if(tableau_char[imoinsun][j] == 'B' && tableau_actif[i][jmoinsun] == true) {
                            
                            this.assignationGroupesBleu(imoinsun, j, y);
                           }
                           if(tableau_char[i][jplusun] == 'B' && tableau_actif[i][jplusun] == true) {
                            this.assignationGroupesBleu(i, jplusun, y);
                            
                         }
                           y = levraiy;
                           compteur+=4;
                           
                        } 
                      }
                        if(jplusun!=j) {
                          if(tableau_char[i][jplusun] == 'B' && tableau_actif[i][jplusun] == true) {
                        
                           this.assignationGroupesBleu(i, j, y);
                           this.assignationGroupesBleu(i, jplusun, y);
                           compteur+=5;
                        }
                        }
                        if(compteur == 1 || compteur>3) {
                          y++;
                        } 
                        
                    }
                    
                }
               
             }


            }
	
	
                /**
                 * remplis les tableaux de groupes à 1 (avant la determination des groupes de couleurs)
                 */
                public void remplirGroupes(){

                    int i,j;
                    for(j = 0; j < 10 ; j++) {

                        for(i = 0; i < 15; i++) {
                           
                            tableau_groupeR[i][j] = 1;
                            tableau_groupeB[i][j] = 1;
                            tableau_groupeV[i][j] = 1;
                            
                        }
                        
                    }



                }

                /**
                 * donne la valeur qu'auront les groupes rouges
                 * @param i
                 * @param j
                 * @param y
                 */
                public void assignationGroupesRouge(int i, int j, int y) {

                  tableau_groupeR[i][j]= y;
                  
                }
    
                /**
                 * donne la valeur qu'auront les groupes verts
                 * @param i
                 * @param j
                 * @param y
                 */
                public void assignationGroupesVert(int i, int j, int y) {
    
                  tableau_groupeV[i][j]= y;
                  
                }
    
                /**
                 * donne la valeur qu'auront les groupes bleus
                 * @param i
                 * @param j
                 * @param y
                 */
                public void assignationGroupesBleu(int i, int j, int y) {
    
                  tableau_groupeB[i][j]= y;
                  
                }


                /**
                 * met toutes les boules autre que rouges à 0
                 */
                public void setGroupeR(){
                  
                int i,j;
                for(j = 0; j < 10 ; j++) {

                    for(i = 0; i < 15; i++) {
                      
                      if (tableau_char[i][j] != 'R'){
                            tableau_groupeR[i][j] = 0;
                      }
                    } 
                }
                

                }
                /**
                 * met toutes les boules autre que vert à 0
                 */
                public void setGroupeV(){
                    int i,j;
                    for(j = 0; j < 10 ; j++) {
    
                        for(i = 0; i < 15; i++) {
                           
                          if (tableau_char[i][j] != 'V'){
                                tableau_groupeV[i][j] = 0;
                          }
                        }
                    }
                }
                
                /**
                 * met toutes les boules autre que bleues à 0
                 */
                public void setGroupeB(){

                    int i,j;
                    for(j = 0; j < 10 ; j++) {
    
                        for(i = 0; i < 15; i++) {
                           
                          if (tableau_char[i][j] != 'B') {
                                tableau_groupeB[i][j] = 0;
                          }
                          
                        }
                        
                    }
                    
                }
          
        }

