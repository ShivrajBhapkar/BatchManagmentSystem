package com.gymmanagement.model;

public class Participant {
	private int id;
	private String name;
	private String email;
	private int batchId;

	// Default constructor
	public Participant() {
	}

	// Parameterized constructor
	public Participant(int id, String name, String email, int batchId) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.batchId = batchId;
	}

	// Getters and Setters
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	// toString method for easy debugging
	@Override
	public String toString() {
		return "Participant{" + "id=" + id + ", name='" + name + '\'' + ", email='" + email + '\'' + ", batchId="
				+ batchId + '}';
	}
}
