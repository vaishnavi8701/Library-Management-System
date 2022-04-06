package com.example.demo.controller;


import java.util.List;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.User;
import com.example.demo.repository.LibraryRepository;
import com.example.demo.service.LibraryService;

@Controller
public class LibraryController {
	
	@Autowired
	private LibraryService libservice;
	

	@Autowired
	private LibraryRepository repo;
	
//	@PostMapping(value = "/create/user")
//	public ResponseEntity<String> createUser(@RequestBody User user) throws Exception {
//		if (user.getEmail() == null) {
//			return new ResponseEntity<String>("Missing emailId value", HttpStatus.BAD_REQUEST);
//		}
//		libservice.createUser(user);
//		return new ResponseEntity<String>("Employee created successfully", HttpStatus.CREATED);
//	}

	@GetMapping(value = {"/", "/index"})
	public String Home(Model model){
		model.addAttribute("user", new User());
		return "index";
	}
	
//	@PostMapping(value="/login")
//	public ModelAndView checkUser(@RequestBody User user) throws Exception {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("admin.html");
//        return modelAndView;
//		
//	}
//	
    @PostMapping("/login")
    public String validateLoginInfo(Model model,@Validated User user, BindingResult bindingResult,final RedirectAttributes redirAttrs) {
        if (bindingResult.hasErrors()) {
            return "/";
        }
        String email = user.getEmail();
        String password = user.getPassword();
        User userByEmail = repo.findByEmail(email);
        boolean status;

        if(userByEmail != null) {
            if(password.equals(userByEmail.getPassword())) {
                status = true;
            }
            else {
                status = false;
            }
        }
        else {
            status = false;
        }

        int id=userByEmail.getUserid();
        if(!status) {
          redirAttrs.addFlashAttribute("error", "Invalid Login.");
          return "redirect:/";
        }
        else if(status && userByEmail.getIsadmin()) {
            redirAttrs.addFlashAttribute("success", "Login Successful.");
            return "redirect:/Adminhome/"+id;
        }
        redirAttrs.addFlashAttribute("success", "Login Successful.");
        return "redirect:/Userhome/"+id;
    }
    
    @GetMapping("/adduser/{id}")
    public String showRegistrationForm(Model model,@PathVariable("id") int id) {
        model.addAttribute("user", new User());
        return "/admin/adduser";
        
	}
    
    @GetMapping("/viewprofile/{id}")
    public String viewprofile(Model model,@PathVariable("id") int id) {
    	Optional<User> user1=repo.findById(id);
		User existsuser=user1.get();
    	model.addAttribute("user", existsuser);
        return "/admin/viewprofile";
        
	}
    
    @GetMapping("/viewuser/{id}")
    public String viewuser(Model model,@PathVariable("id") int id) {
    	model.addAttribute("user", libservice.getalluser(id));
        return "/admin/viewuser";
        
	}

    
    @GetMapping("/Adminhome/{id}")
    public String basic(Model model,@PathVariable("id") int id) {
    	Optional<User> userByEmail = repo.findById(id);
        model.addAttribute("user", userByEmail);
        
    	return "/admin/Adminhome";
    }
    @GetMapping("/Userhome/{id}")
    public String basichome(Model model,@PathVariable("id") int id) {
    	Optional<User> userByEmail = repo.findById(id);
        model.addAttribute("user", userByEmail);
        
    	return "/User/Userhome";
    }

    @PostMapping("/process_register/{id}")
    public String processRegister(@Validated User user,@PathVariable("id") int id, BindingResult bindingResult,final RedirectAttributes redirAttrs) {
        String s= libservice.processRegister(user,bindingResult);
        if(s=="false") {
          redirAttrs.addFlashAttribute("error", "User Already Exists.");
          return "redirect:/adduser/{id}";
        }
        redirAttrs.addFlashAttribute("success", "User Saved Successfully.");
        return "redirect:/viewuser/{id}";
    }
	
//	@GetMapping(value="/get_alluser")
//	public ResponseEntity<List<User>> getUser() {
//		return new ResponseEntity<List<User>>(libservice.getalluser(),HttpStatus.OK);
//	}	
	
	@GetMapping(value="/updateuser/{id}/{updateid}")
	public String updateUser(@PathVariable("id") int id,Model model, @PathVariable("updateid") int updateid) {
		Optional<User> userByEmail = repo.findById(updateid);
        model.addAttribute("user", userByEmail);
        model.addAttribute("userupdate", new User());
		//String response=libservice.updateUser(id,email,name,phno,password,isadmin);
		return "/admin/updateuser";
	}
	
	@GetMapping(value="/updateprofile/{id}/{updateid}")
	public String updateprofile(@PathVariable("id") int id,Model model, @PathVariable("updateid") int updateid) {
		Optional<User> userByEmail = repo.findById(updateid);
        model.addAttribute("user", userByEmail);
        model.addAttribute("userupdate", new User());
		//String response=libservice.updateUser(id,email,name,phno,password,isadmin);
		return "/admin/updateprofile";
	}
	
	@PostMapping(value="/update_register/{id}/{updateid}")
	public String updateregister(@PathVariable("id") int id,Model model,@Validated User userupdate, @PathVariable("updateid") int updateid,final RedirectAttributes redirAttrs) {
        System.out.println(userupdate);
        Boolean response;
      
        if(id==updateid) {
        	  Optional<User> users=repo.findById(updateid); 
        	  User use=users.get();
        	  if(use.getIsadmin()) {
        		  response=libservice.updateUser(updateid,userupdate.getEmail(),userupdate.getName(),userupdate.getPhno(),userupdate.getPassword(),true);
        	  }
        	  response=libservice.updateUser(updateid,userupdate.getEmail(),userupdate.getName(),userupdate.getPhno(),userupdate.getPassword(),true);
        }
        	  else {
		response=libservice.updateUser(updateid,userupdate.getEmail(),userupdate.getName(),userupdate.getPhno(),userupdate.getPassword(),false);
        	  }
		if(response) {
			redirAttrs.addFlashAttribute("success", "User Updated Successfully");
			if(id==updateid) {
				return "redirect:/viewprofile/{id}";
			}
	          return "redirect:/viewuser/{id}";
		}
		redirAttrs.addFlashAttribute("error", "User Failed to Update");
		if(id==updateid) {
			return "redirect:/viewprofile/{id}";
		}
		return "redirect:/viewuser/{id}";
	}

	@PostMapping(value="/deleteuser/{id}/{deleteid}")
	public String deleteUser(@PathVariable("id") int id,@PathVariable("deleteid") int deleteid) {
		libservice.deleteUser(deleteid);
		return "redirect:/viewuser/{id}";
	}
}
