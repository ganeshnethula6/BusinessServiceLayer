package com.example.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.application.repository.ReplayRepository;

@Service
public class ReplayService {
	@Autowired
	private ReplayRepository replayRepository;
	

}
