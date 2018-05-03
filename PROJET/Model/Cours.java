package PROJET.Model;

import java.util.*;

public class Cours {
	
	private int heureDebut;
	private int heureFin;
	private Matiere laMatiere;
	private Professeur leProfesseur;
	
	public Cours() {}
	
	public Cours(int p_heureDebut, int p_heureFin) {
		heureDebut = p_heureDebut;
		heureFin = p_heureFin;
	}

	public Cours(int p_heureDebut, int p_heureFin, Matiere p_laMatiere, Professeur p_leProfesseur) {
		heureDebut = p_heureDebut;
		heureFin = p_heureFin;
		laMatiere = p_laMatiere;
		leProfesseur = p_leProfesseur;
	}
	
	public int getHeureDebut() {
		return this.heureDebut;
	}

	public int getHeureFin() {
		return this.heureFin;
	}

	public Matiere getMatiere() {
		return this.laMatiere;
	}

	public Professeur getProfesseur() {
		return this.leProfesseur;
	}

	public void setLaMatiere(Matiere p_laMatiere) {
		this.laMatiere = p_laMatiere;
	}

	public void setLeProfesseur(Professeur p_leProfesseur) {
		this.leProfesseur = p_leProfesseur;
	}
	
}