package com.bank.data.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.data.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{

}
