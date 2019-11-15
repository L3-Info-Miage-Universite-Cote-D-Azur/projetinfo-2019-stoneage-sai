# Bilan [Iteration 3](https://github.com/L3-Info-Miage-Universite-Cote-D-Azur/projetinfo-2019-stoneage-sai/milestone/3)  :

- [X] Modification de la classe « zone » qui contient deux nouvelles zones :
     - La zone chasse qui peut contenir un nombre illimité d’ouvriers (initialiser a 100 dans notre code).
     - La zone fabrication d’outils qui peut contenir un seul ouvrier.
-----------------------------------------------

- [X] Modification de la classe « Inventaire » pour l’ajout de la nourriture et des outils.
-----------------------------------------------

- [X] Création d’une interface « Joueurs » que nos différents joueurs implémenteront.
-----------------------------------------------

- [X] Modification de la classe « Joueur » afin de s’adapter à la nouvelle interface.
-----------------------------------------------

- [X] Création de deux IA : « JoueurIA » et « JoueurBot2 » qui ont chacun une façon différente de joué.
-----------------------------------------------

- [X] Création d’une classe « StoneAge » qui est le moteur du jeux (cette classe contient uniquement le main).
-----------------------------------------------

- [X] Modification de la classe « Partie » afin de :
     - Nourrir les ouvriers des joueurs en fin de tour avec la nourriture qu’il possède (pénalité de 10 points sur ses ressources ou son score finale si le joueur ne possède pas assez de nourriture pour nourrir ses ouvriers).
     - Lancer une partie avec un nombre de joueurs variable (entre 1 et 4 joueurs).
     - Déterminer le gagnant de la partie (le joueur qui possède le score le plus élevé).
-----------------------------------------------

 * Dans cette iteration on a remis un travail non complet car notre code contient deux bugs, pour lesquelles on a pas trouvé de solution a temps. 
 - [X] Bug 1: Les joueures peuvent utilisé des zones meme si elles ont plus de place disponnible dedans. 
     - Hypothese pour une Solution :les Joueures ont chacun une liste de zone alors qu'il devrait avoir une seul liste pour tout le monde, chaque joueur peut avoir une liste qui contient des booleenne pour chaque zone( zone prise) a la place d'une liste de zone.

- [X] Bug 2: Les joeurs peuvent posé plus de figurine sur une zone que le nombre de place qu'elle posséde. 
     - Hypothese pour une Solution : Pour ce bug, si on regle le 1er bug celui ci va etres corrigé automatiquement.
