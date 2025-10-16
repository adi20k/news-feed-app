package com.news.feed.ak.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.news.feed.ak.dto.MediaUploadResponse;
import com.news.feed.ak.model.MediaFile;
import com.news.feed.ak.repository.MediaFileRepository;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
@RequiredArgsConstructor
public class MediaService {
	private final MediaFileRepository mediaRepo;
	private final S3Client s3Client;

	@Value("${aws.s3.bucket}")
	private String bucket;

	@Value("${aws.s3.cdn-url}")
	private String cdnUrl;

	public MediaUploadResponse uploadMedia(String userId, MultipartFile file) throws Exception {
		String key = "media/" + UUID.randomUUID();
		s3Client.putObject(
				PutObjectRequest.builder().bucket(bucket).key(key).contentType(file.getContentType()).build(),
				RequestBody.fromBytes(file.getBytes()));
		MediaFile media = MediaFile.builder().userId(userId).originalFilename(file.getOriginalFilename()).s3Key(key)
				.contentType(file.getContentType()).cdnUrl(cdnUrl + "/" + key).uploadedAt(Instant.now()).build();
		mediaRepo.save(media);
		return new MediaUploadResponse(media.getId(), media.getCdnUrl());
	}

	public MediaFile getMedia(String id) {
		return mediaRepo.findById(id).orElseThrow();
	}
}
