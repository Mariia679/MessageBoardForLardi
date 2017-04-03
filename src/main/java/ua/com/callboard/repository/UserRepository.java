package ua.com.callboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.com.callboard.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findOne(Long id);

	User findByUsername(String username);
	
	@Query("SELECT u FROM User u WHERE u.username = ?1 ")
	List<User> findByUsernameForCheckbox(String username);

	@Query("SELECT u FROM User u WHERE u.email = ?1 AND u.username = ?2 ")
	User findUnique(String email, String username);

	User findByEmail(String email);

	User findById(Long id);
}
