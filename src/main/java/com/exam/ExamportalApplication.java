package com.exam;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;

@SpringBootApplication
public class ExamportalApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ExamportalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		 System.out.println("code started"); User user = new User();
		 user.setFirstName("yash"); user.setLastName("Gautam");
		  user.setUsername("ykg"); user.setPassword(this.bCryptPasswordEncoder.encode("abc"));
		  user.setEmail("yash@gmail.com"); user.setPhone("9876354212");
		  user.setProfile("default.png");
		  
		  Role role1 = new Role(); role1.setRoleId(44L); role1.setRoleName("ADMIN");
		  
		  UserRole userRole = new UserRole(); userRole.setRole(role1);
		  userRole.setUser(user);
		  
		  Set<UserRole> userRoleSet = new HashSet<>(); userRoleSet.add(userRole);
		  
		  User user1 = this.userService.createUser(user, userRoleSet);
		 System.out.println(user1.getUsername());
		 
	}

}
