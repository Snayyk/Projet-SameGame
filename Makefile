### VARIABLES ###

JC = javac
JCFLAGS = -encoding UTF-8 -implicit:none

JVM = java
JVMFLAGS = 

### REGLES ESSENTIELLES ###

Main.class : Main.java Menu.class Event_menu_aleatoire.class Event_menu_fichier.class
	${JC} ${JCFLAGS} Main.java

Menu.class : Menu.java Event_menu_aleatoire.class Event_menu_fichier.class Jeu.class
	${JC} ${JCFLAGS} Menu.java

Event_menu_fichier.class : Event_menu_fichier.java Menu.class Main.class
	${JC} ${JCFLAGS} Event_menu_fichier.java

Event_menu_aleatoire.class : Event_menu_aleatoire.java Menu.class Main.class
	${JC} ${JCFLAGS} Event_menu_aleatoire.java

Jeu.class : Jeu.java Evenement_survol.class Groupe.class Remplissage.class
	${JC} ${JCFLAGS} Jeu.java

Evenement_survol.class : Evenement_survol.java Groupe.class Remplissage.class Menu_Fin.class
	${JC} ${JCFLAGS} Evenement_survol.java

Groupe.class : Groupe.java Evenement_survol.class
	${JC} ${JCFLAGS} Groupe.java		

Remplissage.class : Remplissage.java Groupe.class
	${JC} ${JCFLAGS} Remplissage.java	

Menu_Fin.class : Menu_Fin.java Evenement_menu_fin.class
	${JC} ${JCFLAGS} Menu_Fin.java		

Evenement_menu_fin.class : Evenement_menu_fin.java Menu_Fin.class
	${JC} ${JCFLAGS} Evenement_menu_fin.java		


### REGLES OPTIONNELLES ###

run : Main.class
	${JVM} ${JVMFLAGS} Main

clean :
	-rm -f *.class

mrproper : clean Main.class

### BUTS FACTICES ###

.PHONY : run clean mrproper

### FIN ###