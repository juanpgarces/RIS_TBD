package com.RIS.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.RIS.model.Appointment;
import com.RIS.model.User;

import application.RISDbConfig;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;

public class AddNewAppointmentController {

    @FXML private DatePicker txtDate; 
    @FXML private TextField txtId;
    @FXML private TextArea txtNotes;
    @FXML private ComboBox<Integer> comboModality;
    @FXML private ComboBox<Integer> comboHour, comboMinute;
    @FXML private Text txtSuccess;
    private String PatientID, Notes, UserID, date, modality;
	private int startTime, stopTime;
	private int ModalityId;
    
    public void initialize() {
    	
    	txtId.setText(PatientID);
    	txtNotes.setText(Notes);
    	//SET MODALITY OPTIONS
    	//USER ID

    	if(date != null) {

    		//SET ALL OTHER VARIABLES FOR APPOINTMENT EDIT
    	}

    	comboHour.getItems().removeAll(comboHour.getItems());
    	comboMinute.getItems().removeAll(comboMinute.getItems());
    	
    	for(int hour=1; hour<= 24; hour++)
    		comboHour.getItems().add(hour);
    	
    	comboMinute.getItems().add(00);
    	comboMinute.getItems().add(15);
    	comboMinute.getItems().add(30);
    	comboMinute.getItems().add(45);
    }
    public void setComboModality() {
        comboModality.getItems().removeAll(comboModality.getItems());
    	String query = "SELECT modID FROM modality WHERE name = ?  AND NOT EXISTS (SELECT modID from appointment WHERE status = 'new' AND Date = ? AND startTime = ? );";
    	ResultSet rs = null;
    	
    	startTime = (comboHour.getValue()) + (comboMinute.getValue()/60)*100;
    	
    	try (Connection conn = RISDbConfig.getConnection();
    			PreparedStatement st = conn.prepareStatement(query);) {
    		st.setString(1, modality);
    		st.setString(2, txtDate.getValue().toString());
    		st.setInt(3, startTime);
    		rs = st.executeQuery(); 		
    	    
            while(rs.next()) {
            	comboModality.getItems().add(rs.getInt("modID"));
            }
    		} catch (Exception e) {
    			System.out.println("Status: operation failed due to "+e);
    			}
    }
 
    public void createAppointment(ActionEvent event){
    	ResultSet rs = null;
    	int duration = 0;
    	
    	startTime = (comboHour.getValue()) + (comboMinute.getValue()/60)*100; 
    	
    	//Gets modality ID and duration based on the modality selected in the comboBox
    	String query = "SELECT duration from modality WHERE modID = ?;";
    	
    	try (Connection conn = RISDbConfig.getConnection();
    		PreparedStatement dur = conn.prepareStatement(query);) {
    		
    		dur.setInt(1, comboModality.getValue());

    		rs = dur.executeQuery();
    		
    		duration = rs.getInt("duration");
    		
    		} catch (Exception e) {
    			System.out.println("Status: operation failed due to "+e);
    			}  	
    	
    	stopTime = ((startTime) + (duration/60)*100);
    	
    	// creates appointment object. 
    	Appointment newApp = new Appointment(
    			UserID,
    			PatientID,
    			comboModality.getValue(),
    			txtDate.getValue().toString(),
    			startTime,
    			stopTime,
    			Notes
    			);		
    	
   		//parameters-->	Appointment(String userId, String patientId, int modalityId, String startTime, String stopTime)
    		/// insert appointment into database
    		query = "INSERT INTO appointment (userID, patientID, modalityID, date, startTime, stopTime, notes, status) " + "VALUES(?,?,?,?,?,?,?,?)";
    			try (Connection conn = RISDbConfig.getConnection();
    					PreparedStatement insertprofile = conn.prepareStatement(query);) {
    				
    					insertprofile.setString(1, newApp.getUserId());
    					insertprofile.setString(2, newApp.getPatientId());
    					insertprofile.setInt(3, newApp.getModalityId());
    					insertprofile.setString(4, newApp.getDate());
    					insertprofile.setInt(5, newApp.getStartTime());
    					insertprofile.setInt(6, newApp.getStopTime());
    					insertprofile.setString(7, newApp.getNotes());
    					insertprofile.setString(8, newApp.getStatus());

    					insertprofile.execute();
    					txtSuccess.setText("Success! Appointment has been created.");
    					
    				} catch (Exception e) {
    					System.out.println("Status: operation failed due to "+e);
    					}    
    }

	public void setID(String id) {
		this.PatientID = id;
    	txtId.setText(id);
	}

	public void setNotes(String notes) {
		this.Notes = notes;
    	txtNotes.setText(notes);
	}

	public void setUserID(String userId) {
		this.UserID = userId;
	}

	public void setModality(String modality) {
		this.modality = modality;
	}
	
	public void setDate(String date) {
		this.date = date;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public void setStopTime(int stopTime) {
		this.stopTime = stopTime;
	}
	public void setModalityId(int modalityId) {
		this.ModalityId = modalityId;
	}	
}