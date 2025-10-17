package com.news.feed.ak.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.news.feed.ak.model.Engagement;
import com.news.feed.ak.model.EngagementType;

public interface EngagementRepository extends JpaRepository<Engagement, Long> {
	Long countByTweetIdAndType(String tweetId, EngagementType type);
}