package com.RIS.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.RIS.model.Appointment;
import com.RIS.model.PACS;
import com.RIS.model.Patient;
import com.RIS.model.Transcript;

import application.RISDbConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RadiologistController {

    @FXML private TableView<Appointment> tableViewApp;
    @FXML private TableView<PACS> tableViewPacs;

    @FXML private TableColumn<PACS, Integer> colImageId;
    @FXML private TableColumn<Appointment, String> colPatientId;
    @FXML private TableColumn<Appointment, String> colModality;
    @FXML private TableColumn<Appointment, String> colDate;
    @FXML private TableColumn<Appointment, Integer> colAppId;

    @FXML private Label lblDOB, lblLastName, lblFirstName, lblPatientId, lblNotesPatient, lblGender, lblNotesApp, lblStopTime, lblStartTime, lblDate, lblModality, lblTranscript;
    private String ID;
    
    @FXML
    public void initialize() {
    	tableViewApp.setItems(refreshApp());
    }
    
    @FXML
    void submitReport(ActionEvent event) {
    	
        ObservableList<Appointment> selectedRows, allApps;
        
        allApps = tableViewApp.getItems();
    	
        //this gives us the rows that were selected
        selectedRows = tableViewApp.getSelectionModel().getSelectedItems();
    	
    	Transcript newTranscript = new Transcript(lblTranscript.getText(),
						selectedRows.get(0).getAppointmentId(),
						ID,
						selectedRows.get(0).getPatientId(),
						selectedRows.get(0).getModalityId());
		
		String query = "INSERT INTO transcripts " + "(transcript, appointment_appID, appointment_userID, appointment_patientID, appointment_modalityID) " + "values(?,?,?,?,?)";
		
		try (Connection conn = RISDbConfig.getConnection();
		PreparedStatement insertTranscript = conn.prepareStatement(query);) {
		
		insertTranscript.setString(1, newTranscript.getTranscript());
		insertTranscript.setInt(2, newTranscript.getAppointmentId());
		insertTranscript.setString(3, newTranscript.getUserId());
		insertTranscript.setString(4, newTranscript.getPatientId());
		insertTranscript.setInt(5, newTranscript.getModalityId());
		
		insertTranscript.execute();
		//Delete Appointment from the current table or mark it as complete and refresh the table.
		//allApps.remove(Appointment);
		
		} catch (Exception e) {
		System.out.println("Status: operation failed due to "+e);
		
		}
    }

    @FXML
    public ObservableList<Appointment> refreshApp() {
    	
    	 //set up the columns in the table
    	colAppId.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("appointmentID"));
    	colPatientId.setCellValueFactory(new PropertyValueFactory<Appointment, String>("patientId"));
    	colDate.setCellValueFactory(new PropertyValueFactory<Appointment, String>("date"));
    	colModality.setCellValueFactory(new PropertyValueFactory<Appointment, String>("modalityId"));
    	
    	tableViewApp.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    	
    	ObservableList<Appointment> appointment = FXCollections.observableArrayList();
        String SQLQuery = "SELECT * FROM Appointment ORDER BY appointmentID;";
       	ResultSet rs = null;

       	try(
       			Connection conn = RISDbConfig.getConnection();
       			PreparedStatement displayapp = conn.prepareStatement(SQLQuery);
       	){
       		rs = displayapp.executeQuery();
       		// check to see if receiving any data
       		while (rs.next()){
       			appointment.add(new Appointment(rs.getInt("appointmentID"), rs.getString("patientID").toString(),rs.getInt("modalityID"),rs.getString("date").toString(), rs.getString("startTime").toString(), rs.getString("stopTime").toString(), rs.getString("notes").toString()));
       		}
       	}catch(SQLException ex){
       		RISDbConfig.displayException(ex);
       		return null;
       	}finally{
       		if(rs != null){
       			//rs.close();
       		}
       	}
        return appointment;
    }
    
    @FXML
    public void loadData() {
    	
        ObservableList<Appointment> selectedRows;

        //this gives us the rows that were selected
        selectedRows = tableViewApp.getSelectionModel().getSelectedItems();
        
        //Load Appointment Details
        lblModality.setText(selectedRows.get(0).getModalityId()+"");
        lblDate.setText(selectedRows.get(0).getDate());
        lblStartTime.setText(selectedRows.get(0).getStartTime()+"");
        lblStopTime.setText(selectedRows.get(0).getStopTime()+"");
        lblNotesApp.setText(selectedRows.get(0).getNotes());
        
        String SQLQuery = "SELECT * FROM Patient WHERE PatientID = ?;";
       	ResultSet rs = null;

       	try(
       			Connection conn = RISDbConfig.getConnection();
       			PreparedStatement displaypatient = conn.prepareStatement(SQLQuery);
       	){
       		displaypatient.setString(1, selectedRows.get(0).getPatientId());
       		
       		rs = displaypatient.executeQuery();
       		// check to see if receiving any data
       		Patient newPatient = new Patient(rs.getString("patientID").toString(), rs.getString("firstName").toString(),rs.getString("lastName").toString(),rs.getString("gender").toString(), rs.getString("DOB").toString(), rs.getString("insurance").toString(), rs.getString("address").toString(), rs.getString("phone").toString(), rs.getString("email").toString(), rs.getString("notes").toString());
       		
       		//Load Patient Info
            lblPatientId.setText(newPatient.getidPatient());
            lblFirstName.setText(newPatient.getFirstName());
            lblLastName.setText(newPatient.getLastName());
            lblDOB.setText(newPatient.getDOB());
            lblGender.setText(newPatient.getGender());
            lblNotesPatient.setText(newPatient.getNotes());
            
            //Load Images
       		tableViewPacs.setItems(LoadImages(newPatient.getidPatient(), selectedRows.get(0).getAppointmentId()));
       		
       	}catch(SQLException ex){
       		RISDbConfig.displayException(ex);
       	}
    	
    }

	public void setID(String text) {
		this.ID = text;	
	}
	
    public ObservableList<PACS> LoadImages(String patientId, int appointmentId) {
    	
    	colImageId.setCellValueFactory(new PropertyValueFactory<PACS, Integer>("imageId"));
    	tableViewPacs.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    	
    	ObservableList<PACS> pacs = FXCollections.observableArrayList();
        String SQLQuery = "SELECT * FROM pacs WHERE patientID = ? AND appointmentID = ? ORDER BY imageID;";
       	ResultSet rs = null;

       	try(
       			Connection conn = RISDbConfig.getConnection();
       			PreparedStatement displayapp = conn.prepareStatement(SQLQuery);
       	){
       		displayapp.setString(1, patientId);
       		displayapp.setInt(2, appointmentId);
       		
       		rs = displayapp.executeQuery();
       		// check to see if receiving any data
       		while (rs.next()){
       			pacs.add(new PACS(rs.getInt("imageID"), rs.getInt("appointment_appID"),rs.getString("appointment_patientID").toString(),rs.getString("image").toString()));
       		}
       	}catch(SQLException ex){
       		RISDbConfig.displayException(ex);
       		return null;
       	}finally{
       		if(rs != null){
       			//rs.close();
       		}
       	}
        return pacs;
    }

}
