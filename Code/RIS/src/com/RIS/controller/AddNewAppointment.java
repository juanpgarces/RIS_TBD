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

public class AddNewAppointment {

    @FXML private DatePicker txtDate; 
    @FXML private TextField txtTime, txtFirstName, txtLastName, txtId;
    @FXML private TextArea txtNotes;
    @FXML private ComboBox<String> comboModality;
    @FXML private Text txtSuccess;
    
    
    
    public void initialize() {
    	
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
    }
 
    public void createAppointment(ActionEvent event){
    	
    	int modID = 0;
    	String userID = null;
    	int duration;
    	String orderNotes = "";
    	
    	  //Gets modality ID and duration based on the modality selected in the comboBox
    	String query = "SELECT modID, duration, notes FROM modality WHERE name='"+comboModality.getValue()+"'";
    	try (Connection conn = RISDbConfig.getConnection();
    			PreparedStatement st = conn.prepareStatement(query);) {
    		ResultSet rs = st.executeQuery();
    		
    		modID = rs.getInt("modID");
    		duration = rs.getInt("duration"); //obtains duration of modality from database
    		orderNotes = rs.getString("notes");
    		
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
    	query = "SELECT userID FROM order WHERE patientID='" + txtId.getText() + "' AND modID='" + modID + "'";
    	try (Connection conn = RISDbConfig.getConnection();
    			PreparedStatement st = conn.prepareStatement(query);) {
    		ResultSet rs = st.executeQuery();
    		
    		userID = rs.getString("userID");
    		
    	      st.close();
    		
    			
    		System.out.println("Success -> userId=" + userID);
    		} catch (Exception e) {
    			System.out.println("Status: operation failed due to "+e);
    			}  
    		
    	
    	// creates appointment object. 
    	Appointment newApp = new Appointment(
    			userID,
    			txtId.getText(),
    			modID,
    			txtTime.getText(),
    			//endTime = txtTime.getText + duration,
    			orderNotes
    			);		
    	
   		//parameters-->	Appointment(String userId, String patientId, int modalityId, String startTime, String stopTime)
    			
    			
    			
    		/// insert appointment into database
    			query = "INSERT INTO appointment " + "(appID, userID, patientID, modalityID, startTime, stopTime) " + "VALUES(?,?,?,?,?,?)";
    			try (Connection conn = RISDbConfig.getConnection();
    					PreparedStatement insertprofile = conn.prepareStatement(query);) {
    				
    				/*
    				 *  Not sure how the appID should be inserted since it is auto generated in the database
    				 */
    					//insertprofile.setString(1, newApp.getAppId());
    					insertprofile.setString(2, newApp.getUserId());
    					insertprofile.setString(3, newApp.getPatientId());
    					insertprofile.setString(4, ""+newApp.getModalityId()); //error because modalityID is an int
    					insertprofile.setString(5, newApp.getStartTime());
    					insertprofile.setString(6, newApp.getStopTime());
    					
    				
    					insertprofile.execute();
    					txtSuccess.setText("Success! Appointment has been created.");
    					
    				} catch (Exception e) {
    					System.out.println("Status: operation failed due to "+e);
    					}    
    }	
}