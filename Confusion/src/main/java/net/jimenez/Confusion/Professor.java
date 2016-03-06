package net.jimenez.Confusion;

public class Professor {
	
	private String nom;
	private String sexe;
	
	public Professor (String n, String s){
		nom = n;
		sexe = s;
	}
	
	public String getNom(){
		return nom;
	}
	
	public String getSexe() {
		return sexe;
	}
}
