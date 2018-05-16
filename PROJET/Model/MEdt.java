package PROJET.Model;


import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class MEdt {
	
	private ArrayList<Edt> lesEDT; 
	
	public MEdt() {
		this.lesEDT = new ArrayList<Edt>();
	}
	
	public ArrayList<Edt> getLesEDT(){
		return this.lesEDT;
	}

	public void ajoutEDT(Edt p_edt) {
		this.lesEDT.add(p_edt);
	}

	public void genererLesEDT(MClasse lesClasses, MEleve lesEleves, MSalle lesSalles) {


		for(Classe c : lesClasses.getLesClasses()) {
			
			/* Classe sans groupe */
			if(c.getLesGroupesClasse().size() == 0) {

				/* On compte le nombre d'élève de la classe */
				c.getNombreEleveClasse(lesEleves, c);

				/* On parcourt les jours et les cours de l'EDT de la Classe c */
				for(Jour j : c.getEDT().getLesJours()) {
					for(Cours co : j.getLesCours()) {

						/* Si le cours est libre */
						if(co.getOccupe() == false) {

							c.trierLesMatieres();
							boolean salleLibre = false;
							boolean professeurLibre = false;
							Salle laSalle = new Salle();
							Professeur leProfesseur = new Professeur();
							Matiere laMatiere = new Matiere();
							int i = 0;

							System.out.println("salleLibre : " + salleLibre);
							System.out.println("professeurLibre : " + professeurLibre);
							System.out.println("nombre de matière : " + c.getLesMatieres().size());
							System.out.println("i : " + i);
							/* Tant qu'on ne trouve pas une salle libre correspondant aux critères, un professeur libre et qu'il reste des matières possibles à placer sur l'EDT */
							while(salleLibre == false && professeurLibre == false && i < c.getLesMatieres().size()) {

								/* On sauvegarder la matière dans un objet Matiere */
								laMatiere = c.getLesMatieres().get(i);

								/* On vérifie si il reste des cours à placer */
								if(laMatiere.getMaxHeureRestante() > 0) {
									System.out.println("---");
									System.out.println("Matiere : " + laMatiere.getNomMatiere());
									System.out.println("CM : " + laMatiere.getNombreHeureCMrestante());
									System.out.println("TD : " + laMatiere.getNombreHeureTDrestante());
									System.out.println("TP : " + laMatiere.getNombreHeureTPrestante());
									System.out.println("Nombre heure restante au max : " + laMatiere.getMaxHeureRestante());
									System.out.println("Type d'heure restante : " + laMatiere.getTypeMatiereMaxHeureRestante());
								/* On parcourt la liste de salle afin d'en trouver une qui correspond aux critères */
									for(Salle s : lesSalles.getLesSalles()) {
										System.out.println("Salle : " + s.getNumeroSalle() + " - Occupé : " + s.estOccupe(j, co));
										System.out.println("Nombre d'eleve de la classe : " + c.getNombreEleveClasse() + " - Nombre de place : " + s.getNombrePlacesSalle());
										System.out.println("laMatiere.getTypeMatiereMaxHeureRestante() :" + laMatiere.getTypeMatiereMaxHeureRestante() + " - s.getTypeSalle() : " + s.getTypeSalle());
										if(s.estOccupe(j, co) == false && s.getNombrePlacesSalle() >= c.getNombreEleveClasse() && laMatiere.getTypeMatiereMaxHeureRestante().equals(s.getTypeSalle())) {
											//System.out.println("ISSOU");
											laSalle = s;
											salleLibre = true;
											break;
										}
									}

									/* On cherche un professeur qui est libre pour assurer cette matière */
									System.out.println("Le professeur : " + laMatiere.getProfesseurMatiere().getNomProfesseur() + " - Occupé : " + laMatiere.getProfesseurMatiere().estOccupe(j,co));
									if(laMatiere.getProfesseurMatiere().estOccupe(j, co) == false) {
										leProfesseur = laMatiere.getProfesseurMatiere();
										professeurLibre = true;
									}
								}
								i++;
							}

							if(salleLibre == true && professeurLibre == true) {
								/* On soustrait les 2 heures de cours placées */
								//System.out.println("Type d'heure enlevé : " + laMatiere.getTypeMatiereMaxHeureRestante());
								//System.out.println("La matiere : " + c.getLesMatieres().get(i-1).getNomMatiere());
								//System.out.println("Nombre heure CM restante : " + c.getLesMatieres().get(i-i).getNombreHeureCMrestante());

								//System.out.println("La matiere : " + laMatiere.getNomMatiere());
								laMatiere.enleverHeureRestante(laMatiere.getTypeMatiereMaxHeureRestante());
								//System.out.println("Nombre heure CM restante : " + laMatiere.getNombreHeureCMrestante());

								/* On met à jour l'EDT du professeur */
								leProfesseur.getEDT().getLeJour(j).getLeCours(co).setLaMatiere(laMatiere);
								leProfesseur.getEDT().getLeJour(j).getLeCours(co).setLaSalle(laSalle);
								leProfesseur.getEDT().getLeJour(j).getLeCours(co).setLaClasse(c);
								leProfesseur.getEDT().getLeJour(j).getLeCours(co).setOccupe();

								/* On met à jour l'EDT de la salle */
								laSalle.getEDT().getLeJour(j).getLeCours(co).setLaMatiere(laMatiere);
								laSalle.getEDT().getLeJour(j).getLeCours(co).setLeProfesseur(leProfesseur);
								laSalle.getEDT().getLeJour(j).getLeCours(co).setLaClasse(c);
								laSalle.getEDT().getLeJour(j).getLeCours(co).setOccupe();

								/* On met à jour l'EDT de la classe */
								c.getEDT().getLeJour(j).getLeCours(co).setLaMatiere(laMatiere);
								c.getEDT().getLeJour(j).getLeCours(co).setLeProfesseur(leProfesseur);
								c.getEDT().getLeJour(j).getLeCours(co).setLaSalle(laSalle);
								c.getEDT().getLeJour(j).getLeCours(co).setOccupe();

							} else {
								//System.out.println("Pas de solution");
							}
							
						}	
					}
				}
			}

			/* Classe avec groupes */
			if(c.getLesGroupesClasse().size() > 0) {
				for(Groupe g : c.getLesGroupesClasse()) {

					/* On compte le nombre d'éleve du groupe */
					g.getNombreEleveGroupe(lesEleves, c, g);
					
					/* On parcourt les jours et les cours de l'EDT du groupe */
					for(Jour j : g.getEDT().getLesJours()) {
						for(Cours co : j.getLesCours()) {

							/* Si le cours est libre */
							if(co.getOccupe() == false) {

								g.trierLesMatieres();
								boolean salleLibre = false;
								boolean professeurLibre = false;
								Salle laSalle = new Salle();
								Professeur leProfesseur = new Professeur();
								Matiere laMatiere = new Matiere();
								int i = 0;
								
								//System.out.println("g.getLesMatieres().size() : " + g.getLesMatieres().size());
								while(salleLibre != true && professeurLibre != true && i < g.getLesMatieres().size()) {

									//System.out.println("Size : " + g.getLesMatieres().size());
									//System.out.println("i : " + i);
									/* On sauvegarder la matière dans un objet Matiere */
									laMatiere = g.getLesMatieres().get(i);

									/* On vérifie si il reste des cours à placer */
									if(laMatiere.getMaxHeureRestante() > 0) {
										//System.out.println("---");
										//System.out.println("Matiere : " + laMatiere.getNomMatiere());
										//System.out.println("Heure debut : " + co.getHeureDebut());
										//System.out.println("Heure fin : " + co.getHeureFin());
										//System.out.println("CM : " + laMatiere.getNombreHeureCMrestante());
										///System.out.println("TD : " + laMatiere.getNombreHeureTDrestante());
										//System.out.println("TP : " + laMatiere.getNombreHeureTPrestante());
										//System.out.println("Nombre heure restante au max : " + laMatiere.getMaxHeureRestante());
										//System.out.println("Type d'heure restante : " + laMatiere.getTypeMatiereMaxHeureRestante());
									/* On parcourt la liste de salle afin d'en trouver une qui correspond aux critères */
										for(Salle s : lesSalles.getLesSalles()) {
											//System.out.println("Salle : " + s.getNumeroSalle() + " - Occupé : " + s.estOccupe(j, co));
											//System.out.println("Nombre d'eleve du groupe : " + g.getNombreEleveGroupe() + " - Nombre de place : " + s.getNombrePlacesSalle());
											//System.out.println("laMatiere.getTypeMatiereMaxHeureRestante() :" + laMatiere.getTypeMatiereMaxHeureRestante() + " - s.getTypeSalle() : " + s.getTypeSalle());
											if(s.estOccupe(j, co) == false && s.getNombrePlacesSalle() >= g.getNombreEleveGroupe() && laMatiere.getTypeMatiereMaxHeureRestante().equals(s.getTypeSalle())) {
												//System.out.println("ISSOU");
												laSalle = s;
												salleLibre = true;
												break;
											}
										}

										/* On cherche un professeur qui est libre pour assurer cette matière */
										//System.out.println("Le professeur : " + laMatiere.getProfesseurMatiere().getNomProfesseur() + " - Occupé : " + laMatiere.getProfesseurMatiere().estOccupe(j,co));
										//System.out.println("Matiere : " + leProfesseur.getEDT().getLeJour(j).getLeCours(co).getMatiere());
										//System.out.println("Salle : " + leProfesseur.getEDT().getLeJour(j).getLeCours(co).getSalle());
										if(laMatiere.getProfesseurMatiere().estOccupe(j, co) == false) {
											leProfesseur = laMatiere.getProfesseurMatiere();
											professeurLibre = true;
										} else { salleLibre = false; }
									}
									i++;
								}

								if(salleLibre == true && professeurLibre == true) {
									/* On soustrait les 2 heures de cours placées */
									//System.out.println("Type d'heure enlevé : " + laMatiere.getTypeMatiereMaxHeureRestante());
									//System.out.println("La matiere : " + g.getLesMatieres().get(i-1).getNomMatiere());
									//System.out.println("Nombre heure restante : " + g.getLesMatieres().get(i-i).getNombreHeureCMrestante());
									//System.out.println("La matiere : " + laMatiere.getNomMatiere());
									//System.out.println("Nombre heure CM restante : " + laMatiere.getNombreHeureCMrestante());
									
									/* Si c'est un TP ou TD, le cours ne concerne qu'un groupe */
									if(laMatiere.getTypeMatiereMaxHeureRestante() == "TP" || laMatiere.getTypeMatiereMaxHeureRestante() == "TD") {
									
										/* On soustrait les 2 heures de cours placées */
										laMatiere.enleverHeureRestante(laMatiere.getTypeMatiereMaxHeureRestante());
										//System.out.println("On enleve 2h de TP ou TD au groupe : " + g.getNomGroupe());

										/* On met à jour l'EDT du professeur */
										leProfesseur.getEDT().getLeJour(j).getLeCours(co).setLaMatiere(laMatiere);
										leProfesseur.getEDT().getLeJour(j).getLeCours(co).setLaSalle(laSalle);
										leProfesseur.getEDT().getLeJour(j).getLeCours(co).setLaClasse(c);
										leProfesseur.getEDT().getLeJour(j).getLeCours(co).setLeGroupe(g);
										leProfesseur.getEDT().getLeJour(j).getLeCours(co).setOccupe();

										/* On met à jour l'EDT de la salle */
										laSalle.getEDT().getLeJour(j).getLeCours(co).setLaMatiere(laMatiere);
										laSalle.getEDT().getLeJour(j).getLeCours(co).setLeProfesseur(leProfesseur);
										laSalle.getEDT().getLeJour(j).getLeCours(co).setLaClasse(c);
										laSalle.getEDT().getLeJour(j).getLeCours(co).setLeGroupe(g);
										laSalle.getEDT().getLeJour(j).getLeCours(co).setOccupe();

										/* On met à jour l'EDT du groupe */ 
										g.getEDT().getLeJour(j).getLeCours(co).setLaMatiere(laMatiere);
										g.getEDT().getLeJour(j).getLeCours(co).setLeProfesseur(leProfesseur);
										g.getEDT().getLeJour(j).getLeCours(co).setLaSalle(laSalle);
										g.getEDT().getLeJour(j).getLeCours(co).setOccupe();

										/* On sauvegarde la classe */
										c.getEDT().getLeJour(j).getLeCours(co).setLaClasse(c);
										c.getEDT().getLeJour(j).getLeCours(co).setLaMatiere(laMatiere);
										c.getEDT().getLeJour(j).getLeCours(co).setLeTypeCours("groupe");
										//System.out.println("Type cours : " + c.getEDT().getLeJour(j).getLeCours(co).getTypeCours());

									}

									/* Si c'est un CM, c'est pour tous les groupes de la classe */
									else {

										/* Si un CM, on enleve les 2h de cours à tous les groupes de la classe */
										String typeCours = laMatiere.getTypeMatiereMaxHeureRestante();
										for(Groupe gr : c.getLesGroupesClasse()) {
											//System.out.println("On enleve 2h de CM au groupe : " + gr.getNomGroupe());
											gr.getLesMatieres().get(i-1).enleverHeureRestante(typeCours);
											//System.out.println("Nombre d'heure de " + gr.getLesMatieres().get(i-1).getNomMatiere() + ": "+ gr.getLesMatieres().get(i-1).getNombreHeureCMrestante());

											/* On met à jour l'EDT des groupes */
											gr.getEDT().getLeJour(j).getLeCours(co).setLaMatiere(laMatiere);
											gr.getEDT().getLeJour(j).getLeCours(co).setLeProfesseur(leProfesseur);
											gr.getEDT().getLeJour(j).getLeCours(co).setLaSalle(laSalle);
											gr.getEDT().getLeJour(j).getLeCours(co).setOccupe();
										}
										/* On met à jour l'EDT du professeur */
										leProfesseur.getEDT().getLeJour(j).getLeCours(co).setLaMatiere(laMatiere);
										leProfesseur.getEDT().getLeJour(j).getLeCours(co).setLaSalle(laSalle);
										leProfesseur.getEDT().getLeJour(j).getLeCours(co).setLaClasse(c);
										leProfesseur.getEDT().getLeJour(j).getLeCours(co).setLeGroupe(g);
										leProfesseur.getEDT().getLeJour(j).getLeCours(co).setOccupe();

										/* On met à jour l'EDT de la salle */
										laSalle.getEDT().getLeJour(j).getLeCours(co).setLaMatiere(laMatiere);
										laSalle.getEDT().getLeJour(j).getLeCours(co).setLeProfesseur(leProfesseur);
										laSalle.getEDT().getLeJour(j).getLeCours(co).setLaClasse(c);
										laSalle.getEDT().getLeJour(j).getLeCours(co).setLeGroupe(g);
										laSalle.getEDT().getLeJour(j).getLeCours(co).setOccupe();

										/* On met à jour l'EDT de la classe */
										c.getEDT().getLeJour(j).getLeCours(co).setLaMatiere(laMatiere);
										c.getEDT().getLeJour(j).getLeCours(co).setLeProfesseur(leProfesseur);
										c.getEDT().getLeJour(j).getLeCours(co).setLaSalle(laSalle);
										c.getEDT().getLeJour(j).getLeCours(co).setOccupe();
									}


								} else {
									//System.out.println("Pas de solution");
								}
							}

						}

					}
				}
			}
		}
		JOptionPane.showMessageDialog(null, "Les emplois du temps ont été généré.");
	}




		/* Nettoyage des EDT*/
		public void viderEDT(MSalle lesSalles, MProfesseur lesProfesseurs, MClasse lesClasses, MGroupe lesGroupes, MEleve lesEleves) {
			for(Salle s : lesSalles.getLesSalles()) {
				for(Jour j : s.getEDT().getLesJours()) {
					for(Cours co : j.getLesCours()) {
						s.getEDT().getLeJour(j).getLeCours(co).setLaMatiere(null);
						s.getEDT().getLeJour(j).getLeCours(co).setLeTypeCours("");
						s.getEDT().getLeJour(j).getLeCours(co).setLeProfesseur(null);
						s.getEDT().getLeJour(j).getLeCours(co).setLaSalle(null);
						s.getEDT().getLeJour(j).getLeCours(co).setLaClasse(null);
						s.getEDT().getLeJour(j).getLeCours(co).setLeGroupe(null);
						s.getEDT().getLeJour(j).getLeCours(co).setOccupe(false);
					}
				}
			}

			for(Professeur p : lesProfesseurs.getLesProfesseurs()) {
				for(Jour j : p.getEDT().getLesJours()) {
					for(Cours co : j.getLesCours()) {
						p.getEDT().getLeJour(j).getLeCours(co).setLaMatiere(null);
						p.getEDT().getLeJour(j).getLeCours(co).setLeTypeCours("");
						p.getEDT().getLeJour(j).getLeCours(co).setLeProfesseur(null);
						p.getEDT().getLeJour(j).getLeCours(co).setLaSalle(null);
						p.getEDT().getLeJour(j).getLeCours(co).setLaClasse(null);
						p.getEDT().getLeJour(j).getLeCours(co).setLeGroupe(null);
						p.getEDT().getLeJour(j).getLeCours(co).setOccupe(false);
					}
				}
			}

			for(Classe c : lesClasses.getLesClasses()) {
				for(Jour j : c.getEDT().getLesJours()) {
					for(Cours co : j.getLesCours()) {
						c.getEDT().getLeJour(j).getLeCours(co).setLaMatiere(null);
						c.getEDT().getLeJour(j).getLeCours(co).setLeTypeCours("");
						c.getEDT().getLeJour(j).getLeCours(co).setLeProfesseur(null);
						c.getEDT().getLeJour(j).getLeCours(co).setLaSalle(null);
						c.getEDT().getLeJour(j).getLeCours(co).setLaClasse(null);
						c.getEDT().getLeJour(j).getLeCours(co).setLeGroupe(null);
						c.getEDT().getLeJour(j).getLeCours(co).setOccupe(false);
						c.setLesMatieres();
					}
				}
			}

			for(Groupe g : lesGroupes.getLesGroupes()) {
				for(Jour j : g.getEDT().getLesJours()) {
					for(Cours co : j.getLesCours()) {
						g.getEDT().getLeJour(j).getLeCours(co).setLaMatiere(null);
						g.getEDT().getLeJour(j).getLeCours(co).setLeTypeCours("");
						g.getEDT().getLeJour(j).getLeCours(co).setLeProfesseur(null);
						g.getEDT().getLeJour(j).getLeCours(co).setLaSalle(null);
						g.getEDT().getLeJour(j).getLeCours(co).setLaClasse(null);
						g.getEDT().getLeJour(j).getLeCours(co).setLeGroupe(null);
						g.getEDT().getLeJour(j).getLeCours(co).setOccupe(false);
					}
				}
			}

			for(Eleve e : lesEleves.getLesEleves()) {
				for(Jour j : e.getEDT().getLesJours()) {
					for(Cours co : j.getLesCours()) {
						e.getEDT().getLeJour(j).getLeCours(co).setLaMatiere(null);
						e.getEDT().getLeJour(j).getLeCours(co).setLeTypeCours("");
						e.getEDT().getLeJour(j).getLeCours(co).setLeProfesseur(null);
						e.getEDT().getLeJour(j).getLeCours(co).setLaSalle(null);
						e.getEDT().getLeJour(j).getLeCours(co).setLaClasse(null);
						e.getEDT().getLeJour(j).getLeCours(co).setLeGroupe(null);
						e.getEDT().getLeJour(j).getLeCours(co).setOccupe(false);
					}
				}
			}
		}


	/*public void afficherLesEDT(MClasse lesClasses) {
		for(Classe c : lesClasses.getLesClasses()) {
			System.out.println("Classe : " + c.getNiveauClasse() + " " + c.getNomClasse());

			// Classe sans groupe 
			if(c.getLesGroupesClasse().size() == 0) {
				// On parcourt les jours et les cours de l'EDT de la Classe c 
				for(Jour j : c.getEDT().getLesJours()) {
					System.out.println("Jour : " + j.getNomJour());
					for(Cours co : j.getLesCours()) {
						if(c.getEDT().getLeJour(j).getLeCours(co).getMatiere() != null && c.getEDT().getLeJour(j).getLeCours(co).getSalle() != null && c.getEDT().getLeJour(j).getLeCours(co).getProfesseur() != null) { 
							System.out.println("	Cours de " + co.getHeureDebut() + " à " + co.getHeureFin() + " - P: " + c.getEDT().getLeJour(j).getLeCours(co).getProfesseur().getNomProfesseur() + " - M: " + c.getEDT().getLeJour(j).getLeCours(co).getMatiere().getNomMatiere() + " - S: " + c.getEDT().getLeJour(j).getLeCours(co).getSalle().getNumeroSalle());
						} else {
							System.out.println("	Cours de " + co.getHeureDebut() + " à " + co.getHeureFin());
						}
					}
					System.out.println("-----");
				}
			}
		}
	}*/

	/*public void afficherLesEDT(MProfesseur lesProfesseurs, MClasse lesClasses) {
		for(Professeur p : lesProfesseurs.getLesProfesseurs()) {
			System.out.println("Professeur :" + p.getNomProfesseur() + " " + p.getPrenomProfesseur());

			for(Jour j : p.getEDT().getLesJours()) {
				System.out.println("Jour : " + j.getNomJour());
				for(Cours co : j.getLesCours()) {
					if(p.getEDT().getLeJour(j).getLeCours(co).getMatiere() != null & p.getEDT().getLeJour(j).getLeCours(co).getSalle() != null) {
						System.out.println("	Cours de " + co.getHeureDebut() + " à " + co.getHeureFin() + " - C : " + p.getEDT().getLeJour(j).getLeCours(co).getClasse().getNiveauClasse() + " " + p.getEDT().getLeJour(j).getLeCours(co).getClasse().getNomClasse() + " - M :" + p.getEDT().getLeJour(j).getLeCours(co).getMatiere().getNomMatiere());
					} else {
						System.out.println("	Cours de " + co.getHeureDebut() + " à " + co.getHeureFin());
					}
				}
				System.out.println("-----");
			}
		}
	}*/

	public void afficherLesEDT(MSalle lesSalles) {
		for(Salle s : lesSalles.getLesSalles()) {
			System.out.println("Salle : " + s.getNumeroSalle());

			for(Jour j : s.getEDT().getLesJours()) {
				System.out.println("Jour : " + j.getNomJour());
				for(Cours co : j.getLesCours()) {
				//System.out.println("Matiere : " + s.getEDT().getLeJour(j).getLeCours(co).getMatiere());
				//System.out.println("Classe : " + s.getEDT().getLeJour(j).getLeCours(co).getClasse());
				//System.out.println("Professeur : " + s.getEDT().getLeJour(j).getLeCours(co).getProfesseur());
					if(s.getEDT().getLeJour(j).getLeCours(co).getMatiere() != null && s.getEDT().getLeJour(j).getLeCours(co).getClasse() != null && s.getEDT().getLeJour(j).getLeCours(co).getProfesseur() != null && s.getEDT().getLeJour(j).getLeCours(co).getGroupe() != null) {
						System.out.println("	Cours de " + co.getHeureDebut() + " à " + co.getHeureFin() + " - M : " + s.getEDT().getLeJour(j).getLeCours(co).getMatiere().getNomMatiere() + " - C : " + s.getEDT().getLeJour(j).getLeCours(co).getClasse().getNiveauClasse() + " " + s.getEDT().getLeJour(j).getLeCours(co).getClasse().getNomClasse() + " - G : " + s.getEDT().getLeJour(j).getLeCours(co).getGroupe().getNomGroupe() +" - P : " + s.getEDT().getLeJour(j).getLeCours(co).getProfesseur().getNomProfesseur());
					} else {
						if(s.getEDT().getLeJour(j).getLeCours(co).getMatiere() != null && s.getEDT().getLeJour(j).getLeCours(co).getClasse() != null && s.getEDT().getLeJour(j).getLeCours(co).getProfesseur() != null && s.getEDT().getLeJour(j).getLeCours(co).getGroupe() == null) {
							System.out.println("	Cours de " + co.getHeureDebut() + " à " + co.getHeureFin() + " - M : " + s.getEDT().getLeJour(j).getLeCours(co).getMatiere().getNomMatiere() + " - C : " + s.getEDT().getLeJour(j).getLeCours(co).getClasse().getNiveauClasse() + " " + s.getEDT().getLeJour(j).getLeCours(co).getClasse().getNomClasse() + " - P : " + s.getEDT().getLeJour(j).getLeCours(co).getProfesseur().getNomProfesseur());
						} else {
							System.out.println("	Cours de " + co.getHeureDebut() + " à " + co.getHeureFin());
						}
					}
				}
				System.out.println("-----");
			}
		}
	}
}