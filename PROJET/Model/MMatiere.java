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

public class MMatiere implements Serializable {
	
	private ArrayList<Matiere> lesMatieres; 
	
	
	public MMatiere() {
		this.lesMatieres = new ArrayList<Matiere>(); 
	}
	
	
	public ArrayList<Matiere> getLesMatieres(){
		return this.lesMatieres;
	}
	public int ajoutMatiere(String nomMatiere, String nombreHeureCM, String nombreHeureTP, String nombreHeureTD, String niveauMatiere, int indexProfesseur, Color couleurMatiere, MProfesseur lesProfesseurs) {
		if(nomMatiere == "") {
			JOptionPane.showMessageDialog(null, "Erreur : Vous devez indiquer un nom pour la matière !");
		} else if(nombreHeureCM == "") {
			JOptionPane.showMessageDialog(null, "Erreur : Vous devez enter un nombre d'heure de cours en salle pour la matière !");
		} else if(nombreHeureTD == "") {
			JOptionPane.showMessageDialog(null, "Erreur : Vous devez enter un nombre d'heure de cours en salle informatique pour la matière !");
		} else if(nombreHeureTP == "") {
			JOptionPane.showMessageDialog(null, "Erreur : Vous devez enter un nombre d'heure de cours en salle de TP pour la matière !");
		} else if(indexProfesseur == -1) {
			JOptionPane.showMessageDialog(null, "Erreur : Vous devez choisir un professeur pour la matiere !");
		} else if(couleurMatiere.getRed() == 238 && couleurMatiere.getGreen() == 238 && couleurMatiere.getBlue() == 238) {
			JOptionPane.showMessageDialog(null, "Erreur : Vous devez choisir une couleur pour la matière !");
		} else {
			try {
				
				Matiere nouvelMatiere = new Matiere(nomMatiere, Integer.parseInt(nombreHeureCM), Integer.parseInt(nombreHeureTP), Integer.parseInt(nombreHeureTD), niveauMatiere, lesProfesseurs.getLesProfesseurs().get(indexProfesseur), couleurMatiere);
				lesMatieres.add(nouvelMatiere);
				if(verificationNombreHeureClasse(niveauMatiere) > 40) {
					int heure = verificationNombreHeureClasse(niveauMatiere);
					JOptionPane.showMessageDialog(null, "Il y a " + -(40-heure) + " heures de cours en trop afin de ne pas dépasser le total d'heures hebdomadaire de la classe !");
					lesMatieres.remove(lesMatieres.size()-1);
					return 1;
		
				} else {
					JOptionPane.showMessageDialog(null, "La matière " + nomMatiere + " a été ajouté.");
				}
				return 0;				
			
			} catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Erreur : Vous devez entre un chiffre entier pour les nombres d'heures !");
			}
		}
		return 1;
	}

	public int verificationNombreHeureClasse(String niveauMatiere) {
		int totalH = 0;
		for(Matiere m : lesMatieres) {
			if(m.getNiveauMatiere().equals(niveauMatiere)) {
				totalH = m.getNombreHeureCM() + m.getNombreHeureTP() + m.getNombreHeureTD() + totalH;
			}
		}
		return totalH;
	}

	public void chargerComboBoxProfesseurMatiere(MProfesseur lesProfesseurs, JComboBox<String> cmbProfesseurMatiere) {
		for(Professeur p : lesProfesseurs.getLesProfesseurs()) {
			cmbProfesseurMatiere.addItem(p.getNomProfesseur() + " " + p.getPrenomProfesseur());
		}
	}


	public void chargerLesMatieres(MProfesseur lesProfesseurs, MClasse lesClasses) {
		try {
			Charset  charset = Charset.forName("UTF-8");
			Path path = Paths.get("saveMatiere.txt");
			List<String> lignes = Files.readAllLines(path, charset);
			int i = 0, nombreHeureCM = 0, nombreHeureTP = 0, nombreHeureTD = 0, indexProfesseur = 0;
			String nomMatiere = "", niveauClasse = "";
			Color couleurMatiere;
			Matiere nouvelleMatiere;
			for(String ligne : lignes) {
				if(i == 0) { nomMatiere = ligne; }
				if(i == 1) { nombreHeureCM = Integer.parseInt(ligne); }
				if(i == 2) { nombreHeureTP = Integer.parseInt(ligne); }
				if(i == 3) { nombreHeureTD = Integer.parseInt(ligne); }
				if(i == 4) { niveauClasse = ligne; }
				if(i == 5) { indexProfesseur = Integer.parseInt(ligne); }
				if(i == 6) {
					couleurMatiere = new Color(Integer.parseInt(ligne), true);
					nouvelleMatiere = new Matiere(nomMatiere, nombreHeureCM, nombreHeureTD, nombreHeureTP, niveauClasse, lesProfesseurs.getLesProfesseurs().get(indexProfesseur), couleurMatiere);
					lesMatieres.add(nouvelleMatiere);
					i = -1;
				}
				i++;
			}
			JOptionPane.showMessageDialog(null, "L'importation est terminée.");
		} catch (Exception ex) {
			System.out.println("Erreur : " + ex);
		}
	}
				
	public void sauvegarderLesMatieres(MProfesseur lesProfesseurs) {
		/* Ajout des matieres dans le fichier saveMatiere.txt */
		try {
			FileWriter monFichier = new FileWriter("saveMatiere.txt");
			BufferedWriter out = new BufferedWriter(monFichier);
			int i = 0;
			for(Professeur p : lesProfesseurs.getLesProfesseurs()) {
				for(Matiere m : lesMatieres) {
					if(m.getProfesseurMatiere() == p) {
						out.write(m.getNomMatiere() + "\n" + m.getNombreHeureCM() + "\n" + m.getNombreHeureTP() + "\n" + m.getNombreHeureTD() + "\n" + m.getNiveauMatiere() + "\n" +  i + "\n" + Integer.toString(m.getCouleurMatiere().getRGB()) + "\n");
					}
				}	
				i++;
			}
			out.close();
			JOptionPane.showMessageDialog(null, "La sauvegarde est terminée.");
		} catch (IOException ex) {
			System.out.println("Erreur : " + ex);
		}
	}

}
