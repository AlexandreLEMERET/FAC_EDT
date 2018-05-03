package PROJET.Model;

import java.util.*;
import java.util.ArrayList;

public class EDT {
	
	private ArrayList<Jour> lesJours;

	public EDT() {
		lesJours = new ArrayList<Jour>();
		this.initialiserLesJours();
	}
	
	public ArrayList<Jour> getLesJours() {
		return this.lesJours;
	}
	
	public void initialiserLesJours() {
		Jour jourLundi = new Jour("Lundi");
		Jour jourMardi = new Jour("Mardi");
		Jour jourMercredi = new Jour("Mercredi");
		Jour jourJeudi = new Jour("Jeudi");
		Jour jourVendredi = new Jour("Vendredi");
		this.lesJours.add(jourLundi);
		this.lesJours.add(jourMardi);
		this.lesJours.add(jourMercredi);
		this.lesJours.add(jourJeudi);
		this.lesJours.add(jourVendredi);
	}
	
}