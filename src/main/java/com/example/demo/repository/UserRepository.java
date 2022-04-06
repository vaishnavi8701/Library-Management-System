package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Userdetails;

@Repository
public interface UserRepository extends JpaRepository<Userdetails, Integer> {

	List<Userdetails> findAllByUserid(int id);

	List<Userdetails> findAllByBookid(int bookid);
    
}
