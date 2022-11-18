package com.cs104.strings;

import java.util.Date;

public class UserProfile {
	private final String userID;
	private FullName userFullName;
	private Date userBirthDate;
	private Date userEnterDateTime;
	private String userPhoneNumber;
	
	
	//default constructor
	public UserProfile() {
		this.userID = NameGenerator.randomUserID();
		this.userFullName = new FullName();
		this.userBirthDate = new Date();
		this.userEnterDateTime = new Date();
	}
	
	//UserID Getter
	public String getUserID(){
		return userID;
	}

	//User name getters/setters

	public String getUserFullName(){
		return this.userFullName.getFullName();
	}
	public void setUserFullName(FullName fullName){
		this.userFullName = fullName;
	}

	public String getUserFirstName(){
		return this.userFullName.getFirstName();
	}
	public String getUserMiddleName(){
		return this.userFullName.getMiddleName();
	}
	public String getUserLastName(){
		return this.userFullName.getLastName();
	}

	public void setFullNameClass(FullName fullName){
		this.userFullName = fullName;
	}
	public void setFullName(String firstName, String middleName, String lastName){
		this.userFullName.setFirstName(firstName);
		this.userFullName.setMiddleName(middleName);
		this.userFullName.setLastName(lastName);
	}

	//User date getters/setters

	public Date getBirthDate(){
		return userBirthDate;
	}
	public void setBirthDate(Date birthDate){
		this.userBirthDate = birthDate;
	}
	public Date getEnterDateTime(){
		return userEnterDateTime;
	}

	//User phone number getter/setter

	public String getPhoneNumber(){
		return userPhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber){
		this.userPhoneNumber = phoneNumber;
	}

}
