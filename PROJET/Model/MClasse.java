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

public class MClasse implements Serializable {
	
	private ArrayList<Classe> lesClasses; 
	
	
	public MClasse() {
		this.lesClasses = new ArrayList<Classe>(); 
	}

	public ArrayList<Classe> getLesClasses(){
		return this.lesClasses;
	}

	public void ajoutClasse(String nomClasse, String niveauClasse, Color couleurClasse, MEdt lesEDT) {
		if(nomClasse.length() == 0) {
			JOptionPane.showMessageDialog(null, "Erreur : Le nom de la classe doit contenir au moins 1 caractère !");
		} else if(couleurClasse.getRed() == 238 && couleurClasse.getGreen() == 238 && couleurClasse.getBlue() == 238) {
			JOptionPane.showMessageDialog(null, "Erreur : Vous devez choisir une couleur pour la classe !");
		} else {
			Edt nouvelEDT = new Edt();
			lesEDT.ajoutEDT(nouvelEDT);
			Classe nouvelleClasse = new Classe(nomClasse, niveauClasse, couleurClasse, nouvelEDT);
			lesClasses.add(nouvelleClasse);
			JOptionPane.showMessageDialog(null, "La classe de " + niveauClasse + " " + nomClasse + " a été ajouté.");
			System.out.println("Nom classe " + nomClasse + " - Niveau classe : " + niveauClasse + " - Couleur : " + couleurClasse);
			
			/* Ajout d'une classe dans le fichier saveClasse.txt */
			try {
				FileWriter monFichier = new FileWriter("saveClasse.txt", true);
				BufferedWriter out = new BufferedWriter(monFichier);
				out.write(nomClasse + "\n" + niveauClasse + "\n" + Integer.toString(couleurClasse.getRGB()) + "\n");
				out.close();
			} catch (IOException ex) {
				System.out.println("Erreur : " + ex);
			}
		}
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
}
