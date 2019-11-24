package stoneage;
import java.util.ArrayList;
import java.util.Random;

public class JoueurBot2 implements Joueurs {
	Random rand = new Random();
	public int gains;
	public String TypeGains;
        String name;
	int num;
	JoueurBot2(String name,int num){
            this.name=name;
            this.num=num;
        }
        public int getNum(){
            return num;
        }
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
	public int cadeauRes(ArrayList<Integer> listeDe ){
		//cette methode va permettre au joueur de choisir la resouce cadeau de la carte civilisation
		return listeDe.get(rand.nextInt(listeDe.size()));
	}
	
	    public int choixTypeRes(int cout,Inventaire inv, int...typeDispo) { 
	        /* cette methode permet au joueure de choisir la resource qu'il va utiliser pour payer ses dettes */
	        	ArrayList<Integer> listTypeDispo = new ArrayList(); //transforme le tableau en liste
	        	for (int i=0 ; i<typeDispo.length; i++) {
	        		listTypeDispo.add(typeDispo[i]);
	        	}
	        	/* le joueur retourne :
	        	 * -1: s'il ne veut pas prendre la carte 
	        	 * 3: s'il choisi de la payer avec bois 
	        	 * 4:  Argile 
	        	 * 5: Pierre 
	        	 * 6: Or 
	        	 */
	        	if ( inv.getNbBois()<cout) {// s'il ya pas assez de bois on enleve l'indice de cette zone de la liste 
	        		listTypeDispo.remove(0);
	    		}
	    		else if ( inv.getNbArgile()<cout) {
	    			listTypeDispo.remove(1);
	    		}
	    		else if ( inv.getNbPierre()>cout) {
	    			listTypeDispo.remove(2);
	    		}
	    		else if (inv.getNbOr()>cout) {
	    			listTypeDispo.remove(3);
	    		}    	
	        	if(listTypeDispo.size()>0) {
	        		int i=rand.nextInt((listTypeDispo.size()));
	        		return listTypeDispo.get(i);
	        	}
	        	else {
	        		return (-1);
	        	}
	        } 
	 
	 public int choixTypeRes(int...typeDispo) {
		 int i=rand.nextInt((typeDispo.length));
		 return typeDispo[i];
	    	
	 }
	
/*  **************************************************************************************** 
 	* Place un ouvrier en fonction de ses ressources et de son nombre d'ouvrier dispo      *
 	* Si les conditions ne marchent pas pour placer ses ouvrier il les place aléatoirement *
    ****************************************************************************************/
		public Choix placerOuvriers(ArrayList<Zone> LesZones ,Inventaire inv){
			if ( (inv.getNbZoneJouer() < 6 && inv.ouvrierDispo())){

				if (inv.listeZonesJouer.get(1)==false && inv.getNourriture() >= 5 && inv.getNourriture() <10  && LesZones.get(1).getNbPlaceDispo()>2 )//Chasse
				{	//SI la zone chasse n'est pas etuliser 
					//si elle a plus que 2 place 
					//si le joueur 5 > nourriture > 3 
					return new Choix(1, 1);
					//on choisi cette zone et on pose 2 ouvrier la dedans 
				}
				else if (inv.listeZonesJouer.get(1)==false && inv.getNourriture() < 3 && LesZones.get(1).getNbPlaceDispo()>3) {
					// SI nourriture < 3
					return new Choix(1, 3);
				}					
				else if (inv.listeZonesJouer.get(2)==false&& LesZones.get(2).getNbPlaceDispo()>=2 && inv.getNbBois()<10 && inv.getNbOuvrierDispo() >= 2) { 
					return new Choix(2, 2);
				}
				else if(inv.listeZonesJouer.get(0)==false && LesZones.get(0).getNbPlaceDispo()>= 1 && inv.getNbOuvrierDispo() >= 1) {//Fabrication outils
						return new Choix(0, 1);
				}
				else if (StoneAge.getNbJoueurTotal()==2){
					if ( (inv.getNbZoneJouer() < 6&&inv.ouvrierDispo())){
						int i=0;
						int zoneChoisie = rand.nextInt(9);
					 	while ( inv.listeZonesJouer.get(zoneChoisie)==true || LesZones.get(zoneChoisie).getNbPlaceDispo()==0|| LesZones.get(zoneChoisie).nbJoueur>=1){				 	
					 		zoneChoisie = rand.nextInt(9);
					 		i++;
					 		if(i==10) {
					 			zoneChoisie = 1;
					 			break;
					 		}
						 }
					 	
					 	
						//IA simple qui choisit une zone au hazard
						int nbOuvChoisie= rand.nextInt(Math.min(inv.getNbOuvrierDispo(),LesZones.get(zoneChoisie).getNbPlaceDispo()))+1;
						//IA simple qui choisit nombre d'ouvrier qu'elle va poser sur cette zone au hazard 
						//le nombre doit etres inferieur au nombre de place disponnible de la zone et inferieur au nombre d'ouvrier dispo de la zone 
			            return new Choix(zoneChoisie, nbOuvChoisie);
						}
				     else{
				    	 return null;
				    }
				}
				else if(StoneAge.getNbJoueurTotal()==3) {
					if ( (inv.getNbZoneJouer() < 6 &&inv.ouvrierDispo())){
						int i=0;
					 	int zoneChoisie = rand.nextInt(10);
					 	while ( inv.listeZonesJouer.get(zoneChoisie)==true || LesZones.get(zoneChoisie).getNbPlaceDispo()==0|| LesZones.get(zoneChoisie).nbJoueur>=2){
					 		zoneChoisie = rand.nextInt(10);
					 		i++;
					 		if(i==10) {
					 			zoneChoisie = 1;
					 			break;
					 		}
					 	}
						//IA simple qui choisit une zone au hazard
						int nbOuvChoisie= rand.nextInt(Math.min(inv.getNbOuvrierDispo(),LesZones.get(zoneChoisie).getNbPlaceDispo()))+1;
						//IA simple qui choisit nombre d'ouvrier qu'elle va poser sur cette zone au hazard 
						//le nombre doit etres inferieur au nombre de place disponnible de la zone et inferieur au nombre d'ouvrier dispo de la zone 
			            return new Choix(zoneChoisie, nbOuvChoisie);
					}
					else{
						return null;
					}		
				}
				else if ( (inv.getNbZoneJouer() < 6 &&inv.ouvrierDispo())){

					 	int zoneChoisie = rand.nextInt(11);
					 	while ( inv.listeZonesJouer.get(zoneChoisie)==true || LesZones.get(zoneChoisie).getNbPlaceDispo()==0){
					 		zoneChoisie = rand.nextInt(11);

					 	}
						//IA simple qui choisit une zone au hazard
						int nbOuvChoisie= rand.nextInt(Math.min(inv.getNbOuvrierDispo(),LesZones.get(zoneChoisie).getNbPlaceDispo()))+1;
						//IA simple qui choisit nombre d'ouvrier qu'elle va poser sur cette zone au hazard 
						//le nombre doit etres inferieur au nombre de place disponnible de la zone et inferieur au nombre d'ouvrier dispo de la zone 
			            return new Choix(zoneChoisie, nbOuvChoisie);
					}
			     else{
			    	 return null;	    
			     }
			}
			return null;
	}
}
