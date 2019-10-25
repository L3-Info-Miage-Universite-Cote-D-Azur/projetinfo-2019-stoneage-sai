/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stoneage;

/**
 *
 * @author Ossama
 * @author Yasmine 
 */
public class Zone {
    private int nbOuvriersPlacés = 0;
    private int niveauZone = 3;
    private int constante;

    
    public Zone(int niveau) {
        this.niveauZone = niveau;
    }
    public Zone(int niveau,int constante){
        this.niveauZone = niveau;
        this.constante=constante;
    }
    public void placerOuvrier(int nbOuvriers){
        nbOuvriersPlacés=nbOuvriers;
    }
    public int resoudre(int constante){ 
        return constante/3;
    }
    public String NomZone(){
    	String[] nomZone={"Agriculture","Chasse","foret"};
    	String nom=nomZone[niveauZone - 1];
    	return nom;
    }
}