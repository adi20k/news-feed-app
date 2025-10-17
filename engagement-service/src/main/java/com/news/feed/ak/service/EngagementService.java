package com.news.feed.ak.service;

import java.time.Instant;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.news.feed.ak.dto.EngagementRequest;
import com.news.feed.ak.model.Engagement;
import com.news.feed.ak.model.EngagementType;
import com.news.feed.ak.repository.EngagementRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EngagementService {
	private final EngagementRepository repository;
	private final RedisTemplate<String, String> redisTemplate;
	private final KafkaTemplate<String, String> kafkaTemplate;

	public void engage(String userId, EngagementRequest request) {
		Engagement engagement = Engagement.builder().userId(userId).tweetId(request.getTweetId())
				.type(request.getType()).content(request.getContent()).createdAt(Instant.now()).build();
		repository.save(engagement);
// Increment Redis counter
		String key = "engagement:" + request.getTweetId() + ":" + request.getType().name().toLowerCase();
		redisTemplate.opsForValue().increment(key);
// Stream to Kafka
		kafkaTemplate.send("engagement-events",
				userId + " " + request.getType().name().toLowerCase() + " tweet " + request.getTweetId());
	}

	public Long getEngagementCount(String tweetId, EngagementType type) {
		String key = "engagement:" + tweetId + ":" + type.name().toLowerCase();
		String cached = redisTemplate.opsForValue().get(key);
		if (cached != null)
			return Long.parseLong(cached);
		Long count = repository.countByTweetIdAndType(tweetId, type);
		redisTemplate.opsForValue().set(key, String.valueOf(count));
		return count;
	}
}