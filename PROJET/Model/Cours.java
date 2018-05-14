package PROJET.Model;

import java.util.*;

public class Cours {
	
	private int heureDebut;
	private int heureFin;
	private Matiere laMatiere;
	private String typeCours;
	private Professeur leProfesseur;
	private Salle laSalle;
	private Classe laClasse;
	private Groupe leGroupe;
	private boolean occupe;
	
	public Cours() {}
	
	public Cours(int p_heureDebut, int p_heureFin) {
		this.heureDebut = p_heureDebut;
		this.heureFin = p_heureFin;
		this.typeCours = "";
		this.occupe = false;
	}

	public Cours(int p_heureDebut, int p_heureFin, Matiere p_laMatiere, String p_typeCours, Professeur p_leProfesseur, Salle p_laSalle, Classe p_laClasse, Groupe p_leGroupe) {
		this.heureDebut = p_heureDebut;
		this.heureFin = p_heureFin;
		this.laMatiere = p_laMatiere;
		this.typeCours = "";
		this.leProfesseur = p_leProfesseur;
		this.laSalle = p_laSalle;
		this.laClasse = p_laClasse;
		this.leGroupe = p_leGroupe;
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

	public String getTypeCours() {
		return this.typeCours;
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

	public Groupe getGroupe() {
		return this.leGroupe;
	}

	public boolean getOccupe() {
		return this.occupe;
	}

	public void setLaMatiere(Matiere p_laMatiere) {
		this.laMatiere = p_laMatiere;
	}

	public void setLeTypeCours(String p_TypeCours) {
		this.typeCours = p_TypeCours;
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

	public void setLeGroupe(Groupe p_leGroupe) {
		this.leGroupe = p_leGroupe;
	}

	public void setOccupe() {
		this.occupe = true;
	}
	
}