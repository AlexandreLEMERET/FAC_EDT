package PROJET.Model;

import java.util.*;
import java.lang.Math;
import java.awt.Color;

public class Matiere implements Cloneable {
	
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
	
	public Matiere(String p_nomMatiere, int p_nombreHeureCM, int p_nombreHeureTD, int p_nombreHeureTP, String p_niveauMatiere, Professeur p_professeurMatiere, Color p_couleurMatiere) {
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
	
	public void setNomMatiere(String p_nomMatiere) {
		this.nomMatiere = p_nomMatiere;
	}

	public void setNombreHeureCM(int p_nombreHeureCM) {
		this.nombreHeureCM = p_nombreHeureCM;
		this.nombreHeureCMrestante = p_nombreHeureCM;
	}

	public void setNombreHeureTP(int p_nombreHeureTP) {
		this.nombreHeureTP = p_nombreHeureTP;
		this.nombreHeureTPrestante = p_nombreHeureTP;
	}

	public void setNombreHeureTD(int p_nombreHeureTD) {
		this.nombreHeureTD = p_nombreHeureTD;
		this.nombreHeureTDrestante = p_nombreHeureTD;
	}

	public void setNiveauMatiere(String p_niveauMatiere) {
		this.niveauMatiere = p_niveauMatiere;
	}

	public void setProfesseurMatiere(Professeur p_leProfesseurMatiere) {
		this.professeurMatiere = p_leProfesseurMatiere;
	}

	public void setCouleurMatiere(Color p_couleurMatiere) {
		this.couleurMatiere = p_couleurMatiere;
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
		if(p_typeCours.equals("CM")) { 
			this.nombreHeureCMrestante = this.nombreHeureCMrestante - 2;
		} else if(p_typeCours.equals("TP")) {
			this.nombreHeureTPrestante = this.nombreHeureTPrestante - 2;
		} else if(p_typeCours.equals("TD")) {
			this.nombreHeureTDrestante = this.nombreHeureTDrestante - 2;
		}
	}

	public Object clone() {
    	Object o = null;
    	try {
      		o = super.clone();
    	} catch(CloneNotSupportedException cnse) {
      		cnse.printStackTrace(System.err);
	    }
	    return o;
  	}
	
}