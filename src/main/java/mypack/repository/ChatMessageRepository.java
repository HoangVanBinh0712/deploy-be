package mypack.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mypack.model.ChatMessage;
import mypack.repository.custom.ChatMessageRepositoryCustom;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long>, ChatMessageRepositoryCustom {

	@Transactional
	@Modifying
	@Query(value = "update chat_message set user1_seen = true, user2_seen = true where chat_room_id = :roomId and sender_id != :userId", nativeQuery = true)
	void updateSeenForMessages(@Param("roomId") Long roomId, @Param("userId") Long userId);

	
	@Transactional
	@Modifying
	@Query(value = "update chat_message set user1_seen = true, user2_seen = true where id = :id", nativeQuery = true)
	void updateSeenForMessage(@Param("id") Long chatId);
	
	@Query(value = "select a.* from chat_message a, (select max(id)  as id from chat_message where chat_room_id in (:roomIds) group by chat_room_id) b  where a.id = b.id and a.chat_room_id in ((select id from chat_room where user1=:userId or user2=:userId))  order by time desc", nativeQuery = true)
	List<ChatMessage> getNewestMessageByRoom(@Param("roomIds") List<Long> roomIds, @Param("userId") Long userId);
}
