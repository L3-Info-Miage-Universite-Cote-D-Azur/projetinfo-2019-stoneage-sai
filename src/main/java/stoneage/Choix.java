package stoneage;

public class Choix {
	public Zone zoneChoisie;
	public int nbOuvriersChoisie;
	public int nbOutils;
	
	public Choix(Zone zoneJouer, int nbOuvriersJouer) { //constructeur qui fait :
		this.zoneChoisie = zoneJouer;               //l'affectation de la zoneChoisie 
		this.nbOuvriersChoisie = nbOuvriersJouer;   //l'affectation des nbOuvriersChoisie
		
	}
	public Choix(int nbOutils){
		this.nbOutils=nbOutils;		
	}
	 
	 public boolean equals(Object o) {
	     if (o instanceof Choix) {
	    	 
	         Choix c = (Choix) o;
	            return (c.nbOuvriersChoisie == nbOuvriersChoisie) && (c.zoneChoisie.niveauZone == zoneChoisie.niveauZone);
	        }
	        else return false;
	    }
        @Override
         public String toString(){
             return "nbOuvriersChoisie="+nbOuvriersChoisie+"  zoneChoisie="+zoneChoisie;
         }
}
