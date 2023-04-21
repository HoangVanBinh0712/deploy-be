package mypack.repository.custom.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Repository;

import mypack.dto.ProfileDTO;
import mypack.model.Profile;
import mypack.model.Profile_;
import mypack.model.User;
import mypack.model.User_;
import mypack.payload.ListWithPagingResponse;
import mypack.repository.custom.ProfileSearchCustomRepository;
import mypack.utility.Page;
import mypack.utility.datatype.EExperience;
import mypack.utility.datatype.EMethod;
import mypack.utility.datatype.EPosition;

@Repository
public class ProfileSearchCustomRepositoryImpl implements ProfileSearchCustomRepository {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    ModelMapper mapper;

    @Override
    public ListWithPagingResponse<ProfileDTO> profileSearch(String keyword, EMethod method, EPosition position,
            EExperience experience,
            Long industryId, Long cityId, Page page) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Profile> cq = cb.createQuery(Profile.class);
        Root<Profile> root = cq.from(Profile.class);
        Join<Profile, User> join = root.join(Profile_.user, JoinType.LEFT);
        List<Predicate> filters = new ArrayList<>();
        filters.add(cb.equal(root.get(Profile_.isPublic), true));

        if (StringUtils.isNotBlank(keyword))
            filters.add(cb.like(root.get(Profile_.name), "%" + keyword + "%"));
        if (method != null)
            filters.add(cb.equal(root.get(Profile_.method), method));
        if (position != null)
            filters.add(cb.equal(root.get(Profile_.position), position));
        if (experience != null)
            filters.add(cb.equal(root.get(Profile_.experience), experience));

        if (industryId != null)
            filters.add(cb.equal(join.get(User_.industry), industryId));
        if (cityId != null)
            filters.add(cb.equal(join.get(User_.city), cityId));
        if (page.getSort() != null) {
            cq.orderBy(QueryUtils.toOrders(page.getSort(), root, cb));
        }
        Predicate predicate = cb.and(filters.toArray(new Predicate[0]));

        cq.select(root).where(predicate);
        TypedQuery<Profile> query = em.createQuery(cq);

        query.setFirstResult(page.getPageNumber() * page.getPageSize());
        query.setMaxResults(page.getPageSize());

        List<Profile> data = query.getResultList();
        ListWithPagingResponse<ProfileDTO> res = new ListWithPagingResponse<>(page.getPageNumber() + 1,
                page.getTotalPage(), page.getPageSize(),
                data.stream().map(x -> mapper.map(x, ProfileDTO.class)).toList());

        return res;
    }

    @Override
    public Long profileCountBeforeSearch(String keyword, EMethod method, EPosition position, EExperience experience,
            Long industryId, Long cityId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Profile> root = cq.from(Profile.class);
        Join<Profile, User> join = root.join(Profile_.user, JoinType.LEFT);
        List<Predicate> filters = new ArrayList<>();
        filters.add(cb.equal(root.get(Profile_.isPublic), true));

        if (StringUtils.isNotBlank(keyword))
            filters.add(cb.like(root.get(Profile_.name), "%" + keyword + "%"));
        if (method != null)
            filters.add(cb.equal(root.get(Profile_.method), method));
        if (position != null)
            filters.add(cb.equal(root.get(Profile_.position), position));
        if (experience != null)
            filters.add(cb.equal(root.get(Profile_.experience), experience));

        if (industryId != null)
            filters.add(cb.equal(join.get(User_.industry), industryId));
        if (cityId != null)
            filters.add(cb.equal(join.get(User_.city), cityId));

        Predicate predicate = cb.and(filters.toArray(new Predicate[0]));

        cq.select(cb.count(join)).where(predicate);
        Long res = em.createQuery(cq).getSingleResult();

        return res;
    }

}
