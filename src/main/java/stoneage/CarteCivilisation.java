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
    private ArrayList<CarteCivilisation> allCards;
    public CarteCivilisation() {
    	addCardsToList();
    }
    //une carte civilisation est caracteriser par un fond de carte jaune 1/vert 0 et son numero qui va nous permettre de specifier son gain et la maniere de l'utilisier.
    public CarteCivilisation(int fondDeCarte, int numeroCarte) {
        this.fondDeCarte = fondDeCarte;
        this.numeroCarte = numeroCarte;
    }
    public  void addCardsToList(){
        //  FOND VERT
    	allCards= new ArrayList<>();
        allCards.add(new CarteCivilisation(0,0));
        allCards.add(new CarteCivilisation(0,1));
        allCards.add(new CarteCivilisation(0,2));
        allCards.add(new CarteCivilisation(0,3));
        allCards.add(new CarteCivilisation(0,4)); //Ex: vert, carte n°4-> Ecriture, pioche carteCiv.
        allCards.add(new CarteCivilisation(0,5));
        allCards.add(new CarteCivilisation(0,6));
        allCards.add(new CarteCivilisation(0,7));
        allCards.add(new CarteCivilisation(0,8));
        allCards.add(new CarteCivilisation(0,9));
        allCards.add(new CarteCivilisation(0,10));
        allCards.add(new CarteCivilisation(0,11));
        allCards.add(new CarteCivilisation(0,12));
        allCards.add(new CarteCivilisation(0,13));
        allCards.add(new CarteCivilisation(0,14));
        allCards.add(new CarteCivilisation(0,15));
        //  FOND JAUNE
        allCards.add(new CarteCivilisation(1,16)); //Ex: jaune, carte n°16-> hutte, nourriture, 1ouvrier.
        allCards.add(new CarteCivilisation(1,17));
        allCards.add(new CarteCivilisation(1,18));
        allCards.add(new CarteCivilisation(1,19));
        allCards.add(new CarteCivilisation(1,20));
        allCards.add(new CarteCivilisation(1,21));
        allCards.add(new CarteCivilisation(1,22));
        allCards.add(new CarteCivilisation(1,23));
        allCards.add(new CarteCivilisation(1,24));
        allCards.add(new CarteCivilisation(1,25));
        allCards.add(new CarteCivilisation(1,26));
        allCards.add(new CarteCivilisation(1,27));
        allCards.add(new CarteCivilisation(1,28));
        allCards.add(new CarteCivilisation(1,29));
        allCards.add(new CarteCivilisation(1,30));
        allCards.add(new CarteCivilisation(1,31));
        allCards.add(new CarteCivilisation(1,32));
        allCards.add(new CarteCivilisation(1,33));
        allCards.add(new CarteCivilisation(1,34));
        allCards.add(new CarteCivilisation(1,35));
    }
    public int getFondDeCarte() {
        return fondDeCarte;
    }
    public int getNumeroCarte() {
        return numeroCarte;
    }
    public  ArrayList<CarteCivilisation> getAllCards() {
        return allCards;
    }
    @Override
    public String toString(){
        return numeroCarte+"";
    }
}