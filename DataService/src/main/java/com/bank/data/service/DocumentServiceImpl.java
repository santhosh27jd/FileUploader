package com.bank.data.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.data.entity.Document;
import com.bank.data.repository.DocumentRepository;

@Service
public class DocumentServiceImpl implements DocumentService {
	@Autowired
	private DocumentRepository repository;

	@Override
	public Document saveDocument(Document document) {
		// TODO Auto-generated method stub
		return repository.save(document);
	}

	@Override
	public List<Document> fetchDocument() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Document updateDocument(Document document, Integer id) {
		// TODO Auto-generated method stub
		Document comDb = repository.findById(id).get();

		if (Objects.nonNull(document.getDocumentType()) && !"".equalsIgnoreCase(document.getDocumentType())) {
			comDb.setDocumentType(document.getDocumentType());
		}

		if (Objects.nonNull(document.getFileName()) && !"".equalsIgnoreCase(document.getFileName())) {
			comDb.setFileName(document.getFileName());
		}

		if (Objects.nonNull(document.getUploadDir()) && !"".equalsIgnoreCase(document.getUploadDir())) {
			comDb.setUploadDir(document.getUploadDir());
		}

		if (Objects.nonNull(document.getDocumentFormat()) && !"".equalsIgnoreCase(document.getDocumentFormat())) {
			comDb.setDocumentFormat(document.getDocumentFormat());
		}
		
		return repository.save(comDb);
	}

	@Override
	public void deleteDocumentsById(Integer id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	@Override
	public Document fetchById(Integer id) {
		// TODO Auto-generated method stub
		return repository.findById(id).get();
	}

	@Override
	public List<Document> fetchList(List<Integer> id) {
		// TODO Auto-generated method stub
		return repository.findAllById(id);
	}

}
