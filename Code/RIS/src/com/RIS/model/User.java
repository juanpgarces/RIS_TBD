package com.RIS.model;
import javafx.beans.property.SimpleStringProperty;

public class User {

	private SimpleStringProperty userId, userType, firstName, lastName, phone, gender, email;
	
	/*Constructor for Doctor*/
	public User(String userId, String userType, String firstName, String lastName, String phone, String gender, String email) {
		this.userId = new SimpleStringProperty(userId);
		this.userType = new SimpleStringProperty(userType);
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.phone = new SimpleStringProperty(phone);
		this.gender = new SimpleStringProperty(gender);
		this.email = new SimpleStringProperty(email);
	}
	
	/* Start of GETTERS AND SETTERS */
	public String getUserId() {
		return userId.get();
	}

	public void setUserId(String userId) {
		this.userId = new SimpleStringProperty(userId);
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
	
	public String getPhone() {
		return phone.get();
	}

	public void setPhone(String phone) {
		this.phone = new SimpleStringProperty(phone);
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
	
	public String getUserType() {
		return userType.get();
	}

	public void setUserType(String userType) {
		this.userType = new SimpleStringProperty(userType);
	}
	/*End for GETTERS AND SETTERS*/

	@Override
	public String toString() {
		return "User [userType=" + userType + ", firstName=" + firstName + ", lastName=" + lastName + ", gender="
				+ gender + ", email=" + email + ", userType=" + userType + "]";
	}

}
