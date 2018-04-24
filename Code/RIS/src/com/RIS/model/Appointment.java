package com.RIS.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.RISDbConfig;
import javafx.beans.property.SimpleStringProperty;

public class Appointment {

	private SimpleStringProperty userId, patientId, notes, date, status;
	private int modalityId, appointmentId, startTime, stopTime;

	//status = 'new' when initially created
	//status = 'pending' when waiting on a radiologist's transcript
	//status = 'complete' when radiologist has added transcript.

	public Appointment(String userId, String patientId, int modalityId, String date,int startTime, int stopTime, String notes) {
		
		this.userId = new SimpleStringProperty(userId);
		this.patientId = new SimpleStringProperty(patientId);
		this.modalityId  = modalityId;
		this.date  = new SimpleStringProperty(date);
		this.startTime  = startTime;
		this.stopTime  = stopTime;
		this.notes = new SimpleStringProperty(notes);
		this.status = new SimpleStringProperty("new") ;
	}

	/* Retrieve Information */
	public Appointment(String patientId, int modalityId, String date,int startTime, int stopTime, String notes) {

		
		this.patientId = new SimpleStringProperty(patientId);
		this.modalityId  = modalityId;
		this.date  = new SimpleStringProperty(date);
		this.startTime  = startTime;
		this.stopTime  = stopTime;
		this.notes = new SimpleStringProperty(notes);
		this.status = new SimpleStringProperty("new");
	}
	
	public Appointment(int appointmentId, String patientId, int modalityId, String date,int startTime, int stopTime, String notes) {
		
		this.appointmentId = appointmentId;
		this.patientId = new SimpleStringProperty(patientId);
		this.modalityId  = modalityId;
		this.date  = new SimpleStringProperty(date);
		this.startTime  = startTime;
		this.stopTime  = stopTime;
		this.notes = new SimpleStringProperty(notes);
		this.status = new SimpleStringProperty("new");
	}
	
	/* Start of GETTERS AND SETTERS */
	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}
	
	public String getUserId() {
		return userId.get();
	}

	public void setUserId(String userId) {
		this.userId = new SimpleStringProperty(userId);
	}

	public String getPatientId() {
		return patientId.get();
	}

	public void setPatientId(String patientId) {
		this.patientId = new SimpleStringProperty(patientId);
	}
	
	public int getModalityId() {
		return modalityId;
	}

	public void setModalityId(int modality) {
		this.modalityId = modality;
	}
	
	public void setDate(String date) {
		this.date = new SimpleStringProperty(date);
	}
	
	public String getDate() {
		return date.get();
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getStopTime() {
		return stopTime;
	}

	public void setStopTime(int stopTime) {
		this.stopTime = stopTime;
	}
	
	public String getNotes() {
		return notes.get();
	}

	public void setNotes(String notes) {
		this.notes = new SimpleStringProperty(notes);
	}
	public String getStatus(){
		return status.get();
	}
	public void setStatus(String status){
		this.status = new SimpleStringProperty(status);
	}
	public String getStartTimeToString(){
		int hour, min;
		String time="";

		hour = startTime/100;
		min = (startTime - (hour * 100))*60/100;
		if(min==0)
			time =  hour + ":00";
		else
			time = hour + ":" + min;
		return time;	
	}
	public String getStopTimeToString(){
		int hour, min;
		String time="";

		hour = stopTime/100;
		min = (stopTime - (hour * 100))*(60/100);
		if(min==0)
			time =  hour + ":00";
		else
			time = hour + ":" + min;
		return time;	
	}
	public String getModName(){
		String modalityName="";
		
		String SQLQuery = "SELECT name FROM modality WHERE modID = '" + modalityId + "';";
       	ResultSet rs = null;
       	try(
       			Connection conn = RISDbConfig.getConnection();
       			PreparedStatement  st = conn.prepareStatement(SQLQuery);
       	){
     
       		rs = st.executeQuery();
       		//always verify if there is a result
       		if(rs.next())
       			modalityName = rs.getString("name");
       		rs.close();
       		
       			
       	}catch(SQLException ex){
       		RISDbConfig.displayException(ex);
       		return null;
       	}	
		return modalityName;
	}
	
	public String getFullName(){
		String fullName="";
		
		String SQLQuery = "SELECT firstName, lastName FROM user WHERE userID = '" + userId + "';";
       	ResultSet rs = null;
       	try(
       			Connection conn = RISDbConfig.getConnection();
       			PreparedStatement  st = conn.prepareStatement(SQLQuery);
       	){
     
       		rs = st.executeQuery();
       		//always verify if there is a result
       		if(rs.next())
       			fullName = rs.getString("firstName") + " " + rs.getString("lastName");
       		rs.close();
	
       	}catch(SQLException ex){
       		RISDbConfig.displayException(ex);
       		return null;
       	}
		return fullName;
	}
	
}
