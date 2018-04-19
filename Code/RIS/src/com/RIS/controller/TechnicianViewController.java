package com.RIS.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.RIS.model.Appointment;

import application.RISDbConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;

public class TechnicianViewController {

    @FXML private TableView<Appointment> techTable;
    @FXML private TableColumn<Appointment, Integer> colPatientID, colUserID;
    @FXML private TableColumn<Appointment, String> colTime, colModality, colEmergencyLevel;
    @FXML private TextField textAreaTechNotes;
    @FXML private TextFlow txtNotes;
    
    File stored;
    private String ID;
    @FXML
    void initialize() { 
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	LocalDate localDate = LocalDate.now();
    	
		techTable.setItems(getAppointmentList(dtf.format(localDate)));
    }

    public ObservableList<Appointment>  getAppointmentList(String date){
    	
    	//Appointment Table
    	colTime.setCellValueFactory(new PropertyValueFactory<Appointment, String>("startTimeToString"));
    	colPatientID.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("patientId"));
    	colUserID.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("userId"));
    	colModality.setCellValueFactory(new PropertyValueFactory<Appointment, String>("modality"));
    	colEmergencyLevel.setCellValueFactory(new PropertyValueFactory<Appointment, String>("emergencyLevel"));
 
    	
    	techTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    	
    	ObservableList<Appointment> appointment = FXCollections.observableArrayList();

        String SQLQuery = "SELECT * FROM appointment WHERE date = '"+date+"' AND status='new' ORDER BY startTime ASC;";
       	ResultSet rs = null;

       	try(
       			Connection conn = RISDbConfig.getConnection();
       			PreparedStatement displayappointment = conn.prepareStatement(SQLQuery);
       	){
       		rs = displayappointment.executeQuery();
       		while (rs.next()){
       			
       			appointment.add(new Appointment(rs.getString("patientID").toString(),rs.getInt("modalityID"),rs.getString("date"), rs.getInt("startTime"), rs.getInt("stopTime"), rs.getString("notes").toString()));
       		}
       	}catch(SQLException ex){
       		RISDbConfig.displayException(ex);
       		return null;
       	}finally{
       		if(rs != null){
       			//rs.close();
       		}
       	}
        return appointment;
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
