package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.Result;
import com.example.demo.repository.ResultRepository;

public class ResultService {
	@Autowired
	ResultRepository resultRepository;

	public Result resultSelect(Integer id) {
		Result result = (Result) resultRepository.findById(id).orElse(null);
		return result;
	}
}
