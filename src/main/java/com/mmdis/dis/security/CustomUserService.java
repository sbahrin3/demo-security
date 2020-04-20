package com.mmdis.dis.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserService implements UserDetailsService {
	 
    @Autowired
    private UserDAOImpl userDao;
    
    public CustomUserService() {
    	System.out.println("custom user service..");
    }
    
   public CustomUser loadUserByUsername(String username) throws UsernameNotFoundException {
       return userDao.loadUserByUsername(username);
   }

}
