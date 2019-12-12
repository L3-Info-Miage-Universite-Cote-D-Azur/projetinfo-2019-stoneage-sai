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
    private ArrayList<Integer> res;
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

    public void recupResCarteBat(Inventaire inventaireJoueur,Zone zone,Joueurs J) {


        if (this.getCardScore() == 0) {   //une seule carte +10 points et coute 2b+1a
            if (inventaireJoueur.lesRessources.get(3).getValeur() >= 2 && inventaireJoueur.lesRessources.get(4).getValeur() >= 1 ) {
                inventaireJoueur.setScore(inventaireJoueur.getScore() + 10);
                inventaireJoueur.lesRessources.get(3).subvaleur(2);
                inventaireJoueur.lesRessources.get(4).subvaleur(1);
                zone.setGains( 10);
                zone.setTypeGains( " points sur la piste score avec la carte Batiment ");
            }
        } else if (this.getCardScore() == 1) { //cartes +11 points
            if (this.getBuildingCost() == 1) { //carte +11 points et coute 1b+2a
                if (inventaireJoueur.lesRessources.get(3).getValeur() >= 1 && inventaireJoueur.lesRessources.get(4).getValeur() >= 2 ) {
                    inventaireJoueur.setScore(inventaireJoueur.getScore() + 11);
                    inventaireJoueur.lesRessources.get(3).subvaleur(1);
                    inventaireJoueur.lesRessources.get(4).subvaleur(2);
                    zone.setGains( 11);
                    zone.setTypeGains( " points sur la piste score avec la carte Batiment ");
                }
            } else {
                if (inventaireJoueur.lesRessources.get(3).getValeur()>= 2 && inventaireJoueur.lesRessources.get(4).getValeur()>= 1 ) {//carte +11 points et coute 2b+1p
                    inventaireJoueur.setScore(inventaireJoueur.getScore() + 11);
                    inventaireJoueur.lesRessources.get(3).subvaleur(2);
                    inventaireJoueur.lesRessources.get(4).subvaleur(1);
                    zone.setGains( 11);
                    zone.setTypeGains( " points sur la piste score avec la carte Batiment ");
                }
            }
        } else if (this.getCardScore() == 2) { //cartes +12 points
            if (this.getBuildingCost() == 3) {  //cartes +12 points et coute 1b+1a+1p (existe en 2 exemplaires)
                if (inventaireJoueur.lesRessources.get(3).getValeur() >= 1 && inventaireJoueur.lesRessources.get(4).getValeur() >= 1 && inventaireJoueur.lesRessources.get(5).getValeur() >= 1 ) {
                    inventaireJoueur.setScore(inventaireJoueur.getScore() + 12);
                    inventaireJoueur.lesRessources.get(3).subvaleur(1);
                    inventaireJoueur.lesRessources.get(4).subvaleur(1);
                    inventaireJoueur.lesRessources.get(5).subvaleur(1);
                    zone.setGains( 12);
                    zone.setTypeGains( " points sur la piste score avec la carte Batiment ");
                }
            }
            if (this.getBuildingCost() == 4) { //carte +12 points et coute 2b+1o
                if (inventaireJoueur.lesRessources.get(3).getValeur() >= 2 && inventaireJoueur.lesRessources.get(6).getValeur() >= 1 ) {
                    inventaireJoueur.setScore(inventaireJoueur.getScore() + 12);
                    inventaireJoueur.lesRessources.get(3).subvaleur(2);
                    inventaireJoueur.lesRessources.get(6).subvaleur(1);
                    zone.setGains( 12);
                    zone.setTypeGains( " points sur la piste score avec la carte Batiment ");
                }
            }
        } else if (this.getCardScore() == 3) { //cartes +13 points
            if (this.getBuildingCost() == 5) { //cartes +13 points et coute 1b+1a+1o (existe en 2 exemplaires)
                if (inventaireJoueur.lesRessources.get(3).getValeur() >= 1 && inventaireJoueur.lesRessources.get(4).getValeur() >= 1 && inventaireJoueur.lesRessources.get(6).getValeur()>= 1) {
                    inventaireJoueur.setScore(inventaireJoueur.getScore() + 13);
                    inventaireJoueur.lesRessources.get(3).subvaleur(1);
                    inventaireJoueur.lesRessources.get(4).subvaleur(1);
                    inventaireJoueur.lesRessources.get(6).subvaleur(1);
                    zone.setGains( 13);
                    zone.setTypeGains( " points sur la piste score avec la carte Batiment ");
                }
            }
            if (this.getBuildingCost() == 6) { //carte +13 points et coute 2a+1p
                if (inventaireJoueur.lesRessources.get(4).getValeur() >= 2 && inventaireJoueur.lesRessources.get(5).getValeur()>= 1 ) {
                    inventaireJoueur.setScore(inventaireJoueur.getScore() + 13);
                    inventaireJoueur.lesRessources.get(5).subvaleur(1);
                    inventaireJoueur.lesRessources.get(4).subvaleur(2);
                    zone.setGains( 13);
                    zone.setTypeGains( " points sur la piste score avec la carte Batiment ");
                }
            }
            if (this.getBuildingCost() == 7) { //carte +13 points et coute 1b+2p
                if (inventaireJoueur.lesRessources.get(3).getValeur()>= 1 && inventaireJoueur.lesRessources.get(5).getValeur() >= 2 ) {
                    inventaireJoueur.setScore(inventaireJoueur.getScore() + 13);
                    inventaireJoueur.lesRessources.get(3).subvaleur(1);
                    inventaireJoueur.lesRessources.get(5).subvaleur(2);
                    zone.setGains( 13);
                    zone.setTypeGains( " points sur la piste score avec la carte Batiment ");
                }
            }
        } else if (this.getCardScore() == 4) { //cartes +14 points
            if (this.getBuildingCost() == 8) { //cartes +14 points et coute 1b+1p+1o (existe en 2 exemplaires)
                if (inventaireJoueur.lesRessources.get(3).getValeur()>= 1 && inventaireJoueur.lesRessources.get(5).getValeur()>= 1 &&inventaireJoueur.lesRessources.get(6).getValeur()>= 1 ) {
                    inventaireJoueur.setScore(inventaireJoueur.getScore() + 14);
                    inventaireJoueur.lesRessources.get(3).subvaleur(1);
                    inventaireJoueur.lesRessources.get(5).subvaleur(1);
                    inventaireJoueur.lesRessources.get(6).subvaleur(1);
                    zone.setTypeGains( " points sur la piste score avec la carte Batiment ");
                    zone.setGains( 14);
                }
            }
            if (this.getBuildingCost() == 9) { //carte +14 points et coute 1a+2p
                if (inventaireJoueur.lesRessources.get(4).getValeur() >= 1 && inventaireJoueur.lesRessources.get(5).getValeur()>= 2) {
                    inventaireJoueur.setScore(inventaireJoueur.getScore() + 14);
                    inventaireJoueur.lesRessources.get(4).subvaleur(1);
                    inventaireJoueur.lesRessources.get(5).subvaleur(2);
                    zone.setTypeGains( " points sur la piste score avec la carte Batiment ");
                    zone.setGains( 14);
                }
            }
            if (this.getBuildingCost() == 10) { //carte +14 points et coute 2a+1o
                if (inventaireJoueur.lesRessources.get(4).getValeur()>= 2 && inventaireJoueur.lesRessources.get(6).getValeur()>= 1) {
                    inventaireJoueur.setScore(inventaireJoueur.getScore() + 14);
                    inventaireJoueur.lesRessources.get(4).subvaleur(2);
                    inventaireJoueur.lesRessources.get(6).subvaleur(1);
                    zone.setTypeGains( " points sur la piste score avec la carte Batiment ");
                    zone.setGains( 14);
                }
            }
        } else if (this.getCardScore() == 5) { //cartes +15 points
            if (this.getBuildingCost() == 11) { //cartes +15 points et coute 1a+1p+1o (existe en 2 exemplaires)
                if (inventaireJoueur.lesRessources.get(4).getValeur() >= 1 && inventaireJoueur.lesRessources.get(5).getValeur() >= 1 && inventaireJoueur.lesRessources.get(6).getValeur() >= 1 ) {
                    inventaireJoueur.setScore(inventaireJoueur.getScore() + 15);
                    inventaireJoueur.lesRessources.get(4).subvaleur(1);
                    inventaireJoueur.lesRessources.get(5).subvaleur(1);
                    inventaireJoueur.lesRessources.get(6).subvaleur(1);
                    zone.setTypeGains( " points sur la piste score avec la carte Batiment ");
                    zone.setGains( 15);
                }
            }
        } else if (this.getCardScore() == 6) { //une seule carte +16 points et coute 2p+1o
            if (inventaireJoueur.lesRessources.get(5).getValeur()>= 2 && inventaireJoueur.lesRessources.get(6).getValeur()>= 1 ) {
                inventaireJoueur.setScore(inventaireJoueur.getScore() + 16);
                inventaireJoueur.lesRessources.get(5).subvaleur(2);
                inventaireJoueur.lesRessources.get(6).subvaleur(1);
                zone.setTypeGains( " points sur la piste score avec la carte Batiment ");
                zone.setGains( 16);
            }
        } else if (this.getCardScore() == 7) {//cartes dont les points gagnes dependent de la valeur des matieres premieres employees(cartes avec ? en haut a gauche)
            if (this.getBuildingCost() == 13) {//carte +? points et coute 5 ressources de 4 types
                res = J.payBuildingWith(inventaireJoueur, 5, 4);
                if (res.size() == 4) {
                    J.resolution(inventaireJoueur, res, 5);
                    zone.setGains( res.get(0) * 6 + res.get(1) * 5 + res.get(2) * 4 + res.get(3) * 3);
                }
            }
            if (this.getBuildingCost() == 15) {//5 ressources de 2 types
                res = J.payBuildingWith(inventaireJoueur, 5, 2);
                if (res.size() == 4) {
                    J.resolution(inventaireJoueur, res, 5);
                    zone.setGains( res.get(0) * 6 + res.get(1) * 5 + res.get(2) * 4 + res.get(3) * 3);
                }
            }
            if (this.getBuildingCost() == 16) {//5 ressources de 1 type
                if (inventaireJoueur.lesRessources.get(6).getValeur() >= 5) {
                    inventaireJoueur.setScore(inventaireJoueur.getScore() + 5 * 6);
                    inventaireJoueur.lesRessources.get(6).subvaleur(5);
                    zone.setGains( 35);
                    zone.setTypeGains( " points sur la piste score avec la carte Batiment ");
                } else if (inventaireJoueur.lesRessources.get(5).getValeur() >= 5) {
                    inventaireJoueur.setScore(inventaireJoueur.getScore() + 5 * 5);
                    inventaireJoueur.lesRessources.get(5).subvaleur(5);
                    zone.setGains( 25);
                    zone.setTypeGains( " points sur la piste score avec la carte Batiment ");
                } else if (inventaireJoueur.lesRessources.get(4).getValeur() >= 5) {
                    inventaireJoueur.setScore(inventaireJoueur.getScore() + 5 * 4);
                    inventaireJoueur.lesRessources.get(4).subvaleur(5);
                    zone.setGains( 20);
                    zone.setTypeGains( " points sur la piste score avec la carte Batiment ");
                } else if (inventaireJoueur.lesRessources.get(3).getValeur() >= 5) {
                    inventaireJoueur.setScore(inventaireJoueur.getScore() + 5 * 3);
                    inventaireJoueur.lesRessources.get(3).subvaleur(5);
                    zone.setGains( 15);
                    zone.setTypeGains( " points sur la piste score avec la carte Batiment ");
                }
            }
            if (this.getBuildingCost() == 17) {//carte +? points et coute 4 ressources de 4 types
                res = J.payBuildingWith(inventaireJoueur, 4, 4);
                if (res.size() == 4) {
                    J.resolution(inventaireJoueur, res, 4);
                    zone.setGains( res.get(0) * 6 + res.get(1) * 5 + res.get(2) * 4 + res.get(3) * 3);
                }
            }
            if (this.getBuildingCost() == 19) {//carte +? points et coute 4 ressources de 2 types
                res = J.payBuildingWith(inventaireJoueur, 4, 2);
                if (res.size() == 4) {
                    J.resolution(inventaireJoueur, res, 4);
                    zone.setGains( res.get(0) * 6 + res.get(1) * 5 + res.get(2) * 4 + res.get(3) * 3);
                }
            }
            if (this.getBuildingCost() == 20) {//carte +? points et coute 4 ressources de 1 types
                if (inventaireJoueur.lesRessources.get(6).getValeur() >= 4) {
                    inventaireJoueur.setScore(inventaireJoueur.getScore() + 4 * 6);
                    inventaireJoueur.lesRessources.get(6).subvaleur(4);
                    zone.setGains( 24);
                    zone.setTypeGains( " points sur la piste score avec la carte Batiment ");
                } else if (inventaireJoueur.lesRessources.get(5).getValeur() >= 4) {
                    inventaireJoueur.setScore(inventaireJoueur.getScore() + 4 * 5);
                    inventaireJoueur.lesRessources.get(5).subvaleur(4);
                    zone.setGains( 20);
                    zone.setTypeGains( " points sur la piste score avec la carte Batiment ");
                } else if (inventaireJoueur.lesRessources.get(4).getValeur() >= 4) {
                    inventaireJoueur.setScore(inventaireJoueur.getScore() + 4 * 4);
                    inventaireJoueur.lesRessources.get(4).subvaleur(4);
                    zone.setGains( 16);
                    zone.setTypeGains( " points sur la piste score avec la carte Batiment ");
                } else if (inventaireJoueur.lesRessources.get(3).getValeur() >= 4) {
                    inventaireJoueur.setScore(inventaireJoueur.getScore() + 4 * 3);
                    inventaireJoueur.lesRessources.get(3).subvaleur(4);
                    zone.setGains( 12);
                    zone.setTypeGains( " points sur la piste score avec la carte Batiment ");
                }
            }
            if (this.getBuildingCost() == 21) {//carte +? points et coute au moins une matiere premiere et au plus 7(existe en 3 exemplaires)
                res = J.payBuilding17(inventaireJoueur);
                int sum = 0;
                for (int a : res) {
                    sum += a;
                }
                J.resolution(inventaireJoueur, res, sum);
                zone.setGains( res.get(0) * 6 + res.get(1) * 5 + res.get(2) * 4 + res.get(3) * 3);
                zone.setTypeGains( " points sur la piste score avec la carte Batiment ");
            }

        }
    }
}
