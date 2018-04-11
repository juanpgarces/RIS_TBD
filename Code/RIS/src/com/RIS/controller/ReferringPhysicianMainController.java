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

public class ReferringPhysicianMainController {
	
	 @FXML private TableColumn<Patient , > txtId;

	    @FXML
	    private TableColumn<?, ?> txtFirstName;

	    @FXML
	    private TableColumn<?, ?> txtLastName;

	    @FXML
	    private TableColumn<?, ?> txtGender;

	    @FXML
	    private TableColumn<?, ?> txtDob;

	    @FXML
	    private TableColumn<?, ?> modalityID;

	    @FXML
	    private Button buttonNewOrder;

	    @FXML
	    private Button buttonLogOut;

	    @FXML
	    private Button buttonNewPatient;

}
