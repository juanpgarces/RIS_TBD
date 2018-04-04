package com.RIS.model;
import javafx.beans.property.SimpleStringProperty;

public class PACS {

	private SimpleStringProperty transcript, image, notes;
	private int imageId, appointmentId;
	
	public PACS(int imageId, int appointmentId, String image, String transcript, String notes) {
		this.imageId  = imageId;
		this.appointmentId  = appointmentId;
		this.image  = new SimpleStringProperty(image);
		this.transcript  = new SimpleStringProperty(transcript);
		this.notes = new SimpleStringProperty(notes);
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

	public String getNotes() {
		return notes.get();
	}

	public void setNotes(String notes) {
		this.notes = new SimpleStringProperty(notes);
	}

	@Override
	public String toString() {
		return "PACS [transcript=" + transcript + ", image=" + image + ", imageId=" + imageId + ", appointmentId="
				+ appointmentId + "]";
	}
	
}
