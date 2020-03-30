package com.mun.workshopmongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mun.workshopmongodb.domain.User;
import com.mun.workshopmongodb.dto.UserDTO;
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
	
	public User insert(User entity) {
		return repository.insert(entity);
	}
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User fromDTO(UserDTO entityDto) {
		User entity = new User();
		entity.setId(entityDto.getId());
		entity.setName(entityDto.getName());
		entity.setEmail(entityDto.getEmail());
		return entity;
	}
	
	
	
}
