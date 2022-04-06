package com.bank.data.service;

import java.util.List;

import com.bank.data.entity.Post;

public interface PostService {

	// Save operation
    Post savePost(Post post);
 
    // Read operation
    List<Post> fetchPost();
 
    // Update operation
    Post updatePost(Post post,
                                Integer id);
 
    // Delete operation
    void deletePostById(Integer id);
}
