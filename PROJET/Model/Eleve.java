package PROJET.Model;

import java.util.*;

public class Eleve {
	
	private String nomEleve;
	private String prenomEleve;
	private Classe classeEleve;
	private Groupe groupeEleve;
	private Edt edtEleve;
	
	public Eleve() {}
	
	public Eleve(String p_nomEleve, String p_prenomEleve, Classe p_classeEleve) {
		this.nomEleve = p_nomEleve;
		this.prenomEleve = p_prenomEleve;
		this.classeEleve = p_classeEleve;
		this.edtEleve = new Edt();
	}

	public Eleve(String p_nomEleve, String p_prenomEleve, Classe p_classeEleve, Groupe p_groupeEleve) {
		this.nomEleve = p_nomEleve;
		this.prenomEleve = p_prenomEleve;
		this.classeEleve = p_classeEleve;
		this.groupeEleve = p_groupeEleve;
		this.edtEleve = new Edt();
	}
	
	public String getNomEleve() {
		return this.nomEleve;
	}
	
	public String getPrenomEleve() {
		return this.prenomEleve;
	}
	
	public Classe getClasseEleve() {
		return this.classeEleve;
	}
	
	public Groupe getGroupeEleve() {
		return this.groupeEleve;
	}

	public Edt getEDT() {
		return this.edtEleve;
	}
}