package stoneage;

public class Choix {
	private Zone getZoneChoisie;
	private int nbOuvriersChoisis;
	private int nbOutils;

	public Choix(Zone zoneJouer, int nbOuvriersJouer) { //constructeur qui fait :
		this.getZoneChoisie = zoneJouer;               //l'affectation de la zoneChoisie
		this.nbOuvriersChoisie = nbOuvriersJouer;   //l'affectation des nbOuvriersChoisie

	}
	 public boolean equals(Object o) {
	     if (o instanceof Choix) {

	         Choix c = (Choix) o;
	            return (c.nbOuvriersChoisie == nbOuvriersChoisie) && (c.getZoneChoisie.niveauZone == getZoneChoisie.niveauZone);
	        }
	        else return false;
	    }

	@Override
	public String toString(){
             return "nbOuvriersChoisie="+nbOuvriersChoisie+"  zoneChoisie="+ getZoneChoisie;
	}


	public Zone getZoneChoisie() {
		return getZoneChoisie;
	}

	public int getNbOuvriersChoisis() {
		return nbOuvriersChoisis;
	}

	public int getNbOutils() {
		return nbOutils;
	}
}
