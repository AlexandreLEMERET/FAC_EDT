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

public class MProfesseur implements Serializable {
	
	private ArrayList<Professeur> lesProfesseurs; 
	
	
	public MProfesseur() {
		this.lesProfesseurs = new ArrayList<Professeur>(); 
	}
	
	
	public ArrayList<Professeur> getLesProfesseurs(){
		return this.lesProfesseurs;
	}
	
	public int ajoutProfesseur(String nomProfesseur, String prenomProfesseur, String nombreHeureProfesseur, MEdt lesEDT) {
		if(nomProfesseur.length() == 0) {
			JOptionPane.showMessageDialog(null, "Erreur : Vous devez indiquer un nom pour le professeur !");
		} else if(prenomProfesseur.length() == 0) {
			JOptionPane.showMessageDialog(null, "Erreur : Vous devez indiquer un prénom pour le professeur !");
		} else {
			try {
				Edt nouvelEDT = new Edt();
				lesEDT.ajoutEDT(nouvelEDT);
				Professeur nouveauProfesseur = new Professeur(nomProfesseur, prenomProfesseur, Integer.parseInt(nombreHeureProfesseur), nouvelEDT);
				lesProfesseurs.add(nouveauProfesseur);
				JOptionPane.showMessageDialog(null,"Le professeur a été ajouté.");
				System.out.println("Nom professeur : " + nomProfesseur + " " + prenomProfesseur + " - Nombre heure : " + nombreHeureProfesseur);
			
				/* Ajout du nouveau professeur dans le fichier saveProfesseur.txt */
				try {
					FileWriter monFichier = new FileWriter("saveProfesseur.txt", true);
					BufferedWriter out = new BufferedWriter(monFichier);
					out.write(nomProfesseur + "\n" + prenomProfesseur + "\n" + nombreHeureProfesseur + "\n");
					out.close();
					return 0;
				} catch (IOException ex) {
					System.out.println("Erreur : " + ex);
				}

			} catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Erreur : Le nombre d'heure doit être un chiffre entier !");
			}
		}
		return 1;
	}

	public void chargerLesProfesseurs() {
		try {
			Charset  charset = Charset.forName("UTF-8");
			Path path = Paths.get("saveProfesseur.txt");
			List<String> lignes = Files.readAllLines(path, charset);
			int i = 0, indexMatiereProfesseur = 0, nombreHeureProfesseur = 0;
			String nomProfesseur = "", prenomProfesseur = "";
			Professeur nouveauProfesseur;
			
			for(String ligne : lignes) {
				if(i == 0) { nomProfesseur = ligne; }
				if(i == 1) { prenomProfesseur = ligne; }
				if(i == 2) { 
					nombreHeureProfesseur = Integer.parseInt(ligne); 
					nouveauProfesseur = new Professeur(nomProfesseur, prenomProfesseur, nombreHeureProfesseur);
					lesProfesseurs.add(nouveauProfesseur);
					i = -1;
				}
				i++;
			}
		} catch (Exception ex) {
			System.out.println("Erreur : " + ex);
		}
	}
 				
}
