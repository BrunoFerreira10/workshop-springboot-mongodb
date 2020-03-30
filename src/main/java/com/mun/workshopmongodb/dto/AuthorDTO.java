package com.mun.workshopmongodb.dto;

import java.io.Serializable;

import com.mun.workshopmongodb.domain.User;

public class AuthorDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	
	public AuthorDTO() {
	}
	
	public AuthorDTO(User entity) {
		this.id = entity.getId();
		this.name = entity.getName();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
