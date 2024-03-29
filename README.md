 projetinfo-2019-stoneage-sai created by GitHub Classroom
-----------------------------------------------


# STONE AGE
 
<p align="center">
<img src="https://user-images.githubusercontent.com/42892662/70863562-ce27a600-1f49-11ea-9cfc-06c9f345d261.png" alt="Example of uploading image to GitHub's " width="400px">
</p>

## Pour Executer Stone Age :
> Pour lancer une seul partie: 
```shell
$ mvn exec:java -Dexec.args="argument"
```
<p align="center">
<img src="https://user-images.githubusercontent.com/42892662/70867079-8b79c400-1f71-11ea-9b73-3e67b8cd08ca.gif" alt="Example d'excution 1 partie  " width="600px">
</p>

> Pour lancer 500 parties avec les statistiques: 
```shell
$ mvn exec:java@500 -Dexec.args="argument"
```
<p align="center">
<img src="https://user-images.githubusercontent.com/42892662/70865975-f91ef380-1f63-11ea-9e86-c99bc669b339.gif" alt="Example d'excution 500 parties" width="600px">
</p>        

*L'argument entré est un nombre entre 2 et 4, il correspond au nombre de joueurs.*

> Lancement sans argument:
```shell
$ mvn exec:java@500
$ mvn exec:java 
``` 
*Le nombre de joueur est par défaut 4.*

### [Itération 1](https://github.com/L3-Info-Miage-Universite-Cote-D-Azur/projetinfo-2019-stoneage-sai/milestone/1) : 
Lancement d'une partie (un tour) avec :
- [X] Une zone Foret (gains Bois= résultat dés /3)
- [X] Un joueur qui possède une figurine(ouvrier)
#### Démo de jeu :
* Début de la partie : un joueur pose une figurine sur zone Foret.
* Fin de la partie : on affiche le gain du joueur en Bois.
#### Date prévue : 
*Le Vendredi 25 Octobre*

-----------------------------------------------

### [Itération 2](https://github.com/L3-Info-Miage-Universite-Cote-D-Azur/projetinfo-2019-stoneage-sai/milestone/2)
Lancement d'une partie en ajoutant :
- [X] Une zone Glaisière (gains Argile= résultat dés /4)
- [X] Une zone Carrière (gains Pierre = résultat dés /5)
- [X] Une zone rivière (gains Or= résultat dés /6)
- [X] Un joueur qui possède 5 figurines(ouvrier)
- [X] Lancé de dés
- [X] Score du joueur (le nombre total des ressources)
- [X] Possibilité de faire plusieurs tours dans une partie (choix IA)


#### Démo de jeu :
* Début de la partie : Le joueur (une IA) pose ses cinq figurines sur les zones qu'il choisit.
Chaque zone possède un nombre de place maximal. 
Un nombre de dé est jeté en fonction du nombre de figurines placées sur les zones. 
* Fin de Tour : lorsque le joueur finit de poser tous ses figurines, on affiche les gains récupérés. 
* Fin de partie : Si le joueur décide d’arrêter la partie en fin de tour (max 5 tours), on affiche le score du joueur. 
#### Date prévue : 
*Le Vendredi 08 Novembre*

-----------------------------------------------

### [Itération 3](https://github.com/L3-Info-Miage-Universite-Cote-D-Azur/projetinfo-2019-stoneage-sai/milestone/3)
Lancement d'une partie entre 4 joueurs en ajoutant :
- [X] Une zone Chasse (gains Nourriture= résultat dés /2)
- [X] Ajouter un 3 nouveaux joueurs parmi lesquelles deux joueurs IA avec chacun une manière de jouer différente.
- [X] Une zone Fabrication d'outils
- [X] Les Tuiles Outils 
- [X] Nourrir les figurines en fin de tour
- [X] La Condition Victoire (le joueur qui possède le score le plus élevé)
#### Démo de jeu :
* Début de la partie : les joueurs (IA) posent leurs figurines sur les zones qu'ils choisissent.
* Fin du tour : Lorsque les joueurs ont posé toutes leurs figurines. Ils récupèrent les gains. 
A la fin du tour, les figurines de chaque joueur sont nourries avec la nourriture qu’il possède, les ressources ou bien son score sera diminué de 10 points. 
* Fin de partie : Au bout de cinq tours, on a un gagnant et on affiche les scores de chaque joueur.
#### Date prévue : 
*Le Vendredi 15 Novembre*

