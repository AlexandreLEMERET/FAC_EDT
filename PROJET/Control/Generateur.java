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
import javax.swing.JButton;


public class Generateur {
	
	private int res, index;
	private String type;
	private String[] typeButtonsStrings;
	private JButton tmpButton;
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
									res = lesSalles.ajoutSalle(interfaceGraphique.getTfNumeroSalle().getText(), interfaceGraphique.getTfNombrePlaceSalle().getText(), interfaceGraphique.getCmbTypeSalle().getSelectedIndex(), lesEDT);
									if(res == 0) { 
										interfaceGraphique.create_buttonSalle(true, interfaceGraphique.getTfNumeroSalle().getText()); 
									enleverLesListeners();
									mettreLesListeners("Salle");
									}
								}
							});
							break;

						case "Professeur":
							interfaceGraphique.create_frameAjoutProfesseur();
							interfaceGraphique.getBoutonAccepterProfesseur().addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent event) {
									res = lesProfesseurs.ajoutProfesseur(interfaceGraphique.getTfNomProfesseur().getText(), interfaceGraphique.getTfPrenomProfesseur().getText(), interfaceGraphique.getTfNombreHeureProfesseur().getText(), lesEDT, lesMatieres);
									if(res == 0) { 
										interfaceGraphique.create_buttonProfesseur(true, interfaceGraphique.getTfNomProfesseur().getText(), interfaceGraphique.getTfPrenomProfesseur().getText());
									enleverLesListeners(); 
									mettreLesListeners("Professeur");
									}
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
									if(res == 0) { 
										interfaceGraphique.create_buttonClasse(true, interfaceGraphique.getCmbNiveauClasse().getSelectedItem().toString(), interfaceGraphique.getTfNomClasse().getText(), interfaceGraphique.getBoutonCouleurClasse().getBackground()); 
									enleverLesListeners();
									mettreLesListeners("Classe");
									}
								}
							});
							break;

						case "Groupe":
							interfaceGraphique.create_frameAjoutGroupe();
							lesGroupes.chargerComboBoxGroupe(lesClasses, interfaceGraphique.getCmbClasseGroupe());
							interfaceGraphique.getBoutonAccepterGroupe().addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent event) {
									res = lesGroupes.ajoutGroupe(interfaceGraphique.getTfNomGroupe().getText(), interfaceGraphique.getCmbClasseGroupe().getSelectedIndex(), lesClasses, lesEDT);
									if(res == 0) { 
										interfaceGraphique.create_buttonGroupe(true, interfaceGraphique.getTfNomGroupe().getText(), interfaceGraphique.getCmbClasseGroupe().getSelectedItem().toString()); 
									enleverLesListeners();
									mettreLesListeners("Groupe");
									}
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
										if(res == 0) { 
											interfaceGraphique.create_buttonEleve(true, interfaceGraphique.getTfNomEleve().getText(), interfaceGraphique.getTfPrenomEleve().getText()); 
										enleverLesListeners();
										mettreLesListeners("Eleve");
										}
									} 
									else {
										res = lesEleves.ajoutEleve(interfaceGraphique.getTfNomEleve().getText(), interfaceGraphique.getTfPrenomEleve().getText(), interfaceGraphique.getCmbClasseEleve().getSelectedIndex(), interfaceGraphique.getCmbGroupeEleve().getSelectedIndex(), lesClasses, lesEDT);
										if(res == 0) { 
											interfaceGraphique.create_buttonEleve(true, interfaceGraphique.getTfNomEleve().getText(), interfaceGraphique.getTfPrenomEleve().getText()); 
										enleverLesListeners();
										mettreLesListeners("Eleve");
										}
									}
								}
							});
							break;

						case "Matiere":
							interfaceGraphique.create_frameAjoutMatiere();
							lesMatieres.chargerComboBoxProfesseurMatiere(lesProfesseurs, interfaceGraphique.getCmbProfesseurMatiere());
							interfaceGraphique.getBoutonCouleurMatiere().addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent event) {
										try {
										Color couleur = JColorChooser.showDialog(null, "Couleur du fond", Color.WHITE);
										interfaceGraphique.getBoutonCouleurMatiere().setBackground(couleur);
										} catch (Exception ex) {
											System.out.println("Erreur : " + ex);
										}
									}
							});
							interfaceGraphique.getBoutonAccepterMatiere().addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent event) {
									res = lesMatieres.ajoutMatiere(interfaceGraphique.getTfNomMatiere().getText(), interfaceGraphique.getTfNombreHeureCM().getText(), interfaceGraphique.getTfNombreHeureTD().getText(), interfaceGraphique.getTfNombreHeureTP().getText(), interfaceGraphique.getCmbNiveauMatiere().getSelectedItem().toString(), interfaceGraphique.getCmbProfesseurMatiere().getSelectedIndex(), interfaceGraphique.getBoutonCouleurMatiere().getBackground(), lesProfesseurs);
									if(res == 0) { 
										interfaceGraphique.create_buttonMatiere(true, interfaceGraphique.getTfNomMatiere().getText(), interfaceGraphique.getBoutonCouleurMatiere().getBackground()); 
										enleverLesListeners();
										mettreLesListeners("Matiere");
									}
								}
							});
							break;
					}
				}
		});
		
		/* On ajoute les listeners sur les boutons*/
		interfaceGraphique.getCmbMessageList().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				switch(interfaceGraphique.getCmbMessageList().getSelectedItem().toString()) {
					case "Salle" :
						enleverLesListeners();
						mettreLesListeners("Salle");
						break;

					case "Professeur" :
						enleverLesListeners();
						mettreLesListeners("Professeur"); 
						break;

					case "Classe" : 
						enleverLesListeners();
						mettreLesListeners("Classe");
						break;

					case "Groupe" : 
						enleverLesListeners();
						mettreLesListeners("Groupe");
						break;

					case "Eleve" :
						enleverLesListeners();
						mettreLesListeners("Eleve");
						break;

					case "Matiere" : 
						enleverLesListeners();
						mettreLesListeners("Matiere");
						break;
				}
			}
		});

		/* Le bouton qui permet de sauvegarder les différentes classes */
		interfaceGraphique.getBoutonSauvegarder().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				String options[] = {"Oui", "Annuler"};

				int resultat = JOptionPane.showOptionDialog( null, "Voulez-vous vraiment sauvegarder ?", "Confirmation de sauvegarde", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);
				if(resultat == 0) {
					lesSalles.sauvegarderLesSalles();
					lesProfesseurs.sauvegarderLesProfesseurs();
					lesClasses.sauvegarderLesClasses();
					lesGroupes.sauvegarderLesGroupes(lesClasses);
					lesEleves.sauvegarderLesEleves(lesClasses);
					lesMatieres.sauvegarderLesMatieres(lesProfesseurs);
				}
			}
		});

		/* Le bouton pour importer les classes à partir des fichiers de sauvegarde */
		interfaceGraphique.getBoutonImporter().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					
					String options[] = {"Oui", "Annuler"};

					int resultat = JOptionPane.showOptionDialog(null, "Voulez-vous vraiment charger votres sauvegarde ?", "Confirmation de chargement", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);
					if(resultat == 0) {

						lesSalles = new MSalle();
						lesProfesseurs = new MProfesseur();
						lesClasses = new MClasse();
						lesGroupes = new MGroupe();
						lesEleves = new MEleve();
						lesMatieres = new MMatiere();
						lesEDT = new MEdt();


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
						enleverLesListeners();
						mettreLesListeners(interfaceGraphique.getCmbMessageList().getSelectedItem().toString());
					}
				}
		});


		/* Bouton qui permet de générer les emplois du temps */
		interfaceGraphique.getBoutonGenerer().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				for(Classe c : lesClasses.getLesClasses()) {
					c.setLesMatieres(lesMatieres);
				}
				for(Classe c : lesClasses.getLesClasses()) {
					for(Groupe g : c.getLesGroupesClasse()) {
						g.setLesMatieres(lesMatieres, c);
					}
				}
				lesEDT.viderEDT(lesSalles, lesProfesseurs, lesClasses, lesGroupes, lesEleves);
				lesEDT.genererLesEDT(lesClasses, lesEleves, lesSalles);
				lesEleves.setEDTEleve(lesClasses, lesGroupes);
				//lesEDT.afficherLesEDT(lesClasses);
				//lesEDT.afficherLesEDT(lesSalles);
			}
		});


		/* Chargement du JComboBox qui contient les salles */
		interfaceGraphique.getBoutonEDT().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					lesSalles.remplirJComboBoxSalle(interfaceGraphique.getCmbChoixList(), lesSalles); 	
					if(lesSalles.getLesSalles().size() > 0) {
						interfaceGraphique.updateCardEDT("Salle", lesSalles.getLesSalles().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(0), lesSalles.getLesSalles().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(1), lesSalles.getLesSalles().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(2), lesSalles.getLesSalles().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(3), lesSalles.getLesSalles().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(4));
					}
				}
		});

		
		/* Chargement des EDT suivant ce qui est selectionné dans cmbTypeList */
		interfaceGraphique.getCmbTypeList().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				switch(interfaceGraphique.getCmbTypeList().getSelectedItem().toString()) {
						case "Salle":
							lesSalles.remplirJComboBoxSalle(interfaceGraphique.getCmbChoixList(), lesSalles);
							if(lesSalles.getLesSalles().size() > 0) { interfaceGraphique.updateCardEDT("Salle", lesSalles.getLesSalles().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(0), lesSalles.getLesSalles().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(1), lesSalles.getLesSalles().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(2), lesSalles.getLesSalles().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(3), lesSalles.getLesSalles().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(4)); }
							else { interfaceGraphique.panelEDTblanc(); }
							break;

						case "Professeur":
							lesProfesseurs.remplirJComboBoxProfesseur(interfaceGraphique.getCmbChoixList(), lesProfesseurs);
							if(lesProfesseurs.getLesProfesseurs().size() > 0) { interfaceGraphique.updateCardEDT("Professeur", lesProfesseurs.getLesProfesseurs().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(0), lesProfesseurs.getLesProfesseurs().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(1), lesProfesseurs.getLesProfesseurs().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(2), lesProfesseurs.getLesProfesseurs().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(3), lesProfesseurs.getLesProfesseurs().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(4)); }
							else { interfaceGraphique.panelEDTblanc(); }
							break;

						case "Classe":
							lesClasses.remplirJComboBoxClasse(interfaceGraphique.getCmbChoixList(), lesClasses);
							if(lesClasses.getLesClasses().size() > 0) { interfaceGraphique.updateCardEDT("Classe", lesClasses.getLesClasses().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(0), lesClasses.getLesClasses().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(1), lesClasses.getLesClasses().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(2), lesClasses.getLesClasses().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(3), lesClasses.getLesClasses().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(4)); }
							else { interfaceGraphique.panelEDTblanc(); }
							break;
						
						case "Groupe":
							lesGroupes.remplirJComboBoxGroupe(interfaceGraphique.getCmbChoixList(), lesGroupes);
							if(lesGroupes.getLesGroupes().size() > 0) { interfaceGraphique.updateCardEDT("Groupe", lesGroupes.getLesGroupes().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(0), lesGroupes.getLesGroupes().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(1), lesGroupes.getLesGroupes().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(2), lesGroupes.getLesGroupes().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(3), lesGroupes.getLesGroupes().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(4)); }
							else { interfaceGraphique.panelEDTblanc(); }
							break;
						
						case "Eleve":
							lesEleves.remplirJComboBoxEleve(interfaceGraphique.getCmbChoixList(), lesEleves);
							if(lesEleves.getLesEleves().size() > 0) { interfaceGraphique.updateCardEDT("Eleve", lesEleves.getLesEleves().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(0), lesEleves.getLesEleves().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(1), lesEleves.getLesEleves().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(2), lesEleves.getLesEleves().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(3), lesEleves.getLesEleves().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(4)); }
							else { interfaceGraphique.panelEDTblanc(); }
							break;
				}
			}
		});

		/* Chargement des EDT suivant ce qui est selectionné dans cmbChoixList */
		interfaceGraphique.getCmbChoixList().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				switch(interfaceGraphique.getCmbTypeList().getSelectedItem().toString()) {
						case "Salle":
							if(interfaceGraphique.getCmbChoixList().getItemCount() > 0) { interfaceGraphique.updateCardEDT("Salle", lesSalles.getLesSalles().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(0), lesSalles.getLesSalles().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(1), lesSalles.getLesSalles().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(2), lesSalles.getLesSalles().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(3), lesSalles.getLesSalles().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(4)); } 
							break;

						case "Professeur":
							if(interfaceGraphique.getCmbChoixList().getItemCount() > 0) { interfaceGraphique.updateCardEDT("Professeur", lesProfesseurs.getLesProfesseurs().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(0), lesProfesseurs.getLesProfesseurs().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(1), lesProfesseurs.getLesProfesseurs().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(2), lesProfesseurs.getLesProfesseurs().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(3), lesProfesseurs.getLesProfesseurs().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(4)); }
							break;

						case "Classe":
							if(interfaceGraphique.getCmbChoixList().getItemCount() > 0) { interfaceGraphique.updateCardEDT("Classe", lesClasses.getLesClasses().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(0), lesClasses.getLesClasses().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(1), lesClasses.getLesClasses().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(2), lesClasses.getLesClasses().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(3), lesClasses.getLesClasses().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(4)); }
							break;
						
						case "Groupe":
							if(interfaceGraphique.getCmbChoixList().getItemCount() > 0) { interfaceGraphique.updateCardEDT("Groupe", lesGroupes.getLesGroupes().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(0), lesGroupes.getLesGroupes().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(1), lesGroupes.getLesGroupes().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(2), lesGroupes.getLesGroupes().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(3), lesGroupes.getLesGroupes().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(4)); }
							break;
						
						case "Eleve":
							if(interfaceGraphique.getCmbChoixList().getItemCount() > 0) { interfaceGraphique.updateCardEDT("Eleve", lesEleves.getLesEleves().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(0), lesEleves.getLesEleves().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(1), lesEleves.getLesEleves().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(2), lesEleves.getLesEleves().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(3), lesEleves.getLesEleves().get(interfaceGraphique.getCmbChoixList().getSelectedIndex()).getEDT().getLesJours().get(4)); }
							break;
				}
			}
		});

	}

	/* Modification des elements*/
	public void mettreLesListeners(String p_type) {
		index = 0;
		type = p_type;
		for(JButton b : interfaceGraphique.getLesButtons(type)) {
			final JButton bF = b;
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					switch(type) {
						case "Salle" :
							index = interfaceGraphique.getLesButtons(type).indexOf(bF);
							interfaceGraphique.create_frameModificationSalle(lesSalles.getLesSalles().get(index).getNumeroSalle(), lesSalles.getLesSalles().get(index).getNombrePlacesSalle(), lesSalles.getLesSalles().get(index).getTypeSalle());
							
							interfaceGraphique.getBoutonModifierSalle().addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent event) {
									lesSalles.modifierSalle(interfaceGraphique.getTfNumeroSalle().getText(), interfaceGraphique.getTfNombrePlaceSalle().getText(), interfaceGraphique.getCmbTypeSalle().getSelectedIndex(), index);
									interfaceGraphique.update_buttonSalle(interfaceGraphique.getTfNumeroSalle().getText(), index);
								}
							});

							interfaceGraphique.getBoutonSupprimerSalle().addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent event) {
									lesSalles.supprimerSalle(index);
									interfaceGraphique.rm_buttonSalle(index);
									interfaceGraphique.getJFrameModificationSalle().dispose();
								}
							});
							break;

						case "Professeur" :
							index = interfaceGraphique.getLesButtons(type).indexOf(bF);
							interfaceGraphique.create_frameModificationProfesseur(lesProfesseurs.getLesProfesseurs().get(index).getNomProfesseur(), lesProfesseurs.getLesProfesseurs().get(index).getPrenomProfesseur(), lesProfesseurs.getLesProfesseurs().get(index).getNombreHeuresProfesseur()); 
							
							interfaceGraphique.getBoutonModifierProfesseur().addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent event) {
									lesProfesseurs.modifierProfesseur(interfaceGraphique.getTfNomProfesseur().getText(), interfaceGraphique.getTfPrenomProfesseur().getText(), interfaceGraphique.getTfNombreHeureProfesseur().getText(), lesMatieres, index);
									interfaceGraphique.update_buttonProfesseur(interfaceGraphique.getTfNomProfesseur().getText(), interfaceGraphique.getTfPrenomProfesseur().getText(), index);
								}
							});

							interfaceGraphique.getBoutonSupprimerProfesseur().addActionListener(new ActionListener(){
								public void actionPerformed(ActionEvent event) {
									ArrayList<Integer> lesIndexMatieres = new ArrayList<Integer>();
									lesIndexMatieres = lesProfesseurs.supprimerProfesseur(index, lesMatieres, lesIndexMatieres);
									interfaceGraphique.rm_buttonProfesseur(index);
									while(lesIndexMatieres.size() != 0) {
										int maxIndex = 0;
										for(int i : lesIndexMatieres) {
											if(i > maxIndex) {
												maxIndex = i;
											}
										}
										interfaceGraphique.rm_buttonMatiere(maxIndex);
										lesMatieres.getLesMatieres().remove(maxIndex);
										lesIndexMatieres.remove(maxIndex);
									}
									interfaceGraphique.getJFrameModificationProfesseur().dispose();
								}
							});

							break;

						case "Classe" : 
							index = interfaceGraphique.getLesButtons(type).indexOf(bF);
							interfaceGraphique.create_frameModificationClasse(lesClasses.getLesClasses().get(index).getNomClasse(), lesClasses.getLesClasses().get(index).getNiveauClasse(), lesClasses.getLesClasses().get(index).getCouleurClasse());
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
							
							interfaceGraphique.getBoutonModifierClasse().addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent event) {
									lesClasses.modifierClasse(interfaceGraphique.getTfNomClasse().getText(), interfaceGraphique.getCmbNiveauClasse().getSelectedItem().toString(), interfaceGraphique.getBoutonCouleurClasse().getBackground(), index); 
									interfaceGraphique.update_buttonClasse(interfaceGraphique.getCmbNiveauClasse().getSelectedItem().toString(), interfaceGraphique.getTfNomClasse().getText(), interfaceGraphique.getBoutonCouleurClasse().getBackground(), index);
								}
							});

							interfaceGraphique.getBoutonSupprimerClasse().addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent event) {
									lesClasses.supprimerClasse(index);
									interfaceGraphique.rm_buttonClasse(index);
									interfaceGraphique.getJFrameModificationClasse().dispose();
								}
							});
							break;

						case "Groupe" : 
							index = interfaceGraphique.getLesButtons(type).indexOf(bF);
							interfaceGraphique.create_frameModificationGroupe(lesGroupes.getLesGroupes().get(index).getNomGroupe(), lesGroupes.getLesGroupes().get(index).getClasseGroupe(), lesClasses);
							interfaceGraphique.getBoutonModifierGroupe().addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent event) {
									lesGroupes.modifierGroupe(interfaceGraphique.getTfNomGroupe().getText(), interfaceGraphique.getCmbClasseGroupe().getSelectedIndex(), lesClasses, lesEleves, index);
									interfaceGraphique.update_buttonGroupe(interfaceGraphique.getTfNomGroupe().getText(), interfaceGraphique.getCmbClasseGroupe().getSelectedItem().toString(), index);
								}
							});

							interfaceGraphique.getBoutonSupprimerGroupe().addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent event) {
									ArrayList<Integer> lesIndexEleves = new ArrayList<Integer>();
									lesIndexEleves = lesGroupes.supprimerGroupe(index, lesEleves, lesIndexEleves);
									interfaceGraphique.rm_buttonGroupe(index);
									interfaceGraphique.getJFrameModificationGroupe().dispose();

									System.out.println("Les eleves nb : " + lesEleves.getLesEleves().size());
									while(lesIndexEleves.size() != 0) {
										int maxIndex = 0;
										for(int i : lesIndexEleves) {
											if(i > maxIndex) {
												maxIndex = i;
											}
										}
										System.out.println("Max index : " + maxIndex);
										System.out.println("Les eleves nb : " + lesEleves.getLesEleves().size());
										interfaceGraphique.rm_buttonEleve(maxIndex);
										lesEleves.getLesEleves().remove(maxIndex);
										System.out.println("Les eleves nb : " + lesEleves.getLesEleves().size());
										lesIndexEleves.remove(new Integer(maxIndex));
									}
								}
							});
							break;

						case "Eleve" :
							index = interfaceGraphique.getLesButtons(type).indexOf(bF);
							if(interfaceGraphique.getCmbGroupeEleve().getSelectedIndex() < 0) {
								interfaceGraphique.create_frameModificationEleve(interfaceGraphique.getTfNomEleve().getText(), interfaceGraphique.getTfPrenomEleve().getText(), interfaceGraphique.getCmbClasseEleve().getSelectedIndex(), lesClasses, lesGroupes);
							} 
							else {
								interfaceGraphique.create_frameModificationEleve(interfaceGraphique.getTfNomEleve().getText(), interfaceGraphique.getTfPrenomEleve().getText(), interfaceGraphique.getCmbClasseEleve().getSelectedIndex(), interfaceGraphique.getCmbGroupeEleve().getSelectedIndex(), lesClasses, lesGroupes);
							}

							interfaceGraphique.getBoutonModifierEleve().addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent event) {
									lesEleves.modifierEleve(interfaceGraphique.getTfNomEleve().getText(), interfaceGraphique.getTfPrenomEleve().getText(), interfaceGraphique.getCmbClasseEleve().getSelectedIndex(), interfaceGraphique.getCmbGroupeEleve().getSelectedIndex(), lesClasses, lesGroupes, index);
									interfaceGraphique.update_buttonEleve(interfaceGraphique.getTfNomEleve().getText(), interfaceGraphique.getTfPrenomEleve().getText(), index);
								}
							});

							interfaceGraphique.getBoutonSupprimerEleve().addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent event) {
									lesEleves.supprimerEleve(index);
									interfaceGraphique.rm_buttonEleve(index);
									interfaceGraphique.getJFrameModificationEleve().dispose();
								}
							});

							break;

						case "Matiere" : 
							index = interfaceGraphique.getLesButtons(type).indexOf(bF);
							interfaceGraphique.create_frameModificationMatiere(lesMatieres.getLesMatieres().get(index).getNomMatiere(), String.valueOf(lesMatieres.getLesMatieres().get(index).getNombreHeureCM()), String.valueOf(lesMatieres.getLesMatieres().get(index).getNombreHeureTD()), String.valueOf(lesMatieres.getLesMatieres().get(index).getNombreHeureTP()), lesMatieres.getLesMatieres().get(index).getNiveauMatiere(), lesProfesseurs.getLesProfesseurs().indexOf(lesMatieres.getLesMatieres().get(index).getProfesseurMatiere()), lesProfesseurs, lesMatieres.getLesMatieres().get(index).getCouleurMatiere(), index);
							
							interfaceGraphique.getBoutonCouleurMatiere().addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent event) {
										try {
										Color couleur = JColorChooser.showDialog(null, "Couleur du fond", Color.WHITE);
										interfaceGraphique.getBoutonCouleurMatiere().setBackground(couleur);
										} catch (Exception ex) {
											System.out.println("Erreur : " + ex);
										}
									}
							});
							interfaceGraphique.getBoutonModifierMatiere().addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent event) {
									lesMatieres.modifierMatiere(interfaceGraphique.getTfNomMatiere().getText(), interfaceGraphique.getTfNombreHeureCM().getText(), interfaceGraphique.getTfNombreHeureTD().getText(), interfaceGraphique.getTfNombreHeureTP().getText(), interfaceGraphique.getCmbNiveauMatiere().getSelectedItem().toString(), interfaceGraphique.getCmbProfesseurMatiere().getSelectedIndex(), interfaceGraphique.getBoutonCouleurMatiere().getBackground(), lesProfesseurs, index);
									interfaceGraphique.update_buttonMatiere(interfaceGraphique.getTfNomMatiere().getText(), interfaceGraphique.getBoutonCouleurMatiere().getBackground(), index);
								}
							});

							interfaceGraphique.getBoutonSupprimerMatiere().addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent event) {
									lesMatieres.supprimerMatiere(index);
									interfaceGraphique.rm_buttonMatiere(index);
									interfaceGraphique.getJFrameModificationMatiere().dispose();
								}
							});
							break; 
					}
				}
			});
		}
	}


	public void enleverLesListeners() {
		String[] typeButtonsStrings = {"Salle", "Professeur", "Classe", "Groupe", "Eleve", "Matiere"};
		for(String s : typeButtonsStrings) {
			for(JButton b : interfaceGraphique.getLesButtons(s)) {
				for(ActionListener al : b.getActionListeners() ) {
			        b.removeActionListener(al);
			    }
			}
		}
	}


	
}

/* Finir de mettre les listeners a toutes les classes */
/* Creer les JFramme de modification */
/* Faire la fonction qui modifie les objets */
/* Supprimer les listeners apres les avoir ajouter */
/* Pouvoir supprimer les objets */
/* Finir l'itnerface graphique et changer la taille de la fenetre*/