package edu.umd.cs.daemondash.sqlite.model;

public class User {

	private Long id;
	private String username;
	
	public User() {
	}
	
	public User(String username) {
		this.username = username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
}