-----------------------------------------------
### [Itération 4](https://github.com/L3-Info-Miage-Universite-Cote-D-Azur/projetinfo-2019-stoneage-sai/milestone/4)
Lancement d'une partie entre 4 joueurs en ajoutant :
- [X] Zone Agriculture (piste score et le champ)
- [X] Les cartes civilisations (carte jaune)
- [X] Condition de fin de partie : lorsqu'il reste moins de cartes civilisation que le nombre de joueurs

#### Démo de jeu :
* Début de la partie : les joueurs (IA) posent leurs figurines sur les zones et les cartes qu'ils choisissent.
* Fin du tour : Lorsque les joueurs ont posé toutes leurs figurines. Ils récupèrent les gains. 
Les cartes civilisation et tuiles bâtiment sont gardées pour la fin de la partie pour chaque joueur.
* Fin de partie : lorsqu'il reste moins de cartes civilisation que le nombre de joueurs. On a un gagnant et on affiche les scores de chaque joueur.
#### Date prévue : 
*Le Vendredi 22 Novembre*

-----------------------------------------------

### [Itération 5](https://github.com/L3-Info-Miage-Universite-Cote-D-Azur/projetinfo-2019-stoneage-sai/milestone/5)
Lancement d'une partie entre 4 joueurs en ajoutant :
- [X] Le reste des cartes civilisations 
- [X] Quelqes Tuiles Bâtiments 
- [X] Condition de fin de partie : lorsqu'il reste moins de tuiles bâtiment et de cartes civilisation que le nombre de joueurs
#### Démo de jeu :
* La partie débute et à chaque tour on voit l’avancement des joueurs et leurs gains. Le joueur peut aussi poser ses ouvriers sur les cartes Tuiles Bâtiments.
* Fin de partie : lorsqu'il reste moins de cartes civilisation et de tuiles bâtiment que le nombre de joueurs.
On a un gagnant et on affiche les scores de chaque joueur.
#### Date prévue : 
*Le Vendredi 29 Novembre*

----------------------------------------------- 
### [Itération 6](https://github.com/L3-Info-Miage-Universite-Cote-D-Azur/projetinfo-2019-stoneage-sai/milestone/6)
- [X] Développer le code permettant de lancer 500 parties 
- [X] Statistique

Lancement d'une partie entre 4 joueurs en ajoutant :
- [X] Zone Hutte
- [X] Toutes les Tuiles Bâtiments 
- [X] Plus de figurines si le joueur pose deux figurines sur la zone hutte dans un tour (jusqu'à 10 max)
 

#### Démo de jeu:
* La partie débute et à chaque tour on voit l’avancement des joueurs et leurs gains. Le joueur peut aussi poser deux ouvriers sur la zone hutte pour avoir un nouvel ouvrier en fin de tour.
* Fin de partie : lorsqu'il reste moins de cartes civilisation et de tuiles bâtiment que le nombre de joueurs.
On a un gagnant et on affiche les scores de chaque joueur.
#### Date prévue : 
*Le Vendredi 06 Décembre*


### [Itération 7](https://github.com/L3-Info-Miage-Universite-Cote-D-Azur/projetinfo-2019-stoneage-sai/milestone/7)
- [X] Régler les derniers bugs. 

#### Démo de jeu:
* On observe les joueurs qui commencent une partie entre bots. 
* On a un gagnant en fin de partie et les scores finaux.
#### Date prévue : 
*Le Dimanche 15 Décembre*



