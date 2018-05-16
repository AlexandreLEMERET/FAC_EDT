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
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class MClasse implements Serializable {
	
	private ArrayList<Classe> lesClasses; 
	
	
	public MClasse() {
		this.lesClasses = new ArrayList<Classe>(); 
	}

	public ArrayList<Classe> getLesClasses(){
		return this.lesClasses;
	}

	public int ajoutClasse(String nomClasse, String niveauClasse, Color couleurClasse, MEdt lesEDT) {
		if(nomClasse.length() == 0) {
			JOptionPane.showMessageDialog(null, "Erreur : Le nom de la classe doit contenir au moins 1 caractère !", "Erreur : Nom de la classe", JOptionPane.ERROR_MESSAGE);
		} else if(couleurClasse.getRed() == 238 && couleurClasse.getGreen() == 238 && couleurClasse.getBlue() == 238) {
			JOptionPane.showMessageDialog(null, "Erreur : Vous devez choisir une couleur pour la classe !", "Erreur : Couleur de la classe", JOptionPane.ERROR_MESSAGE);
		} else {
			Classe nouvelleClasse = new Classe(nomClasse, niveauClasse, couleurClasse);
			lesClasses.add(nouvelleClasse);
			JOptionPane.showMessageDialog(null, "La classe de " + niveauClasse + " " + nomClasse + " a été ajouté.");
			return 0;
		}
		return 1;
	}

	public int modifierClasse(String nomClasse, String niveauClasse, Color couleurClasse, int indexClasse) {
		if(nomClasse.length() == 0) {
			JOptionPane.showMessageDialog(null, "Erreur : Le nom de la classe doit contenir au moins 1 caractère !", "Erreur : Nom de la classe", JOptionPane.ERROR_MESSAGE);
		} else if(couleurClasse.getRed() == 238 && couleurClasse.getGreen() == 238 && couleurClasse.getBlue() == 238) {
			JOptionPane.showMessageDialog(null, "Erreur : Vous devez choisir une couleur pour la classe !", "Erreur : Couleur de la classe", JOptionPane.ERROR_MESSAGE);
		} else {
			lesClasses.get(indexClasse).setNomClasse(nomClasse);
			lesClasses.get(indexClasse).setNiveauClasse(niveauClasse);
			lesClasses.get(indexClasse).setCouleurClasse(couleurClasse);
			JOptionPane.showMessageDialog(null, "La classe de " + niveauClasse + " " + nomClasse + " a été modifié.");
			return 0;
		}
		return 1;
	}
	
	public void chargerLesClasses() {
		try {
			Charset  charset = Charset.forName("UTF-8");
			Path path = Paths.get("saveClasse.txt");
			List<String> lignes = Files.readAllLines(path, charset);
			int i = 0;
			String nomClasse = "", niveauClasse = "";
			Color couleurClasse;
			Classe nouvelleClasse;
			
			for(String ligne : lignes) {
				if(i == 0) { nomClasse = ligne; }
				if(i == 1) { niveauClasse = ligne; }
				if(i == 2) {
					couleurClasse = new Color(Integer.parseInt(ligne), true);
					nouvelleClasse = new Classe(nomClasse, niveauClasse, couleurClasse);
					lesClasses.add(nouvelleClasse);
					i = -1;
				}
				i++;
			}
			
		} catch(Exception ex) {
			System.out.println("Erreur : " + ex);
		}
	}

	public void sauvegarderLesClasses() {
		/* Ajout des classes dans le fichier saveClasse.txt */
		try {
			FileWriter monFichier = new FileWriter("saveClasse.txt");
			BufferedWriter out = new BufferedWriter(monFichier);
			for(Classe c : lesClasses) {
				out.write(c.getNomClasse() + "\n" + c.getNiveauClasse() + "\n" + Integer.toString(c.getCouleurClasse().getRGB()) + "\n");
			}
			out.close();
		} catch (IOException ex) {
			System.out.println("Erreur : " + ex);
		}
	}

	public void remplirJComboBoxClasse(JComboBox cmbClasse, MClasse lesClasses) {
		cmbClasse.removeAllItems();
		for(Classe c : lesClasses.getLesClasses()) {
			cmbClasse.addItem(c.getNiveauClasse() + " " + c.getNomClasse());
		}
	}
							

	
}
