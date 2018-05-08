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

public class MMatiere implements Serializable {
	
	private ArrayList<Matiere> lesMatieres; 
	
	
	public MMatiere() {
		this.lesMatieres = new ArrayList<Matiere>(); 
	}
	
	
	public ArrayList<Matiere> getLesMatieres(){
		return this.lesMatieres;
	}
	
	public int ajoutMatiere(String nomMatiere, String nombreHeureCM, String nombreHeureTP, String nombreHeureTD, Color couleurMatiere) {
		if(nomMatiere == "") {
			JOptionPane.showMessageDialog(null, "Erreur : Vous devez indiquer un nom pour la matière !");
		} else if(nombreHeureCM == "") {
			JOptionPane.showMessageDialog(null, "Erreur : Vous devez enter un nombre d'heure de CM pour la matière !");
		} else if(nombreHeureTD == "") {
			JOptionPane.showMessageDialog(null, "Erreur : Vous devez enter un nombre d'heure de TD pour la matière !");
		} else if(nombreHeureTP == "") {
			JOptionPane.showMessageDialog(null, "Erreur : Vous devez enter un nombre d'heure de TP pour la matière !");
		} else if(couleurMatiere.getRed() == 238 && couleurMatiere.getGreen() == 238 && couleurMatiere.getBlue() == 238) {
			JOptionPane.showMessageDialog(null, "Erreur : Vous devez choisir une couleur pour la matière !");
		} else {
			try {
				Matiere nouvelMatiere = new Matiere(nomMatiere, Integer.parseInt(nombreHeureCM), Integer.parseInt(nombreHeureTP), Integer.parseInt(nombreHeureTD), couleurMatiere);
				lesMatieres.add(nouvelMatiere);
				JOptionPane.showMessageDialog(null, "La matière " + nomMatiere + " a été ajouté.");
				System.out.println("Matiere : " + nomMatiere + " - CM : " + nombreHeureCM + " - TP : " + nombreHeureTP + " - TD : " + nombreHeureTD + " - Couleur : " + couleurMatiere);
			
				/* Ajout de la matière dans le fichier saveMatiere.txt */
				try {
					FileWriter monFichier = new FileWriter("saveMatiere.txt", true);
					BufferedWriter out = new BufferedWriter(monFichier);
					out.write(nomMatiere + "\n" + nombreHeureCM + "\n" + nombreHeureTP + "\n" + nombreHeureTD + "\n" + Integer.toString(couleurMatiere.getRGB()) + "\n");
					out.close();
					return 0;
				} catch (IOException ex) {
					System.out.println("Erreur : " + ex);
				}
			
			} catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Erreur : Vous devez entre un chiffre entier pour les nombres d'heures !");
			}
		}
		return 1;
	}
	
	public void chargerLesMatieres() {
		try {
			Charset  charset = Charset.forName("UTF-8");
			Path path = Paths.get("saveMatiere.txt");
			List<String> lignes = Files.readAllLines(path, charset);
			int i = 0, nombreHeureCM = 0, nombreHeureTP = 0, nombreHeureTD = 0;
			String nomMatiere = "";
			Color couleurMatiere;
			Matiere nouvelleMatiere;
			
			for(String ligne : lignes) {
				if(i == 0) { nomMatiere = ligne; }
				if(i == 1) { nombreHeureCM = Integer.parseInt(ligne); }
				if(i == 2) { nombreHeureTP = Integer.parseInt(ligne); }
				if(i == 3) { nombreHeureTD = Integer.parseInt(ligne); }
				if(i == 4) {
					couleurMatiere = new Color(Integer.parseInt(ligne), true);
					nouvelleMatiere = new Matiere(nomMatiere, nombreHeureCM, nombreHeureTP, nombreHeureTD, couleurMatiere);
					lesMatieres.add(nouvelleMatiere);
					i = -1;
				}
				i++;
			}
		} catch (Exception ex) {
			System.out.println("Erreur : " + ex);
		}
	}
	
}
