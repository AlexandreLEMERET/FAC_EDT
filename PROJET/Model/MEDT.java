package PROJET.Model;


import java.io.*;
import java.util.ArrayList;

public class MEDT {
	
	private ArrayList<EDT> lesEDT; 
	
	public MEDT() {
		this.lesEDT = new ArrayList<EDT>();
	}
	
	public ArrayList<EDT> getLesEDT(){
		return this.lesEDT;
	}

}
