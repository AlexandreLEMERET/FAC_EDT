package PROJET.Model;

import java.util.*;
import java.awt.Color;

public class Classe {
	
	private String nomClasse;
	private String niveauClasse;
	private ArrayList<Groupe> lesGroupes;
	private Color couleurClasse;
	private Edt edtClasse;
	
	public Classe() {}
	
	public Classe(String p_nomClasse, String p_niveauClasse, Color p_couleurClasse) {
		this.nomClasse = p_nomClasse;
		this.niveauClasse = p_niveauClasse;
		this.lesGroupes = new ArrayList<Groupe>();
		this.couleurClasse = p_couleurClasse;
	}
	
	public Classe(String p_nomClasse, String p_niveauClasse, Color p_couleurClasse, Edt p_edtClasse) {
		this.nomClasse = p_nomClasse;
		this.niveauClasse = p_niveauClasse;
		this.lesGroupes = new ArrayList<Groupe>();
		this.couleurClasse = p_couleurClasse;
		this.edtClasse = p_edtClasse;
	}

	public String getNomClasse() {
		return this.nomClasse;
	}
	
	public String getNiveauClasse() {
		return this.niveauClasse;
	}

	public ArrayList<Groupe> getLesGroupesClasse() {
		return this.lesGroupes;
	}
	
	public Color getCouleurClasse() {
		return this.couleurClasse;
	}
}