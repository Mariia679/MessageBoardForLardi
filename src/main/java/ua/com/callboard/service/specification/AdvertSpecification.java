package ua.com.callboard.service.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.com.callboard.dto.filter.AdvertFilter;
import ua.com.callboard.entity.Advert;
import ua.com.callboard.entity.User;

public class AdvertSpecification implements Specification<Advert> {

	private final AdvertFilter filter;

	private final List<Predicate> predicates = new ArrayList<>();

	public AdvertSpecification(AdvertFilter filter) {
		this.filter = filter;

	}

	/**
	 * Creates a WHERE clause for a query of the referenced entity in form of a
	 * {@link Predicate} for the given {@link Root} and {@link CriteriaQuery}.
	 * 
	 * @param root
	 * @param query
	 * @return a {@link Predicate}, must not be {@literal null}.
	 */

	@Override
	public Predicate toPredicate(Root<Advert> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		fetch(root, query);
		findByNameAdvert(root, query);
		findByNameUser(root, query, cb);
		findByTopic(root, query);
		findByUser(root, query);
		if (predicates.isEmpty())
			return null;
		Predicate[] array = new Predicate[predicates.size()];
		array = predicates.toArray(array);
		return cb.and(array);
	}

	private void fetch(Root<Advert> root, CriteriaQuery<?> query) {
		if (!query.getResultType().equals(Long.class)) {
			query.distinct(true);
			root.fetch("user");
			root.fetch("topic");

		}
	}

	public void findByNameAdvert(Root<Advert> root, CriteriaQuery<?> query) {
		if (!filter.getNameAdvert().isEmpty()) {
			predicates.add(root.get("name").in(filter.getNameAdvert()));
		}
	}

	private void findByNameUser(Root<Advert> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		if (!filter.getNameUser().isEmpty()) {
			Join<User, Advert> join = root.join("user");
			predicates.add(cb.like(join.<String>get("username"), filter.getNameUser()
					+ "%"));
		}
	}

	private void findByTopic(Root<Advert> root, CriteriaQuery<?> query) {
		if (!filter.getTopicId().isEmpty()) {
			predicates.add(root.get("topic").in(filter.getTopicId()));
		}
	}

	private void findByUser(Root<Advert> root, CriteriaQuery<?> query) {
		if (!filter.getUserId().isEmpty()) {
			predicates.add(root.get("user").in(filter.getUserId()));
		}
	}

}
