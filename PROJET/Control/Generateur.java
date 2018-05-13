package PROJET.Control;

import PROJET.View.*;
import PROJET.Model.*;

import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JColorChooser;


public class Generateur {
	
	private int res;
	private InterfaceGraphique interfaceGraphique;
	private MSalle lesSalles;
	private MProfesseur lesProfesseurs;
	private MClasse lesClasses;
	private MGroupe lesGroupes;
	private MEleve lesEleves;
	private MMatiere lesMatieres;
	private MEdt lesEDT;
	
	/* Constructeur du generateur */
	public Generateur() {
		
		this.interfaceGraphique = new InterfaceGraphique();
		this.lesSalles = new MSalle();
		this.lesProfesseurs = new MProfesseur();
		this.lesClasses = new MClasse();
		this.lesGroupes = new MGroupe();
		this.lesEleves = new MEleve();
		this.lesMatieres = new MMatiere();
		this.lesEDT = new MEdt();
		
		
		setupActionButton();
	}
	
	public void setupActionButton() {
		
		/* Les boutons pour ajouter un objet parmi les différentes classes */
		interfaceGraphique.getBoutonAjouter().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					
					switch(interfaceGraphique.getCmbMessageList().getSelectedItem().toString()) {

						case "Salle":
							interfaceGraphique.create_frameAjoutSalle();
							interfaceGraphique.getBoutonAccepterSalle().addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent event) {
									res = lesSalles.ajoutSalle(interfaceGraphique.getTfNumeroSalle().getText(), interfaceGraphique.getTfNombrePlaceSalle().getText(), interfaceGraphique.getCmbTypeSalle().getSelectedItem().toString(), lesEDT);
									if(res == 0) { interfaceGraphique.create_buttonSalle(true, interfaceGraphique.getTfNumeroSalle().getText()); }
								}
							});
							break;

						case "Professeur":
							interfaceGraphique.create_frameAjoutProfesseur();
							interfaceGraphique.getBoutonAccepterProfesseur().addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent event) {
									res = lesProfesseurs.ajoutProfesseur(interfaceGraphique.getTfNomProfesseur().getText(), interfaceGraphique.getTfPrenomProfesseur().getText(), interfaceGraphique.getTfNombreHeureProfesseur().getText(), lesEDT);
									if(res == 0) { interfaceGraphique.create_buttonProfesseur(true, interfaceGraphique.getTfNomProfesseur().getText(), interfaceGraphique.getTfPrenomProfesseur().getText()); }
								}
							});
							break;

						case "Classe":
							interfaceGraphique.create_frameAjoutClasse();
							interfaceGraphique.getBoutonCouleurClasse().addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent event) {
									try {
									Color couleur = JColorChooser.showDialog(null, "couleur du fond", Color.WHITE);
									interfaceGraphique.getBoutonCouleurClasse().setBackground(couleur);
									} catch (Exception ex) {
										System.out.println("Erreur : " + ex);
									}
								}
							});
							interfaceGraphique.getBoutonAccepterClasse().addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent event) {
									res = lesClasses.ajoutClasse(interfaceGraphique.getTfNomClasse().getText(), interfaceGraphique.getCmbNiveauClasse().getSelectedItem().toString(), interfaceGraphique.getBoutonCouleurClasse().getBackground(), lesEDT); 
									if(res == 0) { interfaceGraphique.create_buttonClasse(true, interfaceGraphique.getCmbNiveauClasse().getSelectedItem().toString(), interfaceGraphique.getTfNomClasse().getText(), interfaceGraphique.getBoutonCouleurClasse().getBackground()); }
								}
							});
							break;

						case "Groupe":
							interfaceGraphique.create_frameAjoutGroupe();
							lesGroupes.chargerComboBoxGroupe(lesClasses, interfaceGraphique.getCmbClasseGroupe());
							interfaceGraphique.getBoutonAccepterGroupe().addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent event) {
									res = lesGroupes.ajoutGroupe(interfaceGraphique.getTfNomGroupe().getText(), interfaceGraphique.getCmbClasseGroupe().getSelectedIndex(), lesClasses, lesEDT);
									if(res == 0) { interfaceGraphique.create_buttonGroupe(true, interfaceGraphique.getTfNomGroupe().getText(), interfaceGraphique.getCmbClasseGroupe().getSelectedItem().toString()); }
								}
							});
							break;

						case "Eleve":
							interfaceGraphique.create_frameAjoutEleve();
							lesEleves.chargerComboBoxEleve(lesClasses, interfaceGraphique.getCmbClasseEleve(), interfaceGraphique.getCmbGroupeEleve());
							interfaceGraphique.getCmbClasseEleve().addActionListener (new ActionListener () {
							    public void actionPerformed(ActionEvent event) {
							        lesEleves.majComboBoxEleve(lesClasses, interfaceGraphique.getCmbClasseEleve(), interfaceGraphique.getCmbGroupeEleve());
							    }
							});
							interfaceGraphique.getBoutonAccepterEleve().addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent event) {
									if(interfaceGraphique.getCmbGroupeEleve().getSelectedIndex() < 0) {
										res = lesEleves.ajoutEleve(interfaceGraphique.getTfNomEleve().getText(), interfaceGraphique.getTfPrenomEleve().getText(), interfaceGraphique.getCmbClasseEleve().getSelectedIndex(), lesClasses, lesEDT);
										if(res == 0) { interfaceGraphique.create_buttonEleve(true, interfaceGraphique.getTfNomEleve().getText(), interfaceGraphique.getTfPrenomEleve().getText()); }
									} 
									else {
										res = lesEleves.ajoutEleve(interfaceGraphique.getTfNomEleve().getText(), interfaceGraphique.getTfPrenomEleve().getText(), interfaceGraphique.getCmbClasseEleve().getSelectedIndex(), interfaceGraphique.getCmbGroupeEleve().getSelectedIndex(), lesClasses, lesEDT);
										if(res == 0) { interfaceGraphique.create_buttonEleve(true, interfaceGraphique.getTfNomEleve().getText(), interfaceGraphique.getTfPrenomEleve().getText()); }
									}
								}
							});
							break;

						case "Matiere":
							interfaceGraphique.create_frameAjoutMatiere();
							lesMatieres.chargerComboBoxProfesseurMatiere(lesProfesseurs, interfaceGraphique.getCmbProfesseurMatiere());
							interfaceGraphique.getBoutonCouleurMatiere().addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent event) {
										Color couleur = JColorChooser.showDialog(null, "Couleur du fond", Color.WHITE);
										System.out.println("Couleur :" + couleur.toString());
										interfaceGraphique.getBoutonCouleurMatiere().setBackground(couleur);
									}
							});
							interfaceGraphique.getBoutonAccepterMatiere().addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent event) {
									res = lesMatieres.ajoutMatiere(interfaceGraphique.getTfNomMatiere().getText(), interfaceGraphique.getTfNombreHeureCM().getText(), interfaceGraphique.getTfNombreHeureTD().getText(), interfaceGraphique.getTfNombreHeureTP().getText(), interfaceGraphique.getCmbNiveauMatiere().getSelectedItem().toString(), interfaceGraphique.getCmbProfesseurMatiere().getSelectedIndex(), interfaceGraphique.getBoutonCouleurMatiere().getBackground(), lesProfesseurs);
									if(res == 0) { interfaceGraphique.create_buttonMatiere(true, interfaceGraphique.getTfNomMatiere().getText(), interfaceGraphique.getBoutonCouleurMatiere().getBackground()); }
								}
							});
							break;
					}
				}
		});
		
		/* Le bouton qui permet de sauvegarder les différentes classes */
		interfaceGraphique.getBoutonSauvegarder().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				lesSalles.sauvegarderLesSalles();
				lesProfesseurs.sauvegarderLesProfesseurs();
				lesClasses.sauvegarderLesClasses();
				lesGroupes.sauvegarderLesGroupes(lesClasses);
				lesEleves.sauvegarderLesEleves(lesClasses);
				lesMatieres.sauvegarderLesMatieres(lesProfesseurs);
			}
		});

		/* Le bouton pour importer les classes à partir des fichiers de sauvegarde */
		interfaceGraphique.getBoutonImporter().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					
					
					interfaceGraphique.vider_button();
					lesSalles.chargerLesSalles();
					lesProfesseurs.chargerLesProfesseurs();
					lesClasses.chargerLesClasses();
					lesGroupes.chargerLesGroupes(lesClasses);
					lesEleves.chargerLesEleves(lesClasses);
					lesMatieres.chargerLesMatieres(lesProfesseurs, lesClasses);
					
					for(Salle salle : lesSalles.getLesSalles()) {
						interfaceGraphique.create_buttonSalle(false, salle.getNumeroSalle());
					}
					for(Professeur professeur : lesProfesseurs.getLesProfesseurs()) {
						interfaceGraphique.create_buttonProfesseur(false, professeur.getNomProfesseur(), professeur.getPrenomProfesseur());
					}			
					for(Classe classe : lesClasses.getLesClasses()) {
						interfaceGraphique.create_buttonClasse(false, classe.getNiveauClasse(), classe.getNomClasse(), classe.getCouleurClasse());
					}
					for(Groupe groupe : lesGroupes.getLesGroupes()) {
						interfaceGraphique.create_buttonGroupe(false, groupe.getNomGroupe(), groupe.getClasseGroupe().getNomClasse());
					}
					for(Eleve eleve : lesEleves.getLesEleves()) {
						interfaceGraphique.create_buttonEleve(false, eleve.getNomEleve(), eleve.getPrenomEleve());
					}
					for(Matiere matiere : lesMatieres.getLesMatieres()) {
						interfaceGraphique.create_buttonMatiere(false, matiere.getNomMatiere(), matiere.getCouleurMatiere());
					}

					interfaceGraphique.update_panelBoutonObjetsCrees();
				}
		});


		/* Bouton qui permet de générer les emplois du temps */
		interfaceGraphique.getBoutonGenerer().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				for(Classe c : lesClasses.getLesClasses()) {
					c.setLesMatieres(lesMatieres);
				}
				lesEDT.genererLesEDT(lesClasses, lesEleves, lesSalles);
				lesEDT.afficherLesEDT(lesClasses);
				lesEDT.afficherLesEDT(lesProfesseurs, lesClasses);
			}
		});


		/* Chargement du JComboBox qui contient les salles */
		interfaceGraphique.getBoutonEDT().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					lesSalles.remplirJComboBoxSalle(interfaceGraphique.getCmbChoixList(), lesSalles); 	
				}
		});

		/* Chargement des JComboBox sur l'onglet des EDT */
		interfaceGraphique.getCmbTypeList().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				switch(interfaceGraphique.getCmbTypeList().getSelectedItem().toString()) {

						case "Salle":
							lesSalles.remplirJComboBoxSalle(interfaceGraphique.getCmbChoixList(), lesSalles);
							break;

						case "Professeur":
							lesProfesseurs.remplirJComboBoxProfesseur(interfaceGraphique.getCmbChoixList(), lesProfesseurs);
							break;

						case "Classe":
							lesClasses.remplirJComboBoxClasse(interfaceGraphique.getCmbChoixList(), lesClasses);
							break;
						
						case "Groupe":
							lesGroupes.remplirJComboBoxGroupe(interfaceGraphique.getCmbChoixList(), lesGroupes);
							break;
						
						case "Eleve":
							lesEleves.remplirJComboBoxEleve(interfaceGraphique.getCmbChoixList(), lesEleves);
							break;
				}
			}
		});

		/* Chargement des EDT suivant ce qui est selectionné dans les JComboBox */
		interfaceGraphique.getCmbChoixList().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				switch(interfaceGraphique.getCmbTypeList().getSelectedItem().toString()) {
						case "Salle":
							System.out.println("Emploi du temps des salles chargées");
							lesSalles.remplirJComboBoxSalle(interfaceGraphique.getCmbChoixList(), lesSalles);
							interfaceGraphique.updateCardEDT("Salle", lesSalles.getLesSalles().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(0), lesSalles.getLesSalles().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(1), lesSalles.getLesSalles().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(2), lesSalles.getLesSalles().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(3), lesSalles.getLesSalles().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(4));
							break;

						case "Professeur":
							lesProfesseurs.remplirJComboBoxProfesseur(interfaceGraphique.getCmbChoixList(), lesProfesseurs);
							interfaceGraphique.updateCardEDT("Professeur", lesProfesseurs.getLesProfesseurs().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(0), lesProfesseurs.getLesProfesseurs().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(1), lesProfesseurs.getLesProfesseurs().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(2), lesProfesseurs.getLesProfesseurs().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(3), lesProfesseurs.getLesProfesseurs().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(4));
							break;

						/*case "Classe":
							lesClasses.remplirJComboBoxClasse(interfaceGraphique.getCmbChoixList(), lesClasses);
							interfaceGraphique.updateCardEDT("Classe", lesClasses.getLesClasses().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(0), lesClasses.getLesClasses().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(1), lesClasses.getLesClasses().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(2), lesClasses.getLesClasses().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(3), lesClasses.getLesClasses().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(4));
							break;
						
						case "Groupe":
							lesGroupes.remplirJComboBoxGroupe(interfaceGraphique.getCmbChoixList(), lesGroupes);
							interfaceGraphique.updateCardEDT("Groupe", lesGroupes.getLesGroupes().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(0), lesGroupes.getLesGroupes().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(1), lesGroupes.getLesGroupes().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(2), lesGroupes.getLesGroupes().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(3), lesGroupes.getLesGroupes().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(4));
							break;
						
						case "Eleve":
							lesEleves.remplirJComboBoxEleve(interfaceGraphique.getCmbChoixList(), lesEleves);
							interfaceGraphique.updateCardEDT("Eleve", lesEleves.getLesEleves().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(0), lesEleves.getLesEleves().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(1), lesEleves.getLesEleves().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(2), lesEleves.getLesEleves().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(3), lesEleves.getLesEleves().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(4));
							break;*/
				}
			}
		});

	}
	
}
