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

import org.springframework.stereotype.Repository;

import mypack.model.Appointment;
import mypack.model.Appointment_;
import mypack.repository.custom.AppointmentCustomRepository;

@Repository
public class AppointmentCustomRepositoryImpl implements AppointmentCustomRepository {
	@PersistenceContext
	EntityManager em;

	public List<Appointment> getAppointments(Long userId, Long employerId, Date startTime, Boolean deny,
			Boolean complete) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Appointment> cq = cb.createQuery(Appointment.class);
		Root<Appointment> root = cq.from(Appointment.class);

		List<Predicate> filters = new ArrayList<>();

		if (userId != null)
			filters.add(cb.equal(root.get(Appointment_.user), userId));
		if (employerId != null)
			filters.add(cb.equal(root.get(Appointment_.employer), employerId));
		if (startTime != null)
			filters.add(cb.greaterThanOrEqualTo(root.get(Appointment_.startTime), startTime));
		if (deny != null)
			filters.add(cb.equal(root.get(Appointment_.deny), deny));
		if (complete != null)
			filters.add(cb.equal(root.get(Appointment_.complete), complete));

		Predicate predicate = cb.and(filters.toArray(new Predicate[0]));

		cq.select(root).where(predicate);
		TypedQuery<Appointment> query = em.createQuery(cq);

		return query.getResultList();
	}

}
