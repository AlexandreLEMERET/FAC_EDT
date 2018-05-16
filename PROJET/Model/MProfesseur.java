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
	
	public int ajoutProfesseur(String nomProfesseur, String prenomProfesseur, String nombreHeureProfesseur, MEdt lesEDT, MMatiere lesMatieres) {
		if(nomProfesseur.length() == 0) {
			JOptionPane.showMessageDialog(null, "Erreur : Vous devez indiquer un nom pour le professeur !", "Erreur : Nom du professeur", JOptionPane.ERROR_MESSAGE);
		} else if(prenomProfesseur.length() == 0) {
			JOptionPane.showMessageDialog(null, "Erreur : Vous devez indiquer un prénom pour le professeur !", "Erreur : Prénom du professeur", JOptionPane.ERROR_MESSAGE);
		} else if(Integer.parseInt(nombreHeureProfesseur) > 40) {
			JOptionPane.showMessageDialog(null, "Erreur : Le total d'heure du professeur ne peut excéder le total d'heures hebdomadaire (40 heures) !", "Erreur : Nombre d'heures", JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				Professeur nouveauProfesseur = new Professeur(nomProfesseur, prenomProfesseur, Integer.parseInt(nombreHeureProfesseur));
				lesProfesseurs.add(nouveauProfesseur);
				if(verificationNombreHeureProfesseur(lesMatieres, nouveauProfesseur) > 40 || verificationNombreHeureProfesseur(lesMatieres, nouveauProfesseur) > nouveauProfesseur.getNombreHeuresProfesseur()) {
					int heure = verificationNombreHeureProfesseur(lesMatieres, nouveauProfesseur);
					JOptionPane.showMessageDialog(null, "Il y a " + -(40-heure) + " heures de cours en trop afin de ne pas dépasser le total d'heures hebdomadaire du professeur !", "Erreur Nombre d'heures", JOptionPane.ERROR_MESSAGE);
					lesProfesseurs.remove(lesProfesseurs.size()-1);
					return 1;
				}
				JOptionPane.showMessageDialog(null,"Le professeur a été ajouté.");
				return 0;

			} catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Erreur : Le nombre d'heure doit être un chiffre entier !", "Erreur : Nombre d'heures", JOptionPane.ERROR_MESSAGE);
			}
		}
		return 1;
	}

	public int modifierProfesseur(String nomProfesseur, String prenomProfesseur, String nombreHeureProfesseur, MMatiere lesMatieres, int indexProfesseur) {
		if(nomProfesseur.length() == 0) {
			JOptionPane.showMessageDialog(null, "Erreur : Vous devez indiquer un nom pour le professeur !", "Erreur : Nom du professeur", JOptionPane.ERROR_MESSAGE);
		} else if(prenomProfesseur.length() == 0) {
			JOptionPane.showMessageDialog(null, "Erreur : Vous devez indiquer un prénom pour le professeur !", "Erreur : Prénom du professeur", JOptionPane.ERROR_MESSAGE);
		} else if(Integer.parseInt(nombreHeureProfesseur) > 40) {
			JOptionPane.showMessageDialog(null, "Erreur : Le total d'heure du professeur ne peut excéder le total d'heures hebdomadaire (40 heures) !", "Erreur : Nombre d'heures", JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				String tmpNomProfesseur = lesProfesseurs.get(indexProfesseur).getNomProfesseur();
				String tmpPrenomProfesseur = lesProfesseurs.get(indexProfesseur).getPrenomProfesseur();
				String tmpNombreHeureProfesseur = String.valueOf(lesProfesseurs.get(indexProfesseur).getNombreHeuresProfesseur());

				lesProfesseurs.get(indexProfesseur).setNomProfesseur(nomProfesseur);
				lesProfesseurs.get(indexProfesseur).setPrenomProfesseur(prenomProfesseur);
				lesProfesseurs.get(indexProfesseur).setNombreHeuresProfesseur(Integer.parseInt(nombreHeureProfesseur));

				if(verificationNombreHeureProfesseur(lesMatieres, lesProfesseurs.get(indexProfesseur)) > 40 || verificationNombreHeureProfesseur(lesMatieres, lesProfesseurs.get(indexProfesseur)) > lesProfesseurs.get(indexProfesseur).getNombreHeuresProfesseur()) {
					int heure = verificationNombreHeureProfesseur(lesMatieres,lesProfesseurs.get(indexProfesseur));
					JOptionPane.showMessageDialog(null, "Il y a " + -(40-heure) + " heures de cours en trop afin de ne pas dépasser le total d'heures hebdomadaire du professeur !", "Erreur Nombre d'heures", JOptionPane.ERROR_MESSAGE);
					lesProfesseurs.get(indexProfesseur).setNomProfesseur(tmpNomProfesseur);
					lesProfesseurs.get(indexProfesseur).setPrenomProfesseur(tmpPrenomProfesseur);
					lesProfesseurs.get(indexProfesseur).setNombreHeuresProfesseur(Integer.parseInt(tmpNombreHeureProfesseur));
					return 1;
				}
				JOptionPane.showMessageDialog(null,"Le professeur a été modifié.");
				return 0;

			} catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Erreur : Le nombre d'heure doit être un chiffre entier !", "Erreur : Nombre d'heures", JOptionPane.ERROR_MESSAGE);
			}
		}
		return 1;
	}

	public int verificationNombreHeureProfesseur(MMatiere lesMatieres, Professeur leProfesseur) {
		int totalH = 0;
		for(Matiere m : lesMatieres.getLesMatieres()) {
			if(m.getProfesseurMatiere().equals(leProfesseur)) {
				totalH = m.getNombreHeureCM() + m.getNombreHeureTP() + m.getNombreHeureTD() + totalH;
			}
		}
		return totalH;
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

	public void sauvegarderLesProfesseurs() {
		/* Ajout des professeurs dans le fichier saveProfesseur.txt */
		try {
			FileWriter monFichier = new FileWriter("saveProfesseur.txt");
			BufferedWriter out = new BufferedWriter(monFichier);
			for(Professeur p : lesProfesseurs) {
				out.write(p.getNomProfesseur() + "\n" + p.getPrenomProfesseur() + "\n" + p.getNombreHeuresProfesseur() + "\n");
			}
			out.close();
		} catch (IOException ex) {
			System.out.println("Erreur : " + ex);
		}
	}

	public void remplirJComboBoxProfesseur(JComboBox cmbProfesseur, MProfesseur lesProfesseurs) {
		cmbProfesseur.removeAllItems();
		for(Professeur p :lesProfesseurs.getLesProfesseurs()) {
			cmbProfesseur.addItem(p.getNomProfesseur() + " " + p.getPrenomProfesseur());
		}

	}
 				
}
