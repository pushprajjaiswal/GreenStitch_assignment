package com.GreenStitch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.GreenStitch.exceptions.UserException;
import com.GreenStitch.model.UserData;
import com.GreenStitch.repository.UserRepository;
import com.GreenStitch.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// register user with given details
	
	@PostMapping("/app/signup")
	public ResponseEntity<UserData> registerUser(@Validated @RequestBody UserData user) throws UserException 
	{
		user.setPassword(passwordEncoder.encode(user.getPassword())); 
         
		UserData p = userService.registerUser(user);
		
		return new ResponseEntity<UserData>(p,HttpStatus.CREATED);
	}
	
	// first time user login with Email and password and got JWT token 
	
	@GetMapping("/signIn")
	public ResponseEntity<UserData> getLoggedInCustomerDetailsHandler(Authentication auth) throws BadCredentialsException{
		
		UserData customer= userRepository.findByEmail(auth.getName());
		
		if(customer!=null)
		{
			 return new ResponseEntity<>(customer, HttpStatus.ACCEPTED);
		}
		
		throw new BadCredentialsException("Invalid Username or password");
		

	}
	
	// Authentication with JWT token 
	
	@GetMapping("/logged-in/user")
	public ResponseEntity<String> LoginUser() throws UserException
	{
		UserData user =  userService.loginUser();
		
		String message = "Welcome to Shimbhu's Website  : " +user.getFullName();
		
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
}
