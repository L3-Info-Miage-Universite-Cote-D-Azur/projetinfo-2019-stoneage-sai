package stoneage;
import java.util.Random; //importer la Class Random du package util de java 


/**
 * Cette classe représente un dé allant de 1 à 6.
 */
public class Dé {
    private Random dice = new Random();

    /**
     * Simule le lancer d'un dé.
     * @return int : de 1 à 6 aléatoirement
     */
    public int Lancer() {
        return dice.nextInt(6)+1;
    }
}




