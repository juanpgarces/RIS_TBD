package com.RIS.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import application.RISDbConfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class LoginPageController implements Initializable {

	@FXML private Button btn_patient1;	
	@FXML private TextField TextUsername;
	@FXML private PasswordField TextPassword;
	
	// This method will redirect the scene into the patient TableView
    public void LoginTrigger(ActionEvent event) throws IOException
    {
   	
    	ResultSet rs = null;
    	String SQLQuery = "SELECT userType FROM User WHERE userID = ? AND userPwd = ?;";
    	
    	try(
        	    Connection conn = RISDbConfig.getConnection();
        	    PreparedStatement loggin = conn.prepareStatement(SQLQuery);
        	){	
    			loggin.setString(1, TextUsername.getText());
        	    rs = loggin.executeQuery();
        	    
        	    //If the result set found a match, continues
        	    if(rs.next()) {
        	    	
        	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../com/RIS/view/PatientMainMenu.fxml"));
        	    	
        	    	switch(rs.getString(0)) {
        	    		case "Technician": loader = new FXMLLoader(getClass().getResource("../../../com/RIS/view/TechnicianView.fxml"));
        	    		break;
        	    		case "Physician": loader = new FXMLLoader(getClass().getResource("../../../com/RIS/view/ReferringPhysicianView.fxml"));
        	    		break;
        	    		case "Radiologist": loader = new FXMLLoader(getClass().getResource("../../../com/RIS/view/RadiologistPage.fxml"));
        	    		break;
        	    		case "Receptionist": loader = new FXMLLoader(getClass().getResource("../../../com/RIS/view/ReceptionistPage.fxml"));
        	    		break;
        	    		case "Administrator": loader = new FXMLLoader(getClass().getResource("../../../com/RIS/view/AddUser.fxml"));
        	    		break;
        	    		default:
        	    	}
        	    	
        	    	Parent root = (Parent) loader.load();
        	        PatientMainMenuController controller = loader.getController();
        	        controller.setID(TextUsername.getText());
        	        Stage stage = new Stage();
        	        stage.setTitle("RIS");
        		    //stage.getIcons().add(new Image("/com/RIS/images/blueHeartbeat.png"));
        	        stage.setScene(new Scene (root));
        		    stage.setResizable(false);
        	        stage.show();
        	    }
        	    
        	    //If not shows error
        	    else {
        	    		Alert alert = new Alert(AlertType.ERROR);
        	    		alert.setTitle("Invalid Username/Password");
        	    		alert.setHeaderText("User is Invalid");
        	    		alert.setContentText("The Username/Password entered is not present in our current system, please enter a valid Patient ID or Contact and Administrator");
        	    		alert.showAndWait();
        	    }
        	    
        	} catch (Exception e) {
    			System.out.println("Status: operation failed due to "+e);

    		}
    }
    
	// This method will redirect the scene into the patient TableView
    public void changeSceneToDoctorPage(ActionEvent event) throws IOException
    {
    	String username = "Insulinpump";
    	String password = "1234";

    	if(TextUsername.getText().equals(username) && TextPassword.getText().equals(password)) {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../../../com/RIS/view/DoctorPage.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Insulin Pump");
	    window.getIcons().add(new Image("/com/RIS/images/blueHeartbeat.png"));
        window.setScene(tableViewScene);
        window.show();
    	}
    	else {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Username Error");
    		alert.setHeaderText("Username Error");
    		alert.setContentText("The Username/Password entered is invalid, please enter a valid Username/Password");
    		alert.showAndWait();
    	}
    }


	public void initialize (URL url, ResourceBundle rb) {
	}
}


