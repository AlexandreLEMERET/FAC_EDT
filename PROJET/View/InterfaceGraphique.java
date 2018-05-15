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
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import java.util.*;

/* Interface graphique */
@SuppressWarnings("serial")

public class InterfaceGraphique extends JFrame {

	private CardLayout cardLayout;
	
	private JFrame frameAjoutSalle, frameAjoutProfesseur, frameAjoutClasse, frameAjoutGroupe, frameAjoutEleve, frameAjoutMatiere;
	
	private JPanel panelGlobal, panelBoutonCardCreation, cardCreation, cardObjetsCrees, panelBoutonObjetsCrees, panelBoutonPrecedent, panelBoutonSuivant, cardEDT, boutonPane, panelAjoutSalle_1, panelAjoutSalle_2, panelAjoutProfesseur_1, panelAjoutProfesseur_2, panelAjoutClasse_1, panelAjoutClasse_2,
			panelAjoutGroupe_1, panelAjoutGroupe_2, panelAjoutEleve_1, panelAjoutEleve_2, panelAjoutMatiere_1, panelAjoutMatiere_2, panelEDT, panelBoutonCardEDT;
			
	private JTextArea textCreation;
			
	private JButton boutonSauvegarder, boutonPrecedent, boutonSuivant, boutonAjouter, boutonCreation, boutonGenerer, boutonEDT, boutonAccepterSalle, boutonAccepterProfesseur, boutonAccepterClasse, 
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


	
	/* JPanel contenant les boutons des objets créés */
	private JPanel panelBoutonObjetsCrees() {
		panelBoutonObjetsCrees = new JPanel();
		
		return panelBoutonObjetsCrees;
	}

	/* Met à jour les boutons du panelBoutonObjetsCrees */
	public void update_panelBoutonObjetsCrees() {
		
		switch(cmbMessageList.getSelectedItem().toString()) {
			case "Salle" : 
				for(JButton bouton : this.lesBoutonsSalle) {
					panelBoutonObjetsCrees.add(bouton);
				}
				panelBoutonObjetsCrees.updateUI();
				break;

			case "Professeur" : 
				for(JButton bouton : this.lesBoutonsProfesseur) {
					panelBoutonObjetsCrees.add(bouton);
				}
				panelBoutonObjetsCrees.updateUI();
				break;

			case "Classe" : 
				for(JButton bouton : this.lesBoutonsClasse) {
					panelBoutonObjetsCrees.add(bouton);
				}
				panelBoutonObjetsCrees.updateUI();
				break;

			case "Groupe" : 
				for(JButton bouton : this.lesBoutonsGroupe) {
					panelBoutonObjetsCrees.add(bouton);
				}
				panelBoutonObjetsCrees.updateUI();
				break;

			case "Eleve" :
				for(JButton bouton : this.lesBoutonsEleve) {
					panelBoutonObjetsCrees.add(bouton);
				}
				panelBoutonObjetsCrees.updateUI();
				break;

			case "Matiere" : 
				for(JButton bouton : this.lesBoutonsMatiere) {
					panelBoutonObjetsCrees.add(bouton);
				}
				panelBoutonObjetsCrees.updateUI();
				break;
		}
	}

	
	/* JPanel qui affichage les objets crées */
	private JPanel cardObjetsCrees() {
		cardObjetsCrees = new JPanel();
		cardObjetsCrees.setLayout(new BorderLayout());
		cardObjetsCrees.add(panelBoutonObjetsCrees(), BorderLayout.CENTER);
		cardObjetsCrees.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
		
		return cardObjetsCrees;
	}

	/* JPanel qui contient le JComboBox et les boutons */
	private JPanel panelBoutonCardCreation() {
		panelBoutonCardCreation = new JPanel();

		String[] messageStrings = {"Salle", "Professeur", "Classe", "Groupe", "Eleve", "Matiere"};
		cmbMessageList = new JComboBox<String>(messageStrings);
		boutonAjouter = new JButton("Ajouter");
		boutonImporter = new JButton("Importer");
		boutonSauvegarder = new JButton("Sauvegarder");
		boutonGenerer = new JButton("Génération");

		panelBoutonCardCreation.add(cmbMessageList);
		panelBoutonCardCreation.add(boutonAjouter);
		panelBoutonCardCreation.add(boutonImporter);
		panelBoutonCardCreation.add(boutonSauvegarder);
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
		cardCreation.add(panelBoutonCardCreation(), BorderLayout.NORTH);
		cardCreation.add(cardObjetsCrees(), BorderLayout.CENTER);
		
		return cardCreation;
	}

	/* JPanel des EDT sur CardEDT */
	private JPanel panelEDT(){

		panelEDT = new JPanel();
		panelEDT.setLayout(new GridLayout(5,6));
		panelEDT.setBackground(Color.white);



		 // PREMIERE LIGNE DE GAUCHE A DROITE

		 JLabel label1 = new JLabel("/", SwingConstants.CENTER);
		 JPanel panel1 = new JPanel();
		 panel1.setLayout(new BorderLayout());
		 panel1.setBackground(Color.white);
		 //												Haut, Gauche, Bas, Droite
		 panel1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		 panel1.add(label1, BorderLayout.CENTER);

		 JLabel label2 = new JLabel("LUNDI", SwingConstants.CENTER);
		 JPanel panel2 = new JPanel();
		 panel2.setLayout(new BorderLayout());
		 panel2.setBackground(Color.white);
		 panel2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		 panel2.add(label2, BorderLayout.CENTER);

		 JLabel label3 = new JLabel("MARDI", SwingConstants.CENTER);
		 JPanel panel3 = new JPanel();
		 panel3.setLayout(new BorderLayout());
		 panel3.setBackground(Color.white);
		 panel3.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		 panel3.add(label3, BorderLayout.CENTER);

		 JLabel label4 = new JLabel("MERCREDI", SwingConstants.CENTER);
		 JPanel panel4 = new JPanel();
		 panel4.setLayout(new BorderLayout());
		 panel4.setBackground(Color.white);
		 panel4.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		 panel4.add(label4, BorderLayout.CENTER);

		 JLabel label5 = new JLabel("JEUDI", SwingConstants.CENTER);
		 JPanel panel5 = new JPanel();
		 panel5.setLayout(new BorderLayout());
		 panel5.setBackground(Color.white);
		 panel5.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		 panel5.add(label5, BorderLayout.CENTER);

		 JLabel label6 = new JLabel("VENDREDI", SwingConstants.CENTER);
		 JPanel panel6 = new JPanel();
		 panel6.setLayout(new BorderLayout());
		 panel6.setBackground(Color.white);
		 panel6.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		 panel6.add(label6, BorderLayout.CENTER);

         // DEUXIEME LIGNE DE GAUCHE A DROITE

         JLabel label7 = new JLabel("8h - 10h", SwingConstants.CENTER);
		 JPanel panel7 = new JPanel();
		 panel7.setLayout(new BorderLayout());
		 panel7.setBackground(Color.white);
		 panel7.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		 panel7.add(label7, BorderLayout.CENTER);

		 JLabel label8 = new JLabel("", SwingConstants.CENTER);
		 JPanel panel8 = new JPanel();
		 panel8.setLayout(new BorderLayout());
		 panel8.setBackground(Color.white);
		 panel8.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		 panel8.add(label8, BorderLayout.CENTER);

		 JLabel label9 = new JLabel("", SwingConstants.CENTER);
		 JPanel panel9 = new JPanel();
		 panel9.setLayout(new BorderLayout());
		 panel9.setBackground(Color.white);
		 panel9.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		 panel9.add(label9, BorderLayout.CENTER);

		 JLabel label10 = new JLabel("", SwingConstants.CENTER);
		 JPanel panel10 = new JPanel();
		 panel10.setLayout(new BorderLayout());
		 panel10.setBackground(Color.white);
		 panel10.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		 panel10.add(label10, BorderLayout.CENTER);

		 JLabel label11 = new JLabel("", SwingConstants.CENTER);
		 JPanel panel11 = new JPanel();
		 panel11.setLayout(new BorderLayout());
		 panel11.setBackground(Color.white);
		 panel11.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		 panel11.add(label11, BorderLayout.CENTER);

		 JLabel label12 = new JLabel("", SwingConstants.CENTER);
		 JPanel panel12 = new JPanel();
		 panel12.setLayout(new BorderLayout());
		 panel12.setBackground(Color.white);
		 panel12.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		 panel12.add(label12, BorderLayout.CENTER);

         // TROISIEME LIGNE DE GAUCHE A DROITE 

         JLabel label13 = new JLabel("10h - 12h", SwingConstants.CENTER);
		 JPanel panel13 = new JPanel();
		 panel13.setLayout(new BorderLayout());
		 panel13.setBackground(Color.white);
		 panel13.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		 panel13.add(label13, BorderLayout.CENTER);

		 JLabel label14 = new JLabel("", SwingConstants.CENTER);
		 JPanel panel14 = new JPanel();
		 panel14.setLayout(new BorderLayout());
		 panel14.setBackground(Color.white);
		 panel14.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		 panel14.add(label14, BorderLayout.CENTER);

		 JLabel label15 = new JLabel("", SwingConstants.CENTER);
		 JPanel panel15 = new JPanel();
		 panel15.setLayout(new BorderLayout());
		 panel15.setBackground(Color.white);
		 panel15.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		 panel15.add(label15, BorderLayout.CENTER);

		 JLabel label16 = new JLabel("", SwingConstants.CENTER);
		 JPanel panel16 = new JPanel();
		 panel16.setLayout(new BorderLayout());
		 panel16.setBackground(Color.white);
		 panel16.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		 panel16.add(label16, BorderLayout.CENTER);

		 JLabel label17 = new JLabel("", SwingConstants.CENTER);
		 JPanel panel17 = new JPanel();
		 panel17.setLayout(new BorderLayout());
		 panel17.setBackground(Color.white);
		 panel17.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		 panel17.add(label17, BorderLayout.CENTER);

		 JLabel label18 = new JLabel("", SwingConstants.CENTER);
		 JPanel panel18 = new JPanel();
		 panel18.setLayout(new BorderLayout());
		 panel18.setBackground(Color.white);
		 panel18.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		 panel18.add(label18, BorderLayout.CENTER);

         // QUATRIEME LIGNE DE GAUCHE A DROITE //

         JLabel label19 = new JLabel("12h - 14h", SwingConstants.CENTER);
		 JPanel panel19 = new JPanel();
		 panel19.setLayout(new BorderLayout());
		 panel19.setBackground(Color.white);
		 panel19.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		 panel19.add(label19, BorderLayout.CENTER);

		 JLabel label20 = new JLabel("", SwingConstants.CENTER);
		 JPanel panel20 = new JPanel();
		 panel20.setLayout(new BorderLayout());
		 panel20.setBackground(Color.white);
		 panel20.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		 panel20.add(label20, BorderLayout.CENTER);

		 JLabel label21 = new JLabel("", SwingConstants.CENTER);
		 JPanel panel21 = new JPanel();
		 panel21.setLayout(new BorderLayout());
		 panel21.setBackground(Color.white);
		 panel21.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		 panel21.add(label21, BorderLayout.CENTER);

		 JLabel label22 = new JLabel("", SwingConstants.CENTER);
		 JPanel panel22 = new JPanel();
		 panel22.setLayout(new BorderLayout());
		 panel22.setBackground(Color.white);
		 panel22.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		 panel22.add(label22, BorderLayout.CENTER);

		 JLabel label23 = new JLabel("", SwingConstants.CENTER);
		 JPanel panel23 = new JPanel();
		 panel23.setLayout(new BorderLayout());
		 panel23.setBackground(Color.white);
		 panel23.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		 panel23.add(label23, BorderLayout.CENTER);

		 JLabel label24 = new JLabel("", SwingConstants.CENTER);
		 JPanel panel24 = new JPanel();
		 panel24.setLayout(new BorderLayout());
		 panel24.setBackground(Color.white);
		 panel24.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		 panel24.add(label24, BorderLayout.CENTER);

         // CINQUIEME LIGNE DE GAUCHE A DROITE 

         JLabel label25 = new JLabel("14h - 16h", SwingConstants.CENTER);
		 JPanel panel25 = new JPanel();
		 panel25.setLayout(new BorderLayout());
		 panel25.setBackground(Color.white);
		 panel25.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		 panel25.add(label25, BorderLayout.CENTER);

		 JLabel label26 = new JLabel("", SwingConstants.CENTER);
		 JPanel panel26 = new JPanel();
		 panel26.setLayout(new BorderLayout());
		 panel26.setBackground(Color.white);
		 panel26.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		 panel26.add(label26, BorderLayout.CENTER);

		 JLabel label27 = new JLabel("", SwingConstants.CENTER);
		 JPanel panel27 = new JPanel();
		 panel27.setLayout(new BorderLayout());
		 panel27.setBackground(Color.white);
		 panel27.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		 panel27.add(label27, BorderLayout.CENTER);

		 JLabel label28 = new JLabel("", SwingConstants.CENTER);
		 JPanel panel28 = new JPanel();
		 panel28.setLayout(new BorderLayout());
		 panel28.setBackground(Color.white);
		 panel28.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		 panel28.add(label28, BorderLayout.CENTER);

		 JLabel label29 = new JLabel("", SwingConstants.CENTER);
		 JPanel panel29 = new JPanel();
		 panel29.setLayout(new BorderLayout());
		 panel29.setBackground(Color.white);
		 panel29.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		 panel29.add(label29, BorderLayout.CENTER);

		 JLabel label30 = new JLabel("", SwingConstants.CENTER);
		 JPanel panel30 = new JPanel();
		 panel30.setLayout(new BorderLayout());
		 panel30.setBackground(Color.white);
		 panel30.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
		 panel30.add(label30, BorderLayout.CENTER);



		 // Les Adds 

         panelEDT.add(panel1);
		 panelEDT.add(panel2);
		 panelEDT.add(panel3);
		 panelEDT.add(panel4);
		 panelEDT.add(panel5);
		 panelEDT.add(panel6);
		 panelEDT.add(panel7);
		 panelEDT.add(panel8);
		 panelEDT.add(panel9);
		 panelEDT.add(panel10);
		 panelEDT.add(panel11);
		 panelEDT.add(panel12);
		 panelEDT.add(panel13);
		 panelEDT.add(panel14);
		 panelEDT.add(panel15);
		 panelEDT.add(panel16);
		 panelEDT.add(panel17);
		 panelEDT.add(panel18);
		 panelEDT.add(panel19);
		 panelEDT.add(panel20);
		 panelEDT.add(panel21);
		 panelEDT.add(panel22);
		 panelEDT.add(panel23);
		 panelEDT.add(panel24);
		 panelEDT.add(panel25);
		 panelEDT.add(panel26);
		 panelEDT.add(panel27);
		 panelEDT.add(panel28);
		 panelEDT.add(panel29);
		 panelEDT.add(panel30);
		

		return panelEDT;
		
	}

