package stoneage;

public class Choix {
	public int zoneJouer;
	public int nbOuvriersJouer;
	
	public Choix(int zoneJouer, int nbOuvriersJouer) {
		this.zoneJouer = zoneJouer;
		this.nbOuvriersJouer = nbOuvriersJouer;
	}
	
	
	 public boolean equals(Object o) {
	     if (o instanceof Choix) {
	    	 
	         Choix c = (Choix) o;
	            return (c.nbOuvriersJouer == nbOuvriersJouer) && (c.zoneJouer == zoneJouer);
	        }
	        else return false;
	    }
}
