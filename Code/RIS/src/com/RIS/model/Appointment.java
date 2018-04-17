package com.RIS.model;

import javafx.beans.property.SimpleStringProperty;

public class Appointment {

	private SimpleStringProperty userId, patientId, notes, date, startTime, stopTime;
	private int modalityId, appointmentId;

<<<<<<< HEAD
	
	public Appointment(String userId, String patientId, int modalityId, String date,String startTime, String stopTime, String notes) {
=======
	/* Insert Information */
	public Appointment(String userId, String patientId, int modalityId, String date,int startTime, int stopTime, String notes) {
>>>>>>> bd312e524fb42db282a643de99c466b4d3056426
		
		this.userId = new SimpleStringProperty(userId);
		this.patientId = new SimpleStringProperty(patientId);
		this.modalityId  = modalityId;
		this.date  = new SimpleStringProperty(date);
		this.startTime  = new SimpleStringProperty(startTime);
		this.stopTime  = new SimpleStringProperty(stopTime);
		this.notes = new SimpleStringProperty(notes);
	}
	
<<<<<<< HEAD
	public Appointment(String patientId, int modalityId, String date,String startTime, String stopTime, String notes) {
=======
	/* Retrieve Information */
	public Appointment(String patientId, int modalityId, String date,int startTime, int stopTime, String notes) {
>>>>>>> bd312e524fb42db282a643de99c466b4d3056426
		
		this.patientId = new SimpleStringProperty(patientId);
		this.modalityId  = modalityId;
		this.date  = new SimpleStringProperty(date);
		this.startTime  = new SimpleStringProperty(startTime);
		this.stopTime  = new SimpleStringProperty(stopTime);
		this.notes = new SimpleStringProperty(notes);
	}
	
	public Appointment(int appointmentId, String patientId, int modalityId, String date,String startTime, String stopTime, String notes) {
		
		this.appointmentId = appointmentId;
		this.patientId = new SimpleStringProperty(patientId);
		this.modalityId  = modalityId;
		this.date  = new SimpleStringProperty(date);
		this.startTime  = new SimpleStringProperty(startTime);
		this.stopTime  = new SimpleStringProperty(stopTime);
		this.notes = new SimpleStringProperty(notes);
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

	public String getStartTime() {
		return startTime.get();
	}

	public void setStartTime(String startTime) {
		this.startTime = new SimpleStringProperty(startTime);
	}

	public String getStopTime() {
		return stopTime.get();
	}

	public void setStopTime(String stopTime) {
		this.stopTime = new SimpleStringProperty(stopTime);
	}
	
	public String getNotes() {
		return notes.get();
	}

	public void setNotes(String notes) {
		this.notes = new SimpleStringProperty(notes);
	}
}
