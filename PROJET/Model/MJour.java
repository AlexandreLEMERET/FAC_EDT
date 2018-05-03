package PROJET.Model;


import java.io.*;
import java.util.ArrayList;

public class MJour {
	
	private ArrayList<Jour> lesJours; 
	
	public MJour() {
		this.lesJours = new ArrayList<Jour>();
	}
	
	public ArrayList<Jour> getLesJours(){
		return this.lesJours;
	}

}
