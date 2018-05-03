package PROJET.Model;

import java.util.*;
import java.awt.Color;

public class Matiere {
	
	private String nomMatiere;
	private int nombreHeureCM;
	private int nombreHeureTP;
	private int nombreHeureTD;
	private Professeur professeurMatiere;
	private Color couleurMatiere;
	
	public Matiere() {}
	
	public Matiere(String p_nomMatiere, int p_nombreHeureCM, int p_nombreHeureTP, int p_nombreHeureTD, Color p_couleurMatiere) {
		this.nomMatiere = p_nomMatiere;
		this.nombreHeureCM = p_nombreHeureCM;
		this.nombreHeureTP = p_nombreHeureTP;
		this.nombreHeureTD = p_nombreHeureTD;
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
	
	public Professeur getProfesseurMatiere() {
		return this.professeurMatiere;
	}
	
	public Color getCouleurMatiere() {
		return this.couleurMatiere;
	}
	
}