package PROJET.Model;

import java.io.*;
import java.io.Serializable;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class MGroupe implements Serializable {
	
	private ArrayList<Groupe> lesGroupes; 
	
	
	public MGroupe() {
		this.lesGroupes = new ArrayList<Groupe>(); 
	}
	
	
	public ArrayList<Groupe> getLesGroupes(){
		return this.lesGroupes;
	}

	public int ajoutGroupe(String nomGroupe, int indexClasse, MClasse lesClasses, MEdt lesEDT) {
		if(indexClasse < 0) {
			JOptionPane.showMessageDialog(null, "Erreur : Vous devez choisir une classe !");
		} else if (nomGroupe.length() == 0) {
			JOptionPane.showMessageDialog(null, "Erreur : Vous devez indiquer un nom de groupe !");
		} else {
			Groupe nouveauGroupe = new Groupe(nomGroupe, lesClasses.getLesClasses().get(indexClasse));
			lesGroupes.add(nouveauGroupe);
			lesClasses.getLesClasses().get(indexClasse).getLesGroupesClasse().add(nouveauGroupe);
			JOptionPane.showMessageDialog(null, "Le groupe a été ajouté.");
			//System.out.println("Nom groupe : " + nomGroupe + " - Index classe : " + indexClasse + " - Classe : " + lesClasses.getLesClasses().get(indexClasse).getNiveauClasse() + " " + lesClasses.getLesClasses().get(indexClasse).getNomClasse());
	   		return 0;
			
		}
		return 1;
	}
	
	public void chargerLesGroupes(MClasse lesClasses) {
		try {
			Charset  charset = Charset.forName("UTF-8");
			Path path = Paths.get("saveGroupe.txt");
			List<String> lignes = Files.readAllLines(path, charset);
			int i = 0, indexClasse = 0;
			String nomGroupe = "";
			Groupe nouveauGroupe;
			
			for(String ligne : lignes) {
				if(i == 0) { nomGroupe = ligne; }
				if(i == 1) { 
					indexClasse = Integer.parseInt(ligne);
					nouveauGroupe = new Groupe(nomGroupe, lesClasses.getLesClasses().get(indexClasse));
					lesGroupes.add(nouveauGroupe);
					lesClasses.getLesClasses().get(indexClasse).getLesGroupesClasse().add(nouveauGroupe);
					i = -1;
				}
				i++;
			}
		} catch (Exception ex) {
			System.out.println("Erreur : " + ex);
		}
	}

	public void sauvegarderLesGroupes(MClasse lesClasses) {
		/* Ajout du groupe dans le fichier saveGroupe.txt */
		try {
			FileWriter monFichier = new FileWriter("saveGroupe.txt");
			BufferedWriter out = new BufferedWriter(monFichier);
			int i = 0;
			for(Classe c : lesClasses.getLesClasses()) {
				for(Groupe g : c.getLesGroupesClasse()) {
					out.write(g.getNomGroupe() + "\n" + i + "\n");
				}
				i++;
			}
			out.close();
		} catch (IOException ex) {
			System.out.println("Erreur : " + ex);
		}
	}
			

	public void chargerComboBoxGroupe(MClasse lesClasses, JComboBox<String> cmbClasseGroupe) {
		for(Classe c : lesClasses.getLesClasses()) {
			cmbClasseGroupe.addItem(c.getNiveauClasse() + " " + c.getNomClasse());
		}
	}

	public void remplirJComboBoxGroupe(JComboBox cmbGroupe, MGroupe lesGroupes) {
		cmbGroupe.removeAllItems();
		for(Groupe g : lesGroupes.getLesGroupes()) {
			cmbGroupe.addItem(g.getNomGroupe());
		}
	}

}
