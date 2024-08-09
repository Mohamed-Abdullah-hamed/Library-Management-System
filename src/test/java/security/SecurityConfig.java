package security;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@TestConfiguration
public class SecurityConfig {

	@Bean
	@Primary
	public UserDetailsService userDetailsService() {
		var user = User.withUsername("testuser").password("password").roles("USER").build();
		var admin = User.withUsername("testaadmin").password("password").roles("ADMIN").build();
		
		return new InMemoryUserDetailsManager(user,admin);
	}

	@Bean
	@Primary
	public PasswordEncoder passwordEncoder() {
		// For testing, using NoOpPasswordEncoder is fine
		return NoOpPasswordEncoder.getInstance();
	}
}
