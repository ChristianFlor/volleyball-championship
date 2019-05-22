package ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Competitor;
import model.Espectator;
import model.Event;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
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
    private Canvas canva;
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
				Alert a = new Alert(AlertType.CONFIRMATION);
	    		a.setContentText("The data is load correct");
	    		a.show();
			} catch(FileNotFoundException e) {
				Alert a = new Alert(AlertType.INFORMATION);
	    		a.setContentText("Please provide a filepath first");
	    		a.show();
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
        			dFirstName.setText("Name: "+comp.getFirstName());
        			dLastName.setText("Last Name: "+comp.getLastName());
        			dEmail.setText("Email: "+comp.getEmail());
        			dGender.setText("Gender: "+comp.getGender());
        			dCountry.setText("Country: "+comp.getCountry());
        			dBirthday.setText("Birthdate: "+comp.getBirthDay());
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
    	if(e!= null) {
    		canva.getGraphicsContext2D().clearRect(0, 0, canva.getWidth(), canva.getHeight());
        	this.e.setWidth(canva.getWidth());
        	this.e.setHeight(canva.getHeight());
        	this.e.assignePositions();
        	this.canva.getGraphicsContext2D().setLineWidth(3);
        	double ny = e.getTreeHeight()*90+50;
        	if(ny > canva.getHeight()) {
        		canva.setHeight(ny);
        	}
        	e.increaseBounds();
        	this.canva.setWidth(e.getWidth());
        	drawLinesForTree(e.getRoot(), e.getRoot());
        	drawImagesForTree(e.getRoot(), e.getRoot());
    	} else {
    		Alert a = new Alert(AlertType.INFORMATION);
    		a.setContentText("Please load a file with the assistants info first");
    		a.show();
    	}
    }
    
    public void drawImagesForTree(Espectator node, Espectator parent) {
    	if(node != null) {
    		canva.getGraphicsContext2D().drawImage(new Image(node.getPathForPhoto()), node.getX(), node.getY());
    		canva.getGraphicsContext2D().fillText(node.getId()+"", node.getX(), node.getY()+30);
    		drawImagesForTree(node.getLeft(), node);
    		drawImagesForTree(node.getRight(), node);
    	}
    }
    
    public void drawLinesForTree(Espectator node, Espectator parent) {
    	if(node != null) {
    		canva.getGraphicsContext2D().strokeLine(parent.getX()+25, parent.getY()+25, node.getX()+25, node.getY()+25);
    		drawLinesForTree(node.getLeft(), node);
    		drawLinesForTree(node.getRight(), node);
    	}
    }
    
    @FXML
    void participants(ActionEvent event) {
    	if(e!= null) {
    		canva.getGraphicsContext2D().clearRect(0, 0, canva.getWidth(), canva.getHeight());
        	this.e.setWidth(canva.getWidth());
        	this.e.setHeight(canva.getHeight());
        	this.e.assignePositionsList();
        	this.canva.getGraphicsContext2D().setLineWidth(3);
        	double ny = e.getTreeHeight()*90+50;
        	if(ny > canva.getHeight()) {
        		canva.setHeight(ny);
        	}
        	e.increaseBounds();
        	this.canva.setWidth(e.getWidth());
        	drawLinesForList(e.getFirst());
        	drawImagesForList(e.getFirst());
    	} else {
    		Alert a = new Alert(AlertType.INFORMATION);
    		a.setContentText("Please load a file with the assistants info first");
    		a.show();
    	}
    }
    
    public void drawImagesForList(Competitor node) {
    	if(node != null) {
    		canva.getGraphicsContext2D().drawImage(new Image(node.getPathForPhoto()), node.getX(), node.getY());
    		canva.getGraphicsContext2D().fillText(node.getId()+"", node.getX(), node.getY());
    		drawImagesForList(node.getNext());
    	}
    }
    
    public void drawLinesForList(Competitor node) {
    	if(node != null) {
    		if(node.getNext() != null) {
    			canva.getGraphicsContext2D().strokeLine(node.getX()+25, node.getY()+25, node.getNext().getX()+25, node.getNext().getY()+25);
    		}
    		if(node.getPrevious() != null) {
    			canva.getGraphicsContext2D().strokeLine(node.getX()+25, node.getY()+50, node.getPrevious().getX()+25, node.getPrevious().getY()+50);
    		}
    		drawLinesForList(node.getNext());
    	}
    }
    
}
