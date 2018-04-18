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

import com.RIS.model.Patient;

import application.RISDbConfig;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;


public class ReferringPhysicianMainController {
		    
		@FXML private TextField txtSearch, txtPatientId, txtPatientFirstName, txtPatientLastName, txtPatientPhoneNumber, txtPatientAddress, txtPatientDOB, txtInsuranceType, txtPatientEmail;
	    @FXML private TextArea txtPatientNotes, txtOrderNotes;
	    private String ID;
	    
		@FXML private ComboBox<String> txtPatientGender = new ComboBox<String>(
				FXCollections.observableArrayList(
						  "M",
		    	          "F"
		    	 )
		);
		@FXML private ComboBox<String> txtModality = new ComboBox<String>(
				FXCollections.observableArrayList(
						  "X-Ray",
		    	          "Something"
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
	    @FXML
	    public void searchPatient(ActionEvent event) {
	    	
	    	SimpleStringProperty patientid = new SimpleStringProperty(txtSearch.getText());
	    	
	    	String query = "SELECT * FROM patient WHERE patientID = '"+patientid.get()+"';";
	    	ResultSet rs = null;
	    	
	    	try (Connection conn = RISDbConfig.getConnection();
					PreparedStatement getPatient = conn.prepareStatement(query);) {
	    		
	    		rs = getPatient.executeQuery();
	       		// check to see if receiving any data
	       		if(rs != null){
	       			txtPatientId.setText(rs.getString("patientID"));
	       			txtPatientFirstName.setText(rs.getString("firstName"));
	       			txtPatientLastName.setText(rs.getString("lastName"));
	       			txtPatientPhoneNumber.setText(rs.getString("phone"));
	       			txtPatientAddress.setText(rs.getString("address"));
	       			txtPatientDOB.setText(rs.getString("DOB"));
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
	    	int modID = 0;
	    	
	    	String query = "SELECT modID FROM modality WHERE name='"+txtModality.getValue()+"';";
	    	try (Connection conn = RISDbConfig.getConnection();
	    			PreparedStatement st = conn.prepareStatement(query);) {
	    		ResultSet rs = st.executeQuery();
	    		
	    		modID = rs.getInt("modID");
	    		
	    	    st.close();
	    		
	    			
	    		//System.out.println("Success -> modID=" + modID + "/t duration="+duration);
	    		} catch (Exception e) {
	    			System.out.println("Status: operation failed due to "+e);
	    			}  
	  
	    	
	    	
	    	
	    	/*Order newOrder = new Order(
	    			emergencyLevel.getText(),
	    			//userID,
	    			patientId.getText(),
	    			modID,
	    			notes.getText()
	    			);
	    	//public Order(String emergencyLevel, String userID, String patientID, int modalityID, String notes)
	    	
	    	query = "INSERT INTO orders " + "(emergencyLevel, userID, patientID, modalityID, notes) " + "VALUES(?,?,?,?,?)";
			
			try (Connection conn = RISDbConfig.getConnection();
				PreparedStatement insertprofile = conn.prepareStatement(query);) {
				
				
				insertprofile.setString(1, newOrder.getEmergencyLevel());
				insertprofile.setString(2, newOrder.getUserId());
				insertprofile.setString(3, newOrder.getPatientId());
				insertprofile.setString(4, "" + newOrder.getModalityId());
				insertprofile.setString(5, newOrder.getNotes());
				
			
				insertprofile.execute();
				//txtSuccess.setText("Success! User Has Been Created");
				
			} catch (Exception e) {
				System.out.println("Status: operation failed due to "+e);
				} */
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
	    	String query2 = "UPDATE patient SET firstName= ?,lastName= ?, gender= ?, DOB=?, insurance=?, address=? , phone=?, email=?, notes=? WHERE patientID = '"+newPatient.getidPatient()+"';";
	    	String query = "SELECT * WHERE PatientID = '"+newPatient.getidPatient()+"';";
	    	
	    	try (Connection conn = RISDbConfig.getConnection();
					PreparedStatement checkuser = conn.prepareStatement(query);) {
	    		
	    		if(checkuser.executeQuery() != null) {
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
