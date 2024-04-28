package com.example.JobListing.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.JobListing.Model.Post;

@Component
public interface SearchRepository {

	List<Post> findByText(String text);
}
