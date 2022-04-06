package com.bank.data.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.data.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
