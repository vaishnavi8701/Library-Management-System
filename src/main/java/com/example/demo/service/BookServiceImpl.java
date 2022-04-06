package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.example.demo.model.Book;
import com.example.demo.model.User;
import com.example.demo.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository repo;
	
	@Override
	public List<Book> getallbook() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public String bookRegister(Book book, BindingResult bindingResult) {
		boolean exists = repo.existsByBookid((book.getBookid()));
		book.setAvailcount(book.getBookcount());
		if(!exists){
		   repo.save(book);
		   return "true";
		}
		return  "false";
	}

	@Override
	public void deletebook(int deleteid) {
		// TODO Auto-generated method stub
		repo.deleteById(deleteid);
	}

	@Override
	public Boolean updateUser(int id, String bookname, String bookauthor, int bookcount, String bookid) {
		// TODO Auto-generated method stub
		
		Optional<Book> student=repo.findById(id);
		if(student.isPresent()) {
			Book updatedbook=student.get();
			updatedbook.setBookname(bookname);
			updatedbook.setBookauthor(bookauthor);
			updatedbook.setBookcount(bookcount);
			updatedbook.setBookid(bookid);
			repo.save(updatedbook);
			return true;
		}else {
			return false;
		}
	}

}
