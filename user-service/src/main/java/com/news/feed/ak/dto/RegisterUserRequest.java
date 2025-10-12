package com.news.feed.ak.dto;

import lombok.Data;

@Data
public class RegisterUserRequest {
	private String username;
	private String email;
	private String password;
}