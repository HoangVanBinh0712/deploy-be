package mypack.repository.custom.impl;

import java.util.ArrayList;
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

import mypack.model.User;
import mypack.model.User_;
import mypack.repository.custom.UserSerachCustomRepository;
import mypack.utility.Page;
import mypack.utility.datatype.ERole;

@Repository
public class UserSerachCustomRepositoryImpl implements UserSerachCustomRepository {
    @PersistenceContext
    EntityManager em;

    @Override
    public Long countUser(String email, String name, String phone, ERole role, Boolean status, Long industryId,
            Long cityId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<User> root = cq.from(User.class);
        List<Predicate> lstPredicate = new ArrayList<>();
        if (StringUtils.isNotBlank(email))
            lstPredicate.add(cb.like(root.get(User_.email), "%" + email + "%"));
        if (StringUtils.isNotBlank(name))
            lstPredicate.add(cb.like(root.get(User_.name), "%" + name + "%"));
        if (StringUtils.isNotBlank(phone))
            lstPredicate.add(cb.like(root.get(User_.phone), "%" + phone + "%"));

        if (industryId != null)
            lstPredicate.add(cb.equal(root.get(User_.industry), industryId));
        if (cityId != null)
            lstPredicate.add(cb.equal(root.get(User_.city), cityId));
        if (role != null)
            lstPredicate.add(cb.equal(root.get(User_.role), role));
        if (status != null)
            lstPredicate.add(cb.equal(root.get(User_.active), status));

        Predicate predicate = cb.and(lstPredicate.toArray(new Predicate[0]));

        cq.select(cb.count(root)).where(predicate);
        TypedQuery<Long> query = em.createQuery(cq);

        return query.getSingleResult();
    }

    @Override
    public List<User> getSearchUser(String email, String name, String phone, ERole role, Boolean status,
            Long industryId, Long cityId,
            Page page) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        List<Predicate> lstPredicate = new ArrayList<>();
        if (StringUtils.isNotBlank(email))
            lstPredicate.add(cb.like(root.get(User_.email), "%" + email + "%"));
        if (StringUtils.isNotBlank(name))
            lstPredicate.add(cb.like(root.get(User_.name), "%" + name + "%"));
        if (StringUtils.isNotBlank(phone))
            lstPredicate.add(cb.like(root.get(User_.phone), "%" + phone + "%"));

        if (industryId != null)
            lstPredicate.add(cb.equal(root.get(User_.industry), industryId));
        if (cityId != null)
            lstPredicate.add(cb.equal(root.get(User_.city), cityId));
        if (role != null)
            lstPredicate.add(cb.equal(root.get(User_.role), role));
        if (status != null)
            lstPredicate.add(cb.equal(root.get(User_.active), status));

        if (page.getSort() != null) {
            cq.orderBy(QueryUtils.toOrders(page.getSort(), root, cb));
        }
        Predicate predicate = cb.and(lstPredicate.toArray(new Predicate[0]));

        cq.select(root).where(predicate);
        TypedQuery<User> query = em.createQuery(cq);
        query.setFirstResult(page.getPageNumber() * page.getPageSize());
        query.setMaxResults(page.getPageSize());
        return query.getResultList();
    }
}
