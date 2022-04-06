package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class User {
    
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int userid;
	
	private String name;
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String email, String password, Boolean isadmin) {
		super();
		this.email = email;
		this.password = password;
		this.isadmin = isadmin;
	}

	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public User(int userid, String name, String email) {
		super();
		this.userid = userid;
		this.name = name;
		this.email = email;
	}

	public User(int userid, String name, String email, String phno, String password, Boolean isadmin) {
		super();
		this.userid = userid;
		this.name = name;
		this.email = email;
		this.phno = phno;
		this.password = password;
		this.isadmin = isadmin;
	}
	@Column(unique=true)
	private String email;
	
	private String phno;
	private String password;
	
	@Column(nullable=true)
	private Boolean isadmin;
	
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
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

	public String getPhno() {
		return phno;
	}

	public void setPhno(String phno) {
		this.phno = phno;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsadmin() {
		return isadmin;
	}

	public void setIsadmin(Boolean isadmin) {
		this.isadmin = isadmin;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", name=" + name + ", email=" + email + ", phno=" + phno + ", password="
				+ password + ", isadmin=" + isadmin + "]";
	}
	


	
}
