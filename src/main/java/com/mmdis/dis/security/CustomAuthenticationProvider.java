package com.mmdis.dis.security;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
    private CustomUserService userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        
        System.out.println("name: " + username);
        System.out.println("password: " + password);
        
        CustomUser user = userService.loadUserByUsername(username);
        System.out.println(user);
        
        if (user == null || !user.getUsername().equalsIgnoreCase(username)) {
        	throw new BadCredentialsException("Username not found.");
        }
 
        if (!password.equals(user.getPassword())) {
        	throw new BadCredentialsException("Wrong password.");
        }
        
        System.out.println(user.getUsername());
        System.out.println(user.getAuthorities());
        
        List<Role> roles = (List<Role>) user.getAuthorities();
        for ( Role r : roles ) {
        	System.out.println(r.getName());
        }
        
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        
        return new UsernamePasswordAuthenticationToken(user, password, authorities);
        

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
