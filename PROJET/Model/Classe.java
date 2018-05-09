package PROJET.Model;

import java.util.*;
import java.awt.Color;

public class Classe {
	
	private String nomClasse;
	private String niveauClasse;
	private ArrayList<Groupe> lesGroupes;
	private int nombreEleveClasse;
	private Color couleurClasse;
	private ArrayList<Matiere> lesMatieres;
	private Edt edtClasse;
	
	public Classe() {}
	
	public Classe(String p_nomClasse, String p_niveauClasse, Color p_couleurClasse) {
		this.nomClasse = p_nomClasse;
		this.niveauClasse = p_niveauClasse;
		this.lesGroupes = new ArrayList<Groupe>();
		this.couleurClasse = p_couleurClasse;
		this.lesMatieres = new ArrayList<Matiere>();
	}
	
	public Classe(String p_nomClasse, String p_niveauClasse, Color p_couleurClasse, Edt p_edtClasse) {
		this.nomClasse = p_nomClasse;
		this.niveauClasse = p_niveauClasse;
		this.lesGroupes = new ArrayList<Groupe>();
		this.couleurClasse = p_couleurClasse;
		this.lesMatieres = new ArrayList<Matiere>();
		this.edtClasse = p_edtClasse;
	}

	public String getNomClasse() {
		return this.nomClasse;
	}
	
	public String getNiveauClasse() {
		return this.niveauClasse;
	}

	public ArrayList<Groupe> getLesGroupesClasse() {
		return this.lesGroupes;
	}
	
	public int getNombreEleveClasse() {
		return this.nombreEleveClasse;
	}

	public Color getCouleurClasse() {
		return this.couleurClasse;
	}

	public Edt getEdtClasse() {
		return this.edtClasse;
	}

	public void getNombreEleveClasse(MEleve lesEleves, Classe laClasse) {
		int nombreEleve = 0;
		for(Eleve e : lesEleves.getLesEleves()) {
			if(e.getClasseEleve() == laClasse) { 
				nombreEleve++; 
			}
		}
		this.nombreEleveClasse = nombreEleve;
	}

	public void setLesMatieres(MMatiere lesMatieres) {
		System.out.println("Classe : " + this.niveauClasse);
		for(Matiere m : lesMatieres.getLesMatieres()) {
			if(m.getNiveauMatiere() == this.niveauClasse) {
				System.out.println("Matiere : " + m.getNomMatiere());
				this.lesMatieres.add(m);
			}
		}
	}
}