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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LoginPageController implements Initializable {

	@FXML private Button btn_patient1;	
	@FXML private TextField TextUsername;
	@FXML private PasswordField TextPassword;
	private String ID;
	// This method will redirect the scene into the patient TableView
    public void LoginTrigger(ActionEvent event) throws IOException
    {
   	
    	ResultSet rs = null;
    	String SQLQuery = "SELECT userType FROM user WHERE userID = ? AND passwd = ?;";
    	
    	try(
        	    Connection conn = RISDbConfig.getConnection();
        	    PreparedStatement loggin = conn.prepareStatement(SQLQuery);
        	){	
    			loggin.setString(1, TextUsername.getText());
    			loggin.setString(2, TextPassword.getText());
        	    rs = loggin.executeQuery();
        	    
        	    //If the result set found a match, continues
        	    if(rs.next()) {

        	    	/**************THIS EXAMPLE PASSES THE IDS TO OTHER CONTROLLERS******************/
        	    	//Parent tableViewParent = FXMLLoader.load(getClass().getResource("../../../com/RIS/view/AddUser.fxml"));
        	    	String temp = "Doesn't Matter";
        	    	
        	    	//Decides which View to Load
        	    	switch(rs.getString(1)) { 	
    	    		case "Technician": temp = "../../../com/RIS/view/Technician.fxml";
    	    		break;
    	    		case "Physician": temp = "../../../com/RIS/view/ReferringPhysicianMain.fxml";
    	    		break;
    	    		case "Radiologist": temp = "../../../com/RIS/view/Radiologist.fxml";
    	    		break;
    	    		case "Receptionist": temp = "../../../com/RIS/view/ReceptionistMain.fxml";
    	    		break;
    	    		case "Administrator": temp = "../../../com/RIS/view/AddUser.fxml";
    	    		break;
    	    		default:
    	    	}
        	    	
        	    	FXMLLoader loader = new FXMLLoader(getClass().getResource(temp));
        	    	Parent root = (Parent) loader.load();
        	        Stage stage = new Stage();

        	        
        	    	//Decides Which Controller to start Running
        	    	switch(rs.getString(1)) {
        	    		case "Technician": TechnicianViewController tcontroller = loader.getController();
            	        tcontroller.setID(TextUsername.getText());
            	        stage.setTitle("RIS - Technician");
        	    		break;
        	    		case "Physician": ReferringPhysicianMainController pcontroller = loader.getController();
            	        pcontroller.setID(TextUsername.getText());
            	        stage.setTitle("RIS - Physician");
        	    		break;
        	    		case "Radiologist": RadiologistController racontroller = loader.getController();
            	        racontroller.setID(TextUsername.getText());
            	        stage.setTitle("RIS - Radiologist");
        	    		break;
        	    		case "Receptionist": ReceptionistMainController rcontroller = loader.getController();
            	        rcontroller.setID(TextUsername.getText());
            	        stage.setTitle("RIS - Receptionist");
        	    		break;
        	    		case "Administrator":
        	    		stage.setTitle("RIS - Technician");
        	    		break;
        	    		default:
        	    	}
        	    	//Initiates New View
        	        stage.setScene(new Scene (root));
        	        stage.show();
        	        
        	        //Closes the Logging Window
        	        Stage ostage = (Stage) TextUsername.getScene().getWindow();
        	        ostage.close();
        	    }
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
    
    public void setID(String setID) {
    	ID = setID;
    }

    public String getID() {
    	return ID;
    }

	public void initialize (URL url, ResourceBundle rb) {
	}
}


