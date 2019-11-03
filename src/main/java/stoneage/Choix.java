package stoneage;

public class Choix {
	public Zone zoneChoisie;
	public int nbOuvriersChoisie;
	
	public Choix(Zone zoneJouer, int nbOuvriersJouer) { //constructeur qui fait :
		this.zoneChoisie = zoneJouer;               //l'affectation de la zoneChoisie 
		this.nbOuvriersChoisie = nbOuvriersJouer;   //l'affectation des nbOuvriersChoisie
	}
	
	
	 public boolean equals(Object o) {
	     if (o instanceof Choix) {
	    	 
	         Choix c = (Choix) o;
	            return (c.nbOuvriersChoisie == nbOuvriersChoisie) && (c.zoneChoisie == zoneChoisie);
	        }
	        else return false;
	    }
}
