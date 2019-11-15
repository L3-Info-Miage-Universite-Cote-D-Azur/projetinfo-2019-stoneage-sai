package stoneage;


public class Zone {
    private int nbOuvriersPlacés = 0;
    public int niveauZone ;
    private Dé dé;
    private int nbPlaceZone ;
    private int nbPlaceDispo;
    public Zone(int niveau) {
        this.niveauZone = niveau;
        dé=new Dé();
        switch(niveau){
            case 1:
                nbPlaceZone=1;
            case 2:
                nbPlaceZone=100;//le nombre de place dans la zone chasse est illimité
            case 3:case 4:case 5:case 6:
                nbPlaceZone=7;  
        }
        nbPlaceDispo=nbPlaceZone;//au début le nombre de place disponible = au nombre place max de la zone
    }
    public Zone(int niveau,Dé dé){
        this.niveauZone = niveau;
        this.dé=dé;
    }
    
    public int getNbPlaceZone(){
        return nbPlaceZone;
    }
    public int getNbPlaceDispo(){
        return nbPlaceDispo;
    }
    public void setNbPlaceDispo(int nbPlaceDispo)
    {
    	this.nbPlaceDispo=nbPlaceDispo;
    }
    public void placerOuvrier(Inventaire inventaireJoueur,int nbOuvriers){
    	if (nbOuvriers>=1 && nbOuvriers <=nbPlaceDispo && nbOuvriers<=5 ){
    		inventaireJoueur.removeAvailableWorkers(nbOuvriers);//pour placer un nbOuvrier il faut les retirer d'abord de l'inventaire du joueur  
    		nbOuvriersPlacés=nbOuvriers;   
            nbPlaceDispo=nbPlaceDispo-nbOuvriersPlacés;
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