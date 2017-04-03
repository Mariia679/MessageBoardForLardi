package ua.com.callboard.service;

import java.util.List;

import ua.com.callboard.entity.User;

public interface UserService {

	void save(User t);

	User findByEmail(String email);

	User findByUsername(String username);
	
	List<User> findByUsernameForCheckbox(String username);

	User findUnique(String email, String username);

	User findById(Long id);

}
