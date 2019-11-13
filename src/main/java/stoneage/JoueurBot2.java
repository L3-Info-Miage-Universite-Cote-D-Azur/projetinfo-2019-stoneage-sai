package stoneage;
import java.util.ArrayList;
import java.util.Random;

public class JoueurBot2 implements Joueurs {
	Random rand = new Random();
	ArrayList<Integer> zonesDispo = new ArrayList<>();
	@Override
	public void recupeRes(Inventaire inventaireJoueur, Zone zoneChoisi) {
		
		int nbRessources = zoneChoisi.lancéDeDés(zoneChoisi.getNbOuvriersPlaces());
		
		switch(zoneChoisi.niveauZone)
    	{
    		case 1:
    			inventaireJoueur.setNbOutils(inventaireJoueur.getNbOutils()+1);
    			break;
    		case 2:
    			inventaireJoueur.setNourriture(inventaireJoueur.getNourriture()+nbRessources);
    			break;
    		case 3:
    			inventaireJoueur.setNbBois(inventaireJoueur.getNbBois()+nbRessources);
    			inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()+nbRessources);
    			break;
    		case 4: 
    			inventaireJoueur.setNbArgile(inventaireJoueur.getNbArgile()+nbRessources);
    			inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()+nbRessources);
    	    	
    			break;
    		case 5:
    			inventaireJoueur.setNbPierre(inventaireJoueur.getNbPierre()+nbRessources);
    			inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()+nbRessources);
    	    	
    			break;
    		
    		case 6:
    			inventaireJoueur.setNbOr(inventaireJoueur.getNbOr()+nbRessources);
    			inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()+nbRessources);
    			break;
    		default:
    			break;			
    	}
	}
	
/*  **************************************************************************************** 
 	* Place un ouvrier en fonction de ses ressources et de son nombre d'ouvrier dispo      *
 	* Si les conditions ne marchent pas pour placer ses ouvrier il les place aléatoirement *
    ****************************************************************************************/
	
	@Override
	public Choix placerOuvriers(Inventaire inv) {
		for (int i=0; i< inv.listeZonesDispo.size();i++ ){
			zonesDispo.add((inv.listeZonesDispo.get(i)).niveauZone);
		}
		
		if ((inv.listeZonesDispo != null) && (inv.listeZonesDispo.size() > 0))
		{
			if (inv.getNourriture() >= 3  && inv.getNourriture() < 5)
			{	
				return new Choix(inv.listeZonesDispo.get(1), 1);
			}
			else if (inv.getNourriture() < 3) {
				return new Choix(inv.listeZonesDispo.get(1), 3);
			}
			else {
				if (inv.listeZonesDispo.contains(0)) {
					return new Choix(inv.listeZonesDispo.get(0), 1);
				}
				else if(inv.listeZonesDispo.contains(2) && inv.getNbBois() < 10 && inv.getNbOuvrierDispo() >= 2) {
					return new Choix(inv.listeZonesDispo.get(2), 2);
				}
				else if(inv.listeZonesDispo.contains(3) && inv.getNbArgile() < 6 &&  inv.getNbOuvrierDispo() >= 3) {
					return new Choix(inv.listeZonesDispo.get(3), 3);
				}
				else if(inv.listeZonesDispo.contains(4) && inv.getNbPierre() < 4 &&  inv.getNbOuvrierDispo() >= 4) {
					return new Choix(inv.listeZonesDispo.get(4), 4);
				}
				else if(inv.listeZonesDispo.contains(5) && inv.getNbOr() < 2 &&  inv.getNbOuvrierDispo() == 5) {
					return new Choix(inv.listeZonesDispo.get(5), 5);
				}
				else if(inv.listeZonesDispo.contains(2) && inv.listeZonesDispo.contains(3) && inv.listeZonesDispo.contains(4) && inv.listeZonesDispo.contains(5) && inv.ouvrierDispo()){
					return new Choix (inv.listeZonesDispo.get(rand.nextInt(4) + 2), inv.getNbOuvrierDispo());
				}
			}
		}
		return null;
	}

}
