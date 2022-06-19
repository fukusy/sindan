package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Result;
import com.example.demo.repository.ResultRepository;

@Service
public class ResultService {
	@Autowired
	ResultRepository resultRepository;

	public Result resultSelect(Integer userId) {
		Result result = (Result) resultRepository.findById(userId).orElse(null);
		return result;
	}
}
