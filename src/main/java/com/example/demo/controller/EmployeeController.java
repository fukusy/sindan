package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Result;
import com.example.demo.entity.Type;
import com.example.demo.entity.User;
import com.example.demo.service.AccountService;
import com.example.demo.service.ResultService;
import com.example.demo.service.TopService;
import com.example.demo.service.TypeService;


@Controller
public class EmployeeController {
	@Autowired
	AccountService accountService;
	@Autowired
	ResultService resultService;
	@Autowired
	TopService topService;
	@Autowired
	TypeService typeService;

	@Autowired
	HttpSession session;

	@GetMapping("/employee")
	public ModelAndView employee(@PathVariable Integer id) {

		ModelAndView mav = new ModelAndView();
		User loginUser = (User) session.getAttribute("loginUser");

		// ユーザーの情報呼び出し
		Integer userId = loginUser.getId();

		Result result = resultService.resultSelect(userId);

		Type type = TypeService.typeSelect(result.getTypeId());


		List<String> errorMessages = new ArrayList<String>();
//		if(!isValid(user, errorMessages) ) {
//			mav.addObject("errorMessages", errorMessages);
//			mav.setViewName("/newTweet");
//			return mav;
//		}

		session.setAttribute("loginUser", loginUser);
		mav.addObject("loginUser",loginUser);

		// 画面偏移先
		mav.setViewName("/employee");
		return mav;

	}



}
