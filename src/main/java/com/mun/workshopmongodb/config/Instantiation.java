package com.mun.workshopmongodb.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.mun.workshopmongodb.domain.Post;
import com.mun.workshopmongodb.domain.User;
import com.mun.workshopmongodb.dto.AuthorDTO;
import com.mun.workshopmongodb.repository.PostRepository;
import com.mun.workshopmongodb.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		repository.deleteAll();
		repository.saveAll(Arrays.asList(maria,alex,bob));
		
		Post post1 = new Post(null, Instant.parse("2018-03-21T09:33:07Z"), "Partiu viagem", "Vou viajar para São Paulo. Abraços.", new AuthorDTO(maria));
		Post post2 = new Post(null, Instant.parse("2018-03-23T06:52:25Z"), "Bom dia!", "Acordei feliz hoje!", new AuthorDTO(maria));
		postRepository.deleteAll();
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().add(post1);
		maria.getPosts().add(post2);
		repository.save(maria);
	}

}
