package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.User;
import com.example.demo.model.Userdetails;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.LibraryRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.BookService;
import com.example.demo.service.LibraryService;
import com.example.demo.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private LibraryService libservice;
	

	@Autowired
	private LibraryRepository librepo;
	
	@Autowired
	private BookService bookservice;
	

	@Autowired
	private BookRepository bookrepo;
	
	@Autowired
	private UserService userservice;	

	@Autowired
	private UserRepository userrepo;
	
    @GetMapping("/viewprofileuser/{id}")
    public String viewprofileuser(Model model,@PathVariable("id") int id) {
    	Optional<User> user1=librepo.findById(id);
		User existsuser=user1.get();
    	model.addAttribute("user", existsuser);
        return "/User/viewprofile";
        
	}
    
	@PostMapping(value="/update_registeruser/{id}/{updateid}")
	public String updateregisteruser(@PathVariable("id") int id,Model model,@Validated User userupdate, @PathVariable("updateid") int updateid,final RedirectAttributes redirAttrs) {
        System.out.println(userupdate);
		Boolean response=libservice.updateUser(updateid,userupdate.getEmail(),userupdate.getName(),userupdate.getPhno(),userupdate.getPassword(),userupdate.getIsadmin());
		if(response) {
			redirAttrs.addFlashAttribute("success", "User Updated Successfully");
			
				return "redirect:/viewprofileuser/{id}";
		
		}
		redirAttrs.addFlashAttribute("error", "User Failed to Update");
		
			return "redirect:/viewprofileuser/{id}";
		
	}
	
    @GetMapping("/viewbookuser/{id}")
    public String viewbookuser(Model model,@PathVariable("id") int id) {
    	model.addAttribute("book", bookservice.getallbook());
    	model.addAttribute("user", libservice.getalluser(id));
        return "/User/viewbook";
        
	}
    @GetMapping(value="/updateprofileuser/{id}/{updateid}")
	public String updateprofile(@PathVariable("id") int id,Model model, @PathVariable("updateid") int updateid) {
		Optional<User> userByEmail = librepo.findById(updateid);
        model.addAttribute("user", userByEmail);
        model.addAttribute("userupdate", new User());
		//String response=libservice.updateUser(id,email,name,phno,password,isadmin);
		return "/User/updateprofile";
	}
    
    @GetMapping("/viewbookhistory/{id}")
    public String viewbookhistory(Model model,@PathVariable("id") int id) throws Exception  {
    	model.addAttribute("userbook",userservice.findbookid(id));
    	model.addAttribute("user", libservice.getalluser(id));
        return "/User/bookhistory";
        
	}
    
    @GetMapping("/bookpage/{id}/{bookid}")
    public String bookpage(Model model,@PathVariable("id") int id,@PathVariable("bookid") int bookid) throws Exception  {
    	model.addAttribute("user",userservice.finduser(bookid));
    	//model.addAttribute("user", libservice.getalluser(id));
        return "/admin/bookpage";
        
	}
    
    @GetMapping("/userpage/{id}/{userid}")
    public String userpage(Model model,@PathVariable("id") int id,@PathVariable("userid") int userid) throws Exception  {
    	model.addAttribute("userbook",userservice.finduserid(userid));
    	model.addAttribute("userhistory",userservice.findbookid(userid));
    	model.addAttribute("user", libservice.getalluser(userid));
    	System.out.println(model);
        return "/admin/userpage";
        
	}
    @PostMapping(value="/renewbook/{id}/{renewid}")
	public String renewbook(@PathVariable("id") int id,@PathVariable("renewid") int renewid) throws Exception {
		userservice.renewuser(renewid);
		return "redirect:/viewbookissue/{id}";
	}
    
    @GetMapping("/viewbookissue/{id}")
    public String viewbookissue(Model model,@PathVariable("id") int id) throws Exception {
    	model.addAttribute("userbook",userservice.finduserid(id));
    	//model.addAttribute("book", bookservice.getallbook());
    	model.addAttribute("user", libservice.getalluser(id));
        return "/User/bookissue";
        
	}
    
    @PostMapping(value="/borrowbook/{id}/{borrowid}")
	public String borrowbook(@PathVariable("id") int id,Model model,@PathVariable("borrowid") int borrowid) throws Exception {
		userservice.borrowuser(id,borrowid);
		
		return "redirect:/viewbookissue/{id}";
	}
    
    @PostMapping(value="/returnbook/{id}/{returnid}")
   	public String returnbook(@PathVariable("id") int id,@PathVariable("returnid") int returnid) throws Exception {
   		userservice.returnuser(id,returnid);
   		return "redirect:/viewbookissue/{id}";
   	}

}
