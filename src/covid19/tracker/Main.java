package com.cs104.strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

	public static void main(String[] args) {
		//VITUG 11 - Python Programming QA

		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(input);
		LinkedList<String> visitorNameList = new LinkedList<String>();
		LinkedList<String> visitorFirstNameList = new LinkedList<String>();
		LinkedList<String> visitorLastNameList = new LinkedList<String>();
		LinkedList<Date> visitorEntryList = new LinkedList<Date>();
		LinkedList<String> visitorPhoneNumList = new LinkedList<String>();
		LinkedList<String> visitorUserID = new LinkedList<String>();
		FullName fullName = new FullName();
		UserProfile userProfile = new UserProfile();
		
		String superPass = "12345", adminPass = " ";
		String firstName = " ", middleName = " ", lastName = " ", phoneNumber = " ", sBirthDate = " ";
		Date birthDate;
		boolean loginReq = false;

		PrintLn("Welcome to Vitug's COVID-19 Contact Tracing Program");

		while (!loginReq){
			char loginOpt = ' ';

			PrintLn("----------------------------------------------------------");
			PrintLn("Choose User Type:");
			PrintLn("V - Visitor");
			PrintLn("A - Administrator");
			PrintLn("E - Exit");
			Print("Input: ");

			try {
				loginOpt = reader.readLine().charAt(0);
			} catch (IOException ex){
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
			PrintLn("");

			switch (loginOpt){
				case 'v':
				case 'V':
				PrintLn("----------------------------------------------------------");
				PrintLn("You will now be asked to fill up the following\ninformation for contact tracing.");
				PrintLn("Please fill up the following information.");
				Print("First Name: ");
				try {
					firstName = reader.readLine();
					fullName.setFirstName(firstName);
				} catch (IOException ex){
					Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
				}
				Print("Middle Name: ");
				try {
					middleName = reader.readLine();
					fullName.setMiddleName(middleName);
				} catch (IOException ex){
					Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
				}
				Print("Last Name: ");
				try {
					lastName = reader.readLine();
					fullName.setLastName(lastName);
					userProfile.setFullName(firstName, middleName, lastName);
				} catch (IOException ex){
					Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
				}
				boolean birthDateValid = false;
				while (!birthDateValid){
					Print("Birth Date (MM/DD/YYYY): ");
					try {
						sBirthDate = reader.readLine();
						birthDate = new SimpleDateFormat("MM/dd/yyyy").parse(sBirthDate);
						userProfile.setBirthDate(birthDate);
						birthDateValid = true;
						break;
					} catch (IOException ex){
						birthDateValid = false;
						Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
						continue;
					} catch (ParseException ex) {
						birthDateValid = false;
						PrintLn("Invalid Date. Please try again.");
						continue;
					}
				}
				boolean phoneNumValid = false;
				while (!phoneNumValid){
					Print("Phone Number (11-Digit Phone Number): ");
					try {
						phoneNumber = reader.readLine();
						if (phoneNumber.matches("[0-9]+") && phoneNumber.length() == 11){
							userProfile.setPhoneNumber(phoneNumber);
							phoneNumValid = true;
							break;
						} else {
							PrintLn("Invalid number. Please try again.");
							phoneNumValid = false;
							continue;
						}
					} catch (IOException ex){
						Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
				PrintLn("");
				PrintLn("Information: ");
				PrintLn("Time and Date of entry: " + userProfile.getEnterDateTime());
				PrintLn("Full Name: " + userProfile.getUserFirstName() + " " + userProfile.getUserMiddleName().charAt(0) + ". " + userProfile.getUserLastName());

				visitorNameList.add(userProfile.getUserFullName());
				visitorFirstNameList.add(userProfile.getUserFirstName());
				visitorLastNameList.add(userProfile.getUserLastName());
				visitorEntryList.add(userProfile.getEnterDateTime());
				visitorPhoneNumList.add(userProfile.getPhoneNumber());
				visitorUserID.add(NameGenerator.randomUserID());

				loginReq = false;
				continue;
				case 'a':
				case 'A':
				PrintLn("----------------------------------------------------------");
				PrintLn("You are now in the ADMIN Log in screen.");
				PrintLn("Please enter Admin password to gain access.");
				Print("Password: ");
				try {
					adminPass = reader.readLine();
				} catch (IOException ex){
					Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
				}

				if (adminPass.equals(superPass)){
					PrintLn("Successfully logged in !");
					boolean adminReq = false;
		
					while (!adminReq){
						char adminOpt = ' ';
						String pageOpt = " ";
						int i, results, page;
			
						PrintLn("----------------------------------------------------------");
						PrintLn("Welcome to the Admin screen");
						PrintLn("Choose Category:");
						PrintLn("D - Date and Time users entered");
						PrintLn("F - First Name");
						PrintLn("L - Last Name");
						PrintLn("P - Phone Number");
						PrintLn("S - Log Out");
						PrintLn("E - Exit");
						Print("Input: ");
			
						try {
							adminOpt = reader.readLine().charAt(0);
						} catch (IOException ex){
							Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
						}
						PrintLn("");
			
						switch (adminOpt){
							case 'd':
							case 'D':
							pageOpt = " ";
							i = 0;
							results = 10;
							page = 1;
							while(!pageOpt.equals("end")){
								PrintLn("You are on page " + page);
								while (i < visitorEntryList.size() && i < results){
									System.out.println(i + 1 + ". " + visitorEntryList.get(i));
									if (visitorEntryList.get(i) == null){
										break;
									} else {
										i++;
										continue;
									}
								}
								PrintLn("Type 'next' to go to next page, 'prev' to go to the previous page, and 'end' to stop searching.");
								PrintLn("Otherwise, type 'search' to choose the element you want the information for.");
								Print("Input: ");
								try {
									pageOpt = reader.readLine();
								} catch (IOException ex){
									Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
								}
								PrintLn("");

								if (pageOpt.equals("next")){
									i = i + 10;
									results = results + 10;
									page++;
									continue;
								} else if (pageOpt.equals("prev")){
									i = i - 11;
									results = results - 11;
									page--;
									continue;
								} else if(pageOpt.equals("search")){
									i = 0;
									int index = 0;
									boolean searchReq = false;
									while (!searchReq){
										PrintLn("Enter the number of the element you want to inspect.");
										Print("Input: ");
										try {
											index = Integer.parseInt(reader.readLine());
											index = index - 1;
										} catch (NumberFormatException ex){
											PrintLn("Input was invalid. Please try again.");
										} catch (IOException ex){
											Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
										}
										PrintLn("");
										
										if (index < visitorEntryList.size()){
											visitorEntryList.get(index);
										} else {
											PrintLn("Element is invalid or does not exist. Please try again");
											searchReq = true;
											break;
										}
			
										if (visitorEntryList.get(index) == null){
											PrintLn("Element is invalid or does not exist. Please try again.");
											searchReq = false;
											continue;
										} else {
											System.out.println("Time and Date of Entry: " + visitorEntryList.get(index));
											System.out.println("User ID: " + visitorUserID.get(index));
											System.out.println("Phone Number: " + visitorPhoneNumList.get(index));
											System.out.println("");
											searchReq = true;
											break;
										}
									}
								} else if (pageOpt.equals("end")){
									i = 0;
									adminReq = false;
									break;
								} else {
									i = 0;
									PrintLn("Invalid input. Please try again");
									continue;
								}
							}
							break;
							case 'f':
							case 'F':
							pageOpt = " ";
							i = 0;
							results = 10;
							page = 1;
							while(!pageOpt.equals("end")){
								PrintLn("You are on page " + page);
								while (i < visitorFirstNameList.size() && i < results){
									System.out.println(i + 1 + ". " + visitorFirstNameList.get(i));
									if (visitorFirstNameList.get(i) == null){
										break;
									} else {
										i++;
										continue;
									}
								}
								PrintLn("Type 'next' to go to next page, 'prev' to go to the previous page, and 'end' to stop searching.");
								PrintLn("Otherwise, type 'search' to choose the element you want the information for.");
								Print("Input: ");
								try {
									pageOpt = reader.readLine();
								} catch (IOException ex){
									Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
								}
								PrintLn("");

								if (pageOpt.equals("next")){
									i = i + 10;
									results = results + 10;
									page++;
									continue;
								} else if (pageOpt.equals("prev")){
									i = i - 11;
									results = results - 11;
									page--;
									continue;
								} else if(pageOpt.equals("search")){
									i = 0;
									int index = 0;
									boolean searchReq = false;
									while (!searchReq){
										PrintLn("Enter the number of the element you want to inspect.");
										Print("Input: ");
										try {
											index = Integer.parseInt(reader.readLine());
											index = index - 1;
										} catch (NumberFormatException ex){
											PrintLn("Input was invalid. Please try again.");
										} catch (IOException ex){
											Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
										}
										PrintLn("");
			
										if (index < visitorFirstNameList.size()){
											visitorFirstNameList.get(index);
										} else {
											PrintLn("Element is invalid or does not exist. Please try again");
											searchReq = true;
											break;
										}
			
										if (visitorFirstNameList.get(index) == null){
											PrintLn("Element is invalid or does not exist. Please try again.");
											searchReq = false;
											continue;
										} else {
											System.out.println("Time and Date of Entry: " + visitorEntryList.get(index));
											System.out.println("User ID: " + visitorUserID.get(index));
											System.out.println("Phone Number: " + visitorPhoneNumList.get(index));
											System.out.println("");
											searchReq = true;
											break;
										}
									}
								} else if (pageOpt.equals("end")){
									i = 0;
									adminReq = false;
									break;
								} else {
									i = 0;
									PrintLn("Invalid input. Please try again");
									continue;
								}
							}
							break;
							case 'l':
							case 'L':
							pageOpt = " ";
							i = 0;
							results = 10;
							page = 1;
							while(!pageOpt.equals("end")){
								PrintLn("You are on page " + page);
								while (i < visitorLastNameList.size() && i < results){
									System.out.println(i + 1 + ". " + visitorLastNameList.get(i));
									if (visitorLastNameList.get(i) == null){
										break;
									} else {
										i++;
										continue;
									}
								}
								PrintLn("Type 'next' to go to next page, 'prev' to go to the previous page, and 'end' to stop searching.");
								PrintLn("Otherwise, type 'search' to choose the element you want the information for.");
								Print("Input: ");
								try {
									pageOpt = reader.readLine();
								} catch (IOException ex){
									Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
								}
								PrintLn("");

								if (pageOpt.equals("next")){
									i = i + 10;
									results = results + 10;
									page++;
									continue;
								} else if (pageOpt.equals("prev")){
									i = i - 11;
									results = results - 11;
									page--;
									continue;
								} else if(pageOpt.equals("search")){
									i = 0;
									int index = 0;
									boolean searchReq = false;
									while (!searchReq){
										PrintLn("Enter the number of the element you want to inspect.");
										Print("Input: ");
										try {
											index = Integer.parseInt(reader.readLine());
											index = index - 1;
										} catch (NumberFormatException ex){
											PrintLn("Input was invalid. Please try again.");
										} catch (IOException ex){
											Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
										}
										PrintLn("");
			
										if (index < visitorLastNameList.size()){
											visitorLastNameList.get(index);
										} else {
											PrintLn("Element is invalid or does not exist. Please try again");
											searchReq = true;
											break;
										}
			
										if (visitorLastNameList.get(index) == null){
											PrintLn("Element is invalid or does not exist. Please try again.");
											searchReq = false;
											continue;
										} else {
											System.out.println("Time and Date of Entry: " + visitorEntryList.get(index));
											System.out.println("User ID: " + visitorUserID.get(index));
											System.out.println("Phone Number: " + visitorPhoneNumList.get(index));
											System.out.println("");
											searchReq = true;
											break;
										}
									}
								} else if (pageOpt.equals("end")){
									i = 0;
									adminReq = false;
									break;
								} else {
									i = 0;
									PrintLn("Invalid input. Please try again");
									continue;
								}
							}
							break;
							case 'p':
							case 'P':
							pageOpt = " ";
							i = 0;
							results = 10;
							page = 1;
							while(!pageOpt.equals("end")){
								PrintLn("You are on page " + page);
								while (i < visitorPhoneNumList.size() && i < results){
									System.out.println(i + 1 + ". " + visitorPhoneNumList.get(i));
									if (visitorPhoneNumList.get(i) == null){
										break;
									} else {
										i++;
										continue;
									}
								}
								PrintLn("Type 'next' to go to next page, 'prev' to go to the previous page, and 'end' to stop searching.");
								PrintLn("Otherwise, type 'search' to choose the element you want the information for.");
								Print("Input: ");
								try {
									pageOpt = reader.readLine();
								} catch (IOException ex){
									Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
								}
								PrintLn("");

								if (pageOpt.equals("next")){
									i = i + 10;
									results = results + 10;
									page++;
									continue;
								} else if (pageOpt.equals("prev")){
									i = i - 11;
									results = results - 11;
									page--;
									continue;
								} else if(pageOpt.equals("search")){
									i = 0;
									int index = 0;
									boolean searchReq = false;
									while (!searchReq){
										PrintLn("Enter the number of the element you want to inspect.");
										Print("Input: ");
										try {
											index = Integer.parseInt(reader.readLine());
											index = index - 1;
										} catch (NumberFormatException ex){
											PrintLn("Input was invalid. Please try again.");
										} catch (IOException ex){
											Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
										}
										PrintLn("");
			
										if (index < visitorPhoneNumList.size()){
											visitorPhoneNumList.get(index);
										} else {
											PrintLn("Element is invalid or does not exist. Please try again");
											searchReq = true;
											break;
										}
			
										if (visitorPhoneNumList.get(index) == null){
											PrintLn("Element is invalid or does not exist. Please try again.");
											searchReq = false;
											continue;
										} else {
											System.out.println("Time and Date of Entry: " + visitorEntryList.get(index));
											System.out.println("User ID: " + visitorUserID.get(index));
											System.out.println("Phone Number: " + visitorPhoneNumList.get(index));
											System.out.println("");
											searchReq = true;
											break;
										}
									}
								} else if (pageOpt.equals("end")){
									i = 0;
									adminReq = false;
									break;
								} else {
									i = 0;
									PrintLn("Invalid input. Please try again");
									continue;
								}
							}
							break;
							case 's':
							case 'S':
							loginReq = false;
							adminReq = true;
							break;
							case 'e':
							case 'E':
							PrintLn("Exiting Program.");
							System.exit(0);
							default:
							PrintLn("Invalid input, please try again.");
							adminReq = false;
							continue;
						}
					}
					loginReq = false;
					break;
				} else {
					PrintLn("Invalid password. Please try again");
					loginReq = false;
					continue;
				}
				case 'e':
				case 'E':
				PrintLn("Exiting Program.");
				loginReq = true;
				System.exit(0);
				default:
				PrintLn("Invalid input, please try again.");
				loginReq = false;
				continue;
			}
		}
	}

	public static void PrintLn(String message){
		System.out.println(message);
	}
	public static void Print(String message){
		System.out.print(message);
	}

}