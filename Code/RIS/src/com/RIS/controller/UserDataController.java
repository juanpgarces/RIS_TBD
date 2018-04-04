package com.RIS.controller;

import javafx.fxml.FXML;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.RIS.model.Order;

import application.RISDbConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;


import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;

public class UserDataController {
	
	@FXML private AnchorPane paneUserData;
	@FXML private TableView<Order> tblPatientData;
	@FXML private TableColumn<Order, String> clnRecordId;
	@FXML private TableColumn<Order, String> clnReadingTime;
	@FXML private TableColumn<Order, String> clnGlucoseLevel;
	@FXML private TableColumn<Order, String> clnInsulinAdmin;
	@FXML private TableColumn<Order, String> clnStatus;
	@FXML private Button btnPatientMainMenu;
	String IDP;
	
   
 // ObservableList: A list that enables listeners to track changes when they occur
    // The following  method will return an ObservableList of  object
    /*public ObservableList<Order>  getRecordList(){
    	
    	ObservableList<Order> record = FXCollections.observableArrayList();
        String SQLQuery = "SELECT * FROM records WHERE idPatient = ? ORDER BY idRecords DESC;"; //ADD WHERE idPatient = ''
       	ResultSet rs = null;

       	try(
       			Connection conn = RISDbConfig.getConnection();
       			PreparedStatement displayprofile = conn.prepareStatement(SQLQuery);
       	){
       		displayprofile.setString(1, IDP);
       		rs = displayprofile.executeQuery();
       		// check to see if receiving any data
       		while (rs.next()){
       			record.add(new Order(rs.getString("idRecords").toString(),rs.getString("dateTime").toString(),rs.getString("glucoseReading").toString(),rs.getString("insulinAmount").toString(),
       					rs.getString("status").toString(),rs.getString("idPatient").toString(),rs.getString("idDoctor").toString()));
       		}
       	}catch(SQLException ex){
       		RISDbConfig.displayException(ex);
       		return null;
       	}finally{
       		if(rs != null){
       			//rs.close();
       		}
       	}
        return record;
    }*/
    
    // Method used to enable the detailed view button on mouse click event
	@FXML
	public void changeSceneToPatientHome(ActionEvent event) throws IOException {
		    Stage stage = (Stage)btnPatientMainMenu.getScene().getWindow();
		    stage.close();
		}
	       
	
	
	public void setID(String setID){
		IDP = setID;
		
        //set up the columns in the table
		clnRecordId.setCellValueFactory(new PropertyValueFactory<Order, String>("idRecords"));
		clnReadingTime.setCellValueFactory(new PropertyValueFactory<Order, String>("dateTime"));
		clnGlucoseLevel.setCellValueFactory(new PropertyValueFactory<Order, String>("glucoseReading"));
		clnInsulinAdmin.setCellValueFactory(new PropertyValueFactory<Order, String>("InsulinAmount"));
		clnStatus.setCellValueFactory(new PropertyValueFactory<Order, String>("Status"));	
		//tblPatientData.setItems(getRecordList());
		
		tblPatientData.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}	
	
	
	}