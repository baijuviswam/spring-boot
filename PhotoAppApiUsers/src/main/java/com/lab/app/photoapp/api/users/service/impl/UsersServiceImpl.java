package com.lab.app.photoapp.api.users.service.impl;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lab.app.photoapp.api.users.data.UserEntity;
import com.lab.app.photoapp.api.users.data.UserRepository;
import com.lab.app.photoapp.api.users.service.UsersService;
import com.lab.app.photoapp.api.users.shared.UserDto;

@Service
public class UsersServiceImpl implements UsersService{

	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BCryptPasswordEncoder BCryptPasswordEncoder;
	
	@Override
	public UserDto createUser(UserDto userDetails) {
		//generate random userId
		userDetails.setUserId(UUID.randomUUID().toString());
		userDetails.setEncryptedPassword(BCryptPasswordEncoder.encode(userDetails.getPassword()));
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity userEntity =  modelMapper.map(userDetails, UserEntity.class);
		//userEntity.setEncryptedPassword(UUID.randomUUID().toString());
		
		userRepository.save(userEntity);
		
		UserDto responseObject = modelMapper.map(userEntity, UserDto.class);
		
		return responseObject;
	}

}
