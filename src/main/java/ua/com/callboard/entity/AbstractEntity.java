package ua.com.callboard.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Entities may inherit from superclasses that contain persistent state and
 * mapping information but are not entities. That is, the superclass is not
 * decorated with the @Entity annotation and is not mapped as an entity by the
 * Java Persistence provider These superclasses are most often used when you
 * have state and mapping information common to multiple entity classes.
 */
@MappedSuperclass
public class AbstractEntity {

	/**
	 *
	 * @param id indicating the member field below is the primary key of entity
	 *            which are inherited from this superclass.
	 *  The @GeneratedValue annotation is to configure the way of increment of the
	 *  specified column(field).
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/*
    /**********************************************************
    /* Method get() and set()
    /**********************************************************
     */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	/*
    /**********************************************************
    /* Overridden standard methods
    /**********************************************************
     */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractEntity other = (AbstractEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
