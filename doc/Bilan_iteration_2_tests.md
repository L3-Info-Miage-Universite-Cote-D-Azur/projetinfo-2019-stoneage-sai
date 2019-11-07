- Nous avons testé la classe « Inventaire » avec tout d'abord un inventaire initialisé à vide : comparaison du nombre de ressources (0 au début) et comparaison du nombre d'ouvriers (5 au début). 
Puis avec un inventaire avec des valeurs : comparaison du nombre d’ouvriers et de ressources affecté a l'inventaire.
Nous n'avons pas testé toutes les méthodes dans cette classe, seulement les principale.

- La classe « Dé » a été testé avec 100 lancers, et à chaque fois nous comparons si le résultat est compris entre 1 et 6.

- Dans la classe « Zone », nous avons testé plusieurs méthodes en comparant notamment le nombre de ressources gagnées associées à un nombre d’ouvriers.

- Nous avons testé la classe « Choix » avec la méthode equals() appliquée sur un objet Choix et qui le compare avec un autre objet Choix passé en paramètre.

- Dans la classe « Partie » on a testé la méthode phaseAction() en vérifiant la liste des zones disponible après avoir appelé cette méthode (Le résultat est de 0).
Puis on a testé la méthode phasePlacement() en vérifiant si le nombre de zones jouées est égal au nombre de zones total moins le nombre de zones disponible.

- Et enfin nous avons testé la classe « Joueur » en comparant deux objets Choix, le premier créé manuellement avec son constructeur, et le second renvoyé avec la méthode placerOuvriers().