package com.RIS.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.RIS.model.Patient;

import application.RISDbConfig;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;


public class ReferringPhysicianMainController {
	

	    @FXML private TextField searchId;
	    

		@FXML private TextField txtPatientId, txtPatientFirstName, txtPatientLastName, txtPatientPhoneNumber, txtPatientAddress, txtPatientDOB, txtInsuranceType, txtPatientEmail;
	    @FXML private TextArea txtPatientNotes;
	    private String ID;
	    
		@FXML private ComboBox<String> txtPatientGender = new ComboBox<String>(
				FXCollections.observableArrayList(
						  "M",
		    	          "F"
		    	 )
		);
		@FXML private ComboBox<String> txtModality = new ComboBox<String>(
				FXCollections.observableArrayList(
						  "M",
		    	          "F"
		    	 )
		);
		@FXML private ComboBox<String> txtEmergencyLevel = new ComboBox<String>(
				FXCollections.observableArrayList(
						  "Low",
		    	          "High"
		    	 )
		);
		
		
		
	    public void initialize()
	    {

	    }

	    public void newPatient(ActionEvent event) throws IOException{
	    	
	    	Patient newPatient = new Patient(txtPatientId.getText(),
	    								txtPatientFirstName.getText(),
	    								txtPatientLastName.getText(),
	    								txtPatientGender.getValue(),
	    								txtPatientDOB.getText(),
	    								txtInsuranceType.getText(),
	    								txtPatientAddress.getText(),
	    								txtPatientPhoneNumber.getText(),
	    								txtPatientEmail.getText(),
	    								txtPatientNotes.getText());
	    	    	
	    	String query = "INSERT INTO patient " + "(patientID, firstName, lastName, gender, DOB, insurance, address, phone, email, notes) " + "values(?,?,?,?,?,?,?,?,?,?)";
	    	
	    	try (Connection conn = RISDbConfig.getConnection();
					PreparedStatement insertprofile = conn.prepareStatement(query);) {
	    		
	    		insertprofile.setString(1, newPatient.getidPatient());
				insertprofile.setString(2, newPatient.getFirstName());
				insertprofile.setString(3, newPatient.getLastName());
				insertprofile.setString(4, newPatient.getGender());
				insertprofile.setString(5, newPatient.getDOB());
				insertprofile.setString(6, newPatient.getInsurance());
				insertprofile.setString(7, newPatient.getAddress());
				insertprofile.setString(8, newPatient.getPhone());
				insertprofile.setString(9, newPatient.getEmail());
				insertprofile.setString(10, newPatient.getNotes());
	    		
				insertprofile.execute();
				
	    	} catch (Exception e) {
	    		System.out.println("Status: operation failed due to "+e);

			}
	    }
	    
	    public void LogOff(ActionEvent event) throws IOException
	    {
	        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../../../com/RIS/view/LoginPage.fxml"));
	        Scene tableViewScene = new Scene(tableViewParent);
	        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	        window.setTitle("RIS Home");
	        window.setScene(tableViewScene);
	        window.show();
	    	
	    }


		public void setID(String text) {
			// TODO Auto-generated method stub
			this.ID = text;
		}

}
