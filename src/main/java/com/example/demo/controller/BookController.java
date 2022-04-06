package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Book;
import com.example.demo.model.User;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import com.example.demo.service.LibraryService;
import com.example.demo.service.UserService;

@Controller
public class BookController {

	@Autowired
	private BookService bookservice;
	
	@Autowired
	private LibraryService libservice;
	
	@Autowired
	private UserService userservice;
	
	@Autowired
	private BookRepository repo;
	
    @GetMapping("/addbook/{id}")
    public String book(Model model,@PathVariable("id") int id) {
        model.addAttribute("book", new Book());
        model.addAttribute("user", new User());
        return "/admin/addbook";
        
	}
    
	@GetMapping(value="/updatebook/{id}/{updateid}")
	public String updateBook(@PathVariable("id") int id,Model model, @PathVariable("updateid") int updateid) {
		Optional<Book> userByEmail = repo.findById(updateid);
        model.addAttribute("book", userByEmail);
        model.addAttribute("bookupdate", new Book());
		//String response=libservice.updateUser(id,email,name,phno,password,isadmin);
		return "/admin/updatebook";
	}
	
    @GetMapping("/bookstaken/{id}")
    public String viewbookhistory(Model model,@PathVariable("id") int id) throws Exception  {
    	model.addAttribute("userbook",userservice.findall());
    	model.addAttribute("user", libservice.getalluser(id));
        return "/admin/bookstaken";
        
	}
	@PostMapping(value="/updatebook/{id}/{updateid}")
	public String updateregisterbook(@PathVariable("id") int id,Model model,@Validated Book bookupdate, @PathVariable("updateid") int updateid,final RedirectAttributes redirAttrs) {
		Boolean response=bookservice.updateUser(updateid,bookupdate.getBookname(),bookupdate.getBookauthor(),bookupdate.getBookcount(),bookupdate.getBookid());
		if(response) {
			redirAttrs.addFlashAttribute("success", "Book Updated Successfully");
	          return "redirect:/viewbook/{id}";
		}
		redirAttrs.addFlashAttribute("error", "User Failed to Update");
		return "redirect:/viewbook/{id}";
	}
    
	@PostMapping(value="/deletebook/{id}/{deleteid}")
	public String deleteUser(@PathVariable("id") int id,@PathVariable("deleteid") int deleteid) {
		bookservice.deletebook(deleteid);
		return "redirect:/viewbook/{id}";
	}
    
    @GetMapping("/viewbook/{id}")
    public String viewbook(Model model,@PathVariable("id") int id) {
    	model.addAttribute("book", bookservice.getallbook());
    	model.addAttribute("user", libservice.getalluser(id));
        return "/admin/viewbook";
        
	}
    
    @PostMapping("/book_register/{id}")
    public String bookRegister(@Validated Book book,@PathVariable("id") int id, BindingResult bindingResult,final RedirectAttributes redirAttrs) {
        String s= bookservice.bookRegister(book,bindingResult);
        if(s=="false") {
          redirAttrs.addFlashAttribute("error", "Book Already Exists.");
          return "redirect:/addbook/{id}";
        }
        redirAttrs.addFlashAttribute("success", "Book Saved Successfully.");
        return "redirect:/viewbook/{id}";
    }
}
