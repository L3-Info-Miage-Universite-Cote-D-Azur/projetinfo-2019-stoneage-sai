package stoneage;

import java.util.Random;

public class Zone {
    private int nbOuvriersPlacés = 0;
    public int niveauZone ;
    private Dé dé;
    private int nbPlaceZone ;
    private int nbPlaceDispo;
    public int gains; //le nombre des gains du joueur 
    public String TypeGains; //le nom du gain par exemple bois...
    Random rand = new Random();
    public Zone(int niveau) {
        this.niveauZone = niveau;
        dé=new Dé();
        if(niveau==1||niveau==7||niveau==8||niveau==9||niveau==10||niveau==11){
                nbPlaceZone=1;
        }
        else if (niveau== 2){
                nbPlaceZone=100;//le nombre de place dans la zone chasse est illimité
        }
        else if (niveau== 3||(niveau== 4)||(niveau== 5)||(niveau== 6)){

                nbPlaceZone=7;  
        }
        nbPlaceDispo=nbPlaceZone;//au début le nombre de place disponible = au nombre place max de la zone
    }
    public Zone(int niveau,Dé dé){
        this.niveauZone = niveau;
        this.dé=dé;
        //this.type=carte
    }
    public void placerOuvrier(Inventaire inventaireJoueur,int nbOuvriers){
    	if (nbOuvriers>=1 && nbOuvriers <=nbPlaceDispo && nbOuvriers<=inventaireJoueur.getNbOuvrierDispo() ){
    		inventaireJoueur.removeAvailableWorkers(nbOuvriers);//pour placer un nbOuvrier il faut les retirer d'abord de l'inventaire du joueur
    		nbOuvriersPlacés=nbOuvriers;
            nbPlaceDispo=nbPlaceDispo-nbOuvriers; //le nombre de place disponnible dans la zone diminue
             //le nombre d'ouvrier placer dans la zone augmente
    	}
    }

    public String NomZone(){
    	String[] nomZone={"Fabrication d'Outils","Chasse","foret","glaisière","carrière","rivière","champ"};//ajout des zones glaisière,carrière,rivière
    	String nom=nomZone[niveauZone - 1];
    	return nom;
    }


    /*on lance autant de Dés que des nbOuvriersPlacés
    et on retourne la somme des Dés jetés, divisé par niveauZone
    */
    public int lancéDeDés(int nbOuvriersPlacés){
        int sommeDés=0;
        for (int i = 0; i < nbOuvriersPlacés; i++) {
            sommeDés+=dé.Lancer();
        }
        return sommeDés ;
    }
    
    /* cette methode va nous permettre de distribuer
     *  les resources gagner a chaque joueur , ainsi son inventaire va etre modifier
     *   et la zone sera liberer quand il recupere ses ouvrier */
    
