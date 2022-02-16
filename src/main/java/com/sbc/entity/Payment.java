package com.sbc.entity;

public class Payment {

	private int id;
	private String name;
	private int employeeId;
	private double amount;
	
	public Payment() {
		
	}
	
	public Payment(String name, int employeeId, double amount) {
		this.name = name;
		this.employeeId = employeeId;
		this.amount = amount;
	}

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

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", name=" + name + ", employeeId=" + employeeId + ", amount=" + amount + "]";
	}
	
	
	
}
