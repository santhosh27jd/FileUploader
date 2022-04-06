package com.bank.data.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.data.entity.Comment;
import com.bank.data.repository.CommentRepository;

@Service
public class CommentsServiceImpl implements CommentService {
	@Autowired
	private CommentRepository repository;

	@Override
	public Comment saveComment(Comment comment) {
		// TODO Auto-generated method stub
		return repository.save(comment);
	}

	@Override
	public List<Comment> fetchComments() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Comment updateComments(Comment comment, Integer id) {
		// TODO Auto-generated method stub
		Comment comDb = repository.findById(id).get();

		if (Objects.nonNull(comment.getEmail()) && !"".equalsIgnoreCase(comment.getEmail())) {
			comDb.setEmail(comment.getEmail());
		}

		if (Objects.nonNull(comment.getBody()) && !"".equalsIgnoreCase(comment.getBody())) {
			comDb.setBody(comment.getBody());
		}

		if (Objects.nonNull(comment.getName()) && !"".equalsIgnoreCase(comment.getName())) {
			comDb.setName(comment.getName());
		}

		return repository.save(comDb);
	}

	@Override
	public void deleteCommentsById(Integer id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

}
