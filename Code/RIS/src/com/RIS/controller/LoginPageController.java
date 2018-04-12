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
        	    	String temp = "../../../com/InsulinPump/view/RepectionistMaintMain.fxml";
        	    	FXMLLoader loader = new FXMLLoader(getClass().getResource(temp));
        	    	Parent root = (Parent) loader.load();
        	    	
        	    	switch(rs.getString(1)) {
        	    	
        	    		case "Technician": temp = "../../../com/RIS/view/Technician.fxml";
            	        TechnicianViewController tcontroller = loader.getController();
            	        //SETS ID IN OTHER CONTROLLER
            	        tcontroller.setID(TextUsername.getText());
        	    		break;
        	    		case "Physician": temp = "../../../com/RIS/view/ReferringPhysicianMain.fxml";
            	        ReferringPhysicianMainController pcontroller = loader.getController();
            	        pcontroller.setID(TextUsername.getText());
        	    		break;
        	    		case "Radiologist": temp = "../../../com/RIS/view/Radiologist.fxml";
            	        RadiologistController racontroller = loader.getController();
            	        racontroller.setID(TextUsername.getText());
        	    		break;
        	    		case "Receptionist": temp = "../../../com/RIS/view/ReceptionistMain.fxml";
            	        ReceptionistMainController rcontroller = loader.getController();
            	        rcontroller.setID(TextUsername.getText());
        	    		break;
        	    		case "Administrator": temp = "../../../com/RIS/view/AddUser.fxml";
        	    		break;
        	    		default:
        	    	}
        	    
        	        Stage stage = new Stage();
        	        stage.setTitle("RIS");
        	        stage.setScene(new Scene (root));
        	        stage.show();
        	    }
        	    	
        	   /*     Scene tableViewScene = new Scene(tableViewParent);
        	        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        	        window.setTitle("RIS");
        	        window.setScene(tableViewScene);
        	        window.show();
        	    }*/
        	    
        	    //If no error
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
    
	/*// This method will redirect the scene into the patient TableView
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
*/	
    public void setID(String setID) {
    	ID = setID;
    }

    public String getID() {
    	return ID;
    }

	public void initialize (URL url, ResourceBundle rb) {
	}
}


