package PROJET.Model;

import java.util.*;
import java.util.ArrayList;

public class Jour {
	
	private String nomJour;
	private ArrayList<Cours> lesCours;

	public Jour() {}
	
	public Jour(String p_nomJour) {
		nomJour = p_nomJour;
		initialiserLesCours();
	}
	
	public String getNomJour() {
		return this.nomJour;
	}
	
	public void initialiserLesCours() {
		Cours cours8_10 = new Cours(8, 10);
		Cours cours10_12 = new Cours(10, 12);
		Cours cours14_16 = new Cours(14, 16);
		Cours cours16_18 = new Cours(16, 18);
	}
	
}