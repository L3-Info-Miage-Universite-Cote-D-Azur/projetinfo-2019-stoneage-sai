/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stoneage;

import java.util.ArrayList;
import java.util.Map;

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
    public Map NourrirOuv(Inventaire inv ,  int nm);
    public boolean payerBatiment();
    public ArrayList<Integer> payBuildingWith(Inventaire inv,int cout,int types);
    public void resolution(Inventaire inv,ArrayList<Integer> res,int cout);
}
