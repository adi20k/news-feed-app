package com.news.feed.ak.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.news.feed.ak.model.Tweet;

public interface TweetRepository extends MongoRepository<Tweet, String> {
	List<Tweet> findByUserIdOrderByCreatedAtDesc(String userId);
}
