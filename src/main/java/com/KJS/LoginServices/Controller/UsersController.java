package com.KJS.LoginServices.Controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.KJS.LoginServices.Model.CreateUserRequestModel;
import com.KJS.LoginServices.Model.CreateUserResponseModel;
import com.KJS.LoginServices.Service.UsersService;
import com.KJS.LoginServices.Shared.UserDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("users")
@CrossOrigin( allowedHeaders = "*" , origins = "*" )
public class UsersController {
	
	@Autowired
	 Environment env;
	
	@Autowired
	  UsersService usersService;
	

	@GetMapping("/status/check")
	public String status()
	{
		return "Working on port ";
	}
	
	
	@PostMapping(
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
			)
	public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel userDetails)
	{

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDto userDto = modelMapper.map(userDetails, UserDto.class);
		UserDto createdUser =  usersService.createUser(userDto);
		
		CreateUserResponseModel returnValue = modelMapper.map(createdUser, CreateUserResponseModel.class);

		return new ResponseEntity(returnValue , HttpStatus.CREATED);
		
		
	}
	
	
	
	@PostMapping(
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },path  = "mylogin"
			)
	public ResponseEntity<CreateUserResponseModel> mylogin(@Valid @RequestBody CreateUserRequestModel userDetails)
	{

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDto userDto = modelMapper.map(userDetails, UserDto.class);
		UserDto createdUser =  usersService.createUser(userDto);
		
		CreateUserResponseModel returnValue = modelMapper.map(createdUser, CreateUserResponseModel.class);

		return new ResponseEntity(returnValue , HttpStatus.CREATED);
		
		
	}
	
}
