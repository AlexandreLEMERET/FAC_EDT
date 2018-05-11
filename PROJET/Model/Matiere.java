package PROJET.Model;

import java.util.*;
import java.lang.Math;
import java.awt.Color;

public class Matiere {
	
	private String nomMatiere;
	private int nombreHeureCM;
	private int nombreHeureTP;
	private int nombreHeureTD;
	private int nombreHeureCMrestante;
	private int nombreHeureTPrestante;
	private int nombreHeureTDrestante;
	private String niveauMatiere;
	private Professeur professeurMatiere;
	private Color couleurMatiere;
	
	public Matiere() {}
	
	public Matiere(String p_nomMatiere, int p_nombreHeureCM, int p_nombreHeureTP, int p_nombreHeureTD, String p_niveauMatiere, Professeur p_professeurMatiere, Color p_couleurMatiere) {
		this.nomMatiere = p_nomMatiere;
		this.nombreHeureCM = p_nombreHeureCM;
		this.nombreHeureTP = p_nombreHeureTP;
		this.nombreHeureTD = p_nombreHeureTD;
		this.nombreHeureCMrestante = p_nombreHeureCM;
		this.nombreHeureTPrestante = p_nombreHeureTP;
		this.nombreHeureTDrestante = p_nombreHeureTD;
		this.niveauMatiere = p_niveauMatiere;
		this.professeurMatiere = p_professeurMatiere;
		this.couleurMatiere = p_couleurMatiere;
	}
	
	public void setProfesseurMatiere(Professeur leProfesseurMatiere) {
		this.professeurMatiere = leProfesseurMatiere;
	}

	public String getNomMatiere() {
		return this.nomMatiere;
	}
	
	public int getNombreHeureCM() {
		return this.nombreHeureCM;
	}
	
	public int getNombreHeureTP() {
		return this.nombreHeureTP;
	}
	
	public int getNombreHeureTD() {
		return this.nombreHeureTD;
	}

	public int getNombreHeureCMrestante() {
		return this.nombreHeureCMrestante;
	}

	public int getNombreHeureTPrestante() {
		return this.nombreHeureTPrestante;
	}

	public int getNombreHeureTDrestante() {
		return this.nombreHeureTDrestante;
	}

	public int getMaxHeureRestante() {
		return Math.max(this.nombreHeureCMrestante, Math.max(this.nombreHeureTDrestante, this.nombreHeureTPrestante));
	}

	public String getTypeMatiereMaxHeureRestante() {
		if(this.nombreHeureCMrestante >= this.nombreHeureTDrestante && this.nombreHeureCMrestante >= this.nombreHeureTPrestante) {
			return "CM";
		} else if(this.nombreHeureTDrestante >= this.nombreHeureCMrestante && this.nombreHeureTDrestante >= this.nombreHeureTPrestante) {
			return "TD";
		} else {
			return "TP";
		}
	}

	public String getNiveauMatiere() {
		return this.niveauMatiere;
	}
	
	public Professeur getProfesseurMatiere() {
		return this.professeurMatiere;
	}
	
	public Color getCouleurMatiere() {
		return this.couleurMatiere;
	}

	public void enleverHeureRestante(String p_typeCours) {
		if(p_typeCours.equals("CM") { 
			this.nombreHeureCM = this.nombreHeureCM - 2;
		} else if(p_typeCours.equals("TP") {
			this.nombreHeureTP = this.nombreHeureTP - 2;
		} else if(p_typeCours.equals("TP") {
			this.nombreHeureTD = this.nombreHeureTD - 2;
		}
	}
	
}