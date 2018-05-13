package PROJET.Model;


import java.io.*;
import java.util.ArrayList;

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

				/* On compte le nombre d'élève de la classe*/
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

							//System.out.println("salleLibre : " + salleLibre);
							//System.out.println("professeurLibre : " + professeurLibre);
							//System.out.println("nombre de matière : " + c.getLesMatieres().size());
							//System.out.println("i : " + i);
							/* Tant qu'on ne trouve pas une salle libre correspondant aux critères, un professeur libre et qu'il reste des matières possibles à placer sur l'EDT */
							while(salleLibre == false && professeurLibre == false && i < c.getLesMatieres().size()) {

								/* On sauvegarder la matière dans un objet Matiere */
								laMatiere = c.getLesMatieres().get(i);

								/* On vérifie si il reste des cours à placer */
								if(laMatiere.getMaxHeureRestante() > 0) {
									//System.out.println("---");
									//System.out.println("Matiere : " + laMatiere.getNomMatiere());
									//System.out.println("CM : " + laMatiere.getNombreHeureCMrestante());
									//System.out.println("TD : " + laMatiere.getNombreHeureTDrestante());
									//System.out.println("TP : " + laMatiere.getNombreHeureTPrestante());
									//System.out.println("Nombre heure restante au max : " + laMatiere.getMaxHeureRestante());
									//System.out.println("Type d'heure restante : " + laMatiere.getTypeMatiereMaxHeureRestante());
								/* On parcourt la liste de salle afin d'en trouver une qui correspond aux critères */
									for(Salle s : lesSalles.getLesSalles()) {
										//System.out.println("Salle : " + s.getNumeroSalle() + " - Occupé : " + s.estOccupe(j, co));
										//System.out.println("Nombre d'eleve de la classe : " + c.getNombreEleveClasse() + " - Nombre de place : " + s.getNombrePlacesSalle());
										//System.out.println("laMatiere.getTypeMatiereMaxHeureRestante() :" + laMatiere.getTypeMatiereMaxHeureRestante() + " - s.getTypeSalle() : " + s.getTypeSalle());
										if(s.estOccupe(j, co) == false && s.getNombrePlacesSalle() >= c.getNombreEleveClasse() && laMatiere.getTypeMatiereMaxHeureRestante().equals(s.getTypeSalle())) {
											//System.out.println("ISSOU");
											laSalle = s;
											salleLibre = true;
											break;
										}
									}

									/* On cherche un professeur qui est libre pour assurer cette matière */
									//System.out.println("Le professeur : " + laMatiere.getProfesseurMatiere().getNomProfesseur() + " - Occupé : " + laMatiere.getProfesseurMatiere().estOccupe(j,co));
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
								//System.out.println("Nombre heure CM restante : " + laMatiere.getNombreHeureCMrestante());
								laMatiere.enleverHeureRestante(laMatiere.getTypeMatiereMaxHeureRestante());

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
								System.out.println("Pas de solution");
							}
							
						}	
					}
				}
			}
			
		}
	}



	public void afficherLesEDT(MClasse lesClasses) {
		for(Classe c : lesClasses.getLesClasses()) {
			System.out.println("Classe : " + c.getNiveauClasse() + " " + c.getNomClasse());

			/* Classe sans groupe */
			if(c.getLesGroupesClasse().size() == 0) {
				/* On parcourt les jours et les cours de l'EDT de la Classe c */
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
	}

	public void afficherLesEDT(MProfesseur lesProfesseurs, MClasse lesClasses) {
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
	}
}