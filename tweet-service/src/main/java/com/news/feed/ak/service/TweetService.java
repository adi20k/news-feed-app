package com.news.feed.ak.service;

import java.time.Instant;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.news.feed.ak.dto.CreateTweetRequest;
import com.news.feed.ak.model.Tweet;
import com.news.feed.ak.repository.TweetRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TweetService {
	private final TweetRepository tweetRepository;
	private final KafkaTemplate<String, String> kafkaTemplate;

	public Tweet createTweet(String userId, CreateTweetRequest request) {
		Tweet tweet = Tweet.builder().userId(userId).content(request.getContent()).createdAt(Instant.now())
				.updatedAt(Instant.now()).build();
		Tweet saved = tweetRepository.save(tweet);
		kafkaTemplate.send("tweet-events", userId + " tweeted: " + saved.getId());
		return saved;
	}

	public Tweet getTweetById(String id) {
		return tweetRepository.findById(id).orElseThrow();
	}

	@Cacheable(value = "userTweets", key = "#userId")
	public List<Tweet> getTweetsByUser(String userId) {
		return tweetRepository.findByUserIdOrderByCreatedAtDesc(userId);
	}
}
