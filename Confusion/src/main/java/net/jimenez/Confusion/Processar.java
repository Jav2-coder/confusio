package net.jimenez.Confusion;

import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 
 * @author Surrui
 *
 */
public class Processar extends DefaultHandler {

	/**
	 * Objeto String que contiene los valores de cada profesor
	 */
	private List<String> dades = new ArrayList<String>();

	/**
	 * Variable String que contendrá los datos de un profesor
	 */
	private String dada = null;

	/**
	 * Variables boolean necesarias para seleccionar partes del XML.
	 */
	private boolean professor, nom, cognom, sexe = false;

	/**
	 * Metodo que se ejecuta al acabar la lecutra del XML.
	 */
	public void endDocument() throws SAXException {

	}

	/**
	 * Metodo de lectura donde, segun que estiqueta se inicie, captura una
	 * sección u otra del XML.
	 */
	public void startElement(String uri, String localName, String qName, Attributes attr) {

		switch (qName) {
		case "professor":
			professor = true;
			break;
		case "nom":
			nom = true;
			break;
		case "cognom":
			cognom = true;
			break;
		case "sexe":
			sexe = true;
			break;
		}
	}

	/**
	 * Metodo de lectura de los valores de cada etiqueta donde, segun que
	 * etiqueta estemos leyendo, guardaremos el nombre del profesor, su apellido
	 * o su sexo.
	 */
	public void characters(char[] ch, int start, int length) {

		if (professor && nom) {
			dada = new String(ch, start, length);
		}
		if (professor && cognom) {
			dada = dada + " " + new String(ch, start, length);
		}
		if (professor && sexe) {
			dada = dada + ", " + new String(ch, start, length);
		}
	}

	/**
	 * Metodo que almacenará los datos String dentro del List que tenemos
	 * creado.
	 */
	public void endElement(String uri, String localName, String qName) {

		switch (qName) {
		case "professor":
			professor = false;
			dades.add(dada);
			break;
		case "nom":
			nom = false;
			break;
		case "cognom":
			cognom = false;
			break;
		case "sexe":
			sexe = false;
			break;
		}
	}

	/**
	 * Metodo creado para devolver el objeto List con todos los datos de todos
	 * los profesores del XML.
	 * 
	 * @return
	 */
	public List<String> retornarDades() {

		return dades;
	}
}
