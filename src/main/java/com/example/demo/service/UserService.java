package com.example.demo.service;

import java.text.ParseException;
import java.util.List;

import com.example.demo.model.User;
import com.example.demo.model.Userdetails;

public interface UserService {
	List<Userdetails> finduserid(int id) throws Exception;

	List<Userdetails> findbookid(int id) throws Exception;

	void renewuser(int renewid) throws Exception;

	void borrowuser(int id,int borrowid) throws Exception;

	void returnuser(int id, int returnid) throws Exception;

    List<Userdetails> findall() throws Exception;

	List<User> finduser(int bookid) throws Exception;

}
