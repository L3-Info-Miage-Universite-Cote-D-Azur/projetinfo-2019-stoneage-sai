package stoneage;
import java.util.ArrayList;
import java.util.Collections;
/**
 *
 * @author Ossama
 */
public class BuildingTiles {
    private int cardScore; 
    /*
    0:+10points ,1:+11points,2:+12 points,3:+13 points,4:+14 points,5:+15 points,6:+16 points,7:les points gagnes dependent 
    de la valeur des matieres premieres employees
    */
    private int buildingCost;
    /*
    b:bois,a:argile,p:pierre,o:or
    0:2b+1a 1:1b+2a 2:2b+1p 3:1b+1a+1p 4:2b+1o 5:1b+1a+1o 6:2a+1p 7:1b+2p 8:1b+1p+1o 9:1a+2p 10:2a+1o 11:1a+1p+1o
    12:2p+1o 13:5 ressources de 4 types 14:5 ressources de 3 types 15:5 ressources de 2 types 16:5 ressources de 1 types
    17:4 ressources de 4 types 18:4 ressources de 3 types 19:4 ressources de 2 types 20:4 ressources de 1 type 21:au moins une 
    matiere premiere et au plus 7
    */
    private ArrayList<BuildingTiles> cards;
    
    BuildingTiles(){
        addCards();
    }  
    
    BuildingTiles(int cardScore,int buildingCost){
        this.cardScore=cardScore;
        this.buildingCost=buildingCost;
    }
    
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
