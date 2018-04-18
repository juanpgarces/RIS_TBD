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
    @FXML private TextField txtFirstName, txtLastName, txtId;
    @FXML private TextArea txtNotes;
    @FXML private ComboBox<String> comboModality;
    @FXML private ComboBox<Integer> comboHour, comboMinute;
    @FXML private Text txtSuccess;
    private String PatientID, Notes, UserID, date, modality;
	private int setModalityId, startTime, stopTime;
    
    public void initialize() {
    	
    	txtId.setText(PatientID);
    	txtNotes.setText(Notes);
    	//SET MODALITY OPTIONS
    	//USER ID
    	if(date != null) {
    		//SET ALL OTHER VARIABLES FOR APPOINTMENT EDIT
    	}
    	
        comboModality.getItems().removeAll(comboModality.getItems());
    	String query = "SELECT name FROM modality";
    	ResultSet rs;
    	try (Connection conn = RISDbConfig.getConnection();
    			PreparedStatement st = conn.prepareStatement(query);) {
    		rs = st.executeQuery(); 		
    	    st.close();
    	    
            while(rs.next()) {
            	comboModality.getItems().add(rs.getString("name"));
            }
    		} catch (Exception e) {
    			System.out.println("Status: operation failed due to "+e);
    			}
    	comboHour.getItems().removeAll(comboHour.getItems());
    	comboMinute.getItems().removeAll(comboMinute.getItems());
    	for(int hour=8; hour<=12; hour++)
    		comboHour.getItems().add(hour);
    	for(int hour=1; hour<=4; hour++)
    		comboHour.getItems().add(hour);
    	comboMinute.getItems().add(00);
    	comboMinute.getItems().add(15);
    	comboMinute.getItems().add(30);
    	comboMinute.getItems().add(45);
    }
 
    public void createAppointment(ActionEvent event){
    	
    	int modID = 0;
    	String userID = null;
    	int duration = 0;
    	String orderNotes = "";
    	if (comboHour.getValue() <=12) //accounts for AM or PM
    		startTime = (comboHour.getValue()+12)*100 + comboMinute.getValue()*(5/3);
    	else
    		startTime = comboHour.getValue()*100 + comboMinute.getValue()*(5/3);
    	
    	
    	  //Gets modality ID and duration based on the modality selected in the comboBox
    	String query = "SELECT modID, duration FROM modality WHERE name='"+comboModality.getValue()+"'"
    			+ " AND NOT EXISTS(SELECT modID FROM appointment WHERE modID= modality.modID"
    			+ " AND (" + startTime + " AND " + startTime+ " + duration ) > startTime AND" + startTime + "< stopTime"
    			+ " AND );"  ;
    	try (Connection conn = RISDbConfig.getConnection();
    			PreparedStatement st = conn.prepareStatement(query);) {
    		ResultSet rs = st.executeQuery();
    		
    		modID = rs.getInt("modID");
    		duration = rs.getInt("duration"); //obtains duration of modality from database
    		
    	    st.close();
    		
    			
    		//System.out.println("Success -> modID=" + modID + "/t duration="+duration);
    		} catch (Exception e) {
    			System.out.println("Status: operation failed due to "+e);
    			}    	
    	//gets userID from the order with the same patient ID and modality
    	
    	/* 
    	 * 
    	 * Getting the userID this way will only work if the patient doesn't have multiple orders
    	 * *************JP - Why get the userID from the order table if we can just enter the Patient's SSN when creating the order. To even make it better
    	 * 					We can have a button to create new appointment by selecting an order from the Receptionist View and 
    	 * 					passing that ID + Modality to the New Appointment View
    	 * for the same modality (which most likely wouldn't be necessary). I'm still looking for
    	 * the solution online. If I can't, then it's still not essential.
    	 * 
    	 */
    	query = "SELECT userID, notes FROM order WHERE patientID='" + txtId.getText() + "' AND modID='" + modID + "'";
    	try (Connection conn = RISDbConfig.getConnection();
    			PreparedStatement st = conn.prepareStatement(query);) {
    		ResultSet rs = st.executeQuery();
    		
    		userID = rs.getString("userID");
    		orderNotes = rs.getString("notes");
    		
    	      st.close();
    	      
    		
    			
    		System.out.println("Success -> userId =" + userID);
    		} catch (Exception e) {
    			System.out.println("Status: operation failed due to "+e);
    			}  
    	
    	//converts time to decimal
//    	if (comboHour.getValue() <=12) //accounts for AM or PM
//    		startTime = (comboHour.getValue()+12)*100 + comboMinute.getValue()*(5/3);
//    	else
//    		startTime = comboHour.getValue()*100 + comboMinute.getValue()*(5/3);
    	
    	stopTime = startTime + duration;
    	
    	// creates appointment object. 
    	Appointment newApp = new Appointment(
    			userID,
    			txtId.getText(),
    			modID,
    			txtDate.getValue().toString(),
    			startTime,
    			stopTime, //endTime = txtTime.getText + duration,
    			orderNotes
    			);		
    	
   		//parameters-->	Appointment(String userId, String patientId, int modalityId, String startTime, String stopTime)
    			
    			
    			
    		/// insert appointment into database
    			query = "INSERT INTO appointment " + "(userID, patientID, modalityID, startTime, stopTime, notes, status) " + "VALUES(?,?,?,?,?,?,?)";
    			try (Connection conn = RISDbConfig.getConnection();
    					PreparedStatement insertprofile = conn.prepareStatement(query);) {
    				
    				/*
    				 *  Not sure how the appID should be inserted since it is auto generated in the database
    				 */
    					
    					insertprofile.setString(1, newApp.getUserId());
    					insertprofile.setString(2, newApp.getPatientId());
    					insertprofile.setString(3, ""+newApp.getModalityId());
    					insertprofile.setString(4, ""+newApp.getStartTime());
    					insertprofile.setString(5, ""+newApp.getStopTime());
    					insertprofile.setString(6, newApp.getNotes());
    					insertprofile.setString(7, newApp.getStatus());
    					
    				
    					insertprofile.execute();
    					txtSuccess.setText("Success! Appointment has been created.");
    					
    				} catch (Exception e) {
    					System.out.println("Status: operation failed due to "+e);
    					}    
    }

	public void setID(String id) {
		this.PatientID = id;
	}

	public void setNotes(String notes) {
		this.Notes = notes;
	}

	public void setUserID(String userId) {
		this.UserID = userId;
	}

	public void setModality(String modality) {
		this.modality = modality;
	}

	public void setModalityId(int modalityId) {
		this.setModalityId = modalityId;
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
}