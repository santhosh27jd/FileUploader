package com.bank.data.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.bank.data.entity.User;
import com.bank.data.repository.UserRepository;

public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository repository;

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return repository.save(user);
	}

	@Override
	public List<User> fetchUser() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public User updateUser(User user, Integer id) {
		// TODO Auto-generated method stub
		User comDb = repository.findById(id).get();

		if (Objects.nonNull(user.getEmail()) && !"".equalsIgnoreCase(user.getEmail())) {
			comDb.setEmail(user.getEmail());
		}

		if (Objects.nonNull(user.getPhone()) && !"".equalsIgnoreCase(user.getPhone())) {
			comDb.setPhone(user.getPhone());
		}
		
		if (Objects.nonNull(user.getWebsite()) && !"".equalsIgnoreCase(user.getWebsite())) {
			comDb.setWebsite(user.getWebsite());
		}

		if (Objects.nonNull(user.getUsername()) && !"".equalsIgnoreCase(user.getUsername())) {
			comDb.setUsername(user.getUsername());
		}
		
		return repository.save(comDb);
	}

	@Override
	public void deleteUserById(Integer id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	@Override
	public User fetchById(Integer id) {
		// TODO Auto-generated method stub
		return repository.findById(id).get();
	}

	@Override
	public List<User> fetchUserById(Integer id) {
		// TODO Auto-generated method stub
		List<User> list = repository.findAll();
		return list.stream().filter(el -> el.getId().equals(id)).collect(Collectors.toList());
	}

}
