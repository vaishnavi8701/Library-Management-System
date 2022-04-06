package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.example.demo.model.User;
import com.example.demo.repository.LibraryRepository;


@Service
public class LibraryServiceImpl implements LibraryService {

	@Autowired
	private LibraryRepository repo;
	@Override
	public void createUser(User user) throws Exception {
		if (!user.getEmail().contains("@")) {
			throw new Exception("EmailId is invalid");
		}
		// core business logic
		repo.save(user);
	}
	@SuppressWarnings("unlikely-arg-type")
	@Override
	public List<User> getalluser(int id) {
		// TODO Auto-generated method stub
		Optional<User> user1=repo.findById(id);
		if(user1.isPresent()) {
			User existsuser=user1.get();
			List<User> differences = new ArrayList<>(repo.findAll());
			differences.remove(existsuser);

			return differences;
		}
		return repo.findAll();
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}
	
	

	@Override
	public Boolean updateUser(int id, String email, String name,String phno,String password,boolean isadmin) {
		// TODO Auto-generated method stub
		Optional<User> student=repo.findById(id);
		if(student.isPresent()) {
			User updatedstu=student.get();
			updatedstu.setEmail(email);
			updatedstu.setName(name);
			updatedstu.setPhno(phno);
			updatedstu.setPassword(password);
			updatedstu.setIsadmin(isadmin);
			repo.save(updatedstu);
			return true;
		}else {
			return false;
		}
		
	}
	@Override
	public String checkUser(User user) throws Exception {
		// TODO Auto-generated method stub
		if (!user.getEmail().contains("@")) {
			throw new Exception("EmailId is invalid");
		}
		return "User found";
		
	}
 

     public boolean exist(String email){
        return repo.existsByEmail(email);
     }
	@Override
	public String processRegister(User user, BindingResult bindingResult) {
		boolean exists = repo.existsByEmail(user.getEmail());
		// TODO Auto-generated method stub
		if(!exists){
		   repo.save(user);
		   return "true";
		}
		return  "false";
	}



	

}
