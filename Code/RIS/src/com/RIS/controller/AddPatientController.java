package com.RIS.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.RIS.model.*;

import application.RISDbConfig;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddPatientController {
	


	
	@FXML private TextField txtPatientId, txtPatientFirstName, txtPatientLastName, txtPatientPhoneNumber, txtPatientAddress, txtPatientDOB, txtInsuranceType, txtPatientEmail;
	@FXML private Button btnAddNewPatient, btndoctorHomeButton;
    @FXML private TextArea txtPatientNotes;
	@FXML private ComboBox<String> txtPatientGender = new ComboBox<String>(
			FXCollections.observableArrayList(
					  "M",
	    	          "F"
	    	 )
	);

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
    	if(event.getSource() == btnAddNewPatient) {

		    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			Parent tableViewParent = FXMLLoader.load(getClass().getResource("../../../com/RIS/view/ReferringPhysicianView.fxml"));
		    Scene tableViewScene = new Scene(tableViewParent);
	        window.setScene(tableViewScene);
	        window.show();
			}
    }

}