	/* EDT vide */
	public void panelEDTblanc() {
		this.cardEDT.remove(panelEDT);
		this.cardEDT.updateUI();
		
		panelEDT = new JPanel();
		panelEDT.setLayout(new GridLayout(5,6));
		panelEDT.setBackground(Color.white);



		 // PREMIERE LIGNE DE GAUCHE A DROITE

		 JLabel label1 = new JLabel("/", SwingConstants.CENTER);
		 JPanel panel1 = new JPanel();
		 panel1.setLayout(new BorderLayout());
		 panel1.setBackground(Color.white);
		 //												Haut, Gauche, Bas, Droite
		 panel1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		 panel1.add(label1, BorderLayout.CENTER);

		 JLabel label2 = new JLabel("LUNDI", SwingConstants.CENTER);
		 JPanel panel2 = new JPanel();
		 panel2.setLayout(new BorderLayout());
		 panel2.setBackground(Color.white);
		 panel2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		 panel2.add(label2, BorderLayout.CENTER);

		 JLabel label3 = new JLabel("MARDI", SwingConstants.CENTER);
		 JPanel panel3 = new JPanel();
		 panel3.setLayout(new BorderLayout());
		 panel3.setBackground(Color.white);
		 panel3.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		 panel3.add(label3, BorderLayout.CENTER);

		 JLabel label4 = new JLabel("MERCREDI", SwingConstants.CENTER);
		 JPanel panel4 = new JPanel();
		 panel4.setLayout(new BorderLayout());
		 panel4.setBackground(Color.white);
		 panel4.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		 panel4.add(label4, BorderLayout.CENTER);

		 JLabel label5 = new JLabel("JEUDI", SwingConstants.CENTER);
		 JPanel panel5 = new JPanel();
		 panel5.setLayout(new BorderLayout());
		 panel5.setBackground(Color.white);
		 panel5.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		 panel5.add(label5, BorderLayout.CENTER);

		 JLabel label6 = new JLabel("VENDREDI", SwingConstants.CENTER);
		 JPanel panel6 = new JPanel();
		 panel6.setLayout(new BorderLayout());
		 panel6.setBackground(Color.white);
		 panel6.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		 panel6.add(label6, BorderLayout.CENTER);

         // DEUXIEME LIGNE DE GAUCHE A DROITE

         JLabel label7 = new JLabel("8h - 10h", SwingConstants.CENTER);
		 JPanel panel7 = new JPanel();
		 panel7.setLayout(new BorderLayout());
		 panel7.setBackground(Color.white);
		 panel7.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		 panel7.add(label7, BorderLayout.CENTER);

		 JLabel label8 = new JLabel("", SwingConstants.CENTER);
		 JPanel panel8 = new JPanel();
		 panel8.setLayout(new BorderLayout());
		 panel8.setBackground(Color.white);
		 panel8.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		 panel8.add(label8, BorderLayout.CENTER);

		 JLabel label9 = new JLabel("", SwingConstants.CENTER);
		 JPanel panel9 = new JPanel();
		 panel9.setLayout(new BorderLayout());
		 panel9.setBackground(Color.white);
		 panel9.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		 panel9.add(label9, BorderLayout.CENTER);

		 JLabel label10 = new JLabel("", SwingConstants.CENTER);
		 JPanel panel10 = new JPanel();
		 panel10.setLayout(new BorderLayout());
		 panel10.setBackground(Color.white);
		 panel10.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		 panel10.add(label10, BorderLayout.CENTER);

		 JLabel label11 = new JLabel("", SwingConstants.CENTER);
		 JPanel panel11 = new JPanel();
		 panel11.setLayout(new BorderLayout());
		 panel11.setBackground(Color.white);
		 panel11.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		 panel11.add(label11, BorderLayout.CENTER);

		 JLabel label12 = new JLabel("", SwingConstants.CENTER);
		 JPanel panel12 = new JPanel();
		 panel12.setLayout(new BorderLayout());
		 panel12.setBackground(Color.white);
		 panel12.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		 panel12.add(label12, BorderLayout.CENTER);

         // TROISIEME LIGNE DE GAUCHE A DROITE 

         JLabel label13 = new JLabel("10h - 12h", SwingConstants.CENTER);
		 JPanel panel13 = new JPanel();
		 panel13.setLayout(new BorderLayout());
		 panel13.setBackground(Color.white);
		 panel13.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		 panel13.add(label13, BorderLayout.CENTER);

		 JLabel label14 = new JLabel("", SwingConstants.CENTER);
		 JPanel panel14 = new JPanel();
		 panel14.setLayout(new BorderLayout());
		 panel14.setBackground(Color.white);
		 panel14.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		 panel14.add(label14, BorderLayout.CENTER);

		 JLabel label15 = new JLabel("", SwingConstants.CENTER);
		 JPanel panel15 = new JPanel();
		 panel15.setLayout(new BorderLayout());
		 panel15.setBackground(Color.white);
		 panel15.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		 panel15.add(label15, BorderLayout.CENTER);

		 JLabel label16 = new JLabel("", SwingConstants.CENTER);
		 JPanel panel16 = new JPanel();
		 panel16.setLayout(new BorderLayout());
		 panel16.setBackground(Color.white);
		 panel16.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		 panel16.add(label16, BorderLayout.CENTER);

		 JLabel label17 = new JLabel("", SwingConstants.CENTER);
		 JPanel panel17 = new JPanel();
		 panel17.setLayout(new BorderLayout());
		 panel17.setBackground(Color.white);
		 panel17.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		 panel17.add(label17, BorderLayout.CENTER);

		 JLabel label18 = new JLabel("", SwingConstants.CENTER);
		 JPanel panel18 = new JPanel();
		 panel18.setLayout(new BorderLayout());
		 panel18.setBackground(Color.white);
		 panel18.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		 panel18.add(label18, BorderLayout.CENTER);

         // QUATRIEME LIGNE DE GAUCHE A DROITE //

         JLabel label19 = new JLabel("14h - 16h", SwingConstants.CENTER);
		 JPanel panel19 = new JPanel();
		 panel19.setLayout(new BorderLayout());
		 panel19.setBackground(Color.white);
		 panel19.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		 panel19.add(label19, BorderLayout.CENTER);

		 JLabel label20 = new JLabel("", SwingConstants.CENTER);
		 JPanel panel20 = new JPanel();
		 panel20.setLayout(new BorderLayout());
		 panel20.setBackground(Color.white);
		 panel20.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		 panel20.add(label20, BorderLayout.CENTER);

		 JLabel label21 = new JLabel("", SwingConstants.CENTER);
		 JPanel panel21 = new JPanel();
		 panel21.setLayout(new BorderLayout());
		 panel21.setBackground(Color.white);
		 panel21.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		 panel21.add(label21, BorderLayout.CENTER);

		 JLabel label22 = new JLabel("", SwingConstants.CENTER);
		 JPanel panel22 = new JPanel();
		 panel22.setLayout(new BorderLayout());
		 panel22.setBackground(Color.white);
		 panel22.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		 panel22.add(label22, BorderLayout.CENTER);

		 JLabel label23 = new JLabel("", SwingConstants.CENTER);
		 JPanel panel23 = new JPanel();
		 panel23.setLayout(new BorderLayout());
		 panel23.setBackground(Color.white);
		 panel23.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		 panel23.add(label23, BorderLayout.CENTER);

		 JLabel label24 = new JLabel("", SwingConstants.CENTER);
		 JPanel panel24 = new JPanel();
		 panel24.setLayout(new BorderLayout());
		 panel24.setBackground(Color.white);
		 panel24.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		 panel24.add(label24, BorderLayout.CENTER);

         // CINQUIEME LIGNE DE GAUCHE A DROITE 

         JLabel label25 = new JLabel("16h - 18h", SwingConstants.CENTER);
		 JPanel panel25 = new JPanel();
		 panel25.setLayout(new BorderLayout());
		 panel25.setBackground(Color.white);
		 panel25.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		 panel25.add(label25, BorderLayout.CENTER);

		 JLabel label26 = new JLabel("", SwingConstants.CENTER);
		 JPanel panel26 = new JPanel();
		 panel26.setLayout(new BorderLayout());
		 panel26.setBackground(Color.white);
		 panel26.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		 panel26.add(label26, BorderLayout.CENTER);

		 JLabel label27 = new JLabel("", SwingConstants.CENTER);
		 JPanel panel27 = new JPanel();
		 panel27.setLayout(new BorderLayout());
		 panel27.setBackground(Color.white);
		 panel27.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		 panel27.add(label27, BorderLayout.CENTER);

		 JLabel label28 = new JLabel("", SwingConstants.CENTER);
		 JPanel panel28 = new JPanel();
		 panel28.setLayout(new BorderLayout());
		 panel28.setBackground(Color.white);
		 panel28.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		 panel28.add(label28, BorderLayout.CENTER);

		 JLabel label29 = new JLabel("", SwingConstants.CENTER);
		 JPanel panel29 = new JPanel();
		 panel29.setLayout(new BorderLayout());
		 panel29.setBackground(Color.white);
		 panel29.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		 panel29.add(label29, BorderLayout.CENTER);

		 JLabel label30 = new JLabel("", SwingConstants.CENTER);
		 JPanel panel30 = new JPanel();
		 panel30.setLayout(new BorderLayout());
		 panel30.setBackground(Color.white);
		 panel30.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
		 panel30.add(label30, BorderLayout.CENTER);



		 // Les Adds 

         panelEDT.add(panel1);
		 panelEDT.add(panel2);
		 panelEDT.add(panel3);
		 panelEDT.add(panel4);
		 panelEDT.add(panel5);
		 panelEDT.add(panel6);
		 panelEDT.add(panel7);
		 panelEDT.add(panel8);
		 panelEDT.add(panel9);
		 panelEDT.add(panel10);
		 panelEDT.add(panel11);
		 panelEDT.add(panel12);
		 panelEDT.add(panel13);
		 panelEDT.add(panel14);
		 panelEDT.add(panel15);
		 panelEDT.add(panel16);
		 panelEDT.add(panel17);
		 panelEDT.add(panel18);
		 panelEDT.add(panel19);
		 panelEDT.add(panel20);
		 panelEDT.add(panel21);
		 panelEDT.add(panel22);
		 panelEDT.add(panel23);
		 panelEDT.add(panel24);
		 panelEDT.add(panel25);
		 panelEDT.add(panel26);
		 panelEDT.add(panel27);
		 panelEDT.add(panel28);
		 panelEDT.add(panel29);
		 panelEDT.add(panel30);
		

		this.cardEDT.add(panelEDT, BorderLayout.CENTER);
		this.cardEDT.updateUI();
		
	}

