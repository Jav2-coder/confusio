package net.jimenez.Confusion;

import javafx.fxml.FXML;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
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

	/**
	 * Objeto List que contiene los objetos Seleccionador con todos los objetos
	 * Professor para rellenar los Label.
	 */
	private List<Seleccionador> professorat = null;

	/**
	 * Objeto List que contiene momentaneamente los valores a añadir en los
	 * Label del scene.
	 */
	private List<String> novaDireccio = new ArrayList<String>();

	/**
	 * Variables int que ayudaran a rellenar los label del programa.
	 */
	final static int DIRECTOR = 0;
	final static int SECRETARI = 1;
	final static int CAPESTUDIS_A = 2;
	final static int CAPESTUDIS_B = 3;
	final static int COORDINADOR = 4;

	final static int NUM_CARRECS = 4;

	/**
	 * Variable String que contiene el nombre del archivo que se creara en la
	 * raiz del programa.
	 */
	final static String NOM_FITXER = "nova_direccio.txt";

	/**
	 * Variable int que determina la posicion dentro de un List.
	 */
	private int posicio = 0;

	/**
	 * Objeto Random para generar valores int aleatorios.
	 */
	private Random rnd = new Random();

	/**
	 * Metodo que usaremos para elegir un fichero XML que usaremos en el
	 * programa.
	 * 
	 * @param event
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
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

	/**
	 * Metodo que guarda los datos en un txt en el caso de que hayan, sino
	 * mostrara una advertencia.
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void desarDades(ActionEvent event) throws IOException {

		if (capEstudi.getText() == "" || coordinacio.getText() == "" || secretari.getText() == ""
				|| director.getText() == "") {

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Aletra: Falta d'informació");
			alert.setHeaderText("Error al intentar desar la informació");
			alert.setContentText("S'han d'emplenar els camps amb l'arxiu XML.");
			alert.showAndWait();

		} else {

			File archivo = new File(NOM_FITXER);
			BufferedWriter br = new BufferedWriter(new FileWriter(archivo));

			br.write("Director/a: " + director.getText() + "\n");
			br.write("Secretari/a: " + secretari.getText() + "\n");
			br.write("Cap d'Estudis: \n" + capEstudi.getText() + "\n");
			br.write("Coordinació: " + coordinacio.getText());

			br.close();
		}
	}

	/**
	 * Metodo que recalcula y da a cada objeto Professor un nuevo cargo sin
	 * repetir hasta que no existan mas combinaciones.
	 * 
	 * @param event
	 */
	@FXML
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
				alert.setContentText("Has de seleccionar un nou arxiu XML compatible.");
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
			alert.setContentText("Has de seleccionar un arxiu XML compatible.");
			alert.showAndWait();
		}
	}

	/**
	 * Metodo que almacena los datos del XML en un List de objetos
	 * Seleccionador, donde estaran los datos.
	 * 
	 * @param data
	 */
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

	/**
	 * Metodo que selecciona los objetos Professor, teniendo en cuenta que no se
	 * repita y que no haya mas hombres que mujeres y viceversa.
	 * 
	 * @param professors
	 */
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
