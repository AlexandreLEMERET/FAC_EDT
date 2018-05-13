package PROJET.Model;

import java.util.*;

public class Groupe {
	
	private String nomGroupe;
	private Classe classeGroupe;
	private Edt edtGroupe;
	
	public Groupe() {}
	
	public Groupe(String p_nomGroupe, Classe p_classeGroupe) {
		this.nomGroupe = p_nomGroupe;
		this.classeGroupe = p_classeGroupe;
		this.edtGroupe = new Edt();
	}
	
	public String getNomGroupe() {
		return this.nomGroupe;
	}
	
	public Classe getClasseGroupe() {
		return this.classeGroupe;
	}

}