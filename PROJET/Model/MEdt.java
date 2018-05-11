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

							
							/* Tant qu'on ne trouve pas une salle libre correspondant aux critères, un professeur libre et qu'il reste des matières possibles à placer sur l'EDT */
							while(salleLibre != true && professeurLibre != true && i < c.getLesMatieres().size()) {

								/* On sauvegarder la matière dans un objet Matiere */
								laMatiere = c.getLesMatieres().get(i);

								/* On vérifie si il reste des cours à placer */
								System.out.println("Matiere & Heure : " + laMatiere.getNomMatiere() + " - " + laMatiere.getMaxHeureRestante());
								if(laMatiere.getMaxHeureRestante() > 0) {
								/* On parcourt la liste de salle afin d'en trouver une qui correspond aux critères */
									for(Salle s : lesSalles.getLesSalles()) {
										if(s.estOccupe(j, co) == false && s.getNombrePlacesSalle() >= c.getNombreEleveClasse() && laMatiere.getTypeMatiereMaxHeureRestante() == s.getTypeSalle()) {
											laSalle = s;
											salleLibre = true;
										}
										break;
									}

									/* On cherche un professeur qui est libre pour assurer cette matière */
									if(laMatiere.getProfesseurMatiere().estOccupe(j, co) == false) {
										leProfesseur = laMatiere.getProfesseurMatiere();
										professeurLibre = true;
									}
								}
								i++;
							}

							if(salleLibre == true && professeurLibre == true) {
								/* On soustrait les 2 heures de cours placées */
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
						System.out.println("Cours de " + co.getHeureDebut() + " à " + co.getHeureFin());
						if(c.getEDT().getLeJour(j).getLeCours(co).getMatiere() != null) { System.out.println("Matiere : " + c.getEDT().getLeJour(j).getLeCours(co).getMatiere().getNomMatiere()); }
						if(c.getEDT().getLeJour(j).getLeCours(co).getSalle() != null) { System.out.println("Salle : " + c.getEDT().getLeJour(j).getLeCours(co).getSalle().getNumeroSalle()); }
						if(c.getEDT().getLeJour(j).getLeCours(co).getProfesseur() != null) { System.out.println("Professeur : " + c.getEDT().getLeJour(j).getLeCours(co).getProfesseur().getNomProfesseur()); }
						System.out.println("-----");
					}
					System.out.println("-----");
				}
			}
		}
	}
}