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
									if( res == 0) { interfaceGraphique.create_buttonProfesseur(true, interfaceGraphique.getTfNomProfesseur().getText(), interfaceGraphique.getTfPrenomProfesseur().getText()); }
								}
							});
							break;

						case "Classe":
							interfaceGraphique.create_frameAjoutClasse();
							interfaceGraphique.getBoutonCouleurClasse().addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent event) {
									try {
									Color couleur = JColorChooser.showDialog(null, "couleur du fond", Color.WHITE);
									//System.out.println("Couleur :" + couleur.toString());
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
		
		/* Les boutons pour importer les différentes classes */
		/*interfaceGraphique.getBoutonImporter().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					
					switch(interfaceGraphique.getCmbMessageList().getSelectedItem().toString()) {

						case "Salle":
							interfaceGraphique.vider_button(interfaceGraphique.getCmbMessageList().getSelectedItem().toString());
							System.out.println("Nombre de salles : " + lesSalles.getLesSalles().size());
							lesSalles.chargerLesSalles();
							for(Salle salle : lesSalles.getLesSalles()) {
								interfaceGraphique.create_buttonSalle(true, salle.getNumeroSalle());
							}
							System.out.println("Nombre de salles : " + lesSalles.getLesSalles().size());
							break;

						case "Professeur":
							interfaceGraphique.vider_button(interfaceGraphique.getCmbMessageList().getSelectedItem().toString());
							System.out.println("Nombre de professeurs : " + lesProfesseurs.getLesProfesseurs().size());
							lesProfesseurs.chargerLesProfesseurs();
							for(Matiere matiere : lesMatieres.getLesMatieres()) {
								interfaceGraphique.create_buttonMatiere(false, matiere.getNomMatiere(), matiere.getCouleurMatiere());
							}
							for(Professeur professeur : lesProfesseurs.getLesProfesseurs()) {
								interfaceGraphique.create_buttonProfesseur(true, professeur.getNomProfesseur(), professeur.getPrenomProfesseur());
							}
							System.out.println("Nombre de professeurs : " + lesProfesseurs.getLesProfesseurs().size());
							break;

						case "Classe":
							interfaceGraphique.vider_button(interfaceGraphique.getCmbMessageList().getSelectedItem().toString());
							System.out.println("Nombre de classes : " + lesClasses.getLesClasses().size());
							lesClasses.chargerLesClasses();
							for(Classe classe : lesClasses.getLesClasses()) {
								interfaceGraphique.create_buttonClasse(true, classe.getNiveauClasse(), classe.getNomClasse(), classe.getCouleurClasse());
							}
							System.out.println("Nombre de classes : " + lesClasses.getLesClasses().size());
							break;

						case "Groupe":
							interfaceGraphique.vider_button(interfaceGraphique.getCmbMessageList().getSelectedItem().toString());
							System.out.println("Nombre de groupes : " + lesGroupes.getLesGroupes().size());
							lesClasses.chargerLesClasses();
							lesGroupes.chargerLesGroupes(lesClasses);
							for(Classe classe : lesClasses.getLesClasses()) {
								interfaceGraphique.create_buttonClasse(false, classe.getNiveauClasse(), classe.getNomClasse(), classe.getCouleurClasse());
							}
							for(Groupe groupe : lesGroupes.getLesGroupes()) {
								interfaceGraphique.create_buttonGroupe(true, groupe.getNomGroupe(), groupe.getClasseGroupe().getNomClasse());
							}
							System.out.println("Nombre de groupes : " + lesGroupes.getLesGroupes().size());
							System.out.println("Nombre de classes : " + lesClasses.getLesClasses().size());	
							break;

						case "Eleve":
							interfaceGraphique.vider_button(interfaceGraphique.getCmbMessageList().getSelectedItem().toString());
							System.out.println("Nombre d'eleves : " + lesEleves.getLesEleves().size());
							lesClasses.chargerLesClasses();
							lesGroupes.chargerLesGroupes(lesClasses);
							lesEleves.chargerLesEleves(lesClasses);
							for(Classe classe : lesClasses.getLesClasses()) {
								interfaceGraphique.create_buttonClasse(false, classe.getNiveauClasse(), classe.getNomClasse(), classe.getCouleurClasse());
							}
							for(Groupe groupe : lesGroupes.getLesGroupes()) {
								interfaceGraphique.create_buttonGroupe(false, groupe.getNomGroupe(), groupe.getClasseGroupe().getNomClasse());
							}
							for(Eleve eleve : lesEleves.getLesEleves()) {
								interfaceGraphique.create_buttonEleve(true, eleve.getNomEleve(), eleve.getPrenomEleve());
							}
							System.out.println("Nombre d'eleves : " + lesEleves.getLesEleves().size());
							break;

						case "Matiere":
							interfaceGraphique.vider_button(interfaceGraphique.getCmbMessageList().getSelectedItem().toString());
							System.out.println("Nombre de matière : " + lesMatieres.getLesMatieres().size());
							lesProfesseurs.chargerLesProfesseurs();
							lesMatieres.chargerLesMatieres(lesProfesseurs);
							for(Matiere matiere : lesMatieres.getLesMatieres()) {
								interfaceGraphique.create_buttonMatiere(true, matiere.getNomMatiere(), matiere.getCouleurMatiere());
							}
							System.out.println("Nombre de matière : " + lesMatieres.getLesMatieres().size());
							break;
					}
				}
		});*/


		/* Bouton qui permet de générer les emplois du temps */
		interfaceGraphique.getBoutonGenerer().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				for(Classe c : lesClasses.getLesClasses()) {
					c.setLesMatieres(lesMatieres);
				}
				lesEDT.genererLesEDT(lesClasses, lesEleves, lesSalles);
				lesEDT.afficherLesEDT(lesClasses);
			}
		});


		interfaceGraphique.getBoutonEDT().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					System.out.println("Chargement des JComboBox");
				}
		});


	}
	
	
	/* Savoir si on sauvegarde tous les champs ou les objets */ 
	/* Afficher les erreurs dans les ordes quand un champs n'est pas rempli */
	/* Si les objects Matiere/Couleur/etc ne peuvent pas etre importer, sauvegarder tous les champs de l'objet pour le recréer */
	/* Throws l'erreur quand on quitte le choix de couleur */
	/* Faire des JCardBox automatiquement suivant le nombre d'élement a charger, avec des boutons permettant d'edit les elemtns */
	/* Sauvegader peut etre l'objet matiere dans saveProfesseur au lieu de juste le nom */
	/* Pouvoir supprimer des objets */
	/* Arriver a tenir a jour les textes avec les objets */
	/* Arriver a générer un empoi du temps */
	/* Faire des classes EmploiDuTemps  */
	/* Enlever le focus des JComboBox quand ils sont premiers dans les fenetres */
}

