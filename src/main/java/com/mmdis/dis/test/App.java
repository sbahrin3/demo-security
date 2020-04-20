package com.mmdis.dis.test;

import java.util.List;

import com.mmdis.dis.entity.User;
import com.mmdis.dis.jpa.Persistence;

public class App {
	
	public static void main(String[] args) {
		
		Persistence db = Persistence.db();
		
		List<User> users = db.list("select u from User u");
		
		users.stream().forEach(u -> System.out.println(u.getUserName() + ", " + u.getId()) );
		
		db.close();
	}

}
