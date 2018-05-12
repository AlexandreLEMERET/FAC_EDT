package PROJET.View;

import PROJET.Model.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JColorChooser;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.util.*;

/* Interface graphique */
@SuppressWarnings("serial")

public class InterfaceGraphique extends JFrame {

	private CardLayout cardLayout;
	
	private JFrame frameAjoutSalle, frameAjoutProfesseur, frameAjoutClasse, frameAjoutGroupe, frameAjoutEleve, frameAjoutMatiere;
	
	private JPanel panelGlobal, panelBoutonCardCreation, cardCreation, cardObjetsCrees, panelBoutonObjetsCrees, panelBoutonPrecedent, panelBoutonSuivant, cardEDT, boutonPane, panelAjoutSalle_1, panelAjoutSalle_2, panelAjoutProfesseur_1, panelAjoutProfesseur_2, panelAjoutClasse_1, panelAjoutClasse_2,
			panelAjoutGroupe_1, panelAjoutGroupe_2, panelAjoutEleve_1, panelAjoutEleve_2, panelAjoutMatiere_1, panelAjoutMatiere_2;
			
	private JTextArea textCreation;
			
	private JButton boutonPrecedent, boutonSuivant, boutonAjouter, boutonCreation, boutonGenerer, boutonEDT, boutonAccepterSalle, boutonAccepterProfesseur, boutonAccepterClasse, 
			boutonAccepterGroupe, boutonAccepterEleve, boutonAccepterMatiere, boutonCouleurClasse, boutonCouleurMatiere, boutonImporter;
			
	private JComboBox<String> cmbNiveauMatiere, cmbProfesseurMatiere, cmbMessageList, cmbTypeList, cmbChoixList, cmbNiveauClasse, cmbTypeSalle, cmbClasseGroupe, cmbClasseEleve, cmbGroupeEleve;
	
	private JLabel labelNiveauMatiere, labelProfesseurMatiere, labelNumeroSalle, labelNombrePlaceSalle, labelTypeSalle, labelNomProfesseur, labelPrenomProfesseur, labelNombreHeureProfesseur, labelNomClasse, labelNiveauClasse,
			labelCouleurClasse, labelNomGroupe, labelClasseGroupe, labelNomEleve, labelPrenomEleve, labelClasseEleve, labelGroupeEleve, labelNomMatiere, labelNbrHCMMatiere, labelNbrHTDMatiere,labelNbrHTPMatiere,
			labelProfMatiere, labelCouleurMatiere, text;
			
	private JTextField tfNumeroSalle, tfNombrePlaceSalle, tfNomProfesseur, tfPrenomProfesseur, tfNombreHeureProfesseur, tfNomClasse, tfNomGroupe, tfNomEleve, tfPrenomEleve, tfNomMatiere, tfNbrHCMMatiere,
			tfNbrHTDMatiere, tfNbrHTPMatiere;
	
	private String[] messageStrings, typeStrings, choixStrings, niveauxStrings, listContent = {"CARD_1", "CARD_2"};

	private ArrayList<JButton> lesBoutonsSalle = new ArrayList<JButton>(), lesBoutonsProfesseur = new ArrayList<JButton>(), lesBoutonsClasse = new ArrayList<JButton>(), lesBoutonsGroupe = new ArrayList<JButton>(),
	        lesBoutonsEleve = new ArrayList<JButton>(),lesBoutonsMatiere = new ArrayList<JButton>(); 

	/* Constructeur de l'interface */
	public InterfaceGraphique() {
		this.setSize(800,600);
		this.setTitle("Générateur d'emploi du temps");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.getContentPane().add(boutonPane(), BorderLayout.NORTH);
		this.getContentPane().add(panelGlobal(), BorderLayout.CENTER);
		this.setVisible(true);
	}	


	/* JPanel contenant le bouton précedent */
	private JPanel panelBoutonPrecedent() {
		panelBoutonPrecedent = new JPanel();
		panelBoutonPrecedent.setLayout(new BorderLayout());
		panelBoutonPrecedent.setBackground(Color.yellow);
		boutonPrecedent = new JButton("◄");
		panelBoutonPrecedent.add(boutonPrecedent, BorderLayout.CENTER);
		
		return panelBoutonPrecedent;
	}

