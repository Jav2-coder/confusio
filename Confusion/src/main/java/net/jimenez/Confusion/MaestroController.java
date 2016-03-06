package net.jimenez.Confusion;

import javafx.fxml.FXML;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MaestroController {
	@FXML
	private Label capEstudi;
	@FXML
	private Label coordinacio;
	@FXML
	private Label secretari;
	@FXML
	private Label director;

	private List<Seleccionador> professorat = null;

	private List<String> novaDireccio = new ArrayList<String>();

	final static int DIRECTOR = 0;
	final static int SECRETARI = 1;
	final static int CAPESTUDIS_A = 2;
	final static int CAPESTUDIS_B = 3;
	final static int COORDINADOR = 4;

	final static int NUM_CARRECS = 4;

	private int posicio = 0;

	private Random rnd = new Random();

	// Event Listener on Button.onAction
	@FXML
	public void escollirFitxer(ActionEvent event) throws ParserConfigurationException, SAXException, IOException {

		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("XML Files", "*.xml"));

		File selectedFile = fileChooser.showOpenDialog(null);

		if (selectedFile != null) {

			InputStream fitxer = new FileInputStream(selectedFile);

			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser parser = spf.newSAXParser();
			Processar P = new Processar();
			parser.parse(fitxer, P);

			guardarDades(P.retornarDades());

		}
	}

	public void recalcularProfessors(ActionEvent event) {

		novaDireccio.clear();

		if (professorat != null) {

			int candidat = 0;

			while (candidat < NUM_CARRECS || professorat.get(candidat).getListProfessors() != null) {

				if (candidat == 2 || candidat == 3) {

					seleccionarProfessor(professorat.get(2).getListProfessors());
					professorat.get(2).getListProfessors().remove(posicio);

				} else if (candidat == 4) {

					seleccionarProfessor(professorat.get(candidat - 1).getListProfessors());
					professorat.get(candidat - 1).getListProfessors().remove(posicio);

				} else {

					seleccionarProfessor(professorat.get(candidat).getListProfessors());
					professorat.get(candidat).getListProfessors().remove(posicio);

				}

				candidat++;

			}

			if (professorat.get(candidat).getListProfessors() == null) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Aletra: Llista Acabada");
				alert.setHeaderText("Error al intentar recalcular la informació");
				alert.setContentText("Has de seleccionar un nou arxiu XML compatible per treballar.");
				alert.showAndWait();
			} else {

				director.setText(novaDireccio.get(DIRECTOR));
				secretari.setText(novaDireccio.get(SECRETARI));
				capEstudi.setText(novaDireccio.get(CAPESTUDIS_A) + "\n" + novaDireccio.get(CAPESTUDIS_B));
				coordinacio.setText(novaDireccio.get(COORDINADOR));
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Aletra: Falta Documentació");
			alert.setHeaderText("Error al intentar recalcular la informació");
			alert.setContentText("Has de seleccionar un arxiu XML compatible per treballar.");
			alert.showAndWait();
		}
	}

	public void guardarDades(List<String> data) {

		professorat = new ArrayList<Seleccionador>();

		List<Professor> newList = new ArrayList<Professor>();

		for (int i = 0; i < data.size(); i++) {

			String[] dades = data.get(i).split(", ");

			newList.add(new Professor(dades[0], dades[1]));

		}

		professorat.add(new Seleccionador(new ArrayList<Professor>(newList)));
		professorat.add(new Seleccionador(new ArrayList<Professor>(newList)));
		professorat.add(new Seleccionador(new ArrayList<Professor>(newList)));
		professorat.add(new Seleccionador(new ArrayList<Professor>(newList)));
	}

	private void seleccionarProfessor(List<Professor> professors) {

		boolean correcte = false;

		while (!correcte) {

			int[] sexes = { 0, 0 };

			posicio = rnd.nextInt(professors.size());

			String prof = professors.get(posicio).getNom() + ", " + professors.get(posicio).getSexe();

			if (!novaDireccio.contains(prof)) {

				for (int i = 0; i < novaDireccio.size(); i++) {

					if (novaDireccio.get(i).contains("Home")) {
						sexes[0]++;
					} else {
						sexes[1]++;
					}
				}
				if (sexes[1] < 3 && sexes[1] < 3) {

					novaDireccio.add(professors.get(posicio).getNom());
					correcte = true;

				}
			}
		}
	}
}
