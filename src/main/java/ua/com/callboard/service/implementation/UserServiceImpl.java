package ua.com.callboard.service.implementation;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ua.com.callboard.entity.Role;
import ua.com.callboard.entity.User;
import ua.com.callboard.repository.UserRepository;
import ua.com.callboard.service.UserService;

@Service("userDetailsService")
public class UserServiceImpl implements UserDetailsService, UserService {

	/**
	 * @Autowired Fields are injected right after construction of a bean, before
	 *            any config methods are invoked.
	 */
	@Autowired
	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository,
			BCryptPasswordEncoder encoder) {
		this.userRepository = userRepository;
		this.encoder = encoder;
	}

	/**
	 * @param encoder
	 *            Implementation of PasswordEncoder that uses the BCrypt strong
	 *            hashing function.
	 */
	@Autowired
	private BCryptPasswordEncoder encoder;

	/**
	 * @Override from UserDetailsService
	 * @param username
	 *            the username identifying the user whose data is required.
	 *
	 * @return a fully populated user record (never <code>null</code>)
	 *
	 * @throws UsernameNotFoundException
	 *             if the user could not be found or the user has no
	 *             GrantedAuthority
	 */
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		return userRepository.findByUsername(username);
	}

	@Override
	public void save(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setRole(Role.ROLE_USER);
		userRepository.save(user);
	}

	/**
	 * The PostConstruct annotation is used on a method that needs to be
	 * executed after dependency injection is done to perform any
	 * initialization.
	 */
	@PostConstruct
	public void admin() {
		User user = userRepository.findByUsername("admin");
		if (user == null) {
			user = new User();
			user.setEmail("");
			user.setPassword(encoder.encode("admin"));
			user.setRole(Role.ROLE_ADMIN);
			user.setUsername("admin");
			userRepository.save(user);
		}
	}

	@Override
	public User findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public List<User> findByUsernameForCheckbox(String username) {
		return userRepository.findByUsernameForCheckbox(username);
	}

	// Method used for validation

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User findUnique(String email, String username) {
		return userRepository.findUnique(email, username);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
