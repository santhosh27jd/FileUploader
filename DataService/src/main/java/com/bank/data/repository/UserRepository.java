package com.bank.data.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.data.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
