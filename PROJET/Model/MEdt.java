package PROJET.Model;


import java.io.*;
import java.util.ArrayList;

public class MEdt {
	
	private ArrayList<Edt> lesEDT; 
	
	public MEdt() {
		this.lesEDT = new ArrayList<Edt>();
	}
	
	public ArrayList<Edt> getLesEDT(){
		return this.lesEDT;
	}

	public void ajoutEDT(Edt p_edt) {
		this.lesEDT.add(p_edt);
	}

	public void genererLesEDT(MClasse lesClasses, MEleve lesEleves) {
		for(Classe c : lesClasses.getLesClasses()) {
			
			/* Classe sans groupe */
			if(c.getLesGroupesClasse().size() == 0) {
				c.getNombreEleveClasse(lesEleves, c);
				for(Jour j : c.getEdtClasse().getLesJours()) {
					for(Cours co : j.getLesCours()) {
						if(co.getOccupe() == false) {
							c.trierLesMatieres();
						}	
					}
				}
			}
			
		}
	}

}
