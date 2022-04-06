package com.bank.data.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.data.entity.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer>{

}
