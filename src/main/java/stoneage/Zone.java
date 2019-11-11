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
    public void placerOuvrier(Inventaire inventaireJoueur,int nbOuvriers){
    	if (nbOuvriers>=1 && nbOuvriers <=nbPlaceDispo){
    		inventaireJoueur.removeAvailableWorkers(nbOuvriers);//pour placer un nbOuvrier il faut les retirer d'abord de l'inventaire du joueur  
    		nbOuvriersPlacés=nbOuvriers;   
                nbPlaceDispo=nbPlaceZone-nbOuvriersPlacés;
    	}
    }

    public void resoudre(Inventaire inventaireJoueur){ 
    	int nbRessources= lancéDeDés(nbOuvriersPlacés);
    	//recuperer les ressources gagner
    	inventaireJoueur.setNbRessource(inventaireJoueur.getNbRessource()+nbRessources);
    	//ajouter les nouveau ressources a l'inventaire du joueur 
    	switch(niveauZone){
                case 1:
                        inventaireJoueur.setNbOutils(inventaireJoueur.getNbOutils()+1);
                case 2:
                        inventaireJoueur.setNourriture(inventaireJoueur.getNourriture()+nbRessources);
    		case 3:
    			inventaireJoueur.setNbBois(inventaireJoueur.getNbBois()+nbRessources);
    			break;
    		case 4: 
    			inventaireJoueur.setNbArgile(inventaireJoueur.getNbArgile()+nbRessources);
    			break;
    		case 5:
    			inventaireJoueur.setNbPierre(inventaireJoueur.getNbPierre()+nbRessources);
    			break;
    		
    		case 6:
    			inventaireJoueur.setNbOr(inventaireJoueur.getNbOr()+nbRessources);
    			break;
    		default:
    			break;			
    	}
        inventaireJoueur.addAvailableWorkers(nbOuvriersPlacés);
        //recuperer les ouvriers 
        nbOuvriersPlacés=0;
        nbPlaceDispo=nbPlaceZone;//quand on recupere les ouvriers,toutes les places deviennent disponibles.
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
    private int lancéDeDés(int nbOuvriersPlacés){  
        int sommeDés=0;                            
        for (int i = 0; i < nbOuvriersPlacés; i++) {
            sommeDés+=dé.Lancer();
        }
        return sommeDés / niveauZone;
    }
    @Override
    public String toString(){
        return NomZone();
    }
}