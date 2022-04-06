package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Userdetails {
	

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	
	private int userid;
	private int bookid;
	private String dateissued;
	private String bookuniqid;
	public Userdetails(int id, int userid, int bookid, String dateissued, String bookuniqid, String duedate,
			String bookauthor, String bookname, String datereturn, boolean status, String daterenew, boolean overdue) {
		super();
		this.id = id;
		this.userid = userid;
		this.bookid = bookid;
		this.dateissued = dateissued;
		this.bookuniqid = bookuniqid;
		this.duedate = duedate;
		this.bookauthor = bookauthor;
		this.bookname = bookname;
		this.datereturn = datereturn;
		this.status = status;
		this.daterenew = daterenew;
		this.overdue = overdue;
	}


	private String duedate;
	public String getDuedate() {
		return duedate;
	}


	public void setDuedate(String duedate) {
		this.duedate = duedate;
	}


	public Userdetails(int id, int userid, int bookid, String dateissued, String bookuniqid, String bookauthor,
			String bookname, String datereturn, boolean status) {
		super();
		this.id = id;
		this.userid = userid;
		this.bookid = bookid;
		this.dateissued = dateissued;
		this.bookuniqid = bookuniqid;
		this.bookauthor = bookauthor;
		this.bookname = bookname;
		this.datereturn = datereturn;
		this.status = status;
	}


	public String getDatereturn() {
		return datereturn;
	}


	public void setDatereturn(String datereturn) {
		this.datereturn = datereturn;
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	private String bookauthor;
	private String bookname;

	private String datereturn;
	private boolean status;

	public Userdetails(int id, int userid, int bookid, String dateissued, String bookuniqid, String bookauthor,
			String bookname, String datereturn, boolean status, String daterenew, boolean overdue) {
		super();
		this.id = id;
		this.userid = userid;
		this.bookid = bookid;
		this.dateissued = dateissued;
		this.bookuniqid = bookuniqid;
		this.bookauthor = bookauthor;
		this.bookname = bookname;
		this.datereturn = datereturn;
		this.status = status;
		this.daterenew = daterenew;
		this.overdue = overdue;
	}


	private String daterenew;
	public String getDaterenew() {
		return daterenew;
	}


	public void setDaterenew(String daterenew) {
		this.daterenew = daterenew;
	}


	private boolean overdue;



	public boolean isOverdue() {
		return overdue;
	}


	public void setOverdue(boolean overdue) {
		this.overdue = overdue;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getUserid() {
		return userid;
	}


	public void setUserid(int userid) {
		this.userid = userid;
	}


	public int getBookid() {
		return bookid;
	}


	public void setBookid(int bookid) {
		this.bookid = bookid;
	}





	public String getBookauthor() {
		return bookauthor;
	}


	public void setBookauthor(String bookauthor) {
		this.bookauthor = bookauthor;
	}


	public String getBookname() {
		return bookname;
	}


	public void setBookname(String bookname) {
		this.bookname = bookname;
	}


	public String getDateissued() {
		return dateissued;
	}


	public void setDateissued(String dateissued) {
		this.dateissued = dateissued;
	}


	public String getBookuniqid() {
		return bookuniqid;
	}


	public void setBookuniqid(String bookuniqid) {
		this.bookuniqid = bookuniqid;
	}


	public Userdetails() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "Userdetails [id=" + id + ", userid=" + userid + ", bookid=" + bookid + ", dateissued=" + dateissued
				+ ", bookuniqid=" + bookuniqid + ", duedate=" + duedate + ", bookauthor=" + bookauthor + ", bookname="
				+ bookname + ", datereturn=" + datereturn + ", status=" + status + ", daterenew=" + daterenew
				+ ", overdue=" + overdue + "]";
	}


	
	
	
	
	

}
