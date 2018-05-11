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

	public ArrayList<Matiere> getLesMatieres() {
		return this.lesMatieres;
	}

	public Edt getEDT() {
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
		for(Matiere m : lesMatieres.getLesMatieres()) {
			if(m.getNiveauMatiere() == this.niveauClasse) {
				this.lesMatieres.add(m);
			}
		}
	}

	public void trierLesMatieres() {
		int i = 0;
		ArrayList<Matiere> tmpMatiere = new ArrayList<Matiere>();
		if(this.lesMatieres.size() > 0) {
			tmpMatiere.add(lesMatieres.get(0));
			this.lesMatieres.remove(0);
			if(this.lesMatieres.size() > 0) {
				for(Matiere m : lesMatieres) {
					while(i < tmpMatiere.size() && tmpMatiere.get(i).getMaxHeureRestante() > m.getMaxHeureRestante()) {
						i++;
					}
					if(i == tmpMatiere.size()) { System.out.println("On passe ici"); tmpMatiere.add(m); }
					else { tmpMatiere.add(i, m);}
					i = 0;
				}
			}
		}

		this.lesMatieres = tmpMatiere;
	}
}