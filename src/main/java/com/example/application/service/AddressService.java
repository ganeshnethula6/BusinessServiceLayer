package com.example.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.application.repository.AddressRepository;

@Service
public class AddressService {
	@Autowired
	private AddressRepository repository;

}
