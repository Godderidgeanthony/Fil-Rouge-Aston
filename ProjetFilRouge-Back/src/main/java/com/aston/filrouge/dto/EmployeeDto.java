package com.aston.filrouge.dto;


public class EmployeeDto {
	private String firstName;
	private String lastName;
	private String emailId;
	
	public EmployeeDto(String firstName, String lastName, String emailId) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
	}

	public EmployeeDto() {
		super();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "EmployeeDto [firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId + "]";
	}
	
	
}
