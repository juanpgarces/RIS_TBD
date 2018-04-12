package com.RIS.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.RIS.model.Appointment;

import application.RISDbConfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class TechnicianViewController {

    @FXML private TableView<Appointment> techTable;
    @FXML private TableColumn<Appointment, Integer> colPatientID;
    @FXML private TableColumn<Appointment, String> colFirstName, colLastName, colModality, colPhys, colNotes;

    @FXML
    void initialize() { 
    	
    	String modality = "";
    	String physicianName = "";
    	
    	String query = "SELECT name FROM modality WHERE";
    	ResultSet rs=null;
    	try (Connection conn = RISDbConfig.getConnection();
    			PreparedStatement st = conn.prepareStatement(query);) {
    		modality = rs.getString("name");
    		
    	    st.close();
    	    
            while(rs.next()) {
            	//comboModality.getItems().add(rs.getString("name"));
            }
    		} catch (Exception e) {
    			System.out.println("Status: operation failed due to "+e);
    			}
    	
    	
    }



    @FXML
    private TextField techNotesTextArea;

    @FXML
    private Text datetimeText;

    @FXML
    private Text procedureText;

    @FXML
    private Text reasonText;

    @FXML
    private Text referringText;

    @FXML
    private Text priorityText;

    @FXML
    void browse(ActionEvent event) {

    }

    @FXML
    void displayAppointments(ActionEvent event) {

    }

    @FXML
    void submit(ActionEvent event) {

    }

}
