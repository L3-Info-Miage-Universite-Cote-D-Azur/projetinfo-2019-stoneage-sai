Nous avons créé les classes Choix(), Joueur(), Zone() et Partie().
La classe Choix permet de jouer un coup avec 2 paramètres(la zone jouée et le nombre d'ouvriers placés)
Cette classe a aussi une méthode equals(Object o). 

La classe Joueur utilise la classe Choix pour placer un ouvrier sur une zone.

La classe Zone définit une zone par niveauZone, son nombre d'ouvriers et permet de placer des ouvriers (le nombre étant passé en paramètre).
Elle dispose aussi d'une méthode résoudre, qui retourne le nombre de ressources gagnés dans la zone.

La classe Partie lance une partie en utilisant les différentes classes proposées. 
