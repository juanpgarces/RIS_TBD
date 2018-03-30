package com.RIS.model;
import javafx.beans.property.SimpleStringProperty;

public class PACS {

	private SimpleStringProperty transcript, image;
	private int imageId, appointmentId;
	
	public PACS(int imageId, String image, String transcript, int appointmentId) {
		this.imageId  = imageId;
		this.image  = new SimpleStringProperty(image);
		this.transcript  = new SimpleStringProperty(transcript);
		this.appointmentId  = appointmentId;	
	}
	
	/* Start of GETTERS AND SETTERS */
	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public String getImage() {
		return image.get();
	}

	public void setIamge(String image) {
		this.image = new SimpleStringProperty(image);
	}

	public String getTranscript() {
		return transcript.get();
	}

	public void setTranscript(String transcript) {
		this.transcript = new SimpleStringProperty(transcript);
	}
	
	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	@Override
	public String toString() {
		return "PACS [transcript=" + transcript + ", image=" + image + ", imageId=" + imageId + ", appointmentId="
				+ appointmentId + "]";
	}
	
}
