# Bilan [Iteration 7](https://github.com/L3-Info-Miage-Universite-Cote-D-Azur/projetinfo-2019-stoneage-sai/milestone/7)  :

- [X] [Ajout de la JAVADOC](https://github.com/L3-Info-Miage-Universite-Cote-D-Azur/projetinfo-2019-stoneage-sai/issues/120)
* La javadoc de toutes les classes a été effectuée. 
-----------------------------------------------
- [X] [Amélioration du code](https://github.com/L3-Info-Miage-Universite-Cote-D-Azur/projetinfo-2019-stoneage-sai/issues/117) 
* Ajout de la classe Plateau dans laquelle toutes les zones sont instanciées dans une liste.
* Ajout de la classe ressource qui va permettre d'instancier les ressources(Outil/Nourriture/Bois/Argile/Pierre/Or).
* Les ressources sont instanciées dans la classe Inventaire, elles sont ajoutées dans une HashMap qui a comme clé un 
entier qui correspond à l'indice de la zone qui va produire la ressource. 
* Le switch qui alourdit le code dans la classe zone a été enlevé.
-----------------------------------------------

- [X] Finir d'ajouter toutes les tuiles bâtiment
* Ajout des 5 dernières tuiles bâtiment. 
* Maintenant toutes les tuiles ont été implementées.
* Leur utilisation est possible dans le jeu.
-----------------------------------------------

- [X] Modification du joueur Bot2
* Le joueur Bot2 est mainetant plus intelligent, il suit une stratégie différente de celle du joueur IA.
* Suppression de certains choix aléatoires du joueur pour le rendre plus intelligent.

-----------------------------------------------

- [X] Amélioration de l'affichage
* On affiche les outils utilisés lorsque le joueur lance les dés.
* Pour les cartes bâtiment on affiche avec quelles ressources le joueur a choisi de payer.

-----------------------------------------------

*Dans cette dernière itération, nous avons réussi à finir le jeu avec toutes les règles.
Le jeu se joue entre 2 à 4 joueurs.
On peut lancer les statistiques des 500 parties ou seulement une partie pour voir le déroulement du jeu.*
-----------------------------------------------
