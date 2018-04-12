package com.RIS.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import application.RISDbConfig;
import javafx.event.ActionEvent;


public class ReferringPhysicianMainController {
	
	
 

	    @FXML private TextField searchId;
	    @FXML private Button searchIdButton;
	    @FXML private Text returnPatientId;
	    @FXML private Text returnPatientFirstName;
	    @FXML private Text returnPatientLastName;
	    @FXML private Button newOrderButton;
	    @FXML private Button newPatientButton;
	    @FXML private Button logOutButton;

	    public void initialize()
	    {
<<<<<<< HEAD
	    	//returnPatientId.getId().removeAll(returnPatientId.getId());
=======
	    	// How do i remove the existing information in this field?
	    	returnPatientId.getId().???????(returnPatientId.getId());
>>>>>>> 84fc9be4aab7315953d9fdd9ac95e012689fdf1d
	    	String query = "SELECT idPatient FROM patient";
	    }
	    
	    public void searchId (ActionEvent event)
	    {
	    	
	    }

}
