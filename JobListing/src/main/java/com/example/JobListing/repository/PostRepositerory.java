package com.example.JobListing.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.JobListing.Model.Post;

public interface PostRepositerory extends MongoRepository<Post, String>{

}
