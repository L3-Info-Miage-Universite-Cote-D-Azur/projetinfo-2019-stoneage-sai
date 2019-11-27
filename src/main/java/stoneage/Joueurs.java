/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stoneage;

import java.util.ArrayList;

/**
 *
 * @author Ossama
 */
public interface Joueurs {
    public int cadeauRes(ArrayList<Integer> listeDe );
	public int choixTypeRes(int cout,Inventaire inv, int...typeDispo) ;
	public int placerOutils(int nbOutils,int nbRessources, Zone zoneChoisi) ;
	public Choix placerOuvriers(ArrayList<Zone> LesZones ,Inventaire inv);
	public int getNum();
        public boolean payerBatiment();
}
