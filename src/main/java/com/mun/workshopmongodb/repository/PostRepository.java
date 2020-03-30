package com.mun.workshopmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mun.workshopmongodb.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	
}