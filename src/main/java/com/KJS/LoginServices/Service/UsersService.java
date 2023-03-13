package com.KJS.LoginServices.Service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.KJS.LoginServices.Shared.UserDto;

public interface UsersService extends UserDetailsService{

	
	UserDto createUser(UserDto userDetails);
	UserDto getUserDetailsByEmail(String email);
}
