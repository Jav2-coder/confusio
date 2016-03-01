package net.jimenez.Confusion;

import javafx.fxml.FXML;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
				new ExtensionFilter("TXT Files", "*.txt"));

		File selectedFile = fileChooser.showOpenDialog(null);

		BufferedReader u = null;
		
		String r = null;
		
		try {
		
		u = new BufferedReader(new FileReader(selectedFile));
		
		while ((r = u.readLine()) != null) {
			
			String [] dades = r.split(", ");
			
			System.out.println(dades[0]);
			
		}
		
		} catch (IOException e) {

			e.printStackTrace();

		} finally {
			
			try {

				if (u != null) u.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
			
		}
		
		/*if (selectedFile != null) {
			actionStatus.setText("File selected: " + selectedFile.getName());
		} else {
			actionStatus.setText("File selection cancelled.");
		}*/
	}
}
