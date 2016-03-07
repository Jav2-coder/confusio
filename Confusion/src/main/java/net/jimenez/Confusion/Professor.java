package net.jimenez.Confusion;

/**
 * 
 * @author Surrui
 *
 */
public class Professor {

	/**
	 * Variable String que contiene el nombre del objeto Professor.
	 */
	private String nom;

	/**
	 * Variable String que contiene el sexo del objeto Professor.
	 */
	private String sexe;

	/**
	 * Constructor principal del objeto Professor
	 * 
	 * @param n
	 * @param s
	 */
	public Professor(String n, String s) {
		nom = n;
		sexe = s;
	}

	/**
	 * Metodo que devuelve el valor String del nombre del objeto.
	 * 
	 * @return
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Metodo que devuelve el valor String del sexo del objeto.
	 * 
	 * @return
	 */
	public String getSexe() {
		return sexe;
	}
}
