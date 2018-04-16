package com.RIS.model;
import javafx.beans.property.SimpleStringProperty;

public class PACS {

	private SimpleStringProperty image, patientId;
	private int imageId, appointmentId, userId, modalityId;
	
	public PACS(int imageId, int appointmentId, String patientId, String image, int userId, int modalityId) {
		this.imageId  = imageId;
		this.appointmentId  = appointmentId;
		this.patientId = new SimpleStringProperty(patientId);
		this.image  = new SimpleStringProperty(image);
		this.userId = userId;
		this.modalityId = modalityId;
	}
	
	public PACS(int imageId, int appointmentId, String patientId, String image) {
		this.imageId  = imageId;
		this.appointmentId  = appointmentId;
		this.patientId = new SimpleStringProperty(patientId);
		this.image  = new SimpleStringProperty(image);
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getModalityId() {
		return modalityId;
	}

	public void setModalityId(int modalityId) {
		this.modalityId = modalityId;
	}

	/* Start of GETTERS AND SETTERS */
	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
		
	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}
	
	public String getPatientId() {
		return patientId.toString();
	}

	public void setPatientId(String patientId) {
		this.patientId = new SimpleStringProperty(patientId);
	}
	
	public String getImage() {
		return image.get();
	}

	public void setIamge(String image) {
		this.image = new SimpleStringProperty(image);
	}

	@Override
	public String toString() {
		return "PACS [image=" + image + ", imageId=" + imageId + ", appointmentId="
				+ appointmentId + "]";
	}
	
}
