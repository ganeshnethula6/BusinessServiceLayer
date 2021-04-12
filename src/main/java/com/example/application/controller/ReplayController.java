package com.example.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.application.service.ReplayService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/online-advertisement-application")
public class ReplayController {
	@Autowired
	private ReplayService replayService;
	

}
