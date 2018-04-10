package com.RIS.model;
import javafx.beans.property.SimpleStringProperty;

public class Order {

	private SimpleStringProperty emergencyLevel, userID, patientID;
	private int modalityID;
	
	public Order(String emergencyLevel, String userID, String patientID, int modalityID) {
		this.emergencyLevel  = new SimpleStringProperty(emergencyLevel);
		this.userID  = new SimpleStringProperty(userID);
		this.patientID  = new SimpleStringProperty(patientID);
		this.modalityID  = modalityID;	
	}
	
	/* Start of GETTERS AND SETTERS */

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
	
	public int getModalityId(){
		return modalityID;
	}
	
	public void setModalityId(int modalityID) {
		this.modalityID = modalityID;
	}

	@Override
	public String toString() {
		return "Order [emergencyLevel=" + emergencyLevel + ", userID=" + userID
				+ ", patientID=" + patientID + ", modalityID=" + modalityID + "]";
	}	
	
}
