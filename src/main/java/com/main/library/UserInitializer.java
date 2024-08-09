package com.main.library;

import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.main.library.entity.AppRole;
import com.main.library.entity.AppUser;
import com.main.library.repo.AppRoleRepo;
import com.main.library.repo.AppUserRepo;

@Component
public class UserInitializer implements CommandLineRunner {

	private final AppUserRepo userRepo;
	private final PasswordEncoder passwordEncoder;
	private final AppRoleRepo roleRepo;

	@Override
	public void run(String... args) throws Exception {

		List<AppUser> users = userRepo.findAll();
		if (users.size() == 0) {
			AppRole userRole = new AppRole();
			userRole.setName("ROLE_USER");
			
			AppRole adminRole = new AppRole();
			adminRole.setName("ROLE_ADMIN");
			
			roleRepo.save(userRole);
			roleRepo.save(adminRole);

			AppUser admin = new AppUser();
			admin.setName("Libarian");
			admin.setPassword(passwordEncoder.encode("libarian123"));
			admin.setRoles(Set.of(userRole,adminRole));
			userRepo.save(admin);
			AppUser user = new AppUser();
			user.setName("Mohamed");
			user.setPassword(passwordEncoder.encode("mohamed123"));
			user.setRoles(Set.of(userRole));
			userRepo.save(user);
		}
	}

	public UserInitializer(AppUserRepo userRepo, PasswordEncoder passwordEncoder, AppRoleRepo roleRepo) {
		super();
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
		this.roleRepo = roleRepo;
	}

}
