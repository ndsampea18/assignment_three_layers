package com.vti.entity;

import com.vti.backend.utils.DateUtils;

import java.time.LocalDate;

public class Users {
	private int userID;
	private String username;
	private String address;
	private String created_by;
	private LocalDate created_date;
	private String password;

	private String email;
	private String phone;

	public Users() {

	}

	public Users(int userID, String userName, String address, String created_by, LocalDate created_date,
			String password) {
		this.userID = userID;
		this.username = userName;
		this.address = address;
		this.created_by = created_by;
		this.created_date = created_date;
		this.password = password;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public LocalDate getCreated_date() {
		return created_date;
	}

	public void setCreated_date(LocalDate created_date) {
		this.created_date = created_date;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Users{" +
				"userID=" + userID +
				", username='" + username + '\'' +
				", address='" + address + '\'' +
				", created_by='" + created_by + '\'' +
				", created_date=" + DateUtils.convertTime(created_date) +
				", password='" + password + '\'' +
				'}';
	}
}
