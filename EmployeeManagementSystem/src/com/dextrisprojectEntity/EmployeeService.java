package com.dextrisprojectEntity;

import java.util.List;

public interface EmployeeService {

	public boolean Rigister(Employeedetails employee);

	public Employeedetails Login(String email, String password);

	public Employeedetails Search(int id);

	public Employeedetails Search(String email);

	public List<Employeedetails> view();

	public boolean Logout(String email);
}
