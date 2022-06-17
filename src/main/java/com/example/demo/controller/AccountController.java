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
import org.springframework.web.bind.annotation.RequestParam;
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

	//初期画面（ログイン画面）
	@GetMapping("/")
	public ModelAndView loginMenu() {
		ModelAndView mav = new ModelAndView();
		User user = new User();
		if (session.getAttribute("errorMessages") != null) {
			String errorMessages = session.getAttribute("errorMessages").toString();
			mav.addObject("errorMessages",errorMessages);
		}
		mav.setViewName("/login");
		mav.addObject("formModel", user);
		return mav;
	}

	//ログイン処理
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

	//パスワード変更画面
	@GetMapping("/password")
	public ModelAndView passwordMenu() {
		ModelAndView mav = new ModelAndView();
		User loginUser = (User)session.getAttribute("loginUser");
		mav.addObject("formModel", loginUser);
		return mav;
	}

	//パスワード変更処理
	@PostMapping("/change")
	public ModelAndView passChange(@RequestParam(name = "password", required = false) String password,
			@RequestParam(name = "newPassword", required = false) String newPassword,
			@RequestParam(name = "confirmPassword", required = false) String confirmPassword) {
		ModelAndView mav = new ModelAndView();
		List<String> errorMessages = new ArrayList<>();
		User loginUser = (User)session.getAttribute("loginUser");

		if(!isVaildPass(password, newPassword,confirmPassword,loginUser, errorMessages)) {
			mav.setViewName("/password");
			mav.addObject("errorMessages", errorMessages);
			mav.addObject("formModel", loginUser);
			return mav;
		}
		String encodePassword = passwordEncoder.encode(newPassword);
		loginUser.setPassword(encodePassword);

		accountService.save(loginUser);

		if (loginUser.getStatus() == 0) {
			return new ModelAndView("redirect:/manager");
		}else{
			return new ModelAndView("redirect:/employee");
		}
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
			} else if (!passwordEncoder.matches(password, user.getPassword())) {
				errorMessages.add("アカウントまたはパスワードが誤っています");
			}
		}

		if (errorMessages.size() != 0) {
			return false;
		}
		return true;
	}

	//パスワード変更エラー処理
	private boolean isVaildPass(String password, String newPassword, String confirmPassword, User loginUser, List<String> errorMessages) {

		if ((password.isBlank())|| (newPassword.isBlank()) || (confirmPassword.isBlank())){
			errorMessages.add("空欄の箇所があります。全て入力してください");
		} else if (!passwordEncoder.matches(password, loginUser.getPassword())) {
			errorMessages.add("パスワードが一致しません");
		} else if (newPassword.length() < 6) {
			errorMessages.add("パスワードは英数字を含む6文字以上で入力してください");
		} else if (!newPassword.matches("^(?=.+\\d)(?=.+[a-zA-Z]).*$")) {
			errorMessages.add("パスワードは英数字を含む6文字以上で入力してください");
		}  else if (!(newPassword.equals(confirmPassword))) {
			errorMessages.add("入力したパスワードと確認用パスワードが一致しません");
		}

		if (errorMessages.size() != 0) {
			return false;
		}
		return true;
	}
}