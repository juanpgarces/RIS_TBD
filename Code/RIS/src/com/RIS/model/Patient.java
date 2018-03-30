package com.RIS.model;
import javafx.beans.property.SimpleStringProperty;

public class Patient {

	private SimpleStringProperty idPatient, firstName, lastName, gender,  DOB, insurance, address, phone , email, notes;
	
	public Patient(String idPatient, String firstName, String lastName, String gender, String DOB, String insurance, String address, String phone, String email, String notes) {
		this.idPatient  = new SimpleStringProperty(idPatient);
		this.firstName  = new SimpleStringProperty(firstName);
		this.lastName  = new SimpleStringProperty(lastName);
		this.gender  = new SimpleStringProperty(gender);
		this.DOB  = new SimpleStringProperty(DOB);
		this.insurance  = new SimpleStringProperty(insurance);
		this.address  = new SimpleStringProperty(address);
		this.phone  = new SimpleStringProperty(phone);
		this.email  = new SimpleStringProperty(email);
		this.notes  = new SimpleStringProperty(notes);
		
	}
	
	public Patient(String idPatient) {
		this.idPatient = new SimpleStringProperty(idPatient);
	}
	
	/* Start of GETTERS AND SETTERS */
	public String getidPatient() {
		return idPatient.get();
	}

	public void setidPatient(String idPatient) {
		this.idPatient = new SimpleStringProperty(idPatient);
	}

	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(String firstName) {
		this.firstName = new SimpleStringProperty(firstName);
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String lastName) {
		this.lastName = new SimpleStringProperty(lastName);
	}
	
	public String getGender() {
		return gender.get();
	}

	public void setGender(String gender) {
		this.gender = new SimpleStringProperty(gender);
	}
	
	public String getDOB() {
		return DOB.get();
	}

	public void setDOB(String DOB) {
		this.DOB = new SimpleStringProperty(DOB);
	}

	public String getInsurance() {
		return insurance.get();
	}

	public void setnsurance(String insurance) {
		this.insurance = new SimpleStringProperty(insurance);
	}

	public String getAddress() {
		return address.get();
	}

	public void setAddress(String address) {
		this.address = new SimpleStringProperty(address);
	}

	public String getPhone() {
		return phone.get();
	}

	public void setPhone(String phone) {
		this.phone = new SimpleStringProperty(phone);
	}

	public String getEmail() {
		return email.get();
	}

	public void setEmail(String email) {
		this.email = new SimpleStringProperty(email);
	}

	public String getNotes() {
		return notes.get();
	}
	
	public void setNotes(String notes) {
		this.notes = new SimpleStringProperty(notes);
	}
	
	@Override
	public String toString() {
		return "Patient [idPatient=" + idPatient + ", firstName=" + firstName + ", lastName=" + lastName + ", gender="
				+ gender + ", DOB=" + DOB + ", insurance=" + insurance + ", address=" + address + ", phone=" + phone
				+ ", email=" + email + ", notes=" + notes + "]";
	}


	
	
}
