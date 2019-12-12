package stoneage;
import java.util.ArrayList;

/**
 * Cette classe représente les tuiles bâtiment.
 * Dans cette classe:
 *
 * Chaque tuile a un coût allant de 0 à 21:
 *      @see int#buildingCost :
 *      Chaque entier correspond à un coût: (TYPO: b = bois, a =argile, p = pierre, o =or)
 *          0:2b+1a, 1:1b+2a, 2:2b+1p, 3:1b+1a+1p,  4:2b+1o, 5:1b+1a+1o, 6:2a+1p, 7:1b+2p, 8:1b+1p+1o, 9:1a+2p
 *          10:2a+1o, 11:1a+1p+1o, 12:2p+1o
 *          5 ressources de:
 *              13: 4types, 14:3 types, 15: 2types, 16: 1type
 *          4 ressources de:
 *              17: 4types, 18: 3 types, 19: 2types, 20:1type
 *          21: Au moins une matière première et au maximum 7.
 *
 * Chaque tuile a un score allant de 0 à 7:
 *      @see int#cardScore
 *      Chaque entier correspond à un nombre de points:
 *          0:10points, 1:11points, 2:12points, 3:13points, 4:14points, 5:15points, 6:16points.
 *          Exception pour l'entier 7, où le nombre de points dépend de la valeur de la matière première utilisée.
 *
 *  Il y a aussi une liste générale de toutes les cartes
 *      @see #cards
 *  Cette liste est instanciée avec la méthode addCards().
 *      @see #addCards()
 */

public class BuildingTiles {
    private int cardScore;
    private int buildingCost;
    private ArrayList<BuildingTiles> cards;

    /**
     * Ajoute toutes les tuiles bâtiment dans la liste cards.
     * @see #cards
     * @see #addCards()
     *
     */
    BuildingTiles(){
        addCards();
    }

    /**
     * Constructeur de la classe BuildingTiles
     * @param cardScore:
     *                 int (de 0 à 7)
     *                 Score de la tuile bâtiment.
     * @param buildingCost:
     *                    int (de 0 à 21)
     *                    Coût de la tuile bâtiment.
     */
    BuildingTiles(int cardScore,int buildingCost){
        this.cardScore=cardScore;
        this.buildingCost=buildingCost;
    }

    /**
     * Méthode pour ajouter toutes les tuiles bâtiment dans la liste cards.
     * @see #cards
     *
     */
    public void addCards(){
        cards=new ArrayList<>();
        cards.add(new BuildingTiles(0,0));//carte numero 0(car a l'indice 0) donne +10 points et coute 2b+1a
        cards.add(new BuildingTiles(1,1));//+11
        cards.add(new BuildingTiles(1,2));
        cards.add(new BuildingTiles(2,3));//+12
        cards.add(new BuildingTiles(2,3));
        cards.add(new BuildingTiles(2,4));
        cards.add(new BuildingTiles(3,5));//+13
        cards.add(new BuildingTiles(3,5));
        cards.add(new BuildingTiles(3,6));
        cards.add(new BuildingTiles(3,7));
        cards.add(new BuildingTiles(4,8));//+14
        cards.add(new BuildingTiles(4,8));
        cards.add(new BuildingTiles(4,9));
        cards.add(new BuildingTiles(4,10));
        cards.add(new BuildingTiles(5,11));//+15
        cards.add(new BuildingTiles(5,11));
        cards.add(new BuildingTiles(6,12));//+16
        cards.add(new BuildingTiles(7,13));
        cards.add(new BuildingTiles(7,14));
        cards.add(new BuildingTiles(7,15));
        cards.add(new BuildingTiles(7,16));
        cards.add(new BuildingTiles(7,17));
        cards.add(new BuildingTiles(7,18));
        cards.add(new BuildingTiles(7,19));
        cards.add(new BuildingTiles(7,20));
        cards.add(new BuildingTiles(7,21));
        cards.add(new BuildingTiles(7,21));
        cards.add(new BuildingTiles(7,21));
    }

    /**
     * Ajoute une tuile bâtiment dans l'inventaire d'un joueur.
     * @param inventaire:
     *                  Inventaire d'un joueur.
     */
    public void ajouterDansInventaire(Inventaire inventaire){
        inventaire.addCarteBat(this);
    }

    public int getCardScore() {
        return cardScore;
    }
    public int getBuildingCost() {
        return buildingCost;
    }
    public ArrayList<BuildingTiles> getCards() {
        return cards;
    }
}
