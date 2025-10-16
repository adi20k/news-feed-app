package com.news.feed.ak.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.news.feed.ak.dto.MediaUploadResponse;
import com.news.feed.ak.model.MediaFile;
import com.news.feed.ak.service.MediaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/media")
@RequiredArgsConstructor
public class MediaController {
	private final MediaService mediaService;

	@PostMapping("/upload")
	public MediaUploadResponse upload(@RequestHeader("userId") String userId, @RequestParam("file") MultipartFile file)
			throws Exception {
		return mediaService.uploadMedia(userId, file);
	}

	@GetMapping("/{id}")
	public MediaFile getMedia(@PathVariable String id) {
		return mediaService.getMedia(id);
	}
}
