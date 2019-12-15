package stoneage;
import java.util.ArrayList;

/**
 * Cette classe représente le plateau de jeu.
 * Elle contient toutes les zones de jeu.
 * @see #lesZones
 * @see Zone
 */
public class Plateau {
    public ArrayList<Zone> lesZones = new ArrayList();

    /**
     * Méthode qui ajoute toutes les zones à une liste.
     * @return une liste de toutes les zones.
     */
    public ArrayList<Zone> addAllZones() {
        this.lesZones.add(new Zone(1, 1, "Fabrication d'Outils"));
        this.lesZones.add(new Zone(2, 100, "Chasse"));
        this.lesZones.add(new Zone(3, 7, "foret"));
        this.lesZones.add(new Zone(4, 7, "glaisière"));
        this.lesZones.add(new Zone(5, 7, "carrière"));
        this.lesZones.add(new Zone(6, 7, "rivière"));
        this.lesZones.add(new Zone(7, 1, "champ"));
        this.lesZones.add(new Zone(8, 1, "Civilisation 1"));
        this.lesZones.add(new Zone(9, 1, "Civilisation 2"));
        this.lesZones.add(new Zone(10, 1, "Civilisation 3"));
        this.lesZones.add(new Zone(11, 1, "Civilisation 4"));
        this.lesZones.add(new Zone(12, 1, "Batiment 1"));
        this.lesZones.add(new Zone(13, 1, "Batiment 2"));
        this.lesZones.add(new Zone(14, 1, "Batiment 3"));
        this.lesZones.add(new Zone(15, 1, "Batiment 4"));
        this.lesZones.add(new Zone(16, 2, "Hutte"));
        return this.lesZones;
    }
}
