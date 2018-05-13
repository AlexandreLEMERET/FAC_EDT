package PROJET.Model;

import java.util.*;
import java.io.*;
import java.io.Serializable;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Salle implements Serializable {

	private String numeroSalle;
	private int nombrePlacesSalle;
	private String typeSalle;
	private Edt edtSalle;
	
	public Salle() {}
	
	public Salle(String p_numeroSalle, int p_nombrePlacesSalle, String p_typeSalle) {
		this.numeroSalle = p_numeroSalle;
		this.nombrePlacesSalle = p_nombrePlacesSalle;
		this.typeSalle = p_typeSalle;
		this.edtSalle = new Edt();
	}
	
	public String getNumeroSalle() {
		return this.numeroSalle;
	}
	
	public int getNombrePlacesSalle() {
		return this.nombrePlacesSalle;
	}
	
	public String getTypeSalle() {
		return this.typeSalle;
	}

 	public String toString() {
		return "Numero salle : " + this.numeroSalle + "\n Nbr places : " + this.nombrePlacesSalle + "\n Type salle : " + this.typeSalle + "\n"; 
	} 

	public boolean estOccupe(Jour p_leJour, Cours p_leCours) {
		return this.edtSalle.getLeJour(p_leJour).getLeCours(p_leCours).getOccupe();
	}

	public Edt getEDT() {
		return this.edtSalle;
	}

}
