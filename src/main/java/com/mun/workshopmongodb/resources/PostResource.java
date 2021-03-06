package com.mun.workshopmongodb.resources;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mun.workshopmongodb.domain.Post;
import com.mun.workshopmongodb.repository.util.URL;
import com.mun.workshopmongodb.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {
	
	@Autowired
	private PostService service;
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id){
		Post entityDto = service.findById(id);
		return ResponseEntity.ok().body(entityDto);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/titlecontaining")
	public ResponseEntity<List<Post>> findByTitleContainingIgnoreCase(@RequestParam(value = "text", defaultValue = "") String text){
		List<Post> list = service.findByTitleContainingIgnoreCase(URL.decodeParam(text));
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/titlecontaining2")
	public ResponseEntity<List<Post>> findByTitleContainingIgnoreCase2(@RequestParam(value = "text", defaultValue = "") String text){
		List<Post> list = service.findByTitleContainingIgnoreCase(URL.decodeParam(text));
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findAll(){		
		List<Post> list = service.findAll();		
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/anytextbydate")
	public ResponseEntity<List<Post>> findByAnyTextByDate(	
															@RequestParam(value = "text", defaultValue = "") String text,
															@RequestParam(value = "startDate", defaultValue = "") String startDate,
															@RequestParam(value = "endDate", defaultValue = "") String endDate){
		
		text = URL.decodeParam(text);
		Instant start = URL.convertInstant(startDate, Instant.parse("1970-01-01T00:00:00Z"));
		Instant end = URL.convertInstant(endDate, Instant.now());
		List<Post> list = service.findByAnyTextByDate(text, start, end);
		return ResponseEntity.ok().body(list);
	}

}
