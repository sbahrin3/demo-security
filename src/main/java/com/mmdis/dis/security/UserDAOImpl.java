package com.mmdis.dis.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mmdis.dis.entity.User;
import com.mmdis.dis.jpa.Persistence;

@Repository
public class UserDAOImpl {

	Persistence db = Persistence.db();

	public CustomUser loadUserByUsername(final String username) {

		CustomUser cu = new CustomUser();
		System.out.println("Load by username: ");
		User user = db.get("select u from User u where u.userName = '" + username + "'");
		if ( user != null ) {
			System.out.println("get user from database: " + user.getFirstName());
			cu.setFirstName(user.getFirstName());
			cu.setLastName(user.getLastName());
			cu.setUsername(user.getUserName());
			cu.setPassword("123456");
			Role r = new Role();
			r.setName("USER");
			List<Role> roles = new ArrayList<Role>();
			roles.add(r);
			cu.setAuthorities(roles);
		} else {
			cu.setUsername("");
			cu.setPassword("");
		}

		return cu;
	}

}
