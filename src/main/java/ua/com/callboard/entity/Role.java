package ua.com.callboard.entity;

/**
 * 
 * A role is an abstract name for the permission to access a particular set of
 * resources in an application. A role can be compared to a key that can open a
 * lock. Many people might have a copy of the key. The lock doesn’t care who you
 * are, only that you have the right key.
 *
 */
public enum Role {
	/**
	 * 
	 */
	ROLE_USER, ROLE_ADMIN;
}
