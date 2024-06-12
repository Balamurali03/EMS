package com.dextrisprojectEntity;

import java.util.List;
import java.util.Scanner;

public class EmployeeMain {

	static Scanner sc;
	public static boolean startstop(String choice) {
		if(choice.equalsIgnoreCase("start")) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {

		EmployeeService service = new EmployeeServiceImpl();
		sc = new Scanner(System.in);
		System.out.println("Hello user Type Start to start the application");
		boolean value = startstop(sc.next());

		while (value == true) {
			System.out.println("Hello user trigger the function from the list \n 1. Register \n 2. Login "
					+ "\n 3. GetDataById \n 4. GetDataByEmail \n 5. GetAllData \n 6. Logout \n 7. Stop");
			String trigger = sc.next().toLowerCase();
			switch (trigger) {
			case "register":
				Employeedetails employeedetails = new Employeedetails();
				System.out.println("Enter details");
				System.out.println("Enter name");
				employeedetails.setName(sc.next());
				System.out.println("Enter password");
				employeedetails.setPassword(sc.next());
				System.out.println("Enter email");
				employeedetails.setEmail(sc.next());
				System.out.println("Enter DOB in format of (dd/mm/yyyy)");
				employeedetails.setDob(sc.next());
				employeedetails.setStatus("INACTIVE");
				boolean val = service.Rigister(employeedetails);
				if (val) {
					System.out.println("User registered sucessful");
				} else {
					System.out.println("User not registered sucessful");
				}
				break;

			case "login":
				System.out.println("Enter credentials");
				System.out.println("Enter email");
				String email = sc.next();
				System.out.println("Enter password");
				String pass = sc.next();
				Employeedetails userData = service.Login(email, pass);
				if (userData != null) {
					System.out.println("The user details are :" + userData.toString());
				} else {
					System.out.println("No user found");
				}
				break;

			case "getdatabyid":
				System.out.println("Enter id");

				int id = sc.nextInt();
				Employeedetails user = service.Search(id);
				if (user != null) {
					System.out.println("The user details are :" + user.toString());
				} else {
					System.out.println("No user found OR need to Login");
				}
				break;

			case "getdatabyemail":

				System.out.println("Enter mailId");

				String mailId = sc.next();
				Employeedetails data = service.Search(mailId);
				if (data != null) {
					System.out.println("The user details are :" + data.toString());
				} else {
					System.out.println("No user found with this mailId OR need to Login");
				}
				break;

			case "getalldata":
				System.out.println("Here the list of data");
				List<Employeedetails> list = service.view();
				if (list.size() > 0) {
					System.out.println("The user data are :" + list);
				} else {
					System.out.println("No data found OR need to Login");
				}
				break;

			case "logout":
				System.out.println("provide the mailId to trigger the logout sequence");
				String logout = sc.next();
				boolean v = service.Logout(logout);
				if (v) {
					System.out.println("You have loggedout sucessfully");
				} else {
					System.out.println("You haven't loggedout sucessfully OR need to Login");
				}
				break;
				
			case "stop": 
				System.out.println("Thanks for using the app .");
				value=startstop("stop");
				break;

			default: {
				System.out.println("Write something from the list without spelling mistake ");
			}

			}
		}
		main(args);

	}

}
