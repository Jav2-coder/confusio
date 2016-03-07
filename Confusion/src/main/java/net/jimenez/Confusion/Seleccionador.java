package net.jimenez.Confusion;

import java.util.List;

/**
 * 
 * @author Surrui
 *
 */
public class Seleccionador {
	
	/**
	 * Objeto List que contiene la lista de objetos Professor
	 */
	private List<Professor> professors;
	
	/**
	 * Constructor principal del objeto Seleccionador.
	 * 
	 * @param p
	 */
	public Seleccionador (List<Professor> p) {
		
		professors = p;
		
	}
	
	/**
	 * Metodo que devuelve el valor del List del objeto Seleccionador.
	 * 
	 * @return
	 */
	public List<Professor> getListProfessors() {
		return professors;
	}
}
