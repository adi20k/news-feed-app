package com.news.feed.ak.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.news.feed.ak.dto.EngagementRequest;
import com.news.feed.ak.model.EngagementType;
import com.news.feed.ak.service.EngagementService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/engagement")
@RequiredArgsConstructor
public class EngagementController {
	private final EngagementService service;

	@PostMapping
	public void engage(@RequestHeader("userId") String userId, @RequestBody EngagementRequest request) {
		service.engage(userId, request);
	}

	@GetMapping("/{tweetId}")
	public EngagementStats getStats(@PathVariable String tweetId) {
		return EngagementStats.builder().likes(service.getEngagementCount(tweetId, EngagementType.LIKE))
				.retweets(service.getEngagementCount(tweetId, EngagementType.RETWEET))
				.replies(service.getEngagementCount(tweetId, EngagementType.REPLY)).build();
	}

	@lombok.Builder
	@lombok.Data
	public static class EngagementStats {
		private Long likes;
		private Long retweets;
		private Long replies;
	}
}