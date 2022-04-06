package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table
public class Book {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	@Column(name = "book_name")
	private String bookname;
	
	@Column(name = "book_author")
	private String bookauthor;

	@Column(name = "book_id",unique=true)
	private String bookid;
	
	@NotNull
	@Column(name = "book_count")
	private int bookcount=1;
 
	public Book(int id, String bookname, String bookauthor, String bookid, int bookcount, int availcount) {
		super();
		this.id = id;
		this.bookname = bookname;
		this.bookauthor = bookauthor;
		this.bookid = bookid;
		this.bookcount = bookcount;
		this.availcount = availcount;
	}

	public int getAvailcount() {
		return availcount;
	}

	public void setAvailcount(int availcount) {
		this.availcount = availcount;
	}

	private int availcount=bookcount;
	
	public Book(int id, String bookname, String bookauthor, String bookid, int bookcount) {
		super();
		this.id = id;
		this.bookname = bookname;
		this.bookauthor = bookauthor;
		this.bookid = bookid;
		this.bookcount = bookcount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getBookauthor() {
		return bookauthor;
	}

	public void setBookauthor(String bookauthor) {
		this.bookauthor = bookauthor;
	}

	public String getBookid() {
		return bookid;
	}

	public void setBookid(String bookid) {
		this.bookid = bookid;
	}

	public int getBookcount() {
		return bookcount;
	}

	public void setBookcount(int bookcount) {
		this.bookcount = bookcount;
	}

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", bookname=" + bookname + ", bookauthor=" + bookauthor + ", bookid=" + bookid
				+ ", bookcount=" + bookcount + ", availcount=" + availcount + "]";
	}
	
	
}
