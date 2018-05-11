package PROJET.Model;

import java.util.*;

public class Professeur {
	
	private String nomProfesseur;
	private String prenomProfesseur;
	private int nombreHeuresProfesseur;
	private Edt edtProfesseur;
	
	public Professeur() {}
	
	public Professeur(String p_nomProfesseur, String p_prenomProfesseur, int p_nombreHeuresProfesseur) {
		this.nomProfesseur = p_nomProfesseur;
		this.prenomProfesseur = p_prenomProfesseur;
		this.nombreHeuresProfesseur = p_nombreHeuresProfesseur;
	}

	public Professeur(String p_nomProfesseur, String p_prenomProfesseur, int p_nombreHeuresProfesseur, Edt p_edtProfesseur) {
		this.nomProfesseur = p_nomProfesseur;
		this.prenomProfesseur = p_prenomProfesseur;
		this.nombreHeuresProfesseur = p_nombreHeuresProfesseur;
		this.edtProfesseur = p_edtProfesseur;
	}
	
	public String getNomProfesseur() {
		return this.nomProfesseur;
	}
	
	public String getPrenomProfesseur() {
		return this.prenomProfesseur;
	}
	
	public int getNombreHeuresProfesseur() {
		return this.nombreHeuresProfesseur;
	}

	public boolean estLibre(Jour p_leJour, Cours p_leCours) {
		return this.edtProfesseur.getLeJour(p_leJour).getLeCours(p_leCours).getOccupe();
	}
	
}