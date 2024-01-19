package com.GreenStitch.service;

import com.GreenStitch.exceptions.UserException;
import com.GreenStitch.model.UserData;

public interface UserService {

	public UserData registerUser(UserData user) throws UserException;
	public UserData loginUser()  throws UserException;
}
