package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.User;
import com.example.demo.service.AccountService;

@Controller
public class AccountController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private HttpSession session;

	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@GetMapping
	public ModelAndView loginMenu() {
		ModelAndView mav = new ModelAndView();
		User user = new User();
		mav.setViewName("/login");
		mav.addObject("formModel", user);
		return mav;
	}

	@PostMapping("/login")
	public ModelAndView login(@ModelAttribute("formModel") User user, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		List<String> errorMessages = new ArrayList<>();

		String account = user.getAccount();
		String password = user.getPassword();

		User loginUser = accountService.findAccount(account);

		if (!isValidLogin(loginUser, account, password, errorMessages)) {
			mav.setViewName("/login");
			mav.addObject("errorMessages", errorMessages);
			return mav;
		}
		session = request.getSession();
		session.setAttribute("loginUser", loginUser);

		String defaultPass = loginUser.getAccount();

		if (passwordEncoder.matches(defaultPass, loginUser.getPassword())) {
			return new ModelAndView("redirect:/password");
		}

		if (loginUser.getStatus() == 0) {
			return new ModelAndView("redirect:/manager");
		}else{
			return new ModelAndView("redirect:/employee");
		}
	}

	@GetMapping("/password")
	public ModelAndView passwordMenu(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		session = request.getSession();

		if (session.getAttribute("errorMessages") != null) {
			String errorMessages = (String) session.getAttribute("errorMessages");
			mav.addObject("errorMessages", errorMessages);
		}

		User user = new User();
		mav.setViewName("/password");
		mav.addObject("formModel", user);
		return mav;
	}
	//ログイン時エラー処理
	private boolean isValidLogin(User user, String account, String password, List<String> errorMessages) {

		if (account.isBlank()) {
			errorMessages.add("アカウント名を入力してください");
		}

		if (password.isBlank()) {
			errorMessages.add("パスワードを入力してください");
		}

		if (!(account.isBlank() || password.isBlank())) {
			if (user == null) {
				errorMessages.add("アカウントまたはパスワードが誤っています");
			} else if ((!passwordEncoder.matches(password, user.getPassword()))) {
				errorMessages.add("アカウントまたはパスワードが誤っています");
			}
		}

		if (errorMessages.size() != 0) {
			return false;
		}
		return true;

	}
}
