package com.dextrisprojectEntity;

public class Employeedetails {

	@Override
	public String toString() {
		return "Employeedetails [id=" + id + ", name=" + name + ", dob=" + dob + ", email=" + email + ", password="
				+ password + ", logintime=" + logintime + ", logouttime=" + logouttime + ", status=" + status + "]";
	}

	private int id;
	private String name;
	private String dob;
	private String email;
	private String password;
	private String logintime;
	private String logouttime;
	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogintime() {
		return logintime;
	}

	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}

	public String getLogouttime() {
		return logouttime;
	}

	public void setLogouttime(String logouttime) {
		this.logouttime = logouttime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Employeedetails(int id, String name, String dob, String email, String password, String logintime,
			String logouttime, String status) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.email = email;
		this.password = password;
		this.logintime = logintime;
		this.logouttime = logouttime;
		this.status = status;
	}

	public Employeedetails() {
		super();
	}
}
