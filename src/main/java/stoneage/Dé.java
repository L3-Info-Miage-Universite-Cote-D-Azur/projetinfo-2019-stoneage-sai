package stoneage;
import java.util.Random; //importer la Class Random du package util de java 

public class Dé {
    private Random dé = new Random();
    public int Lancer() { //cette methode retourne un entier entre 1 et 6.
        return dé.nextInt(6)+1; //utiliser la methode nextInt(n) qui donne un nombre au hazare ente 0 et n exclut
    }
}



