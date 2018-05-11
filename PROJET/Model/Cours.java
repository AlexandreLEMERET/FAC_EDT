package PROJET.Model;

import java.util.*;

public class Cours {
	
	private int heureDebut;
	private int heureFin;
	private Matiere laMatiere;
	private Professeur leProfesseur;
	private Salle laSalle;
	private Classe laClasse;
	private boolean occupe;
	
	public Cours() {}
	
	public Cours(int p_heureDebut, int p_heureFin) {
		this.heureDebut = p_heureDebut;
		this.heureFin = p_heureFin;
		this.occupe = false;
	}

	public Cours(int p_heureDebut, int p_heureFin, Matiere p_laMatiere, Professeur p_leProfesseur, Salle p_laSalle, Classe p_laClasse) {
		this.heureDebut = p_heureDebut;
		this.heureFin = p_heureFin;
		this.laMatiere = p_laMatiere;
		this.leProfesseur = p_leProfesseur;
		this.laSalle = p_laSalle;
		this.laClasse = p_laClasse;
		this.occupe = true;
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

	public Salle getSalle() {
		return this.laSalle;
	}

	public Classe getClasse() {
		return this.laClasse;
	}

	public boolean getOccupe() {
		return this.occupe;
	}

	public void setLaMatiere(Matiere p_laMatiere) {
		this.laMatiere = p_laMatiere;
	}

	public void setLeProfesseur(Professeur p_leProfesseur) {
		this.leProfesseur = p_leProfesseur;
	}

	public void setLaSalle(Salle p_laSalle) {
		this.laSalle = p_laSalle;
	}

	public void setLaClasse(Classe p_laClasse) {
		this.laClasse = p_laClasse;
	}

	public void setOccupe() {
		this.occupe = true;
	}
	
}