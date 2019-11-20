package stoneage;
import java.util.ArrayList;
import java.util.Random;

public class JoueurBot2 implements Joueurs {
	Random rand = new Random();
	public int gains;
	public String TypeGains;

	public int getGains(){
		return gains;
	}
	public String TypeGains(){
		return  TypeGains;
		
	}

	 public int placerOutils(int nbOutils,int nbRessources, Zone zoneChoisi) { 
	    int OutilChoisie ;	
		if ((zoneChoisi.niveauZone-(nbRessources % zoneChoisi.niveauZone))<=nbOutils)
    	{
			OutilChoisie =(zoneChoisi.niveauZone-(nbRessources % zoneChoisi.niveauZone));
    	}
    	else {
    	    OutilChoisie = rand.nextInt(nbOutils+1); 
    	}
    	return OutilChoisie;
    }	
		
	
/*  **************************************************************************************** 
 	* Place un ouvrier en fonction de ses ressources et de son nombre d'ouvrier dispo      *
 	* Si les conditions ne marchent pas pour placer ses ouvrier il les place aléatoirement *
    ****************************************************************************************/
	


	public Choix placerOuvriers(ArrayList<Zone> LesZones ,Inventaire inv){
		
		if ( (inv.getNbZoneJouer() < 6&&inv.ouvrierDispo())){

			if (inv.listeZonesJouer.get(1)==false&&inv.getNourriture() >= 3  && LesZones.get(1).getNbPlaceDispo()>1&& inv.getNourriture() < 5)//Chasse
			{	//SI la zone fabrication d'outil n'est pas etuliser 
				//si elle a plus que 1 place 
				//si le joueur a moins que 5 nourriture 
				return new Choix(1, 1);
				//on choisi cette zone et on pose 1 ouvrier la dedans 
			}
			else if (inv.listeZonesJouer.get(1)==false&&inv.getNourriture() < 3&& LesZones.get(1).getNbPlaceDispo()>3) {
				return new Choix(1, 3);
			}
			else {
				if (inv.listeZonesJouer.get(0)==false&& LesZones.get(0).getNbPlaceDispo()>=1) { //Fabrication d'outils
				
					return new Choix(0, 1);
				}
				else if(inv.listeZonesJouer.get(5)==false&& LesZones.get(5).getNbPlaceDispo()>=5 && inv.getNbOr() < 4 &&  inv.getNbOuvrierDispo() == 5) {//Riviere
					return new Choix(5, 5);
				}
				else if(inv.listeZonesJouer.get(4)==false && inv.getNbPierre() < 4 && LesZones.get(4).getNbPlaceDispo()>= 4&&  inv.getNbOuvrierDispo() >= 4) { //Carriere
					return new Choix(4, 4);
				}
				else if(inv.listeZonesJouer.get(3)==false && inv.getNbArgile() < 6 && LesZones.get(3).getNbPlaceDispo()>= 3&&  inv.getNbOuvrierDispo() >= 3) { //Glaisiere
					return new Choix(3, 3);
				}
				else if(inv.listeZonesJouer.get(2)==false && LesZones.get(2).getNbPlaceDispo()>= 2 && inv.getNbBois() < 10 && inv.getNbOuvrierDispo() >= 2) {//Foret
					return new Choix(2, 2);
				}
				else {
					int zoneChoisie = (rand.nextInt(4) + 2);
					int nbOuvChoisie= (Math.min(inv.getNbOuvrierDispo(),LesZones.get(zoneChoisie).getNbPlaceDispo()));
					return new Choix (zoneChoisie, nbOuvChoisie);
					
				}
			}
		}
		
		return null;
		
	}

}