	/* Update EDT pour le chargement de chaque EDT */
	public void updateCardEDT(String type, Jour p_jour1, Jour p_jour2, Jour p_jour3, Jour p_jour4, Jour p_jour5) {
		this.cardEDT.remove(panelEDT);
		this.cardEDT.updateUI();

		panelEDT = new JPanel();
		panelEDT.setLayout(new GridLayout(5,6));
		panelEDT.setBackground(Color.white);
		JLabel label1, label2, label3, label4, label5, label6, label7, label8, label8_1, label9, label10, label11, label12, label13, label14, label15, label16, label17, label18, label19, label20, label21, label22, label23, label24, label25, label26, label27, label28, label29, label30;
		JPanel panel1 = new JPanel(), panel2 = new JPanel(), panel3 = new JPanel(), panel4 = new JPanel(), panel5 = new JPanel(), panel6 = new JPanel(), panel7 = new JPanel(), panel8 = new JPanel(), panel8_1 = new JPanel(), panel8_2 = new JPanel(), panel9 = new JPanel(), panel10 = new JPanel(), panel11 = new JPanel(), panel12 = new JPanel(), panel13 = new JPanel(), panel14 = new JPanel(), panel15 = new JPanel(), panel16 = new JPanel(), panel17 = new JPanel(), panel18 = new JPanel(), panel19 = new JPanel(), panel20 = new JPanel(), panel21 = new JPanel(), panel22 = new JPanel(), panel23 = new JPanel(), panel24 = new JPanel(), panel25 = new JPanel(), panel26 = new JPanel(), panel27 = new JPanel(), panel28 = new JPanel(), panel29 = new JPanel(), panel30 = new JPanel();
		
		switch(type) {

				case "Salle":

					// Premiere ligne 

					label1 = new JLabel("/", SwingConstants.CENTER);
					panel1 = new JPanel();
					panel1.setLayout(new BorderLayout());
					panel1.setBackground(Color.white);
					panel1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel1.add(label1, BorderLayout.CENTER);

					label2 = new JLabel("LUNDI", SwingConstants.CENTER);
					panel2 = new JPanel();
					panel2.setLayout(new BorderLayout());
					panel2.setBackground(Color.white);
					panel2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel2.add(label2, BorderLayout.CENTER);

					label3 = new JLabel("MARDI", SwingConstants.CENTER);
					panel3 = new JPanel();
					panel3.setLayout(new BorderLayout());
					panel3.setBackground(Color.white);
					panel3.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel3.add(label3, BorderLayout.CENTER);

					label4 = new JLabel("MERCREDI", SwingConstants.CENTER);
					panel4 = new JPanel();
					panel4.setLayout(new BorderLayout());
					panel4.setBackground(Color.white);
					panel4.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel4.add(label4, BorderLayout.CENTER);

					label5 = new JLabel("JEUDI", SwingConstants.CENTER);
					panel5 = new JPanel();
					panel5.setLayout(new BorderLayout());
					panel5.setBackground(Color.white);
					panel5.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel5.add(label5, BorderLayout.CENTER);

					label6 = new JLabel("VENDREDI", SwingConstants.CENTER);
					panel6 = new JPanel();
					panel6.setLayout(new BorderLayout());
					panel6.setBackground(Color.white);
					panel6.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
					panel6.add(label6, BorderLayout.CENTER);


					// DEUXIEME LIGNE DE GAUCHE A DROITE 

			        label7 = new JLabel("8h - 10h", SwingConstants.CENTER);
					panel7 = new JPanel();
					panel7.setLayout(new BorderLayout());
					panel7.setBackground(Color.white);
					panel7.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel7.add(label7, BorderLayout.CENTER);

					label8 = new JLabel();
					if(p_jour1.getLesCours().get(0).getMatiere() == null) { label8 = new JLabel("Libre", SwingConstants.CENTER); } else { label8 = new JLabel("<html> <center>" + p_jour1.getLesCours().get(0).getClasse().getNiveauClasse() + " " + p_jour1.getLesCours().get(0).getClasse().getNomClasse() + " <br><br> " + p_jour1.getLesCours().get(0).getMatiere().getNomMatiere() + " <br><br> " + p_jour1.getLesCours().get(0).getProfesseur().getNomProfesseur() + "</center> </html>", SwingConstants.CENTER);}
					panel8 = new JPanel();
					panel8.setLayout(new BorderLayout());
					if(p_jour1.getLesCours().get(0).getMatiere() == null) { panel8.setBackground(Color.white); } else { panel8.setBackground(p_jour1.getLesCours().get(0).getClasse().getCouleurClasse()); }
					panel8.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel8.add(label8, BorderLayout.CENTER);

					label9 = new JLabel();
					if(p_jour2.getLesCours().get(0).getMatiere() == null) { label9 = new JLabel("Libre", SwingConstants.CENTER); } else { label9 = new JLabel("<html> <center>" + p_jour2.getLesCours().get(0).getClasse().getNiveauClasse() + " " + p_jour2.getLesCours().get(0).getClasse().getNomClasse() + " <br><br> " + p_jour2.getLesCours().get(0).getMatiere().getNomMatiere() + " <br><br> " + p_jour2.getLesCours().get(0).getProfesseur().getNomProfesseur() + "</center> </html>", SwingConstants.CENTER); }
					panel9 = new JPanel();
					panel9.setLayout(new BorderLayout());
					if(p_jour2.getLesCours().get(0).getMatiere() == null) { panel9.setBackground(Color.white); } else { panel9.setBackground(p_jour2.getLesCours().get(0).getClasse().getCouleurClasse()); }
					panel9.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel9.add(label9, BorderLayout.CENTER);

					label10 = new JLabel();
					if(p_jour3.getLesCours().get(0).getMatiere() == null) { label10 = new JLabel("Libre", SwingConstants.CENTER); } else { label10 = new JLabel("<html> <center>" + p_jour3.getLesCours().get(0).getClasse().getNiveauClasse() + " " + p_jour3.getLesCours().get(0).getClasse().getNomClasse() + " <br><br> " + p_jour3.getLesCours().get(0).getMatiere().getNomMatiere() + " <br><br> " + p_jour3.getLesCours().get(0).getProfesseur().getNomProfesseur() + "</center> </html>", SwingConstants.CENTER); }
					panel10 = new JPanel();
					panel10.setLayout(new BorderLayout());
					if(p_jour3.getLesCours().get(0).getMatiere() == null) { panel10.setBackground(Color.white); } else { panel10.setBackground(p_jour3.getLesCours().get(0).getClasse().getCouleurClasse()); }
					panel10.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel10.add(label10, BorderLayout.CENTER);

					label11 = new JLabel();
					if(p_jour4.getLesCours().get(0).getMatiere() == null) { label11 = new JLabel("Libre", SwingConstants.CENTER); } else { label11 = new JLabel("<html> <center>" + p_jour4.getLesCours().get(0).getClasse().getNiveauClasse() + " " + p_jour4.getLesCours().get(0).getClasse().getNomClasse() + " <br><br> " + p_jour4.getLesCours().get(0).getMatiere().getNomMatiere() + " <br><br> " + p_jour4.getLesCours().get(0).getProfesseur().getNomProfesseur() + "</center> </html>", SwingConstants.CENTER); }
					panel11 = new JPanel();
					panel11.setLayout(new BorderLayout());
					if(p_jour4.getLesCours().get(0).getMatiere() == null) { panel11.setBackground(Color.white); } else { panel11.setBackground(p_jour4.getLesCours().get(0).getClasse().getCouleurClasse()); }
					panel11.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel11.add(label11, BorderLayout.CENTER);

					label12 = new JLabel();
					if(p_jour5.getLesCours().get(0).getMatiere() == null) { label12 = new JLabel("Libre", SwingConstants.CENTER); } else { label12 = new JLabel("<html> <center>" + p_jour5.getLesCours().get(0).getClasse().getNiveauClasse() + " " + p_jour5.getLesCours().get(0).getClasse().getNomClasse() + " <br><br> " + p_jour5.getLesCours().get(0).getMatiere().getNomMatiere() + " <br><br> " + p_jour5.getLesCours().get(0).getProfesseur().getNomProfesseur() + "</center> </html>", SwingConstants.CENTER); }
					panel12 = new JPanel();
					panel12.setLayout(new BorderLayout());
					if(p_jour5.getLesCours().get(0).getMatiere() == null) { panel12.setBackground(Color.white); } else { panel12.setBackground(p_jour5.getLesCours().get(0).getClasse().getCouleurClasse()); }
					panel12.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
					panel12.add(label12, BorderLayout.CENTER);

					// TROISIEME LIGNE DE GAUCHE A DROITE 

			        label13 = new JLabel("10h - 12h", SwingConstants.CENTER);
					panel13 = new JPanel();
					panel13.setLayout(new BorderLayout());
					panel13.setBackground(Color.white);
					panel13.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel13.add(label13, BorderLayout.CENTER);

					label14 = new JLabel();
					if(p_jour1.getLesCours().get(1).getMatiere() == null) { label14 = new JLabel("Libre", SwingConstants.CENTER); } else { label14 = new JLabel("<html> <center>" + p_jour1.getLesCours().get(1).getClasse().getNiveauClasse() + " " + p_jour1.getLesCours().get(1).getClasse().getNomClasse() + " <br><br> " + p_jour1.getLesCours().get(1).getMatiere().getNomMatiere() + " <br><br> " + p_jour1.getLesCours().get(1).getProfesseur().getNomProfesseur() + "</center> </html>", SwingConstants.CENTER); }
					panel14 = new JPanel();
					panel14.setLayout(new BorderLayout());
					if(p_jour1.getLesCours().get(1).getMatiere() == null) { panel14.setBackground(Color.white); } else { panel14.setBackground(p_jour1.getLesCours().get(1).getClasse().getCouleurClasse()); }
					panel14.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel14.add(label14, BorderLayout.CENTER);

					label15 = new JLabel();
					if(p_jour2.getLesCours().get(1).getMatiere() == null) { label15 = new JLabel("Libre", SwingConstants.CENTER); } else { label15 = new JLabel("<html> <center>" + p_jour2.getLesCours().get(1).getClasse().getNiveauClasse() + " " + p_jour2.getLesCours().get(1).getClasse().getNomClasse() + " <br><br> " + p_jour2.getLesCours().get(1).getMatiere().getNomMatiere() + " <br><br> " + p_jour2.getLesCours().get(1).getProfesseur().getNomProfesseur() + "</center> </html>", SwingConstants.CENTER); }
					panel15 = new JPanel();
					panel15.setLayout(new BorderLayout());
					if(p_jour2.getLesCours().get(1).getMatiere() == null) { panel15.setBackground(Color.white); } else { panel15.setBackground(p_jour2.getLesCours().get(1).getClasse().getCouleurClasse()); }
					panel15.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel15.add(label15, BorderLayout.CENTER);

					label16 = new JLabel();
					if(p_jour3.getLesCours().get(1).getMatiere() == null) { label16 = new JLabel("Libre", SwingConstants.CENTER); } else { label16 = new JLabel("<html> <center>" + p_jour3.getLesCours().get(1).getClasse().getNiveauClasse() + " " + p_jour3.getLesCours().get(1).getClasse().getNomClasse() + " <br><br> " + p_jour3.getLesCours().get(1).getMatiere().getNomMatiere() + " <br><br> " + p_jour3.getLesCours().get(1).getProfesseur().getNomProfesseur() + "</center> </html>", SwingConstants.CENTER); }
					panel16 = new JPanel();
					panel16.setLayout(new BorderLayout());
					if(p_jour3.getLesCours().get(1).getMatiere() == null) { panel16.setBackground(Color.white); } else { panel16.setBackground(p_jour3.getLesCours().get(1).getClasse().getCouleurClasse()); }
					panel16.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel16.add(label16, BorderLayout.CENTER);

					label17 = new JLabel();
					if(p_jour4.getLesCours().get(1).getMatiere() == null) { label17 = new JLabel("Libre", SwingConstants.CENTER); } else { label17 = new JLabel("<html> <center>" + p_jour4.getLesCours().get(1).getClasse().getNiveauClasse() + " " + p_jour4.getLesCours().get(1).getClasse().getNomClasse() + " <br><br> " + p_jour4.getLesCours().get(1).getMatiere().getNomMatiere() + " <br><br> " + p_jour4.getLesCours().get(1).getProfesseur().getNomProfesseur() + "</center> </html>", SwingConstants.CENTER); }
					panel17 = new JPanel();
					panel17.setLayout(new BorderLayout());
					if(p_jour4.getLesCours().get(1).getMatiere() == null) { panel17.setBackground(Color.white); } else { panel17.setBackground(p_jour4.getLesCours().get(1).getClasse().getCouleurClasse()); }
					panel17.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel17.add(label17, BorderLayout.CENTER);

					label18 = new JLabel();
					if(p_jour5.getLesCours().get(1).getMatiere() == null) { label18 = new JLabel("Libre", SwingConstants.CENTER); } else { label18 = new JLabel("<html> <center>" + p_jour5.getLesCours().get(1).getClasse().getNiveauClasse() + " " + p_jour5.getLesCours().get(1).getClasse().getNomClasse() + " <br><br> " + p_jour5.getLesCours().get(1).getMatiere().getNomMatiere() + " <br><br> " + p_jour5.getLesCours().get(1).getProfesseur().getNomProfesseur() + "</center> </html>", SwingConstants.CENTER); }
					panel18 = new JPanel();
					panel18.setLayout(new BorderLayout());
					if(p_jour5.getLesCours().get(1).getMatiere() == null) { panel18.setBackground(Color.white); } else { panel18.setBackground(p_jour5.getLesCours().get(1).getClasse().getCouleurClasse()); }
					panel18.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
					panel18.add(label18, BorderLayout.CENTER);


					// QUATRIEME LIGNE DE GAUCHE A DROITE 

			        label19 = new JLabel("14h - 16h", SwingConstants.CENTER);
					panel19 = new JPanel();
					panel19.setLayout(new BorderLayout());
					panel19.setBackground(Color.white);
					panel19.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel19.add(label19, BorderLayout.CENTER);

					label20 = new JLabel();
					if(p_jour1.getLesCours().get(2).getMatiere() == null) { label20 = new JLabel("Libre", SwingConstants.CENTER); } else { label20 = new JLabel("<html> <center>" + p_jour1.getLesCours().get(2).getClasse().getNiveauClasse() + " " + p_jour1.getLesCours().get(2).getClasse().getNomClasse() + " <br><br> " + p_jour1.getLesCours().get(2).getMatiere().getNomMatiere() + " <br><br> " + p_jour1.getLesCours().get(2).getProfesseur().getNomProfesseur() + "</center> </html>", SwingConstants.CENTER); }
					panel20 = new JPanel();
					panel20.setLayout(new BorderLayout());
					if(p_jour1.getLesCours().get(2).getMatiere() == null) { panel20.setBackground(Color.white); } else { panel20.setBackground(p_jour1.getLesCours().get(2).getClasse().getCouleurClasse()); }
					panel20.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel20.add(label20, BorderLayout.CENTER);

					label21 = new JLabel();
					if(p_jour2.getLesCours().get(2).getMatiere() == null) { label21 = new JLabel("Libre", SwingConstants.CENTER); } else { label21 = new JLabel("<html> <center>" + p_jour2.getLesCours().get(2).getClasse().getNiveauClasse() + " " + p_jour2.getLesCours().get(2).getClasse().getNomClasse() + " <br><br> " + p_jour2.getLesCours().get(2).getMatiere().getNomMatiere() + " <br><br> " + p_jour2.getLesCours().get(2).getProfesseur().getNomProfesseur() + "</center> </html>", SwingConstants.CENTER); }
					panel21 = new JPanel();
					panel21.setLayout(new BorderLayout());
					if(p_jour2.getLesCours().get(2).getMatiere() == null) { panel21.setBackground(Color.white); } else { panel21.setBackground(p_jour2.getLesCours().get(2).getClasse().getCouleurClasse()); }
					panel21.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel21.add(label21, BorderLayout.CENTER);

					label22 = new JLabel();
					if(p_jour3.getLesCours().get(2).getMatiere() == null) { label22 = new JLabel("Libre", SwingConstants.CENTER); } else { label22 = new JLabel("<html> <center>" + p_jour3.getLesCours().get(2).getClasse().getNiveauClasse() + " " + p_jour3.getLesCours().get(2).getClasse().getNomClasse() + " <br><br> " + p_jour3.getLesCours().get(2).getMatiere().getNomMatiere() + " <br><br> " + p_jour3.getLesCours().get(2).getProfesseur().getNomProfesseur() + "</center> </html>", SwingConstants.CENTER); }
					panel22 = new JPanel();
					panel22.setLayout(new BorderLayout());
					if(p_jour3.getLesCours().get(2).getMatiere() == null) { panel22.setBackground(Color.white); } else { panel22.setBackground(p_jour3.getLesCours().get(2).getClasse().getCouleurClasse()); }
					panel22.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel22.add(label22, BorderLayout.CENTER);

					label23 = new JLabel();
					if(p_jour4.getLesCours().get(2).getMatiere() == null) { label23 = new JLabel("Libre", SwingConstants.CENTER); } else { label23 = new JLabel("<html> <center>" + p_jour4.getLesCours().get(2).getClasse().getNiveauClasse() + " " + p_jour4.getLesCours().get(2).getClasse().getNomClasse() + " <br><br> " + p_jour4.getLesCours().get(2).getMatiere().getNomMatiere() + " <br><br> " + p_jour4.getLesCours().get(2).getProfesseur().getNomProfesseur() + "</center> </html>", SwingConstants.CENTER); }
					panel23 = new JPanel();
					panel23.setLayout(new BorderLayout());
					if(p_jour4.getLesCours().get(2).getMatiere() == null) { panel23.setBackground(Color.white); } else { panel23.setBackground(p_jour4.getLesCours().get(2).getClasse().getCouleurClasse()); }
					panel23.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel23.add(label23, BorderLayout.CENTER);

					label24 = new JLabel();
					if(p_jour5.getLesCours().get(2).getMatiere() == null) { label24 = new JLabel("Libre", SwingConstants.CENTER); } else { label24 = new JLabel("<html> <center>" + p_jour5.getLesCours().get(2).getClasse().getNiveauClasse() + " " + p_jour5.getLesCours().get(2).getClasse().getNomClasse() + " <br><br> " + p_jour5.getLesCours().get(2).getMatiere().getNomMatiere() + " <br><br> " + p_jour5.getLesCours().get(2).getProfesseur().getNomProfesseur() + "</center> </html>", SwingConstants.CENTER); }
					panel24 = new JPanel();
					panel24.setLayout(new BorderLayout());
					if(p_jour5.getLesCours().get(2).getMatiere() == null) { panel24.setBackground(Color.white); } else { panel24.setBackground(p_jour5.getLesCours().get(2).getClasse().getCouleurClasse()); }
					panel24.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
					panel24.add(label24, BorderLayout.CENTER);

					// CINQUIEME LIGNE DE GAUCHE A DROITE 

			        label25 = new JLabel("16h - 18h", SwingConstants.CENTER);
					panel25 = new JPanel();
					panel25.setLayout(new BorderLayout());
					panel25.setBackground(Color.white);
					panel25.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
					panel25.add(label25, BorderLayout.CENTER);

					label26 = new JLabel();
					if(p_jour1.getLesCours().get(3).getMatiere() == null) { label26 = new JLabel("Libre", SwingConstants.CENTER); } else { label26 = new JLabel("<html> <center>" + p_jour1.getLesCours().get(3).getClasse().getNiveauClasse() + " " + p_jour1.getLesCours().get(3).getClasse().getNomClasse() + " <br><br> " + p_jour1.getLesCours().get(3).getMatiere().getNomMatiere() + " <br><br> " + p_jour1.getLesCours().get(3).getProfesseur().getNomProfesseur() + "</center> </html>", SwingConstants.CENTER); }
					panel26 = new JPanel();
					panel26.setLayout(new BorderLayout());
					if(p_jour1.getLesCours().get(3).getMatiere() == null) { panel26.setBackground(Color.white); } else { panel26.setBackground(p_jour1.getLesCours().get(3).getClasse().getCouleurClasse()); }
					panel26.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
					panel26.add(label26, BorderLayout.CENTER);

					label27 = new JLabel();
					if(p_jour2.getLesCours().get(3).getMatiere() == null) { label27 = new JLabel("Libre", SwingConstants.CENTER); } else { label27 = new JLabel("<html> <center>" + p_jour2.getLesCours().get(3).getClasse().getNiveauClasse() + " " + p_jour2.getLesCours().get(3).getClasse().getNomClasse() + " <br><br> " + p_jour2.getLesCours().get(3).getMatiere().getNomMatiere() + " <br><br> " + p_jour2.getLesCours().get(3).getProfesseur().getNomProfesseur() + "</center> </html>", SwingConstants.CENTER); }
					panel27 = new JPanel();
					panel27.setLayout(new BorderLayout());
					if(p_jour2.getLesCours().get(3).getMatiere() == null) { panel27.setBackground(Color.white); } else { panel27.setBackground(p_jour2.getLesCours().get(3).getClasse().getCouleurClasse()); }
					panel27.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
					panel27.add(label27, BorderLayout.CENTER);

					label28 = new JLabel();
					if(p_jour3.getLesCours().get(3).getMatiere() == null) { label28 = new JLabel("Libre", SwingConstants.CENTER); } else { label28 = new JLabel("<html> <center>" + p_jour3.getLesCours().get(3).getClasse().getNiveauClasse() + " " + p_jour3.getLesCours().get(3).getClasse().getNomClasse() + " <br><br> " + p_jour3.getLesCours().get(3).getMatiere().getNomMatiere() + " <br><br> " + p_jour3.getLesCours().get(3).getProfesseur().getNomProfesseur() + "</center> </html>", SwingConstants.CENTER); }
					panel28 = new JPanel();
					panel28.setLayout(new BorderLayout());
					if(p_jour3.getLesCours().get(3).getMatiere() == null) { panel28.setBackground(Color.white); } else { panel28.setBackground(p_jour3.getLesCours().get(3).getClasse().getCouleurClasse()); }
					panel28.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
					panel28.add(label28, BorderLayout.CENTER);

					label29 = new JLabel();
					if(p_jour4.getLesCours().get(3).getMatiere() == null) { label29 = new JLabel("Libre", SwingConstants.CENTER); } else { label29 = new JLabel("<html> <center>" + p_jour4.getLesCours().get(3).getClasse().getNiveauClasse() + " " + p_jour4.getLesCours().get(3).getClasse().getNomClasse() + " <br><br> " + p_jour4.getLesCours().get(3).getMatiere().getNomMatiere() + " <br><br> " + p_jour4.getLesCours().get(3).getProfesseur().getNomProfesseur() + "</center> </html>", SwingConstants.CENTER); }
					panel29 = new JPanel();
					panel29.setLayout(new BorderLayout());
					if(p_jour4.getLesCours().get(3).getMatiere() == null) { panel29.setBackground(Color.white); } else { panel29.setBackground(p_jour4.getLesCours().get(3).getClasse().getCouleurClasse()); }
					panel29.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
					panel29.add(label29, BorderLayout.CENTER);

					label30 = new JLabel();
					if(p_jour5.getLesCours().get(3).getMatiere() == null) { label30 = new JLabel("Libre", SwingConstants.CENTER); } else { label30 = new JLabel("<html> <center>" + p_jour5.getLesCours().get(3).getClasse().getNiveauClasse() + " " + p_jour5.getLesCours().get(3).getClasse().getNomClasse() + " <br><br> " + p_jour5.getLesCours().get(3).getMatiere().getNomMatiere() + " <br><br> " + p_jour5.getLesCours().get(3).getProfesseur().getNomProfesseur() + "</center> </html>", SwingConstants.CENTER); }
					panel30 = new JPanel();
					panel30.setLayout(new BorderLayout());
					if(p_jour5.getLesCours().get(3).getMatiere() == null) { panel30.setBackground(Color.white); } else { panel30.setBackground(p_jour5.getLesCours().get(3).getClasse().getCouleurClasse()); }
					panel30.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
					panel30.add(label30, BorderLayout.CENTER);
					break;

				case "Professeur":

					// Premiere ligne 

					label1 = new JLabel("/", SwingConstants.CENTER);
					panel1 = new JPanel();
					panel1.setLayout(new BorderLayout());
					panel1.setBackground(Color.white);
					panel1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel1.add(label1, BorderLayout.CENTER);

					label2 = new JLabel("LUNDI", SwingConstants.CENTER);
					panel2 = new JPanel();
					panel2.setLayout(new BorderLayout());
					panel2.setBackground(Color.white);
					panel2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel2.add(label2, BorderLayout.CENTER);

					label3 = new JLabel("MARDI", SwingConstants.CENTER);
					panel3 = new JPanel();
					panel3.setLayout(new BorderLayout());
					panel3.setBackground(Color.white);
					panel3.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel3.add(label3, BorderLayout.CENTER);

					label4 = new JLabel("MERCREDI", SwingConstants.CENTER);
					panel4 = new JPanel();
					panel4.setLayout(new BorderLayout());
					panel4.setBackground(Color.white);
					panel4.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel4.add(label4, BorderLayout.CENTER);

					label5 = new JLabel("JEUDI", SwingConstants.CENTER);
					panel5 = new JPanel();
					panel5.setLayout(new BorderLayout());
					panel5.setBackground(Color.white);
					panel5.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel5.add(label5, BorderLayout.CENTER);

					label6 = new JLabel("VENDREDI", SwingConstants.CENTER);
					panel6 = new JPanel();
					panel6.setLayout(new BorderLayout());
					panel6.setBackground(Color.white);
					panel6.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
					panel6.add(label6, BorderLayout.CENTER);


					// DEUXIEME LIGNE DE GAUCHE A DROITE 

					label7 = new JLabel("8h - 10h", SwingConstants.CENTER);
					panel7 = new JPanel();
					panel7.setLayout(new BorderLayout());
					panel7.setBackground(Color.white);
					panel7.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel7.add(label7, BorderLayout.CENTER);

					label8 = new JLabel();
					if(p_jour1.getLesCours().get(0).getClasse() == null) { label8 = new JLabel("Libre", SwingConstants.CENTER); 
					} else { 
						label8 = new JLabel("<html> <center>" +  p_jour1.getLesCours().get(0).getMatiere().getNomMatiere() + " <br> <br> " + p_jour1.getLesCours().get(0).getClasse().getNiveauClasse() + " " + p_jour1.getLesCours().get(0).getClasse().getNomClasse() + "</center> </html>", SwingConstants.CENTER); 
					 }
					panel8 = new JPanel();
					panel8.setLayout(new BorderLayout());
					if(p_jour1.getLesCours().get(0).getClasse() == null) { panel8.setBackground(Color.white); 
					} else { 
						panel8.setBackground(p_jour1.getLesCours().get(0).getClasse().getCouleurClasse()); 
					}
					panel8.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel8.add(label8, BorderLayout.CENTER);

					label9 = new JLabel();
					if(p_jour2.getLesCours().get(0).getClasse() == null) { label9 = new JLabel("Libre", SwingConstants.CENTER); 
					} else { 
						label9 = new JLabel("<html> <center>" +  p_jour2.getLesCours().get(0).getMatiere().getNomMatiere() + " <br> <br> " + p_jour2.getLesCours().get(0).getClasse().getNiveauClasse() + " " + p_jour2.getLesCours().get(0).getClasse().getNomClasse(), SwingConstants.CENTER);
					}
					panel9 = new JPanel();
					panel9.setLayout(new BorderLayout());
					if(p_jour2.getLesCours().get(0).getClasse() == null) { panel9.setBackground(Color.white); 
					} else { 
						panel9.setBackground(p_jour2.getLesCours().get(0).getClasse().getCouleurClasse()); 
					 }
					panel9.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel9.add(label9, BorderLayout.CENTER);

					label10 = new JLabel();
					if(p_jour3.getLesCours().get(0).getClasse() == null) { label10 = new JLabel("Libre", SwingConstants.CENTER); 
					} else { 
						label10 = new JLabel("<html> <center>" +  p_jour3.getLesCours().get(0).getMatiere().getNomMatiere() + " <br> <br> " + p_jour3.getLesCours().get(0).getClasse().getNiveauClasse() + " " + p_jour3.getLesCours().get(0).getClasse().getNomClasse() + "</center> </html>", SwingConstants.CENTER);			
					}
					panel10 = new JPanel();
					panel10.setLayout(new BorderLayout());
					if(p_jour3.getLesCours().get(0).getClasse() == null) { panel10.setBackground(Color.white); 
					} else { 
						panel10.setBackground(p_jour3.getLesCours().get(0).getClasse().getCouleurClasse()); 
					}
					panel10.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel10.add(label10, BorderLayout.CENTER);

					label11 = new JLabel();
					if(p_jour4.getLesCours().get(0).getClasse() == null) { label11 = new JLabel("Libre", SwingConstants.CENTER); 
					} else { 
						label11 = new JLabel("<html> <center>" +  p_jour4.getLesCours().get(0).getMatiere().getNomMatiere() + " <br> <br> " + p_jour4.getLesCours().get(0).getClasse().getNiveauClasse() + " " + p_jour4.getLesCours().get(0).getClasse().getNomClasse() + "</center> </html>", SwingConstants.CENTER); 
					}
					panel11 = new JPanel();
					panel11.setLayout(new BorderLayout());
					if(p_jour4.getLesCours().get(0).getClasse() == null) { panel11.setBackground(Color.white); 
					} else { 
						panel11.setBackground(p_jour4.getLesCours().get(0).getClasse().getCouleurClasse());
					}
					panel11.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel11.add(label11, BorderLayout.CENTER);

					label12 = new JLabel();
					if(p_jour5.getLesCours().get(0).getClasse() == null) { label12 = new JLabel("Libre", SwingConstants.CENTER); 
					} else { 
						label12 = new JLabel("<html> <center>" +  p_jour5.getLesCours().get(0).getMatiere().getNomMatiere() + " <br> <br> " + p_jour5.getLesCours().get(0).getClasse().getNiveauClasse() + " " + p_jour5.getLesCours().get(0).getClasse().getNomClasse() + "</center> </html>", SwingConstants.CENTER);
					}
					panel12 = new JPanel();
					panel12.setLayout(new BorderLayout());
					if(p_jour5.getLesCours().get(0).getClasse() == null) { panel12.setBackground(Color.white); 
					} else { 
						panel12.setBackground(p_jour5.getLesCours().get(0).getClasse().getCouleurClasse()); 
					}
					panel12.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
					panel12.add(label12, BorderLayout.CENTER);

					// TROISIEME LIGNE DE GAUCHE A DROITE 

					label13 = new JLabel("10h - 12h", SwingConstants.CENTER);
					panel13 = new JPanel();
					panel13.setLayout(new BorderLayout());
					panel13.setBackground(Color.white);
					panel13.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel13.add(label13, BorderLayout.CENTER);

					label14 = new JLabel();
					if(p_jour1.getLesCours().get(1).getClasse() == null) { label14 = new JLabel("Libre", SwingConstants.CENTER); 
					} else { 
						label14 = new JLabel("<html> <center>" +  p_jour1.getLesCours().get(1).getMatiere().getNomMatiere() + " <br> <br> " + p_jour1.getLesCours().get(1).getClasse().getNiveauClasse() + " " + p_jour1.getLesCours().get(1).getClasse().getNomClasse() + "</center> </html>", SwingConstants.CENTER);
					}
					panel14 = new JPanel();
					panel14.setLayout(new BorderLayout());
					if(p_jour1.getLesCours().get(1).getClasse() == null) { panel14.setBackground(Color.white); 
					} else { 
						panel14.setBackground(p_jour1.getLesCours().get(1).getClasse().getCouleurClasse()); 
					}
					panel14.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel14.add(label14, BorderLayout.CENTER);

					label15 = new JLabel();
					if(p_jour2.getLesCours().get(1).getClasse() == null) { label15 = new JLabel("Libre", SwingConstants.CENTER); 
					} else { 
						label15 = new JLabel("<html> <center>" +  p_jour2.getLesCours().get(1).getMatiere().getNomMatiere() + " <br> <br> " + p_jour2.getLesCours().get(1).getClasse().getNiveauClasse() + " " + p_jour2.getLesCours().get(1).getClasse().getNomClasse() + "</center> </html>", SwingConstants.CENTER); 
					}
					panel15 = new JPanel();
					panel15.setLayout(new BorderLayout());
					if(p_jour2.getLesCours().get(1).getClasse() == null) { panel15.setBackground(Color.white); 
					} else {
						panel15.setBackground(p_jour2.getLesCours().get(1).getClasse().getCouleurClasse()); 
					}
					panel15.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel15.add(label15, BorderLayout.CENTER);

					label16 = new JLabel();
					if(p_jour3.getLesCours().get(1).getClasse() == null) { label16 = new JLabel("Libre", SwingConstants.CENTER); 
					} else { 
						label16 = new JLabel("<html> <center>" +  p_jour3.getLesCours().get(1).getMatiere().getNomMatiere() + " <br> <br> " + p_jour3.getLesCours().get(1).getClasse().getNiveauClasse() + " " + p_jour3.getLesCours().get(1).getClasse().getNomClasse() + "</center> </html>", SwingConstants.CENTER); 
					}
					panel16 = new JPanel();
					panel16.setLayout(new BorderLayout());
					if(p_jour3.getLesCours().get(1).getClasse() == null) { panel16.setBackground(Color.white); 
					} else { 
						panel16.setBackground(p_jour3.getLesCours().get(1).getClasse().getCouleurClasse()); 
					}
					panel16.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel16.add(label16, BorderLayout.CENTER);

					label17 = new JLabel();
					if(p_jour4.getLesCours().get(1).getClasse() == null) { label17 = new JLabel("Libre", SwingConstants.CENTER); 
					} else { 
						label17 = new JLabel("<html> <center>" +  p_jour4.getLesCours().get(1).getMatiere().getNomMatiere() + " <br> <br> " + p_jour4.getLesCours().get(1).getClasse().getNiveauClasse() + " " + p_jour4.getLesCours().get(1).getClasse().getNomClasse() + "</center> </html>", SwingConstants.CENTER);
					}
					panel17 = new JPanel();
					panel17.setLayout(new BorderLayout());
					if(p_jour4.getLesCours().get(1).getClasse() == null) { panel17.setBackground(Color.white); 
					} else { 
						panel17.setBackground(p_jour4.getLesCours().get(1).getClasse().getCouleurClasse()); 
					}
					panel17.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel17.add(label17, BorderLayout.CENTER);

					label18 = new JLabel();
					if(p_jour5.getLesCours().get(1).getClasse() == null) { label18 = new JLabel("Libre", SwingConstants.CENTER); 
					} else { 
						label18 = new JLabel("<html> <center>" +  p_jour5.getLesCours().get(1).getMatiere().getNomMatiere() + " <br> <br> " + p_jour5.getLesCours().get(1).getClasse().getNiveauClasse() + " " + p_jour5.getLesCours().get(1).getClasse().getNomClasse() + "</center> </html>", SwingConstants.CENTER);
					}
					panel18 = new JPanel();
					panel18.setLayout(new BorderLayout());
					if(p_jour5.getLesCours().get(1).getClasse() == null) { panel18.setBackground(Color.white); 
					} else { 
						panel18.setBackground(p_jour5.getLesCours().get(1).getClasse().getCouleurClasse()); 
					}
					panel18.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
					panel18.add(label18, BorderLayout.CENTER);


					// QUATRIEME LIGNE DE GAUCHE A DROITE 

					label19 = new JLabel("14h - 16h", SwingConstants.CENTER);
					panel19 = new JPanel();
					panel19.setLayout(new BorderLayout());
					panel19.setBackground(Color.white);
					panel19.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel19.add(label19, BorderLayout.CENTER);

					label20 = new JLabel();
					if(p_jour1.getLesCours().get(2).getClasse() == null) { label20 = new JLabel("Libre", SwingConstants.CENTER); 
					} else { 
						label20 = new JLabel("<html> <center>" +  p_jour1.getLesCours().get(2).getMatiere().getNomMatiere() + " <br> <br> " + p_jour1.getLesCours().get(2).getClasse().getNiveauClasse() + " " + p_jour1.getLesCours().get(2).getClasse().getNomClasse() + "</center> </html>", SwingConstants.CENTER); 
					}
					panel20 = new JPanel();
					panel20.setLayout(new BorderLayout());
					if(p_jour1.getLesCours().get(2).getClasse() == null) { panel20.setBackground(Color.white); 
					} else { 
						panel20.setBackground(p_jour1.getLesCours().get(2).getClasse().getCouleurClasse()); 
					}
					panel20.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel20.add(label20, BorderLayout.CENTER);

					label21 = new JLabel();
					if(p_jour2.getLesCours().get(2).getClasse() == null) { label21 = new JLabel("Libre", SwingConstants.CENTER); 
					} else { 
						label21 = new JLabel("<html> <center>" +  p_jour2.getLesCours().get(2).getMatiere().getNomMatiere() + " <br> <br> " + p_jour2.getLesCours().get(2).getClasse().getNiveauClasse() + " " + p_jour2.getLesCours().get(2).getClasse().getNomClasse() + "</center> </html>", SwingConstants.CENTER); 
					}
					panel21 = new JPanel();
					panel21.setLayout(new BorderLayout());
					if(p_jour2.getLesCours().get(2).getClasse() == null) { panel21.setBackground(Color.white); 
					} else { 
						panel21.setBackground(p_jour2.getLesCours().get(2).getClasse().getCouleurClasse()); 
					}
					panel21.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel21.add(label21, BorderLayout.CENTER);

					label22 = new JLabel();
					if(p_jour3.getLesCours().get(2).getClasse() == null) { label22 = new JLabel("Libre", SwingConstants.CENTER); 
					} else { 
						label22 = new JLabel("<html> <center>" +  p_jour3.getLesCours().get(2).getMatiere().getNomMatiere() + " <br> <br> " + p_jour3.getLesCours().get(2).getClasse().getNiveauClasse() + " " + p_jour3.getLesCours().get(2).getClasse().getNomClasse() + "</center> </html>", SwingConstants.CENTER); 
					}
					panel22 = new JPanel();
					panel22.setLayout(new BorderLayout());
					if(p_jour3.getLesCours().get(2).getClasse() == null) { panel22.setBackground(Color.white); 
					} else { 
						panel22.setBackground(p_jour3.getLesCours().get(2).getClasse().getCouleurClasse()); 
					}
					panel22.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel22.add(label22, BorderLayout.CENTER);

					label23 = new JLabel();
					if(p_jour4.getLesCours().get(2).getClasse() == null) { label23 = new JLabel("Libre", SwingConstants.CENTER); 
					} else { 
						label23 = new JLabel("<html> <center>" +  p_jour4.getLesCours().get(2).getMatiere().getNomMatiere() + " <br> <br> " + p_jour4.getLesCours().get(2).getClasse().getNiveauClasse() + " " + p_jour4.getLesCours().get(2).getClasse().getNomClasse() + "</center> </html>", SwingConstants.CENTER); 
					}
					panel23 = new JPanel();
					panel23.setLayout(new BorderLayout());
					if(p_jour4.getLesCours().get(2).getClasse() == null) { panel23.setBackground(Color.white); 
					} else { 
						panel23.setBackground(p_jour4.getLesCours().get(2).getClasse().getCouleurClasse()); 
					}
					panel23.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel23.add(label23, BorderLayout.CENTER);

					label24 = new JLabel();
					if(p_jour5.getLesCours().get(2).getClasse() == null) { label24 = new JLabel("Libre", SwingConstants.CENTER); 
					} else { 
						label24 = new JLabel("<html> <center>" +  p_jour5.getLesCours().get(2).getMatiere().getNomMatiere() + " <br> <br> " + p_jour5.getLesCours().get(2).getClasse().getNiveauClasse() + " " + p_jour5.getLesCours().get(2).getClasse().getNomClasse() + "</center> </html>", SwingConstants.CENTER);
					}
					panel24 = new JPanel();
					panel24.setLayout(new BorderLayout());
					if(p_jour5.getLesCours().get(2).getClasse() == null) { panel24.setBackground(Color.white); 
					} else { 
						panel24.setBackground(p_jour5.getLesCours().get(2).getClasse().getCouleurClasse()); 
					}
					panel24.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
					panel24.add(label24, BorderLayout.CENTER);

					// CINQUIEME LIGNE DE GAUCHE A DROITE 

					label25 = new JLabel("16h - 18h", SwingConstants.CENTER);
					panel25 = new JPanel();
					panel25.setLayout(new BorderLayout());
					panel25.setBackground(Color.white);
					panel25.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
					panel25.add(label25, BorderLayout.CENTER);

					label26 = new JLabel();
					if(p_jour1.getLesCours().get(3).getClasse() == null) { label26 = new JLabel("Libre", SwingConstants.CENTER); 
					} else { 
						label26 = new JLabel("<html> <center>" +  p_jour1.getLesCours().get(3).getMatiere().getNomMatiere() + " <br> <br> " + p_jour1.getLesCours().get(3).getClasse().getNiveauClasse() + " " + p_jour1.getLesCours().get(3).getClasse().getNomClasse() + "</center> </html>", SwingConstants.CENTER);
					}
					panel26 = new JPanel();
					panel26.setLayout(new BorderLayout());
					if(p_jour1.getLesCours().get(3).getClasse() == null) { panel26.setBackground(Color.white); } else { panel26.setBackground(p_jour1.getLesCours().get(3).getClasse().getCouleurClasse()); }
					panel26.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
					panel26.add(label26, BorderLayout.CENTER);

					label27 = new JLabel();
					if(p_jour2.getLesCours().get(3).getClasse() == null) { label27 = new JLabel("Libre", SwingConstants.CENTER); 
					} else { 
						label27 = new JLabel("<html> <center>" +  p_jour2.getLesCours().get(3).getMatiere().getNomMatiere() + " <br> <br> " + p_jour2.getLesCours().get(3).getClasse().getNiveauClasse() + " " + p_jour1.getLesCours().get(3).getClasse().getNomClasse() + "</center> </html>", SwingConstants.CENTER); 
					}
					panel27 = new JPanel();
					panel27.setLayout(new BorderLayout());
					if(p_jour2.getLesCours().get(3).getClasse() == null) { panel27.setBackground(Color.white); 
					} else { 
						panel27.setBackground(p_jour2.getLesCours().get(3).getClasse().getCouleurClasse()); 
					}
					panel27.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
					panel27.add(label27, BorderLayout.CENTER);

					label28 = new JLabel();
					if(p_jour3.getLesCours().get(3).getClasse() == null) { label28 = new JLabel("Libre", SwingConstants.CENTER); 
					} else { 
						label28 = new JLabel("<html> <center>" +  p_jour3.getLesCours().get(3).getMatiere().getNomMatiere() + " <br> <br> " + p_jour3.getLesCours().get(3).getClasse().getNiveauClasse() + " " + p_jour1.getLesCours().get(3).getClasse().getNomClasse() + "</center> </html>", SwingConstants.CENTER); 
					}
					panel28 = new JPanel();
					panel28.setLayout(new BorderLayout());
					if(p_jour3.getLesCours().get(3).getClasse() == null) { panel28.setBackground(Color.white); 
					} else { 
						panel28.setBackground(p_jour3.getLesCours().get(3).getClasse().getCouleurClasse()); 
					}
					panel28.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
					panel28.add(label28, BorderLayout.CENTER);

					label29 = new JLabel();
					if(p_jour4.getLesCours().get(3).getClasse() == null) { label29 = new JLabel("Libre", SwingConstants.CENTER); 
					} else { 
						label29 = new JLabel("<html> <center>" +  p_jour4.getLesCours().get(3).getMatiere().getNomMatiere() + " <br> <br> " + p_jour4.getLesCours().get(3).getClasse().getNiveauClasse() + " " + p_jour1.getLesCours().get(3).getClasse().getNomClasse() + "</center> </html>", SwingConstants.CENTER); 
					}
					panel29 = new JPanel();
					panel29.setLayout(new BorderLayout());
					if(p_jour4.getLesCours().get(3).getClasse() == null) { panel29.setBackground(Color.white); 
					} else { 
						panel29.setBackground(p_jour4.getLesCours().get(3).getClasse().getCouleurClasse()); 
					}
					panel29.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
					panel29.add(label29, BorderLayout.CENTER);

					label30 = new JLabel();
					if(p_jour5.getLesCours().get(3).getClasse() == null) { label30 = new JLabel("Libre", SwingConstants.CENTER); 
					} else { 
						label30 = new JLabel("<html> <center>" +  p_jour5.getLesCours().get(3).getMatiere().getNomMatiere() + " <br> <br> " + p_jour5.getLesCours().get(3).getClasse().getNiveauClasse() + " " + p_jour1.getLesCours().get(3).getClasse().getNomClasse() + "</center> </html>", SwingConstants.CENTER); 
					}
					panel30 = new JPanel();
					panel30.setLayout(new BorderLayout());
					if(p_jour5.getLesCours().get(3).getClasse() == null) { panel30.setBackground(Color.white); 
					} else { 
						panel30.setBackground(p_jour5.getLesCours().get(3).getClasse().getCouleurClasse()); 
					}
					panel30.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
					panel30.add(label30, BorderLayout.CENTER);
					break;

				case "Classe":

					// Premiere ligne 

					label1 = new JLabel("/", SwingConstants.CENTER);
					panel1 = new JPanel();
					panel1.setLayout(new BorderLayout());
					panel1.setBackground(Color.white);
					panel1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel1.add(label1, BorderLayout.CENTER);

					label2 = new JLabel("LUNDI", SwingConstants.CENTER);
					panel2 = new JPanel();
					panel2.setLayout(new BorderLayout());
					panel2.setBackground(Color.white);
					panel2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel2.add(label2, BorderLayout.CENTER);

					label3 = new JLabel("MARDI", SwingConstants.CENTER);
					panel3 = new JPanel();
					panel3.setLayout(new BorderLayout());
					panel3.setBackground(Color.white);
					panel3.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel3.add(label3, BorderLayout.CENTER);

					label4 = new JLabel("MERCREDI", SwingConstants.CENTER);
					panel4 = new JPanel();
					panel4.setLayout(new BorderLayout());
					panel4.setBackground(Color.white);
					panel4.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel4.add(label4, BorderLayout.CENTER);

					label5 = new JLabel("JEUDI", SwingConstants.CENTER);
					panel5 = new JPanel();
					panel5.setLayout(new BorderLayout());
					panel5.setBackground(Color.white);
					panel5.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel5.add(label5, BorderLayout.CENTER);

					label6 = new JLabel("VENDREDI", SwingConstants.CENTER);
					panel6 = new JPanel();
					panel6.setLayout(new BorderLayout());
					panel6.setBackground(Color.white);
					panel6.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
					panel6.add(label6, BorderLayout.CENTER);


					// DEUXIEME LIGNE DE GAUCHE A DROITE 

					label7 = new JLabel("8h - 10h", SwingConstants.CENTER);
					panel7 = new JPanel();
					panel7.setLayout(new BorderLayout());
					panel7.setBackground(Color.white);
					panel7.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel7.add(label7, BorderLayout.CENTER);

					label8 = new JLabel();
					label8_1 = new JLabel();
					panel8 = new JPanel();
					panel8_1.setLayout(new BorderLayout());
					panel8_2.setLayout(new BorderLayout());
					panel8.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));

