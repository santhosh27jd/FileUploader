package com.bank.data.service;

import java.util.List;

import com.bank.data.entity.Document;

public interface DocumentService {

	// Save operation
    Document saveDocument(Document document);
 
    // Read operation
    List<Document> fetchDocument();
 
    // Update operation
    Document updateDocument(Document document,
                                Integer id);
 
    // Delete operation
    void deleteDocumentsById(Integer id);
    
    Document fetchById(Integer id);
    
    List<Document> fetchList(List<Integer> id);
}
