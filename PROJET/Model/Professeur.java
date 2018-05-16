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
		this.edtProfesseur = new Edt();
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

	public Edt getEDT() {
		return this.edtProfesseur;
	}

	public boolean estOccupe(Jour p_leJour, Cours p_leCours) {
		return this.edtProfesseur.getLeJour(p_leJour).getLeCours(p_leCours).getOccupe();
	}

	public void setNomProfesseur(String p_nomProfesseur) {
		this.nomProfesseur = p_nomProfesseur;
	}

	public void setPrenomProfesseur(String p_prenomProfesseur) {
		this.prenomProfesseur = p_prenomProfesseur;
	}

	public void setNombreHeuresProfesseur(int p_nombreHeuresProfesseur) {
		this.nombreHeuresProfesseur = p_nombreHeuresProfesseur;
	}
	
}