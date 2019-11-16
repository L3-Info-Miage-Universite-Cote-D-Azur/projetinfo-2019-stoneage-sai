package stoneage;

import java.util.ArrayList;

public class Zone {
    private int nbOuvriersPlacés = 0;
    public int niveauZone ;
    private Dé dé;
    private int nbPlaceZone ;
    private int nbPlaceDispo;
    public ArrayList<Zone> listeZones = new ArrayList<>();
    public Zone(int niveau) {
        this.niveauZone = niveau;
        dé=new Dé();
        if(niveau==1){
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
    }
    
    /*******/
    public void ListeZones(){ // c'est la liste general des zone pour le jeu 
    	for(int i=1; i<7;i++){
    		Zone zone = new Zone(i);
    		listeZones.add(zone);
    	}
    }
    
    /******/
    
    public int getNbPlaceZone(){
        return nbPlaceZone;
    }
    public int getNbPlaceDispo(){
        return nbPlaceDispo;
    }
    public void setNbPlaceDispo(int nbPlaceDispo)
    {
    	this.nbPlaceDispo=nbPlaceDispo;
    }// le nombre de place disponnible par zone 
    
    public void placerOuvrier(Inventaire inventaireJoueur,int nbOuvriers){
    	if (nbOuvriers>=1 && nbOuvriers <=nbPlaceDispo && nbOuvriers<=inventaireJoueur.getNbOuvrierDispo() ){
    		inventaireJoueur.removeAvailableWorkers(nbOuvriers);//pour placer un nbOuvrier il faut les retirer d'abord de l'inventaire du joueur  
    		nbOuvriersPlacés=nbOuvriers;
            nbPlaceDispo=nbPlaceDispo-nbOuvriers; //le nombre de place disponnible dans la zone diminue
             //le nombre d'ouvrier placer dans la zone augmente
    	}
    }

    public int getNbOuvriersPlaces(){
    	return nbOuvriersPlacés;
    }
    public void resetNbOuvriersPlaces(){
    	nbOuvriersPlacés=0;
    }

    public String NomZone(){
    	String[] nomZone={"Fabrication d'Outils","Chasse","foret","glaisière","carrière","rivière"};//ajout des zones glaisière,carrière,rivière
    	String nom=nomZone[niveauZone - 1];
    	return nom;
    }
    
    //retourne une valeur booléenne pour vérifier si tous les ouvrier ont était placer ou non.
    public boolean ouvrierPlace(int nbOuvriers){ 
        return (nbOuvriersPlacés == nbOuvriers);
    }
    
    /*on lance autant de Dés que des nbOuvriersPlacés
    et on retourne la somme des Dés jetées divisé par niveauZone
    */
    public int lancéDeDés(int nbOuvriersPlacés){  
        int sommeDés=0;                            
        for (int i = 0; i < nbOuvriersPlacés; i++) {
            sommeDés+=dé.Lancer();
        }
        return sommeDés ;
    }
    @Override
    public String toString(){
        return NomZone();
    }
}