package com.bank.data.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.bank.data.entity.Comment;
import com.bank.data.entity.Document;
import com.bank.data.entity.Post;
import com.bank.data.entity.User;
import com.bank.data.service.CommentService;
import com.bank.data.service.DocumentService;
import com.bank.data.service.PostService;
import com.bank.data.service.UserService;

@RestController
public class FileController {

	private final Path fileStorageLocation = Paths.get("/Users/Documents/Sample").toAbsolutePath()
			.normalize();
	private final String POST_API = "https://jsonplaceholder.typicode.com/posts";
	private final String USER_API = "https://jsonplaceholder.typicode.com/users";
	private final String COMM_API = "https://jsonplaceholder.typicode.com/comments";


	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private DocumentService docService;
	
	/**
	 * Get post
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("/posts/get")
	public List<Post> getAllPost() {
		String url = POST_API;
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
		return restTemplate.exchange(url, HttpMethod.GET, entity, List.class).getBody();
	}

	@GetMapping("/posts/{id}")
	public Post getPost(@PathVariable Integer id) {
		String url = POST_API + "/{id}";
		return restTemplate.getForObject(url, Post.class, id);
	}
	
	/**
	 * Create post
	 * @param post
	 * @return
	 */
	@PostMapping("/posts/add")
	public String addPost(@RequestBody Post post) {
		String url = POST_API;
		postService.savePost(post);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<Post> entity = new HttpEntity<Post>(post, httpHeaders);
		return restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
	}

	/**
	 * update post
	 * @param id
	 * @param post
	 * @return
	 */
	@PutMapping(value = "/posts/update/{id}")
	public String updatePost(@PathVariable("id") int id, @RequestBody Post post) {
		postService.updatePost(post, id);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<Post> entity = new HttpEntity<Post>(post, httpHeaders);
		return restTemplate.exchange(POST_API + "/" + id, HttpMethod.PUT, entity, String.class).getBody();
	}

	@DeleteMapping(value = "/posts/delete/{id}")
	public String deletePost(@PathVariable("id") int id) {
		postService.deletePostById(id);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
		return restTemplate.exchange(POST_API + "/" + id, HttpMethod.DELETE, entity, String.class).getBody();
	}

	/**
	 * get all user
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("/users/get")
	public List<User> getAllUser() {
		String url = USER_API;
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
		return restTemplate.exchange(url, HttpMethod.GET, entity, List.class).getBody();
	}
	
	/**
	 * get All document
	 */
	@GetMapping("/users/{id}")
	public List<Document> getDocumentList(@PathVariable Integer id) {
		List<User> userdoclist = userService.fetchUserById(id);
		List<Integer> docId = userdoclist.stream().map(el->el.getDocId()).collect(Collectors.toList());
		List<Document> docList = docService.fetchList(docId);
		return docList;
	}

	@GetMapping("/users/{id}")
	public User getUser(@PathVariable Integer id) {
		String url = USER_API + "/{id}";
		return restTemplate.getForObject(url, User.class, id);
	}
	
	@GetMapping("/document/{id}")
	public Document getDocument(@PathVariable Integer id) {
		return docService.fetchById(id);
	}

	/**
	 * Create user
	 * @param user
	 * @return
	 */
	@PostMapping("/users/add")
	public String addUser(@RequestBody User user) {
		String url = USER_API;
		userService.saveUser(user);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<User> entity = new HttpEntity<User>(user, httpHeaders);
		return restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
	}

	/**
	 * Update user
	 * @param id
	 * @param user
	 * @return
	 */
	@PutMapping(value = "/users/update/{id}")
	public String updateUser(@PathVariable("id") int id, @RequestBody User user) {
		userService.updateUser(user,id);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<User> entity = new HttpEntity<User>(user, httpHeaders);
		return restTemplate.exchange(USER_API + "/" + id, HttpMethod.PUT, entity, String.class).getBody();
	}

