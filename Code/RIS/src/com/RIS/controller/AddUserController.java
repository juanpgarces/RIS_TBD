package com.RIS.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.RIS.model.User;

import application.RISDbConfig;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;

public class AddUserController {

    @FXML private TextField txtUserId, txtPasswd;
    @FXML private TextField txtUserFirstName;
    @FXML private TextField txtUserLastName;
    @FXML private TextField txtUserPhoneNumber;
    @FXML private TextField txtUserEmail;
    @FXML private Button btnAddNewUser;
    @FXML private Button btndoctorHomeButton;
    @FXML private ComboBox<String> txtUserGender;
    @FXML private ComboBox<String> txtUserType;
    @FXML private Text txtSuccess;
    
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

		String query = "INSERT INTO user " + "(userId, passwd, userType, firstName, lastName, phone, gender, email) " + "VALUES(?,?,?,?,?,?,?,?)";
		
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
			txtSuccess.setText("Success! User Has Been Created");
			
		} catch (Exception e) {
			System.out.println("Status: operation failed due to "+e);
			}    	
    }
    
    public void LogOff(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../../../com/RIS/view/LoginPage.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("RIS Home - Administrator");
        window.setScene(tableViewScene);
        window.show();
    	
    }
}
