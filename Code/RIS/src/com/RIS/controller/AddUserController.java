package com.RIS.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.RIS.model.User;

import application.RISDbConfig;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AddUserController {

    @FXML private TextField txtUserId, txtPasswd;
    @FXML private TextField txtUserFirstName;
    @FXML private TextField txtUserLastName;
    @FXML private TextField txtUserPhoneNumber;
    @FXML private TextField txtUserEmail;
    @FXML private Button btnAddNewUser;
    @FXML private Button btndoctorHomeButton;
    @FXML private ComboBox<String> txtUserGender = new ComboBox<String>(
    	    FXCollections.observableArrayList(
    	            "M",
    	            "F"
    	        )
    	    );
    @FXML private ComboBox<String> txtUserType = new ComboBox<String>(
    	    FXCollections.observableArrayList(
    	            "Technician",
    	            "Physician",
    	            "Radiologist",
    	            "Receptionist"
    	    )
    	);
    
    @FXML
    void addNewUser(ActionEvent event) {
    	
    	//Create User Model
    	User newUser = new User(
    			txtUserId.getText(),
    			txtPasswd.getText(),
    			txtUserType.getValue(),
    			txtUserFirstName.getText(),
    			txtUserLastName.getText(),
    			txtUserPhoneNumber.getText(),
    			txtUserGender.getValue(),
    			txtUserEmail.getText());

		String query = "INSERT INTO User " + "(userId, passwd, userType, firstName, lastName, phone, gender, email) " + "VALUES(?,?,?,?,?,?,?,?)";
		
		try (Connection conn = RISDbConfig.getConnection();
			PreparedStatement insertprofile = conn.prepareStatement(query);) {
		
			insertprofile.setString(1, newUser.getUserId());
			insertprofile.setString(2, newUser.getPassword());
			insertprofile.setString(3, newUser.getUserType());
			insertprofile.setString(4, newUser.getFirstName());
			insertprofile.setString(5, newUser.getLastName());
			insertprofile.setString(6, newUser.getPhone());
			insertprofile.setString(7, newUser.getGender());
			insertprofile.setString(8, newUser.getEmail());
		
			insertprofile.execute();
			
		} catch (Exception e) {
			System.out.println("Status: operation failed due to "+e);
			}    	
    }

}
