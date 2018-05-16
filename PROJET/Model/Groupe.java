package PROJET.Model;

import java.util.*;

public class Groupe {
	
	private String nomGroupe;
	private Classe classeGroupe;
	private int nombreEleveGroupe;
	private ArrayList<Matiere> lesMatieres;
	private Edt edtGroupe;
	
	public Groupe() {}
	
	public Groupe(String p_nomGroupe, Classe p_classeGroupe) {
		this.nomGroupe = p_nomGroupe;
		this.classeGroupe = p_classeGroupe;
		this.lesMatieres = new ArrayList<Matiere>();
		this.edtGroupe = new Edt();
	}
	
	public void setNomGroupe(String p_nomGroupe) {
		this.nomGroupe = p_nomGroupe;
	}

	public void setClasse(Classe p_laClasse) {
		this.classeGroupe = p_laClasse;
	}

	public String getNomGroupe() {
		return this.nomGroupe;
	}
	
	public Classe getClasseGroupe() {
		return this.classeGroupe;
	}

	public int getNombreEleveGroupe() {
		return this.nombreEleveGroupe;
	}

	public ArrayList<Matiere> getLesMatieres() {
		return this.lesMatieres;
	}

	public Matiere getLaMatiere(Matiere p_laMatiere) {
		for(Matiere m : lesMatieres) {
			if(m.equals(p_laMatiere)) {
				return m;
			}
		}
		return null;
	}

	public Edt getEDT() {
		return this.edtGroupe;
	}

	public void getNombreEleveGroupe(MEleve lesEleves, Classe laClasse, Groupe leGroupe) {
		int nombreEleve = 0;
		for(Eleve e : lesEleves.getLesEleves()) {
			if(e.getClasseEleve() == laClasse) {
				if(e.getGroupeEleve() == leGroupe) 
				nombreEleve++; 
			}
		}
		this.nombreEleveGroupe = nombreEleve;
	}

	public void setLesMatieres(MMatiere lesMatieres, Classe c) {
		for(Matiere m : c.getLesMatieres()) {
			if(m.getNiveauMatiere().equals(this.getClasseGroupe().getNiveauClasse())) {
				this.lesMatieres.add((Matiere)m.clone());
			}
		}
	}

	public void trierLesMatieres() {
		int i = 0;
		ArrayList<Matiere> tmpMatiere = new ArrayList<Matiere>();
		if(this.lesMatieres.size() > 0) {
			tmpMatiere.add(lesMatieres.get(0));
			this.lesMatieres.remove(0);
			if(this.lesMatieres.size() > 0) {
				for(Matiere m : lesMatieres) {
					while(i < tmpMatiere.size() && tmpMatiere.get(i).getMaxHeureRestante() > m.getMaxHeureRestante()) {
						i++;
					}
					if(i == tmpMatiere.size()) { tmpMatiere.add(m); }
					else { tmpMatiere.add(i, m);}
					i = 0;
				}
			}
		}

		this.lesMatieres = tmpMatiere;
	}

}