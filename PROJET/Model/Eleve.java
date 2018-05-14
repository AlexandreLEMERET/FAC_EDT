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

	public void setEDT(MGroupe lesGroupes) {
		for(Groupe g : lesGroupes.getLesGroupes()) {
			if(this.groupeEleve == g) {
				for(Jour j : edtEleve.getLesJours()) {
					for(Cours co : j.getLesCours()) {
						this.edtEleve.getLeJour(j).getLeCours(co).setLaMatiere(g.getEDT().getLeJour(j).getLeCours(co).getMatiere());
						this.edtEleve.getLeJour(j).getLeCours(co).setLeTypeCours(g.getEDT().getLeJour(j).getLeCours(co).getTypeCours());
						this.edtEleve.getLeJour(j).getLeCours(co).setLeProfesseur(g.getEDT().getLeJour(j).getLeCours(co).getProfesseur());
						this.edtEleve.getLeJour(j).getLeCours(co).setLaSalle(g.getEDT().getLeJour(j).getLeCours(co).getSalle());
						this.edtEleve.getLeJour(j).getLeCours(co).setLaClasse(g.getEDT().getLeJour(j).getLeCours(co).getClasse());
						this.edtEleve.getLeJour(j).getLeCours(co).setLeGroupe(g.getEDT().getLeJour(j).getLeCours(co).getGroupe());
						this.edtEleve.getLeJour(j).getLeCours(co).setOccupe();
					}
				}
			}
		}
	}

	public void setEDT(MClasse lesClasses) {
		for(Classe c : lesClasses.getLesClasses()) {
			if(this.classeEleve == c) {
				for(Jour j : edtEleve.getLesJours()) {
					for(Cours co : j.getLesCours()) {
						this.edtEleve.getLeJour(j).getLeCours(co).setLaMatiere(c.getEDT().getLeJour(j).getLeCours(co).getMatiere());
						this.edtEleve.getLeJour(j).getLeCours(co).setLeTypeCours(c.getEDT().getLeJour(j).getLeCours(co).getTypeCours());
						this.edtEleve.getLeJour(j).getLeCours(co).setLeProfesseur(c.getEDT().getLeJour(j).getLeCours(co).getProfesseur());
						this.edtEleve.getLeJour(j).getLeCours(co).setLaSalle(c.getEDT().getLeJour(j).getLeCours(co).getSalle());
						this.edtEleve.getLeJour(j).getLeCours(co).setLaClasse(c.getEDT().getLeJour(j).getLeCours(co).getClasse());
						this.edtEleve.getLeJour(j).getLeCours(co).setLeGroupe(c.getEDT().getLeJour(j).getLeCours(co).getGroupe());
						this.edtEleve.getLeJour(j).getLeCours(co).setOccupe();
					}
				}
			}
		}
	}
}