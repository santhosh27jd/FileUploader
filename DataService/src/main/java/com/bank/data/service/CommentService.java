package com.bank.data.service;

import java.util.List;

import com.bank.data.entity.Comment;

public interface CommentService {

	// Save operation
    Comment saveComment(Comment comment);
 
    // Read operation
    List<Comment> fetchComments();
 
    // Update operation
    Comment updateComments(Comment comment,
                                Integer id);
 
    // Delete operation
    void deleteCommentsById(Integer id);
}
