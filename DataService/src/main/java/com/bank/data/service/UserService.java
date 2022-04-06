package com.bank.data.service;

import java.util.List;

import com.bank.data.entity.User;

public interface UserService {

	// Save operation
	User saveUser(User user);
 
    // Read operation
    List<User> fetchUser();
 
    // Update operation
    User updateUser(User user,
                                Integer id);
 
    // Delete operation
    void deleteUserById(Integer id);
    
    User fetchById(Integer id);
    
    List<User> fetchUserById(Integer id);
}
