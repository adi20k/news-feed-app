package com.news.feed.ak.model;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "tweets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tweet {
	@Id
	private String id;
	private String userId;
	private String content;
	private Instant createdAt;
	private Instant updatedAt;
}
