package com.cs104.strings;

public class FullName {
	
	private String firstName;
	private String middleName;
	private String lastName;
	
	public FullName() {
		this.firstName = "Dummy FirstName";
		this.middleName = "Dummy MiddleName";
		this.lastName = "Dummy LastName";
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
	
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getFullName(){
		return firstName + " " + middleName + " " + lastName;
	}
}