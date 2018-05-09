package PROJET.Model;

import java.util.*;
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

	public String getNiveauMatiere() {
		return this.niveauMatiere;
	}
	
	public Professeur getProfesseurMatiere() {
		return this.professeurMatiere;
	}
	
	public Color getCouleurMatiere() {
		return this.couleurMatiere;
	}

	public void enleverHeureCMrestante(int nbHeureCM) {
		this.nombreHeureCM = this.nombreHeureCM - nbHeureCM;
	}

	public void enleverHeureTPrestante(int nbHeureTP) {
		this.nombreHeureTP = this.nombreHeureTP - nbHeureTP;
	}

	public void enleverHeureTDrestante(int nbHeureTD) {
		this.nombreHeureTD = this.nombreHeureTD - nbHeureTD;
	}
	
}