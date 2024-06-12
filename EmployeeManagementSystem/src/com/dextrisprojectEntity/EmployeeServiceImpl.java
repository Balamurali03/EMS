package com.dextrisprojectEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

	@Override
	public boolean Rigister(Employeedetails employee) {
		String query = "INSERT INTO employees (name, dob, email, password) VALUES (?, ?, ?, ?)";
		boolean value = false;
		try (Connection connection = DatabaseConnection.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(1, employee.getName());
			statement.setString(2, dateConversion(employee.getDob()));
			statement.setString(3, employee.getEmail());
			statement.setString(4, employee.getPassword());

			int reg = statement.executeUpdate();
			if (reg == 1) {
				value = true;
			}

		} catch (SQLException e) {

			System.out.println("SQLException: " + e.getMessage());
		}
		return value;
	}

	@Override
	public Employeedetails Login(String email, String password) {
		String query = "SELECT * FROM employees WHERE email = ? AND password = ?";
		String updateStatus = "UPDATE employees SET status = 'ACTIVE',logintime = ? WHERE email = ?";
		Employeedetails employee = null;

		try (Connection connection = DatabaseConnection.getConnection()) {

			PreparedStatement statement = connection.prepareStatement(query);
			PreparedStatement statement1 = connection.prepareStatement(updateStatus);

			statement.setString(1, email);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet != null) {
				LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				String logintime = now.format(formatter);

				statement1.setString(1, logintime);
				statement1.setString(2, email);
				int UpdateResultSet = statement1.executeUpdate();

				if (UpdateResultSet == 1) {
					EmployeeService service = new EmployeeServiceImpl();
					employee = service.Search(email);
				}
			}
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}

		return employee;
	}

	@Override
	public Employeedetails Search(int id) {
		String query = "SELECT * FROM employees WHERE id = ? AND status = 'ACTIVE'";
		Employeedetails employee = null;

		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {

			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				String name = resultSet.getString("name");
				String dob = resultSet.getString("dob");
				String email = resultSet.getString("email");
				String password = resultSet.getString("password");
				String logintime = resultSet.getString("logintime");
				String logouttime = resultSet.getString("logouttime");
				String status = resultSet.getString("status");

				employee = new Employeedetails(id, name, dob, email, password, logintime, logouttime, status);
			}
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}

		return employee;
	}

	@Override
	public Employeedetails Search(String email) {
		String query = "SELECT * FROM employees WHERE email = ? AND status = 'ACTIVE'";
		Employeedetails employee = null;

		try (Connection connection = DatabaseConnection.getConnection()){
				PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				System.out.println(resultSet.getString("status"));
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String dob = resultSet.getString("dob");
				// String email = resultSet.getString("email");
				String password = resultSet.getString("password");
				String logintime = resultSet.getString("logintime");
				String logouttime = resultSet.getString("logouttime");
				String status = resultSet.getString("status");

				employee = new Employeedetails(id, name, dob, email, password, logintime, logouttime, status);
			}

		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}

		return employee;
	}

	@Override
	public List<Employeedetails> view() {

		String query = "SELECT * FROM employees";
		List<Employeedetails> allData = new ArrayList<Employeedetails>();

		try (Connection connection = DatabaseConnection.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Employeedetails emp = new Employeedetails();
				emp.setId(resultSet.getInt("id"));
				emp.setDob(resultSet.getString("dob"));
				emp.setEmail(resultSet.getString("email"));
				emp.setLogintime(resultSet.getString("logintime"));
				emp.setLogouttime(resultSet.getString("logouttime"));
				emp.setName(resultSet.getString("name"));
				emp.setPassword(null);
				emp.setStatus(resultSet.getString("status"));
				allData.add(emp);
			}

		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}
		return allData;
	}

	@Override
	public boolean Logout(String email) {
		String query = "SELECT * FROM employees WHERE email = ? AND status = 'ACTIVE'";
		String updateStatus = "UPDATE employees SET status = 'INACTIVE',logouttime = ? WHERE email = ?";
		boolean value = false;

		try (Connection connection = DatabaseConnection.getConnection()) {

			PreparedStatement statement = connection.prepareStatement(query);
			PreparedStatement statement1 = connection.prepareStatement(updateStatus);

			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				String logouttime = now.format(formatter);

				statement1.setString(1, logouttime);
				statement1.setString(2, email);
				int UpdateResultSet = statement1.executeUpdate();

				if (UpdateResultSet == 1) {
					value = true;
				}
			}
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}
		return value;
	}

	public static String dateConversion(String date) {

		String outputDate = null;
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
		try {
			LocalDate localDate = LocalDate.parse(date, inputFormatter);
			outputDate = localDate.format(outputFormatter);
		} catch (DateTimeParseException e) {
			System.out.println("Invalid date format: " + e.getMessage());
		}
		return outputDate;
	}

}
