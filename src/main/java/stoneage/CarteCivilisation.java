package stoneage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CarteCivilisation {
    private int fondDeCarte; //Fond vert (0) ou jaune (1)
    private int numeroCarte;
    /* 0-1:Potery, 2-3:Art, 4-5:Ecriture, 6-7:Medecine, 8-9:Cadran, 10-11:Transport, 12-13:Musique, 14-15:Tissage,
       16-17-18-19-20: Hutte
       21-22-23-24-25: Agriculture
       26-27-28-29-30: Outils
       31-32-33-34-35: Figurine  */
    private  ArrayList<CarteCivilisation> allCards;
    private int nbBuilderOnIt;
    /*  0:Pour les cartes fond verts
        1 ou 2 ou 3: Nb Builder present sur chaque carte */
    private int partieSuperieur;
    /*  0: Point de victoire
        1: Nourriture
        2: Ressources
        3: Agriculture
        4: Outils
        5: Ressource au choix
        6: Lancer de dé, suivant le nombre de joueurs: 4joueurs = 4lancers de dé
        7: 2 dés + ressources (memes conditions que les lancer pour gagner des ressources)
        8: Pioche une carte civilisation */

    public CarteCivilisation() {
    	addCardsToList();
    	
    }
    public CarteCivilisation(int fondDeCarte, int numeroCarte, int partieSuperieur, int nbBuilderOnIt) {
        this.fondDeCarte = fondDeCarte;
        this.numeroCarte = numeroCarte;
        this.nbBuilderOnIt = nbBuilderOnIt;
        this.partieSuperieur = partieSuperieur;
    }

    public void ajouterDansInventaire(Inventaire inventaire){
        inventaire.addCarteCiv(this);
    }
    public void initialiseDeck(){
        shuffleCards(allCards);
    }
    public void shuffleCards(ArrayList<CarteCivilisation> allCards){
        /* TODO */
    }
    public  void addCardsToList(){
        //  FOND VERT
    	allCards= new ArrayList<>();
        allCards.add(new CarteCivilisation(0,0,1,0));
        allCards.add(new CarteCivilisation(0,1,6,0));
        allCards.add(new CarteCivilisation(0,2,7,0));
        allCards.add(new CarteCivilisation(0,3,4,0));
        allCards.add(new CarteCivilisation(0,4,8,0)); //Ex: vert, carte n°4-> Ecriture, pioche carteCiv.
        allCards.add(new CarteCivilisation(0,5,6,0));
        allCards.add(new CarteCivilisation(0,6,5,0));
        allCards.add(new CarteCivilisation(0,7,1,0));
        allCards.add(new CarteCivilisation(0,8,3,0));
        allCards.add(new CarteCivilisation(0,9,6,0));
        allCards.add(new CarteCivilisation(0,10,6,0));
        allCards.add(new CarteCivilisation(0,11,2,0));
        allCards.add(new CarteCivilisation(0,12,0,0));
        allCards.add(new CarteCivilisation(0,13,0,0));
        allCards.add(new CarteCivilisation(0,14,1,0));
        allCards.add(new CarteCivilisation(0,15,1,0));
        //  FOND JAUNE
        allCards.add(new CarteCivilisation(1,16,1,1)); //Ex: jaune, carte n°16-> hutte, nourriture, 1ouvrier.
        allCards.add(new CarteCivilisation(1,17,6,1));
        allCards.add(new CarteCivilisation(1,18,6,2));
        allCards.add(new CarteCivilisation(1,19,1,2));
        allCards.add(new CarteCivilisation(1,20,0,3));
        allCards.add(new CarteCivilisation(1,21,1,2));
        allCards.add(new CarteCivilisation(1,22,6,2));
        allCards.add(new CarteCivilisation(1,23,2,1));
        allCards.add(new CarteCivilisation(1,24,6,1));
        allCards.add(new CarteCivilisation(1,25,3,1));
        allCards.add(new CarteCivilisation(1,26,4,1));
        allCards.add(new CarteCivilisation(1,27,4,1));
        allCards.add(new CarteCivilisation(1,28,6,2));
        allCards.add(new CarteCivilisation(1,29,6,2));
        allCards.add(new CarteCivilisation(1,30,4,2));
        allCards.add(new CarteCivilisation(1,31,2,2));
        allCards.add(new CarteCivilisation(1,32,7,2));
        allCards.add(new CarteCivilisation(1,33,7,1));
        allCards.add(new CarteCivilisation(1,34,2,1));
        allCards.add(new CarteCivilisation(1,35,2,1));
    }
    public int getFondDeCarte() {
        return fondDeCarte;
    }
    public int getNumeroCarte() {
        return numeroCarte;
    }
    public int getNbBuilderOnIt() {
        return nbBuilderOnIt;
    }
    public int getPartieSuperieur() {
        return partieSuperieur;
    }
    public  ArrayList<CarteCivilisation> getAllCards() {
        return allCards;
    }
}
