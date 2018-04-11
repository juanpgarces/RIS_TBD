package com.RIS.model;
import java.util.Date;

import javafx.beans.property.SimpleStringProperty;

public class Appointment {

	private SimpleStringProperty userId, patientId, startTime, stopTime, modality, notes;
	private int appId;

	
	public Appointment(String userId, String patientId, String modality, String startTime, String stopTime, String notes) {
		
		this.userId = new SimpleStringProperty(userId);
		this.patientId = new SimpleStringProperty(patientId);
		this.modality  = new SimpleStringProperty(modality);
		this.startTime  = new SimpleStringProperty(startTime);
		this.stopTime  = new SimpleStringProperty(stopTime);
		this.notes = new SimpleStringProperty(notes);
	}
	
	/* Start of GETTERS AND SETTERS */
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

	public void setModalityId(int modalityId) {
		this.modalityId = modalityId;
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
