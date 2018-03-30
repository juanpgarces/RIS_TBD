package com.RIS.model;
import javafx.beans.property.SimpleStringProperty;

public class User {

	private SimpleStringProperty userType, firstName, lastName, gender, email;
	
	/*Constructor for Doctor*/
	public User(String userType, String firstName, String lastName, String gender, String email) {
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.gender = new SimpleStringProperty(gender);
		this.email = new SimpleStringProperty(email);
	}
	
	/* Start of GETTERS AND SETTERS */
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

	public String getEmail() {
		return email.get();
	}

	public void setEmail(String email) {
		this.email = new SimpleStringProperty(email);
	}
	/*End for GETTERS AND SETTERS*/

	@Override
	public String toString() {
		return "User [userType=" + userType + ", firstName=" + firstName + ", lastName=" + lastName + ", gender="
				+ gender + ", email=" + email + "]";
	}

}
