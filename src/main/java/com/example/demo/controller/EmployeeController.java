package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.User;
import com.example.demo.service.AccountService;
import com.example.demo.service.ResultService;
import com.example.demo.service.TopService;


@Controller
public class EmployeeController {
	@Autowired
	AccountService accountService;
	@Autowired
	ResultService resultService;
	@Autowired
	TopService topService;

	@Autowired
	HttpSession session;

	@GetMapping("/employee/{id}")
	public ModelAndView employee(@PathVariable String id) {

		ModelAndView mav = new ModelAndView();
		User loginUser = (User) session.getAttribute("loginUser");

		// ユーザーの情報呼び出し
		Integer userId = loginUser.getId();




		session.setAttribute("loginUser", loginUser);
		mav.addObject("loginUser",loginUser);

		// 画面偏移先
		mav.setViewName("/employee");
		return mav;

	}



}
