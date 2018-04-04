package com.RIS.model;
import javafx.beans.property.SimpleStringProperty;

public class Appointment {

	private SimpleStringProperty userId, patientId, startTime, stopTime;
	private int appId, modalityId;
	
	public Appointment(int appId, String userId, String patientId, int modalityId, String startTime, String stopTime) {
		this.appId  = appId;
		this.userId = new SimpleStringProperty(userId);
		this.patientId = new SimpleStringProperty(patientId);
		this.modalityId  = modalityId;
		this.startTime  = new SimpleStringProperty(startTime);
		this.stopTime  = new SimpleStringProperty(stopTime);	
	}
	
	/* Start of GETTERS AND SETTERS */
	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
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
	
}
