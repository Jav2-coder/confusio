package net.jimenez.Confusion;

import java.util.List;

public class Seleccionador {
	
	private List<Professor> professors;
	
	public Seleccionador (List<Professor> p) {
		
		professors = p;
		
	}
	
	public List<Professor> getListProfessors() {
		return professors;
	}
}
