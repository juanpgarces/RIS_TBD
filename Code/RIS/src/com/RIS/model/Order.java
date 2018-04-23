package com.RIS.model;
import javafx.beans.property.SimpleStringProperty;

public class Order {

	private SimpleStringProperty emergencyLevel, userID, patientID, notes, modality;
	private String status;
	private int orderId;
	
	public Order(int orderId, String emergencyLevel, String userID, String patientID, String modality, String notes) {
		this.orderId = orderId;
		this.emergencyLevel  = new SimpleStringProperty(emergencyLevel);
		this.userID  = new SimpleStringProperty(userID);
		this.patientID  = new SimpleStringProperty(patientID);
		this.modality  = new SimpleStringProperty(modality);
		this.notes = new SimpleStringProperty(notes);
		this.status = "new";
	}	


	public Order(String emergencyLevel, String userID, String patientID, String modality, String notes) {
		this.emergencyLevel  = new SimpleStringProperty(emergencyLevel);
		this.userID  = new SimpleStringProperty(userID);
		this.patientID  = new SimpleStringProperty(patientID);
		this.modality  = new SimpleStringProperty(modality);
		this.notes = new SimpleStringProperty(notes);
		this.status = "new";
	}
	
	/* Start of GETTERS AND SETTERS */
	
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getEmergencyLevel() {
		return emergencyLevel.get();
	}

	public void setEmergencyLevel(String emergencyLevel) {
		this.emergencyLevel = new SimpleStringProperty(emergencyLevel);
	}

	public String getUserId() {
		return userID.get();
	}

	public void setUserId(String userID) {
		this.userID = new SimpleStringProperty(userID);
	}
	
	public String getPatientId() {
		return patientID.get();
	}

	public void setPatientId(String patientID) {
		this.patientID = new SimpleStringProperty(patientID);
	}
	
	public String getModality(){
		return modality.get();
	}
	
	public void setModality(String modality) {
		this.modality = new SimpleStringProperty(modality);
	}
	
	public String getNotes() {
		return notes.get();
	}

	public void setNotes(String notes) {
		this.notes = new SimpleStringProperty(notes);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Order [emergencyLevel=" + emergencyLevel + ", userID=" + userID
				+ ", patientID=" + patientID + ", modalityID=" + modality + ", notes=" + notes + "]";
	}	
	
}
