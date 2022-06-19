package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	@Query(value = "SELECT * FROM Users WHERE account = :account", nativeQuery = true)
	User findByAccount(String account);


}
