package PROJET.Model;

import java.util.*;

public class Professeur {
	
	private String nomProfesseur;
	private String prenomProfesseur;
	private Matiere matiereProfesseur;
	private int nombreHeuresProfesseur;
	
	public Professeur() {}
	
	public Professeur(String p_nomProfesseur, String p_prenomProfesseur, Matiere p_matiereProfesseur, int p_nombreHeuresProfesseur) {
		this.nomProfesseur = p_nomProfesseur;
		this.prenomProfesseur = p_prenomProfesseur;
		this.matiereProfesseur = p_matiereProfesseur;
		this.nombreHeuresProfesseur = p_nombreHeuresProfesseur;
	}
	
	public String getNomProfesseur() {
		return this.nomProfesseur;
	}
	
	public String getPrenomProfesseur() {
		return this.prenomProfesseur;
	}
	
	public Matiere getMatiereProfesseur() {
		return this.matiereProfesseur;
	}
	
	public int getNombreHeuresProfesseur() {
		return this.nombreHeuresProfesseur;
	}
	
}