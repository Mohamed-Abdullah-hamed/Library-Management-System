package com.main.library.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.main.library.entity.AppUser;

public class CustomeUserDetails implements UserDetails{

		
	private AppUser user;
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
	        return user.getRoles().stream()
	                    .map(role -> new SimpleGrantedAuthority(role.getName()))
	                    .collect(Collectors.toSet());
	    
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getName();
	}

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	public CustomeUserDetails() {
		super();
	}

	public CustomeUserDetails(AppUser user) {
		super();
		this.user = user;
	}

}
