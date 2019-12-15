package stoneage;

/**
 * Cette classe représente le choix d'une zone.
 * @see int#zoneChoisie:
 * 		Entier qui correspond à la zone choisie
 * 		Valeur comprise entre 0 et 15
 * @see int#nbOuvriersChoisie
 * 		Nombre d'ouvriers choisis pour les placer sur une zone.
 */
public class Choix {
	public int zoneChoisie;
	public int nbOuvriersChoisie;

	/**
	 * Constructeur de la classe Choix
	 * @param zoneJouer:
	 *                    Zone choisie pour placer les ouvriers
	 * @param nbOuvriersJouer:
	 *                       Nombre d'ouvriers à placer.
	 *
	 */
	public Choix(int zoneJouer, int nbOuvriersJouer) {
		this.zoneChoisie = zoneJouer;
		this.nbOuvriersChoisie = nbOuvriersJouer;
		
	}
	public boolean equals(Object o) {
	     if (o instanceof Choix) {
	         Choix c = (Choix) o;
	            return (c.nbOuvriersChoisie == nbOuvriersChoisie) && (c.zoneChoisie == zoneChoisie);
	        }
	        else return false;
	}
	@Override
	public String toString(){
             return "nbOuvriersChoisis="+nbOuvriersChoisie+"  Indice de la zone Choisie="+(zoneChoisie+1);
         }
}