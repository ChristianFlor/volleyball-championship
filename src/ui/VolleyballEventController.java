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
    void searchEspec(ActionEvent event) {
    	if(e != null) {
    		String id = idEspec.getText();
        	long start = System.currentTimeMillis();
    		Espectator esp = e.searchEspectator(id);
    		long endTime = (System.currentTimeMillis() - start);
           	timeCheck.setText("Time: "+endTime);
        	if(id != "" && id != null) {
        		if(esp != null) {
        			foundCompImg.setImage(new Image(esp.getPathForPhoto()));
        			dFirstName.setText("Name: "+esp.getFirstName());
        			dLastName.setText("Last Name: "+esp.getLastName());
        			dEmail.setText("Email: "+esp.getEmail());
        			dGender.setText("Gender: "+esp.getGender());
        			dCountry.setText("Country: "+esp.getCountry());
        			dBirthday.setText("Birthdate: "+esp.getBirthDay());
        		}else {
        			Alert a = new Alert(AlertType.INFORMATION);
            		a.setContentText("Competitor with ID: " + id + " not found");
            		a.show();
        		}
        		
        	} else {
        		Alert a = new Alert(AlertType.INFORMATION);
        		a.setContentText("Please type an ID");
        		a.show();
        	}
    	} else {
    		Alert a = new Alert(AlertType.INFORMATION);
    		a.setContentText("Please load a file with the assistants info first");
    		a.show();
    	}
    }

    @FXML
    void searchComp(ActionEvent event) {
    	if(e != null) {
    		String id = idComp.getText();
        	if(id != "" && id != null) {
        		long start = System.currentTimeMillis();
        		Competitor comp = e.searchCompetitor(id);
        		long endTime = (System.currentTimeMillis() - start);
               	timeCheck1.setText("Time: "+endTime);
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
    	} else {
    		Alert a = new Alert(AlertType.INFORMATION);
    		a.setContentText("Please load a file with the assistants info first");
    		a.show();
    	}
    }

   

    @FXML
    void spectators(ActionEvent event) {
    	
    }
    @FXML
    void participants(ActionEvent event) {
    	
    }
}
