package com.RIS.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.RIS.model.Order;

import application.RISDbConfig;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
public class CreateOrderController {

    @FXML private ComboBox<String> comboModality;
    @FXML private TextField emergencyLevel, patientId;
    @FXML private TextArea notes;



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


    @FXML
    void submit(ActionEvent event) {
    	int modID = 0;
    	String query = "SELECT modID FROM modality WHERE name='"+comboModality.getValue()+"'";
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

    @FXML
    void cancel(ActionEvent event) {

    }
}