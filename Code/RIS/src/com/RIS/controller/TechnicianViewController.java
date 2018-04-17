package com.RIS.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.RIS.model.Appointment;

import application.RISDbConfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

public class TechnicianViewController {

    @FXML private TableView<Appointment> techTable;
    @FXML private TableColumn<Appointment, Integer> colPatientID;
    @FXML private TableColumn<Appointment, String> colFirstName, colLastName, colModality, colPhys, colNotes;
    File stored;
    private String ID;
    @FXML
    void initialize() { 
    	
    	String modality = "";
    	String physicianName = "";
    	
    	String query = "SELECT name FROM modality WHERE";
    	ResultSet rs=null;
    	try (Connection conn = RISDbConfig.getConnection();
    			PreparedStatement st = conn.prepareStatement(query);) {
    		modality = rs.getString("name");
    		
    	    st.close();
    	    
            while(rs.next()) {
            	//comboModality.getItems().add(rs.getString("name"));
            }
    		} catch (Exception e) {
    			System.out.println("Status: operation failed due to "+e);
    			}
    	
    	
    }



    @FXML
    private TextField techNotesTextArea;

    @FXML
    private Text datetimeText;

    @FXML
    private Text procedureText;

    @FXML
    private Text reasonText;

    @FXML
    private Text referringText;

    @FXML
    private Text priorityText;

    @FXML
    void browse(ActionEvent event) {

    }
    
    
    @FXML
    void displayAppointments(ActionEvent event) {

    }

    @FXML
    void submit(ActionEvent event) {
    	//set status = "pending" in the database.

    }
    
    @FXML void imageSelect(ActionEvent event) {
    	
    	//Select Image
    	FileChooser fileChooser = new FileChooser();
    	File file = fileChooser.showOpenDialog(null);
    	stored = file;
    }
    
    @FXML void imageSave(ActionEvent event) {
    	//Save Image
    	if (stored != null) {
    		try {
    			saveImage(stored);
    		} catch (IOException ex) {
    			ex.printStackTrace();
    		}
    	}   	    	
    }
    
    public void saveImage(File file) throws IOException {
    	BufferedImage bufferedImage = new BufferedImage(100, 100, 1);
    	stored = new File("images/" + file.getName());
    	try {
    		if (!stored.exists()) {
    			ImageIO.write(bufferedImage, "png", stored);
    		}
    	} catch (IOException ex) {
    		ex.printStackTrace();
    	}   	
    }

	public void setID(String text) {
		// TODO Auto-generated method stub
		this.ID = text;
	}

}
