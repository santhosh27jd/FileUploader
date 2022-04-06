package com.bank.data.service;

import java.util.List;
import java.util.Objects;


import org.springframework.beans.factory.annotation.Autowired;

import com.bank.data.entity.Post;
import com.bank.data.repository.PostRepository;

public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepository repository;

	@Override
	public Post savePost(Post post) {
		// TODO Auto-generated method stub
		return repository.save(post);
	}

	@Override
	public List<Post> fetchPost() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Post updatePost(Post post, Integer id) {
		// TODO Auto-generated method stub
		Post postDb = repository.findById(id).get();


		if (Objects.nonNull(post.getBody()) && !"".equalsIgnoreCase(post.getBody())) {
			postDb.setBody(post.getBody());
		}

		if (Objects.nonNull(post.getTitle()) && !"".equalsIgnoreCase(post.getTitle())) {
			postDb.setTitle(post.getTitle());
		}

		return repository.save(postDb);
	}

	@Override
	public void deletePostById(Integer id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

}
