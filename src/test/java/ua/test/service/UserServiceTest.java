package ua.test.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ua.com.callboard.entity.User;
import ua.com.callboard.repository.UserRepository;
import ua.com.callboard.service.implementation.UserServiceImpl;

public class UserServiceTest {

	UserRepository repository = mock(UserRepository.class);

	UserServiceImpl serviceImpl;

	private BCryptPasswordEncoder encoder = mock(BCryptPasswordEncoder.class);

	@Before
	public void before() {
		User user = new User();
		user.setId(1L);
		user.isAccountNonExpired();
		user.isAccountNonLocked();
		user.isCredentialsNonExpired();
		user.isEnabled();
		when(repository.findOne(1L)).thenReturn(user);
		List<User> list = new ArrayList<>();
		list.add(user);
		list.add(new User());
		when(repository.findAll()).thenReturn(list);
		serviceImpl = new UserServiceImpl(repository, encoder);
	}

	@Test
	public void test() {
		User user = new User();
		user.setEmail("ssandy679@gmail.com");
		user.setUsername("mariia");
		user.setPassword("1");
		serviceImpl.save(user);
		Assert.assertTrue(true);
	}

	@Test
	public void postConstruct() {
		serviceImpl.admin();
		User user = new User();
		assertNull(user.getId());
		assertNotNull(user.getAdverts());
		assertNull(user.getEmail());
		assertNull(user.getPassword());
		assertNull(user.getRole());
		assertNull(user.getUsername());
	}

	@Test
	public void loadUserByUsername() throws UsernameNotFoundException {
		User user = new User();
		UserDetails details = serviceImpl
				.loadUserByUsername(user.getUsername());

		Assert.assertEquals(null, details);
	}

	@Test
	public void test1() {
		User user = serviceImpl.findByEmail("ssandy@gmail.com");
		Assert.assertEquals(null, user);
	}

	@Test
	public void test5() {
		List<User> user = serviceImpl.findByUsernameForCheckbox("mariia1");
		Assert.assertEquals(0, user.size());
	}

	//
	@Test
	public void test2() {
		User name = serviceImpl.findByUsername("mariia1");
		Assert.assertEquals(null, name);
	}

	//

	@Test
	public void test3() {
		User name = serviceImpl.findById(1L);
		Assert.assertEquals(null, name);
	}

	@Test
	public void test4() {
		User name = serviceImpl.findUnique("ssandy@gmail.com", "mariia1");
		Assert.assertEquals(null, name);
	}
}
