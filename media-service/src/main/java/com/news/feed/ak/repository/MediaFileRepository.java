package com.news.feed.ak.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.news.feed.ak.model.MediaFile;

public interface MediaFileRepository extends MongoRepository<MediaFile, String> {
}