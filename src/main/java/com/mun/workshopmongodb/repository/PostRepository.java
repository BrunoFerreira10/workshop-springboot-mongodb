package com.mun.workshopmongodb.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.mun.workshopmongodb.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	
	public List<Post> findByTitleContainingIgnoreCase(String text);
	
	@Query("{'title': { $regex: ?0, $options: 'i' } }")
	public List<Post> findByTitleContainingIgnoreCase2(String text);	
	
	@Query("{ $and: [ {date: { $gte: ?1} },"
			+ "{date: { $lte: ?2} },"
			+ "{$or : [{'title': { $regex: ?0, $options: 'i' } },{'body': { $regex: ?0, $options: 'i' } },{'comments.text': { $regex: ?0, $options: 'i' } }] } ]}")
	public List<Post> findByAnyTextByDate(String text, Instant startDate, Instant endDate);
	
}
