package mypack.repository.custom.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Repository;

import mypack.model.Post;
import mypack.model.Post_;
import mypack.repository.custom.PostSearchCustomRepository;
import mypack.utility.Page;
import mypack.utility.datatype.ECurrency;
import mypack.utility.datatype.EExperience;
import mypack.utility.datatype.EGender;
import mypack.utility.datatype.EMethod;
import mypack.utility.datatype.EPosition;
import mypack.utility.datatype.EStatus;

@Repository
public class PostSearchCustomRepositoryImpl implements PostSearchCustomRepository {

	@PersistenceContext
	EntityManager em;

	public List<Post> postSearch(String keyword, Long recruit, Long salary, EMethod method, EPosition position,
			EExperience experience, EGender gender, ECurrency currency, Long authorId, Long industryId, Long cityId,
			EStatus status, Date expirationDate, Date startDate, Long serviceId, Page page) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Post> cq = cb.createQuery(Post.class);
		Root<Post> root = cq.from(Post.class);
		List<Predicate> lstPredicate = new ArrayList<>();

		if (StringUtils.isNotBlank(keyword))
			lstPredicate.add(cb.like(root.get(Post_.title), "%" + keyword + "%"));

		if (recruit != null)
			lstPredicate.add(cb.greaterThanOrEqualTo(root.get(Post_.recruit), recruit));

		if (salary != null)
			lstPredicate.add(cb.greaterThanOrEqualTo(root.get(Post_.salary), salary));

		if (method != null)
			lstPredicate.add(cb.equal(root.get(Post_.method), method));
		if (position != null)
			lstPredicate.add(cb.equal(root.get(Post_.position), position));
		if (experience != null)
			lstPredicate.add(cb.equal(root.get(Post_.experience), experience));
		if (gender != null)
			lstPredicate.add(cb.equal(root.get(Post_.gender), gender));
		if (currency != null)
			lstPredicate.add(cb.equal(root.get(Post_.currency), currency));

		if (authorId != null)
			lstPredicate.add(cb.equal(root.get(Post_.author), authorId));

		if (industryId != null)
			lstPredicate.add(cb.equal(root.get(Post_.industry), industryId));
		if (cityId != null)
			lstPredicate.add(cb.equal(root.get(Post_.city), cityId));

		if (expirationDate != null)
			lstPredicate.add(cb.greaterThanOrEqualTo(root.get(Post_.expirationDate), expirationDate));
		else {
			Date today = new Date();
			lstPredicate.add(cb.greaterThanOrEqualTo(root.get(Post_.expirationDate), today));
		}
		if (startDate != null)
			lstPredicate.add(cb.greaterThanOrEqualTo(root.get(Post_.createDate), startDate));

		if (serviceId != null)
			lstPredicate.add(cb.equal(root.get(Post_.service), serviceId));
		if (status != null)
			lstPredicate.add(cb.equal(root.get(Post_.status), status));
		else {
			lstPredicate.add(cb.or(cb.equal(root.get(Post_.status), EStatus.ACTIVE),
					cb.equal(root.get(Post_.status), EStatus.DISABLE)));
		}

		if (page.getSort() != null) {
			cq.orderBy(QueryUtils.toOrders(page.getSort(), root, cb));
		}
		Predicate predicate = cb.and(lstPredicate.toArray(new Predicate[0]));

		cq.select(root).where(predicate);
		TypedQuery<Post> query = em.createQuery(cq);

		query.setFirstResult(page.getPageNumber() * page.getPageSize());
		query.setMaxResults(page.getPageSize());

		return query.getResultList();
	}

	public Long postCountBeforeSearch(String keyword, Long recruit, Long salary, EMethod method, EPosition position,
			EExperience experience, EGender gender, ECurrency currency, Long authorId, Long industryId, Long cityId,
			EStatus status, Date expirationDate,
			Date startDate, Long serviceId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Post> root = cq.from(Post.class);
		List<Predicate> lstPredicate = new ArrayList<>();

		if (StringUtils.isNotBlank(keyword))
			lstPredicate.add(cb.like(root.get(Post_.title), "%" + keyword + "%"));

		if (recruit != null)
			lstPredicate.add(cb.greaterThanOrEqualTo(root.get(Post_.recruit), recruit));

		if (salary != null)
			lstPredicate.add(cb.greaterThanOrEqualTo(root.get(Post_.salary), salary));

		if (method != null)
			lstPredicate.add(cb.equal(root.get(Post_.method), method));
		if (position != null)
			lstPredicate.add(cb.equal(root.get(Post_.position), position));
		if (experience != null)
			lstPredicate.add(cb.equal(root.get(Post_.experience), experience));
		if (gender != null)
			lstPredicate.add(cb.equal(root.get(Post_.gender), gender));
		if (currency != null)
			lstPredicate.add(cb.equal(root.get(Post_.currency), currency));

		if (authorId != null)
			lstPredicate.add(cb.equal(root.get(Post_.author), authorId));

		if (industryId != null)
			lstPredicate.add(cb.equal(root.get(Post_.industry), industryId));
		if (cityId != null)
			lstPredicate.add(cb.equal(root.get(Post_.city), cityId));

		if (expirationDate != null)
			lstPredicate.add(cb.greaterThanOrEqualTo(root.get(Post_.expirationDate), expirationDate));
		else {
			Date today = new Date();
			lstPredicate.add(cb.greaterThanOrEqualTo(root.get(Post_.expirationDate), today));
		}
		if (startDate != null)
			lstPredicate.add(cb.greaterThanOrEqualTo(root.get(Post_.createDate), startDate));

		if (serviceId != null)
			lstPredicate.add(cb.equal(root.get(Post_.service), serviceId));
		if (status != null)
			lstPredicate.add(cb.equal(root.get(Post_.status), status));
		else {
			lstPredicate.add(cb.or(cb.equal(root.get(Post_.status), EStatus.ACTIVE),
					cb.equal(root.get(Post_.status), EStatus.DISABLE)));
		}

		Predicate predicate = cb.and(lstPredicate.toArray(new Predicate[0]));

		cq.select(cb.count(root)).where(predicate);
		TypedQuery<Long> query = em.createQuery(cq);

		return query.getSingleResult();
	}
}
