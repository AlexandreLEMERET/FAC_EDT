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
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class MSalle implements Serializable {
	
	private ArrayList<Salle> lesSalles; 
	
	/* Constructeur d'une salle */
	public MSalle() {
		this.lesSalles = new ArrayList<Salle>(); 
	}
	
	/* Renvoie la liste de salles */
	public ArrayList<Salle> getLesSalles() {
		return this.lesSalles;
	}

	/* Permet d'ajouter une salle */
	public int ajoutSalle(String numeroSalle, String nombrePlace, int valeurIndex, MEdt lesEDT) {
		if (numeroSalle.length() == 0) {
			JOptionPane.showMessageDialog(null,"Erreur : Vous devez indiquer un nom de salle !", "Erreur : Nom de salle", JOptionPane.ERROR_MESSAGE);
		} else if(nombrePlace == "") {
			JOptionPane.showMessageDialog(null,"Erreur : Vous devez indiquer un nombre de place !", "Erreur : Nombre de place", JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				String typeSalle = "";
				if(valeurIndex == 0) { typeSalle = "CM"; }
				if(valeurIndex == 1) { typeSalle = "TP"; }
				if(valeurIndex == 2) { typeSalle = "TD"; }
	        	Salle nouvelleSalle = new Salle(numeroSalle, Integer.parseInt(nombrePlace), typeSalle);
	   			lesSalles.add(nouvelleSalle);
				JOptionPane.showMessageDialog(null,"La salle " + numeroSalle + " a été ajouté."); 
				return 0;
				
			
	   		} catch(NumberFormatException e) {
	        	JOptionPane.showMessageDialog(null,"Erreur : Le nombre de place doit être un chiffre entier !", "Erreur : Nombre de place", JOptionPane.ERROR_MESSAGE); 
	    	}
	    }
	    return 1;
	}
	
	public void chargerLesSalles() {
		try {
			Charset  charset = Charset.forName("UTF-8");
			Path path = Paths.get("saveSalle.txt");
			List<String> lignes = Files.readAllLines(path, charset);
			int i = 0;
			String nombrePlace = "", numeroSalle = "", typeSalle = "";
			Salle nouvelleSalle;
			
			for(String ligne : lignes) {
				if(i == 0) { numeroSalle = ligne; }
				if(i == 1) { nombrePlace = ligne; }
				if(i == 2) { typeSalle = ligne;
					nouvelleSalle = new Salle(numeroSalle, Integer.parseInt(nombrePlace), typeSalle);
					lesSalles.add(nouvelleSalle);
					i = -1;
				} 
				i++;
			}
		} catch(Exception ex) {
			System.out.println("Erreur : " + ex);
		}
	}

	public void sauvegarderLesSalles() {
		/* Ajout des dans le fichier saveSalle.txt */
		try {
			FileWriter monFichier = new FileWriter("saveSalle.txt");
			BufferedWriter out = new BufferedWriter(monFichier);
			for(Salle s : lesSalles) {
				out.write(s.getNumeroSalle() + "\n" + s.getNombrePlacesSalle() + "\n" + s.getTypeSalle() + "\n");
			}
			out.close();
		} catch (IOException ex) {
			System.out.println("Erreur : " + ex);
		}
	}

	public void remplirJComboBoxSalle(JComboBox cmbSalle, MSalle lesSalles) {
		cmbSalle.removeAllItems();
		for(Salle s : lesSalles.getLesSalles()) {
			cmbSalle.addItem(s.getNumeroSalle());
		}
	}
	
}

