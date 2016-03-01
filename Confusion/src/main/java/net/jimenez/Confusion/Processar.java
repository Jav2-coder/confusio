package net.jimenez.Confusion;

import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class Processar extends DefaultHandler {

	private List<String> dades = new ArrayList<String>();

	private String dada = null;

	private boolean professor, nom, cognom, sexe = false;

	public void endDocument() throws SAXException {
		for (int i = 0; i < dades.size(); i++) {
			System.out.println(dades.get(i));
		}
	}

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

}
