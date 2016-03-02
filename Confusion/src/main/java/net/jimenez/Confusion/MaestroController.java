package net.jimenez.Confusion;

import javafx.fxml.FXML;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import javafx.event.ActionEvent;
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

	private List<String> dades;

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

			parser.parse(fitxer, new Processar());

		}
	}

	public void guardarDades(List<String> data) {
		dades = data;

		for (int i = 0; i < dades.size(); i++) {
			System.out.println(dades.get(i));
		}
	}
}
