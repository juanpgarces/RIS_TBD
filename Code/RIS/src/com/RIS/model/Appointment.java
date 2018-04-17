package com.RIS.model;

import javafx.beans.property.SimpleStringProperty;

public class Appointment {

	private SimpleStringProperty userId, patientId, notes, date;
	private int modalityId, startTime, stopTime, appointmentId;

	/* Insert Information */
	public Appointment(String userId, String patientId, int modalityId, String date,int startTime, int stopTime, String notes) {
		
		this.userId = new SimpleStringProperty(userId);
		this.patientId = new SimpleStringProperty(patientId);
		this.modalityId  = modalityId;
		this.date  = new SimpleStringProperty(date);
		this.startTime  = startTime;
		this.stopTime  = stopTime;
		this.notes = new SimpleStringProperty(notes);
	}
	
	/* Retrieve Information */
	public Appointment(String patientId, int modalityId, String date,int startTime, int stopTime, String notes) {
		
		this.patientId = new SimpleStringProperty(patientId);
		this.modalityId  = modalityId;
		this.date  = new SimpleStringProperty(date);
		this.startTime  = startTime;
		this.stopTime  = stopTime;
		this.notes = new SimpleStringProperty(notes);
	}
	
	public Appointment(int appointmentId, String patientId, int modalityId, String date,int startTime, int stopTime, String notes) {
		
		this.appointmentId = appointmentId;
		this.patientId = new SimpleStringProperty(patientId);
		this.modalityId  = modalityId;
		this.date  = new SimpleStringProperty(date);
		this.startTime  = startTime;
		this.stopTime  = stopTime;
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
}
