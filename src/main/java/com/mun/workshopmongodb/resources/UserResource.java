package com.mun.workshopmongodb.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mun.workshopmongodb.domain.Post;
import com.mun.workshopmongodb.domain.User;
import com.mun.workshopmongodb.dto.UserDTO;
import com.mun.workshopmongodb.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id){
		UserDTO entityDto = new UserDTO(service.findById(id));
		return ResponseEntity.ok().body(entityDto);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody UserDTO entityDto){
		User entity = service.insert(service.fromDTO(entityDto));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<Void> update(@PathVariable String id, @RequestBody UserDTO entityDto){
		User entity = service.fromDTO(entityDto);
		entity.setId(id);
		entity = service.update(entity);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll(){
		
		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());		
		
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/posts")
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id){
		List<Post> list = service.findById(id).getPosts();
		return ResponseEntity.ok().body(list);
	}

}
