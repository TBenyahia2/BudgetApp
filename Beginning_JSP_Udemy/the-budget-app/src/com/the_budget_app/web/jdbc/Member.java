package com.the_budget_app.web.jdbc;

public class Member {
	
	private int id;
	private String name;
	private String userName;
	private String email;
	private int account_id;
	private int pin;
	
	public Member(String name, String userName, String email, int account_id, int pin) {
		this.name = name;
		this.userName = userName;
		this.email = email;
		this.account_id = account_id;
		this.pin = pin;
	}
	
	public Member(int id, String name, String userName, String email, int account_id, int pin) {
		this.id = id;
		this.name = name;
		this.userName = userName;
		this.email = email;
		this.account_id = account_id;
		this.pin = pin;
	}
	
	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", userName=" + userName + ", email=" + email + ", account_id="
				+ account_id + "]";
	}
}
