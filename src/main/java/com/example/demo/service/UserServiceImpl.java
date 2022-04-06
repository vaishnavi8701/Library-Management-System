package com.example.demo.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Book;
import com.example.demo.model.User;
import com.example.demo.model.Userdetails;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.LibraryRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {


	@Autowired
	private LibraryRepository librepo;
	
	@Autowired
	private BookRepository bookrepo;
		

	@Autowired
	private UserRepository userrepo;


	@Override
	public List<Userdetails> finduserid(int id) throws Exception {
		// TODO Auto-generated method stub
		List<Userdetails>users= userrepo.findAllByUserid(id);
		List<Userdetails> present =  new ArrayList<Userdetails>();
		for (int i = 0; i < users.size(); i++) 
		{
			Userdetails user=users.get(i);
			if(user.isStatus()) {
				
				//Date issue
			    Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(user.getDateissued()); 
			    SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
				user.setDateissued(DATE_FORMAT.format(date1));
				
				//Date return
			    Date date2=new SimpleDateFormat("yyyy-MM-dd").parse(user.getDaterenew()); 
			    SimpleDateFormat DATE_FORMAT1 = new SimpleDateFormat("dd-MM-yyyy");
				user.setDaterenew(DATE_FORMAT1.format(date2)); 
				
				//Due date
			    Date date3=new SimpleDateFormat("yyyy-MM-dd").parse(user.getDuedate()); 
			    SimpleDateFormat DATE_FORMAT3 = new SimpleDateFormat("dd-MM-yyyy");
				user.setDuedate(DATE_FORMAT3.format(date3)); 
				
				DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

				Date today = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			    Date firstDate = sdf.parse(formatter.format(date2));
			    Date secondDate = sdf.parse(formatter.format(today));

			    long diffInMillies = (secondDate.getTime() - firstDate.getTime());
			    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
			    if(diff<=15) {
			    	user.setOverdue(false);
			    }
			    else {
			    	user.setOverdue(true);
			    }
				
				present.add(user);

			}
				}
		return present;
	}

	@Override
	public List<Userdetails> findbookid(int id) throws Exception {
		// TODO Auto-generated method stub
		List<Userdetails>users= userrepo.findAllByUserid(id);
		List<Userdetails> present =  new ArrayList<Userdetails>();
		for (int i = 0; i < users.size(); i++) 
		{
			Userdetails user=users.get(i);
			if(!user.isStatus()) {
				
				//Date issue
			    Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(user.getDateissued()); 
			    SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
				user.setDateissued(DATE_FORMAT.format(date1));
				
				//Date return
			    Date date2=new SimpleDateFormat("yyyy-MM-dd").parse(user.getDatereturn()); 
			    SimpleDateFormat DATE_FORMAT1 = new SimpleDateFormat("dd-MM-yyyy");
				user.setDatereturn(DATE_FORMAT1.format(date2));
				
				present.add(user);

			}
				}
		return present;
	}

	

	@Override
	public void renewuser(int id) throws Exception {
		// TODO Auto-generated method stub
		Optional<Userdetails> users= userrepo.findById(id);
		Userdetails user=users.get();
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		Date today = new Date();

		Date todays = formatter.parse(formatter.format(today));
		 SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
		user.setDaterenew(DATE_FORMAT.format(todays));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Using today's date
		c.add(Calendar.DATE, 15); // Adding 15 days
		user.setDuedate( sdf.format(c.getTime()));
		userrepo.save(user);
		
		
	}

	@Override
	public void borrowuser(int id, int borrowid) throws Exception {
		// TODO Auto-generated method stub
		Optional<Book>books=bookrepo.findById(borrowid);
		Book book=books.get();
		Userdetails user=new Userdetails();
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		Date today = new Date();

		Date todays = formatter.parse(formatter.format(today));
		 SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
		user.setDaterenew(DATE_FORMAT.format(todays));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Using today's date
		c.add(Calendar.DATE, 15); // Adding 15 days
		user.setDuedate( sdf.format(c.getTime()));
		user.setBookauthor(book.getBookauthor());
		user.setDateissued(DATE_FORMAT.format(todays));
		user.setBookid(borrowid);
		user.setBookname(book.getBookname());
		user.setBookuniqid(book.getBookid());
		user.setDatereturn(null);
		user.setOverdue(false);
		user.setStatus(true);
		user.setUserid(id);
		userrepo.save(user);
		book.setAvailcount(book.getAvailcount()-1);
		//System.out.println(book.getAvailcount());
		bookrepo.save(book);
		
		
	}

	@Override
	public void returnuser(int id, int returnid) throws Exception {
		// TODO Auto-generated method stub
		Optional<Userdetails>users=userrepo.findById(returnid);
		Userdetails user=users.get();
		user.setStatus(false);
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		Date today = new Date();

		Date todays = formatter.parse(formatter.format(today));
		 SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
		user.setDatereturn(DATE_FORMAT.format(todays));
		userrepo.save(user);
		Optional<Book>books=bookrepo.findById(user.getBookid());
		Book book=books.get();
		book.setAvailcount(book.getAvailcount()+1);
		//System.out.println(book.getAvailcount());
		bookrepo.save(book);
		
	}

	@Override
	public List<Userdetails> findall() throws Exception {
		// TODO Auto-generated method stub
		List<Userdetails>users= userrepo.findAll();
		List<Userdetails> present =  new ArrayList<Userdetails>();
		for (int i = 0; i < users.size(); i++) 
		{
			Userdetails user=users.get(i);
			if(user.isStatus()) {
				
				//Date issue
			    Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(user.getDateissued()); 
			    SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
				user.setDateissued(DATE_FORMAT.format(date1));
				
				
				present.add(user);

			}
				}
		System.out.println(present);
		return present;
	}

	@Override
	public List<User> finduser(int bookid) throws Exception {
		// TODO Auto-generated method stub
		
		List<Userdetails>users= userrepo.findAllByBookid(bookid);
		List<User> present =  new ArrayList<User>();
		for (int i = 0; i < users.size(); i++) 
		{
			Userdetails user=users.get(i);
			if(user.isStatus()) {
				
				//Date issue
				Optional<User> member=librepo.findById(user.getUserid());
				
				present.add(member.get());

			}
				}
		return present;
	}

	
}
