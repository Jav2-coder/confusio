package net.jimenez.Confusion;

import javafx.fxml.FXML;

import java.io.File;

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

	// Event Listener on Button.onAction
	@FXML
	public void escollirFitxer(ActionEvent event) {
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("XML Files", "*.xml"));

		File selectedFile = fileChooser.showOpenDialog(null);

		if (selectedFile != null) {
			actionStatus.setText("File selected: " + selectedFile.getName());
		} else {
			actionStatus.setText("File selection cancelled.");
		}
	}
}
