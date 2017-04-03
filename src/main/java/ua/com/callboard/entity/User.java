package ua.com.callboard.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "_user", indexes = @Index(columnList = "username"))
public class User extends AbstractEntity implements UserDetails {

	/**
	 * @param serialVersionUID
	 *            The serialization runtime associates with each serializable
	 *            class a version number, which is used during deserialization
	 *            to verify that the sender and receiver of a serialized object
	 *            have loaded classes for that object that are compatible with
	 *            respect to serialization. If the receiver has loaded a class
	 *            for the object that has a different serialVersionUID than that
	 *            of the corresponding sender's class, then deserialization will
	 *            result in an InvalidClassException.
	 */

	private static final long serialVersionUID = -2048537316822556266L;

	/**
	 * @param email
	 *            Contains email
	 */

	private String email;

	/**
	 * @param password
	 *            Contains user password
	 */

	private String password;

	/**
	 * @param username
	 *            Contains user login
	 */

	private String username;

	/**
	 * Specifies that a persistent property or field should be persisted as a
	 * enumerated type.
	 * 
	 */
	@Enumerated
	@Column(name = "_role")
	private Role role;

	/**
	 * @param adverts
	 *            contains a collection of ads An entity instance can be related
	 *            to multiple instances of the other entities.
	 * MappedBy says that this party is not responsible for the connection
	 */

	@OneToMany(mappedBy = "user")
	private List<Advert> adverts = new ArrayList<>();

	/**
	 * Returns the authorities granted to the user. Cannot return
	 * <code>null</code>.
	 *
	 * @return the authorities, sorted by natural key (never <code>null</code>)
	 */

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> list = new ArrayList<>();
		list.add(new SimpleGrantedAuthority(role.name()));
		return list;
	}

	/**
	 * Indicates whether the user's account has expired. An expired account
	 * cannot be authenticated.
	 *
	 * @return <code>true</code> if the user's account is valid (ie
	 *         non-expired), <code>false</code> if no longer valid (ie expired)
	 */

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * Indicates whether the user is locked or unlocked. A locked user cannot be
	 * authenticated.
	 *
	 * @return <code>true</code> if the user is not locked, <code>false</code>
	 *         otherwise
	 */

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * Indicates whether the user's credentials (password) has expired. Expired
	 * credentials prevent authentication.
	 *
	 * @return <code>true</code> if the user's credentials are valid (ie
	 *         non-expired), <code>false</code> if no longer valid (ie expired)
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * Indicates whether the user is enabled or disabled. A disabled user cannot
	 * be authenticated.
	 *
	 * @return <code>true</code> if the user is enabled, <code>false</code>
	 *         otherwise
	 */

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	/*
    /**********************************************************
    /* Method get() and set()
    /**********************************************************
     */

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Returns the password used to authenticate the user.
	 *
	 * @return the password
	 */

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Returns the username used to authenticate the user. Cannot return
	 * <code>null</code> .
	 *
	 * @return the username (never <code>null</code>)
	 */

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Advert> getAdverts() {
		return adverts;
	}

	public void setAdverts(List<Advert> adverts) {
		this.adverts = adverts;
	}

}
