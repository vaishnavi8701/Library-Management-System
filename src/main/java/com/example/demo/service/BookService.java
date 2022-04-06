package com.example.demo.service;

import java.util.List;

import org.springframework.validation.BindingResult;

import com.example.demo.model.Book;

public interface BookService {

	List<Book> getallbook();

	String bookRegister(Book book, BindingResult bindingResult);

	void deletebook(int deleteid);

	Boolean updateUser(int id, String bookname, String bookauthor, int bookcount, String bookid);

}
