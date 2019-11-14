package stoneage;
import java.util.ArrayList;

public class Zone {
    private int nbOuvriersPlacés = 0;
    public int niveauZone;
    private Dé dice;

    private static ArrayList<Zone> listeZone = new ArrayList<Zone>();
    private static ArrayList<Boolean> listeZoneDispo = new ArrayList<Boolean>();
    private static ArrayList<Integer> placeDispoParZone = new ArrayList<Integer>();

    private Zone zone1;
    private Zone zone2;
    private Zone zone3;
    private Zone zone4;
    private Zone zone5;
    private Zone zone6;

    public Zone(int niveau) {
        initializeZone();
        this.niveauZone = niveau;
        dice = new Dé();
    }
    public Zone(int niveau, Dé dice){
        initializeZone();
        this.niveauZone = niveau;
        this.dice=dice;
    }
    public void initializeZone(){
        listeZone.add(zone1);
        listeZone.add(zone2);
        listeZone.add(zone3);
        listeZone.add(zone4);
        listeZone.add(zone5);
        listeZone.add(zone6);

        for(int i=0; i< 6 ;i++) {
            listeZoneDispo.add(true); //true si disponible, false sinon
        }

        placeDispoParZone.add(1);
        placeDispoParZone.add(100);
        placeDispoParZone.add(7);
        placeDispoParZone.add(7);
        placeDispoParZone.add(7);
        placeDispoParZone.add(7);
    }
    public void placerOuvrier(Inventaire inventaireJoueur, int nbOuvriers){
    	if (nbOuvriers>=1 && nbOuvriers <= placeDispoParZone.get(niveauZone - 1) && nbOuvriers <=5 ){
    		inventaireJoueur.removeAvailableWorkers(nbOuvriers);//pour placer un nbOuvrier il faut les retirer d'abord de l'inventaire du joueur
            placeDispoParZone.set(niveauZone - 1, placeDispoParZone.get(niveauZone - 1) - nbOuvriers);
            nbOuvriersPlacés = nbOuvriers;
        }
    }

    public int getNbOuvriersPlaces(){
    	return nbOuvriersPlacés;
    }
    public void resetNbOuvriersPlaces(){
    	nbOuvriersPlacés=0;
    }
    public String NomZone(){
    	String[] nomZone={"Fabrication d'Outils","Chasse","foret","glaisière","carrière","rivière"};//ajout des zones glaisière,carrière,rivière
    	String nom=nomZone[niveauZone - 1];
    	return nom;
    }

    //retourne une valeur booléenne pour vérifier si tous les ouvriers ont été placés ou non.
    public boolean ouvrierPlace(int nbOuvriers){
        return (nbOuvriersPlacés == nbOuvriers);
    }

    /*on lance autant de Dés que des nbOuvriersPlacés
    et on retourne la somme des Dés jetées divisé par niveauZone
    */
    public int lancéDeDés(int nbOuvriersPlacés){
        int sommeDés=0;
        for (int i = 0; i < nbOuvriersPlacés; i++) {
            sommeDés+= dice.Lancer();
        }
        return sommeDés ;
    }

    public static ArrayList<Zone> getListeZone() {
        return listeZone;
    }

    public static ArrayList<Integer> getPlaceDispoParZone() {
        return placeDispoParZone;
    }

    public static ArrayList<Boolean> getListeZoneDispo() {
        return listeZoneDispo;
    }

    @Override
    public String toString(){
        return NomZone();
    }
}