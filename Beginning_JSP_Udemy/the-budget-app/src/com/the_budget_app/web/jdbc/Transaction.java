package com.the_budget_app.web.jdbc;

public class Transaction {
	private int id;
	private int account_id;
	private int member_id;
	private double amount;
	private String name;
	private String date;
	
	public Transaction(int id, int account_id, int member_id, double amount, String name, String date) {
		this.id = id;
		this.account_id = account_id;
		this.member_id = member_id;
		this.amount = amount;
		this.name = name;
		this.date = date;
	}
	public Transaction(int account_id, int member_id, double amount, String name, String date) {
		this.account_id = account_id;
		this.member_id = member_id;
		this.amount = amount;
		this.name = name;
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAccount_id() {
		return account_id;
	}
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", account_id=" + account_id + ", member_id=" + member_id + ", amount="
				+ amount + ", name=" + name + ", date=" + date + "]";
	}	
}
