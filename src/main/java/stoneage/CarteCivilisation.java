package stoneage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CarteCivilisation {
    private int fondDeCarte; //Fond jaune (0) ou vert (1)
    private int numeroCarte;
    /* 0-1:Potery, 2-3:Art, 4-5:Ecriture, 6-7:Medecine, 8-9:Cadran, 10-11:Transport, 12-13:Musique, 14-15:Tissage,
       16-17-18-19: Hutte
       20-21-22-23-24:  Agriculture
       25-26-27-28-29: Outils
       30-31-32-33-34-35: Figurine  */
    private ArrayList<Integer> partieInferieure = new ArrayList<Integer>();
    private ArrayList<Integer> partieInferieureNbBuilderDessus = new ArrayList<Integer>();
    /*  0:Pour les cartes fond verts
        1 ou 2 ou 3: Nb Builder present sur chaque carte */

    private ArrayList<Integer> partieSuperieur;
    /*  0: Point de victoire
        1: Nourriture
        2: Ressources
        3: Agriculture
        4: Outils
        5: Ressource au choix
        6: Lancer de dé suivant le nombre de joueurs: 4joueurs = 4lancer de dé
        7: 2 dés + ressources (memes conditions que les lancer pour gagner des ressources)
        8: Pioche une carte civilisation */

    public CarteCivilisation(int fondDeCarte, int numeroCarte) {
        this.fondDeCarte = fondDeCarte;
        this.numeroCarte = numeroCarte;
    }


    public void ajouterDansInventaire(Inventaire inventaire){
        inventaire.addCard(this);
    }
}
