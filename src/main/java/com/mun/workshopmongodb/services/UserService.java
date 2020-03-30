package com.mun.workshopmongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mun.workshopmongodb.domain.User;
import com.mun.workshopmongodb.repository.UserRepository;
import com.mun.workshopmongodb.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public User findById(String id) {
		Optional<User> optional = repository.findById(id);
		return optional.orElseThrow(() -> new ObjectNotFoundException("Object not found!"));		
	}
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	
	
}
