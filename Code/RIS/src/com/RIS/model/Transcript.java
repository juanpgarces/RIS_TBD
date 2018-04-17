package com.RIS.model;
import javafx.beans.property.SimpleStringProperty;

public class Transcript {

	private SimpleStringProperty transcript, patientId, userId;
	private int transcriptId, appointmentId, modalityId;
	
	/*Insert Infortmation*/
	public Transcript(String transcript, int appointmentId, String patientId, String userId, int modalityId) {
		this.transcript  = new SimpleStringProperty(transcript);
		this.appointmentId  = appointmentId;
		this.patientId  = new SimpleStringProperty(patientId);
		this.userId = new SimpleStringProperty(userId);
		this.modalityId = modalityId;
	}
	
	/* Retrive Information*/
	public Transcript(int transcriptId, String transcript, int appointmentId, String patientId, String userId, int modalityId) {
		this.transcriptId  = transcriptId;
		this.transcript  = new SimpleStringProperty(transcript);
		this.appointmentId  = appointmentId;
		this.patientId  = new SimpleStringProperty(patientId);
		this.userId = new SimpleStringProperty(userId);
		this.modalityId = modalityId;
	}
	
	/* Start of GETTERS AND SETTERS */
	public String getUserId() {
		return userId.get();
	}

	public void setUserId(String userId) {
		this.userId = new SimpleStringProperty(userId);
	}

	public int getModalityId() {
		return modalityId;
	}

	public void setModalityId(int modalityId) {
		this.modalityId = modalityId;
	}
	
	public String getTranscript() {
		return transcript.get();
	}

	public void setTranscript(String transcript) {
		this.transcript = new SimpleStringProperty(transcript);
	}

	public String getPatientId() {
		return patientId.get();
	}

	public void setPatientId(String patientId) {
		this.patientId = new SimpleStringProperty(patientId);
	}

	public int getTranscriptId() {
		return transcriptId;
	}

	public void setTranscriptId(int transcriptId) {
		this.transcriptId = transcriptId;
	}

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	@Override
	public String toString() {
		return "Transcript [transcript=" + transcript + ", patientId=" + patientId + ", transcriptId=" + transcriptId
				+ ", appointmentId=" + appointmentId + "]";
	}

	
}