	@DeleteMapping(value = "/users/delete/{id}")
	public String deleteUser(@PathVariable("id") int id) {
		userService.deleteUserById(id);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
		return restTemplate.exchange(USER_API + "/" + id, HttpMethod.DELETE, entity, String.class).getBody();
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/comments/get")
	public List<User> getAllComments() {
		String url = COMM_API;
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
		return restTemplate.exchange(url, HttpMethod.GET, entity, List.class).getBody();
	}

	@GetMapping("/comments/{id}")
	public Comment getComments(@PathVariable Integer id) {
		String url = COMM_API + "/{id}";
		return restTemplate.getForObject(url, Comment.class, id);
	}

	/**
	 * Add comment for the post
	 * @param comment
	 * @return
	 */
	@PostMapping("/comments/add")
	public String addComments(@RequestBody Comment comment) {
		String url = COMM_API;
		commentService.saveComment(comment);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<Comment> entity = new HttpEntity<Comment>(comment, httpHeaders);
		return restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
	}

	/**
	 * Update comments
	 * @param id
	 * @param comment
	 * @return
	 */
	@PutMapping(value = "/comments/update/{id}")
	public String updateComments(@PathVariable("id") int id, @RequestBody Comment comment) {
		commentService.updateComments(comment, id);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<Comment> entity = new HttpEntity<Comment>(comment, httpHeaders);
		return restTemplate.exchange(COMM_API + "/" + id, HttpMethod.PUT, entity, String.class).getBody();
	}

	@DeleteMapping(value = "/comments/delete/{id}")
	public String deleteComments(@PathVariable("id") int id) {
		commentService.deleteCommentsById(id);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
		return restTemplate.exchange(COMM_API + "/" + id, HttpMethod.DELETE, entity, String.class).getBody();
	}

	/**
	 * Uploading document
	 * @param file
	 * @param userId
	 * @param docType
	 * @return
	 */
	
	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("userId") Integer userId,
			@RequestParam("docType") String docType) {
		try {
			Document doc = storeFile(file, userId, docType);
			User user = userService.fetchById(userId);
			if(user != null) {
				user.setDocId(doc.getDocumentId());
				userService.updateUser(user, userId);
			}else {
				System.out.println("USER NOT FOUND");
			}
			return doc.getFileName();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "File is not stored";
	}

	/**
	 * Delete document
	 * @param userId
	 * @param name
	 * @return
	 */
	@DeleteMapping("/delete")
	public String deleteFile(@RequestParam("userId") Integer userId, @RequestParam("fileName") String name) {
		try {
			Path targetLocation = this.fileStorageLocation.resolve(name);
			Files.delete(targetLocation);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "File is deleted";
	}

	/**
	 * View/Download document
	 * @param userId
	 * @param docType
	 * @param request
	 * @param name
	 * @return
	 */
	@GetMapping("/download")
	public ResponseEntity<Resource> downloadFile(@RequestParam("userId") Integer userId,
			@RequestParam("docType") String docType, HttpServletRequest request, @RequestParam("name") String name) {
		String fileName = name;
		Resource resource = null;
		if (fileName != null && !fileName.isEmpty()) {
			try {
				Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
				resource = new UrlResource(filePath.toUri());
				if (!resource.exists()) {
					throw new FileNotFoundException("File not found " + fileName);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			String contentType = null;
			try {
				contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
			} catch (IOException ex) {
			}
			if (contentType == null) {
				contentType = "application/octet-stream";
			}
			return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
					.body(resource);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Create document and store
	 * @param file
	 * @param userId
	 * @param docType
	 * @return
	 * @throws Exception
	 */
	public Document storeFile(MultipartFile file, Integer userId, String docType) throws Exception {

		Document doc = new Document();
		// Normalize file name
		String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
		String fileName = "";
		try {
			// Check if the file's name contains invalid characters
			if (originalFileName.contains("..")) {
				throw new Exception("Sorry! Filename contains invalid path sequence " + originalFileName);
			}
			String fileExtension = "";
			try {
				fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
			} catch (Exception e) {
				fileExtension = "";
			}
			fileName = userId + "_" + docType + fileExtension;
			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			doc.setUploadDir(targetLocation.toString());
			doc.setUserId(userId);
			doc.setDocumentType("pdf");
			doc.setDocumentFormat("text");
			doc.setFileName(fileName);
			return docService.saveDocument(doc);
		} catch (IOException ex) {
			throw new Exception("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

}
