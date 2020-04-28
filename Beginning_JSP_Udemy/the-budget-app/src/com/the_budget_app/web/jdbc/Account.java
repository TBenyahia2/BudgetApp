package com.the_budget_app.web.jdbc;

public class Account {

	private int id;
	private String account_name;
	private double total_amount;
	private String email;
	private String username;
	private String name;
	private int pin;
	
	public Account(int id, String account_name, double total_amount, String email, String username, String name,
			int pin) {
		super();
		this.id = id;
		this.account_name = account_name;
		this.total_amount = total_amount;
		this.email = email;
		this.username = username;
		this.name = name;
		this.pin = pin;
	}

	public Account(String account_name, double total_amount, String email, String username, String name, int pin) {
		super();
		this.account_name = account_name;
		this.total_amount = total_amount;
		this.email = email;
		this.username = username;
		this.name = name;
		this.pin = pin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public double getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", account_name=" + account_name + ", total_amount=" + total_amount + ", email="
				+ email + ", username=" + username + ", name=" + name + ", pin=" + pin + "]";
	}
	
	
}
