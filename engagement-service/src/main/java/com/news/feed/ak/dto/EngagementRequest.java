package com.news.feed.ak.dto;

import com.news.feed.ak.model.EngagementType;

import lombok.Data;

@Data
public class EngagementRequest {
	private String tweetId;
	private EngagementType type;
	private String content; // Optional (used in replies)
}