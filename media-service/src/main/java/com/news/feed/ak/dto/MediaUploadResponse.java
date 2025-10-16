package com.news.feed.ak.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MediaUploadResponse {
	private String mediaId;
	private String cdnUrl;
}