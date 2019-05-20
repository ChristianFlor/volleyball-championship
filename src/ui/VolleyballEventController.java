package ui;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Competitor;
import model.Espectator;
import model.Event;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VolleyballEventController {
	private Event e;
    @FXML
    private ImageView foundCompImg;
    @FXML
    private Label dFirstName;

    @FXML
    private Label dLastName;

    @FXML
    private Label dEmail;

    @FXML
    private Label dGender;

    @FXML
    private Label dCountry;

    @FXML
    private Label dBirthday;

    @FXML
    private TextField dataPath;

    @FXML
    private TextField idEspec;

    @FXML
    private Label timeCheck;

    @FXML
    private TextField idComp;

    @FXML
    private Label timeCheck1;
    
    @FXML
    public void initialize() {
    	dataPath.setStyle("-fx-text-inner-color: black;");
    }

    @FXML
    void explore(ActionEvent event) {
    	JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "CSV FILE", "csv");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
        	String path = chooser.getCurrentDirectory().getPath();
        	dataPath.setText(path + File.separator + chooser.getSelectedFile().getName());
        }
    }

    @FXML
    void load(ActionEvent event) {
    	if(dataPath.getText() == "" || dataPath.getText() == null) {
    		Alert a = new Alert(AlertType.ERROR);
    		a.setContentText("The path hasn't been chosen");
    		a.show();
    	} else {
    		try {
    			e = new Event();
				e.load(dataPath.getText());
				e.selectCompetitors();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
    	}
    }

    @FXML
    void participants(ActionEvent event) {
    	
    }

    @FXML
    void searchComp(ActionEvent event) {
    	String id = idComp.getText();
    	if(id != "" && id != null) {
    		Competitor comp = e.searchCompetitor(id);
    		if(comp != null) {
    			foundCompImg.setImage(new Image(comp.getPathForPhoto()));
    		} else {
    			Alert a = new Alert(AlertType.INFORMATION);
        		a.setContentText("Competitor with ID: "+ id +" not found");
        		a.show();
    		}
    		
    	} else {
    		Alert a = new Alert(AlertType.INFORMATION);
    		a.setContentText("Please type an ID");
    		a.show();
    	}
    	
    }

    @FXML
    void searchEspec(ActionEvent event) {
    	String id = idEspec.getText();
    	if(id != "" && id != null) {
    		foundCompImg.setImage(new Image(e.searchEspectator(id).getPathForPhoto()));
    	} else {
    		Alert a = new Alert(AlertType.INFORMATION);
    		a.setContentText("Please type an ID");
    		a.show();
    	}
    	
    }

    @FXML
    void spectators(ActionEvent event) {
    	
    }

}
