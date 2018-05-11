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
				for(Jour j : c.getEdtClasse().getLesJours()) {
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

								/* On parcourt la liste de salle afin d'en trouver une qui correspond aux critères */
								for(Salle s : lesSalles.getLesSalles()) {
									if(s.estOccupe(j, co) == false && s.getNombrePlacesSalle() >= c.getNombreEleveClasse() && laMatiere.getTypeMatiereMaxHeureRestante() == s.getTypeSalle()) {
										System.out.println("La salle numéro " + s.getNumeroSalle() + " correspond aux critères");
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


								System.out.println("Un professeur et une salle ont été trouvé pour le cours.");
								System.out.println("Classe : " + c.getNiveauClasse() + " " + c.getNomClasse());
								System.out.println("Matiere : " + laMatiere.getNomMatiere());
								System.out.println("Salle : " + laSalle.getNumeroSalle());
								System.out.println("Professeur : " + leProfesseur.getNomProfesseur() + " " + leProfesseur.getPrenomProfesseur()); 
							} else {
								System.out.println("Pas de solution");
							}
						}	
					}
				}
			}
			
		}
	}

}

	private Matiere laMatiere;
	private Professeur leProfesseur;
	private Salle laSalle;
	private Classe laClasse;
	private boolean occupe;