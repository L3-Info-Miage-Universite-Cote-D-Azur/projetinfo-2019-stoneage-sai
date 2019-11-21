package stoneage;

import java.util.ArrayList;
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
    	String[] nomZone={"Fabrication d'Outils","Chasse","foret","glaisière","carrière","rivière","champ","Carte 1","Carte 2","Carte 3","Carte 4"};//ajout des zones glaisière,carrière,rivière
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
    
    public void recupeRes(ArrayList<CarteCivilisation> listeDesCartes,Inventaire inventaireJoueur, Joueurs J) { 
    	int nbRessources= this.lancéDeDés(this.getNbOuvriersPlaces());
    	int nbOutilsDuJoueur=inventaireJoueur.getNbOutils();
    	int outilChoisie=J.placerOutils(nbOutilsDuJoueur,nbRessources,this);
		
    	nbRessources=nbRessources +outilChoisie;
    	nbRessources=nbRessources / this.niveauZone;
    	inventaireJoueur.setNbOutils(inventaireJoueur.getNbOutils()-outilChoisie);	   	   		
    	//recuperer les ressources gagner
    	//ajouter les nouveau ressources a l'inventaire du joueur 
    	switch(this.niveauZone){
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
    			int coutCarte=this.niveauZone-7; // carte 1 vaut 1 / carte 2 vaut 2/ carte 3 vaut 3...
    			boolean payer=false;   			
        		CarteCivilisation carte=listeDesCartes.get(this.niveauZone-8); // si niveau de zone = 8 alors carte 1 sinon carte 2 sinon...
    			
    			/* Si le joueur paye la carte (il a assez de resource pour la payer et choisi de la prendre ) elle s'eneleve de a liste 
    			 *  sinon elle sera rendu a la liste  */ 
    			int typeCout= J.choixTypeRes(coutCarte,inventaireJoueur,3,4,5,6);
    			
    			if (typeCout==3 ) {
    				inventaireJoueur.setNbBois(inventaireJoueur.getNbBois()-coutCarte);
    				inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()-coutCarte);
    				payer=true;
    			}
    			else if (typeCout==4) {
    				inventaireJoueur.setNbArgile(inventaireJoueur.getNbArgile()-coutCarte);
    				inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()-coutCarte);
    				payer=true;
    			}
    			else if (typeCout==5) {
    				inventaireJoueur.setNbPierre(inventaireJoueur.getNbPierre()-coutCarte);
    				inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()-coutCarte);
    				payer=true;
    			}
    			else if (typeCout==6) {
    				inventaireJoueur.setNbOr(inventaireJoueur.getNbOr()-coutCarte);
    				inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()-coutCarte);
    				payer=true;
    			}

    		if (payer==true) {
    			if (carte.getFondDeCarte()==0) { 
    			//cartes vertes
    				inventaireJoueur.addNbCarteVert();
    				if (carte.getNumeroCarte()<2) {
    				//potery
    					inventaireJoueur.addTypeCarteCivVerte(1); //type de la carte verte 
    					if (carte.getNumeroCarte()==0) {
    					//carte potery 0
    						inventaireJoueur.setNourriture(inventaireJoueur.getNourriture()+7);
    		    			gains=7;
    		    			TypeGains="Nourriture";
    						//la carte 0 permet au joueur de gagner 7 nourriture 
    					}
    					else { 
    					// Carte poterie 1 
    						//lancement de 4 dé avec une methode dans joeur pour que les joueur choisi quelle ressource il veut 
    		    			gains=00;
    		    			TypeGains=" Rien (pas fini) ";
    					}
    				}
       				if (carte.getNumeroCarte()>1 && carte.getNumeroCarte()<4) {
       				//Art
    					inventaireJoueur.addTypeCarteCivVerte(2);
    					if (carte.getNumeroCarte()==2) {
    					// carte Art 2
    						inventaireJoueur.setNbOutils(inventaireJoueur.getNbOutils()+1);
    		    			gains=1;
    		    			TypeGains="Outil";
    					}
    					else {
    					// carte Art 3
    						//LANCEMENT 2 DEE OR
    						int gainsOr=this.lancéDeDés(2)/6;
    						inventaireJoueur.setNbOr(inventaireJoueur.getNbOr()+gainsOr);
    		    			gains=gainsOr;
    		    			TypeGains="Or";
    					}
       				}
       				if (carte.getNumeroCarte()>3 && carte.getNumeroCarte()<6) {
       				//Ecriture
    					inventaireJoueur.addTypeCarteCivVerte(3);
    					if (carte.getNumeroCarte()==4) {
        				// carte Ecriture 4 : c'est une carte civilisation qui sert que pour calcile Score final 
    						inventaireJoueur.addNbcarteCiv();
    		    			gains=1;
    		    			TypeGains=" Carte Civilisation";
    					}
    					else {
    					// carte Ecriture 5
    							
    						//lancement de 4 dé avec une methode dans joueur pour que les joueures choisient quelle ressouces il veut 
    					}
    				}
       				if (carte.getNumeroCarte()>5 && carte.getNumeroCarte()<8) {
       				//Medecine
    					inventaireJoueur.addTypeCarteCivVerte(4);
    					if (carte.getNumeroCarte()==6) {
    					//carte Medecine 6 
    						//permet au joueur de choisir 2 resource 
    						//methode choix dans jouer  
    		    			gains=00;
    		    			TypeGains=" Rien (pas fini) ";
    						
    					}
    					else {
    					//carte Medecine 7: ajoute 5 nourriture au joueur
    						inventaireJoueur.setNourriture(inventaireJoueur.getNourriture()+5);	
    		    			gains=5;
    		    			TypeGains="Nourriture";
    					}
    				}
       				if (carte.getNumeroCarte()>7 && carte.getNumeroCarte()<10) {
       				// carte cadran solaire
    					inventaireJoueur.addTypeCarteCivVerte(5);
    					if (carte.getNumeroCarte()==8) {
    					// carte cadran solaire 8:  gagner 1 score champ donc niveau champ +1
    						inventaireJoueur.setScoreChamp(inventaireJoueur.getScoreChamp()+1);	
    		    			gains=1;
    		    			TypeGains="Niveau Champ ";
    					}
    					else {
    					// carte cadan solaire 9: lancer 4 dés 
    						//chois de ressouces entre les 4 
    		    			gains=00;
    		    			TypeGains=" Rien (pas fini) ";
    					}
    				}
       				if (carte.getNumeroCarte()>9 && carte.getNumeroCarte()<12) {
       				//transport
    					inventaireJoueur.addTypeCarteCivVerte(6);
    					if (carte.getNumeroCarte()==10) {
    					// carte transport 10: 
    						//lancer 4 dé 
    						//chois de ressouces entre les 4 
    		    			gains=00;
    		    			TypeGains=" Rien (pas fini) ";
    					}
    					else {
    					// carte transport 11:  gagner 2 pierres
    						inventaireJoueur.setNbPierre(inventaireJoueur.getNbPierre()+2);	
    		    			gains=2;
    		    			TypeGains="Pierre ";
    					}
    				}
       				if (carte.getNumeroCarte()>11 && carte.getNumeroCarte()<14) {
       				//musique
    					inventaireJoueur.addTypeCarteCivVerte(7);
    					//carte musique 12 : gagner 3 pts dans le score 
    					//carte musique 13 : gagner 3 pts dans le score
    					inventaireJoueur.setScore(inventaireJoueur.getScore()+3);	
    				}
       				if (carte.getNumeroCarte()>13 && carte.getNumeroCarte()<16) {
       				//tissage
    					inventaireJoueur.addTypeCarteCivVerte(8);
    					if (carte.getNumeroCarte()==14) {
       					//carte Tissage 14 : gagner 3 nourriture 
    						inventaireJoueur.setNourriture(inventaireJoueur.getNourriture()+3);				
    					}
    					else {
        				//carte TISSAGE 15 : gagner 1 nourriture
    						inventaireJoueur.setNourriture(inventaireJoueur.getNourriture()+1);	
    					}
    				}
    			}
    			else {//Carte Jaune
    				if (carte.getNumeroCarte()>15 && carte.getNumeroCarte()<21){
    				// Cartes Constructeur
    					if (carte.getNumeroCarte()==16) {
           				//carte constructeur 16 : gagner 1 constructeur et  4 nourriture
        						inventaireJoueur.addNbConstructeur(1);
        						inventaireJoueur.setNourriture(inventaireJoueur.getNourriture()+4);				
        					}
    					if (carte.getNumeroCarte()==17) {
            			//carte constructeur 17 : gagner 1 constructeur  et  1 type de ressource
        						inventaireJoueur.addNbConstructeur(1);
        						//lancememnt de 4 dé et choix de ressource avec methode choix de joueur 
       						
        					}
    					if (carte.getNumeroCarte()==18) {
    					//carte constructeur 18 : gagner 2 constructeur et 2 nourriture
        					inventaireJoueur.addNbConstructeur(2);
    						inventaireJoueur.setNourriture(inventaireJoueur.getNourriture()+2);    
    		    			gains=2;
    		    			TypeGains=" Nourriture ";
    					}
    					if (carte.getNumeroCarte()==19) {
        				//carte constructeur 19 : gagner 2 constructeur et  1 type de ressource
        					inventaireJoueur.addNbConstructeur(2);
    						//lancememnt de 4 dé et choix de ressource avec methode choix de joueur 
    		    			gains=00;
    		    			TypeGains=" Rien (pas fini) ";

        					
    					}
    					if (carte.getNumeroCarte()==20) {
        				//carte constructeur 20 : gagner 3 constructeur et 3 pts dans score final
        					inventaireJoueur.addNbConstructeur(3);
        					inventaireJoueur.setScore(inventaireJoueur.getScore()+3);
    		    			gains=3;
    		    			TypeGains=" point dans le score ";

    					}
    				}
       				if (carte.getNumeroCarte()>20 && carte.getNumeroCarte()<26) {
       				//Paysan      				
    					if (carte.getNumeroCarte()==21) {
            			//carte Paysan 21 : gagner 1 Paysan et 1 pierre
    						inventaireJoueur.addNbPaysan(1);
        					inventaireJoueur.setNbPierre(inventaireJoueur.getNbPierre()+1);	
    		    			gains=1;
    		    			TypeGains=" Pierre ";
    					}
    					if (carte.getNumeroCarte()==22) {
            			//carte Paysan 22 : gagner 1 Paysan et 1 pts dans Niveau champ
    						inventaireJoueur.addNbPaysan(1);
        					inventaireJoueur.setScoreChamp(inventaireJoueur.getScoreChamp()+1);	
    		    			gains=1;
    		    			TypeGains=" Niveau Champ ";

    					}
    					if (carte.getNumeroCarte()==23) {
            			//carte Paysan 23 : gagner 1 Paysan et 1 TYPE DES resources parmie les 4 dé
    						inventaireJoueur.addNbPaysan(1);	
    						//lancer les 4 dé et choisir une ressource avec methode chois 
    		    			gains=00;
    		    			TypeGains=" Rien (pas fini) ";
    					}
    					if (carte.getNumeroCarte()==24) {
            			//carte Paysan 24 : gagner 2 Paysan et 1 TYPE DES resources parmie les 4 dé
    						inventaireJoueur.addNbPaysan(2);
    						//lancer les 4 dé et choisir une ressource avec methode chois 
    		    			gains=00;
    		    			TypeGains=" Rien (pas fini) ";
    					}
    					if (carte.getNumeroCarte()==25) {
            			//carte Paysan 25 : gagner 2 Paysan et 3 nourriture
    						inventaireJoueur.addNbPaysan(2);
        					inventaireJoueur.setNourriture(inventaireJoueur.getNourriture()+3);	
    		    			gains=3;
    		    			TypeGains=" Nourriture ";
    					}
    				}
       				if (carte.getNumeroCarte()>25 && carte.getNumeroCarte()<31) {
       				// FABRICANT D'outils
    					if (carte.getNumeroCarte()==26) {
                		//carte Fabricant 26 : gagner 1 Fabricant et 3 outil
        					inventaireJoueur.setNbOutils(inventaireJoueur.getNbOutils()+3);	
    						inventaireJoueur.addNbFabricant(1);
    		    			gains=3;
    		    			TypeGains=" Outils ";
    					}
    					if (carte.getNumeroCarte()==27) {
                    	//carte Fabricant 27 : gagner 1 Fabricant et 4 outil
        					inventaireJoueur.setNbOutils(inventaireJoueur.getNbOutils()+4);	
        					inventaireJoueur.addNbFabricant(1);  
    		    			gains=4;
    		    			TypeGains=" Outils ";
    					}
    					if (carte.getNumeroCarte()==28) {
                    	//carte Fabricant 28 : gagner 2 Fabricant et 2 OUTIL
        					inventaireJoueur.setNbOutils(inventaireJoueur.getNbOutils()+2);	
        					inventaireJoueur.addNbFabricant(2); 
    		    			gains=2;
    		    			TypeGains=" Outils ";
    					}
    					if (carte.getNumeroCarte()==29) {
                    	//carte Fabricant 29 : gagner 2 Fabricant et 1 type de ressouce au choix 
    						inventaireJoueur.addNbFabricant(2);	
    						//lancer les 4 dé et choisir une ressource avec methode chois 
    		    			gains=00;
    		    			TypeGains=" Rien (pas fini) ";
    					}
    					if (carte.getNumeroCarte()==30) {
                    	//carte Fabricant 30 : gagner 2 Fabricant et 1 type de ressource au choix 
    						//lancer les 4 dé et choisir une ressource avec methode chois 
    		    			gains=00;
    		    			TypeGains=" Rien (pas fini) ";
        					inventaireJoueur.addNbFabricant(2);	
    					}
    				}
       				//if (carte.getNumeroCarte()>30 && carte.getNumeroCarte()<36) {
       				else {
       				//Chamane
    					if (carte.getNumeroCarte()==31) {
                        //carte chamane 31 : gagner 1 Chamane  et 1 PIERRE
    						inventaireJoueur.addNbChamane(1);
        					inventaireJoueur.setNbPierre(inventaireJoueur.getNbPierre()+1);	
    		    			gains=1;
    		    			TypeGains=" Pierre ";
    					}
    					if (carte.getNumeroCarte()==32) {
                        //carte chamane 32 : gagner 1 Chamane  et 1 OR 
        					inventaireJoueur.addNbChamane(1);
            				inventaireJoueur.setNbOr(inventaireJoueur.getNbOr()+1);	
    		    			gains=1;
    		    			TypeGains=" Or ";
        					}
    					if (carte.getNumeroCarte()==33) {
                        //carte chamane 33 : gagner 1 Chamane  et lancer 2 dé pierre
    						int gain =this.lancéDeDés(2)/5 ;
        					inventaireJoueur.setNbPierre((gain)+inventaireJoueur.getNbPierre());	
        					inventaireJoueur.addNbChamane(1);
    		    			gains=gain;
    		    			TypeGains=" Pierre ";
        					}
    					if (carte.getNumeroCarte()==34) {
                        //carte chamane 34 : gagner 2 Chamane  et 1 argile
        					inventaireJoueur.addNbChamane(2);
            				inventaireJoueur.setNbArgile(inventaireJoueur.getNbArgile()+1);	
    		    			gains=2;
    		    			TypeGains=" Argile ";

        					}
    					else {//(carte.getNumeroCarte()==35) {
                        //carte chamane 35 : gagner 2 Chamane  et 1 lancer 2 dé bois
        					inventaireJoueur.addNbChamane(2);
        					int gain =this.lancéDeDés(2)/3;
        					inventaireJoueur.setNbBois((gain )+inventaireJoueur.getNbBois());	
    		    			gains=gain;
    		    			TypeGains=" Bois ";
        					}   					
    				}   				
    			}
    			listeDesCartes.remove(this.niveauZone-8);
    			TypeGains+=" avec la carte Civilisation. ";
                break;
    		}
    		else {
    			
    			System.out.println("Le joueur decide d'abandonner sa carte civilisation .");
    			break;
    		}                       
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