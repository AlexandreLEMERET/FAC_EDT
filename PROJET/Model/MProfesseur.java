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
	
	public int ajoutProfesseur(String nomProfesseur, String prenomProfesseur, int indexMatiereProfesseur, String nombreHeureProfesseur, MMatiere lesMatieres, MEdt lesEDT) {
		if(nomProfesseur.length() == 0) {
			JOptionPane.showMessageDialog(null, "Erreur : Vous devez indiquer un nom pour le professeur !");
		} else if(prenomProfesseur.length() == 0) {
			JOptionPane.showMessageDialog(null, "Erreur : Vous devez indiquer un prénom pour le professeur !");
		} else if(indexMatiereProfesseur < 0) {
			JOptionPane.showMessageDialog(null, "Erreur : Vous devez choisir une matière pour le professeur !");
		} else {
			try {
				Edt nouvelEDT = new Edt();
				lesEDT.ajoutEDT(nouvelEDT);
				Professeur nouveauProfesseur = new Professeur(nomProfesseur, prenomProfesseur, lesMatieres.getLesMatieres().get(indexMatiereProfesseur), Integer.parseInt(nombreHeureProfesseur), nouvelEDT);
				lesProfesseurs.add(nouveauProfesseur);
				lesMatieres.getLesMatieres().get(indexMatiereProfesseur).setProfesseurMatiere(nouveauProfesseur);
				JOptionPane.showMessageDialog(null,"Le professeur a été ajouté.");
				System.out.println("Nom professeur : " + nomProfesseur + " " + prenomProfesseur + " - Matiere : " + lesMatieres.getLesMatieres().get(indexMatiereProfesseur).getNomMatiere() + " - Nombre heure : " + nombreHeureProfesseur);
			
				/* Ajout du nouveau professeur dans le fichier saveProfesseur.txt */
				try {
					FileWriter monFichier = new FileWriter("saveProfesseur.txt", true);
					BufferedWriter out = new BufferedWriter(monFichier);
					out.write(nomProfesseur + "\n" + prenomProfesseur + "\n" + indexMatiereProfesseur + "\n" + nombreHeureProfesseur + "\n");
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

	public void chargerComboBoxProfesseur(MMatiere lesMatieres, JComboBox<String> cmbMatiereProfesseur) {
		for(Matiere m : lesMatieres.getLesMatieres()) {
			cmbMatiereProfesseur.addItem(m.getNomMatiere());
		}
	}

	public void chargerLesProfesseurs(MMatiere lesMatieres) {
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
				if(i == 2) { indexMatiereProfesseur = Integer.parseInt(ligne); }
				if(i == 3) { 
					nombreHeureProfesseur = Integer.parseInt(ligne); 
					nouveauProfesseur = new Professeur(nomProfesseur, prenomProfesseur, lesMatieres.getLesMatieres().get(indexMatiereProfesseur), nombreHeureProfesseur);
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
