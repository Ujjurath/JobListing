package com.example.JobListing.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import org.bson.Document;
import com.mongodb.client.AggregateIterable;

import com.example.JobListing.Model.Post;

@Component
public class SearchRepositoryImpl implements SearchRepository {

	@Autowired
	com.mongodb.client.MongoClient client;
	
	@Autowired
	MongoConverter converter;

	@Override
	public List<Post> findByText(String text) {
		
		final List<Post> posts = new ArrayList<Post>();
		
		MongoDatabase database = client.getDatabase("Example");
		MongoCollection<Document> collection = database.getCollection("JobListing");
		AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search", 
		    new Document("text", 
		    new Document("query", text)
		                .append("path", Arrays.asList("techs", "desc", "profile")))), 
		    new Document("$sort", 
		    new Document("exp", 1L)), 
		    new Document("$limit", 5L)));
		
		result.forEach(doc -> posts.add(converter.read(Post.class, doc)));
		return posts;
	}
	
	
}