	/* JPanel contenant les boutons des objets créés */
	private JPanel panelBoutonObjetsCrees() {
		panelBoutonObjetsCrees = new JPanel();
		panelBoutonObjetsCrees.setBackground(Color.green);
		
		return panelBoutonObjetsCrees;
	}

	/* Met à jour les boutons du panelBoutonObjetsCrees */
	private void update_panelBoutonObjetsCrees() {
		
		switch(cmbMessageList.getSelectedItem().toString()) {
			case "Salle" : 
				for(JButton bouton : this.lesBoutonsSalle) {
					panelBoutonObjetsCrees.add(bouton);
				}
				panelBoutonObjetsCrees.updateUI();
				System.out.println("Selectionné : Salle");
				break;

			case "Professeur" : 
				for(JButton bouton : this.lesBoutonsProfesseur) {
					panelBoutonObjetsCrees.add(bouton);
				}
				panelBoutonObjetsCrees.updateUI();
				System.out.println("Selectionné : Professeur");
				break;

			case "Classe" : 
				for(JButton bouton : this.lesBoutonsClasse) {
					panelBoutonObjetsCrees.add(bouton);
				}
				panelBoutonObjetsCrees.updateUI();
				System.out.println("Selectionné : Classe");
				break;

			case "Groupe" : 
				for(JButton bouton : this.lesBoutonsGroupe) {
					panelBoutonObjetsCrees.add(bouton);
				}
				panelBoutonObjetsCrees.updateUI();
				System.out.println("Selectionné : Groupe");
				break;

			case "Eleve" :
				for(JButton bouton : this.lesBoutonsEleve) {
					panelBoutonObjetsCrees.add(bouton);
				}
				panelBoutonObjetsCrees.updateUI();
				System.out.println("Selectionné : Eleve");
				break;

			case "Matiere" : 
				for(JButton bouton : this.lesBoutonsMatiere) {
					panelBoutonObjetsCrees.add(bouton);
				}
				panelBoutonObjetsCrees.updateUI();
				System.out.println("Selectionné : Matiere");
				break;
		}
	}

	/* JPanel contenant le bouton suivant */
	private JPanel panelBoutonSuivant() {
		panelBoutonSuivant = new JPanel();
		panelBoutonSuivant.setLayout(new BorderLayout());
		panelBoutonSuivant.setBackground(Color.black);
		boutonSuivant = new JButton("►");
		panelBoutonSuivant.add(boutonSuivant, BorderLayout.CENTER);
		
		return panelBoutonSuivant;
	}

	/* JPanel qui affichage les objets crées */
	private JPanel cardObjetsCrees() {
		cardObjetsCrees = new JPanel();
		cardObjetsCrees.setLayout(new BorderLayout());
		cardObjetsCrees.add(panelBoutonPrecedent(), BorderLayout.WEST);
		cardObjetsCrees.add(panelBoutonObjetsCrees(), BorderLayout.CENTER);
		cardObjetsCrees.add(panelBoutonSuivant(), BorderLayout.EAST);
		
		return cardObjetsCrees;
	}

