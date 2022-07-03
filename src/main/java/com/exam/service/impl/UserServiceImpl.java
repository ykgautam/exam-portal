package com.exam.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	// creating user
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		User local = this.userRepository.findByUsername(user.getUsername());
		if (local != null) {
			System.out.println("user already exists");
			throw new Exception("user already present...");
		} else {
			// create user
			for (UserRole ur : userRoles) {
				this.roleRepository.save(ur.getRole());
			}
			// save userRoles in user object
			user.getUserRoles().addAll(userRoles);
			local = this.userRepository.save(user);
		}
		return local;
	}

	// getting user by username
	@Override
	public User getUser(String username) {
		return this.userRepository.findByUsername(username);
	}

	// delete user by id
	@Override
	public void deleteUser(Long Id) {
		this.userRepository.deleteById(Id);
	}

}
