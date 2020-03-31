package com.mun.workshopmongodb.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mun.workshopmongodb.domain.Post;
import com.mun.workshopmongodb.repository.PostRepository;
import com.mun.workshopmongodb.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;
	
	public Post findById(String id) {
		Optional<Post> optional = repository.findById(id);
		return optional.orElseThrow(() -> new ObjectNotFoundException("Object not found!"));		
	}
	
	public Post insert(Post entity) {
		return repository.insert(entity);
	}
	
	public void delete(String id) { 
		findById(id);
		repository.deleteById(id);
	}
	
	public List<Post> findAll(){
		return repository.findAll();
	}
	
	public List<Post> findByTitleContainingIgnoreCase(String text){
		return repository.findByTitleContainingIgnoreCase(text);
	}
	
	public List<Post> findByTitleContainingIgnoreCase2(String text){
		return repository.findByTitleContainingIgnoreCase2(text);
	}
	
	public List<Post> findByAnyTextByDate(String text, Instant startDate, Instant endDate){
		return repository.findByAnyTextByDate(text, startDate, endDate);
	}
	
}
