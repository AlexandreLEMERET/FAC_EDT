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
	public int ajoutSalle(String numeroSalle, String nombrePlace, String typeSalle, MEdt lesEDT) {
		if (numeroSalle.length() == 0) {
			JOptionPane.showMessageDialog(null,"Erreur : Vous devez indiquer un nom de salle !");
		} else if(nombrePlace == "") {
			JOptionPane.showMessageDialog(null,"Erreur : Vous devez indiquer un nombre de place !");
		} else {
			try {
	        	Salle nouvelleSalle = new Salle(numeroSalle, Integer.parseInt(nombrePlace), typeSalle);
	   			lesSalles.add(nouvelleSalle);
				JOptionPane.showMessageDialog(null,"La salle " + numeroSalle + " a été ajouté."); 
				//System.out.println("Numéro salle : " + numeroSalle + " - Nombre places : " + nombrePlace + " - Type salle : " + typeSalle);
				return 0;
				
			
	   		} catch(NumberFormatException e) {
	        	JOptionPane.showMessageDialog(null,"Erreur : Le nombre de place doit être un chiffre entier !"); 
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
		//System.out.println("Nombre de salles : " + lesSalles.getLesSalles().size());
		for(Salle s : lesSalles.getLesSalles()) {
			//System.out.println("Numero salle : " + s.getNumeroSalle());
			cmbSalle.addItem(s.getNumeroSalle());
		}
	}
	
}

