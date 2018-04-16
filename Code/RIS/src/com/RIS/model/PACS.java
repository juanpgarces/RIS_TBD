package com.RIS.model;
import javafx.beans.property.SimpleStringProperty;

public class PACS {

	private SimpleStringProperty image;
	private int imageId, appointmentId;
	
	public PACS(int imageId, int appointmentId, String image) {
		this.imageId  = imageId;
		this.appointmentId  = appointmentId;
		this.image  = new SimpleStringProperty(image);
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

	@Override
	public String toString() {
		return "PACS [image=" + image + ", imageId=" + imageId + ", appointmentId="
				+ appointmentId + "]";
	}
	
}
