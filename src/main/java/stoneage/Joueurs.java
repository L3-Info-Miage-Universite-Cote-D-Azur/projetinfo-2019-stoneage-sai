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
	public int getGains();
	public String TypeGains();
    public void recupeRes(Inventaire inventaireJoueur, Zone zoneChoisi);
    public Choix placerOuvriers(ArrayList<Zone> LesZones ,Inventaire inv);
}
