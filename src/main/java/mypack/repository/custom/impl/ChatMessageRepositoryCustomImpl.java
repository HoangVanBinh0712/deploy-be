package mypack.repository.custom.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import mypack.model.ChatMessage;
import mypack.model.ChatMessage_;
import mypack.model.ChatRoom;
import mypack.model.ChatRoom_;
import mypack.repository.custom.ChatMessageRepositoryCustom;
import mypack.utility.Page;

@Repository
public class ChatMessageRepositoryCustomImpl implements ChatMessageRepositoryCustom {
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<ChatMessage> get(Long user1Id, Long user2Id, Long chatRoomId, Page page, Long messageId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ChatMessage> cq = cb.createQuery(ChatMessage.class);
		Root<ChatMessage> root = cq.from(ChatMessage.class);

		Predicate p1 = cb.equal(root.get(ChatMessage_.chatRoom), chatRoomId);
		if (messageId != null) {
			Predicate p2 = cb.lessThan(root.get(ChatMessage_.id), messageId);
			p1 = cb.and(p1, p2);
		}	
		
		cq.select(root).where(p1);

		cq.orderBy(cb.desc(root.get(ChatMessage_.time)));

		TypedQuery<ChatMessage> query = em.createQuery(cq);
		if (page != null) {
//			query.setFirstResult(page.getPageNumber() * page.getPageSize());
			query.setMaxResults(page.getPageSize());
		}

		return query.getResultList();
	}

	@Override
	public Long count(Long user1Id, Long user2Id, Long chatRoomId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<ChatMessage> root = cq.from(ChatMessage.class);

		cq.select(cb.count(root)).where(cb.equal(root.get(ChatMessage_.chatRoom), chatRoomId));

		return em.createQuery(cq).getSingleResult();
	}

	@Override
	public List<ChatRoom> getChatRoom(Long userId, Page page) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ChatRoom> cq = cb.createQuery(ChatRoom.class);
		Root<ChatRoom> root = cq.from(ChatRoom.class);

		cq.select(root)
				.where(cb.or(cb.equal(root.get(ChatRoom_.user1), userId), cb.equal(root.get(ChatRoom_.user2), userId)));

		cq.orderBy(cb.desc(root.get(ChatRoom_.lastChat)));

		TypedQuery<ChatRoom> query = em.createQuery(cq);
		if (page != null) {
			query.setFirstResult(page.getPageNumber() * page.getPageSize());
			query.setMaxResults(page.getPageSize());
		}

		return query.getResultList();
	}

	@Override
	public Long countChatRoom(Long userId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<ChatRoom> root = cq.from(ChatRoom.class);

		cq.select(cb.count(root))
				.where(cb.or(cb.equal(root.get(ChatRoom_.user1), userId), cb.equal(root.get(ChatRoom_.user2), userId)));

		return em.createQuery(cq).getSingleResult();
	}
}
