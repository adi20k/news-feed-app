package com.news.feed.ak.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	@Id
	private String id;
	private String username;
	private String password;
	private String email;
	private Set<String> followers = new HashSet<>();
	private Set<String> following = new HashSet<>();
}