					if(p_jour1.getLesCours().get(0).getTypeCours() != "groupe") {
						if(p_jour1.getLesCours().get(0).getMatiere() == null) { label8 = new JLabel("Libre", SwingConstants.CENTER); } else { label8 = new JLabel(p_jour1.getLesCours().get(0).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
						panel8.setLayout(new BorderLayout());
						if(p_jour1.getLesCours().get(0).getMatiere() == null) { panel8.setBackground(Color.white); } else { panel8.setBackground(p_jour1.getLesCours().get(0).getMatiere().getCouleurMatiere()); }
						panel8.add(label8, BorderLayout.CENTER);
					} else {
						panel8.setLayout(new GridLayout(1,2));
						if(p_jour1.getLesCours().get(0).getClasse().getLesGroupesClasse().size() == 2) { System.out.println("Le groupe 2 : " + p_jour1.getLesCours().get(0).getClasse().getLesGroupesClasse().get(1).getNomGroupe()); }
						
						if(p_jour1.getLesCours().get(0).getClasse().getLesGroupesClasse().get(0).getEDT().getLeJour(p_jour1).getLesCours().get(0).getMatiere() == null) { label8 = new JLabel("Libre", SwingConstants.CENTER); } else { label8 = new JLabel(p_jour1.getLesCours().get(0).getClasse().getLesGroupesClasse().get(0).getEDT().getLeJour(p_jour1).getLesCours().get(0).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
						if(p_jour1.getLesCours().get(0).getClasse().getLesGroupesClasse().size() == 2) {
							if(p_jour1.getLesCours().get(0).getClasse().getLesGroupesClasse().get(1).getEDT().getLeJour(p_jour1).getLesCours().get(0).getMatiere() == null) { 
								label8_1 = new JLabel("Libre", SwingConstants.CENTER); 
							} else { 
								label8_1 = new JLabel(p_jour1.getLesCours().get(0).getClasse().getLesGroupesClasse().get(1).getEDT().getLeJour(p_jour1).getLesCours().get(0).getMatiere().getNomMatiere(), SwingConstants.CENTER); 
							}
						} else {
							label8_1 = new JLabel("Libre", SwingConstants.CENTER);
						}
						if(p_jour1.getLesCours().get(0).getClasse().getLesGroupesClasse().get(0).getEDT().getLeJour(p_jour1).getLesCours().get(0).getMatiere() == null) { panel8_1.setBackground(Color.white); } else { System.out.println("Couleur avant : " + panel8_1.getBackground()); System.out.println("Couleur a mettre : " + p_jour1.getLesCours().get(0).getClasse().getLesGroupesClasse().get(0).getEDT().getLeJour(p_jour1).getLesCours().get(0).getMatiere().getCouleurMatiere()); panel8_1.setBackground(p_jour1.getLesCours().get(0).getClasse().getLesGroupesClasse().get(0).getEDT().getLeJour(p_jour1).getLesCours().get(0).getMatiere().getCouleurMatiere()); System.out.println("Couleur apres : " + panel8_1.getBackground());}			
						
						if(p_jour1.getLesCours().get(0).getClasse().getLesGroupesClasse().size() == 2) {
							if(p_jour1.getLesCours().get(0).getClasse().getLesGroupesClasse().get(1).getEDT().getLeJour(p_jour1).getLesCours().get(0).getMatiere() == null) { 
								panel8_2.setBackground(Color.white); 
							} else { 
								panel8_2.setBackground(p_jour1.getLesCours().get(0).getClasse().getLesGroupesClasse().get(1).getEDT().getLeJour(p_jour1).getLesCours().get(0).getMatiere().getCouleurMatiere()); 
							}	
						}
						
						panel8_1.add(label8, BorderLayout.CENTER);
						panel8_2.add(label8_1, BorderLayout.CENTER);

						panel8.add(panel8_1);
						panel8.add(panel8_2);
					}

					label9 = new JLabel();
					if(p_jour2.getLesCours().get(0).getMatiere() == null) { label9 = new JLabel("Libre", SwingConstants.CENTER); } else { label9 = new JLabel(p_jour2.getLesCours().get(0).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel9 = new JPanel();
					panel9.setLayout(new BorderLayout());
					if(p_jour2.getLesCours().get(0).getMatiere() == null) { panel9.setBackground(Color.white); } else { panel9.setBackground(p_jour2.getLesCours().get(0).getMatiere().getCouleurMatiere()); }
					panel9.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel9.add(label9, BorderLayout.CENTER);

					label10 = new JLabel();
					if(p_jour3.getLesCours().get(0).getMatiere() == null) { label10 = new JLabel("Libre", SwingConstants.CENTER); } else { label10 = new JLabel(p_jour3.getLesCours().get(0).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel10 = new JPanel();
					panel10.setLayout(new BorderLayout());
					if(p_jour3.getLesCours().get(0).getMatiere() == null) { panel10.setBackground(Color.white); } else { panel10.setBackground(p_jour3.getLesCours().get(0).getMatiere().getCouleurMatiere()); }
					panel10.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel10.add(label10, BorderLayout.CENTER);

					label11 = new JLabel();
					if(p_jour4.getLesCours().get(0).getMatiere() == null) { label11 = new JLabel("Libre", SwingConstants.CENTER); } else { label11 = new JLabel(p_jour4.getLesCours().get(0).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel11 = new JPanel();
					panel11.setLayout(new BorderLayout());
					if(p_jour4.getLesCours().get(0).getMatiere() == null) { panel11.setBackground(Color.white); } else { panel11.setBackground(p_jour4.getLesCours().get(0).getMatiere().getCouleurMatiere()); }
					panel11.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel11.add(label11, BorderLayout.CENTER);

					label12 = new JLabel();
					if(p_jour5.getLesCours().get(0).getMatiere() == null) { label12 = new JLabel("Libre", SwingConstants.CENTER); } else { label12 = new JLabel(p_jour5.getLesCours().get(0).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel12 = new JPanel();
					panel12.setLayout(new BorderLayout());
					if(p_jour5.getLesCours().get(0).getMatiere() == null) { panel12.setBackground(Color.white); } else { panel12.setBackground(p_jour5.getLesCours().get(0).getMatiere().getCouleurMatiere()); }
					panel12.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
					panel12.add(label12, BorderLayout.CENTER);

					// TROISIEME LIGNE DE GAUCHE A DROITE 

					label13 = new JLabel("10h - 12h", SwingConstants.CENTER);
					panel13 = new JPanel();
					panel13.setLayout(new BorderLayout());
					panel13.setBackground(Color.white);
					panel13.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel13.add(label13, BorderLayout.CENTER);

					label14 = new JLabel();
					if(p_jour1.getLesCours().get(1).getMatiere() == null) { label14 = new JLabel("Libre", SwingConstants.CENTER); } else { label14 = new JLabel(p_jour1.getLesCours().get(1).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel14 = new JPanel();
					panel14.setLayout(new BorderLayout());
					if(p_jour1.getLesCours().get(1).getMatiere() == null) { panel14.setBackground(Color.white); } else { panel14.setBackground(p_jour1.getLesCours().get(1).getMatiere().getCouleurMatiere()); }
					panel14.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel14.add(label14, BorderLayout.CENTER);

					label15 = new JLabel();
					if(p_jour2.getLesCours().get(1).getMatiere() == null) { label15 = new JLabel("Libre", SwingConstants.CENTER); } else { label15 = new JLabel(p_jour2.getLesCours().get(1).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel15 = new JPanel();
					panel15.setLayout(new BorderLayout());
					if(p_jour2.getLesCours().get(1).getMatiere() == null) { panel15.setBackground(Color.white); } else { panel15.setBackground(p_jour2.getLesCours().get(1).getMatiere().getCouleurMatiere()); }
					panel15.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel15.add(label15, BorderLayout.CENTER);

					label16 = new JLabel();
					if(p_jour3.getLesCours().get(1).getMatiere() == null) { label16 = new JLabel("Libre", SwingConstants.CENTER); } else { label16 = new JLabel(p_jour3.getLesCours().get(1).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel16 = new JPanel();
					panel16.setLayout(new BorderLayout());
					if(p_jour3.getLesCours().get(1).getMatiere() == null) { panel16.setBackground(Color.white); } else { panel16.setBackground(p_jour3.getLesCours().get(1).getMatiere().getCouleurMatiere()); }
					panel16.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel16.add(label16, BorderLayout.CENTER);

					label17 = new JLabel();
					if(p_jour4.getLesCours().get(1).getMatiere() == null) { label17 = new JLabel("Libre", SwingConstants.CENTER); } else { label17 = new JLabel(p_jour4.getLesCours().get(1).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel17 = new JPanel();
					panel17.setLayout(new BorderLayout());
					if(p_jour4.getLesCours().get(1).getMatiere() == null) { panel17.setBackground(Color.white); } else { panel17.setBackground(p_jour4.getLesCours().get(1).getMatiere().getCouleurMatiere()); }
					panel17.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel17.add(label17, BorderLayout.CENTER);

					label18 = new JLabel();
					if(p_jour5.getLesCours().get(1).getMatiere() == null) { label18 = new JLabel("Libre", SwingConstants.CENTER); } else { label18 = new JLabel(p_jour5.getLesCours().get(1).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel18 = new JPanel();
					panel18.setLayout(new BorderLayout());
					if(p_jour5.getLesCours().get(1).getMatiere() == null) { panel18.setBackground(Color.white); } else { panel18.setBackground(p_jour5.getLesCours().get(1).getMatiere().getCouleurMatiere()); }
					panel18.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
					panel18.add(label18, BorderLayout.CENTER);


					// QUATRIEME LIGNE DE GAUCHE A DROITE 

					label19 = new JLabel("14h - 16h", SwingConstants.CENTER);
					panel19 = new JPanel();
					panel19.setLayout(new BorderLayout());
					panel19.setBackground(Color.white);
					panel19.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel19.add(label19, BorderLayout.CENTER);

					label20 = new JLabel();
					if(p_jour1.getLesCours().get(2).getMatiere() == null) { label20 = new JLabel("Libre", SwingConstants.CENTER); } else { label20 = new JLabel(p_jour1.getLesCours().get(2).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel20 = new JPanel();
					panel20.setLayout(new BorderLayout());
					if(p_jour1.getLesCours().get(2).getMatiere() == null) { panel20.setBackground(Color.white); } else { panel20.setBackground(p_jour1.getLesCours().get(2).getMatiere().getCouleurMatiere()); }
					panel20.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel20.add(label20, BorderLayout.CENTER);

					label21 = new JLabel();
					if(p_jour2.getLesCours().get(2).getMatiere() == null) { label21 = new JLabel("Libre", SwingConstants.CENTER); } else { label21 = new JLabel(p_jour2.getLesCours().get(2).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel21 = new JPanel();
					panel21.setLayout(new BorderLayout());
					if(p_jour2.getLesCours().get(2).getMatiere() == null) { panel21.setBackground(Color.white); } else { panel21.setBackground(p_jour2.getLesCours().get(2).getMatiere().getCouleurMatiere()); }
					panel21.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel21.add(label21, BorderLayout.CENTER);

					label22 = new JLabel();
					if(p_jour3.getLesCours().get(2).getMatiere() == null) { label22 = new JLabel("Libre", SwingConstants.CENTER); } else { label22 = new JLabel(p_jour3.getLesCours().get(2).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel22 = new JPanel();
					panel22.setLayout(new BorderLayout());
					if(p_jour3.getLesCours().get(2).getMatiere() == null) { panel22.setBackground(Color.white); } else { panel22.setBackground(p_jour3.getLesCours().get(2).getMatiere().getCouleurMatiere()); }
					panel22.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel22.add(label22, BorderLayout.CENTER);

					label23 = new JLabel();
					if(p_jour4.getLesCours().get(2).getMatiere() == null) { label23 = new JLabel("Libre", SwingConstants.CENTER); } else { label23 = new JLabel(p_jour4.getLesCours().get(2).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel23 = new JPanel();
					panel23.setLayout(new BorderLayout());
					if(p_jour4.getLesCours().get(2).getMatiere() == null) { panel23.setBackground(Color.white); } else { panel23.setBackground(p_jour4.getLesCours().get(2).getMatiere().getCouleurMatiere()); }
					panel23.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel23.add(label23, BorderLayout.CENTER);

					label24 = new JLabel();
					if(p_jour5.getLesCours().get(2).getMatiere() == null) { label24 = new JLabel("Libre", SwingConstants.CENTER); } else { label24 = new JLabel(p_jour5.getLesCours().get(2).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel24 = new JPanel();
					panel24.setLayout(new BorderLayout());
					if(p_jour5.getLesCours().get(2).getMatiere() == null) { panel24.setBackground(Color.white); } else { panel24.setBackground(p_jour5.getLesCours().get(2).getMatiere().getCouleurMatiere()); }
					panel24.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
					panel24.add(label24, BorderLayout.CENTER);

					// CINQUIEME LIGNE DE GAUCHE A DROITE 

					label25 = new JLabel("16h - 18h", SwingConstants.CENTER);
					panel25 = new JPanel();
					panel25.setLayout(new BorderLayout());
					panel25.setBackground(Color.white);
					panel25.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
					panel25.add(label25, BorderLayout.CENTER);

					label26 = new JLabel();
					if(p_jour1.getLesCours().get(3).getMatiere() == null) { label26 = new JLabel("Libre", SwingConstants.CENTER); } else { label26 = new JLabel(p_jour1.getLesCours().get(3).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel26 = new JPanel();
					panel26.setLayout(new BorderLayout());
					if(p_jour1.getLesCours().get(3).getMatiere() == null) { panel26.setBackground(Color.white); } else { panel26.setBackground(p_jour1.getLesCours().get(3).getMatiere().getCouleurMatiere()); }
					panel26.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
					panel26.add(label26, BorderLayout.CENTER);

					label27 = new JLabel();
					if(p_jour2.getLesCours().get(3).getMatiere() == null) { label27 = new JLabel("Libre", SwingConstants.CENTER); } else { label27 = new JLabel(p_jour2.getLesCours().get(3).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel27 = new JPanel();
					panel27.setLayout(new BorderLayout());
					if(p_jour2.getLesCours().get(3).getMatiere() == null) { panel27.setBackground(Color.white); } else { panel27.setBackground(p_jour2.getLesCours().get(3).getMatiere().getCouleurMatiere()); }
					panel27.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
					panel27.add(label27, BorderLayout.CENTER);

					label28 = new JLabel();
					if(p_jour3.getLesCours().get(3).getMatiere() == null) { label28 = new JLabel("Libre", SwingConstants.CENTER); } else { label28 = new JLabel(p_jour3.getLesCours().get(3).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel28 = new JPanel();
					panel28.setLayout(new BorderLayout());
					if(p_jour3.getLesCours().get(3).getMatiere() == null) { panel28.setBackground(Color.white); } else { panel28.setBackground(p_jour3.getLesCours().get(3).getMatiere().getCouleurMatiere()); }
					panel28.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
					panel28.add(label28, BorderLayout.CENTER);

					label29 = new JLabel();
					if(p_jour4.getLesCours().get(3).getMatiere() == null) { label29 = new JLabel("Libre", SwingConstants.CENTER); } else { label29 = new JLabel(p_jour4.getLesCours().get(3).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel29 = new JPanel();
					panel29.setLayout(new BorderLayout());
					if(p_jour4.getLesCours().get(3).getMatiere() == null) { panel29.setBackground(Color.white); } else { panel29.setBackground(p_jour4.getLesCours().get(3).getMatiere().getCouleurMatiere()); }
					panel29.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
					panel29.add(label29, BorderLayout.CENTER);

					label30 = new JLabel();
					if(p_jour5.getLesCours().get(3).getMatiere() == null) { label30 = new JLabel("Libre", SwingConstants.CENTER); } else { label30 = new JLabel(p_jour5.getLesCours().get(3).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel30 = new JPanel();
					panel30.setLayout(new BorderLayout());
					if(p_jour5.getLesCours().get(3).getMatiere() == null) { panel30.setBackground(Color.white); } else { panel30.setBackground(p_jour5.getLesCours().get(3).getMatiere().getCouleurMatiere()); }
					panel30.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
					panel30.add(label30, BorderLayout.CENTER);
					break;
				
				case "Groupe":

					// Premiere ligne 

					label1 = new JLabel("/", SwingConstants.CENTER);
					panel1 = new JPanel();
					panel1.setLayout(new BorderLayout());
					panel1.setBackground(Color.white);
					panel1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel1.add(label1, BorderLayout.CENTER);

					label2 = new JLabel("LUNDI", SwingConstants.CENTER);
					panel2 = new JPanel();
					panel2.setLayout(new BorderLayout());
					panel2.setBackground(Color.white);
					panel2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel2.add(label2, BorderLayout.CENTER);

					label3 = new JLabel("MARDI", SwingConstants.CENTER);
					panel3 = new JPanel();
					panel3.setLayout(new BorderLayout());
					panel3.setBackground(Color.white);
					panel3.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel3.add(label3, BorderLayout.CENTER);

					label4 = new JLabel("MERCREDI", SwingConstants.CENTER);
					panel4 = new JPanel();
					panel4.setLayout(new BorderLayout());
					panel4.setBackground(Color.white);
					panel4.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel4.add(label4, BorderLayout.CENTER);

					label5 = new JLabel("JEUDI", SwingConstants.CENTER);
					panel5 = new JPanel();
					panel5.setLayout(new BorderLayout());
					panel5.setBackground(Color.white);
					panel5.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel5.add(label5, BorderLayout.CENTER);

					label6 = new JLabel("VENDREDI", SwingConstants.CENTER);
					panel6 = new JPanel();
					panel6.setLayout(new BorderLayout());
					panel6.setBackground(Color.white);
					panel6.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
					panel6.add(label6, BorderLayout.CENTER);


					// DEUXIEME LIGNE DE GAUCHE A DROITE 

					label7 = new JLabel("8h - 10h", SwingConstants.CENTER);
					panel7 = new JPanel();
					panel7.setLayout(new BorderLayout());
					panel7.setBackground(Color.white);
					panel7.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel7.add(label7, BorderLayout.CENTER);

					label8 = new JLabel();
					if(p_jour1.getLesCours().get(0).getMatiere() == null) { label8 = new JLabel("Libre", SwingConstants.CENTER); } else { label8 = new JLabel(p_jour1.getLesCours().get(0).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel8 = new JPanel();
					panel8.setLayout(new BorderLayout());
					if(p_jour1.getLesCours().get(0).getMatiere() == null) { panel8.setBackground(Color.white); } else { panel8.setBackground(p_jour1.getLesCours().get(0).getMatiere().getCouleurMatiere()); }
					panel8.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel8.add(label8, BorderLayout.CENTER);

					label9 = new JLabel();
					if(p_jour2.getLesCours().get(0).getMatiere() == null) { label9 = new JLabel("Libre", SwingConstants.CENTER); } else { label9 = new JLabel(p_jour2.getLesCours().get(0).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel9 = new JPanel();
					panel9.setLayout(new BorderLayout());
					if(p_jour2.getLesCours().get(0).getMatiere() == null) { panel9.setBackground(Color.white); } else { panel9.setBackground(p_jour2.getLesCours().get(0).getMatiere().getCouleurMatiere()); }
					panel9.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel9.add(label9, BorderLayout.CENTER);

					label10 = new JLabel();
					if(p_jour3.getLesCours().get(0).getMatiere() == null) { label10 = new JLabel("Libre", SwingConstants.CENTER); } else { label10 = new JLabel(p_jour3.getLesCours().get(0).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel10 = new JPanel();
					panel10.setLayout(new BorderLayout());
					if(p_jour3.getLesCours().get(0).getMatiere() == null) { panel10.setBackground(Color.white); } else { panel10.setBackground(p_jour3.getLesCours().get(0).getMatiere().getCouleurMatiere()); }
					panel10.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel10.add(label10, BorderLayout.CENTER);

					label11 = new JLabel();
					if(p_jour4.getLesCours().get(0).getMatiere() == null) { label11 = new JLabel("Libre", SwingConstants.CENTER); } else { label11 = new JLabel(p_jour4.getLesCours().get(0).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel11 = new JPanel();
					panel11.setLayout(new BorderLayout());
					if(p_jour4.getLesCours().get(0).getMatiere() == null) { panel11.setBackground(Color.white); } else { panel11.setBackground(p_jour4.getLesCours().get(0).getMatiere().getCouleurMatiere()); }
					panel11.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel11.add(label11, BorderLayout.CENTER);

					label12 = new JLabel();
					if(p_jour5.getLesCours().get(0).getMatiere() == null) { label12 = new JLabel("Libre", SwingConstants.CENTER); } else { label12 = new JLabel(p_jour5.getLesCours().get(0).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel12 = new JPanel();
					panel12.setLayout(new BorderLayout());
					if(p_jour5.getLesCours().get(0).getMatiere() == null) { panel12.setBackground(Color.white); } else { panel12.setBackground(p_jour5.getLesCours().get(0).getMatiere().getCouleurMatiere()); }
					panel12.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
					panel12.add(label12, BorderLayout.CENTER);

					// TROISIEME LIGNE DE GAUCHE A DROITE 

					label13 = new JLabel("10h - 12h", SwingConstants.CENTER);
					panel13 = new JPanel();
					panel13.setLayout(new BorderLayout());
					panel13.setBackground(Color.white);
					panel13.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel13.add(label13, BorderLayout.CENTER);

					label14 = new JLabel();
					if(p_jour1.getLesCours().get(1).getMatiere() == null) { label14 = new JLabel("Libre", SwingConstants.CENTER); } else { label14 = new JLabel(p_jour1.getLesCours().get(1).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel14 = new JPanel();
					panel14.setLayout(new BorderLayout());
					if(p_jour1.getLesCours().get(1).getMatiere() == null) { panel14.setBackground(Color.white); } else { panel14.setBackground(p_jour1.getLesCours().get(1).getMatiere().getCouleurMatiere()); }
					panel14.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel14.add(label14, BorderLayout.CENTER);

					label15 = new JLabel();
					if(p_jour2.getLesCours().get(1).getMatiere() == null) { label15 = new JLabel("Libre", SwingConstants.CENTER); } else { label15 = new JLabel(p_jour2.getLesCours().get(1).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel15 = new JPanel();
					panel15.setLayout(new BorderLayout());
					if(p_jour2.getLesCours().get(1).getMatiere() == null) { panel15.setBackground(Color.white); } else { panel15.setBackground(p_jour2.getLesCours().get(1).getMatiere().getCouleurMatiere()); }
					panel15.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel15.add(label15, BorderLayout.CENTER);

					label16 = new JLabel();
					if(p_jour3.getLesCours().get(1).getMatiere() == null) { label16 = new JLabel("Libre", SwingConstants.CENTER); } else { label16 = new JLabel(p_jour3.getLesCours().get(1).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel16 = new JPanel();
					panel16.setLayout(new BorderLayout());
					if(p_jour3.getLesCours().get(1).getMatiere() == null) { panel16.setBackground(Color.white); } else { panel16.setBackground(p_jour3.getLesCours().get(1).getMatiere().getCouleurMatiere()); }
					panel16.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel16.add(label16, BorderLayout.CENTER);

					label17 = new JLabel();
					if(p_jour4.getLesCours().get(1).getMatiere() == null) { label17 = new JLabel("Libre", SwingConstants.CENTER); } else { label17 = new JLabel(p_jour4.getLesCours().get(1).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel17 = new JPanel();
					panel17.setLayout(new BorderLayout());
					if(p_jour4.getLesCours().get(1).getMatiere() == null) { panel17.setBackground(Color.white); } else { panel17.setBackground(p_jour4.getLesCours().get(1).getMatiere().getCouleurMatiere()); }
					panel17.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel17.add(label17, BorderLayout.CENTER);

					label18 = new JLabel();
					if(p_jour5.getLesCours().get(1).getMatiere() == null) { label18 = new JLabel("Libre", SwingConstants.CENTER); } else { label18 = new JLabel(p_jour5.getLesCours().get(1).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel18 = new JPanel();
					panel18.setLayout(new BorderLayout());
					if(p_jour5.getLesCours().get(1).getMatiere() == null) { panel18.setBackground(Color.white); } else { panel18.setBackground(p_jour5.getLesCours().get(1).getMatiere().getCouleurMatiere()); }
					panel18.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
					panel18.add(label18, BorderLayout.CENTER);


					// QUATRIEME LIGNE DE GAUCHE A DROITE 

					label19 = new JLabel("14h - 16h", SwingConstants.CENTER);
					panel19 = new JPanel();
					panel19.setLayout(new BorderLayout());
					panel19.setBackground(Color.white);
					panel19.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel19.add(label19, BorderLayout.CENTER);

					label20 = new JLabel();
					if(p_jour1.getLesCours().get(2).getMatiere() == null) { label20 = new JLabel("Libre", SwingConstants.CENTER); } else { label20 = new JLabel(p_jour1.getLesCours().get(2).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel20 = new JPanel();
					panel20.setLayout(new BorderLayout());
					if(p_jour1.getLesCours().get(2).getMatiere() == null) { panel20.setBackground(Color.white); } else { panel20.setBackground(p_jour1.getLesCours().get(2).getMatiere().getCouleurMatiere()); }
					panel20.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel20.add(label20, BorderLayout.CENTER);

					label21 = new JLabel();
					if(p_jour2.getLesCours().get(2).getMatiere() == null) { label21 = new JLabel("Libre", SwingConstants.CENTER); } else { label21 = new JLabel(p_jour2.getLesCours().get(2).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel21 = new JPanel();
					panel21.setLayout(new BorderLayout());
					if(p_jour2.getLesCours().get(2).getMatiere() == null) { panel21.setBackground(Color.white); } else { panel21.setBackground(p_jour2.getLesCours().get(2).getMatiere().getCouleurMatiere()); }
					panel21.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel21.add(label21, BorderLayout.CENTER);

					label22 = new JLabel();
					if(p_jour3.getLesCours().get(2).getMatiere() == null) { label22 = new JLabel("Libre", SwingConstants.CENTER); } else { label22 = new JLabel(p_jour3.getLesCours().get(2).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel22 = new JPanel();
					panel22.setLayout(new BorderLayout());
					if(p_jour3.getLesCours().get(2).getMatiere() == null) { panel22.setBackground(Color.white); } else { panel22.setBackground(p_jour3.getLesCours().get(2).getMatiere().getCouleurMatiere()); }
					panel22.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel22.add(label22, BorderLayout.CENTER);

					label23 = new JLabel();
					if(p_jour4.getLesCours().get(2).getMatiere() == null) { label23 = new JLabel("Libre", SwingConstants.CENTER); } else { label23 = new JLabel(p_jour4.getLesCours().get(2).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel23 = new JPanel();
					panel23.setLayout(new BorderLayout());
					if(p_jour4.getLesCours().get(2).getMatiere() == null) { panel23.setBackground(Color.white); } else { panel23.setBackground(p_jour4.getLesCours().get(2).getMatiere().getCouleurMatiere()); }
					panel23.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel23.add(label23, BorderLayout.CENTER);

					label24 = new JLabel();
					if(p_jour5.getLesCours().get(2).getMatiere() == null) { label24 = new JLabel("Libre", SwingConstants.CENTER); } else { label24 = new JLabel(p_jour5.getLesCours().get(2).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel24 = new JPanel();
					panel24.setLayout(new BorderLayout());
					if(p_jour5.getLesCours().get(2).getMatiere() == null) { panel24.setBackground(Color.white); } else { panel24.setBackground(p_jour5.getLesCours().get(2).getMatiere().getCouleurMatiere()); }
					panel24.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
					panel24.add(label24, BorderLayout.CENTER);

					// CINQUIEME LIGNE DE GAUCHE A DROITE 

					label25 = new JLabel("16h - 18h", SwingConstants.CENTER);
					panel25 = new JPanel();
					panel25.setLayout(new BorderLayout());
					panel25.setBackground(Color.white);
					panel25.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
					panel25.add(label25, BorderLayout.CENTER);

					label26 = new JLabel();
					if(p_jour1.getLesCours().get(3).getMatiere() == null) { label26 = new JLabel("Libre", SwingConstants.CENTER); } else { label26 = new JLabel(p_jour1.getLesCours().get(3).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel26 = new JPanel();
					panel26.setLayout(new BorderLayout());
					if(p_jour1.getLesCours().get(3).getMatiere() == null) { panel26.setBackground(Color.white); } else { panel26.setBackground(p_jour1.getLesCours().get(3).getMatiere().getCouleurMatiere()); }
					panel26.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
					panel26.add(label26, BorderLayout.CENTER);

					label27 = new JLabel();
					if(p_jour2.getLesCours().get(3).getMatiere() == null) { label27 = new JLabel("Libre", SwingConstants.CENTER); } else { label27 = new JLabel(p_jour2.getLesCours().get(3).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel27 = new JPanel();
					panel27.setLayout(new BorderLayout());
					if(p_jour2.getLesCours().get(3).getMatiere() == null) { panel27.setBackground(Color.white); } else { panel27.setBackground(p_jour2.getLesCours().get(3).getMatiere().getCouleurMatiere()); }
					panel27.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
					panel27.add(label27, BorderLayout.CENTER);

					label28 = new JLabel();
					if(p_jour3.getLesCours().get(3).getMatiere() == null) { label28 = new JLabel("Libre", SwingConstants.CENTER); } else { label28 = new JLabel(p_jour3.getLesCours().get(3).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel28 = new JPanel();
					panel28.setLayout(new BorderLayout());
					if(p_jour3.getLesCours().get(3).getMatiere() == null) { panel28.setBackground(Color.white); } else { panel28.setBackground(p_jour3.getLesCours().get(3).getMatiere().getCouleurMatiere()); }
					panel28.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
					panel28.add(label28, BorderLayout.CENTER);

					label29 = new JLabel();
					if(p_jour4.getLesCours().get(3).getMatiere() == null) { label29 = new JLabel("Libre", SwingConstants.CENTER); } else { label29 = new JLabel(p_jour4.getLesCours().get(3).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel29 = new JPanel();
					panel29.setLayout(new BorderLayout());
					if(p_jour4.getLesCours().get(3).getMatiere() == null) { panel29.setBackground(Color.white); } else { panel29.setBackground(p_jour4.getLesCours().get(3).getMatiere().getCouleurMatiere()); }
					panel29.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
					panel29.add(label29, BorderLayout.CENTER);

					label30 = new JLabel();
					if(p_jour5.getLesCours().get(3).getMatiere() == null) { label30 = new JLabel("Libre", SwingConstants.CENTER); } else { label30 = new JLabel(p_jour5.getLesCours().get(3).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel30 = new JPanel();
					panel30.setLayout(new BorderLayout());
					if(p_jour5.getLesCours().get(3).getMatiere() == null) { panel30.setBackground(Color.white); } else { panel30.setBackground(p_jour5.getLesCours().get(3).getMatiere().getCouleurMatiere()); }
					panel30.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
					panel30.add(label30, BorderLayout.CENTER);
					break;

				case "Eleve":

					// Premiere ligne 

					label1 = new JLabel("/", SwingConstants.CENTER);
					panel1 = new JPanel();
					panel1.setLayout(new BorderLayout());
					panel1.setBackground(Color.white);
					panel1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel1.add(label1, BorderLayout.CENTER);

					label2 = new JLabel("LUNDI", SwingConstants.CENTER);
					panel2 = new JPanel();
					panel2.setLayout(new BorderLayout());
					panel2.setBackground(Color.white);
					panel2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel2.add(label2, BorderLayout.CENTER);

					label3 = new JLabel("MARDI", SwingConstants.CENTER);
					panel3 = new JPanel();
					panel3.setLayout(new BorderLayout());
					panel3.setBackground(Color.white);
					panel3.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel3.add(label3, BorderLayout.CENTER);

					label4 = new JLabel("MERCREDI", SwingConstants.CENTER);
					panel4 = new JPanel();
					panel4.setLayout(new BorderLayout());
					panel4.setBackground(Color.white);
					panel4.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel4.add(label4, BorderLayout.CENTER);

					label5 = new JLabel("JEUDI", SwingConstants.CENTER);
					panel5 = new JPanel();
					panel5.setLayout(new BorderLayout());
					panel5.setBackground(Color.white);
					panel5.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel5.add(label5, BorderLayout.CENTER);

					label6 = new JLabel("VENDREDI", SwingConstants.CENTER);
					panel6 = new JPanel();
					panel6.setLayout(new BorderLayout());
					panel6.setBackground(Color.white);
					panel6.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
					panel6.add(label6, BorderLayout.CENTER);


					// DEUXIEME LIGNE DE GAUCHE A DROITE 

					label7 = new JLabel("8h - 10h", SwingConstants.CENTER);
					panel7 = new JPanel();
					panel7.setLayout(new BorderLayout());
					panel7.setBackground(Color.white);
					panel7.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel7.add(label7, BorderLayout.CENTER);

					label8 = new JLabel();
					if(p_jour1.getLesCours().get(0).getMatiere() == null) { label8 = new JLabel("Libre", SwingConstants.CENTER); } else { label8 = new JLabel(p_jour1.getLesCours().get(0).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel8 = new JPanel();
					panel8.setLayout(new BorderLayout());
					if(p_jour1.getLesCours().get(0).getMatiere() == null) { panel8.setBackground(Color.white); } else { panel8.setBackground(p_jour1.getLesCours().get(0).getMatiere().getCouleurMatiere()); }
					panel8.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel8.add(label8, BorderLayout.CENTER);

					label9 = new JLabel();
					if(p_jour2.getLesCours().get(0).getMatiere() == null) { label9 = new JLabel("Libre", SwingConstants.CENTER); } else { label9 = new JLabel(p_jour2.getLesCours().get(0).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel9 = new JPanel();
					panel9.setLayout(new BorderLayout());
					if(p_jour2.getLesCours().get(0).getMatiere() == null) { panel9.setBackground(Color.white); } else { panel9.setBackground(p_jour2.getLesCours().get(0).getMatiere().getCouleurMatiere()); }
					panel9.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel9.add(label9, BorderLayout.CENTER);

					label10 = new JLabel();
					if(p_jour3.getLesCours().get(0).getMatiere() == null) { label10 = new JLabel("Libre", SwingConstants.CENTER); } else { label10 = new JLabel(p_jour3.getLesCours().get(0).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel10 = new JPanel();
					panel10.setLayout(new BorderLayout());
					if(p_jour3.getLesCours().get(0).getMatiere() == null) { panel10.setBackground(Color.white); } else { panel10.setBackground(p_jour3.getLesCours().get(0).getMatiere().getCouleurMatiere()); }
					panel10.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel10.add(label10, BorderLayout.CENTER);

					label11 = new JLabel();
					if(p_jour4.getLesCours().get(0).getMatiere() == null) { label11 = new JLabel("Libre", SwingConstants.CENTER); } else { label11 = new JLabel(p_jour4.getLesCours().get(0).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel11 = new JPanel();
					panel11.setLayout(new BorderLayout());
					if(p_jour4.getLesCours().get(0).getMatiere() == null) { panel11.setBackground(Color.white); } else { panel11.setBackground(p_jour4.getLesCours().get(0).getMatiere().getCouleurMatiere()); }
					panel11.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel11.add(label11, BorderLayout.CENTER);

					label12 = new JLabel();
					if(p_jour5.getLesCours().get(0).getMatiere() == null) { label12 = new JLabel("Libre", SwingConstants.CENTER); } else { label12 = new JLabel(p_jour5.getLesCours().get(0).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel12 = new JPanel();
					panel12.setLayout(new BorderLayout());
					if(p_jour5.getLesCours().get(0).getMatiere() == null) { panel12.setBackground(Color.white); } else { panel12.setBackground(p_jour5.getLesCours().get(0).getMatiere().getCouleurMatiere()); }
					panel12.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
					panel12.add(label12, BorderLayout.CENTER);

					// TROISIEME LIGNE DE GAUCHE A DROITE 

					label13 = new JLabel("10h - 12h", SwingConstants.CENTER);
					panel13 = new JPanel();
					panel13.setLayout(new BorderLayout());
					panel13.setBackground(Color.white);
					panel13.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel13.add(label13, BorderLayout.CENTER);

					label14 = new JLabel();
					if(p_jour1.getLesCours().get(1).getMatiere() == null) { label14 = new JLabel("Libre", SwingConstants.CENTER); } else { label14 = new JLabel(p_jour1.getLesCours().get(1).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel14 = new JPanel();
					panel14.setLayout(new BorderLayout());
					if(p_jour1.getLesCours().get(1).getMatiere() == null) { panel14.setBackground(Color.white); } else { panel14.setBackground(p_jour1.getLesCours().get(1).getMatiere().getCouleurMatiere()); }
					panel14.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel14.add(label14, BorderLayout.CENTER);

					label15 = new JLabel();
					if(p_jour2.getLesCours().get(1).getMatiere() == null) { label15 = new JLabel("Libre", SwingConstants.CENTER); } else { label15 = new JLabel(p_jour2.getLesCours().get(1).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel15 = new JPanel();
					panel15.setLayout(new BorderLayout());
					if(p_jour2.getLesCours().get(1).getMatiere() == null) { panel15.setBackground(Color.white); } else { panel15.setBackground(p_jour2.getLesCours().get(1).getMatiere().getCouleurMatiere()); }
					panel15.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel15.add(label15, BorderLayout.CENTER);

					label16 = new JLabel();
					if(p_jour3.getLesCours().get(1).getMatiere() == null) { label16 = new JLabel("Libre", SwingConstants.CENTER); } else { label16 = new JLabel(p_jour3.getLesCours().get(1).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel16 = new JPanel();
					panel16.setLayout(new BorderLayout());
					if(p_jour3.getLesCours().get(1).getMatiere() == null) { panel16.setBackground(Color.white); } else { panel16.setBackground(p_jour3.getLesCours().get(1).getMatiere().getCouleurMatiere()); }
					panel16.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel16.add(label16, BorderLayout.CENTER);

					label17 = new JLabel();
					if(p_jour4.getLesCours().get(1).getMatiere() == null) { label17 = new JLabel("Libre", SwingConstants.CENTER); } else { label17 = new JLabel(p_jour4.getLesCours().get(1).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel17 = new JPanel();
					panel17.setLayout(new BorderLayout());
					if(p_jour4.getLesCours().get(1).getMatiere() == null) { panel17.setBackground(Color.white); } else { panel17.setBackground(p_jour4.getLesCours().get(1).getMatiere().getCouleurMatiere()); }
					panel17.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel17.add(label17, BorderLayout.CENTER);

					label18 = new JLabel();
					if(p_jour5.getLesCours().get(1).getMatiere() == null) { label18 = new JLabel("Libre", SwingConstants.CENTER); } else { label18 = new JLabel(p_jour5.getLesCours().get(1).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel18 = new JPanel();
					panel18.setLayout(new BorderLayout());
					if(p_jour5.getLesCours().get(1).getMatiere() == null) { panel18.setBackground(Color.white); } else { panel18.setBackground(p_jour5.getLesCours().get(1).getMatiere().getCouleurMatiere()); }
					panel18.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
					panel18.add(label18, BorderLayout.CENTER);


					// QUATRIEME LIGNE DE GAUCHE A DROITE 

					label19 = new JLabel("14h - 16h", SwingConstants.CENTER);
					panel19 = new JPanel();
					panel19.setLayout(new BorderLayout());
					panel19.setBackground(Color.white);
					panel19.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel19.add(label19, BorderLayout.CENTER);

					label20 = new JLabel();
					if(p_jour1.getLesCours().get(2).getMatiere() == null) { label20 = new JLabel("Libre", SwingConstants.CENTER); } else { label20 = new JLabel(p_jour1.getLesCours().get(2).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel20 = new JPanel();
					panel20.setLayout(new BorderLayout());
					if(p_jour1.getLesCours().get(2).getMatiere() == null) { panel20.setBackground(Color.white); } else { panel20.setBackground(p_jour1.getLesCours().get(2).getMatiere().getCouleurMatiere()); }
					panel20.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel20.add(label20, BorderLayout.CENTER);

					label21 = new JLabel();
					if(p_jour2.getLesCours().get(2).getMatiere() == null) { label21 = new JLabel("Libre", SwingConstants.CENTER); } else { label21 = new JLabel(p_jour2.getLesCours().get(2).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel21 = new JPanel();
					panel21.setLayout(new BorderLayout());
					if(p_jour2.getLesCours().get(2).getMatiere() == null) { panel21.setBackground(Color.white); } else { panel21.setBackground(p_jour2.getLesCours().get(2).getMatiere().getCouleurMatiere()); }
					panel21.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel21.add(label21, BorderLayout.CENTER);

					label22 = new JLabel();
					if(p_jour3.getLesCours().get(2).getMatiere() == null) { label22 = new JLabel("Libre", SwingConstants.CENTER); } else { label22 = new JLabel(p_jour3.getLesCours().get(2).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel22 = new JPanel();
					panel22.setLayout(new BorderLayout());
					if(p_jour3.getLesCours().get(2).getMatiere() == null) { panel22.setBackground(Color.white); } else { panel22.setBackground(p_jour3.getLesCours().get(2).getMatiere().getCouleurMatiere()); }
					panel22.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel22.add(label22, BorderLayout.CENTER);

					label23 = new JLabel();
					if(p_jour4.getLesCours().get(2).getMatiere() == null) { label23 = new JLabel("Libre", SwingConstants.CENTER); } else { label23 = new JLabel(p_jour4.getLesCours().get(2).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel23 = new JPanel();
					panel23.setLayout(new BorderLayout());
					if(p_jour4.getLesCours().get(2).getMatiere() == null) { panel23.setBackground(Color.white); } else { panel23.setBackground(p_jour4.getLesCours().get(2).getMatiere().getCouleurMatiere()); }
					panel23.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
					panel23.add(label23, BorderLayout.CENTER);

					label24 = new JLabel();
					if(p_jour5.getLesCours().get(2).getMatiere() == null) { label24 = new JLabel("Libre", SwingConstants.CENTER); } else { label24 = new JLabel(p_jour5.getLesCours().get(2).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel24 = new JPanel();
					panel24.setLayout(new BorderLayout());
					if(p_jour5.getLesCours().get(2).getMatiere() == null) { panel24.setBackground(Color.white); } else { panel24.setBackground(p_jour5.getLesCours().get(2).getMatiere().getCouleurMatiere()); }
					panel24.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
					panel24.add(label24, BorderLayout.CENTER);

					// CINQUIEME LIGNE DE GAUCHE A DROITE 

					label25 = new JLabel("16h - 18h", SwingConstants.CENTER);
					panel25 = new JPanel();
					panel25.setLayout(new BorderLayout());
					panel25.setBackground(Color.white);
					panel25.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
					panel25.add(label25, BorderLayout.CENTER);

					label26 = new JLabel();
					if(p_jour1.getLesCours().get(3).getMatiere() == null) { label26 = new JLabel("Libre", SwingConstants.CENTER); } else { label26 = new JLabel(p_jour1.getLesCours().get(3).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel26 = new JPanel();
					panel26.setLayout(new BorderLayout());
					if(p_jour1.getLesCours().get(3).getMatiere() == null) { panel26.setBackground(Color.white); } else { panel26.setBackground(p_jour1.getLesCours().get(3).getMatiere().getCouleurMatiere()); }
					panel26.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
					panel26.add(label26, BorderLayout.CENTER);

					label27 = new JLabel();
					if(p_jour2.getLesCours().get(3).getMatiere() == null) { label27 = new JLabel("Libre", SwingConstants.CENTER); } else { label27 = new JLabel(p_jour2.getLesCours().get(3).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel27 = new JPanel();
					panel27.setLayout(new BorderLayout());
					if(p_jour2.getLesCours().get(3).getMatiere() == null) { panel27.setBackground(Color.white); } else { panel27.setBackground(p_jour2.getLesCours().get(3).getMatiere().getCouleurMatiere()); }
					panel27.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
					panel27.add(label27, BorderLayout.CENTER);

					label28 = new JLabel();
					if(p_jour3.getLesCours().get(3).getMatiere() == null) { label28 = new JLabel("Libre", SwingConstants.CENTER); } else { label28 = new JLabel(p_jour3.getLesCours().get(3).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel28 = new JPanel();
					panel28.setLayout(new BorderLayout());
					if(p_jour3.getLesCours().get(3).getMatiere() == null) { panel28.setBackground(Color.white); } else { panel28.setBackground(p_jour3.getLesCours().get(3).getMatiere().getCouleurMatiere()); }
					panel28.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
					panel28.add(label28, BorderLayout.CENTER);

					label29 = new JLabel();
					if(p_jour4.getLesCours().get(3).getMatiere() == null) { label29 = new JLabel("Libre", SwingConstants.CENTER); } else { label29 = new JLabel(p_jour4.getLesCours().get(3).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel29 = new JPanel();
					panel29.setLayout(new BorderLayout());
					if(p_jour4.getLesCours().get(3).getMatiere() == null) { panel29.setBackground(Color.white); } else { panel29.setBackground(p_jour4.getLesCours().get(3).getMatiere().getCouleurMatiere()); }
					panel29.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
					panel29.add(label29, BorderLayout.CENTER);

					label30 = new JLabel();
					if(p_jour5.getLesCours().get(3).getMatiere() == null) { label30 = new JLabel("Libre", SwingConstants.CENTER); } else { label30 = new JLabel(p_jour5.getLesCours().get(3).getMatiere().getNomMatiere(), SwingConstants.CENTER); }
					panel30 = new JPanel();
					panel30.setLayout(new BorderLayout());
					if(p_jour5.getLesCours().get(3).getMatiere() == null) { panel30.setBackground(Color.white); } else { panel30.setBackground(p_jour5.getLesCours().get(3).getMatiere().getCouleurMatiere()); }
					panel30.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
					panel30.add(label30, BorderLayout.CENTER);
					break;
				
		}

		


		//Les Adds 
		//System.out.println("On passe ici aussi");
        panelEDT.add(panel1);
		panelEDT.add(panel2);
		panelEDT.add(panel3);
		panelEDT.add(panel4);
		panelEDT.add(panel5);
		panelEDT.add(panel6);
		panelEDT.add(panel7);
		panelEDT.add(panel8);
		panelEDT.add(panel9);
		panelEDT.add(panel10);
		panelEDT.add(panel11);
		panelEDT.add(panel12);
		panelEDT.add(panel13);
		panelEDT.add(panel14);
		panelEDT.add(panel15);
		panelEDT.add(panel16);
		panelEDT.add(panel17);
		panelEDT.add(panel18);
		panelEDT.add(panel19);
		panelEDT.add(panel20);
		panelEDT.add(panel21);
		panelEDT.add(panel22);
		panelEDT.add(panel23);
		panelEDT.add(panel24);
		panelEDT.add(panel25);
		panelEDT.add(panel26);
		panelEDT.add(panel27);
		panelEDT.add(panel28);
		panelEDT.add(panel29);
		panelEDT.add(panel30);

		this.cardEDT.add(panelEDT, BorderLayout.CENTER);
		this.cardEDT.updateUI();
	}


	/* JPanel CombBox cardEDT */
	private JPanel panelBoutonCardEDT() {
		panelBoutonCardEDT = new JPanel();

		String[] typeStrings = {"Salle", "Professeur", "Classe", "Groupe", "Eleve"};
		cmbTypeList = new JComboBox<String>(typeStrings);
		cmbChoixList = new JComboBox<String>();

		panelBoutonCardEDT.add(cmbTypeList);
		panelBoutonCardEDT.add(cmbChoixList);
		panelBoutonCardEDT.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

	
		return panelBoutonCardEDT;
	}

	
	/* JPanel qui affiche les emplois du temps crées */
	private JPanel cardEDT() {
		cardEDT = new JPanel();
		cardEDT.setLayout(new BorderLayout());
		cardEDT.add(panelBoutonCardEDT(), BorderLayout.NORTH);
		cardEDT.add(panelEDT(), BorderLayout.CENTER);
		
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

		boutonPane.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

		
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
		String[] typeSalleStrings = {"Salle de cours", "Salle de TP", "Salle Informatique"};
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
		frameAjoutSalle.setSize(400,125);
		frameAjoutSalle.setTitle("Ajout d'une salle");
		frameAjoutSalle.setLocationRelativeTo(null);
		frameAjoutSalle.setResizable(false);
		frameAjoutSalle.setVisible(true);
		frameAjoutSalle.getContentPane().add(panelAjoutSalle_1, BorderLayout.CENTER);
		frameAjoutSalle.getContentPane().add(panelAjoutSalle_2, BorderLayout.SOUTH);
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
		frameAjoutProfesseur.setSize(400,145);
		frameAjoutProfesseur.setTitle("Ajout d'un professeur");
		frameAjoutProfesseur.setLocationRelativeTo(null);
		frameAjoutProfesseur.setResizable(false);
		frameAjoutProfesseur.setVisible(true);
		frameAjoutProfesseur.getContentPane().add(panelAjoutProfesseur_1, BorderLayout.CENTER);
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
		frameAjoutClasse.setSize(400,115);
		frameAjoutClasse.setTitle("Ajout d'une classe");
		frameAjoutClasse.setLocationRelativeTo(null);
		frameAjoutClasse.setResizable(false);
		frameAjoutClasse.setVisible(true);
		frameAjoutClasse.getContentPane().add(panelAjoutClasse_1, BorderLayout.CENTER);
		frameAjoutClasse.getContentPane().add(panelAjoutClasse_2, BorderLayout.SOUTH);
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
		frameAjoutGroupe.setSize(400,85);
		frameAjoutGroupe.setTitle("Ajout d'un groupe");
		frameAjoutGroupe.setLocationRelativeTo(null);
		frameAjoutGroupe.setResizable(false);
		frameAjoutGroupe.setVisible(true);
		frameAjoutGroupe.getContentPane().add(panelAjoutGroupe_1, BorderLayout.CENTER);
		frameAjoutGroupe.getContentPane().add(panelAjoutGroupe_2, BorderLayout.SOUTH);
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
		frameAjoutEleve.setSize(350,135);
		frameAjoutEleve.setTitle("Ajout d'un élève");
		frameAjoutEleve.setLocationRelativeTo(null);
		frameAjoutEleve.setResizable(false);
		frameAjoutEleve.setVisible(true);
		frameAjoutEleve.getContentPane().add(panelAjoutEleve_1, BorderLayout.CENTER);
		frameAjoutEleve.getContentPane().add(panelAjoutEleve_2, BorderLayout.SOUTH);
		frameAjoutEleve.setVisible(true);
		
		return frameAjoutEleve;
	}
	
	
	/* JPanel 'Ajout d'une matiere' */  
	public JFrame create_frameAjoutMatiere() {
		panelAjoutMatiere_1 = new JPanel();
		panelAjoutMatiere_1.setLayout(new GridLayout(7,2));
		
		labelNomMatiere = new JLabel("Nom :");
		tfNomMatiere = new JTextField(10);
		labelNbrHCMMatiere = new JLabel("Nombre d'heures en salle de Cours :");
		tfNbrHCMMatiere = new JTextField(10);
		labelNbrHTDMatiere = new JLabel("Nombre d'heures en Salle Informatique :");
		tfNbrHTDMatiere = new JTextField(10);
		labelNbrHTPMatiere = new JLabel("Nombre d'heures en Salle de TP :");
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
		frameAjoutMatiere.setSize(600,210);
		frameAjoutMatiere.setTitle("Ajout d'une matière");
		frameAjoutMatiere.setLocationRelativeTo(null);
		frameAjoutMatiere.setResizable(false);
		frameAjoutMatiere.setVisible(true);
		frameAjoutMatiere.getContentPane().add(panelAjoutMatiere_1, BorderLayout.CENTER);
		frameAjoutMatiere.getContentPane().add(panelAjoutMatiere_2, BorderLayout.SOUTH);
		frameAjoutMatiere.setVisible(true);
		
		return frameAjoutMatiere;
	}
	
	public void vider_button() {
		
		lesBoutonsSalle.clear();
		lesBoutonsProfesseur.clear();
		lesBoutonsClasse.clear();
		lesBoutonsGroupe.clear();
		lesBoutonsEleve.clear();
		lesBoutonsMatiere.clear();
		panelBoutonObjetsCrees.removeAll();
	}

	public void create_buttonSalle(boolean visible, String nomSalle) {
		JButton salle = new JButton(nomSalle);
		this.lesBoutonsSalle.add(salle);
		if(visible) { panelBoutonObjetsCrees.add(salle); }
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

	public JButton getBoutonSauvegarder() {
		return this.boutonSauvegarder;
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

	public ArrayList<JButton> getLesButtons(String type) {
		switch(type) {
			case "Salle":
				return this.lesBoutonsSalle;
				break;

			case "Professeur":
				return this.lesBoutonsProfesseur;
				break;

			case "Classe":
				return this.lesBoutonsClasse;
				break;
			
			case "Groupe":
				return this.lesBoutonsGroupe;
				break;
			
			case "Eleve":
				return this.lesBoutonsEleve;
				break;
		}
	}
	
}