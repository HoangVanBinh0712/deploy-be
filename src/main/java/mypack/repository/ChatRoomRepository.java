package mypack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mypack.model.ChatRoom;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

	@Query(value = "select * from chat_room where (user1 = :user1 and user2 = :user2) or (user1 = :user2 and user2 = :user1) limit 1", nativeQuery = true)
	ChatRoom getSingeChatRoom(@Param("user1") Long user1, @Param("user2") Long user2);

}
