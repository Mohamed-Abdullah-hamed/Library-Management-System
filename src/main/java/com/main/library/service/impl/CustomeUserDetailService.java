package com.main.library.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.main.library.entity.AppUser;
import com.main.library.repo.AppUserRepo;
import com.main.library.security.CustomeUserDetails;

@Service
public class CustomeUserDetailService implements UserDetailsService {

	@Autowired
	private AppUserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user = userRepo.findByName(username);
		if (user != null) {
			CustomeUserDetails userDetails = new CustomeUserDetails(user);
			return userDetails;
		}
		throw new UsernameNotFoundException("User not found");
	}

}
