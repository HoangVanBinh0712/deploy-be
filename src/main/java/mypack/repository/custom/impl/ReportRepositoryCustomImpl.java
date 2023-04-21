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

import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Repository;

import mypack.model.Report;
import mypack.model.Report_;
import mypack.repository.custom.ReportRepositoryCustom;
import mypack.utility.Page;

@Repository
public class ReportRepositoryCustomImpl implements ReportRepositoryCustom {
    @PersistenceContext
    EntityManager em;

    @Override
    public Long countBeforeSearchReport(Long postId, Boolean handle, Date date, Date handleDate) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Report> root = cq.from(Report.class);
        List<Predicate> lstPredicate = new ArrayList<>();
        if (postId != null)
            lstPredicate.add(cb.equal(root.get(Report_.post), postId));
        if (handle != null)
            lstPredicate.add(cb.equal(root.get(Report_.handle), handle));

        if (date != null)
            lstPredicate.add(cb.greaterThanOrEqualTo(root.get(Report_.date), date));
        
        if (handleDate != null)
            lstPredicate.add(cb.greaterThanOrEqualTo(root.get(Report_.date), handleDate));
        
        Predicate predicate = cb.and(lstPredicate.toArray(new Predicate[0]));

        cq.select(cb.count(root)).where(predicate);
        TypedQuery<Long> query = em.createQuery(cq);

        return query.getSingleResult();

    }

    @Override
    public List<Report> searchReport(Long postId, Boolean handle, Date date,Date handleDate, Page page) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Report> cq = cb.createQuery(Report.class);
        Root<Report> root = cq.from(Report.class);
        List<Predicate> lstPredicate = new ArrayList<>();
        if (postId != null)
            lstPredicate.add(cb.equal(root.get(Report_.post), postId));
        if (handle != null)
            lstPredicate.add(cb.equal(root.get(Report_.handle), handle));

        if (date != null)
            lstPredicate.add(cb.greaterThanOrEqualTo(root.get(Report_.date), date));
        if (handleDate != null)
            lstPredicate.add(cb.greaterThanOrEqualTo(root.get(Report_.date), handleDate));
        if (page.getSort() != null) {
            cq.orderBy(QueryUtils.toOrders(page.getSort(), root, cb));
        }
        Predicate predicate = cb.and(lstPredicate.toArray(new Predicate[0]));

        cq.select(root).where(predicate);
        TypedQuery<Report> query = em.createQuery(cq);
        query.setFirstResult(page.getPageNumber() * page.getPageSize());
        query.setMaxResults(page.getPageSize());
        return query.getResultList();

    }
}
