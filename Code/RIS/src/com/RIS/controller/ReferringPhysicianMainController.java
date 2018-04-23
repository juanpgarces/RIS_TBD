package com.RIS.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.RIS.model.Order;
import com.RIS.model.Patient;

import application.RISDbConfig;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;


public class ReferringPhysicianMainController {
		    
		@FXML private TextField txtSearch, txtPatientId, txtPatientFirstName, txtPatientLastName, txtPatientPhoneNumber, txtPatientAddress, txtPatientDOB, txtInsuranceType, txtPatientEmail;
	    @FXML private TextArea txtPatientNotes, txtOrderNotes;
	    private String ID;
	    
		@FXML private ComboBox<String> txtPatientGender;
		@FXML private ComboBox<String> txtModality;
		@FXML private ComboBox<String> txtEmergencyLevel;
			
	    public void initialize()
	    {
	    	txtModality.getItems().removeAll(txtModality.getItems());
	    	txtPatientGender.getItems().removeAll(txtPatientGender.getItems());
	    	txtPatientGender.getItems().add("M");
	    	txtPatientGender.getItems().add("F");
	    	txtEmergencyLevel.getItems().removeAll(txtEmergencyLevel.getItems());
	    	txtEmergencyLevel.getItems().add("1");
	    	txtEmergencyLevel.getItems().add("2");
	    	txtEmergencyLevel.getItems().add("3");
	        
	    	String query = "SELECT DISTINCT name FROM modality";
	    	ResultSet rs;
	    	try (Connection conn = RISDbConfig.getConnection();
	    			PreparedStatement st = conn.prepareStatement(query);) {
	    		rs = st.executeQuery(); 		
	    	    
	            while(rs.next()) {
	            	txtModality.getItems().add(rs.getString("name"));
	            }
	    		} catch (Exception e) {
	    			System.out.println("Status: operation failed due to "+e);
	    			}

	    }
	    @FXML
	    public void searchPatient(ActionEvent event) {
	    	
	    	SimpleStringProperty patientid = new SimpleStringProperty(txtSearch.getText());
	    	
	    	String query = "SELECT * FROM patient WHERE patientID = ?;";
	    	ResultSet rs;
	    	
	    	try (Connection conn = RISDbConfig.getConnection();
					PreparedStatement getPatient = conn.prepareStatement(query);) {
	    		
	    		getPatient.setString(1, patientid.get());
	    		rs = getPatient.executeQuery();
	       		// check to see if receiving any data
	       		if(rs.next()){
	       			txtPatientId.setText(rs.getString("patientID"));
	       			txtPatientFirstName.setText(rs.getString("firstName"));
	       			txtPatientLastName.setText(rs.getString("lastName"));
	       			txtPatientGender.setValue(rs.getString("gender"));
	       			txtPatientPhoneNumber.setText(rs.getString("phone"));
	       			txtPatientAddress.setText(rs.getString("address"));
	       			txtPatientDOB.setText(rs.getDate("DOB").toString());
	       			txtInsuranceType.setText(rs.getString("insurance"));
	       			txtPatientEmail.setText(rs.getString("email"));
	       			txtPatientNotes.setText(rs.getString("notes"));
	       			
	       		}
	       		else {
	       			
	       			Alert alert = new Alert(AlertType.ERROR);
    	    		alert.setTitle("User Not Found");
    	    		alert.setHeaderText("User Not Found");
    	    		alert.setContentText("The User you are searching for does not appear in the system. Please check the Patient ID or Insert a New Patient");
    	    		alert.showAndWait();
	       		}
				
	    	} catch (Exception e) {
	    		System.out.println("Status: operation failed due to "+e);

			}
	    }
	    @FXML
	    void submitOrder(ActionEvent event) {	    	
	    	
	    	Order newOrder = new Order(
	    			txtEmergencyLevel.getValue(),
	    			ID,
	    			txtPatientId.getText(),
	    			txtModality.getValue(),
	    			txtOrderNotes.getText()
	    			);
	    	
	    	String query = "INSERT INTO orders " + "(emergencyLevel, userID, patientID, modality, notes) " + "VALUES(?,?,?,?,?)";
			
			try (Connection conn = RISDbConfig.getConnection();
				PreparedStatement insertprofile = conn.prepareStatement(query);) {
				
				insertprofile.setString(1, newOrder.getEmergencyLevel());
				insertprofile.setString(2, newOrder.getUserId());
				insertprofile.setString(3, newOrder.getPatientId());
				insertprofile.setString(4, newOrder.getModality());
				insertprofile.setString(5, newOrder.getNotes());
			
				insertprofile.execute();
				//message to successfull
       			Alert alert = new Alert(AlertType.ERROR);
	    		alert.setTitle("Order Created");
	    		alert.setHeaderText("Order Created");
	    		alert.setContentText("A new Order has been created.");
	    		alert.showAndWait();
	    		
	    		//SET EVERYTHING TO BLANK
	    		txtPatientId.setText("");
				txtPatientFirstName.setText("");
				txtPatientLastName.setText("");
				txtPatientGender.setValue("");
				txtPatientDOB.setText("");
				txtInsuranceType.setText("");
				txtPatientAddress.setText("");
				txtPatientPhoneNumber.setText("");
				txtPatientEmail.setText("");
				txtPatientNotes.setText("");
				txtEmergencyLevel.setValue("");
    			txtModality.setValue("");
    			txtOrderNotes.setText("");
	    		
				
			} catch (Exception e) {
				System.out.println("Status: operation failed due to "+e);
				} 
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
	    	    	
	    	String query1 = "INSERT INTO patient " + "(patientID, firstName, lastName, gender, DOB, insurance, address, phone, email, notes) " + "values(?,?,?,?,?,?,?,?,?,?);";
	    	String query2 = "UPDATE patient SET firstName= ?,lastName= ?, gender= ?, DOB=?, insurance=?, address=? , phone=?, email=?, notes=? WHERE patientID = ?;";
	    	String query = "SELECT * FROM patient WHERE patientID = ?;";
	    	
	    	try (Connection conn = RISDbConfig.getConnection();
					PreparedStatement checkuser = conn.prepareStatement(query);) {
	    		
	    		checkuser.setString(1, newPatient.getidPatient());
	    		
	    		if(checkuser.executeQuery().next()) {
	    			PreparedStatement updateprofile = conn.prepareStatement(query2);
	    			
	    			updateprofile.setString(1, newPatient.getFirstName());
	    			updateprofile.setString(2, newPatient.getLastName());
	    			updateprofile.setString(3, newPatient.getGender());
	    			updateprofile.setString(4, newPatient.getDOB());
	    			updateprofile.setString(5, newPatient.getInsurance());
	    			updateprofile.setString(6, newPatient.getAddress());
	    			updateprofile.setString(7, newPatient.getPhone());
	    			updateprofile.setString(8, newPatient.getEmail());
	    			updateprofile.setString(9, newPatient.getNotes());	
	    			updateprofile.setString(10, newPatient.getidPatient());
	    			
	    			updateprofile.execute();
	    		}
	    		else {
	    			PreparedStatement insertprofile = conn.prepareStatement(query1);
	    			
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
	    		}		
	    	} catch (Exception e) {
	    		System.out.println("Status: operation failed due to "+e);

			}
	    }
	    
	    public void Logout(ActionEvent event) throws IOException
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
