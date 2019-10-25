# Bilan [Iteration 1](https://github.com/L3-Info-Miage-Universite-Cote-D-Azur/projetinfo-2019-stoneage-sai/milestone/1)  : 
## La class « Choix » permet au joueur de faire le choix de sa zone de posé un nombre d’ouvrier (ici 1 seul ouvrier) : 
Elle contient : 
- [X] un constructeur « Choix(int zoneJouer, int nbOuvriersJouer ) » qui attribue les valeur entre en paramètre dans les variables publique  de type entier  zoneJouer et nbOuvrier Joueur. 
- [X] Un Accesseurs « equals (Object o) » qui vérifie si les variables de la class vont bien êtres modifier en cas d’appel. 
-----------------------------------------------

## La class « Partie » qui va nous permettre de lancer une partie avec un seul bot joueur et observer son gain final en bois.
Elle contient : 
- [X] Un constructeur « Partie() » qui va cree un joueur et une zone (ici la zone foret). 
- [X] Un mutateur « jouer(int constante) » qui prend en paramètre une  constante de type entier qui remplace la valeur du dé qu’on ajoutera lors de la seconde itération. Cette méthode permet au joueur de faire le choix de placer un ouvrier dans une zone.
Elle va donc afficher le nombre d’ouvrier que le joueur a placer dans la zone qu’il a choisie ainsi que sont gains en bois. Ici le joueur n’a que la possibilité de poser un seul ouvrier dans une seul zone foret. La valeur du gains bois est calculer avec la constante/3.
Par exemple : 
- "Le joueur a placé 1 ouvrier(s) dans la zone foret. "
- "Le joueur gagne avec 2 bois."
- [X] La méthode « main » qui va lancer un début de partie. 

-----------------------------------------------

## La class «Zone » qui permettra de construire la zone foret dans la class « Partie ».
Elle contient :
- [X] Un constructeur « Zone(int niveau) » qui prend en paramètre un entier niveau pour le niveau de la zone dans le plateau. 
- [X] Un constructeur « Zone(int niveau,int constante) » qui prend en paramètre  un entier de plus que la précédente, c’est la constante qui représente la valeur du dé. 
- [X] Un mutateur « placerOuvrier(int nbOuvriers) » qui permettra de changer la variable qui contient le nombre d’ouvrier placer dans la zone. 
- [X] Un Accesseur « resoudre(int constante) » qui calcule et retourne le gains. 
- [X] Un Accesseur  «NomZone()» qui va retourner le nom de la zone qui a été créé. Les noms des zones sont donc placés dans une liste de type String. 
- [X] Un Accesseurs « ouvrierPlace(int nbOuvriers) » qui retourne une valeur booléenne pour vérifier si tous les ouvrier ont était placer ou non. 
 
## La class «Joueur» qui  va cree le joueur dans la Class « Partie ». 
Elle contient :
- [X] Un Accesseurs « placerOuvriers(int zone, int nbOuvriers) » qui va retourner un type Choix avec la zone et le nombre d’ouvrier qu’il pose dans cette zone. 
-----------------------------------------------
* *Le travaille Qu’on avait prévue dans la première itération a était réaliser.*
