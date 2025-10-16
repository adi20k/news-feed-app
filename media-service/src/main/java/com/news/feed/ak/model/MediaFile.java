package com.news.feed.ak.model;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "media_files")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MediaFile {
	@Id
	private String id;
	private String userId;
	private String originalFilename;
	private String s3Key;
	private String contentType;
	private String cdnUrl;
	private Instant uploadedAt;
}
