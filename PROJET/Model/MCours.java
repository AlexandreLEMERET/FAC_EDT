package PROJET.Model;


import java.io.*;
import java.util.ArrayList;

public class MCours {
	
	private ArrayList<Cours> lesCours; 
	
	public MCours() {
		this.lesCours = new ArrayList<Cours>();
	}
	
	public ArrayList<Cours> getLesCours(){
		return this.lesCours;
	}

}
