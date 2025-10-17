package com.news.feed.ak.model;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "engagements")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Engagement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String userId;
	private String tweetId;
	@Enumerated(EnumType.STRING)
	private EngagementType type;
	private String content; // For replies only
	private Instant createdAt;
}
