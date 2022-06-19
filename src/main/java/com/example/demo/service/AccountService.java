package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class AccountService {

	@Autowired
	UserRepository userRepository;
	//ログイン処理
	public User findAccount(String account) {
		return userRepository.findByAccount(account);
	}

	//パスワード変更処理
	public void save(User loginUser) {
		userRepository.save(loginUser);
	}

	public User userSelect(Integer id) {
		User user = userRepository.findById(id).orElse(null);
		return user;
	}
}
