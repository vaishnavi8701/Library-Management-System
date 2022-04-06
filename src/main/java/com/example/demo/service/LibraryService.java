package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.User;

public interface LibraryService {

	void createUser(User user) throws Exception;
	
	String checkUser(User user) throws Exception;
	List<User> getalluser(int id);
	void deleteUser(int id);
	String processRegister(@Validated User user, BindingResult bindingResult);

	Boolean updateUser(int id, String email, String name, String phno, String password, boolean isadmin);


	


}
