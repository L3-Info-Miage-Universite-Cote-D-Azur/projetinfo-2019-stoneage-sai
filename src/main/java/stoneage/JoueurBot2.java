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


	public void recupeRes(Inventaire inventaireJoueur, Zone zoneChoisi) {
		
		int nbRessources = zoneChoisi.lancéDeDés(zoneChoisi.getNbOuvriersPlaces());
		
		if ((zoneChoisi.niveauZone-(nbRessources % zoneChoisi.niveauZone))<=inventaireJoueur.getNbOutils())
    	{
    		nbRessources+=(zoneChoisi.niveauZone-(nbRessources % zoneChoisi.niveauZone));
    		nbRessources=(nbRessources / zoneChoisi.niveauZone);
    		inventaireJoueur.setNbOutils(inventaireJoueur.getNbOutils()-(nbRessources % zoneChoisi.niveauZone));	
    	}
		
		switch(zoneChoisi.niveauZone)
    	{
    		case 1:
    			inventaireJoueur.setNbOutils(inventaireJoueur.getNbOutils()+1);

    			gains=1;
    			TypeGains="Outils";
    			break;
    		case 2:
    			inventaireJoueur.setNourriture(inventaireJoueur.getNourriture()+nbRessources);

    			gains=nbRessources;
    			TypeGains="Nourriture";
    			break;
    		case 3:
    			inventaireJoueur.setNbBois(inventaireJoueur.getNbBois()+nbRessources);
    			inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()+nbRessources);

    			gains=nbRessources;
    			TypeGains="Bois";
    	    	
    			break;
    		case 4: 
    			inventaireJoueur.setNbArgile(inventaireJoueur.getNbArgile()+nbRessources);
    			inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()+nbRessources);

    			gains=nbRessources;
    			TypeGains="Argile";
    	    	
    			break;
    		case 5:
    			inventaireJoueur.setNbPierre(inventaireJoueur.getNbPierre()+nbRessources);
    			inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()+nbRessources);

    			gains=nbRessources;
    			TypeGains="Pierre";
    	    	
    			break;

    		case 6:
    			inventaireJoueur.setNbOr(inventaireJoueur.getNbOr()+nbRessources);
    			inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()+nbRessources);

    			gains=nbRessources;
    			TypeGains="Or";
     			break;
    		default:
    			break;			
    	}
		 inventaireJoueur.addAvailableWorkers(zoneChoisi.getNbOuvriersPlaces());
	     zoneChoisi.resetNbOuvriersPlaces();
	     zoneChoisi.setNbPlaceDispo(zoneChoisi.getNbPlaceZone());
	}
	
/*  **************************************************************************************** 
 	* Place un ouvrier en fonction de ses ressources et de son nombre d'ouvrier dispo      *
 	* Si les conditions ne marchent pas pour placer ses ouvrier il les place aléatoirement *
    ****************************************************************************************/
	


	public Choix placerOuvriers(ArrayList<Zone> LesZones ,Inventaire inv){
		
		if ( (inv.getNbZoneJouer() < 6)){

			if (inv.listeZonesJouer.get(1)==false&&inv.getNourriture() >= 3  && inv.getNourriture() < 5)//Chasse
			{	//
				return new Choix(1, 1);
			}
			else if (inv.listeZonesJouer.get(1)==false&&inv.getNourriture() < 3) {
				return new Choix(1, 3);
			}
			else if (inv.listeZonesJouer.get(0)==false&& LesZones.get(0).getNbPlaceDispo()>1) { //Fabrication d'outils
					return new Choix(0, 1);
				}
			else if(inv.listeZonesJouer.get(5)==false && inv.getNbOr() < 4 &&  inv.getNbOuvrierDispo() == 5) {//Riviere
				return new Choix(5, 5);
			}
			else if(inv.listeZonesJouer.get(4)==false && inv.getNbPierre() < 4 &&  inv.getNbOuvrierDispo() >= 4) { //Carriere
				return new Choix(4, 4);
			}
			else if(inv.listeZonesJouer.get(3)==false && inv.getNbArgile() < 6 &&  inv.getNbOuvrierDispo() >= 3) { //Glaisiere
				return new Choix(3, 3);
			}
			else if(inv.listeZonesJouer.get(2)==false && inv.getNbBois() < 10 && inv.getNbOuvrierDispo() >= 2) {//Foret
				return new Choix(2, 2);
			}
			else {
				int zoneChoisie = (rand.nextInt(4) + 2);
				int nbOuvChoisie= rand.nextInt(Math.min(inv.getNbOuvrierDispo(),LesZones.get(zoneChoisie).getNbPlaceDispo()))+1;
				return new Choix (zoneChoisie, nbOuvChoisie);
			}
		}
		else {
			return null;
		}
	}

}