	/* JPanel qui contient le JComboBox et les boutons */
	private JPanel panelBoutonCardCreation() {
		panelBoutonCardCreation = new JPanel();
		panelBoutonCardCreation.setBackground(Color.blue);

		String[] messageStrings = {"Salle", "Professeur", "Classe", "Groupe", "Eleve", "Matiere"};
		cmbMessageList = new JComboBox<String>(messageStrings);
		boutonAjouter = new JButton("Ajouter");
		boutonImporter = new JButton("Importer");
		boutonGenerer = new JButton("Génération");

		panelBoutonCardCreation.add(cmbMessageList);
		panelBoutonCardCreation.add(boutonAjouter);
		panelBoutonCardCreation.add(boutonImporter);
		panelBoutonCardCreation.add(boutonGenerer);

		cmbMessageList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				panelBoutonObjetsCrees.removeAll();
				panelBoutonObjetsCrees.validate();
				update_panelBoutonObjetsCrees();
			}
		});
	
		return panelBoutonCardCreation;
	}

	/* JPanel de création des emplois du temps */
	private JPanel cardCreation() {
		cardCreation = new JPanel();
		cardCreation.setLayout(new BorderLayout());
		cardCreation.setBackground(Color.blue);
		cardCreation.add(panelBoutonCardCreation(), BorderLayout.NORTH);
		cardCreation.add(cardObjetsCrees(), BorderLayout.CENTER);
		
		return cardCreation;
	}

	
	/* JPanel qui affiche les emplois du temps crées */
	private JPanel cardEDT() {
		cardEDT = new JPanel();
		cardEDT.setBackground(Color.red);
		String[] typeStrings = {"Salle", "Professeur", "Classe", "Groupe", "Eleve"};
		cmbTypeList = new JComboBox<String>(typeStrings);
		cmbChoixList = new JComboBox<String>();
		cardEDT.add(cmbTypeList);
		cardEDT.add(cmbChoixList);
		
		return cardEDT;
	}
	
	/* JPanel qui contient les boutons en haut */
	private JPanel boutonPane() {
		boutonPane = new JPanel();
		boutonCreation = new JButton("Création");
		boutonCreation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				cardLayout.show(panelGlobal, listContent[0]);
			}
		});
		boutonEDT = new JButton("Emploi du temps");
		boutonEDT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				cardLayout.show(panelGlobal, listContent[1]);
			}
		});
		boutonPane.add(boutonCreation);
		boutonPane.add(boutonEDT);
		
		return boutonPane;
	}
	
	/* JPanel global qui contient cardCreation & cardEDT */
	private JPanel panelGlobal() {
		cardLayout = new CardLayout();
		panelGlobal = new JPanel();
		panelGlobal.setLayout(cardLayout);
		panelGlobal.add(cardCreation(), listContent[0]);
		panelGlobal.add(cardEDT(), listContent[1]);
		
		return panelGlobal;
	}
	
	/* JPanel 'Ajout d'une salle' */
	public JFrame create_frameAjoutSalle() {
		panelAjoutSalle_1 = new JPanel();
		panelAjoutSalle_1.setLayout(new GridLayout(3,2));
		labelNumeroSalle = new JLabel("Numéro :");
		tfNumeroSalle = new JTextField(10);
		labelNombrePlaceSalle = new JLabel("Nombre de places :");
		tfNombrePlaceSalle = new JTextField(10);
		labelTypeSalle = new JLabel("Type de salle :");
		String[] typeSalleStrings = {"CM", "TP", "TD"};
		cmbTypeSalle = new JComboBox<String>(typeSalleStrings);
		panelAjoutSalle_1.add(labelNumeroSalle);
		panelAjoutSalle_1.add(tfNumeroSalle);
		panelAjoutSalle_1.add(labelNombrePlaceSalle);
		panelAjoutSalle_1.add(tfNombrePlaceSalle);
		panelAjoutSalle_1.add(labelTypeSalle);
		panelAjoutSalle_1.add(cmbTypeSalle);

		panelAjoutSalle_2 = new JPanel();
		boutonAccepterSalle = new JButton("✓");
		panelAjoutSalle_2.add(boutonAccepterSalle);
		
		frameAjoutSalle = new JFrame();
		frameAjoutSalle.setSize(400,300);
		frameAjoutSalle.setTitle("Ajout d'une salle");
		frameAjoutSalle.setLocationRelativeTo(null);
		frameAjoutSalle.setResizable(false);
		frameAjoutSalle.setVisible(true);
		frameAjoutSalle.getContentPane().add(panelAjoutSalle_1, BorderLayout.NORTH);
		frameAjoutSalle.getContentPane().add(panelAjoutSalle_2, BorderLayout.CENTER);
		frameAjoutSalle.setVisible(true);
		
		return frameAjoutSalle;
	}
	
	/* JPanel 'Ajout d'un professeur' */
	public JFrame create_frameAjoutProfesseur() {
		panelAjoutProfesseur_1 = new JPanel();
		panelAjoutProfesseur_1.setLayout(new GridLayout(4,2));
		labelNomProfesseur = new JLabel("Nom :");
		tfNomProfesseur = new JTextField(10);
		labelPrenomProfesseur = new JLabel("Prénom :");
		tfPrenomProfesseur = new JTextField(10);
		labelNombreHeureProfesseur = new JLabel("Nombre d'heures :");
		tfNombreHeureProfesseur = new JTextField(10);
		panelAjoutProfesseur_1.add(labelNomProfesseur);
		panelAjoutProfesseur_1.add(tfNomProfesseur);
		panelAjoutProfesseur_1.add(labelPrenomProfesseur);
		panelAjoutProfesseur_1.add(tfPrenomProfesseur);
		panelAjoutProfesseur_1.add(labelNombreHeureProfesseur);
		panelAjoutProfesseur_1.add(tfNombreHeureProfesseur);
		
		panelAjoutProfesseur_2 = new JPanel();
		boutonAccepterProfesseur = new JButton("✓");
		panelAjoutProfesseur_2.add(boutonAccepterProfesseur);
		
		frameAjoutProfesseur = new JFrame();
		frameAjoutProfesseur.setSize(400,300);
		frameAjoutProfesseur.setTitle("Ajout d'un professeur");
		frameAjoutProfesseur.setLocationRelativeTo(null);
		frameAjoutProfesseur.setResizable(false);
		frameAjoutProfesseur.setVisible(true);
		frameAjoutProfesseur.getContentPane().add(panelAjoutProfesseur_1, BorderLayout.NORTH);
		frameAjoutProfesseur.getContentPane().add(panelAjoutProfesseur_2, BorderLayout.SOUTH);
		frameAjoutProfesseur.setVisible(true);
		
		return frameAjoutProfesseur;
	}
		
		
	/* JPanel 'Ajout d'une classe' */
	public JFrame create_frameAjoutClasse() {
		panelAjoutClasse_1 = new JPanel();
		panelAjoutClasse_1.setLayout(new GridLayout(3,2));
		labelNomClasse = new JLabel("Nom :");
		tfNomClasse = new JTextField(10);
		labelNiveauClasse = new JLabel("Niveau :");
		String[] niveauxStrings = {"6 ème", "5 ème", "4 ème", "3 ème"};
		cmbNiveauClasse = new JComboBox<String>(niveauxStrings);
		labelCouleurClasse = new JLabel("Couleur :");
		boutonCouleurClasse = new JButton("Couleur");
		
		panelAjoutClasse_1.add(labelNiveauClasse);
		panelAjoutClasse_1.add(cmbNiveauClasse);
		panelAjoutClasse_1.add(labelNomClasse);
		panelAjoutClasse_1.add(tfNomClasse);
		panelAjoutClasse_1.add(labelCouleurClasse);
		panelAjoutClasse_1.add(boutonCouleurClasse);
		
		panelAjoutClasse_2 = new JPanel();
		boutonAccepterClasse = new JButton("✓");
		panelAjoutClasse_2.add(boutonAccepterClasse);
		
		frameAjoutClasse = new JFrame();
		frameAjoutClasse.setSize(400,300);
		frameAjoutClasse.setTitle("Ajout d'une classe");
		frameAjoutClasse.setLocationRelativeTo(null);
		frameAjoutClasse.setResizable(false);
		frameAjoutClasse.setVisible(true);
		frameAjoutClasse.getContentPane().add(panelAjoutClasse_1, BorderLayout.NORTH);
		frameAjoutClasse.getContentPane().add(panelAjoutClasse_2, BorderLayout.CENTER);
		frameAjoutClasse.setVisible(true);
		
		return frameAjoutClasse;
	}
	
	/* JPanel 'Ajout d'un groupe' */
	public JFrame create_frameAjoutGroupe() {
		panelAjoutGroupe_1 = new JPanel();
		panelAjoutGroupe_1.setLayout(new GridLayout(2,2));
		labelClasseGroupe = new JLabel("Classe :");
		cmbClasseGroupe = new JComboBox<String>();
		labelNomGroupe = new JLabel("Nom :");
		tfNomGroupe = new JTextField(10);
		panelAjoutGroupe_1.add(labelClasseGroupe);
		panelAjoutGroupe_1.add(cmbClasseGroupe);
		panelAjoutGroupe_1.add(labelNomGroupe);
		panelAjoutGroupe_1.add(tfNomGroupe);
		
		panelAjoutGroupe_2 = new JPanel();
		boutonAccepterGroupe = new JButton("✓");
		panelAjoutGroupe_2.add(boutonAccepterGroupe);
		
		frameAjoutGroupe = new JFrame();
		frameAjoutGroupe.setSize(400,300);
		frameAjoutGroupe.setTitle("Ajout d'un groupe");
		frameAjoutGroupe.setLocationRelativeTo(null);
		frameAjoutGroupe.setResizable(false);
		frameAjoutGroupe.setVisible(true);
		frameAjoutGroupe.getContentPane().add(panelAjoutGroupe_1, BorderLayout.NORTH);
		frameAjoutGroupe.getContentPane().add(panelAjoutGroupe_2, BorderLayout.CENTER);
		frameAjoutGroupe.setVisible(true);
		
		return frameAjoutGroupe;
	}
	
	/* JPanel 'Ajout d'un eleve' */
	public JFrame create_frameAjoutEleve() {
		panelAjoutEleve_1 = new JPanel();
		panelAjoutEleve_1.setLayout(new GridLayout(4,2));
		labelNomEleve = new JLabel("Nom :");
		tfNomEleve = new JTextField(10);
		labelPrenomEleve = new JLabel("Prénom :");
		tfPrenomEleve = new JTextField(10);
		labelClasseEleve = new JLabel("Classe :");
		cmbClasseEleve = new JComboBox<String>();
		labelGroupeEleve = new JLabel("Groupe :");
		cmbGroupeEleve = new JComboBox<String>();
		panelAjoutEleve_1.add(labelNomEleve);
		panelAjoutEleve_1.add(tfNomEleve);
		panelAjoutEleve_1.add(labelPrenomEleve);
		panelAjoutEleve_1.add(tfPrenomEleve);
		panelAjoutEleve_1.add(labelClasseEleve);
		panelAjoutEleve_1.add(cmbClasseEleve);
		panelAjoutEleve_1.add(labelGroupeEleve);
		panelAjoutEleve_1.add(cmbGroupeEleve);
		
		panelAjoutEleve_2 = new JPanel();
		boutonAccepterEleve = new JButton("✓");
		panelAjoutEleve_2.add(boutonAccepterEleve);
		
		frameAjoutEleve = new JFrame();
		frameAjoutEleve.setSize(400,300);
		frameAjoutEleve.setTitle("Ajout d'un élève");
		frameAjoutEleve.setLocationRelativeTo(null);
		frameAjoutEleve.setResizable(false);
		frameAjoutEleve.setVisible(true);
		frameAjoutEleve.getContentPane().add(panelAjoutEleve_1, BorderLayout.NORTH);
		frameAjoutEleve.getContentPane().add(panelAjoutEleve_2, BorderLayout.CENTER);
		frameAjoutEleve.setVisible(true);
		
		return frameAjoutEleve;
	}
	
	
	/* JPanel 'Ajout d'une matiere' */  
		public JFrame create_frameAjoutMatiere() {
		panelAjoutMatiere_1 = new JPanel();
		panelAjoutMatiere_1.setLayout(new GridLayout(7,2));
		
		labelNomMatiere = new JLabel("Nom :");
		tfNomMatiere = new JTextField(10);
		labelNbrHCMMatiere = new JLabel("Nombre d'heures de CM :");
		tfNbrHCMMatiere = new JTextField(10);
		labelNbrHTDMatiere = new JLabel("Nombre d'heures de TD :");
		tfNbrHTDMatiere = new JTextField(10);
		labelNbrHTPMatiere = new JLabel("Nombre d'heures de TP :");
		tfNbrHTPMatiere = new JTextField(10);
		labelNiveauMatiere = new JLabel("Niveau :");
		String[] niveauxStrings = {"6 ème", "5 ème", "4 ème", "3 ème"};
		cmbNiveauMatiere = new JComboBox<String>(niveauxStrings);
		labelProfesseurMatiere = new JLabel("Professeur :");
		cmbProfesseurMatiere = new JComboBox<String>();
		labelCouleurMatiere = new JLabel("Couleur :");
		boutonCouleurMatiere = new JButton("Couleur");
		
		panelAjoutMatiere_1.add(labelNomMatiere);
		panelAjoutMatiere_1.add(tfNomMatiere);
		panelAjoutMatiere_1.add(labelNbrHCMMatiere);
		panelAjoutMatiere_1.add(tfNbrHCMMatiere);
		panelAjoutMatiere_1.add(labelNbrHTDMatiere);
		panelAjoutMatiere_1.add(tfNbrHTDMatiere);
		panelAjoutMatiere_1.add(labelNbrHTPMatiere);
		panelAjoutMatiere_1.add(tfNbrHTPMatiere);
		panelAjoutMatiere_1.add(labelNiveauMatiere);
		panelAjoutMatiere_1.add(cmbNiveauMatiere);
		panelAjoutMatiere_1.add(labelProfesseurMatiere);
		panelAjoutMatiere_1.add(cmbProfesseurMatiere);
		panelAjoutMatiere_1.add(labelCouleurMatiere);
		panelAjoutMatiere_1.add(boutonCouleurMatiere);
		
		panelAjoutMatiere_2 = new JPanel();
		boutonAccepterMatiere = new JButton("✓");
		panelAjoutMatiere_2.add(boutonAccepterMatiere);
		
		frameAjoutMatiere = new JFrame();
		frameAjoutMatiere.setSize(400,300);
		frameAjoutMatiere.setTitle("Ajout d'une matière");
		frameAjoutMatiere.setLocationRelativeTo(null);
		frameAjoutMatiere.setResizable(false);
		frameAjoutMatiere.setVisible(true);
		frameAjoutMatiere.getContentPane().add(panelAjoutMatiere_1, BorderLayout.NORTH);
		frameAjoutMatiere.getContentPane().add(panelAjoutMatiere_2, BorderLayout.CENTER);
		frameAjoutMatiere.setVisible(true);
		
		return frameAjoutMatiere;
	}
	
	public void vider_button(String type) {
		switch(type) {
			case "Salle" : 
				lesBoutonsSalle.clear();
				panelBoutonObjetsCrees.removeAll();
				break;

			case "Professeur" : 
				lesBoutonsProfesseur.clear();
				panelBoutonObjetsCrees.removeAll();
				break;

			case "Classe" : 
				lesBoutonsClasse.clear();
				panelBoutonObjetsCrees.removeAll();
				break;

			case "Groupe" : 
				lesBoutonsGroupe.clear();
				panelBoutonObjetsCrees.removeAll();
				break;

			case "Eleve" :
				lesBoutonsEleve.clear();
				panelBoutonObjetsCrees.removeAll();
				break;

			case "Matiere" : 
				lesBoutonsMatiere.clear();
				panelBoutonObjetsCrees.removeAll();
				break;
		}	
	}

	public void create_buttonSalle(boolean visible, String nomSalle) {
		JButton salle = new JButton(nomSalle);
		this.lesBoutonsSalle.add(salle);
		if(visible) { panelBoutonObjetsCrees.add(salle); }
		System.out.println("Nombre de bouton salle : " + lesBoutonsSalle.size());
		panelBoutonObjetsCrees.updateUI();
	}

	public void create_buttonProfesseur(boolean visible, String nomProfesseur, String prenomProfesseur) {
		JButton professeur = new JButton(nomProfesseur + " " + prenomProfesseur);
		this.lesBoutonsProfesseur.add(professeur);
		if(visible) { panelBoutonObjetsCrees.add(professeur); }
		panelBoutonObjetsCrees.updateUI();
	}

	public void create_buttonClasse(boolean visible, String niveauClasse, String nomClasse, Color couleurClasse) {
		JButton classe = new JButton(niveauClasse + " " + nomClasse);
		classe.setBackground(couleurClasse);
		this.lesBoutonsClasse.add(classe);
		if(visible) { panelBoutonObjetsCrees.add(classe); }
		panelBoutonObjetsCrees.updateUI();
	}

	public void create_buttonGroupe(boolean visible, String nomGroupe, String nomClasse) {
		System.out.println("Nom groupe :" + nomGroupe);
		System.out.println("Nom classe : " + nomClasse);
		JButton groupe = new JButton(nomClasse + " - " + nomGroupe);
		this.lesBoutonsGroupe.add(groupe);
		if(visible) { panelBoutonObjetsCrees.add(groupe); }
		panelBoutonObjetsCrees.updateUI();
	}

	public void create_buttonEleve(boolean visible, String nomEleve, String prenomEleve) {
		JButton eleve = new JButton(nomEleve + " " + prenomEleve);
		this.lesBoutonsEleve.add(eleve);
		if(visible) { panelBoutonObjetsCrees.add(eleve); }
		panelBoutonObjetsCrees.updateUI();
	}

	public void create_buttonMatiere(boolean visible, String nomMatiere, Color couleurMatiere) {
		JButton matiere = new JButton(nomMatiere);
		matiere.setBackground(couleurMatiere);
		this.lesBoutonsMatiere.add(matiere);
		if(visible) { panelBoutonObjetsCrees.add(matiere); }
		panelBoutonObjetsCrees.updateUI();
	}



	/* -------------------------------------- */
	/* --- Getteurs JFrame Ajout Salle ------ */
	
	public JFrame getJFrameAjouterSalle() {
		return this.frameAjoutSalle;
	}

	public JButton getBoutonAccepterSalle() {
		return this.boutonAccepterSalle;
	}

	public JTextField getTfNumeroSalle() {
		return this.tfNumeroSalle;
	}

	public JTextField getTfNombrePlaceSalle() {
		return this.tfNombrePlaceSalle;
	}

	public JComboBox<String> getCmbTypeSalle() {
		return this.cmbTypeSalle;
	}

	/* -------------------------------------- */
	/* --- Getteurs JFrame Ajout Professeur - */
	
	public JFrame getJFrameAjouterProfesseur() {
		return this.frameAjoutProfesseur;
	}

	public JButton getBoutonAccepterProfesseur() {
		return this.boutonAccepterProfesseur;
	}

	public JTextField getTfNomProfesseur() {
		return this.tfNomProfesseur;
	}

	public JTextField getTfPrenomProfesseur() {
		return this.tfPrenomProfesseur;
	}

	public JTextField getTfNombreHeureProfesseur() {
		return this.tfNombreHeureProfesseur;
	}

	/* -------------------------------------- */
	/* --- Getteurs JFrame Ajout Classe ----- */
	
	public JFrame getJFrameAjouterClasse() {
		return this.frameAjoutClasse;
	}

	public JButton getBoutonAccepterClasse() {
		return this.boutonAccepterClasse;
	}
	
	public JButton getBoutonCouleurClasse() {
		return this.boutonCouleurClasse;
	}

	public JTextField getTfNomClasse() {
		return this.tfNomClasse;
	}

	public JComboBox<String> getCmbNiveauClasse() {
		return this.cmbNiveauClasse;
	}


	/* -------------------------------------- */
	/* --- Getteurs JFrame Ajout Groupe ----- */
	
	public JFrame getJFrameAjouterGroupe() {
		return this.frameAjoutGroupe;
	}

	public JButton getBoutonAccepterGroupe() {
		return this.boutonAccepterGroupe;
	}

	public JTextField getTfNomGroupe() {
		return this.tfNomGroupe;
	}

	public JComboBox<String> getCmbClasseGroupe() {
		return this.cmbClasseGroupe;
	}

	/* -------------------------------------- */
	/* --- Getteurs JFrame Ajout Eleve ------ */
	
	public JFrame getJFrameAjouterEleve() {
		return this.frameAjoutEleve;
	}

	public JButton getBoutonAccepterEleve() {
		return this.boutonAccepterEleve;
	}

	public JTextField getTfNomEleve() {
		return this.tfNomEleve;
	}

	public JTextField getTfPrenomEleve() {
		return this.tfPrenomEleve;
	}

	public JComboBox<String> getCmbClasseEleve() {
		return this.cmbClasseEleve;
	}

	public JComboBox<String> getCmbGroupeEleve() {
		return this.cmbGroupeEleve;
	}
	
	/* -------------------------------------- */
	/* --- Getteurs JFrame Ajout Matiere ---- */
	
	public JFrame getJFrameAjouterMatiere() {
		return this.frameAjoutSalle;
	}

	public JButton getBoutonAccepterMatiere() {
		return this.boutonAccepterMatiere;
	}

	public JButton getBoutonCouleurMatiere() {
		return this.boutonCouleurMatiere;
	}
	
	public JTextField getTfNomMatiere() {
		return this.tfNomMatiere;
	}

	public JTextField getTfNombreHeureCM() {
		return this.tfNbrHCMMatiere;
	}

	public JTextField getTfNombreHeureTD() {
		return this.tfNbrHTDMatiere;
	}

	public JTextField getTfNombreHeureTP() {
		return this.tfNbrHTPMatiere;
	}

	public JComboBox getCmbProfesseurMatiere() {
		return this.cmbProfesseurMatiere;
	}

	public JComboBox getCmbNiveauMatiere() {
		return this.cmbNiveauMatiere;
	}

	/* -------------------------------------- */
	/* --- Getteurs JFrame general ---------- */
	

	public JButton getBoutonAjouter() {
		return this.boutonAjouter;
	}
	
	public JButton getBoutonImporter() {
		return this.boutonImporter;
	}
	
	public JButton getBoutonCreation() {
		return this.boutonCreation;
	}

	public JButton getBoutonGenerer() {
		return this.boutonGenerer;
	}

	public JButton getBoutonPrecedent() {
		return this.boutonPrecedent;
	}

	public JButton getBoutonSuivant() {
		return this.boutonSuivant;
	}
	
	public void activerBoutonPrecedent() {
		this.boutonPrecedent.setVisible(true);
	}

	public void desactiverBoutonPrecedent() {
		this.boutonPrecedent.setVisible(false);
	}

	public void activerBoutonSuivant() {
		this.boutonSuivant.setVisible(true);
	}

	public void desactiverBoutonSuivant() {
		this.boutonSuivant.setVisible(false);
	}

	public JLabel getLabeltext() {
		return this.text;
	}
	
	public JButton getBoutonEDT() {
		return this.boutonEDT;
	}

	public JLabel getLabelNumero() {
		return this.labelNumeroSalle;
	}
	
	public JTextField getTfNumero() {
		return this.tfNumeroSalle;
	}
	
	public JComboBox<String> getCmbMessageList() {
		return this.cmbMessageList;
	}
	
	public JComboBox<String> getCmbTypeList() {
		return this.cmbTypeList;
	}
	
	public JComboBox<String> getCmbChoixList() {
		return this.cmbChoixList;
	}
	
	public String[] getMessageStrings() {
		return this.messageStrings;
	}
	
	public String[] getTypeStrings() {
		return this.typeStrings;
	}
	
	public String[] getChoixStrings() {
		return this.choixStrings;
	}

	public JPanel getPanelBoutonObjetsCrees() {
		return this.panelBoutonObjetsCrees;
	}
	
}