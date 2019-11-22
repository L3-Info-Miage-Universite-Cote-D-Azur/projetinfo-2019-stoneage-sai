# Bilan [Iteration 4](https://github.com/L3-Info-Miage-Universite-Cote-D-Azur/projetinfo-2019-stoneage-sai/milestone/4)  :

- [X] Ajouts des cartes civilisations :
     - Ajouts des 36 cartes civilisations pour jouer. 
     - Mélange de cartes possibles pour ne pas avoir les cartes dans le même ordre à chaque lancement de partie(pas encore utilisé)

Pour les cartes civilisations, tout n'est pas encore implémenté:
- Les cartes contenant les jets de dés suivant le nombre de joueurs peuvent être prises mais ne font l'objet d'aucune utilisation. 
- Si un joueur a pris la carte dont le résultat est calculé en fonction du nombre de bâtiments: 
	- On dit que le nombre de bâtiments du joueurs est de 1 pour utiliser la carte. 
	- Ceci sera changé lors l'ajout des Tuiles Bâtiments.
-----------------------------------------------

- [X] Modification de la classe Zone :
     - Les zones permettent de distribuer les ressources, gagnées par les joueurs, dans leur inventaire.
     - Ajout de l'emplacement des cartes civilisations pour pouvoir placer des ouvriers dessus.
     
-----------------------------------------------

- [X] Modification des Joueurs
     - Les joueurs peuvent maintenant choisir de se placer sur une carte, de la prendre ou non contre des ressources(choix de stratégie possible)
     - Utilisation des cartes possibles
-----------------------------------------------

- [X] Modification de la classe StoneAge :
     - Modification de l'affichage d'une partie suivant le nombre de joueurs
     - Plus de détail pour le déroulé de la partie. 
-----------------------------------------------

- [X] Modifications de conditions de fin de partie :
     - La partie se termine lorsque le nombre de cartes Civilisations est inférieur au nombre de joueurs.

-----------------------------------------------

- [X] Ajout de règles spécifiques en rapport au nombre de joueurs présents  dans la partie
     - S'il y a 3 joueurs, il y a un maximum de 2 joueurs par zone (forêt, glaisière, carrière et rivière)
     - S'il y a 2 joueurs, il y a un seul joueur par zone (forêt, glaisière, carrière et rivière)

Pour l'instant, lorsqu'un joueur fait un choix non autorisé par ces règles, il refait un autre choix.
Pour éviter le cas où le joueur fait toujours un choix non autorisé, au bout d'un certain nombre (10) de tentatives.
On choisira la zone chasse à sa place (en mettant toujours le nombre d'ouvriers qu'il le souhaite) 


Pour ce point, nous n'avons pas encore fini d'implémenter les règles, nous y travaillons encore.
En effet, puisque la zone Hutte n'est pas encore créée, la règle disant que: 
Pour 2 ou 3 joueurs, seulement 2 des 3 zones (Fabrication d'outils, Hutte et Champs), n'est pas encore d'actualité.

----------------------------------------------

- [X] Calcul du score avec les ressources et les cartes 

----------------------------------------------
Pour la phase où le joueur doit nourrir ces ouvriers, le joueur n'a pas encore le choix de dire s'il préfère donner des ressources ou alors perdre des points, lorsque celui-ci n'a pas assez de nourriture pour nourrir ses ouvriers.
Cette option sera ajoutée prochainement.
