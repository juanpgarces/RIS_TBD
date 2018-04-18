package com.RIS.model;

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
		if (startTime >= 1200){
			min = (startTime - 1200)*(3/5);
			hour= ((startTime - min)/100)-12;	
		}
		else{
			hour = startTime/100;
			min = (startTime-(hour*100))*(3/5);
		}
		time = hour + ":" + min;
		return time;	
	}
	public String getStopTimeToString(){
		int hour, min;
		String time="";
		if (stopTime >= 1200){
			min = (stopTime - 1200)*(3/5);
			hour= ((stopTime - min)/100)-12;	
		}
		else{
			hour = stopTime/100;
			min = (stopTime-(hour*100))*(3/5);
		}
		time = hour + ":" + min;
		return time;	
	}
}
