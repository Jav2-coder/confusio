package net.jimenez.Confusion;

import javafx.fxml.FXML;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

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

	private List<List<String>> professorat;

	private List<String> novaDireccio = new ArrayList<String>();

	final static int DIRECTOR = 0;
	final static int SECRETARI = 1;
	final static int CAPESTUDIS_A = 2;
	final static int CAPESTUDIS_B = 3;
	final static int COORDINADOR = 4;

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

		int candidat = 0;
		
		while (candidat < 5) {

			seleccioCandidats(professorat.get(candidat));
			
			candidat++;
		}
		
		director.setText(novaDireccio.get(DIRECTOR));
		secretari.setText(novaDireccio.get(SECRETARI));
		capEstudi.setText(novaDireccio.get(CAPESTUDIS_A) + "\n" + novaDireccio.get(CAPESTUDIS_B));
		coordinacio.setText(novaDireccio.get(COORDINADOR));
	}

	public void guardarDades(List<String> data) {

		List<String> newList = new ArrayList<String>();

		for (int i = 0; i < data.size(); i++) {

			newList.add(data.get(i));

		}

		for (int i = 0; i < 5; i++) {

			professorat.add(newList);

		}
	}

	public void seleccioCandidats(List<String> professors){
	
		boolean correcte = false;
		
		while (!correcte) {
			
			int posicio = rnd.nextInt(professors.size());
			
			String professor = professors.get(posicio);
			
			
		// comprovar si pot ser i no està repetit ...
		
		// si és correcte
			
		}
	}
}