    public void recupeRes(Inventaire inventaireJoueur, Joueurs J) { 
    	int nbRessources= this.lancéDeDés(this.getNbOuvriersPlaces());
    	int nbOutilsDuJoueur=inventaireJoueur.getNbOutils();
    	int outilChoisie=J.placerOutils(nbOutilsDuJoueur,nbRessources,this);
		
    	nbRessources=nbRessources +outilChoisie;
    	nbRessources=nbRessources / this.niveauZone;
    	inventaireJoueur.setNbOutils(inventaireJoueur.getNbOutils()-outilChoisie);	   	   		
    	//recuperer les ressources gagner
    	//ajouter les nouveau ressources a l'inventaire du joueur 
    	switch(this.niveauZone)
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
    		case 7:
    			inventaireJoueur.setScoreChamp(inventaireJoueur.getScoreChamp()+1);
                inventaireJoueur.setNourriture(inventaireJoueur.getNourriture()+inventaireJoueur.getScoreChamp());
                break;
                
    		case 8:case 9: case 10: case 11:
    			if(this.niveauZone==8) {
    				inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()+nbRessources);
    			}
    			CarteCivilisation carte=CarteCivilisation.getAllCards().get(0);
    			if (carte.getFondDeCarte()==0) { //cartes verte
    				
    				inventaireJoueur.addNbCarteVert();
    				if (carte.getNumeroCarte()<2) {//potery
    					
    					inventaireJoueur.addTypeCarteCivVerte(1); //type de la carte verte 
    					if (carte.getNumeroCarte()==0) {
    						inventaireJoueur.setNourriture(inventaireJoueur.getNourriture()+7);
    		    			gains=7;
    		    			TypeGains="Nourriture";
    						//la carte 0 permet au joueur de gagner 7 nourriture 
    					}
    					else {
    						
    						
    					}
    				}
       				if (carte.getNumeroCarte()>1 && carte.getNumeroCarte()<4) {//art
    					inventaireJoueur.addTypeCarteCivVerte(2);
    				}
       				if (carte.getNumeroCarte()>3 && carte.getNumeroCarte()<6) {//ecriture
    					inventaireJoueur.addTypeCarteCivVerte(3);
    				}
       				if (carte.getNumeroCarte()>5 && carte.getNumeroCarte()<8) {//medecine
    					inventaireJoueur.addTypeCarteCivVerte(4);
    				}
       				if (carte.getNumeroCarte()>7 && carte.getNumeroCarte()<10) {//cadran
    					inventaireJoueur.addTypeCarteCivVerte(5);
    				}
       				if (carte.getNumeroCarte()>9 && carte.getNumeroCarte()<12) {//transport
    					inventaireJoueur.addTypeCarteCivVerte(6);
    				}
       				if (carte.getNumeroCarte()>11 && carte.getNumeroCarte()<14) {//musique
    					inventaireJoueur.addTypeCarteCivVerte(7);
    				}
       				if (carte.getNumeroCarte()>13 && carte.getNumeroCarte()<16) {//tissage
    					inventaireJoueur.addTypeCarteCivVerte(8);
    				}
    			}
    			else {//carte jaune
    				if (carte.getNumeroCarte()>15 && carte.getNumeroCarte()<21){//batiment
    					inventaireJoueur.addNbConstructeur(1); 
    				}
       				if (carte.getNumeroCarte()>20 && carte.getNumeroCarte()<26) {//agriculture
    					inventaireJoueur.addNbPaysan(1);
    				}
       				if (carte.getNumeroCarte()>25 && carte.getNumeroCarte()<31) {//outils
    					inventaireJoueur.addNbFabricant(1);
    				}
       				if (carte.getNumeroCarte()>30 && carte.getNumeroCarte()<36) {//figurine
    					inventaireJoueur.addNbChamane(1);
    				}
    				
    			}
   
    			// ajoute daans inventaire tous ce qui va etres modifier avec la carte ++ pour la fin et ++ pour le debut  
    			inventaireJoueur.setScoreChamp(inventaireJoueur.getScoreChamp()+1);
                inventaireJoueur.setNourriture(inventaireJoueur.getNourriture()+inventaireJoueur.getScoreChamp());
                break;
                        
    		default:
    			break;			
    	}
        inventaireJoueur.addAvailableWorkers(this.getNbOuvriersPlaces());
        //recuperer les ouvriers 
        this.resetNbOuvriersPlaces(); // 
        this.setNbPlaceDispo(this.getNbPlaceZone());//quand on recupere les ouvriers,toutes les places deviennent disponibles.    
    }

    
    
    
    public int getGains(){
	return gains;
    }
    public String TypeGains(){
	return  TypeGains;	
    }
	
    public boolean ouvrierPlace(int nbOuvriers){
        return (nbOuvriersPlacés == nbOuvriers);
    }//retourne une valeur booléenne pour vérifier si tous les ouvrier ont été placés ou non.
    
    public void resetNbOuvriersPlaces(){
    	nbOuvriersPlacés=0;
    }

    public int getNbPlaceZone(){
        return nbPlaceZone;
    }
    public int getNbPlaceDispo(){
        return nbPlaceDispo;
    }
    public int getNbOuvriersPlaces(){
        return nbOuvriersPlacés;
    }
    public void setNbPlaceDispo(int nbPlaceDispo) {
        this.nbPlaceDispo=nbPlaceDispo;
    }// le nombre de place disponible par zone

    @Override
    public String toString(){
        return NomZone();
    }
}