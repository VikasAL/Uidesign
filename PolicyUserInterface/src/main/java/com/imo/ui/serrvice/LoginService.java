package com.imo.ui.serrvice;

import org.springframework.stereotype.Service;

import com.imo.ui.modal.User;

@Service
public class LoginService {

public boolean validate(User user)	{
		
		if (user.getUserName().equals(user.getUserPassword()) && user.getUserName()!="") {
			return true;
		}
		return false;
	}
}
