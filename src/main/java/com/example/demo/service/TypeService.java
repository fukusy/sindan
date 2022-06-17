package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.Type;
import com.example.demo.repository.TypeRepository;

public class TypeService {
	@Autowired
	TypeRepository typeRepository;

	public Type selectType (Integer id) {
		Type type = typeRepository.findById(id).orElse(null);
		return type;
	}

}
